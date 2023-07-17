import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import { BookService } from '../services/book.service';
import * as BookActions from '../actions/book.actions';

@Injectable()
export class BookEffects {
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
      mergeMap(() =>
        this.bookService.getAllBooks().pipe(
          map(books => BookActions.getAllBooksSuccess({ books })),
          catchError(error => of(BookActions.getBooksFailure({ error })))
        )
      )
    )
  );

  constructor(
    private actions$: Actions,
    private bookService: BookService
  ) {}
}
