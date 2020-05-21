import { Component, OnInit } from '@angular/core';
import { Proposal } from '../../models/proposal';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-proposal-form',
  templateUrl: './proposal-form.component.html',
  styleUrls: ['./proposal-form.component.css']
})
export class ProposalFormComponent {

  proposal: Proposal;

  constructor(private route: ActivatedRoute,
              private router: Router,
  ) {
    this.proposal = new Proposal();
  }

  onSubmit() {  }

}
