import { Component, OnInit } from '@angular/core';
import {Paper} from '../../models/paper';
import {ReviewerServiceService} from '../../Shared/reviewer-service.service';

@Component({
  selector: 'app-give-qualifier',
  templateUrl: './give-qualifier.component.html',
  styleUrls: ['./give-qualifier.component.css']
})
export class GiveQualifierComponent implements OnInit {
  title: String;
  title2: String;
  errorMessage: string;

  papers: Paper[];
  constructor(private service: ReviewerServiceService) {
    this.title = 'Reviewer';
    this.title2 = 'Give Qualifier';

  }
  ngOnInit() {
    this.getPapers();
  }


  getPapers() {
    this.service.findAll()
      .subscribe(
        papers => this.papers = papers,
        error => this.errorMessage = <any>error
      );
  }
}
