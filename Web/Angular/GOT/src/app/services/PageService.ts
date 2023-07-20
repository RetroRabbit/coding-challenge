import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class PageService {
    pageSize: number = 10;
    bookLastPage : number = 2;
    houseLastPage : number = 45;
    characterLastPage : number = 214;
    constructor() { }
}
