import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/app/shared/models/customer/customer';
import { Restaurant } from 'src/app/shared/models/customer/restaurant';
import { CustomerService } from 'src/app/shared/services/customer.service';

@Component({
  selector: 'app-restaurants',
  templateUrl: './restaurants.component.html',
  styleUrls: ['./restaurants.component.scss'],
})
export class RestaurantsComponent implements OnInit {
  customer: Customer;
  restaurants: Restaurant[];

  constructor(
    private router: Router,
    private customerService: CustomerService
  ) {
    this.customer = this.router.getCurrentNavigation().extras.state['customer'];
    console.log(this.customer);
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

  handleViewMenu(restaurant: Restaurant) {
    this.router.navigate(['/customer/restaurants/view-menu'], {
      state: {
        restaurant: restaurant,
      },
    });
  }

  // console.log(restaurant);
}
