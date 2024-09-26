import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService} from "ng-zorro-antd/message";
import {ActivatedRoute, Router} from "@angular/router";
import {IncomeService} from "../../services/incomeService/income.service";

@Component({
  selector: 'app-update-income',
  templateUrl: './update-income.component.html',
  styleUrl: './update-income.component.css'
})
export class UpdateIncomeComponent implements OnInit{

  id: number;


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
    this.incomeFrm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      category: [null, Validators.required],
      date: ['', Validators.required],
      amount: ['', Validators.required]
    });
    this.getIncomeById();
  }

  getIncomeById(){
    this.incomeService.getIncomeById(this.id).subscribe({
      next: (response)=>{
        this.incomeFrm.patchValue(response)
      },error: (err)=>{
        this.message.error("Failed while updating income", {nzDuration:2000});
        console.log("The error", err)
      }
    })
  }

  submitForm(){
    this.incomeService.updateIncome(this.id, this.incomeFrm.value).subscribe({
      next: (res)=>{
        this.message.success("Successfully submitted", {nzDuration:2000});
        this.router.navigateByUrl("/income");
      },error:(error)=>{
        this.message.error("Failed while submitting", {nzDuration:2000});
        console.log("Submitted failed", error);
      }
    })
  }
}
