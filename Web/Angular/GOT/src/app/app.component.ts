import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  ChangeLinkColor(event: MouseEvent) {
    var links = document.getElementsByClassName('nav-link');
    for (var i = 0; i < links.length; i++) {
      links[i].classList.remove('selected');
    }
    var selectedLink = event.target as HTMLElement;
    selectedLink.classList.add('selected');
  }
  
}
