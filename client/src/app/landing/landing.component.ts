import { Component } from '@angular/core';
import { LandingNavbarComponent } from "../landing-navbar/landing-navbar.component";
import { SignInComponent } from '../sign-in/sign-in.component';
import { SignUpComponent } from '../sign-up/sign-up.component';
import { CommonModule } from '@angular/common';
import { FooterComponent } from '../footer/footer.component';



@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [CommonModule, LandingNavbarComponent, SignInComponent, SignUpComponent, FooterComponent],
  templateUrl: './landing.component.html',
  styleUrl: './landing.component.css'
})
export class LandingComponent {
  renderSignUp: boolean = false;
  renderSignIn: boolean = true;

  

}
