import { Component, OnInit, Input, ViewChild } from '@angular/core';
import {User} from "../interfaces/User"
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.less'],
  providers: [UserDetailsComponent, NgbModal]

})
export class UserDetailsComponent implements OnInit {
  @Input() user! : User

  constructor(config: NgbModalConfig, private modalService: NgbModal) {
      // customize default values of modals used by this component tree
      config.backdrop = 'static';
      config.keyboard = false;
  }
  ngOnInit(): void {}

  open(content:any) {
     this.modalService.open(content);
  }



}
