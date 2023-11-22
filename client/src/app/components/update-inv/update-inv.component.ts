import {Component, Inject} from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA, MatDialogRef, MatDialogModule} from '@angular/material/dialog';
import {NgIf} from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {FormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";

// export interface DialogData {
//   animal: string;
//   name: string;
// }
export interface Inventory {
  brand?: string;
  description?: string;
  expired_on?: Date | null;
  id?: number | null;
  price?: number | null;
  type?: string;
}

@Component({
  selector: 'app-update-inv',
  templateUrl: './update-inv.component.html',
  styleUrls: ['./update-inv.component.scss'],
  standalone: true,
  imports: [MatDialogModule, MatFormFieldModule, MatInputModule, FormsModule, MatButtonModule,MatDatepickerModule, MatNativeDateModule],
})
export class UpdateInvComponent {
  constructor(
    public dialogRef: MatDialogRef<UpdateInvComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Inventory,
  )
  {
    console.log("inside of the constructor")
    console.log(data)

  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
