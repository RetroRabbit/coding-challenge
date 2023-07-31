import { createReducer, on } from '@ngrx/store';
import { Book } from '../models/book.model';
import * as BookActions from '../actions/book.actions';

export interface BookState {
  book: Book | null;
  books: Book[];
  loading: boolean;
  error: any;
}

export const initialState: BookState = {
  book: null,
  books: [],
  loading: false,
  error: null
};

export const bookReducer = createReducer(
  initialState,
  on(BookActions.getBook, (state, { bookId }) => {
    const selectedBook = state.books.find(book => book.isbn === bookId);
    return {
      ...state,
      book: selectedBook || null,
      loading: true
    };
  }),
  on(BookActions.getBookSuccess, (state, { book }) => ({ ...state, book, loading: false })),
  on(BookActions.getAllBooks, state => ({ ...state, loading: true })),
  on(BookActions.getAllBooksSuccess, (state, { books }) => ({ ...state, books, loading: false })),
  on(BookActions.getBooksFailure, (state, { error }) => ({ ...state, error, loading: false }))
);
