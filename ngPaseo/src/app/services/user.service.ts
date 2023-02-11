import { AuthService } from './auth.service';
import { User } from 'src/app/models/user';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
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



  updateUser(user: User): Observable<User> {
    console.log(user);
    // Create GET request to authenticate credentials
    return this.http.put<User>(this.url + 'api/users/' + user.id, user, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.update(): error updating user.')
        );
      })
    );
  }

  deleteUser(){}

  generateBasicAuthCredentials(username: string | null
    , password: string | null
    ): string {
    return btoa(`${username}:${password}`);
  }

  findUser(id: number): Observable<User> {


    return this.http.get<User>(this.url + 'api/users/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.findUser(): error finding user.')
        );
      })
    );
  }

}
