import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Food } from 'src/app/shared/models/food';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss'],
})
export class TableComponent implements OnInit {
  foods?: Food[];
  displayedColumns: string[] = ['name', 'description', 'price'];
  dataSource: Food[];

  constructor(router: Router) {
    this.dataSource = router.getCurrentNavigation().extras.state['dataSource'];
    console.log(this.dataSource);
  }

  ngOnInit(): void {}
}
