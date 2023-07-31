import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { getBook, getAllBooks } from '../../actions/book.actions';
import { Book } from '../../models/book.model';
import { Router } from '@angular/router';
import { PageService } from 'src/app/services/PageService';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent {
  book: Book | undefined;
  books: Book[] = [];
  lastPage !: number;
  static currentPage = 1;

  public pageSize: number = this.pageService.pageSize;

  constructor(private store: Store<{ book: Book }>, private router : Router, private pageService : PageService, private http: HttpClient) {}
  ngOnInit() {
    this.store.dispatch(getAllBooks({ pageNumber: BookComponent.currentPage}));
    this.store.select('book').subscribe(state => {
      this.books = state.books;
      this.lastPage = this.pageService.bookLastPage;
    });
  }
  SelectedBook(isbn: string){
    this.store.dispatch(getBook({ bookId: isbn }));
    this.router.navigate(['/BookDetails/']);
  }

  GetCurrentPage(): number {
    return BookComponent.currentPage;
  }
}
