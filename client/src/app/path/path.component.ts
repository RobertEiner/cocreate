import { Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-path',
  standalone: true,
  imports: [],
  templateUrl: './path.component.html',
  styleUrl: './path.component.css'
})
export class PathComponent {

  activeRoute: ActivatedRoute = inject(ActivatedRoute);
  router: Router = inject(Router);

  navigateTo() {
    this.router.navigate(['path-two'], {relativeTo: this.activeRoute});
  }
}
