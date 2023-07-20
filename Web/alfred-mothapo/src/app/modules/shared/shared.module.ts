import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from './sidebar/sidebar/sidebar.component';
import { NavbarComponent } from './navbar/navbar/navbar.component';
import { SidebarModule } from 'primeng/sidebar';
import { ButtonModule } from 'primeng/button';
import {RouterModule} from '@angular/router';



@NgModule({
  declarations: [
    SidebarComponent,
    NavbarComponent
  ],
  imports: [
    CommonModule,
    SidebarModule,
    ButtonModule,
    RouterModule
  ],
  exports: [SidebarComponent, NavbarComponent]
})
export class SharedModule { }
