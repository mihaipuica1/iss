import {selector} from 'rxjs/operator/publish';
import {Section} from './section';

export class Conference {
  id: string;
  date: string;
  interval: string;
  abstractDeadline: string;
  proposalDeadline: string;
  biddingDeadline: string;
  name: string;
  country: string;
  city: string;
  section: Section;


  constructor(id: string, date: string, interval: string, abstractDeadline: string, proposalDeadline: string, biddingDeadline: string, name: string, country: string, city: string, section: Section) {
    this.id = id;
    this.date = date;
    this.interval = interval;
    this.abstractDeadline = abstractDeadline;
    this.proposalDeadline = proposalDeadline;
    this.biddingDeadline = biddingDeadline;
    this.name = name;
    this.country = country;
    this.city = city;
    this.section = section;
  }
}
