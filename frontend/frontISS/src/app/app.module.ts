import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing/app-routing.module';
import {HttpClientModule} from '@angular/common/http';


import { AppComponent } from './app.component';
import { ProposalFormComponent } from './speaker/proposal-form/proposal-form.component';
import {AuthorComponent} from './speaker/author.component';
import { ConferenceChairComponent } from './conference-chair/conference-chair.component';
import { ReviewerComponent } from './reviewer/reviewer.component';
import { MySubmissionsComponent } from './speaker/my-submissions/my-submissions.component';
import { BidProposalComponent } from './reviewer/bid-proposal/bid-proposal.component';
import { GiveQualifierComponent } from './reviewer/give-qualifier/give-qualifier.component';
import { AddConferenceComponent } from './conference-chair/add-conference/add-conference.component';
import { ViewConferencesComponent } from './conference-chair/view-conferences/view-conferences.component';
import { AssignPapersComponent } from './conference-chair/assign-papers/assign-papers.component';
import { AuthService } from './AuthentificatrionService/auth.service';
import { HomePageComponent } from './home-page/home-page.component';
import { AuthGuardService } from './Guards/auth-guard.service';
import { SectionComponent } from './conference-chair/section/section.component';
import { AssignSectionComponent } from './conference-chair/assign-section/assign-section.component';
import { AssignSupervisorComponent } from './conference-chair/assign-supervisor/assign-supervisor.component';
import { SpeakerGuardService } from './Guards/speaker-guard.service';
import { ChairGuardService } from './Guards/chair-guard.service';
import { SpeakerServiceService } from './Shared/speaker-service.service';



@NgModule({
  declarations: [
    AppComponent,
    ProposalFormComponent,
    AuthorComponent,
    ConferenceChairComponent,
    ReviewerComponent,
    MySubmissionsComponent,
    BidProposalComponent,
    GiveQualifierComponent,
    AddConferenceComponent,
    ViewConferencesComponent,
    AssignPapersComponent,
    HomePageComponent,
    SectionComponent,
    AssignSectionComponent,
    AssignSupervisorComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    
  ],
  providers: [AuthService, AuthGuardService, SpeakerGuardService, ChairGuardService, SpeakerServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
