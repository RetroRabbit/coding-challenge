import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { House } from '../models/house.model';
import { PageService } from './PageService';
@Injectable({
  providedIn: 'root'
})
export class HouseService {
  private baseUrl = 'https://anapioficeandfire.com/api/houses';

  constructor(private http: HttpClient, private pageService : PageService) {}

  getHouse(houseId: string): Observable<House> {
    const url = `${this.baseUrl}/${houseId}`;
    return this.http.get<House>(url);
  }
  getAllHouses(pageNumber: number): Observable<House[]> {
    const queries = `?page=${pageNumber}&pageSize=${this.pageService.pageSize}`;
    return this.http.get<House[]>(this.baseUrl + queries);
  }
  getAHouse(endpoint: string): Observable<House> {
    const url = `${endpoint}`;
    return this.http.get<House>(url);
  }
}
