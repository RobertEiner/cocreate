import { Routes } from '@angular/router';
import { SignInComponent } from './sign-in/sign-in.component';
import { NotFoundErrorComponent } from './not-found-error/not-found-error.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PathComponent } from './path/path.component';
import { PathTwoComponent } from './path-two/path-two.component';
import { PostDetailComponent } from './post-detail/post-detail.component';
import { SignUpComponent } from './sign-up/sign-up.component';


export const routes: Routes = [
    {path: '', component: SignInComponent},
    {path: 'signup', component: SignUpComponent},
    {path: 'dashboard', component: DashboardComponent},
    {path: 'dashboard/post/:id', component: PostDetailComponent},
    {path: 'dashboard/path', component: PathComponent},
    {path: 'dashboard/path/path-two', component: PathTwoComponent},
    {path: 'path', component: PathComponent},
    {path: '**', component: NotFoundErrorComponent}

    
];
