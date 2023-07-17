import { createReducer, on } from '@ngrx/store';
import { House } from '../models/house.model';
import * as HouseActions from '../actions/house.actions';

export interface HouseState {
  house: House | null;
  houses: House[];
  loading: boolean;
  error: any;
}

export const initialState: HouseState = {
  house: null,
  houses: [],
  loading: false,
  error: null
};

export const houseReducer = createReducer(
  initialState,
  on(HouseActions.getHouse, (state, { houseId }) => {
    const selectedHouse = state.houses.find(house => house.name === houseId);
    return {
      ...state,
      house: selectedHouse || null,
      loading: true
    };
  }),
  on(HouseActions.getHouseSuccess, (state, { house }) => ({ ...state, house, loading: false })),
  on(HouseActions.getAllHouses, state => ({ ...state, loading: true })),
  on(HouseActions.getAllHousesSuccess, (state, { houses }) => ({ ...state, houses, loading: false })),
  on(HouseActions.getHousesFailure, (state, { error }) => ({ ...state, error, loading: false }))
);
