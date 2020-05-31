export class ConferenceTest {
  date: string;
  interval: string;
  abstractDeadline: string;
  proposalDeadline: string;
  biddingDeadline: string;
  name: string;
  country: string;
  city: string;

  constructor(date: string, interval: string, abstractDeadline: string, proposalDeadline: string, biddingDeadline: string, name: string, country: string, city: string) {
    this.date = date;
    this.interval = interval;
    this.abstractDeadline = abstractDeadline;
    this.proposalDeadline = proposalDeadline;
    this.biddingDeadline = biddingDeadline;
    this.name = name;
    this.country = country;
    this.city = city;
  }
}
