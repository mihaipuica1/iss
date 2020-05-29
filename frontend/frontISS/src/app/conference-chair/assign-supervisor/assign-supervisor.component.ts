import { Component, OnInit } from '@angular/core';
import {PcMember} from '../../models/pcmember';
import {Section} from '../../models/section';

@Component({
  selector: 'app-assign-supervisor',
  templateUrl: './assign-supervisor.component.html',
  styleUrls: ['./assign-supervisor.component.css']
})
export class AssignSupervisorComponent implements OnInit {
  pcMembers: PcMember[];
  sections: Section[];
  title: string;
  title1: string;
  title2: string;
  pcMember: any;
  i: any;
  ok: boolean;
  constructor() { }

  ngOnInit() {
    this.title = 'Choose supervisor';
    this.title1 = 'Pc Member';
    this.title2 = 'Section';

  }

  assign(value: string, value2: string) {

  }
  isInFirstTable(value: string) {
    this.ok = false;
    for (this.i = 0; this.i <= this.pcMembers.length; this.i++) {
        if (this.pcMembers[this.i].name === value) {
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
