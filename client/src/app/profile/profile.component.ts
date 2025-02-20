import { Component, inject, OnInit } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { Util } from '../../util/util';
import { DeveloperService } from '../services/developer/developer.service';
import { Developer } from '../models/developer';
import { MyPostsComponent } from '../my-posts/my-posts.component';
import { CommonModule } from '@angular/common';




@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, NavbarComponent, MyPostsComponent],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {

  // Services
  developerService: DeveloperService = inject(DeveloperService);

  // Misc  
  util: Util = new Util();

  // class variables
  devId: number | null = null;
  dev: Developer = {
    userName: '',
    password: '',
    emailAddress: '',
    preferredLanguage: '',
  }
  optionTitle: string = 'posts';
  postsSelected: boolean = true;

  ngOnInit(): void {
    this.devId = parseInt(this.util.getDevIdFromUrl()!);
    // console.log("DEVID :  " + this.devId)
    this.getDevInfo();
    console.log('profile posts: ');
    console.log(this.dev.posts)
  }

  getDevInfo() {
    if(this.devId) {
      this.developerService.getDeveloperById(this.devId).subscribe({
        next: (response: Developer) => {
          this.dev = response;
        },
        error(err) {
          console.error(err);
        }
  
      });
    }
  }

  handleOption(option: string) {
    if(option === 'myposts') {
      this.postsSelected = true;
      this.optionTitle = 'posts'
    }
  } 





}
