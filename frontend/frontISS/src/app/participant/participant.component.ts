import { Component, OnInit } from '@angular/core';
import { Paper } from '../models/paper';

@Component({
  selector: 'app-participant',
  templateUrl: './participant.component.html',
  styleUrls: ['./participant.component.css']
})
export class ParticipantComponent implements OnInit {
  paper:Paper;
  constructor() { }

  ngOnInit() {
    this.paper= new Paper(1, 'a', 'b','c','b','d','v');
  }

}
