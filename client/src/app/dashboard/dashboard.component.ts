import { CommonModule } from '@angular/common';
import { HttpClient} from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Developer } from '../models/developer';
import { DashboardService } from '../services/dashboard.service';
import { Post } from '../models/post';


@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  httpClient: HttpClient = inject(HttpClient);
  dashboardService: DashboardService = inject(DashboardService);

  developers: Developer[] = [];
  posts: Post[] = [];

  getAllDevelopers() {
   this.developers = this.dashboardService.getAllDevelopers();
  }

  getAllPosts() {
    this.posts = this.dashboardService.getAllPosts();
   }
  
}
