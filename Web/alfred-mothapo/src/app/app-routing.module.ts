import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadChildren: ()=> import('./modules/dashboard/dashboard.module').then(m =>m.DashboardModule)
  },
  {
    path: 'house',
    loadChildren: ()=> import('./modules/houses/houses.module').then(m =>m.HousesModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
