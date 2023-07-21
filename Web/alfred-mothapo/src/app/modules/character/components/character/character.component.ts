import { Component, Input, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { take } from 'rxjs';
import { Character } from 'src/app/models/character';
import { CharacterService } from 'src/app/services/character/character.service';

@Component({
  selector: 'app-character',
  templateUrl: './character.component.html'
})
export class CharacterComponent implements OnInit{

  public character: Character | undefined;
  @Input()
  public characterUrl: string | undefined;
  @Input()
  public title: string = '';
  public loading: boolean = false;
  public showBooksDialog: boolean = false; 
  
  constructor(
    private messageService: MessageService,
    private characterService: CharacterService){}

  ngOnInit() {
    this.getCharacter();
  }

  private getCharacter(){
    this.loading = true;
    if (this.characterUrl){
      this.characterService.getCharacter(this.characterUrl)
      .pipe(take(1))
      .subscribe({
        next: (character)=>{
          this.character = character;
          this.loading = false;
        },
        error: (error)=>{
          this.showError(error.message);
          this.loading = false;
        }
      })
    }
  }

  showError(errorMessage: string) {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: `An error occured, details: ${errorMessage}` });
  }

  showBooks(){
    this.showBooksDialog = true;
  }
}
