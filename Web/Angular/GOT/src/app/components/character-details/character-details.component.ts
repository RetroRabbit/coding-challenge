import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Book } from 'src/app/models/book.model';
import { Character } from 'src/app/models/character.model';
import { House } from 'src/app/models/house.model';
import { CharacterService } from 'src/app/services/character.service';

@Component({
  selector: 'app-character-details',
  templateUrl: './character-details.component.html',
  styleUrls: ['./character-details.component.css']
})
export class CharacterDetailsComponent {

  selectedCharacter !: Character
  spouse !: Character
  mother !: Character
  father !: Character
  allegiances : House[] = [];
  books : Book[] = [];
  povbooks : Book[] = [];
  constructor(private store : Store<{character : Character}>,  private characterService: CharacterService){}
  ngOnInit(){
    this.store.select('character').subscribe(state => {
      this.selectedCharacter = state.character;
      console.log(this.selectedCharacter)
    });
    if(this.selectedCharacter.spouse){this.GetSpouse(this.selectedCharacter.spouse)}
    if(this.selectedCharacter.mother){this.GetMother(this.selectedCharacter.mother)}
    if(this.selectedCharacter.father){this.GetFather(this.selectedCharacter.father)}
    if(this.selectedCharacter.books){this.GetBooks(this.selectedCharacter.books)}
  }

  GetBooks(urls : string[]){
    
  }
  GetAllegiances(){} 
  GetSpouse(url: string){
    const spouseRequest : Observable<Character>[] =[this.characterService.getCharacter(url)];
    spouseRequest.forEach(observable => {
      observable.subscribe(character => {
       this.spouse = character;
      });
    });
  }
  GetFather(url: string){
    const spouseRequest : Observable<Character>[] =[this.characterService.getCharacter(url)];
    spouseRequest.forEach(observable => {
      observable.subscribe(character => {
       this.father = character;
      });
    });
  }
  GetMother(url: string){
    const spouseRequest : Observable<Character>[] =[this.characterService.getCharacter(url)];
    spouseRequest.forEach(observable => {
      observable.subscribe(character => {
       this.mother = character;
      });
    });
  }
}
