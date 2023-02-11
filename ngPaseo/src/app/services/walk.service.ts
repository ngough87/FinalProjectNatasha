import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Walk } from '../models/walk';
import { AuthService } from './auth.service';
import { catchError, throwError, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
@Injectable()
export class WalkService {


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



  index(): Observable <Walk[]>{
    //return this.http.get<Todo[]>(this.url + '?sorted=true').pipe(
      return this.http.get<Walk[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'WalkService.index(): error retrieving walk: ' + err
            )
        );
      })
    );
  }


  create(walk: Walk): Observable<Walk> {





    const httpOptions = {
      headers: {
        'Content-Type': 'application/json'
      }
    };

    return this.http.post<Walk>(this.url + 'api/walks', walk, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () =>
            new Error(
              'WalkService.create(): error creating Walk: ' + err
            )
        );
      })
    );
  }

  update(walk: Walk): Observable<Walk> {
    console.log(walk);
    // Create GET request to authenticate credentials
    return this.http.put<Walk>(this.url + 'api/users/' + walk.id, walk, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('WalkService.update(): error updating walk.')
        );
      })
    );
  }

  destroy(){}
}

