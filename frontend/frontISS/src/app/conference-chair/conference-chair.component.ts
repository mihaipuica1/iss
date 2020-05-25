import { Component, OnInit } from '@angular/core';
import {PcMember} from '../models/pcmember';

@Component({
  selector: 'app-conference-chair',
  templateUrl: './conference-chair.component.html',
  styleUrls: ['./conference-chair.component.css']
})
export class ConferenceChairComponent implements OnInit {
  title: String;
  title2: String;
  
  pcMembers: PcMember[];
  constructor() {
    this.title = 'Conference Chair';
    this.title2 = 'PcMembers';
    this.pcMembers = [new PcMember('1', 'a', 'a@cs.ubbcluj.ro')];
  }

  ngOnInit() {
  }

}
