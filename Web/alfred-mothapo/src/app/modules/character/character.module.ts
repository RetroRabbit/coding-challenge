import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CharacterComponent } from './components/character/character.component';
import { FieldsetModule } from 'primeng/fieldset';


@NgModule({
  declarations: [
    CharacterComponent
  ],
  imports: [
    CommonModule,
    FieldsetModule
  ],
  exports: [CharacterComponent]
})
export class CharacterModule { }
