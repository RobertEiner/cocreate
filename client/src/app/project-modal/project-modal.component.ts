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

  updateComment: boolean = false;
  commentToEdit: number = 0;
  editCommentContent: string = "";

  deletePostPressed: boolean = false;
  editPostPressed: boolean = false;


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

  convertDate(comment: Comment): string {
    const date = comment.createdAt ? new Date(comment.createdAt) : null;
    return date?.getFullYear() + '-' + date?.getMonth() + '-' + date?.getDate();

  }

  // ------------------------------- Edit comment ------------------------

  editComment(commentId: number) {
    this.updateComment = true;
    this.commentToEdit = commentId;
  }

  editCommentInForm(commentId: number) {
    const commentDTO: CommentDTO = { 
      content: this.editCommentContent 
    }

    this.commentService.editComment(commentId, commentDTO).subscribe({
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
        this.postDeleted.emit(postId);
        this.deletePostPressed = false;
      },
      error(err) {
        console.log(err);
      }
    })
  }

  // ----------------------------- Edit post ----------------------------

  displayEditPostTextbox(postId: number) {
    this.editPostPressed = true;
    // TODO: finish edit post functionality
  }

  cancelEditPost() {
    this.editPostPressed = false;
  }

  editText(textToEdit: TextToEdit) {
    if(textToEdit.type === 'Post') {
      this.editPost(textToEdit);
    }
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
        console.log('post edited right')
        // TODO: rerender component here to get the updated post content immediately
        this.editPostPressed = false;
        this.postContentUpdated.emit(this.postId);
        // the line above also works with this line:
        //this.postDescription = editedPost.content;

      },
      error(err) {
        console.error(err);
      }
    })

  }



}
