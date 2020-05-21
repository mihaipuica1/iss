import { NgModule } from '@angular/core';

import {RouterModule, Routes} from '@angular/router';
import { ProposalFormComponent } from '../proposal-form/proposal-form/proposal-form.component';
import { PaperFormComponent } from '../paper-form/paper-form/paper-form.component';
import {AuthorComponent} from '../speaker/author.component';


const routes: Routes = [
  {path: 'speaker', component: AuthorComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
  
})
export class AppRoutingModule { }
