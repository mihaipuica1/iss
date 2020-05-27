import { Component, OnInit } from '@angular/core';
import {PcMember} from '../models/pcmember';

@Component({
  selector: 'app-conference-chair',
  templateUrl: './conference-chair.component.html',
  styleUrls: ['./conference-chair.component.css']
})
export class ConferenceChairComponent implements OnInit {
  title: String;

  constructor() {
    this.title = 'Conference Chair';

  }

  ngOnInit() {
  }

}
