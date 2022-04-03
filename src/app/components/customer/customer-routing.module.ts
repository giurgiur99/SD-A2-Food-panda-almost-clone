import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartComponent } from './cart/cart.component';
import { RestaurantViewMenuComponent } from './restaurant-view-menu/restaurant-view-menu.component';
import { RestaurantsComponent } from './restaurants/restaurants.component';

const routes: Routes = [
  { path: 'restaurants', component: RestaurantsComponent },
  { path: 'restaurants/view-menu', component: RestaurantViewMenuComponent },
  { path: 'restaurant/order', component: CartComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CustomerRoutingModule {}
