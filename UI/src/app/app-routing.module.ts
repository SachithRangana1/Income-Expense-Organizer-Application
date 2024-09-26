import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExpenseComponent } from './component/expense/expense.component';
import {UpdateExpenseComponent} from "./component/update-expense/update-expense.component";
import {IncomeComponent} from "./component/income/income.component";
import {UpdateIncomeComponent} from "./component/update-income/update-income.component";
import {DashboardComponent} from "./component/dashboard/dashboard.component";

const routes: Routes = [
  {
    path: 'income',
    component: IncomeComponent
  },
  {
    path: 'expense',
    component: ExpenseComponent,
  },
  {
    path: "income/:id/edit",
    component: UpdateIncomeComponent
  },
  {
    path: "expense/:id/edit",
    component: UpdateExpenseComponent
  },
  {
    path: "",
    component: DashboardComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
