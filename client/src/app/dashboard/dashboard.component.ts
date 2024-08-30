import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Developer } from '../models/developer';
import { Post } from '../models/post';
import { ProjectCardComponent } from '../project-card/project-card.component';
import { PostService } from '../services/post/post.service';
import { DeveloperService } from '../services/developer/developer.service';
import { CreatePostComponent } from '../create-post/create-post.component';


@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterLink, CommonModule, ProjectCardComponent, CreatePostComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})

export class DashboardComponent implements OnInit {
  // navigation & http
  activeRoute: ActivatedRoute = inject(ActivatedRoute);
  httpClient: HttpClient = inject(HttpClient);
  // services
  postService: PostService = inject(PostService);
  developerService: DeveloperService = inject(DeveloperService);

  // class variables
  developerId: number = 0;
  userName: string = '';
  posts: Post[] = [];
  activeView: string = 'showPosts';


  ngOnInit(): void {
    const idParam: string | null = this.activeRoute.snapshot.paramMap.get('id');
    if(idParam !== null) {
      this.developerId = parseInt(idParam, 10); // tells parseInt we want a base 10 num, i.e. a decimal number. e.g. if the input would be 08, we get 8
      this.developerService.getDeveloperById(this.developerId).subscribe({
        next: (response: Developer) => {
          this.userName = response.userName;
        },
        error(err) {
          console.error('not found\n', err);
        }
      })
      
    } else {
      console.error('Could not get ID from path');
    }
  }

  createPost() {
    this.activeView = 'createPost'
  }

  getAllPosts() {
    this.activeView = 'showPosts'

    // when the button gets pressed, this subscribe method gets executed, and then the observable gets executed.
    this.postService.getAllPosts().subscribe({ //
      next: (response: Post[]) => {
        this.posts = response;
        // console.log(this.posts);
      },
      error(error) {
        console.error('Error fetching posts: ', error);
      },
    });
  }
}
