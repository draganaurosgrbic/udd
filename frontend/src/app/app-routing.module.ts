import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdvertisementsComponent } from './components/application/advertisements/advertisements.component';
import { ApplicationListComponent } from './components/application/application-list/application-list.component';
import { ApplicationSearchComponent } from './components/application/application-search/application-search.component';
import { ApplicationUploadComponent } from './components/application/application-upload/application-upload.component';
import { LoginComponent } from './components/auth/login/login.component';
import { RoleAuth } from './models/auth';
import { AuthGuard } from './utils/auth.guard';
import { Route } from './utils/route';

const routes: Routes = [
  {
    path: Route.LOGIN,
    component: LoginComponent
  },
  {
    path: Route.ADVERTISEMENTS,
    component: AdvertisementsComponent,
    canActivate: [AuthGuard],
    data: { roles: [RoleAuth.CANDIDATE] }
  },
  {
    path: Route.APPLICATION_LIST,
    component: ApplicationListComponent,
    canActivate: [AuthGuard],
    data: { roles: [RoleAuth.HR]}
  },
  {
    path: `${Route.APPLICATION_UPLOAD}/:${Route.ADVERTISEMENT_ID}`,
    component: ApplicationUploadComponent,
    canActivate: [AuthGuard],
    data: { roles: [RoleAuth.CANDIDATE] }
  },
  {
    path: Route.APPLICATION_SEARCH,
    component: ApplicationSearchComponent,
    canActivate: [AuthGuard],
    data: { roles: [RoleAuth.HR] }
  },
  {
    path: '**',
    pathMatch: 'full',
    redirectTo: Route.LOGIN
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
