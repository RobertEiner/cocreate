import { Routes } from '@angular/router';
import { SignInComponent } from './sign-in/sign-in.component';
import { NotFoundErrorComponent } from './not-found-error/not-found-error.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SignUpComponent } from './sign-up/sign-up.component';


export const routes: Routes = [
    {path: '', component: SignInComponent},
    {path: 'signup', component: SignUpComponent},
    {path: 'developer/:id/dashboard', component: DashboardComponent},
    {path: 'dashboard', component: DashboardComponent},
    {path: '**', component: NotFoundErrorComponent}

    
];
