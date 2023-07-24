import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { House } from 'src/app/models/house';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HouseService {

  baseUrl: string = environment.baseUrl;

  constructor(private httpClient: HttpClient) { }

  public getHouses(pageNumber: number, pageSize: number): Observable<Array<House>>{
    return this.httpClient.get<Array<House>>(`${this.baseUrl}/houses?page=${pageNumber}&pageSize=${pageSize}`);
  }

  public getHouseDetails(houseId: number): Observable<House>{
    return this.httpClient.get<House>(`${this.baseUrl}/houses/${houseId}`);
  }
}
