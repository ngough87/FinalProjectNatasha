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

  delete(id: number): Observable<void>   {
    return this.http.delete<void>(this.url + 'api/users/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('userService.delete(): error deleting user profile: ' + err)
        );
      })
    );
    }

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

  findUserByUsername(username: string): Observable<User> {
    return this.http.get<User>(this.url + 'api/users/find/' + username, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.findUser(): error finding user.')
        );
      })
    );
  }

  findUsersByPreferences() : Observable<User[]> {
    return this.http.get<User[]>(this.url + 'api/users/matches', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.findUsersByPreferences(): error finding user.')
        );
      })
    );
  }

  findFriends(id: number): Observable<User[]> {
    return this.http.get<User[]>(this.url + 'api/users/' + id + '/friends', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.findFriend(): error finding friend.')
        );
      })
    );
  }

  findFollowers(id:number): Observable<User[]> {
    return this.http.get<User[]>(this.url + 'api/users/' + id + '/following', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.findFollowers(): error finding friend.')
        );
      })
    );
  }

  followUser(id:number):Observable<void> {
    return this.http.post<void>(this.url + "api/followedUsers/" + id, "",this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.followUser(): error following user.')
        );
      })
    );
  }

  unfollowUser(id:number):Observable<void> {
    return this.http.delete<void>(this.url + "api/followedUsers/" + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.followUser(): error unfollowing user.')
        );
      })
    );
  }


  // userPreferences(id:number):Observable<void> {
  //   return this.http.put<void>(this.url + "api/users/matches/" + id, "",this.getHttpOptions()).pipe(
  //     catchError((err: any) => {
  //       console.log(err);
  //       return throwError(
  //         () => new Error('UserService.pref(): error updating preferences.')
  //       );
  //     })
  //   );
  // }


}
