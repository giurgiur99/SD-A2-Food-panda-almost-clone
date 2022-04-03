import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Restaurant } from 'src/app/shared/models/customer/restaurant';
import { Food } from 'src/app/shared/models/food';

@Component({
  selector: 'app-restaurant-view-menu',
  templateUrl: './restaurant-view-menu.component.html',
  styleUrls: ['./restaurant-view-menu.component.scss'],
})
export class RestaurantViewMenuComponent implements OnInit {
  restaurant: Restaurant;
  displayedColumns: string[] = ['name', 'description', 'price'];
  dataSource: Food[] = [];

  constructor(private router: Router) {
    this.restaurant = router.getCurrentNavigation().extras.state['restaurant'];
    console.log(this.restaurant);
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
    // console.log(this.dataSource);
  }
}
