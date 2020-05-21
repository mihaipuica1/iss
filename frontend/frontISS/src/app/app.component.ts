import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title: string;
  @ViewChild('myModal') myModal:ElementRef;
  constructor() {
    this.title = 'Users';
  }

  ngOnInit(){
    
      this.myModal.nativeElement.click();

  }
}
