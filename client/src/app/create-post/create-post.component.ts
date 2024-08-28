import { CommonModule } from '@angular/common';
import { Component, inject, Input, ViewChild } from '@angular/core';
import { FormsModule, NgForm, NgModel } from '@angular/forms';
import { PostService } from '../services/post/post.service';
import { Post } from '../models/post';



@Component({
  selector: 'app-create-post',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './create-post.component.html',
  styleUrl: './create-post.component.css'
})
export class CreatePostComponent {

  @ViewChild('createPostForm') form: NgForm = new NgForm([], []);
  postService: PostService = inject(PostService);
  @Input() devId: number = 0;

  // Class variables
  title: string = '';
  content: string = '';



  onFormSubmit() {
    console.log(this.title, " ", this.content)
    // const newPost: Post = new Post(this.title, this.content);
    // this.postService.createPost(newPost)
    console.log(this.devId)
  }




}
