import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Message } from '../models/message';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class MessageService {
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

  index(): Observable<Message[]> {
    return this.http
      .get<Message[]>(this.url + 'api/messages/received', this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'MessageService.index(): error retrieving message: ' + err
              )
          );
        })
      );
  }

  create(message: Message): Observable<Message> {
    return this.http
      .post<Message>(this.url + 'api/messages/create', message, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () =>
              new Error(
                'MessageService.create(): error creating message: ' + err
              )
          );
        })
      );
  }

  destroy(id: number): Observable<void> {
    return this.http
      .delete<void>(this.url + 'api/messages/' + id, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'MessageService.destroy(): error deleting message: ' + err
              )
          );
        })
      );
  }

  getSenderMessage(senderId: number): Observable<Message[]> {
    console.log(senderId);
    return this.http
      .get<Message[]>(
        this.url + 'api/messages/senderId/' + senderId,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'MessageService.getSenderMessage(): error retrieving message for sender: ' +
                  err
              )
          );
        })
      );
  }

  getReceiverMessage(receiverId: number): Observable<Message[]> {
    console.log(receiverId);
    return this.http
      .get<Message[]>(
        this.url + 'api/messages/receiverId/' + receiverId,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'MessageService.getSenderMessage(): error retrieving message for receiver: ' +
                  err
              )
          );
        })
      );
  }
}
