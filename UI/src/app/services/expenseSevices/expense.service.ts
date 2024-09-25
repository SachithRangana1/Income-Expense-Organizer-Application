import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {catchError, Observable, throwError} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ExpenseserviceService {

  private BASE_URL= "http://localhost:8081/app/expense";

  constructor(private http: HttpClient) { }

  postExpense(expenseDto:any):Observable<any>{
    return this.http.post( this.BASE_URL, expenseDto);
  }

  getAllExpenses():Observable<any>{
    return this.http.get( this.BASE_URL + "/all");
  }

  deleteExpense(id: any): Observable<any> {
    return this.http.delete(this.BASE_URL + `/${id}`).pipe(
    catchError(error=>{
      return throwError(error)
    })
    );
  }

  getExpenseById(id: number):Observable<any>{
    return this.http.get( this.BASE_URL + `/${id}`);
  }

  updateExpense(id: number, expenseDto: any):Observable<any>{
    return this.http.put( this.BASE_URL + `/${id}`, expenseDto);
  }

}
