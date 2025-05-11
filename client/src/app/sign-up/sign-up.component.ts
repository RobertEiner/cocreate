import { CommonModule } from '@angular/common';
import { Component, inject, Output, ViewChild, EventEmitter } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Developer } from '../models/developer';
import { DeveloperService } from '../services/developer/developer.service';
import { Router } from '@angular/router';
import { LandingNavbarComponent } from '../landing-navbar/landing-navbar.component';



@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [FormsModule, CommonModule, LandingNavbarComponent],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {

  router: Router = inject(Router);
  title = 'Sign up';
  email: string = '';
  userName: string = '';
  password: string = '';
  preferredLanguage: string = '';

  developerService: DeveloperService = inject(DeveloperService);
  @ViewChild('registrationForm') form: NgForm = new NgForm([], []);
  @Output() renderSignInEmitter: EventEmitter<string> = new EventEmitter<string>();

  onFormSubmitted() {
    // console.log(this.form.controls['userName'].value);
    // console.log(this.form);
    this.email = this.form.value.email;
    this.userName = this.form.value.userName;
    this.password = this.form.value.password;
    this.preferredLanguage = this.form.value.prefLang;
    const newDev: Developer = new Developer(this.userName, this.password, this.email, this.preferredLanguage);
    
    this.developerService.createDeveloper(newDev).subscribe({
      next: (response: Developer) => {
        console.log(response.userName)
        this.router.navigate([`developer/${response.id}/dashboard`])
      },
      error(err) {
        console.error("couldn't create dev: ", err);
        // handle errors when creating account with bad data
      }
    });


  }

  resetForm() {
    this.form.reset();
  }

  // renderSignIn() {
  //   this.renderSignInEmitter.emit('sign-in');
  // }







}
