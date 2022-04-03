import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Administrator } from 'src/app/shared/models/customer/administrator';
import { Restaurant } from 'src/app/shared/models/customer/restaurant';
import { Food } from 'src/app/shared/models/food';
import { AdministratorService } from 'src/app/shared/services/administrator.service';

@Component({
  selector: 'app-add-menu',
  templateUrl: './add-menu.component.html',
  styleUrls: ['./add-menu.component.scss'],
})
export class AddMenuComponent implements OnInit {
  administrator?: Administrator;
  restaurant: Restaurant = { name: '', location: '', deliveryZones: '' };
  selectedRestaurant: Restaurant = {
    name: '',
    location: '',
    deliveryZones: '',
  };
  restaurants: any;
  food: Food = { name: '', description: '', price: 0 };
  menuName: string = '';
  selectedCategory: string = '';

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
    if (this.administrator?.name)
      this.restaurants = await this.administratorService.getRestaurants(
        this.administrator.name
      );
    console.log(this.restaurants);
  }

  addMenu() {
    if (this.administrator) {
      this.administratorService.createMenu(
        this.selectedRestaurant,
        this.menuName,
        this.selectedCategory,
        this.food
      );
    }
  }
}
