import { Component, OnInit } from '@angular/core';
import {Paper} from '../../models/paper';

@Component({
  selector: 'app-view-other-proposals',
  templateUrl: './view-other-proposals.component.html',
  styleUrls: ['./view-other-proposals.component.css']
})
export class ViewOtherProposalsComponent implements OnInit {
title: string;
papers: Paper[];
  constructor() {
    this.title = 'Other Proposals';
  }

  ngOnInit() {
  }

}
