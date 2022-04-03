import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Administrator } from 'src/app/shared/models/customer/administrator';
import { Restaurant } from 'src/app/shared/models/customer/restaurant';
import { AdministratorService } from 'src/app/shared/services/administrator.service';

@Component({
  selector: 'app-add-restaurant',
  templateUrl: './add-restaurant.component.html',
  styleUrls: ['./add-restaurant.component.scss'],
})
export class AddRestaurantComponent implements OnInit {
  administrator?: Administrator;
  restaurant: Restaurant = {
    name: '',
    location: '',
    deliveryZones: '',
  };

  constructor(
    private router: Router,
    private administratorService: AdministratorService
  ) {
    //@ts-ignore
    // this.administrator = this.router.getCurrentNavigation().extras.state.data;

    this.administrator =
      //@ts-ignore
      this.router.getCurrentNavigation().extras.state.administrator;
    console.log(this.administrator);
  }

  ngOnInit(): void {}

  handleClick() {
    console.log(this.restaurant);
  }

  handleAddRestaurant() {
    this.administratorService.addRestaurantToAdministrator(
      //@ts-ignore
      this.administrator,
      this.restaurant
    );
  }
}
