import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Character } from 'src/app/models/character';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CharacterService {

  baseUrl: string = environment.baseUrl;

  constructor(private httpClient: HttpClient) { }

  public getCharacter(url: string): Observable<Character>{
    return this.httpClient.get<Character>(url)
  }
  
}
