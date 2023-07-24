import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Character } from 'src/app/models/character';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CharacterService {

  private baseUrl = environment.baseUrl;

  constructor(private httpClient: HttpClient) { }

  public getCharacter(url: string): Observable<Character>{
    return this.httpClient.get<Character>(url)
  }

  public getCharacters(pageNumber: number, pageSize: number): Observable<Array<Character>>{
    return this.httpClient.get<Array<Character>>(`${this.baseUrl}/characters?page=${pageNumber}&pageSize=${pageSize}`);
  }

}
