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
import { NavbarComponent } from '../navbar/navbar.component';
import { Util } from '../../util/util';
import { FooterComponent } from "../footer/footer.component";


@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterLink, CommonModule, ProjectCardComponent, CreatePostComponent, NavbarComponent, FooterComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})

export class DashboardComponent implements OnInit {
  // navigation & http
  activeRoute: ActivatedRoute = inject(ActivatedRoute);
  httpClient: HttpClient = inject(HttpClient);
  util: Util = new Util();


  // services
  postService: PostService = inject(PostService);
  developerService: DeveloperService = inject(DeveloperService);

  // class variables
  developerId: number = 0;
  userName: string = '';
  posts: Post[] = [];
  activeView: string = 'showPosts';


  ngOnInit(): void {
    const idParam: string | null = this.util.getDevIdFromUrl();
    if(idParam !== null) {
      this.developerId = parseInt(idParam, 10); // tells parseInt we want a base 10 num, i.e. a decimal number. e.g. if the input would be 08, we get 8
      this.developerService.getDeveloperById(this.developerId).subscribe({
        next: (response: Developer) => {
          this.userName = response.userName;
          this.getAllPosts();
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
      },
      error(error) {
        console.error('Error fetching posts: ', error);
      },
    });
  }
}
