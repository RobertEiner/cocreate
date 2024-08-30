import { Component, inject } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';


@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [RouterLink, FormsModule],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css',
})
export class SignInComponent {
  router: Router = inject(Router); //inject allows us to use DI without constructor
  activeRoute: ActivatedRoute = inject(ActivatedRoute);


  emailAddress: string = '';
  password: string = '' ;

  navigateToDashboard() {
    // this.router.navigateByUrl('dashboard'); // navigateByURl uses an absolute path by default even if / is omitted. NavigatByUrl cannot use relative path
    // this.router.navigate(['developer/:id/dashboard'], {relativeTo: this.activeRoute});  // also uses abs path by default, but can use relative poth if currently active route is explicitly mentioned with ActivatedRoute
    console.log(this.emailAddress, this.password);
    

 
  }

 
}
