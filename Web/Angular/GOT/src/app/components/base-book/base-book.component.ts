import { Component, Input } from '@angular/core';
import { Book } from 'src/app/models/book.model';

@Component({
  selector: 'app-base-book',
  templateUrl: './base-book.component.html',
})
export class BaseBookComponent {
  @Input('book') book !: Book;
}
