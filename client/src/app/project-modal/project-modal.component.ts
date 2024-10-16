import { Component, EventEmitter, inject, Input, OnInit, Output, ViewChild } from '@angular/core';
import { CommentService } from '../services/comment/comment.service';
import { CommentDTO } from '../models/comment-dto';
import { FormsModule, NgForm } from '@angular/forms';
import { Comment } from '../models/comment';
import { CommonModule } from '@angular/common';
import { PostService } from '../services/post/post.service';
import { EditTextboxComponent } from '../edit-textbox/edit-textbox.component';



@Component({
  selector: 'app-project-modal',
  standalone: true,
  imports: [FormsModule, CommonModule, EditTextboxComponent],
  templateUrl: './project-modal.component.html',
  styleUrl: './project-modal.component.css',
})

export class ProjectModalComponent implements OnInit {
  @ViewChild('commentForm') form: NgForm = new NgForm([], []);
  @ViewChild('editCommentForm') editCommentForm: NgForm = new NgForm([], []);
  // Inputs and Outputs
  @Input() postTitle: string = '';
  @Input() postDescription: string = '';
  @Input() postAuthor: string = '';
  @Input() postComments: Comment[] = []
  @Input() postId: number = 0;
  @Input() devId: number = 0;
  @Input() signedInUser: string = "";
  @Output() commentUpdated: EventEmitter<number> = new EventEmitter<number>();
  @Output() postDeleted: EventEmitter<number> = new EventEmitter<number>();

  // Class variables
  itemToEdit: string = "Post";
  commentService: CommentService = inject(CommentService);
  postService: PostService = inject(PostService);
  commentContent: string = '';

  updateComment: boolean = false;
  commentToEdit: number = 0;
  editCommentContent: string = "";

  deletePostPressed: boolean = false;
  editPostPressed: boolean = false;



  ngOnInit() {
  // TODO: här ska du inkludera JS för tooltip? 
  }

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
        this.commentUpdated.emit(this.postId);
      },
      error(err) {
        console.error(err);
      }
    })
  }

  // ------------------------------- Edit comment ------------------------

  editComment(commentId: number) {
    this.updateComment = true;
    this.commentToEdit = commentId;
  }

  editCommentInForm(commentId: number, ) {
    const commentDTO: CommentDTO = { 
      content: this.editCommentContent 
    }
    console.log(this.editCommentForm);
    console.log(commentId, this.editCommentContent)
    this.commentService.editComment(commentId, commentDTO).subscribe({
      // TODO: FIX error here
      next: () => {
        this.commentUpdated.emit(this.postId);
        this.commentToEdit = -1;
        this.editCommentContent = ""; 
      },
      error(err) {
        console.error(err);
      }
    })
  }

  cancelEditComment() {
    this.commentToEdit = -1;
    this.editCommentContent = "";
    console.log(this.signedInUser, " ", this.postAuthor)
  }

 
  deleteComment(commentId: number, postId: number) {
    this.commentService.deleteComment(commentId).subscribe({
      next: (response: Comment) => {
        this.commentUpdated.emit(postId);
      },
      error(err) {
        console.error(err);
      }
    })
  }

  // ----------------------------- Delete post ----------------------------

  deletePostModal(postId: number) {
    this.deletePostPressed = true;
  }

  cancelDeletePost() {
    this.deletePostPressed = false;
  }

  reallyDeletePost(postId: number) {
    this.postService.deletePostById(postId).subscribe({
      next: () => {
        console.log('deleted');
        this.postDeleted.emit(postId);
        this.deletePostPressed = false;
      },
      error(err) {
        console.log(err);
      }
    })
  }

  // ----------------------------- Edit post ----------------------------

  editPost(postId: number) {
    this.editPostPressed = true;
  }

  cancelEditPost() {
    this.editPostPressed = false;
  }

}
