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
  constructor(private pageService: PageService) {
    this.pageService.FetchData(this.pageSize);
  }
  
  ChangeLinkColor(event: MouseEvent) {
    var links = document.getElementsByClassName('nav-link');
    for (var i = 0; i < links.length; i++) {
      links[i].classList.remove('selected');
    }
    var selectedLink = event.target as HTMLElement;
    selectedLink.classList.add('selected');
  }

  FetchData() {
    this.pageService.FetchData(this.pageSize);
  }
}
