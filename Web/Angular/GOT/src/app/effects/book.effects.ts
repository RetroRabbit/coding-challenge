import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import { BookService } from '../services/book.service';
import * as BookActions from '../actions/book.actions';

@Injectable()
export class BookEffects {
  constructor(
    private actions$: Actions,
    private bookService: BookService
  ) { }
  getBook$ = createEffect(() =>
    this.actions$.pipe(
      ofType(BookActions.getBook),
      mergeMap(({ bookId }) =>
        this.bookService.getBook(bookId).pipe(
          map(book => BookActions.getBookSuccess({ book })),
          catchError(error => of(BookActions.getBooksFailure({ error })))
        )
      )
    )
  );
  getAllBooks$ = createEffect(() =>
  this.actions$.pipe(
    ofType(BookActions.getAllBooks),
    mergeMap(({ pageNumber }) =>
      this.bookService.getAllBooks(pageNumber).pipe(
        map(books => BookActions.getAllBooksSuccess({ books })),
        catchError(error => of(BookActions.getBooksFailure({ error })))
      )
    )
  )
);
}
