import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdministratorRoutingModule } from './administrator-routing.module';
import { AddRestaurantComponent } from './add-restaurant/add-restaurant.component';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddMenuComponent } from './add-menu/add-menu.component';
import { ViewMenuComponent } from './view-menu/view-menu.component';
import { TableComponent } from './table/table.component';

@NgModule({
  declarations: [AddRestaurantComponent, AddMenuComponent, ViewMenuComponent, TableComponent],
  imports: [
    CommonModule,
    AdministratorRoutingModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    AngularMaterialModule,
    FormsModule,
    FlexLayoutModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AdministratorModule {}
