import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {LoginComponent} from "./components/login/login.component";
import {UserRegisterComponent} from './components/user-register/user-register.component'
import {SuccessComponent} from "./components/success/success.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {AddNewInvComponent} from "./components/add-new-inv/add-new-inv.component";


const routes: Routes = [

  {
    path:'',
    component:LoginComponent
  },
  {
    path:'register',
    component:UserRegisterComponent
  },
  {
    path:'success',
    component:SuccessComponent
  },
  {
    path: 'users/:username',
    component: DashboardComponent
  },



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
