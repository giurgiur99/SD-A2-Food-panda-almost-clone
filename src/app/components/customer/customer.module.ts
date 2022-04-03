import { CommonModule } from '@angular/common';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';

import { CustomerRoutingModule } from './customer-routing.module';
import { RestaurantsComponent } from './restaurants/restaurants.component';
import { Component } from '@angular/core';
import { AdministratorRoutingModule } from '../administrator/administrator-routing.module';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RestaurantViewMenuComponent } from './restaurant-view-menu/restaurant-view-menu.component';
import { CartComponent } from './cart/cart.component';

@NgModule({
  declarations: [
    RestaurantsComponent,
    RestaurantViewMenuComponent,
    CartComponent,
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
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
export class CustomerModule {}
