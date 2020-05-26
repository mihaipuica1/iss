import { Component, OnInit } from '@angular/core';
import {Paper} from '../../models/paper';

@Component({
  selector: 'app-my-submissions',
  templateUrl: './my-submissions.component.html',
  styleUrls: ['./my-submissions.component.css']
})
export class MySubmissionsComponent implements OnInit {
  title: string;
  title2: string;
  papers: Paper[];
  constructor() {
    this.title = 'My Submissions';
    this.title2 = 'My Papers';
    this.papers = [new Paper('1', '1', '1')];
  }

  ngOnInit() {
  }

}
