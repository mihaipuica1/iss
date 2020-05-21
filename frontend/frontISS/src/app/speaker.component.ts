import { Component } from '@angular/core';

@Component({
  selector: 'app-speaker-root',
  templateUrl: './speaker.component.html',
  styleUrls: ['./speaker.component.css']
})
export class SpeakerComponent {
  title: string;
  constructor() {
    this.title = 'Speaker/Author';
  }
}
