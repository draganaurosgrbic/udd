import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from 'src/app/services/storage.service';
import { Route } from 'src/app/utils/route';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss']
})
export class ToolbarComponent {

  constructor(
    private storageService: StorageService,
    private router: Router
  ) { }

  get login() {
    return this.router.url.includes(Route.LOGIN);
  }

  get search() {
    return this.router.url.includes(Route.APPLICATION_SEARCH);
  }

  logout() {
    this.storageService.removeAuth();
    this.router.navigate([Route.LOGIN]);
  }

  goToApplicationList(){
    this.router.navigate([Route.APPLICATION_LIST]);
  }

  goToKibana() {
    window.open(environment.kibanaUrl);
  }

}
