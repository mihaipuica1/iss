import { Component, OnInit } from '@angular/core';
import {Conference} from '../../models/conference';
import {ActivatedRoute, Router} from '@angular/router';
import {pTest} from '../../models/pTest';
import {ConferenceTest} from '../../models/ConferenceTest';
import {ConferenceChairServiceService} from '../../Shared/conference-chair-service.service';

@Component({
  selector: 'app-add-conference',
  templateUrl: './add-conference.component.html',
  styleUrls: ['./add-conference.component.css']
})
export class AddConferenceComponent implements OnInit {
  conference: Conference;
  errorMessage: string;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private service: ConferenceChairServiceService
  ) {
    // this.conference = new Conference("1","A","a","A","a","a","A","a","a",null);
  }


  ngOnInit() {
    this.getConference();
  }
  getConference() {
    this.service.getConference(1)
      .subscribe(
        conference => this.conference = conference,
        error => this.errorMessage = <any>error
      );
  }

  onSubmit() {
    const thisID = this.conference.id.toString();
    // tslint:disable-next-line:max-line-length
    let x = new ConferenceTest(this.conference.date,this.conference.interval,this.conference.abstractDeadline,this.conference.proposalDeadline,this.conference.biddingDeadline,this.conference.name,this.conference.country,this.conference.city);

    this.service.updateConference(x);
  }
}
