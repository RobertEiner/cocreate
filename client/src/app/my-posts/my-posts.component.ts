import { Component, inject, Input, OnInit } from '@angular/core';
import { Post } from '../models/post';
import { CommonModule } from '@angular/common';
import { ProjectModalComponent } from '../project-modal/project-modal.component';
import { ProjectCardComponent } from '../project-card/project-card.component';
import { PostService } from '../services/post/post.service';
import { PostModalComponent } from '../post-modal/post-modal.component';
import { Util } from '../../util/util';




@Component({
  selector: 'app-my-posts',
  standalone: true,
  imports: [CommonModule, ProjectModalComponent, ProjectCardComponent, PostModalComponent],
  templateUrl: './my-posts.component.html',
  styleUrl: './my-posts.component.css'
})
export class MyPostsComponent implements OnInit {

  util: Util = new Util();
  @Input() posts: Post[] = [];
  @Input() devId: number | null = 0;
  projectCardComponent: ProjectCardComponent = new ProjectCardComponent();

  postService: PostService = inject(PostService);

  displayPostModal: boolean = false; 
  // class variables
  post: Post = {
     title: '',
     content: '',
     developer: null,
     comments: []
  }

  ngOnInit(): void {
    console.log('len here ' + this.posts?.length);
  }
  populatePost(selectedPost: Post) {
    this.post = selectedPost;
    this.displayPostModal = true;
  }


  onCommentUpdated(postId: number) {
    this.postService.getPostById(postId).subscribe({
      next: (response: Post) => {
        console.log('RESPONSE' + response)
        this.post.comments = response.comments;
        // this.postUpdated.emit('comments updated');

      },
      error(err) {
        console.error('Error updating comments:', err);
      }
    })    
  }

  onPostDeleted(postId: number) {
    this.projectCardComponent.onPostDeleted(postId);
  }

  onPostUpdated(postId: number) {
    this.projectCardComponent.onPostUpdated(postId)
  }



}
