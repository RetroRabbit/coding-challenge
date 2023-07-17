import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HouseComponent } from './components/house/house.component';
import { CharacterComponent } from './components/character/character.component';
import { BookComponent } from './components/book/book.component';

const routes: Routes = [
  
  {path: '', component: HouseComponent},
  {path: 'Characters', component: CharacterComponent},
  {path: 'Books', component: BookComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
