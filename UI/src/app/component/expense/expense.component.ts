import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ExpenseserviceService } from '../../services/expenseSevices/expense.service';
import {Router} from "@angular/router";


@Component({
  selector: 'app-expense',
  templateUrl: './expense.component.html',
  styleUrl: './expense.component.css'
})
export class ExpenseComponent implements OnInit{

  expenses:any;
  expenseFrm !: FormGroup;
  loading: boolean = false;
  private BASE_URL= "http://localhost:8081/app/expense";


  listOfCategory: any[] = [
    "Education",
    "Health",
    "Groceries",
    "Shopping",
    "Travelling",
    "Gift",
    "SkinCare",
    "Donations",
    "Dating",
    "Foods",
    "Subscriptions",
    "Others"
  ];
  constructor(private router: Router, private expenseService: ExpenseserviceService, private message: NzMessageService, private fb: FormBuilder) {
  }
  ngOnInit(){
    this.getAllExpenses();
    this.expenseFrm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      category: [null, Validators.required],
      date: ['', Validators.required],
      amount: ['', Validators.required]
    })
  }

  submitForm() {
    if (this.expenseFrm.valid) {
      this.loading = true;
      this.expenseService.postExpense(this.expenseFrm.value).subscribe({
        next: (response) => {
          this.expenses.push(response);
          this.message.success("Expense added successfully");
          this.expenseFrm.reset();
        },
        error: (any) => this.message.error("Expense added failed"),
        complete: () => this.loading = false
      });
      console.log(this.expenseFrm.value);
    }
  }

  getAllExpenses(){
    this.expenseService.getAllExpenses().subscribe(res=>{
      this.expenses = res;
      console.log(this.expenses);
    });
  }

  updateExpense(id:number){
    this.router.navigateByUrl(`/expense/${id}/edit`)
  }

  deleteExpense(id: any) {
    this.loading = true;
    this.expenseService.deleteExpense(id).subscribe({
      next: (response) => {
        console.log("Response from delete API:", response);
        if (response && response.success) {
          this.expenses = this.expenses.filter((expense: any) => expense.id !== id);
          this.message.success("Expense deleted successfully");
        } else {
          this.message.error("Expense deletion failed. Please try again.");
        }
      },
      error: (err) => {
        console.error("Error occurred while deleting expense:", err);
        this.message.error("Expense deletion failed");
      },
      complete: () => this.loading = false
    });
  }
}

