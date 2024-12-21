import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import  { Router } from '@angular/router'



@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {
  title = 'CoCreate'
  profileDropdown: string = 'My CoCreate'

  activeRoute: ActivatedRoute = inject(ActivatedRoute);
  router: Router = inject(Router);


  ngOnInit(): void {
    
  }

  navigate(location: string): void {
    if(location === 'profile') {
      this.router.navigate(['../profile'], {relativeTo: this.activeRoute})
    }
    if(location === 'dashboard') {
      this.router.navigate(['../dashboard'], {relativeTo: this.activeRoute})

    }

  }


}
