import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/app/shared/models/customer/customer';
import { Restaurant } from 'src/app/shared/models/customer/restaurant';
import { Food } from 'src/app/shared/models/food';
import { CustomerService } from 'src/app/shared/services/customer.service';

@Component({
  selector: 'app-restaurants',
  templateUrl: './restaurants.component.html',
  styleUrls: ['./restaurants.component.scss'],
})
export class RestaurantsComponent implements OnInit {
  customer: Customer;
  restaurants: Restaurant[];
  selectedFood: Food[];

  constructor(
    private router: Router,
    private customerService: CustomerService
  ) {
    this.customer = this.router.getCurrentNavigation().extras.state['customer'];
    this.selectedFood =
      this.router.getCurrentNavigation().extras.state['selectedFood'];
    console.log(this.customer);
    console.log(this.selectedFood);
  }

  counter(i: number) {
    return new Array(i);
  }

  async ngOnInit() {
    this.restaurants = <Restaurant[]>(
      await this.customerService.getRestaurants()
    );
    console.log(this.restaurants);
    for (let restaurant of this.restaurants) {
      restaurant.imgUrl =
        '../../../../assets/card-pics/asset_' + restaurant.name + '.jpg';
    }
  }

  handleViewMenu(restaurant: Restaurant, customer: Customer) {
    this.router.navigate(['/customer/restaurants/view-menu'], {
      state: {
        restaurant: restaurant,
        customer: customer,
      },
    });
  }

  // console.log(restaurant);
}
