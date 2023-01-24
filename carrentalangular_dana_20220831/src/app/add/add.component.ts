import { Component, OnInit, Input } from '@angular/core';
import {User} from "../interfaces/User"
import {HttpClient} from "@angular/common/http";
import {UserService} from "../service/user.service";
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.less']
})


export class AddComponent implements OnInit {

@Input() display!:String;

checkoutForm = this.formBuilder.group({
    email: '',
    password: '',
    username: '',
    firstname: '',
    lastname: '',
    country: '',
    license_number: '',
    status: ''
  });

  constructor(private http: HttpClient, private userService : UserService, private formBuilder : FormBuilder) { }

  ngOnInit(): void {

  }

  onSubmit() {
    console.log(this.checkoutForm.value);
    let newUser = this.checkoutForm.value as User;
      this.userService.postUser(newUser).subscribe(data => {
          document.getElementById("addUser")!.style.display = 'none';
          window.location.reload();
      });
  }




}
