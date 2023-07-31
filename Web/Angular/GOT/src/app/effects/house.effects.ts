import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, mergeMap } from 'rxjs/operators';
import { HouseService } from '../services/house.service';
import * as HouseActions from '../actions/house.actions';

@Injectable()
export class HouseEffects {
  constructor(
    private actions$: Actions,
    private houseService: HouseService
  ) { }
  getHouse$ = createEffect(() =>
    this.actions$.pipe(
      ofType(HouseActions.getHouse),
      mergeMap(({ houseId }) =>
        this.houseService.getHouse(houseId).pipe(
          map(house => HouseActions.getHouseSuccess({ house })),
          catchError(error => of(HouseActions.getHousesFailure({ error })))
        )
      )
    )
  );
  getAllHouses$ = createEffect(() =>
  this.actions$.pipe(
    ofType(HouseActions.getAllHouses),
    mergeMap(({ pageNumber }) =>
      this.houseService.getAllHouses(pageNumber).pipe(
        map(houses => HouseActions.getAllHousesSuccess({ houses })),
        catchError(error => of(HouseActions.getHousesFailure({ error })))
      )
    )
  )
);
}
