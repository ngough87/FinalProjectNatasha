import { AuthService } from 'src/app/services/auth.service';
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



  index(): Observable<Gender[]> {


    // Create GET request to authenticate credentials
    return this.http.get<Gender[]>(this.url + 'api/genders', this.getHttpOptions()).pipe(
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
