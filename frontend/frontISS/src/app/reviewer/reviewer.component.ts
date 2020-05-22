import { Component, OnInit } from '@angular/core';
import {Paper} from '../models/paper';

@Component({
  selector: 'app-reviewer',
  templateUrl: './reviewer.component.html',
  styleUrls: ['./reviewer.component.css']
})
export class ReviewerComponent implements OnInit {
  title: String;
  title2: String;

  papers: Paper[];


  constructor() {
    this.title = 'Reviewer';
    this.title2 = 'Papers';
    this.papers = [new Paper('1', 'a', 'a')];
  }

  ngOnInit() {
  }
onclick(){
    return '$(\'#paper\').click(function(){$(this).data(\'clicked\', false);})';
}
}
