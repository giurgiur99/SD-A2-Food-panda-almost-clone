import { HttpClient } from '@angular/common/http';
import { Token } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { Customer } from '../models/customer/customer';
import { Food } from '../models/food';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  url: string = 'http://localhost:8080/restaurant/customer';
  constructor(private http: HttpClient) {}

  async checkLogin(name: string, password: string) {
    let postData = { name: name, password: password };
    let returnStatement = await firstValueFrom(
      this.http.post(this.url + '/login', postData)
    );
    return returnStatement;
  }

  async addCustomer(name: string, password: string) {
    let postData = { name: name, password: password };

    return await firstValueFrom(
      this.http.post(this.url + '/add', postData)
    ).catch((error) => {
      console.log('--User already registerd');
    });
  }

  async getRestaurants() {
    let headers;

    console.log('Bearer ' + sessionStorage.getItem('token'));
    if (sessionStorage.getItem('token')) {
      headers = { Authorization: 'Bearer ' + sessionStorage.getItem('token') };
    }
    return await firstValueFrom(
      this.http.get(this.url + '/explore', {
        headers: headers,
      })
    );
  }

  async placeOrder(customer: Customer, food: Food[]) {
    let headers;
    console.log('Bearer ' + sessionStorage.getItem('token'));
    if (sessionStorage.getItem('token')) {
      headers = { Authorization: 'Bearer ' + sessionStorage.getItem('token') };
    }

    let postData = food[0];
    return await firstValueFrom(
      this.http.post(this.url + '/order?customer=' + customer.name, postData, {
        headers: headers,
      })
    ).catch((error) => {
      console.log('-- Order could not be processed! --');
    });
  }
}
