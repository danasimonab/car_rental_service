import { Component, OnInit, ViewChild, AfterViewInit, AfterContentInit } from '@angular/core';
import {User} from "../interfaces/User"
import {HttpClient} from "@angular/common/http";
import {UserService} from "../service/user.service";


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.less']
})
export class UserComponent implements OnInit {

  users:User[] | undefined;
  selectedUser!:User;
  displayTable = "none";

  constructor(private http: HttpClient, private getUserService : UserService) {}

  ngOnInit(): void {
      this.getUsersList();
  }

  getUsersList() {
      this.getUserService.getUsers().subscribe(data => {
      console.log(data)
        this.users = data;
      }, error => {
                 console.log("Error");
                 console.error(error);
             }
             ).add(() => {
                 //subscribe is completed both success or error

             });
  }

  deleteUserById(userId:number){
      this.getUserService.deleteUser(userId).subscribe(data => {
          //data is available
          console.log("user was deleted!");
      },
      error => {
          console.log("Error");
          console.error(error);
      }
      ).add(() => {
          //subscribe is completed both success or error
           this.getUsersList();
      });

  }

  onSelect(user:User) {
     this.selectedUser = user;
  }


  addUser() {
      this.displayTable = "block";
  }

}
