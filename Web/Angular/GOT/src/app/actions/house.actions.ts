import { createAction, props } from '@ngrx/store';
import { House } from '../models/house.model';

export const getHouse = createAction('[House] Get House', props<{ houseId: string }>());
export const getHouseSuccess = createAction('[House] Get House Success', props<{ house: House }>());
export const getAllHouses = createAction('[House] Get All Houses', props<{ pageNumber: number }>());

export const getAllHousesSuccess = createAction('[House] Get All Houses Success', props<{ houses: House[] }>());
export const getHousesFailure = createAction('[House] Get Houses Failure', props<{ error: any }>());