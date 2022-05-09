import { Component, Input, OnInit, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { Customer } from 'src/app/shared/models/customer/customer';
import { HttpClient } from '@angular/common/http';
import { CustomerService } from 'src/app/shared/services/customer.service';
import { Router } from '@angular/router';
import { AdministratorService } from 'src/app/shared/services/administrator.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  // user: Customer = { name: 'boss', password: 'boss' };
  user: Customer = { name: 'user1', password: 'parola' };
  @Output() userClick = new EventEmitter<Customer>();

  constructor(
    private customerService: CustomerService,
    private administatorService: AdministratorService,
    private router: Router
  ) {}

  async handleClickLogin() {
    try {
      const customerAuth = await this.customerService.checkLogin(
        this.user.name,
        this.user.password
      );
      console.log(customerAuth.toString());
      if (customerAuth !== 'false') {
        sessionStorage.setItem('token', customerAuth.toString());

        return this.router.navigate(['/customer/restaurants'], {
          state: { customer: this.user },
        });
      }
    } catch (error) {
      console.log(error);
      console.log('--Customer not found!--');
    }
    try {
      const adminAuth = await this.administatorService.checkLogin(
        this.user.name,
        this.user.password
      );
      console.log(adminAuth);
      if (adminAuth) {
        return this.router.navigate(['/administrator/add'], {
          state: { administrator: this.user },
        });
      }
    } catch (error) {
      return console.log('--Admin not found!--');
    }
  }

  async handleClickRegister() {
    await this.customerService.addCustomer(this.user.name, this.user.password);
  }

  ngOnInit(): void {}
}
function RSA_PRIVATE_KEY(
  arg0: {},
  RSA_PRIVATE_KEY: any,
  arg2: { algorithm: 'RS256'; expiresIn: number; subject: any },
  arg3: void
) {
  throw new Error('Function not implemented.');
}
