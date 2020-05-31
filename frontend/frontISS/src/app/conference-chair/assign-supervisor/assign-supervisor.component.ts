import { Component, OnInit } from '@angular/core';
import {PcMember} from '../../models/pcmember';
import {Section} from '../../models/section';
import {ConferenceChairServiceService} from '../../Shared/conference-chair-service.service';
import {forEach} from '@angular/router/src/utils/collection';
import {Conference} from '../../models/conference';
import {PcMem} from '../../models/PcMem';

@Component({
  selector: 'app-assign-supervisor',
  templateUrl: './assign-supervisor.component.html',
  styleUrls: ['./assign-supervisor.component.css']
})
export class AssignSupervisorComponent implements OnInit {
  events : Conference[];
  pcMembers: PcMember[];
  pcMem: string[];
  sections: Section[];
  title: string;
  title1: string;
  title2: string;
  pcMember: any;
  i: any;
  ok: boolean;
  errorMessage: string;
  constructor(private service: ConferenceChairServiceService) {
    this.title = 'Choose supervisor';
    this.title1 = 'Pc Member';
    this.title2 = 'Section';
  }

  ngOnInit() {
this.getPcMembers();
this.getSections();

  }

  assign(value: string, value2: string) {

  }
  isInFirstTable(value: string) {
    this.ok = false;
    for (this.i = 0; this.i <= this.pcMembers.length; this.i++) {
        if (this.pcMembers[this.i].name === value) {
          this.ok = true;
        }
    }
    return this.ok;
  }

  isInSecondTable(value: string) {
    this.ok = false;
    for (this.i = 0; this.i <= this.sections.length; this.i++) {
      if (this.sections[this.i].name === value) {
        this.ok = true;
      }
    }
    return this.ok;
  }
  getPcMembers() {
    this.service.findAllPcMembers()
      .subscribe(
        members => this.pcMem = members,
        error => this.errorMessage = <any>error
      );
  }
  getConferences() {
    this.service.findAll()
      .subscribe(
        events => this.events = events,
        error => this.errorMessage = <any>error
      );
  }
  getSections() {
    for ( this.i = 0; this.i <= this.events.length; this.i++) {
      this.sections.push(this.events[this.i].section);
    }
  }

}
