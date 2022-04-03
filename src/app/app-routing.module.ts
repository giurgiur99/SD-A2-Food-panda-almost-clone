import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  { path: 'login', component: LoginComponent },
  {
    path: 'administrator',
    loadChildren: () =>
      import('./components/administrator/administrator.module').then(
        (m) => m.AdministratorModule
      ),
  },
  { path: 'customer', loadChildren: () => import('./components/customer/customer.module').then(m => m.CustomerModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
