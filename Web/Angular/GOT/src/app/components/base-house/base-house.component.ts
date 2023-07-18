import { Component, Input } from '@angular/core';
import { House } from 'src/app/models/house.model';

@Component({
  selector: 'app-base-house',
  templateUrl: './base-house.component.html',
  styleUrls: ['./base-house.component.css']
})
export class BaseHouseComponent {
  @Input('baseHouse') house !: House
}
