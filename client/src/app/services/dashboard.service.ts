import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Developer } from '../models/developer';
import { Post } from '../models/post';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  constructor() {}
  httpClient: HttpClient = inject(HttpClient);
  developers: Developer[] = [];
  // posts: Post[] = [];

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
  getAllPosts(): Observable<Post[]> { 
    // the httpClient.get method returns an observable by default
    // The http request is async, the observable that is returned will emit the fetched Post objects when the request is complete.
    // the observable doesn't start executing until the component subscribes to it!
    return this.httpClient.get<Post[]>('http://localhost:8080/api/v1/posts/'); 
  }
}
