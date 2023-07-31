import { createReducer, on } from '@ngrx/store';
import { Character } from '../models/character.model';
import * as CharacterActions from '../actions/character.actions';

export interface CharacterState {
  character: Character | null;
  characters: Character[];
  loading: boolean;
  error: any;
}

export const initialState: CharacterState = {
  character: null,
  characters: [],
  loading: false,
  error: null
};

export const characterReducer = createReducer(
  initialState,
  on(CharacterActions.getCharacter, state => ({ ...state, loading: true })),
  on(CharacterActions.getCharacter, (state, { characterId }) => {
    const selectedCharacter = state.characters.find(character => character.url === characterId);
    return {
      ...state,
      house: selectedCharacter || null,
      loading: true
    };
  }),
  on(CharacterActions.getCharacterSuccess, (state, { character }) => ({ ...state, character, loading: false })),
  on(CharacterActions.getAllCharacters, state => ({ ...state, loading: true })),
  on(CharacterActions.getAllCharactersSuccess, (state, { characters }) => ({ ...state, characters, loading: false })),
  on(CharacterActions.getCharactersFailure, (state, { error }) => ({ ...state, error, loading: false }))
);
