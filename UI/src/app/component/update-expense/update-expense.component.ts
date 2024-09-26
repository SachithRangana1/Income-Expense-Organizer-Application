import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ExpenseserviceService} from "../../services/expenseSevices/expense.service";
import {NzMessageService} from "ng-zorro-antd/message";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-update-expense',
  templateUrl: './update-expense.component.html',
  styleUrl: './update-expense.component.css'
})
export class UpdateExpenseComponent implements OnInit{

  expenses:any;
  expenseFrm !: FormGroup;
  id: number;

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
  constructor(private router: Router, private expenseService: ExpenseserviceService, private message: NzMessageService, private fb: FormBuilder, private activatedRoute: ActivatedRoute) {
    this.id = this.activatedRoute.snapshot.params['id'];
  }
  ngOnInit(){
    this.expenseFrm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      category: [null, Validators.required],
      date: ['', Validators.required],
      amount: ['', Validators.required]
    });
    this.getExpenseById();
  }

  getExpenseById(){
    this.expenseService.getExpenseById(this.id).subscribe({
      next: (res)=>{
      this.expenseFrm.patchValue(res);
    },error: () =>{
      this.message.error("Something went error", {nzDuration: 500})
    }
  })
}

  submitForm() {
    this.expenseService.updateExpense(this.id, this.expenseFrm.value).subscribe({
      next: (res)=>{
        this.message.success("Expense updated successfully", {nzDuration:2000});
        this.router.navigateByUrl("/expense");
      }, error: ()=> this.message.error("Failed while updating expense", {nzDuration: 2000})
    })
  }
}
