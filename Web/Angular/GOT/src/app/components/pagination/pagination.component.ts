import { Component, Input } from '@angular/core';
import { PageService } from '../../services/PageService';
import { getAllHouses } from 'src/app/actions/house.actions';
import { Store } from '@ngrx/store';
import { House } from 'src/app/models/house.model';
import { getAllCharacters } from 'src/app/actions/character.actions';
import { getAllBooks } from 'src/app/actions/book.actions';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html'
})
export class PaginationComponent {
  
  
  @Input('pagnination') pagination !:string
  public pageSize: number = this.pageService.pageSize;
    constructor(private pageService : PageService, private store: Store<{house : House}>){}


  ShowFirst(){
    switch(this.pagination){
      case 'house':{
        this.pageService.houseCurrentPage = 1;
        this.store.dispatch(getAllHouses({ pageNumber: this.pageService.houseCurrentPage}));
        break;
      }
      case 'character': {
        this.pageService.characterCurrentPage = 1;
        this.store.dispatch(getAllCharacters({ pageNumber: this.pageService.characterCurrentPage}));
        break;
      }
      case 'book':{
        this.pageService.bookCurrentPage = 1;
        this.store.dispatch(getAllBooks({ pageNumber: this.pageService.bookCurrentPage}));
        break;
      }
    }
  }

  ShowLast(){
    switch(this.pagination){
      case 'house':{
        this.pageService.houseCurrentPage = this.pageService.houseLastPage;
        this.store.dispatch(getAllHouses({ pageNumber: this.pageService.houseCurrentPage}));
        break;
      }
      case 'character':{
        this.pageService.characterCurrentPage = this.pageService.characterLastPage;
        this.store.dispatch(getAllCharacters({ pageNumber: this.pageService.characterCurrentPage}));
        break;
      }
      case 'book':{
        this.pageService.bookCurrentPage = this.pageService.bookLastPage;
        this.store.dispatch(getAllBooks({ pageNumber: this.pageService.bookCurrentPage}));
        break;
      }
    }
  }

  ShowNext(){
    switch(this.pagination){
      case 'house':{
        if(this.pageService.houseCurrentPage < this.pageService.houseLastPage){
          this.pageService.houseCurrentPage += 1;
          this.store.dispatch(getAllHouses({ pageNumber: this.pageService.houseCurrentPage}));
        }
        break;
      }
      case 'character': {
        if(this.pageService.characterCurrentPage < this.pageService.characterLastPage){
          this.pageService.characterCurrentPage += 1;
          this.store.dispatch(getAllCharacters({ pageNumber: this.pageService.characterCurrentPage}));
        }
        break;
      }
      case 'book':{
        if(this.pageService.bookCurrentPage < this.pageService.bookLastPage){
          this.pageService.bookCurrentPage += 1;
          this.store.dispatch(getAllBooks({ pageNumber: this.pageService.bookCurrentPage}));
        }
        break;
      }
    }
  }

  ShowPrev(){
    switch(this.pagination){
      case 'house':{
        if(this.pageService.houseCurrentPage > 1){
          this.pageService.houseCurrentPage -= 1;
          this.store.dispatch(getAllHouses({ pageNumber: this.pageService.houseCurrentPage}));
        }
        break;
      }
      case 'character':{
        if(this.pageService.characterCurrentPage > 1){
          this.pageService.characterCurrentPage -= 1;
          this.store.dispatch(getAllCharacters({ pageNumber: this.pageService.characterCurrentPage}));
        }
        break;
      }
      case 'book':{
        if(this.pageService.bookCurrentPage > 1){
          this.pageService.bookCurrentPage -= 1;
          this.store.dispatch(getAllBooks({ pageNumber: this.pageService.bookCurrentPage}));
        }
        break;
      }
    }
  }
  FetchData() {
    this.pageService.FetchData(this.pageSize);
    this.pageService.houseCurrentPage = 1;
    this.pageService.characterCurrentPage = 1;
    this.pageService.bookCurrentPage = 1;
  }
  GetCurrentPage(): number {
    let currentPage : number = 0;
    switch(this.pagination){
      case 'house':{
        currentPage = this.pageService.houseCurrentPage;
        break;
      }
      case 'character':{
        currentPage = this.pageService.characterCurrentPage;
        break;
      }
      case 'book':{
        currentPage = this.pageService.bookCurrentPage;
        break;
      }
    }
    return currentPage;
  }
  GetLastPage(): number {
    let lastPage : number = 0;
    switch(this.pagination){
      case 'house':{
        lastPage = this.pageService.houseLastPage;
        break;
      }
      case 'character':{
        lastPage = this.pageService.characterLastPage;
        break;
      }
      case 'book':{
        lastPage = this.pageService.bookLastPage;
        break;
      }
    }
    return lastPage;
  }
}
