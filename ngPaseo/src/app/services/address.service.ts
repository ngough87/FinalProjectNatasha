import { Address } from './../models/address';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AddressService {
  private url = environment.baseUrl;

  constructor(private http: HttpClient) {}

  updateAddress(address: Address, username: string|null, password: string|null): Observable<Address> {
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
    return this.http.put<Address>(this.url + 'api/address/' + address.id, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AddressService.update(): error updating address.')
        );
      })
    );
  }

  createAddress(address: Address, username: string|null, password: string|null): Observable<Address> {
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
    return this.http.post<Address>(this.url + 'api/address/', address, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AddressService.create(): error creating address.')
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
