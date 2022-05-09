import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { Administrator } from '../models/customer/administrator';
import { Restaurant } from '../models/customer/restaurant';
import { Food } from '../models/food';

@Injectable({
  providedIn: 'root',
})
export class AdministratorService {
  url: string = 'http://localhost:8080/restaurant';
  constructor(private http: HttpClient) {}

  async checkLogin(name: string, password: string) {
    let postData = { name: name, password: password };
    let returnStatement = await firstValueFrom(
      this.http.post(this.url + '/administrator/login', postData)
    );
    return returnStatement;
  }

  async addRestaurantToAdministrator(
    administrator: Administrator,
    restaurant: Restaurant
  ) {
    let postData = {
      name: restaurant.name,
      location: restaurant.location,
      deliveryZones: restaurant.deliveryZones,
    };
    let returnStatement = await firstValueFrom(
      this.http.post(
        this.url +
          '/administrator/restaurant/add?name=' +
          `${administrator.name}`,
        postData
      )
    );
    console.log(returnStatement);
    return returnStatement;
  }

  async getRestaurants(name: string) {
    let queryParam = { administratorName: name };

    return await firstValueFrom(
      this.http.get(this.url + '/administrator/locations', {
        params: queryParam,
      })
    ).catch((error) => {
      console.log('--User already registerd');
    });
  }

  async createMenu(
    selectedRestaurant: Restaurant,
    menuName: string,
    cateogries: string,
    food: Food
  ) {
    let bodyParam = {
      name: food.name,
      description: food.description,
      price: food.price,
    };

    let returnStatement = await firstValueFrom(
      this.http.post(
        this.url +
          '/administrator/menu' +
          `?restaurantName=${selectedRestaurant.name}&categories=${cateogries}&menuName=${menuName}`,
        bodyParam
      )
    );
    console.log(returnStatement);
    return returnStatement;
  }
}
