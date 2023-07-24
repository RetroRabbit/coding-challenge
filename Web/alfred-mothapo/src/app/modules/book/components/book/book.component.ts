import { Component, Input, OnInit } from '@angular/core';
import * as moment from 'moment';
import { take } from 'rxjs';
import { Book } from 'src/app/models/book';
import { BookService } from 'src/app/services/book/book.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html'
})
export class BookComponent implements OnInit {

  @Input()
  bookUrl: string = '';
  public book: Book | undefined;
  public loading: boolean = false;

  constructor(
    private bookService: BookService
  ){}

  ngOnInit() {
    this.getBook();
  }

  getBook(){
    if (this.bookUrl){
      this.loading = true;
      this.bookService.getBook(this.bookUrl)
      .pipe(take(1))
      .subscribe({
        next: (book)=>{
          this.book = book;
          // format date
          this.book.released = moment(this.book.released).format("YYYY-MM-DD");
          this.loading = false;
        },
        error: (error)=>{
          this.loading = false;
        }
      })
    }
  }
}
