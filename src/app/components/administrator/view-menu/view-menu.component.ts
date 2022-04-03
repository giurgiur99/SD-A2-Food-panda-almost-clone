import { Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Administrator } from 'src/app/shared/models/customer/administrator';
import { Restaurant } from 'src/app/shared/models/customer/restaurant';
import { Food } from 'src/app/shared/models/food';

import { AdministratorService } from 'src/app/shared/services/administrator.service';

@Component({
  selector: 'app-view-menu',
  templateUrl: './view-menu.component.html',
  styleUrls: ['./view-menu.component.scss'],
})
export class ViewMenuComponent implements OnInit {
  administrator?: Administrator;
  restaurants?: Restaurant[];
  restaurant: Restaurant = {
    name: '',
    location: '',
    deliveryZones: '',
  };
  selectedRestaurant: Restaurant = this.restaurant;
  selectedCategory: string = '';
  foods?: Food[];
  dataSource: Food[] = [];

  constructor(
    private router: Router,
    private administratorService: AdministratorService
  ) {
    this.administrator =
      //@ts-ignore
      this.router.getCurrentNavigation().extras.state['administrator'];
    console.log(this.administrator);
  }

  async ngOnInit() {
    if (this.administrator?.name) {
      //@ts-ignore
      this.restaurants = await this.administratorService.getRestaurants(
        this.administrator.name
      );
    }
    console.log(this.restaurants);
  }

  handleShowSelectedMenu() {
    console.log(this.selectedRestaurant);
    // console.log(this.selectedCategory);
    this.selectedRestaurant.menuList?.forEach((menu) => {
      menu.categories === this.selectedCategory
        ? this.dataSource.push(menu.foods[0])
        : null;
    });
    this.router.navigate(['/administrator/table'], {
      state: {
        dataSource: this.dataSource,
      },
    });
  }
}
