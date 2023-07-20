import { Component } from '@angular/core';
import { Location } from '@angular/common';
import { House } from 'src/app/models/house.model';
import { Store } from '@ngrx/store';
import { Character } from 'src/app/models/character.model';
import { getCharacter } from 'src/app/actions/character.actions';
import { CharacterService } from 'src/app/services/character.service';
import { Observable } from 'rxjs';
import { HouseService } from 'src/app/services/house.service';
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
  founder !: Character;
  heir !: Character;
  cadetBranches : House[] = [];
  overlord !: House;
  hascurrentLord = false;
  houseWords : string[] = [];
  currentCharacter$ = this.store.select('character');

  constructor(private store: Store<{ house: House, character: Character }>, private location: Location, private characterService: CharacterService, private houseService: HouseService) { }
  ngOnInit() {
    this.swornMembers = []
    this.store.select('house').subscribe(state => {
      this.selectedHouse = state.house;
      this.store.dispatch(getCharacter({ characterId: this.selectedHouse.currentLord }));
      this.store.select('character').subscribe(state => {
          this.currentLord = state.character
        })
    });
    this.GetSwornMembers(this.selectedHouse.swornMembers);
    this.GetFounder(this.selectedHouse.founder);
    this.GetHeir(this.selectedHouse.heir);
    this.GetCadetBranches(this.selectedHouse.cadetBranches);
    this.GetOverLord(this.selectedHouse.overlord);
  }
   
  GetSwornMembers(swornMembers: string[]) {
    const swornMemberRequests: Observable<Character>[] = swornMembers.map(endpoint => {
      return this.characterService.getCharacter(endpoint);
    });
    swornMemberRequests.forEach(observable => {
      observable.subscribe(character => {
        this.swornMembers.push(character)
      });
    });
  } 
  GetFounder(url : string){
    const founderRequest: Observable<Character>[] = [this.characterService.getCharacter(url)];
    founderRequest.forEach(observable => {
      observable.subscribe(character => {
       this.founder = character;
      });
    });
  }
  GetHeir(url : string){
    const founderRequest: Observable<Character>[] = [this.characterService.getCharacter(url)];
    founderRequest.forEach(observable => {
      observable.subscribe(character => {
       this.heir = character;
      });
    });
  }

  GetCadetBranches(cadetBranches: string[]) {
    const cadetBranchesRequst: Observable<House>[] = cadetBranches.map(endpoint => {
      return this.houseService.getAHouse(endpoint);
    });
    cadetBranchesRequst.forEach(observable => {
      observable.subscribe(character => {
        this.cadetBranches.push(character)
      });
    });
  }

  GetOverLord(url : string){
    const overlordRequest: Observable<House>[] = [this.houseService.getAHouse(url)];
    overlordRequest.forEach(observable => {
      observable.subscribe(house => {
       this.overlord = house;
      });
    });
  }
  GoBack() {
    this.location.back();

  }
}
