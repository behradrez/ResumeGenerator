import { Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { SectionEditPageComponent } from './section-edit-page/section-edit-page.component';

export const routes: Routes = [
    {
        title:"LoginPage",
        path:"login",
        component:LoginPageComponent
    },
    {
        title:"ResumeEditPage",
        path:"resume",
        component: SectionEditPageComponent
    }
];
