import { Component, OnInit } from '@angular/core';
import {Conference} from '../../models/conference';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-add-conference',
  templateUrl: './add-conference.component.html',
  styleUrls: ['./add-conference.component.css']
})
export class AddConferenceComponent implements OnInit {
  conference: Conference;
  constructor(private route: ActivatedRoute,
              private router: Router,
  ) {
    this.conference = new Conference();
  }


  ngOnInit() {
  }

  onSubmit() {
  }
}
