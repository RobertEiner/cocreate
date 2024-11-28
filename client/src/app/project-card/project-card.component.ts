import { CommonModule } from '@angular/common';
import { Component, inject, Input, OnInit, ViewChild, Output, EventEmitter, OnChanges, SimpleChanges, AfterViewInit, AfterViewChecked } from '@angular/core';
import { Post } from '../models/post';
import { Comment } from '../models/comment';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';
import { CommentService } from '../services/comment/comment.service';
import { ProjectModalComponent } from '../project-modal/project-modal.component';
import { PostService } from '../services/post/post.service';


@Component({
  selector: 'app-project-card',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule, ProjectModalComponent],
  templateUrl: './project-card.component.html',
  styleUrl: './project-card.component.css'
})

export class ProjectCardComponent implements OnInit {

  @ViewChild('commentForm') form: NgForm = new NgForm([], []);
  @Input() posts: Post[] = [];
  @Input() devId: number = 0;
  @Input() devUserName: string = "";
  @Output() commentsUpdated: EventEmitter<string> = new EventEmitter<string>();
  @Output() postDeleted: EventEmitter<number> = new EventEmitter<number>();

  // services
  commentService: CommentService = inject(CommentService);
  postService: PostService = inject(PostService);
  // routing
  router: Router = inject(Router);
  avtiveRoute: ActivatedRoute = inject(ActivatedRoute);
  // class variables
  createdAt: Date = new Date();
  selectedPostTitle: string = '';
  selectedPostContent: string = '';
  selectedPostAuthor: string = '';
  selectedPostComments: Comment[] = [];
  selectedPostId: number = 0;          // maybe shouldn't have default value??
  commentContent: string = '';

  
  ngOnInit(): void {
    // console.log(this.posts); 
  }

  // ngOnChanges(changes: SimpleChanges): void {
  //  if(changes['posts']) {
  //   console.log(this.posts)
  //   for(const post in this.posts) {
  //     //  const date = this.posts[post].createdAt ? new Date(this.posts[post].createdAt) : null;
  //     // console.log(date)
  //     // this.posts[post].createdAt = date?.getFullYear() + '-' + ((date?.getMonth() ?? 0) + 1) +  '-' + date?.getDate()
  //   }
  //  } 
   
  // }

  convertDate(post: Post): string {
    const date = post.createdAt ? new Date(post.createdAt) : null;
    return date?.getFullYear() + '-' + date?.getMonth() + '-' + date?.getDate();
  }

  openPostDetails(post: Post) {
    // this.router.navigate([`post/${post.postId}`],  {relativeTo: this.avtiveRoute});
    this.selectedPostTitle = post.title;
    this.selectedPostContent = post.content;
    this.selectedPostComments = post.comments;
    this.selectedPostAuthor = post.developer ? post.developer?.userName : 'Unknown'; // ternary operator. means we are checking if the post.developer is null, if it is we give it a default username 'Unknown'
    this.selectedPostId = post.postId ? post.postId : 0;
    // console.log(post);
    const date = post.createdAt ? new Date(post.createdAt) : null;
    console.log(date); //TODO: figure out what is wrong here!

  }

  onPostDeleted(postId: number) {
    this.postDeleted.emit(postId);
  }

  onCommentUpdated(postId: number) {
    this.postService.getPostById(postId).subscribe({
      next: (response: Post) => {
        this.selectedPostComments = response.comments;
        this.commentsUpdated.emit('comments updated');

      },
      error(err) {
        console.error('Error updating comments:', err);
      }
    })    
  }

  onPostUpdated(postId: number) {
    this.postService.getPostById(postId).subscribe({
      next: (response: Post) => {
        this.selectedPostContent = response.content;
      },
      error(err) {
        console.error('Error updating comments:', err);
      }
    })    

  }


}
