import { Component, OnInit } from '@angular/core';
import {Section} from '../../models/section';
import {pTest} from '../../models/pTest';
import {ConferenceTest} from '../../models/ConferenceTest';
import {SectionTest} from '../../models/SectionTest';
import {ConferenceChairServiceService} from '../../Shared/conference-chair-service.service';

@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {
  section: Section;
  constructor(private service: ConferenceChairServiceService) { }

  ngOnInit() {
  }

  onSubmit(id: string) {
    let x= new SectionTest(this.section.name);
    this.service.saveSection(id, x);
  }

}
