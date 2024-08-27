import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Developer } from '../models/developer';
import { DashboardService } from '../services/dashboard.service';
import { Post } from '../models/post';
import { ProjectCardComponent } from '../project-card/project-card.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterLink, CommonModule, ProjectCardComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent {
  httpClient: HttpClient = inject(HttpClient);
  dashboardService: DashboardService = inject(DashboardService);

  developers: Developer[] = [];
  posts: Post[] = [];

  getAllDevelopers() {
    this.developers = this.dashboardService.getAllDevelopers();
  }

  getAllPosts() {
    // when the button gets pressed, this subscribe method gets executed, and then the observable gets executed.
    this.dashboardService.getAllPosts().subscribe({ //
      next: (response: Post[]) => {
        this.posts = response;
        console.log(this.posts);
      },
      error(error) {
        console.error('Error fetching posts: ', error);
      },
    });
  }
}
