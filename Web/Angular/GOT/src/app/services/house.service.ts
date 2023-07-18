import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { House } from '../models/house.model';

@Injectable({
  providedIn: 'root'
})
export class HouseService {
  private baseUrl = 'https://anapioficeandfire.com/api/houses';

  constructor(private http: HttpClient) {}

  getHouse(houseId: string): Observable<House> {
    const url = `${this.baseUrl}/${houseId}`;
    return this.http.get<House>(url);
  }
  getAllHouses(pageNumber: number): Observable<House[]> {
    const queries = `?page=${pageNumber}&pageSize=8`;
    return this.http.get<House[]>(this.baseUrl + queries);
  }
}
