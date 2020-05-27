import { Component, OnInit } from '@angular/core';
import {Conference} from '../../models/conference';

@Component({
  selector: 'app-view-conferences',
  templateUrl: './view-conferences.component.html',
  styleUrls: ['./view-conferences.component.css']
})
export class ViewConferencesComponent implements OnInit {
  title: string;
  conferences: Conference[];
  constructor() {
    this.title = 'Conferences';
  }

  ngOnInit() {
  }

}
