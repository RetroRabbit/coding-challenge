import { Component,Input } from '@angular/core';
import { Character } from '../../models/character.model';
@Component({
  selector: 'app-character',
  templateUrl: './character.component.html'
})
export class CharacterComponent {
  @Input('chr') character!: Character;
}
