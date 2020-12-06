import {Component} from '@angular/core';
import {ThemePalette} from "@angular/material/core";
import {NAV_ROUTES, UNAUTHORIZED_NAV_ROUTES} from "./models/Links";
import {AuthService} from "./services/auth.service";
import {Router} from "@angular/router";
import {KubernetesClientService} from "./services/kubernetes-client.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  userName: string

  constructor(private authService: AuthService,
              private router: Router) {
    this.showUsername()
    //kubservice.listNamespacedPod()
  }

  isAuthorized() {
    return this.authService.isAuthorized()
  }

  showUsername() {
    this.authService.getUserData().subscribe(
      (data) => this.userName = data?.firstName + ' ' + data?.lastName
      , (error => {console.log('User not authorized yet')})
    )
  }

  title = 'cloud-frontend';
  links = NAV_ROUTES;
  unauthorizedLinks = UNAUTHORIZED_NAV_ROUTES;
  background: ThemePalette = undefined;

  logOut() {
    this.authService.logOut();
    this.router.navigate(['login'])
  }

}
