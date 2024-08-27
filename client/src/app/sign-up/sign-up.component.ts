import { Component, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';


@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {

  title = 'Sign up';
  email: string = '';
  userName: string = '';
  password: string = '';
  preferredLanguage: string = '';
  @ViewChild('registrationForm') form: NgForm = new NgForm([], []);

  onFormSubmitted() {
    // console.log(this.form.controls['userName'].value);
    console.log(this.form);
  }





}
