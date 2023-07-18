import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { getAllHouses, getHouse } from '../../actions/house.actions';
import { House } from '../../models/house.model';
import { Router } from '@angular/router';
import { getAllCharacters } from '../../actions/character.actions';
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
  constructor(private store: Store<{house : House}>, private cstore : Store<{character : Character}>, private router : Router){}
  ngOnInit() {
    this.store.dispatch(getAllHouses());
    this.store.select('house').subscribe(state => {
      // this.house = state.house;
      this.houses = state.houses;
    });
  }
  SelectedHouse(houseName: string){
    console.log(houseName)
    this.store.dispatch(getHouse({ houseId: houseName }));
    this.router.navigate(['/Details/']);
  }
}