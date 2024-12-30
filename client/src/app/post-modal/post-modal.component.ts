import { Component, Input } from '@angular/core';
import { Post } from '../models/post';
import { Util } from '../../util/util';
import { CommonModule } from '@angular/common';




@Component({
  selector: 'app-post-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './post-modal.component.html',
  styleUrl: './post-modal.component.css'
})
export class PostModalComponent {

  util: Util = new Util();
  @Input() selectedPost: Post = new Post("", "");

  // TODO: here
  // 1. send in post object as an input from the my-posts-component
  // 2. populate the modal with title, content, comments and date
  // 3. create funtionality for creating comments, changing comments, editing post including title, deleting etc. Use edit-textbox-component

}
