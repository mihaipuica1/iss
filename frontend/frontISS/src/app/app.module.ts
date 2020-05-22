import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing/app-routing.module';
import {HttpClientModule} from '@angular/common/http';


import { AppComponent } from './app.component';
import { PaperFormComponent } from './speaker/paper-form/paper-form.component';
import { ProposalFormComponent } from './speaker/proposal-form/proposal-form.component';
import {AuthorComponent} from './speaker/author.component';
import { ConferenceChairComponent } from './conference-chair/conference-chair.component';
import { ReviewerComponent } from './reviewer/reviewer.component';



@NgModule({
  declarations: [
    AppComponent,
    PaperFormComponent,
    ProposalFormComponent,
    AuthorComponent,
    ConferenceChairComponent,
    ReviewerComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
