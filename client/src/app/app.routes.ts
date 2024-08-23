import { Routes } from '@angular/router';
import { SignInComponent } from './sign-in/sign-in.component';
import { NotFoundErrorComponent } from './not-found-error/not-found-error.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PathComponent } from './path/path.component';
import { PathTwoComponent } from './path-two/path-two.component';


export const routes: Routes = [
    {path: '', component: SignInComponent},
    {path: 'dashboard', component: DashboardComponent},
    {path: 'dashboard/path', component: PathComponent},
    {path: 'dashboard/path/path-two', component: PathTwoComponent},
    {path: 'path', component: PathComponent},
    {path: '**', component: NotFoundErrorComponent}

    
];
