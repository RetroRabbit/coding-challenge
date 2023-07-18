import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { Character } from 'src/app/models/character.model';

@Component({
  selector: 'app-character-details',
  templateUrl: './character-details.component.html',
  styleUrls: ['./character-details.component.css']
})
export class CharacterDetailsComponent {

  selectedCharacter !: Character
  constructor(private store : Store<{character : Character}>){}
  ngOnInit(){
    this.store.select('character').subscribe(state => {
      this.selectedCharacter = state.character;
      console.log(this.selectedCharacter)
    });
  }
}
