import { NgModule } from '@angular/core';

import {RouterModule, Routes} from '@angular/router';
import { ProposalFormComponent } from '../proposal-form/proposal-form/proposal-form.component';
import { PaperFormComponent } from '../paper-form/paper-form/paper-form.component';
import {SpeakerAppRoutingModule} from './speaker-app-routing.module';
import {SpeakerComponent} from '../speaker.component';

const routes: Routes = [
  {path: 'speaker', component: SpeakerComponent },

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
