import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { getAllCharacters, getCharacter } from 'src/app/actions/character.actions';
import { Character } from 'src/app/models/character.model';

@Component({
  selector: 'app-characters',
  templateUrl: './characters.component.html',
  styleUrls: ['./characters.component.css']
})
export class CharactersComponent {

  static currentPage = 1;
  characters : Character[] = []
  constructor(private store: Store<{character : Character}>, private router : Router){}
  ngOnInit() {
    this.store.dispatch(getAllCharacters({ pageNumber: CharactersComponent.currentPage}));
    this.store.select('character').subscribe(state => {
      this.characters = state.characters;
    });
  }

  SelectCharacter(characterUrl: string){
    this.store.dispatch(getCharacter({ characterId: characterUrl }));
    this.router.navigate(['/CharacterDetails/']);
  }

  ShowFirst(){
    CharactersComponent.currentPage = 1;
    this.store.dispatch(getAllCharacters({ pageNumber: CharactersComponent.currentPage}));
  }

  ShowLast(){
    CharactersComponent.currentPage = 213;
    this.store.dispatch(getAllCharacters({ pageNumber: CharactersComponent.currentPage}));
  }

  ShowNext(){
    if(CharactersComponent.currentPage < 213){
      CharactersComponent.currentPage += 1;
    }else{
      CharactersComponent.currentPage = 213
    }
    this.store.dispatch(getAllCharacters({ pageNumber: CharactersComponent.currentPage}));
  }

  ShowPrev(){
    if(CharactersComponent.currentPage > 1){
      CharactersComponent.currentPage -= 1;
    }else{
      CharactersComponent.currentPage = 1
    }
    this.store.dispatch(getAllCharacters({ pageNumber: CharactersComponent.currentPage}));
  }
  GetCurrentPage(): number {
    return CharactersComponent.currentPage;
  }
}
