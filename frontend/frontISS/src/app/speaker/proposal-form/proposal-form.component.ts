import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {Paper} from '../../models/paper';

@Component({
  selector: 'app-proposal-form',
  templateUrl: './proposal-form.component.html',
  styleUrls: ['./proposal-form.component.css']
})
export class ProposalFormComponent {

  proposal: Paper;

  constructor(private route: ActivatedRoute,
              private router: Router,
  ) {
    this.proposal = new Paper( null,null,null,null,null,null,null);
  }

  onSubmit() {  }

}
