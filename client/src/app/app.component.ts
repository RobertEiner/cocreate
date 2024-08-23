import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'CoCreate';
  developers: {userName: string, email: string, prefLang: string, posts: object[]}[] = []

  getDevs() {
    fetch('http://localhost:8080/api/v1/developers/')
     .then(res => res.json())
     .then(data => {
       this.developers = data;
     console.log("devs:",this.developers);
     });
  }
}
