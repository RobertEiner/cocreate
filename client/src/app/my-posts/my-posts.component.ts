import { Component, inject, Input } from '@angular/core';
import { Post } from '../models/post';
import { CommonModule } from '@angular/common';
import { ProjectModalComponent } from '../project-modal/project-modal.component';
import { ProjectCardComponent } from '../project-card/project-card.component';


@Component({
  selector: 'app-my-posts',
  standalone: true,
  imports: [CommonModule, ProjectModalComponent, ProjectCardComponent],
  templateUrl: './my-posts.component.html',
  styleUrl: './my-posts.component.css'
})
export class MyPostsComponent {

  @Input() posts: Post[] = [];
  @Input() devId: number | null = 0;
  projectCardComponent: ProjectCardComponent = new ProjectCardComponent();

  displayPostModal: boolean = false; 
  // class variables
  post: Post = {
     title: '',
     content: '',
     developer: null,
     comments: []
  }


  populatePost(selectedPost: Post) {
    this.post = selectedPost;
    this.displayPostModal = true;
    
    console.log(this.post.developer);
    console.log(this.post.postId);
  }


onCommentUpdated(postId: number) {
  this.projectCardComponent.onCommentUpdated(postId);
}

onPostDeleted(postId: number) {
  this.projectCardComponent.onPostDeleted(postId);
}

onPostUpdated(postId: number) {
  this.projectCardComponent.onPostUpdated(postId)
}



}
