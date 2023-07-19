import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Book } from 'src/app/models/book.model';
import { Character } from 'src/app/models/character.model';
import { House } from 'src/app/models/house.model';
import { CharacterService } from 'src/app/services/character.service';
import { BookService } from '../../services/book.service';

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
  constructor(private store : Store<{character : Character}>,  private characterService: CharacterService, private bookService: BookService){}
  ngOnInit(){
    this.store.select('character').subscribe(state => {
      this.selectedCharacter = state.character;
<<<<<<< Updated upstream
=======
      // console.log(this.selectedCharacter.books)
      this.GetBooks(this.selectedCharacter.books)
>>>>>>> Stashed changes
    });
    // if(this.selectedCharacter.books){console.log(this.selectedCharacter.books);this.GetBooks(this.selectedCharacter.books)}
    if(this.selectedCharacter.spouse){this.GetSpouse(this.selectedCharacter.spouse)}
    if(this.selectedCharacter.mother){this.GetMother(this.selectedCharacter.mother)}
    if(this.selectedCharacter.father){this.GetFather(this.selectedCharacter.father)}
  }

  GetBooks(books : any[]){
    // console.log(books)
    const bookRequest: Observable<Book>[] = books.map(endpoint => {
      return this.bookService.getBook(endpoint);
    });
    bookRequest.forEach(observable => {
      observable.subscribe(book => {
        this.books.push(book)
      });
    });
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
