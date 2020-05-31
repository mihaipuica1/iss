import { Component, OnInit } from '@angular/core';
import {Conference} from '../../models/conference';
import {ActivatedRoute, Router} from '@angular/router';
import {pTest} from '../../models/pTest';
import {ConferenceTest} from '../../models/ConferenceTest';
import {ConferenceChairServiceService} from '../../Shared/conference-chair-service.service';
import {Location} from '../../models/Location';
import {Program} from '../../models/Program';
import {SectionTest} from '../../models/SectionTest';
import {Section} from '../../models/section';



@Component({
  selector: 'app-add-conference',
  templateUrl: './add-conference.component.html',
  styleUrls: ['./add-conference.component.css']
})
export class AddConferenceComponent implements OnInit {
  conference: Conference;
  location: Location;
  program: Program;
  errorMessage: string;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private service: ConferenceChairServiceService
  ) {
    this.location = new Location(null,null,null,null);
    this.program = new Program(null,null,null,null,null,null);
    this.conference = new Conference(null,null,this.location,null,this.program,null,null,null);
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
    console.log(this.conference);
    const thisID = this.conference.id.toString();

    let x = new ConferenceTest(this.conference.name,this.conference.program.date,this.conference.program.interval,this.conference.program.abstractDeadline,this.conference.program.proposalDeadline,this.conference.program.biddingDeadline,this.conference.location.country,this.conference.location.city);

    this.service.updateConference(x).subscribe(res=>console.log(res));
  }
}
