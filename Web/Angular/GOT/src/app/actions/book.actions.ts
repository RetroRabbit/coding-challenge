import { createAction, props } from '@ngrx/store';
import { Book } from '../models/book.model';

export const getBook = createAction('[Book] Get Book', props<{ bookId: string }>());
export const getBookSuccess = createAction('[Book] Get Book Success', props<{ book: Book }>());
export const getAllBooks = createAction('[Book] Get All Books',  props<{ pageNumber: number }>());
export const getAllBooksSuccess = createAction('[Book] Get All Books Success', props<{ books: Book[] }>());
export const getBooksFailure = createAction('[Book] Get Books Failure', props<{ error: any }>());