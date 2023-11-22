import {Component} from '@angular/core';
import {
  FormControl,
  FormGroupDirective,
  NgForm,
  Validators,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {UsersService} from "../../services/users.service";
import {Router} from "@angular/router";
/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  passwordFormControl = new FormControl('', [
    Validators.required,
    // Add more password validation rules if needed
  ]);



  // matcher: any; // Assuming you have defined an errorStateMatcher

  matcher = new MyErrorStateMatcher()


  constructor(private userservice : UsersService,private router:Router) {
    console.log(this.emailFormControl)
  }


  onLogin() {
    this.userservice.loginUser(this.emailFormControl.value,this.passwordFormControl.value).subscribe(
      {
        next: (r) => {
          console.log(r)

          this.router.navigate([`users/${ r.firstname}`]);

        }
      }
    )
  }
}
