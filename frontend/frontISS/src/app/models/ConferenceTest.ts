import {Section} from './section';
import {Program} from './Program';
import {Location} from './Location';

export class ConferenceTest {
  name: string;
  location: Location;
  section: Section;
  program: Program;
  programCommittee: string[];
  participants: string[];
  speakers: string[];


  constructor(name: string,date: string,interval:string,abstractDeadline: string, proposalDeadline: string,bidingDeasline: string,country:string,city: string) {
    this.location= new Location(null,null,null,null);
    this.program = new Program(null,null,null,null,null,null);
    this.name = name;
    this.location.country =country;
    this.location.city= city;
    this.program.date =date;
    this.program.interval = interval;
    this.program.abstractDeadline =abstractDeadline;
    this.program.proposalDeadline = proposalDeadline;
    this.program.biddingDeadline = bidingDeasline;
  }
}
