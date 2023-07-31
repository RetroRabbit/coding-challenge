import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Character } from '../models/character.model';
import { PageService } from './PageService';
@Injectable({
  providedIn: 'root'
})
export class CharacterService {
  private baseUrl = 'https://anapioficeandfire.com/api/characters';

  constructor(private http: HttpClient, private pageService : PageService) {}

  getCharacter(characterId: string): Observable<Character> {
    const url = `${characterId}`;
    return this.http.get<Character>(url);
  }

  getAllCharacters(pageNumber: number): Observable<Character[]> {
    const queries = `?page=${pageNumber}&pageSize=${this.pageService.pageSize}`;
    return this.http.get<Character[]>(this.baseUrl + queries);
  }

  getACharacter(endpoint: string): Observable<Character> {
    const url = `${endpoint}`;

    return this.http.get<Character>(url);
  }
}
