import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CharacterComponent } from './components/character/character.component';
import { FieldsetModule } from 'primeng/fieldset';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { ProgressBarModule } from 'primeng/progressbar';
import { DialogModule } from 'primeng/dialog';
import { BookModule } from '../book/book.module';
import { ButtonModule } from 'primeng/button';


@NgModule({
  declarations: [
    CharacterComponent
  ],
  imports: [
    CommonModule,
    FieldsetModule,
    ToastModule,
    ProgressBarModule,
    DialogModule,
    BookModule,
    ButtonModule
  ],
  exports: [CharacterComponent],
  providers: [MessageService]
})
export class CharacterModule { }
