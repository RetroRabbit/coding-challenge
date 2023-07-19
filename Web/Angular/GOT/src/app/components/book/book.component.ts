import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { getBook, getAllBooks } from '../../actions/book.actions';
import { Book } from '../../models/book.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent {
  book: Book | undefined;
  books: Book[] = [];
  static currentPage = 1;
  constructor(private store: Store<{ book: Book }>, private router : Router) {}
  ngOnInit() {
    this.store.dispatch(getAllBooks({ pageNumber: BookComponent.currentPage}));
    this.store.select('book').subscribe(state => {
      this.books = state.books;
    });
  }
  SelectedHouse(bookName: string){
    this.store.dispatch(getBook({ bookId: bookName }));
    this.router.navigate(['/BookDetails/']);
  }

  ShowFirst(){
    BookComponent.currentPage = 1;
    this.store.dispatch(getAllBooks({ pageNumber: BookComponent.currentPage}));
  }

  ShowLast(){
    BookComponent.currentPage = 213;
    this.store.dispatch(getAllBooks({ pageNumber: BookComponent.currentPage}));
  }

  ShowNext(){
    if(BookComponent.currentPage < 213){
      BookComponent.currentPage += 1;
    }else{
      BookComponent.currentPage = 213
    }
    this.store.dispatch(getAllBooks({ pageNumber: BookComponent.currentPage}));
  }

  ShowPrev(){
    if(BookComponent.currentPage > 1){
      BookComponent.currentPage -= 1;
    }else{
      BookComponent.currentPage = 1
    }
    this.store.dispatch(getAllBooks({ pageNumber: BookComponent.currentPage}));
  }

  GetCurrentPage(): number {
    return BookComponent.currentPage;
  }
}
