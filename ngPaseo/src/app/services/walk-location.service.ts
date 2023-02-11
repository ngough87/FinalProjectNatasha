import { WalkLocation } from './../models/walk-location';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class WalkLocationService {
  private url = environment.baseUrl;

  constructor(private http: HttpClient, private auth: AuthService) {}
  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  create(walkLocation: WalkLocation): Observable<WalkLocation> {
    return this.http
      .post<WalkLocation>(
        this.url + 'api/walkLocation',
        walkLocation,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error('WalkLocation.create(): error creating WalkLocation.')
          );
        })
      );
  }

  update(walkLocation: WalkLocation): Observable<WalkLocation> {
    console.log('In updateAddress()' + walkLocation.id);
    // Create GET request to authenticate credentials
    return this.http
      .put<WalkLocation>(
        this.url + 'api/walkLocation/' + walkLocation.id,
        walkLocation,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('AddressService.update(): error updating address.')
          );
        })
      );
  }

  index(): Observable<WalkLocation[]> {
    console.log('In walklocationService index');

    // Create GET request to authenticate credentials
    return this.http
      .get<WalkLocation[]>(
        this.url + 'api/walkLocation/',
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error('WalkLocationService.index(): error updating address.')
          );
        })
      );
  }
}
