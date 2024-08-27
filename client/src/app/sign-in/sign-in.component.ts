import { Component, inject } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css',
})
export class SignInComponent {
  router: Router = inject(Router); //inject allows us to use DI without constructor
  activeRoute: ActivatedRoute = inject(ActivatedRoute);

  navigateToDashboard() {
    // this.router.navigateByUrl('dashboard'); // navigateByURl uses an absolute path by default even if / is omitted. NavigatByUrl cannot use relative path
    this.router.navigate(['dashboard'], {relativeTo: this.activeRoute});  // also uses abs path by default, but can use relative poth if currently active route is explicitly mentioned with ActivatedRoute
  }

 
}
