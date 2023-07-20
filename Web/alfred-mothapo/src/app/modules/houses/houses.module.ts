import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HousesRoutingModule } from './houses-routing.module';
import { HouseInfoComponent } from './components/house-info/house-info.component';
import { SharedModule } from '../shared/shared.module';
import {CardModule} from 'primeng/card';
import { FieldsetModule } from 'primeng/fieldset';
import { TableModule } from 'primeng/table';
import { CharacterModule } from '../character/character.module';


@NgModule({
  declarations: [
    HouseInfoComponent
  ],
  imports: [
    CommonModule,
    HousesRoutingModule,
    SharedModule,
    CardModule,
    FieldsetModule,
    TableModule,
    CharacterModule
  ],
  exports: [HouseInfoComponent]
})
export class HousesModule { }
