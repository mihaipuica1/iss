import { Injectable } from '@angular/core';
import {Paper} from '../models/paper';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Conference} from '../models/conference';

@Injectable()
export class ConferenceChairServiceService {
  private conferenceURL: string;
  private conferenceUReL = 'http://localhost:8080/api/event';
  private conference: Conference;
  constructor(private http: HttpClient) {
    this.conferenceURL = 'http://localhost:8080/api/events';
  }

  findAll(): Observable<Conference[]> {
    return this.http
      .get<Array<Conference>>(this.conferenceURL);
  }

  getConference(id: number): Observable<Conference> {
    const url = `${this.conferenceUReL}/${id}`;
    return this.http.get<Conference>(url);
  }
}
