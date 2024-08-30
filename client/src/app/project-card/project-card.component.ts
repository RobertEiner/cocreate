import { CommonModule } from '@angular/common';
import { Component, inject, Input, OnInit, ViewChild } from '@angular/core';
import { Post } from '../models/post';
import { Comment } from '../models/comment';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';
import { CommentService } from '../services/comment/comment.service';
import { CommentDTO } from '../models/comment-dto';
import { ProjectModalComponent } from '../project-modal/project-modal.component';
import { PostService } from '../services/post/post.service';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-project-card',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule, ProjectModalComponent],
  templateUrl: './project-card.component.html',
  styleUrl: './project-card.component.css'
})
export class ProjectCardComponent {

  @Input() posts: Post[] = [];
  @Input() devId: number = 0;
  @ViewChild('commentForm') form: NgForm = new NgForm([], []);

  // services
  commentService: CommentService = inject(CommentService);
  postService: PostService = inject(PostService);
  // routing
  router: Router = inject(Router);
  avtiveRoute: ActivatedRoute = inject(ActivatedRoute);
  // class variables
  selectedPostTitle: string = '';
  selectedPostContent: string = '';
  selectedPostAuthor: string = '';
  selectedPostComments: Comment[] = [];
  selectedPostId: number = 0;          // maybe shouldn't have default value??
  commentContent: string = '';
  

  openPostDetails(post: Post) {
    // this.router.navigate([`post/${post.postId}`],  {relativeTo: this.avtiveRoute});
    this.selectedPostTitle = post.title;
    this.selectedPostContent = post.content;
    this.selectedPostComments = post.comments;
    this.selectedPostAuthor = post.developer ? post.developer?.userName : 'Unknown'; // ternary operator. means we are checking if the post.developer is null, if it is we give it a default username 'Unknown'
    this.selectedPostId = post.postId ? post.postId : 0;
  }

  onCommentCreated(postId: number) {
      this.postService.getPostById(postId).subscribe({
        next: (response: Post) => {
          this.selectedPostComments = response.comments;
        },
        error(err) {
          console.error('This is ERROR:', err);
        }
      })
      // this.selectedPostComments = post.comments;
    
  }


    


}
