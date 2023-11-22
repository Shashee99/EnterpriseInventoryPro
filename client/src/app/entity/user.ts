export class User {
  private email:string;
  private firstname:string;
  private lastname:string;
  private password:string;

  constructor(email:string,firstname:string,lastname:string,password:string) {
    this.email = email;
    this.firstname=firstname;
    this.lastname=lastname;
    this.password=password;

  }
}
