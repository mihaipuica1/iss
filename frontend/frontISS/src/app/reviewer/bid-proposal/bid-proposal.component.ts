import { Component, OnInit } from '@angular/core';
import {Paper} from '../../models/paper';

@Component({
  selector: 'app-bid-proposal',
  templateUrl: './bid-proposal.component.html',
  styleUrls: ['./bid-proposal.component.css']
})
export class BidProposalComponent implements OnInit {
  title: String;
  title2: String;

  papers: Paper[];
  constructor() { this.title = 'Reviewer';
    this.title2 = 'Bid proposal';
    this.papers = [new Paper(1, 'a', 'a','a','a','a','a')]; }

  ngOnInit() {
  }

}
