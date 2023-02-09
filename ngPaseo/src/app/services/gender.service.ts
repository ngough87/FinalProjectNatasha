import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Gender } from '../models/gender';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class GenderService {
  private url = environment.baseUrl;

  constructor(private http: HttpClient) {}

  index(username: string|null, password: string|null): Observable<Gender[]> {
    // Make credentials
    const credentials = this.generateBasicAuthCredentials(username, password);
    // Send credentials as Authorization header specifying Basic HTTP authentication
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };

    // Create GET request to authenticate credentials
    return this.http.get<Gender[]>(this.url + '/api/gender', httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('GenderService.index(): error getting genders.')
        );
      })
    );
  }


  generateBasicAuthCredentials(username: string | null
    , password: string | null
    ): string {
    return btoa(`${username}:${password}`);
  }

}
