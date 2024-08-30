import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../../models/post';
import { baseUrl } from '../../environment/environment';


@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor() { }
  baseUrl = baseUrl;

  httpClient: HttpClient = inject(HttpClient);

  createPost(developerId: number, newPost: Post): Observable<Post> {
    return this.httpClient.post<Post>(`http://localhost:8080/api/v1/developers/${developerId}/posts`, newPost);
  }

  getAllPosts(): Observable<Post[]> { 
    // the httpClient.get method returns an observable by default
    // The http request is async, the observable that is returned will emit the fetched Post objects when the request is complete.
    // the observable doesn't start executing until the observer subscribes to it!
    return this.httpClient.get<Post[]>(`${baseUrl}/posts/`); 
  }

  getPostById(id: number): Observable<Post> {
    return this.httpClient.get<Post>(`${baseUrl}/posts/${id}`);
  }

}
