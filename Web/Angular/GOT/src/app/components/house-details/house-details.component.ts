import { Component } from '@angular/core';
import { Location } from '@angular/common';
import { House } from 'src/app/models/house.model';
import { Store } from '@ngrx/store';
@Component({
  selector: 'app-house-details',
  templateUrl: './house-details.component.html',
  styleUrls: ['./house-details.component.css']
})
export class HouseDetailsComponent {
  selectedHouse : House | undefined
  constructor(private store: Store<{house : House}>, private location: Location) { }
  ngOnInit() {
    this.store.select('house').subscribe(state => {
      this.selectedHouse = state.house;
    });
  }
  GoBack() {
    this.location.back();
  }
  
}
