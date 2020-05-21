import { NgModule, Component } from '@angular/core';

import {RouterModule, Routes} from '@angular/router';
import { ProposalFormComponent } from '../speaker/proposal-form/proposal-form.component';
import { PaperFormComponent } from '../speaker/paper-form/paper-form.component';
import {AuthorComponent} from '../speaker/author.component';


const routes: Routes = [
  {path: 'speaker', component: AuthorComponent , children:[

    {
      path: 'submitProposal', component: ProposalFormComponent
    },
  
    { 
      path: 'uploadFullPaper', component: PaperFormComponent 
    }
  ]

  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
  
})
export class AppRoutingModule { }
