import { Component, OnInit } from '@angular/core';
import {Paper} from '../../models/paper';

@Component({
  selector: 'app-give-qualifier',
  templateUrl: './give-qualifier.component.html',
  styleUrls: ['./give-qualifier.component.css']
})
export class GiveQualifierComponent implements OnInit {
  title: String;
  title2: String;

  papers: Paper[];
  constructor() {
    this.title = 'Reviewer';
    this.title2 = 'Papers';
    this.papers = [new Paper('1', 'a', 'a','a','a','a','a')];
  }

  ngOnInit() {
  }

}
