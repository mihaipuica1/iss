import { NgModule, Component } from '@angular/core';

import {RouterModule, Routes} from '@angular/router';
import { ProposalFormComponent } from '../speaker/proposal-form/proposal-form.component';
import { PaperFormComponent } from '../speaker/paper-form/paper-form.component';
import {AuthorComponent} from '../speaker/author.component';
import {ConferenceChairComponent} from '../conference-chair/conference-chair.component';
import {ReviewerComponent} from '../reviewer/reviewer.component';


const routes: Routes = [
  {path: 'speaker', component: AuthorComponent , children:[

    {
      path: 'submitProposal', component: ProposalFormComponent
    },

    {
      path: 'uploadFullPaper', component: PaperFormComponent
    }
  ]

  },
  {
    path: 'reviewer', component: ReviewerComponent
  },
  {
    path: 'conferenceChair', component: ConferenceChairComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]

})
export class AppRoutingModule { }
