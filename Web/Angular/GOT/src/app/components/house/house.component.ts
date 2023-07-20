import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { getAllHouses, getHouse } from '../../actions/house.actions';
import { House } from '../../models/house.model';
import { Router } from '@angular/router';
import { Character } from '../../models/character.model';
import { PageService } from 'src/app/services/PageService';
@Component({
  selector: 'app-house',
  templateUrl: './house.component.html',
  styleUrls: ['./house.component.css']
})
export class HouseComponent  {
  house : House | undefined;
  houses : House[] = [];
  characters : Character[] = [];
  static currentPage = 1;
  
  lastPage !: number;
  public pageSize: number = this.pageService.pageSize;
  constructor(private store: Store<{house : House}>, private router : Router, private pageService: PageService){}

  ngOnInit() {
    this.store.dispatch(getAllHouses({ pageNumber: HouseComponent.currentPage}));
    this.store.select('house').subscribe(state => {
      this.houses = state.houses;
      this.lastPage = this.pageService.houseLastPage;
    });
  }
  SelectedHouse(houseName: string){
    this.store.dispatch(getHouse({ houseId: houseName }));
    this.router.navigate(['/Details/']);
  }

  ShowFirst(){
    HouseComponent.currentPage = 1;
    this.store.dispatch(getAllHouses({ pageNumber: HouseComponent.currentPage}));
  }

  ShowLast(){
    HouseComponent.currentPage = this.lastPage;
    this.store.dispatch(getAllHouses({ pageNumber: HouseComponent.currentPage}));
  }

  ShowNext(){
    if(HouseComponent.currentPage < this.lastPage){
      HouseComponent.currentPage += 1;
    }else{
      HouseComponent.currentPage = this.lastPage;
    }
    this.store.dispatch(getAllHouses({ pageNumber: HouseComponent.currentPage}));
  }

  ShowPrev(){
    if(HouseComponent.currentPage > 1){
      HouseComponent.currentPage -= 1;
    }else{
      HouseComponent.currentPage = 1
    }
    this.store.dispatch(getAllHouses({ pageNumber: HouseComponent.currentPage}));
  }

  GetCurrentPage(): number {
    return HouseComponent.currentPage;
  }

  onMouseOver(event: MouseEvent) {
    const target = event.target as HTMLElement;
    const card = target.closest('.card');
    if (card) {
      const index = Array.from(card.parentElement!.children).indexOf(card);
      this.houses[index].hovered = true;
    }
  }

  onMouseOut(event: MouseEvent) {
    const target = event.target as HTMLElement;
    const card = target.closest('.card');
    if (card) {
      const index = Array.from(card.parentElement!.children).indexOf(card);
      this.houses[index].hovered = false;
    }
  }
  FetchData() {
    this.pageService.FetchData(this.pageSize);
  }
}