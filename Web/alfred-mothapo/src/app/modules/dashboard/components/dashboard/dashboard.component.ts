import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { take } from 'rxjs';
import { House } from 'src/app/models/house';
import { HouseService } from 'src/app/services/house/house.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{

  private pageNumber: number = 1;
  private pageSize: number = 50;
  public houses: Array<House> = new Array<House>();
  public loading: boolean = false;
  public disableNext: boolean = false;
  public disablePrevious: boolean = false;
  public searchValue: string = "";

  constructor(
    private houseService: HouseService, 
    private messageService: MessageService,
    private router: Router){}

  ngOnInit(): void {
    this.getHouses();
  }
  
  getHouses(){
    this.loading = true;
    this.houseService.getHouses(this.pageNumber, this.pageSize)
    .pipe(take(1)).subscribe({
      next: (results) => {
        if (results && results.length > 0){
          if (this.houses && this.houses.length > 0){
            this.houses = this.houses.concat(results)
          }
          else {
            this.houses = results;
          }
        }
        this.loading = false;
      },
      error: (error) =>{
        this.showError(error.message);
        this.loading = false;
      }
    })
  }

  next(){
    this.pageNumber +=1;
    this.getHouses();
  }

  viewHouseDetails(url: string){
    var urlSplit = url.split("/");
    this.router.navigate([`/house/house-info/${urlSplit[urlSplit.length-1]}`])
  }

  showError(errorMessage: string) {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: `An error occured, details: ${errorMessage}` });
  } 

}
