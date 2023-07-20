import { Component, Input, OnInit } from '@angular/core';
import { take } from 'rxjs';
import { Character } from 'src/app/models/character';
import { CharacterService } from 'src/app/services/character/character.service';

@Component({
  selector: 'app-character',
  templateUrl: './character.component.html',
  styleUrls: ['./character.component.css']
})
export class CharacterComponent implements OnInit{

  public character: Character | undefined;
  @Input()
  characterUrl: string | undefined;
  @Input()
  title: string = '';
  
  constructor(private characterService: CharacterService){}

  ngOnInit() {
    this.getCharacter();
  }

  private getCharacter(){
    if (this.characterUrl){
      this.characterService.getCharacter(this.characterUrl)
      .pipe(take(1))
      .subscribe({
        next: (character)=>{
          this.character = character;
        },
        error: ()=>{

        }
      })
    }
  }
}
