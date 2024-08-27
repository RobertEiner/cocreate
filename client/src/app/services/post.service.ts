import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../models/post';


@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor() { }

  httpClient: HttpClient = inject(HttpClient);


  getAllPosts(): Observable<Post[]> { 
    // the httpClient.get method returns an observable by default
    // The http request is async, the observable that is returned will emit the fetched Post objects when the request is complete.
    // the observable doesn't start executing until the component subscribes to it!
    return this.httpClient.get<Post[]>('http://localhost:8080/api/v1/posts/'); 
  }

  getPostById(id: number) {

  }

}
