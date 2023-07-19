import { Component, OnInit } from '@angular/core';
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
  private pageSize: number = 100;
  public houses: Array<House> = new Array<House>();
  public loading: boolean = false;
  public disableNext: boolean = false;
  public disablePrevious: boolean = false;
  public searchValue: string = "";

  constructor(private houseService: HouseService){}

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
          this.loading = false;
        }
      },
      error: (error) =>{
        this.loading = false;
      }
    })
  }

  next(){
    this.pageNumber +=1;
    this.getHouses();
  }
}
