import { CommonModule } from '@angular/common';
import { Component, inject, Input, ViewChild, Output, EventEmitter } from '@angular/core';
import { Post } from '../models/post';
import { Comment } from '../models/comment';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';
import { CommentService } from '../services/comment/comment.service';
import { ProjectModalComponent } from '../project-modal/project-modal.component';
import { PostService } from '../services/post/post.service';
import { Util } from '../../util/util';


@Component({
  selector: 'app-project-card',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule, ProjectModalComponent],
  templateUrl: './project-card.component.html',
  styleUrl: './project-card.component.css'
})

export class ProjectCardComponent {

  @ViewChild('commentForm') form: NgForm = new NgForm([], []);
  @Input() posts: Post[] = [];
  @Input() devId: number = 0;
  @Input() devUserName: string = "";
  @Output() postUpdated: EventEmitter<string> = new EventEmitter<string>();
  @Output() postDeleted: EventEmitter<number> = new EventEmitter<number>();
  util: Util = new Util();

  // services
  commentService: CommentService = inject(CommentService);
  postService: PostService = inject(PostService);
  // routing
  router: Router = inject(Router);
  avtiveRoute: ActivatedRoute = inject(ActivatedRoute);
  // class variables
  // createdAt: Date = new Date();
  // selectedPostTitle: string = '';
  // selectedPostContent: string = '';
  // selectedPostDevCategory: string = '';
  // selectedPostAuthor: string = '';
  // selectedPostComments: Comment[] = [];
  // selectedPostId: number = 0;          // maybe shouldn't have default value??
  commentContent: string = '';
  currentPost: Post = new Post('', '', '');


  openPostDetails(post: Post) {
    this.currentPost = post;
    // this.router.navigate([`post/${post.postId}`],  {relativeTo: this.avtiveRoute});
    // this.selectedPostTitle = post.title;
    // this.selectedPostContent = post.content;
    // this.selectedPostDevCategory = post.devCategory;
    // this.selectedPostComments = post.comments;
    // this.selectedPostAuthor = post.developer ? post.developer?.userName : 'Unknown'; // ternary operator. means we are checking if the post.developer is null, if it is we give it a default username 'Unknown'
    // this.selectedPostId = post.postId ? post.postId : 0;
    // console.log(post);
    const date = post.createdAt ? new Date(post.createdAt) : null;
    console.log(date); 

  }

  onPostDeleted(postId: number) {
    this.postDeleted.emit(postId);
  }

  onCommentUpdated(postId: number) {
    this.postService.getPostById(postId).subscribe({
      next: (response: Post) => {
        this.currentPost.comments = response.comments;
        this.postUpdated.emit('comments updated');

      },
      error(err) {
        console.error('Error updating comments:', err);
      }
    })    
  }

  onPostUpdated(postId: number) {
    this.postService.getPostById(postId).subscribe({
      next: (response: Post) => {
        this.currentPost.content = response.content;
        this.postUpdated.emit("post updated")
      },
      error(err) {
        console.error('Error updating comments:', err);
      }
    })    

  }


}
