import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {AnonymousUser} from "../../models/User";
import {Observable} from "rxjs";
import {map, startWith} from "rxjs/operators";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-add-user-dialog',
  templateUrl: './add-user-dialog.component.html',
  styleUrls: ['./add-user-dialog.component.css']
})
export class AddUserDialogComponent implements OnInit {

  addUserControl = new FormControl();
  availableUsers: AnonymousUser[];
  filteredUsers: Observable<AnonymousUser[]>;

  constructor(public dialogRef: MatDialogRef<AddUserDialogComponent>,
  @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    this.availableUsers = this.data?.users;
    this.filteredUsers = this.addUserControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );
  }

  private _filter(value: string): AnonymousUser[] {
    const filterValue = value.toLowerCase();
    return this.availableUsers.filter(option => option.userName.toLowerCase().includes(filterValue));
  }

  onNoClick(){
    this.dialogRef.close();
  }

}
