import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {UserComponent} from "./user/user.component";
import {AddComponent} from "./add/add.component";
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
    { path: 'user', component: UserComponent },
    { path: 'adduser', component: AddComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
