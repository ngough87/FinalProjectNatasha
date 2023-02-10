import { AuthService } from './auth.service';
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

  constructor(private http: HttpClient, private auth: AuthService) {}
  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      },
    };
    return options;
  }


  updateAddress(address: Address): Observable<Address> {
    console.log('In updateAddress()' + address.id)
    // Create GET request to authenticate credentials
    return this.http.put<Address>(this.url + 'api/address/' + address.id, address, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AddressService.update(): error updating address.')
        );
      })
    );
  }

  createAddress(address: Address ): Observable<Address> {

    console.log(address.city);
    // Create GET request to authenticate credentials
    return this.http.post<Address>(this.url + 'api/address', address, this.getHttpOptions()).pipe(
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
