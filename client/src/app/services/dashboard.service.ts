import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Developer } from '../models/developer';
import { Post } from '../models/post';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  constructor() {}
  httpClient: HttpClient = inject(HttpClient);
  developers: Developer[] = [];
  posts: Post[] = [];

  getAllDevelopers(): Developer[] {
    this.httpClient
      .get<Developer[]>('http://localhost:8080/api/v1/developers/')
      .subscribe((response) => {
        // console.log(response);
        this.developers = response;
        console.log(this.developers);
      });
    return this.developers;
  }

  // Get all posts
  getAllPosts(): Post[] { // change to Observable???
    this.httpClient
      .get<Post[]>('http://localhost:8080/api/v1/posts/')
      .subscribe((response) => {
        this.posts = response;
        console.log('response', response);
      });
    return this.posts;
  }
}
