import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService} from "ng-zorro-antd/message";
import {ActivatedRoute, Router} from "@angular/router";
import {IncomeService} from "../../services/incomeService/income.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-income',
  templateUrl: './income.component.html',
  styleUrl: './income.component.css'
})
export class IncomeComponent implements OnInit{

  id: number;
  incomes:any;
  loading:boolean = false;

  incomeFrm!: FormGroup;
  listOfCategory:any[] = [
    "Monthly Salary",
    "Freelancing",
    "Investment",
    "Stocks",
    "Bitcoin",
    "Bank Transfer",
    "YouTube",
    "Drug Deal",
    "Business",
    "Property Sale",
    "Black Money",
    "Other"
  ];

  constructor(private fb: FormBuilder, private message: NzMessageService,
              private router: Router, private incomeService: IncomeService
              , private activatedRoute: ActivatedRoute) {
    this.id = this.activatedRoute.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.getAllIncomes();
    this.incomeFrm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      category: [null, Validators.required],
      date: ['', Validators.required],
      amount: ['', Validators.required]
    })
  }

  submitForm() {
    if (this.incomeFrm.valid) {
      this.loading = true;
      this.incomeService.postIncome(this.incomeFrm.value).subscribe({
        next: (res) => {
          // this.getAllIncomes();
          this.incomes.push(res);
          this.message.success("Income added successfully", {nzDuration: 1000})
          this.incomeFrm.reset();
        }, error: () => this.message.error("Failed while adding income", {nzDuration: 1000}),
           complete: () => this.loading = false
      })
    }
  }

  getAllIncomes(){
    this.incomeService.getAllIncomes().subscribe({
      next: (res)=>{
        this.incomes = res;
      }, error: ()=>{
        this.message.error("Fetch Unsuccessfully")
      }
    })
  }

  deleteIncome(id: any){
    this.loading = true;
    this.incomeService.deleteIncome(id).subscribe({
      next: (response:any)=>{
        console.log("Deleted by delete API", response);
        if (response && response.success){
          this.incomes = this.incomes.filter((expense:any)=> expense.id !== id);
          this.message.success("Successfully income deleted")
        }else{
          this.message.error("Deletion failed, please try again");
        }
      },error: (err)=> {
        this.message.error("Failed deletion of income");
        console.log("Deletion failed", err);
      },
      complete: ()=> this.loading = false
    })
  }

  updateIncome(id:number){
    this.router.navigateByUrl(`/income/${id}/edit`);
  }
}
