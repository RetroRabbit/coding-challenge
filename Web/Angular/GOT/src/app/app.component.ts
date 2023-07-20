import { Component } from '@angular/core';
import { PageService } from './services/PageService';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  captureInput !: EventListener
  public pageSize: number = 10;
  lastPage !: number;

  types : string[] = ['character', 'house', 'book'];
  urls :string[] = ['https://www.anapioficeandfire.com/api/characters?page=1&pageSize=','https://www.anapioficeandfire.com/api/houses?page=1&pageSize=','https://www.anapioficeandfire.com/api/books?page=1&pageSize=']
  constructor(private pageService: PageService) {
    this.pageService.FetchData(this.pageSize);
  }
  
  selectedpage = "";



  ChangeLinkColor(event: Event) {
    const target = event.target as HTMLAnchorElement;
    switch (target.innerText) {
      case 'Books':
       this.selectedpage = 'book'
        break;
      case 'Characters':
        this.selectedpage = 'character'
        break;
      default:
        this.selectedpage = ''
    }
  }
}
