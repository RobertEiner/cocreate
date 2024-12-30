import { Component, EventEmitter, inject, Input, OnInit, Output, ViewChild } from '@angular/core';
import { CommentService } from '../services/comment/comment.service';
import { CommentDTO } from '../models/comment-dto';
import { FormsModule, NgForm } from '@angular/forms';
import { Comment } from '../models/comment';
import { CommonModule } from '@angular/common';
import { PostService } from '../services/post/post.service';
import { EditTextboxComponent } from '../edit-textbox/edit-textbox.component';
import { TextToEdit} from '../interfaces/textToEdit';
import { Post } from '../models/post';
import { Util } from '../../util/util';


@Component({
  selector: 'app-project-modal',
  standalone: true,
  imports: [FormsModule, CommonModule, EditTextboxComponent],
  templateUrl: './project-modal.component.html',
  styleUrl: './project-modal.component.css',
})

export class ProjectModalComponent {
  @ViewChild('commentForm') form: NgForm = new NgForm([], []);
  @ViewChild('editCommentForm') editCommentForm: NgForm = new NgForm([], []);
  util: Util = new Util();
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
  @Output() postContentUpdated: EventEmitter<number> = new EventEmitter<number>();

  // Class variables
  itemToEdit: string = "Post";
  commentService: CommentService = inject(CommentService);
  postService: PostService = inject(PostService);
  commentContent: string = '';

  editTextContent = '';

  commentToEdit: number = 0;
  // editCommentContent: string = "";

  deletePostPressed: boolean = false;
  editPostPressed: boolean = false;


  // ------------------------------- Create comment ------------------------
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
        console.log('HÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄR')
        this.commentUpdated.emit(this.postId);
      },
      error(err) {
        console.error(err);
      }
    })
  }
  
  // ------------------------------- Convert date ------------------------
  // convertDate(comment: Comment): string {
  //   const date = comment.createdAt ? new Date(comment.createdAt) : null;
  //   return date?.getFullYear() + '-' + date?.getMonth() + '-' + date?.getDate();
    
  // }
  
  // ------------------------------- EDIT TEXT ------------------------
  editText(textToEdit: TextToEdit) {
    if(textToEdit.type === 'post') {
      this.editPost(textToEdit);
    } 
    if(textToEdit.type === 'comment') {
      this.editComment(textToEdit);
    }
  }
  
  cancelEditText(itemToEdit: string) {
    if(itemToEdit === 'post') {
      this.editPostPressed = false;
    }
    if(itemToEdit === 'comment') {
      this.commentToEdit = -1;
    }
  }
  
  // ----------------------------- Edit post ----------------------------
  
  displayEditPostTextbox(postId: number, currentPostContent: string) {
    this.editPostPressed = true;
    this.itemToEdit = 'post';
    this.editTextContent = currentPostContent;
  }

  editPost(postContent: TextToEdit) {
    const editedPost: Post = {
      title: this.postTitle,
      content: postContent.newContent,
      developer: null,
      comments: []
    }
    this.postService.editPostById(this.postId, editedPost).subscribe({
      next: () => {
        this.editPostPressed = false;
        this.postContentUpdated.emit(this.postId);
      },
      error(err) {
        console.error(err);
      }
    })
  }
  
  deletePostModal(postId: number) {
    this.deletePostPressed = true;
  }

  cancelDeletePost() {
    this.deletePostPressed = false;
  }

  reallyDeletePost(postId: number) {
    this.postService.deletePostById(postId).subscribe({
      next: () => {
        this.postDeleted.emit(postId);
        this.deletePostPressed = false;
      },
      error(err) {
        console.log(err);
      }
    })
  }

  // ------------------------------- Edit comment ------------------------
  displayEditCommentTextBox(commentId: number, currentCommentContent: string) {
    this.commentToEdit = commentId;
    this.itemToEdit = 'comment';
    this.editTextContent = currentCommentContent;
  }

  editComment(commentContent: TextToEdit) {
    const editedComment: CommentDTO = {
      content: commentContent.newContent
    } 

    this.commentService.editComment(this.commentToEdit, editedComment).subscribe({
      next: () => {
        console.log('comment edited');
        this.commentUpdated.emit(this.postId);
        this.commentToEdit = -1;
      }, 
      error(err) {
        console.error(err);
      }
    })
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

}
