import { Component, OnInit } from '@angular/core';
import { take } from 'rxjs';
import { Character } from 'src/app/models/character';
import { CharacterService } from 'src/app/services/character/character.service';

@Component({
  selector: 'app-character-list',
  templateUrl: './character-list.component.html',
})
export class CharacterListComponent implements OnInit {

  public loading: boolean = false;
  private pageNumber: number = 1;
  private pageSize: number = 50;
  public characters: Array<Character> = new Array<Character>();
  public selectedCharacter: Character | undefined;
  public showCharacterDialog: boolean = false;

  constructor(private characterService: CharacterService){}

  ngOnInit(): void {
   this.getCharacters();
  }
  
  getCharacters(){
    this.loading = true;
    this.characterService.getCharacters(this.pageNumber, this.pageSize)
    .pipe(take(1)).subscribe({
      next: (results) => {
        if (results && results.length > 0){
          if (this.characters && this.characters.length > 0){
            this.characters = this.characters.concat(results)
          }
          else {
            this.characters = results;
          }
        }
        this.loading = false;
      },
      error: (error) =>{
        this.loading = false;
      }
    })
  }
  next(){
    this.pageNumber +=1;
    this.getCharacters();
  }

  showBooksDialog(character: Character){
    this.selectedCharacter = character;
    this.showCharacterDialog = true;
  }
}
