import { createAction, props } from '@ngrx/store';
import { Character } from '../models/character.model';

export const getCharacter = createAction('[Character] Get Character', props<{ characterId: string }>());
export const getCharacterSuccess = createAction('[Character] Get Character Success', props<{ character: Character }>());
export const getAllCharacters = createAction('[Character] Get All Characters', props<{ pageNumber: number }>());
export const getAllCharactersSuccess = createAction('[Character] Get All Characters Success', props<{ characters: Character[] }>());
export const getCharactersFailure = createAction('[Character] Get Characters Failure', props<{ error: any }>());