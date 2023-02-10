import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { WalkType } from '../models/walk-type';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class WalkTypeService {
  private url = environment.baseUrl;

  constructor(private http: HttpClient, private auth: AuthService) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(): Observable<WalkType[]> {
    console.log('In walklocationService index');

    // Create GET request to authenticate credentials
    return this.http.get<WalkType[]>(this.url + 'api/walkType', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('WalkLocationService.index(): error updating address.')
        );
      })
    );
  }
}
