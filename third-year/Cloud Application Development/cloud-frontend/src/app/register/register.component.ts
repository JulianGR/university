import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {UserService} from "../services/user.service";
import {User} from "../models/User";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
  }

  registerForm = new FormGroup({
    username: new FormControl(''),
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl(''),
  });

  register() {
    if(this.registerForm.value.password !== this.registerForm.value.confirmPassword) {
      console.log('ERROR')
      return;
    }

    let newUser: User = {
      userName: this.registerForm.value.username,
      firstName: this.registerForm.value.firstname,
      lastName: this.registerForm.value.lastname,
      password: this.registerForm.value.password
    }
    //ToDo: Notification
    this.userService.registerUser(newUser).subscribe(
      (data) => {
          this.router.navigate(['/login'])
      }, (error) => console.log(error)
    )
  }

}
