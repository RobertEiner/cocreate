import { CommonModule } from '@angular/common';
import { Component, inject, Input, OnInit } from '@angular/core';
import { Post } from '../models/post';
import { Comment } from '../models/comment';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';


@Component({
  selector: 'app-project-card',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './project-card.component.html',
  styleUrl: './project-card.component.css'
})
export class ProjectCardComponent {
  router: Router = inject(Router);
  avtiveRoute: ActivatedRoute = inject(ActivatedRoute);

  @Input() title: string = '';
  @Input() content: string = '';
  @Input() posts: Post[] = [];


 
  navigateToPost(post: Post) {
    console.log(post.title);
    this.router.navigate([`post/${post.postId}`],  {relativeTo: this.avtiveRoute});
  }

}
