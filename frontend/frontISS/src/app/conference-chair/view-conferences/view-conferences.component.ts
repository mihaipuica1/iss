import { Component, OnInit } from '@angular/core';
import {Conference} from '../../models/conference';
import {ConferenceChairServiceService} from '../../Shared/conference-chair-service.service';

@Component({
  selector: 'app-view-conferences',
  templateUrl: './view-conferences.component.html',
  styleUrls: ['./view-conferences.component.css']
})
export class ViewConferencesComponent implements OnInit {
  title: string;
  errorMessage: string;
  conferences: Conference[];
  constructor(private service: ConferenceChairServiceService) {
    this.title = 'Conferences';
  }
  ngOnInit() {
    this.getConferences();
  }


  getConferences() {
    this.service.findAll()
      .subscribe(
        conferences => this.conferences = conferences,
        error => this.errorMessage = <any>error
      );
  }

}
