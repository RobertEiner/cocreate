import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';




@Component({
  selector: 'app-post-detail',
  standalone: true,
  imports: [],
  templateUrl: './post-detail.component.html',
  styleUrl: './post-detail.component.css'
})
export class PostDetailComponent implements OnInit {

  postId: number = 0; 
  activeRoute: ActivatedRoute = inject(ActivatedRoute);

  ngOnInit(): void {
    // figure out how to cast this, the keep working on the post-detail component to display detila about spoecific component
    this.postId = parseInt(this.activeRoute.snapshot.paramMap.get('id')!, 10);
    console.log(this.postId);
  }

}
