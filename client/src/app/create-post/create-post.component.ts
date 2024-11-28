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
  @Input() devId: number = 0;
  postService: PostService = inject(PostService);

  // Class variables
  title: string = '';
  content: string = '';
  showSuccessMessage: boolean = false;



  onFormSubmit() {
    const newPost: Post = new Post(this.title, this.content);
    this.postService.createPost(this.devId, newPost).subscribe({
      next: (response: Post) => {
        console.log(response);
        this.form.resetForm();
        this.showSuccessMessage = true;
        setTimeout(() => {
          this.showSuccessMessage = false;
        }, 3000)
      },
      error(err) {
        console.log(err);
      }
    })
  }




}
