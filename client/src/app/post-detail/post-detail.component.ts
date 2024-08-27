import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Post } from '../models/post';
import { PostService } from '../services/post.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-post-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './post-detail.component.html',
  styleUrl: './post-detail.component.css'
})
export class PostDetailComponent implements OnInit {

  postId: number = 0; 
  projectTitle: string = '';
  // post: Post;
  activeRoute: ActivatedRoute = inject(ActivatedRoute);

  ngOnInit(): void {
    const idParam: string | null = this.activeRoute.snapshot.paramMap.get('id');
    if(idParam !== null) {
      this.postId = parseInt(idParam, 10); // tells parseInt we want a base 10 num, i.e. a decimal number. e.g. if the input would be 08, we get 8
      this.getPost(this.postId);
    } else {
      //console.error('ID param is missing')
    }
  }

  getPost(id: number) {

  }

}
