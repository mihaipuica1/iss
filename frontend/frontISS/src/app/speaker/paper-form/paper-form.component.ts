import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { Paper } from '../../models/paper';

@Component({
  selector: 'app-paper-form',
  templateUrl: './paper-form.component.html',
  styleUrls: ['./paper-form.component.css']
})

export class PaperFormComponent {
  paper: Paper;
  constructor(private route: ActivatedRoute,
              private router: Router,
             ) {
    this.paper = new Paper('1', 'a', 'a');
             }

  onSubmit() {

  }
}
