import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing/app-routing.module';
import {HttpClientModule} from '@angular/common/http';


import { AppComponent } from './app.component';
import { PaperFormComponent } from './paper-form/paper-form/paper-form.component';
import { ProposalFormComponent } from './proposal-form/proposal-form/proposal-form.component';
import {SpeakerAppRoutingModule} from './app-routing/speaker-app-routing.module';
import {SpeakerComponent} from './speaker.component';


@NgModule({
  declarations: [
    AppComponent,
    PaperFormComponent,
    ProposalFormComponent,
    SpeakerComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    SpeakerAppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
