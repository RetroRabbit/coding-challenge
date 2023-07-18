import { Component } from '@angular/core';
import { Location } from '@angular/common';
import { House } from 'src/app/models/house.model';
import { Store } from '@ngrx/store';
import { Character } from 'src/app/models/character.model';
import { getCharacter } from 'src/app/actions/character.actions';
import { asapScheduler } from 'rxjs/internal/scheduler/asap';
import { CharacterService } from 'src/app/services/character.service';
import { Observable, forkJoin, map } from 'rxjs';
@Component({
  selector: 'app-house-details',
  templateUrl: './house-details.component.html',
  styleUrls: ['./house-details.component.css']
})
export class HouseDetailsComponent {
  selectedHouse!: House;
  character !: Character;
  currentLord !: Character
  swornMembers: Character[] = [];
  hascurrentLord = false;

  currentCharacter$ = this.store.select('character');

  constructor(private store: Store<{ house: House, character: Character }>, private location: Location, private Cservice: CharacterService) { }
  ngOnInit() {
    this.swornMembers = []
    this.store.select('house').subscribe(state => {
      this.selectedHouse = state.house;
      console.log(this.selectedHouse.url)
      this.store.dispatch(getCharacter({ characterId: this.selectedHouse.currentLord }));
      this.store.select('character').subscribe(state => {
          this.currentLord = state.character
        })
    });
    this.GetSwornMembers(this.selectedHouse.swornMembers)
  }
   
  GetSwornMembers(swornMembers: string[]) {
    const swornMemberRequests: Observable<Character>[] = swornMembers.map(endpoint => {
      return this.Cservice.getCharacter(endpoint);
    });
    swornMemberRequests.forEach(observable => {
      observable.subscribe(character => {
        // console.log(character);
        this.swornMembers.push(character)
      });
    });
    console.log(this.swornMembers);
  } 

  GetMember(c : any) : Character{
    console.log(c)

    let newCharacter!: Character;
    return newCharacter;
  }
  GoBack() {
    this.location.back();
  }

  ReturnCharacter(c: any, url: any): Character | undefined {
    // this.GetCharacter(url);
    // console.log(c.character)

    let newCharacter: Character | undefined;
    return c.character;
  }
}
