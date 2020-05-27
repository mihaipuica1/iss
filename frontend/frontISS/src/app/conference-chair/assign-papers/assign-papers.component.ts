import { Component, OnInit } from '@angular/core';
import {PcMember} from '../../models/pcmember';

@Component({
  selector: 'app-assign-papers',
  templateUrl: './assign-papers.component.html',
  styleUrls: ['./assign-papers.component.css']
})
export class AssignPapersComponent implements OnInit {
  title2: String;

  pcMembers: PcMember[];
  constructor() {
    this.title2 = 'PcMembers';
    this.pcMembers = [new PcMember('1', 'a', 'a@cs.ubbcluj.ro')];}

  ngOnInit() {
  }

}
