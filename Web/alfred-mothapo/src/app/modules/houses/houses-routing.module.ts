import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HouseInfoComponent } from './components/house-info/house-info.component';

const routes: Routes = [
  {
    path: "house-info/:id",
    component: HouseInfoComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HousesRoutingModule { }
