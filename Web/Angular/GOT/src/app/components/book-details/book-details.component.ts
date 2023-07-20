import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Book } from 'src/app/models/book.model';
import { Character } from 'src/app/models/character.model';
import { BookService } from 'src/app/services/book.service';
import { CharacterService } from 'src/app/services/character.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent {
  characters: Character[] = []
  povCharacters: Character[] = [];
  selectedBook !: Book;

  constructor(private store: Store<{ book: Book }>,private location: Location, private characterService: CharacterService, private bookService: BookService) { }
  ngOnInit() {
    this.store.select('book').subscribe(state => {
      this.selectedBook = state.book;
    });
    this.GetPOVCharacters(this.selectedBook.povCharacters);
    this.GetCharacters(this.selectedBook.characters)
  }

  FormatDate(time: string): string {
    const date = new Date(time);
    return date.toLocaleDateString();
  }

  GetCharacters(charactersUrl: any[]) {
    const bookRequest: Observable<Character>[] = charactersUrl.map(endpoint => {
      return this.characterService.getCharacter(endpoint);
    });
    bookRequest.forEach(observable => {
      observable.subscribe(character => {
        this.characters.push(character)
      });
    });
  }

  GetPOVCharacters(charactersUrl: any[]) {
    const bookRequest: Observable<Character>[] = charactersUrl.map(endpoint => {
      return this.characterService.getCharacter(endpoint);
    });
    bookRequest.forEach(observable => {
      observable.subscribe(character => {
        this.povCharacters.push(character)
      });
    });
  }
  GoBack() {
    this.location.back();

  }
}
