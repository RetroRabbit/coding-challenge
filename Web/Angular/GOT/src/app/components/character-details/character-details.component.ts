import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Book } from 'src/app/models/book.model';
import { Character } from 'src/app/models/character.model';
import { House } from 'src/app/models/house.model';
import { CharacterService } from 'src/app/services/character.service';
import { BookService } from '../../services/book.service';
import { HouseService } from 'src/app/services/house.service';
import { Location } from '@angular/common';

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

  constructor(private store : Store<{character : Character}>, private location : Location, private characterService: CharacterService, private bookService: BookService, private houseService: HouseService){}
  
  ngOnInit(){
    this.store.select('character').subscribe(state => {
      this.selectedCharacter = state.character;
      this.GetBooks(this.selectedCharacter.books);
      this.GetBooks(this.selectedCharacter.povBooks);
      this.GetAllegiances(this.selectedCharacter.allegiances);
      this.GetSpouse(this.selectedCharacter.spouse)
      this.GetMother(this.selectedCharacter.mother)
      this.GetFather(this.selectedCharacter.father)
    });
  }

  GetBooks(books : any[]){
    const bookRequest: Observable<Book>[] = books.map(endpoint => {
      return this.bookService.getBook(endpoint);
    });
    bookRequest.forEach(observable => {
      observable.subscribe(book => {
        this.books.push(book)
      });
    });
  }

  GetPOVBooks(books : any[]){
    const bookRequest: Observable<Book>[] = books.map(endpoint => {
      return this.bookService.getBook(endpoint);
    });
    bookRequest.forEach(observable => {
      observable.subscribe(book => {
        this.books.push(book)
      });
    });
  }
  GetAllegiances(allegiances : any[]){
    const allegianceRequest: Observable<House>[] = allegiances.map(endpoint => {
      return this.houseService.getAHouse(endpoint);
    });
    allegianceRequest.forEach(observable => {
      observable.subscribe(house => {
        this.allegiances.push(house)
      });
    });
  } 
  GetSpouse(url: string){
    const spouseRequest : Observable<Character>[] =[this.characterService.getCharacter(url)];
    spouseRequest.forEach(observable => {
      observable.subscribe(character => {
       this.spouse = character;
      });
    });
  }
  GetFather(url: string){
    const fatherRequest : Observable<Character>[] =[this.characterService.getCharacter(url)];
    fatherRequest.forEach(observable => {
      observable.subscribe(character => {
       this.father = character;
      });
    });
  }
  GetMother(url: string){
    const motherReuqst : Observable<Character>[] =[this.characterService.getCharacter(url)];
    motherReuqst.forEach(observable => {
      observable.subscribe(character => {
       this.mother = character;
      });
    });
  }
  GoBack() {
    this.location.back();
  }
}
