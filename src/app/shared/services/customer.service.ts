import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { Customer } from '../models/customer/customer';

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
    return await firstValueFrom(this.http.get(this.url + '/explore'));
  }
}
