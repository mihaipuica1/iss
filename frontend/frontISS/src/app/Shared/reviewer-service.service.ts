import { Injectable } from '@angular/core';
import {Paper} from '../models/paper';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class ReviewerServiceService {
  private paperURL: string;
  private paperUReL = 'http://localhost:8080/api/paper';
  private paper: Paper;
  constructor(private http: HttpClient) {
    this.paperURL = 'http://localhost:8080/api/papers';
  }

  findAll(): Observable<Paper[]> {
    return this.http
      .get<Array<Paper>>(this.paperURL);
  }

  getPaper(id: number): Observable<Paper> {
    const url = `${this.paperUReL}/${id}`;
    return this.http.get<Paper>(url);
  }
}
