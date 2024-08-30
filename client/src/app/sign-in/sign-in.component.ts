import { Component, inject, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { DeveloperService } from '../services/developer/developer.service';
import { Developer } from '../models/developer';
import { CommonModule } from '@angular/common';



@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css',
})
export class SignInComponent {

  @ViewChild('signInForm') form: NgForm = new NgForm([], []);
  // Routing
  router: Router = inject(Router); //inject allows us to use DI without constructor
  activeRoute: ActivatedRoute = inject(ActivatedRoute);
  // Services
  developerService: DeveloperService = inject(DeveloperService);


  userName: string = '';
  password: string = '' ;

  invalidPassword: boolean = false;
  invalidUserName: boolean = false;

  navigateToDashboard() {
    this.invalidPassword = false;
    this.invalidUserName = false;
    // this.router.navigateByUrl('dashboard'); // navigateByURl uses an absolute path by default even if / is omitted. NavigatByUrl cannot use relative path
    console.log(this.userName, this.password);
    this.developerService.checkDeveloperCredentials(this.userName, this.password).subscribe({
      next: (response: Developer) => {
        console.log(response.id);
        this.router.navigate([`developer/${response.id}/dashboard`], {relativeTo: this.activeRoute});  // also uses abs path by default, but can use relative poth if currently active route is explicitly mentioned with ActivatedRoute
      },
      error: (err) => { // When we use arrow function here, it means that the this keyword is referring to the class, and not the scoped response (Developer) object
        if(err.status === 404) {
          this.invalidUserName = true;
          this.form.resetForm();

        } else if(err.status === 401) {
          console.log('invalidnpas')
          this.invalidPassword = true;
          this.form.reset();
          console.log('AFTER: ', this.form)

        }
        console.error(err.status);

      }
    });
  }

}
