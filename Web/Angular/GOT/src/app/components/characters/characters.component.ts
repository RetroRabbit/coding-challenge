import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { getAllCharacters, getCharacter } from 'src/app/actions/character.actions';
import { Character } from 'src/app/models/character.model';
import { PageService } from 'src/app/services/PageService';

@Component({
  selector: 'app-characters',
  templateUrl: './characters.component.html',
  styleUrls: ['./characters.component.css']
})
export class CharactersComponent {

  static currentPage = 1;
  characters : Character[] = [];
  lastPage !: number;
  
  public pageSize: number = this.pageService.pageSize;

  constructor(private store: Store<{character : Character}>, private router : Router, private pageService: PageService){}
  ngOnInit() {
    this.store.dispatch(getAllCharacters({ pageNumber: CharactersComponent.currentPage}));
    this.store.select('character').subscribe(state => {
      this.characters = state.characters;
      this.lastPage = this.pageService.characterLastPage
    });
  }

  SelectCharacter(characterUrl: string){
    this.store.dispatch(getCharacter({ characterId: characterUrl }));
    this.router.navigate(['/CharacterDetails/']);
  }

  GetCurrentPage(): number {
    return CharactersComponent.currentPage;
  }
}
