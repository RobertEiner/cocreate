import { CommonModule } from '@angular/common';
import { Component, inject, Input, Output, ViewChild, EventEmitter } from '@angular/core';
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
  @Output() postCreated: EventEmitter<string> = new EventEmitter<string>();
  postService: PostService = inject(PostService);

  // Class variables
  title: string = '';
  content: string = '';
  devCategory: string = '';
  showSuccessMessage: boolean = false;

  onFormSubmit() {
    const newPost: Post = new Post(this.title, this.content, this.devCategory);
    console.log(newPost)
    this.postService.createPost(this.devId, newPost).subscribe({
      next: (response: Post) => { 
        console.log(response);
        this.form.resetForm();        
        if (typeof window !== 'undefined') {
        import('bootstrap').then(({ Toast }) => {
        const toastSuccess = document.getElementById('successToast');
        if (toastSuccess) {
          const toast = Toast.getOrCreateInstance(toastSuccess);
          toast.show();
        }
        this.postCreated.emit('created');
      });
      }
     },
    error(err) {
       console.log(err);
    }
    })
  }

  resetForm() {
    this.form.resetForm();
  }




}
