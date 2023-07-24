import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookComponent } from './components/book/book.component';
import { FieldsetModule } from 'primeng/fieldset';
import { ProgressBarModule } from 'primeng/progressbar';



@NgModule({
  declarations: [
    BookComponent
  ],
  imports: [
    CommonModule,
    FieldsetModule,
    ProgressBarModule
  ],
  exports: [BookComponent]
})
export class BookModule { }
