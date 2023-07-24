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
import { CharacterListComponent } from './components/character-list/character-list.component';
import { SharedModule } from '../shared/shared.module';
import { CharacterRoutingModule } from './character-routing.module';
import { TableModule } from 'primeng/table';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';


@NgModule({
  declarations: [
    CharacterComponent,
    CharacterListComponent
  ],
  imports: [
    CommonModule,
    CharacterRoutingModule,
    FieldsetModule,
    ToastModule,
    ProgressBarModule,
    DialogModule,
    BookModule,
    ButtonModule,
    SharedModule,
    TableModule,
    CardModule,
    InputTextModule,
  ],
  exports: [CharacterComponent, CharacterListComponent],
  providers: [MessageService]
})
export class CharacterModule { }
