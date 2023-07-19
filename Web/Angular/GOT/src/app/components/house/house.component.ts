import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { getAllHouses, getHouse } from '../../actions/house.actions';
import { House } from '../../models/house.model';
import { Router } from '@angular/router';
import { Character } from '../../models/character.model';
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
  constructor(private store: Store<{house : House}>, private router : Router){}
  ngOnInit() {
    this.store.dispatch(getAllHouses({ pageNumber: HouseComponent.currentPage}));
    this.store.select('house').subscribe(state => {
      this.houses = state.houses;
    });
  }
  SelectedHouse(houseName: string){
    console.log(houseName)
    this.store.dispatch(getHouse({ houseId: houseName }));
    this.router.navigate(['/Details/']);
  }

  ShowFirst(){
    HouseComponent.currentPage = 1;
    this.store.dispatch(getAllHouses({ pageNumber: HouseComponent.currentPage}));
  }

  ShowLast(){
    HouseComponent.currentPage = 213;
    this.store.dispatch(getAllHouses({ pageNumber: HouseComponent.currentPage}));
  }

  ShowNext(){
    if(HouseComponent.currentPage < 213){
      HouseComponent.currentPage += 1;
    }else{
      HouseComponent.currentPage = 213
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
}