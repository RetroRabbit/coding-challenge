import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';
import { take } from 'rxjs';
import { Character } from 'src/app/models/character';
import { House } from 'src/app/models/house';
import { CharacterService } from 'src/app/services/character/character.service';
import { HouseService } from 'src/app/services/house/house.service';

@Component({
  selector: 'app-house-info',
  templateUrl: './house-info.component.html',
  styleUrls: ['./house-info.component.css'],
  providers: [HouseService]
})
export class HouseInfoComponent implements OnInit{

  public currentHouse: House | undefined;
  public houseId: number = 0;
  public swornMembers: Array<Character> = new Array<Character>();
  public loading: boolean = false;

  constructor(
    private houseService: HouseService,
    private characterService: CharacterService,
    private messageService: MessageService, 
    private route: ActivatedRoute){}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.houseId = params['id'];
      this.getHouseDetails();
    });
  } 

  getHouseDetails(){
    this.loading = true;
    this.houseService.getHouseDetails(this.houseId)
    .pipe(take(1)).subscribe({
      next: (results) =>{
        this.currentHouse = results;

        if (this.currentHouse.swornMembers && this.currentHouse.swornMembers.length > 0){
          this.currentHouse.swornMembers.forEach(element => {
            this.characterService.getCharacter(element)
            .pipe(take(1)).subscribe({
              next: (resp)=>{
                this.swornMembers.push(resp);
                this.loading = false;
              }
            })
          });
        } else {
          this.loading = false;
        }
      },
      error: (err)=>{
        this.loading = false;
        this.showError(err.message);
      }    
    })
  }

  showError(errorMessage: string) {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: `An error occured, details: ${errorMessage}` });
  } 
}
