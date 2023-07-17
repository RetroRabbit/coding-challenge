import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { getAllHouses, getHouse } from '../../actions/house.actions';
import { House } from '../../models/house.model';

@Component({
  selector: 'app-house',
  templateUrl: './house.component.html',
  styleUrls: ['./house.component.css']
})
export class HouseComponent  {
  house : House | undefined;
  houses : House[] = [];

  constructor(private store: Store<{house : House}>){}
  ngOnInit() {
    this.store.select('house').subscribe(state => {
      this.house = state.house;
      this.houses = state.houses;
    });

    // Dispatch actions to get book and all books
    // this.store.dispatch(getBook({ bookId: '1' }));
    this.store.dispatch(getAllHouses());
  }
  // getHouse(houseId: any){
  //   this.store.dispatch(getHouse({ houseId }));
  // }

  SelectedHouse(houseId: number){
    console.log(houseId)
  }
}


