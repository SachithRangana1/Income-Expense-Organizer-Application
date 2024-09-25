import {Component, ElementRef, ViewChild} from '@angular/core';
import {StatsService} from "../../services/stats/stats.service";
import Chart from 'chart.js/auto';
import {CategoryScale} from 'chart.js';

Chart.register(CategoryScale);
interface Stats {
  income: number;
  expense: number;
  latestIncome?: { amount: number, title: string };
  latestExpense?: { amount: number, title: string };
  balance: number;
  minIncome: number;
  maxIncome: number;
  minExpense: number;
  maxExpense: number;
}
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  stats: Stats | null = null;
  incomes: any[] = [];
  expenses: any[] = [];

  gridStyle = {
    width: '25%',
    textAlign: 'center'
  };

  @ViewChild("incomeLineChartRef")
  private incomeLineChartRef:ElementRef;

  @ViewChild("expenseLineChartRef")
  private expenseLineChartRef:ElementRef;

  constructor(private statService: StatsService) {
    this.getStats();
    this.getChartData();
  }

  createLineChart(){
    const incomeCtx = this.incomeLineChartRef.nativeElement.getContext("2d");

    new Chart(incomeCtx, {
      type: 'line',
      data: {
        labels: this.incomes.map(income => income.date),
        datasets: [{
          label: 'income',
          data: this.incomes.map(income => income.amount),
          borderWidth: 1,
          backgroundColor: 'rgb(80, 200, 120)',
          borderColor: 'rgb(0, 100, 0)'
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });

    const expenseCtx = this.expenseLineChartRef.nativeElement.getContext("2d");

    new Chart(expenseCtx, {
      type: 'line',
      data: {
        labels: this.expenses.map(income => income.date),
        datasets: [{
          label: 'expense',
          data: this.expenses.map(income => income.amount),
          borderWidth: 1,
          backgroundColor: 'rgb(255, 0, 0)',
          borderColor: 'rgb(255, 0, 0)'
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }

  getStats() {
    this.statService.getStats().subscribe({
      next: (res: Stats) => {
        console.log(res);
        this.stats = res;
      },
      error: (err) => {
        console.error("Error fetching stats", err);
      }
    });
  }

  getChartData() {
    this.statService.getChart().subscribe({
      next: (res) => {
        if (res.expenseList != null && res.incomeList != null) {
          this.incomes = res.incomeList;
          this.expenses = res.expenseList;
          console.log(res);

          this.createLineChart()
        }
      },
      error: (err) => {
        console.error("Error fetching chart data", err);
      }
    });
  }
}
