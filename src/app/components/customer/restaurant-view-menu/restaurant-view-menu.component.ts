import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/app/shared/models/customer/customer';
import { Restaurant } from 'src/app/shared/models/customer/restaurant';
import { Food } from 'src/app/shared/models/food';
import { CustomerService } from 'src/app/shared/services/customer.service';

@Component({
  selector: 'app-restaurant-view-menu',
  templateUrl: './restaurant-view-menu.component.html',
  styleUrls: ['./restaurant-view-menu.component.scss'],
})
export class RestaurantViewMenuComponent implements OnInit {
  restaurant: Restaurant;
  displayedColumns: string[] = ['name', 'description', 'price', 'cart'];
  dataSource: Food[] = [];
  selectedFood: Food[] = [];
  customer: Customer;
  public textShow = false;

  constructor(
    private router: Router,
    private customerService: CustomerService
  ) {
    this.customer = router.getCurrentNavigation().extras.state['customer'];
    this.restaurant = router.getCurrentNavigation().extras.state['restaurant'];
    console.log(this.customer);
  }

  ngOnInit(): void {
    let i = 0;
    for (let menu of this.restaurant.menuList) {
      menu.foods.forEach((food) => {
        this.dataSource.push(food);
      });
      console.log(this.dataSource);
      i++;
    }
    console.log(this.customer);
  }

  handleAddToCart(food: Food) {
    console.log(food);
    this.selectedFood.push(food);
  }

  placeOrder() {
    console.log(this.selectedFood);
    console.log(this.customer);
    this.customerService.placeOrder(this.customer, this.selectedFood);
    this.showText();
  }

  showText() {
    this.textShow = true;
  }
  hideText() {
    this.textShow = false;
  }
}
