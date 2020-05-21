import { NgModule } from '@angular/core';

import {RouterModule, Routes} from '@angular/router';
import { ProposalFormComponent } from '../proposal-form/proposal-form/proposal-form.component';
import { PaperFormComponent } from '../paper-form/paper-form/paper-form.component';


const routes: Routes = [
  {path: 'submitProposal', component: ProposalFormComponent },
  {path: 'uploadFullPaper', component: PaperFormComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class SpeakerRoutingModule { }