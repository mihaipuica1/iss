import { Injectable, Output } from '@angular/core';
import { Paper } from '../models/paper';
import { pTest } from '../models/pTest';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import {map, retry, catchError, tap, mergeMap} from "rxjs/operators";

@Injectable()
export class SpeakerServiceService {

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

  save(paper): Observable<Paper> {
      return this.http.post<Paper>(this.paperUReL, paper);
  }


  update(paper, id): Observable<Paper> {
    const url = `${this.paperUReL}/${id}`;
    return this.http.put<Paper>(url, paper);
  }

  }

