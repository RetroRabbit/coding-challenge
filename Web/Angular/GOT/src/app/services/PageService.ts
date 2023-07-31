import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Character } from '../models/character.model';
import { Store } from '@ngrx/store';
import { House } from '../models/house.model';
import { Book } from '../models/book.model';
import { getAllBooks } from '../actions/book.actions';
import { getAllCharacters } from '../actions/character.actions';
import { getAllHouses } from '../actions/house.actions';

@Injectable({
    providedIn: 'root'
})
export class PageService {
    pageSize: number = 10;
    bookLastPage !: number ;
    houseLastPage !: number ;
    characterLastPage !: number ;
    lastPage !: number;

    houseCurrentPage : number = 1;
    bookCurrentPage : number = 1;
    characterCurrentPage : number = 1;

    types : string[] = ['character', 'house', 'book'];
    urls :string[] = ['https://www.anapioficeandfire.com/api/characters?page=1&pageSize=','https://www.anapioficeandfire.com/api/houses?page=1&pageSize=','https://www.anapioficeandfire.com/api/books?page=1&pageSize=']
    
    constructor(private http: HttpClient, private houseStore: Store<{house : House}>,  private characterStore: Store<{character : Character}>, private bookStore: Store<{book : Book}>) {
     }

    FetchData(size : number) {
        this.pageSize = size;
        for(let index = 0; index < this.types.length; index++){
          this.http.get(this.urls[index]+this.pageSize, { observe: 'response' })
          .subscribe((response: { headers: HttpHeaders; }) => {
            this.lastPage = this.GetLastPageFromHeader(response.headers);
            this.UpdateLastPage(this.lastPage, this.types[index]);
          });
        }
      }
      
      private GetLastPageFromHeader(headers: HttpHeaders): number {
        const linkHeader = headers.get('Link');
        if (linkHeader) {
          const regex = /<[^>]*page=(\d+)[^>]*>; rel="last"/;
          const matches = regex.exec(linkHeader);
          if (matches && matches.length > 1) {
            return +matches[1];
          }
        }
        return -1;
      }
      
      UpdateLastPage(page: number, type : string) {
        this.pageSize = this.pageSize;
        switch(type){
          case 'character':{
            this.characterLastPage = page;
            this.characterStore.dispatch(getAllCharacters({ pageNumber: 1}));
            break;
          }
          case 'house':{
            this.houseLastPage = page;
            this.houseStore.dispatch(getAllHouses({ pageNumber: 1}));
            break;
          }
          case 'book':{
            this.bookLastPage = page;
            this.bookStore.dispatch(getAllBooks({ pageNumber: 1}));
            break;
          }
        }
      }
}
