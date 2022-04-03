import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddMenuComponent } from './add-menu/add-menu.component';
import { AddRestaurantComponent } from './add-restaurant/add-restaurant.component';
import { TableComponent } from './table/table.component';
import { ViewMenuComponent } from './view-menu/view-menu.component';

const routes: Routes = [
  { path: 'add', component: AddRestaurantComponent },
  { path: 'add-menu', component: AddMenuComponent },
  { path: 'view-menu', component: ViewMenuComponent },
  { path: 'table', component: TableComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdministratorRoutingModule {}
