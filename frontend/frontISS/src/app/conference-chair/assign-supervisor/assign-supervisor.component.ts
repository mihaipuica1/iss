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
  constructor() { }

  ngOnInit() {
    this.title = 'Choose supervisor';
    this.title1 = 'Pc Member';
    this.title2 = 'Section';

  }

}
