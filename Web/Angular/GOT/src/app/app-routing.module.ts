import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HouseComponent } from './components/house/house.component';
import { BookComponent } from './components/book/book.component';
import { HouseDetailsComponent } from './components/house-details/house-details.component';
import { CharactersComponent } from './components/characters/characters.component';
import { CharacterDetailsComponent } from './components/character-details/character-details.component';
import { BookDetailsComponent } from './components/book-details/book-details.component';

const routes: Routes = [
  
  {path: '', component: HouseComponent},
  {path: 'Characters', component: CharactersComponent},
  {path: 'Books', component: BookComponent},
  {path: 'Details', component: HouseDetailsComponent },
  {path: 'CharacterDetails', component: CharacterDetailsComponent },
  {path: 'BookDetails', component: BookDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
