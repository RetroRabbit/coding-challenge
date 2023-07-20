import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import { CharacterService } from '../services/character.service';
import * as CharacterActions from '../actions/character.actions';
import { getAllCharacters, getAllCharactersSuccess } from '../actions/character.actions';

@Injectable()
export class CharacterEffects {
  constructor(
    private actions$: Actions,
    private characterService: CharacterService
  ) { }
  getCharacter$ = createEffect(() =>
    this.actions$.pipe(
      ofType(CharacterActions.getCharacter),
      mergeMap(({ characterId }) =>
        this.characterService.getCharacter(characterId).pipe(
          map(character => CharacterActions.getCharacterSuccess({ character })),
          catchError(error => of(CharacterActions.getCharactersFailure({ error })))
        )
      ) 
    )
  );
  
  getAllCharacters$ = createEffect(() =>
  this.actions$.pipe(
    ofType(CharacterActions.getAllCharacters),
    mergeMap(({ pageNumber }) =>
      this.characterService.getAllCharacters(pageNumber).pipe(
        map(characters => CharacterActions.getAllCharactersSuccess({ characters })),
        catchError(error => of(CharacterActions.getCharactersFailure({ error })))
      )
    )
  ));
}
