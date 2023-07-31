import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from '../models/book.model';
import { PageService } from './PageService';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private baseUrl = 'https://anapioficeandfire.com/api/books';

  constructor(private http: HttpClient, private pageService : PageService) {}

  getBook(bookId: string): Observable<Book> {
    const url = `${bookId}`;
    return this.http.get<Book>(url);
  }

  getAllBooks(pageNumber: number): Observable<Book[]> {
    const queries = `?page=${pageNumber}&pageSize=${this.pageService.pageSize}`;
    return this.http.get<Book[]>(this.baseUrl + queries);
  }
}
