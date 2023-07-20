import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { getBook, getAllBooks } from '../../actions/book.actions';
import { Book } from '../../models/book.model';
import { Router } from '@angular/router';
import { PageService } from 'src/app/services/PageService';
import { HttpClient, HttpHeaders } from '@angular/common/http';

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

  ShowFirst(){
    BookComponent.currentPage = 1;
    this.store.dispatch(getAllBooks({ pageNumber: BookComponent.currentPage}));
  }

  ShowLast(){
    BookComponent.currentPage = this.lastPage;
    this.store.dispatch(getAllBooks({ pageNumber: BookComponent.currentPage}));
  }

  ShowNext(){
    if(BookComponent.currentPage < 2){
      BookComponent.currentPage += 1;
    }else{
      BookComponent.currentPage = this.lastPage
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
