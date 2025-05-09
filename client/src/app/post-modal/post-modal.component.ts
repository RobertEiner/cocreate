import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { Post } from '../models/post';
import { Util } from '../../util/util';
import { CommonModule } from '@angular/common';
import { EditTextboxComponent } from '../edit-textbox/edit-textbox.component';
import { TextToEdit } from '../interfaces/textToEdit';
import { CommentDTO } from '../models/comment-dto';
import { CommentService } from '../services/comment/comment.service';
import { PostService } from '../services/post/post.service';
import { DeleteEntryComponent } from '../delete-entry/delete-entry.component';
import { CreateEntryComponent } from '../create-entry/create-entry.component';

@Component({
  selector: 'app-post-modal',
  standalone: true,
  imports: [CommonModule, EditTextboxComponent, DeleteEntryComponent, CreateEntryComponent],
  templateUrl: './post-modal.component.html',
  styleUrl: './post-modal.component.css'
})
export class PostModalComponent {
  util: Util = new Util();
  // Services
  commentService: CommentService = inject(CommentService);
  postService: PostService = inject(PostService)

  // Inputs
  @Input() selectedPost: Post = new Post("", "");
  @Output() commentUpdated: EventEmitter<number> = new EventEmitter<number>();
  @Output() postUpdated: EventEmitter<number> = new EventEmitter<number>();
  @Output() postDeleted: EventEmitter<number> = new EventEmitter<number>();

  // Class variables
  changeComment: boolean = false;
  commentIdToEdit: number = -1;
  commentToDelete: number = -1;
  itemToEdit: string = '';
  textContent: string = '';
  deleteCommentPressed: boolean = false;
  editTitlePressed: boolean = false;
  editContentPressed: boolean = false;
  deletePostPressed: boolean = false;

  onModalClose() {
    this.deleteCommentPressed = false;
    this.commentIdToEdit = -1;
    this.editTitlePressed = false;
    this.deletePostPressed = false;
  }

  // Display edit & delete options
  displayEditComment(commentId: number, content: string) {
  this.deleteCommentPressed = false;  // If edit is pressed while delete textbox is open, close delete box
  this.itemToEdit = 'comment';
  this.textContent = content;
  this.changeComment = true;
  this.commentIdToEdit = commentId;
 }

 displayDeleteComment(commentId: number) {
  this.commentIdToEdit = -1; // If delete is pressed while edit textbox is open, close edit box
  this.commentToDelete = commentId;
  this.deleteCommentPressed = true;
 }


 emitUpdatedText(updatedText: TextToEdit) {
  if(updatedText.type === 'comment') {
    this.editComment(updatedText.newContent);
  } else if(updatedText.type === 'post title') {
    this.editPostTitle(updatedText.newContent);
  } else if(updatedText.type === 'post content') {
    this.editPostContent(updatedText.newContent);
  }
}

//  ----------------- EDIT ----------------------

//POST

dispayEditPostTitle(title: string) {
  // clear other text boxes
  this.commentIdToEdit = -1;
  this.deleteCommentPressed = false;  // If edit is pressed while delete textbox is open, close delete box
  this.editTitlePressed = true;

  this.itemToEdit = 'post title';
  this.textContent = title;
}

dispayEditPostContent(content: string) {
  this.editContentPressed = true;
  this.itemToEdit = 'post content';
  this.textContent = content;
}

editPostTitle(newTitle: string) {
  const editedPost: Post = {
    title: newTitle,
    content: this.selectedPost.content,
    developer: null,
    comments: []
  }

  this.postService.editPostById(this.selectedPost.postId!, editedPost).subscribe({
    next: () => {
      this.editTitlePressed = false;
      this.selectedPost.title = newTitle;
    },
    error(err) {
      console.error(err);
    }
  });
}

editPostContent(newContent: string) {
  const editedPost: Post = {
    title: this.selectedPost.title,
    content: newContent,
    developer: null,
    comments: []
  }

  this.postService.editPostById(this.selectedPost.postId!, editedPost).subscribe({
    next: () => {
      this.editContentPressed = false;
      this.selectedPost.content = newContent;
    },
    error(err) {
      console.error(err);
    }
  });

}



// COMMENT
 cancelEditText(comment: string) {
  this.commentIdToEdit = -1;
  this.editTitlePressed = false;
  this.editContentPressed = false;
}

 editComment(updatedCommentContent: string) {
  const commentDTO: CommentDTO= {
    content: updatedCommentContent
  };

  this.commentService.editComment(this.commentIdToEdit, commentDTO).subscribe({
    next: () => {
      this.commentUpdated.emit(this.selectedPost.postId);
      this.commentIdToEdit = -1;
      this.changeComment = true;

    }, 
    error(err) {
      console.error(err);
    }
  });
}

//  ----------------- DELETE ----------------------

deleteComment() {
  this.commentService.deleteComment(this.commentToDelete).subscribe({
    next: () => {
      console.log('deleteed coment')
      this.commentUpdated.emit(this.selectedPost.postId);
      this.commentToDelete = -1;
      this.deleteCommentPressed = true;
    },
    error(err) {

    }
  })
}

cancelDeleteComment() {
  this.deleteCommentPressed = false;
}

displayDeletePost() {
  // this.postDeleted.emit(this.selectedPost.postId);
  this.deletePostPressed = true;
}

deletePost() {
  this.postDeleted.emit(this.selectedPost.postId);
}

cancelDeletePost() {
  this.deletePostPressed = false;
}







}
