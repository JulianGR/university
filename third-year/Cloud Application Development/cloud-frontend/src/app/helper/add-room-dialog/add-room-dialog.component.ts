import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-add-room-dialog',
  templateUrl: './add-room-dialog.component.html',
  styleUrls: ['./add-room-dialog.component.css']
})
export class AddRoomDialogComponent implements OnInit {

  privateForm = new FormGroup({
    roomName: new FormControl(''),
  });

  constructor(public dialogRef: MatDialogRef<AddRoomDialogComponent>) { }

  ngOnInit(): void {
  }


  onNoClick(){
    this.dialogRef.close();
  }
}
