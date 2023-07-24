import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HousesRoutingModule } from './houses-routing.module';
import { HouseInfoComponent } from './components/house-info/house-info.component';
import { SharedModule } from '../shared/shared.module';
import {CardModule} from 'primeng/card';
import { FieldsetModule } from 'primeng/fieldset';
import { TableModule } from 'primeng/table';
import { CharacterModule } from '../character/character.module';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';


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
    CharacterModule,
    ToastModule
  ],
  exports: [HouseInfoComponent],
  providers: [MessageService]
})
export class HousesModule { }
