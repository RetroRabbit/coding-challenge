import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { PageService } from './services/PageService';
import { getAllHouses } from './actions/house.actions';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { House } from './models/house.model';
import { Character } from './models/character.model';
import { getAllCharacters } from './actions/character.actions';
import { getAllBooks } from './actions/book.actions';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  captureInput !: EventListener
  public pageSize: number = 10;
  lastPage !: number;
  types : string[] = ['character', 'house', 'book'];
  urls :string[] = ['https://www.anapioficeandfire.com/api/characters?page=1&pageSize=','https://www.anapioficeandfire.com/api/houses?page=1&pageSize=','https://www.anapioficeandfire.com/api/books?page=1&pageSize=']
  constructor(private http: HttpClient, private pageService: PageService, private router : Router, private houseStore: Store<{house : House}>,  private characterStore: Store<{character : Character}>, private bookStore: Store<{book : Character}>) {}
  
  ChangeLinkColor(event: MouseEvent) {
    var links = document.getElementsByClassName('nav-link');
    for (var i = 0; i < links.length; i++) {
      links[i].classList.remove('selected');
    }
    var selectedLink = event.target as HTMLElement;
    selectedLink.classList.add('selected');
  }

  FetchData() {
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
    this.pageService.pageSize = this.pageSize;
    switch(type){
      case 'character':{
        this.pageService.characterLastPage = page;
        this.characterStore.dispatch(getAllCharacters({ pageNumber: 1}));
        break;
      }
      case 'house':{
        this.pageService.houseLastPage = page;
        this.houseStore.dispatch(getAllHouses({ pageNumber: 1}));
        break;
      }
      case 'book':{
        this.pageService.bookLastPage = page;
        this.bookStore.dispatch(getAllBooks({ pageNumber: 1}));
        break;
      }
    }
  }

}
