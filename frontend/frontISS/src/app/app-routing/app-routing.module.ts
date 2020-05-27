import { NgModule, Component } from '@angular/core';

import {RouterModule, Routes} from '@angular/router';
import { ProposalFormComponent } from '../speaker/proposal-form/proposal-form.component';

import {AuthorComponent} from '../speaker/author.component';
import {ConferenceChairComponent} from '../conference-chair/conference-chair.component';
import {ReviewerComponent} from '../reviewer/reviewer.component';
import { AppComponent } from '../app.component';
import {MySubmissionsComponent} from '../speaker/my-submissions/my-submissions.component';
import {GiveQualifierComponent} from '../reviewer/give-qualifier/give-qualifier.component';
import {BidProposalComponent} from '../reviewer/bid-proposal/bid-proposal.component';
import {ViewOtherProposalsComponent} from '../reviewer/view-other-proposals/view-other-proposals.component';
import {AddConferenceComponent} from '../conference-chair/add-conference/add-conference.component';
import {AssignPapersComponent} from '../conference-chair/assign-papers/assign-papers.component';
import {ViewConferencesComponent} from '../conference-chair/view-conferences/view-conferences.component';
import { HomePageComponent } from '../home-page/home-page.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {
    path: 'giveQualifier', component: GiveQualifierComponent
  },
  {
    path: 'home', component: HomePageComponent
  },
  {
    path: 'bidProposal', component: BidProposalComponent
  },
  {
    path: 'viewOtherProposals', component: ViewOtherProposalsComponent
  },
{
  path: 'submitProposal', component: ProposalFormComponent
},
{
  path: 'mysubmissions', component: MySubmissionsComponent
},
  {path: 'speaker', component: AuthorComponent , children:[

    {
      path: 'submitProposal', component: ProposalFormComponent
    },

    {
      path: 'mysubmissions', component: MySubmissionsComponent
    }
  ]

  },
  {
    path: 'reviewer', component: ReviewerComponent, children: [
      {
        path: 'giveQualifier', component: GiveQualifierComponent
      },

      {
        path: 'bidProposal', component: BidProposalComponent
      },
      {
        path: 'viewOtherProposals', component: ViewOtherProposalsComponent
      }
    ]
  },
  {
    path: 'conferenceChair', component: ConferenceChairComponent, children: [
      {
        path: 'addConference', component: AddConferenceComponent
      },

      {
        path: 'assignPapers', component: AssignPapersComponent
      },
      {
        path: 'viewConferences', component: ViewConferencesComponent
      }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]

})
export class AppRoutingModule { 
}
