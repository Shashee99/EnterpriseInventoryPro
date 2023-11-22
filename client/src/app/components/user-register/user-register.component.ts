import { Component } from '@angular/core';
import {FormControl, Validators, FormsModule, ReactiveFormsModule, FormGroup, FormBuilder} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {Router} from "@angular/router";
import {User} from "../../entity/user";
import {UsersService} from "../../services/users.service";
@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.scss']
})
export class UserRegisterComponent {
  registerForm: FormGroup;


  constructor(private fb: FormBuilder, private router: Router,private userService:UsersService) {
    this.registerForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
    });
  }

  onCancel() {
    this.router.navigate(['']);
  }

  onSubmit() {
    if (this.registerForm.valid) {
      // Form is valid, perform actions here
      const formData = this.registerForm.value;
      console.log('Form Data:', formData);
      const user = new User(formData.email,formData.firstName, formData.lastName,formData.password);

      this.userService.createUser(user).subscribe({

        error: (e) => {
          console.log(e)  },    // errorHandler
        next: (r) => {
          console.log(r);
          this.router.navigate(['success']);

        }     // nextHandler

      });
    }
  }


}
