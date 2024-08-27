import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Developer } from '../models/developer';
import { Post } from '../models/post';
import { ProjectCardComponent } from '../project-card/project-card.component';
import { PostDetailComponent } from '../post-detail/post-detail.component';
import { PostService } from '../services/post.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterLink, CommonModule, ProjectCardComponent, PostDetailComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent {
  httpClient: HttpClient = inject(HttpClient);
  postService: PostService = inject(PostService);

  developers: Developer[] = [];
  posts: Post[] = [];



  getAllDevelopers() {
    // this.developers = this.dashboardService.getAllDevelopers();
  }

  getAllPosts() {
    // when the button gets pressed, this subscribe method gets executed, and then the observable gets executed.
    this.postService.getAllPosts().subscribe({ //
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
