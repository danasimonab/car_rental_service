import { Component, OnInit, Input } from '@angular/core';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {User} from "../interfaces/User"
import { FormBuilder } from '@angular/forms';
import {UserService} from "../service/user.service";


@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.less'],
  providers: [UserUpdateComponent, NgbModal]
})
export class UserUpdateComponent implements OnInit {
  @Input() user! : User
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

  constructor(config: NgbModalConfig, private modalService: NgbModal, private formBuilder: FormBuilder, private userService: UserService) {
     // customize default values of modals used by this component tree
     config.backdrop = 'static';
     config.keyboard = false;

   }

  ngOnInit(): void {
    this.checkoutForm.setValue({email: this.user.email, password:this.user.password, username:this.user.username, firstname:this.user.firstname, lastname:this.user.lastname, country:this.user.country, license_number:this.user.license_number, status:this.user.status});
  }

  open(content:any) {
       this.modalService.open(content);
  }

  onSubmit() {
      console.log(this.checkoutForm.value);
      let newUser = this.checkoutForm.value as User;
      newUser.user_id = this.user.user_id;
      console.log(newUser);
      console.log(newUser.email);
      this.userService.updateUser(newUser).subscribe(data => {
           console.log("after save = " + data);
           window.location.reload();
      });
    }

}
