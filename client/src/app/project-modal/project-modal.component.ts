import { Component, EventEmitter, inject, Input, Output, ViewChild } from '@angular/core';
import { CommentService } from '../services/comment/comment.service';
import { CommentDTO } from '../models/comment-dto';
import { FormsModule, NgForm } from '@angular/forms';
import { Comment } from '../models/comment';
import { CommonModule } from '@angular/common';




@Component({
  selector: 'app-project-modal',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './project-modal.component.html',
  styleUrl: './project-modal.component.css'
})
export class ProjectModalComponent {
  @ViewChild('commentForm') form: NgForm = new NgForm([], []);
  // Inputs and Outputs
  @Input() postTitle: string = '';
  @Input() postDescription: string = '';
  @Input() postAuthor: string = '';
  @Input() postComments: Comment[] = []
  @Input() postId: number = 0;
  @Input() devId: number = 0;
  @Input() signedInUser: string = "";
  @Output() commentCreated: EventEmitter<number> = new EventEmitter<number>();

  // Class variables
  commentService: CommentService = inject(CommentService)
  commentContent: string = '';

  
  createComment() {
    const commentDTO: CommentDTO = { 
      content: this.commentContent 
    }
    
    this.commentService.createComment(this.postId, commentDTO, this.devId).subscribe({
      next: (response: Comment) => {
        console.log(response.content);
        // clear the textarea
        this.form.reset();
        // emit to parent that a comment has been created
        this.commentCreated.emit(this.postId);
      },
      error(err) {
        console.error(err);
      }
    })
  }

}
