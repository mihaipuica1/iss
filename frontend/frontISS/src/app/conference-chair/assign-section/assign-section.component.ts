import { Component, OnInit } from '@angular/core';
import {Paper} from '../../models/paper';
import {Section} from '../../models/section';
import {subscriptionLogsToBeFn} from 'rxjs/testing/TestScheduler';

@Component({
  selector: 'app-assign-section',
  templateUrl: './assign-section.component.html',
  styleUrls: ['./assign-section.component.css']
})
export class AssignSectionComponent implements OnInit {
papers: Paper[];
sections: Section[];
title: string;
title1: string;
title2: string;
  constructor() {
    this.title = 'Choose the papers Sections';
    this.title1 = 'Papers';
    this.title2 = 'Sections';
  }

  ngOnInit() {
  }

}
