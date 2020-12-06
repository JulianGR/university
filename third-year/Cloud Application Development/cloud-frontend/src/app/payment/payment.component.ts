import {Component, OnInit} from '@angular/core';
import {UserService} from "../services/user.service";
import {Router} from "@angular/router";
import {TenantService} from "../services/tenant.service";
import {FormControl, FormGroup} from "@angular/forms";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  constructor(private userService: UserService,
              private tenantService: TenantService,
              private router: Router) {
  }


  tenantForm = new FormGroup({
    serverName: new FormControl(''),
  });

  isTenant: boolean;
  SINGLE_PREMIUM_URL = environment.jitsiUrl

  ngOnInit(): void {
    this.userService.getUserData().subscribe(
      (data) => this.isTenant = data.isTenant
    )
  }

  subscribe() {
    this.userService.patchTenantStatus(true).subscribe(
      (data) => {
        this.initializeTenant();
        this.router.navigate(['/server']);
      }
    )
  }

  unsubscribe() {
    this.userService.patchTenantStatus(false).subscribe(
      (data) => this.router.navigate(['/rooms'])
    )
  }

  initializeTenant() {
    this.tenantService.initializeTenant(this.SINGLE_PREMIUM_URL, this.tenantForm.value.serverName).subscribe(
      (data) => console.log(data)
    )
  }
}
