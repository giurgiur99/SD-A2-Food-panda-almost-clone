import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/app/shared/models/customer/customer';
import { Food } from 'src/app/shared/models/food';
import { AdministratorService } from 'src/app/shared/services/administrator.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss'],
})
export class CartComponent implements OnInit {
  selectedFood: Food[];
  displayedColumns: string[] = ['name', 'description', 'price'];
  customer: Customer;

  constructor(
    private router: Router,
    private administratorService: AdministratorService
  ) {
    this.customer = this.router.getCurrentNavigation().extras.state['customer'];
    this.selectedFood =
      this.router.getCurrentNavigation().extras.state['selectedFood'];
    console.log(this.selectedFood);
  }

  ngOnInit(): void {}
}
