import { Component, OnInit } from '@angular/core';
import {PcMember} from '../../models/pcmember';
import {Paper} from '../../models/paper';

@Component({
  selector: 'app-assign-papers',
  templateUrl: './assign-papers.component.html',
  styleUrls: ['./assign-papers.component.css']
})
export class AssignPapersComponent implements OnInit {
  title: string;

title2: string;
  pcMembers: PcMember[];
  papers: Paper[];
  constructor() {
    this.title = 'PcMembers';
    this.pcMembers = [new PcMember('1', 'a', 'a@cs.ubbcluj.ro')];
    this.title2 = 'Papers';
  }

  ngOnInit() {
  }

  assign(value: string, value2: string) {

  }
}
