import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { getBook, getAllBooks } from '../actions/book.actions';
import { Book } from '../models/book.model';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent {
  book: Book | undefined;
  books: Book[] = [];

  constructor(private store: Store<{ book: Book }>) {}

  ngOnInit() {
    this.store.select('book').subscribe(state => {
      this.book = state.book;
      this.books = state.books;
    });

    // Dispatch actions to get book and all books
    // this.store.dispatch(getBook({ bookId: '1' }));
    this.store.dispatch(getAllBooks());
  }
  getBook(bookId: any) {
    this.store.dispatch(getBook({ bookId }));
  }
}
