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
ok: boolean;
i: any;
  constructor() {
    this.title = 'Choose the papers Sections';
    this.title1 = 'Papers';
    this.title2 = 'Sections';
  }

  ngOnInit() {
  }

  assign(value: string, value2: string) {

  }

  isInFirstTable(value: string) {
    this.ok = false;
    for (this.i = 0; this.i <= this.papers.length; this.i++) {
      if (this.papers[this.i].title === value) {
        this.ok = true;
      }
    }
    return this.ok;
  }

  isInSecondTable(value: string) {
  this.ok = false;
  for (this.i = 0; this.i <= this.sections.length; this.i++) {
    if (this.sections[this.i].name === value) {
      this.ok = true;
    }
  }
  return this.ok;
}

}
