import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment } from '../../models/comment';
import { baseUrl } from '../../environment/environment';
import { CommentDTO } from '../../models/comment-dto';



@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor() { }

  // baseUrl: string = baseUrl;
  httpClient: HttpClient = inject(HttpClient);


  createComment(postId: number, newComment: CommentDTO, developerId: number): Observable<Comment> {
    return this.httpClient.post<Comment>(`${baseUrl}/posts/${postId}/developers/${developerId}/comments`, newComment);
  }

  deleteComment(commentId: number) {
    return this.httpClient.delete<Comment>(`${baseUrl}/comments/${commentId}`);
  }

  editComment(commentId: number, newContent: CommentDTO) {
    console.log('SERVICE')
    return this.httpClient.patch(`${baseUrl}/comments/${commentId}`, newContent);
  }

 

  


}
