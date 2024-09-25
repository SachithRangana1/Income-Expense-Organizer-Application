import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class IncomeService {

  private BASE_URL = "http://localhost:8081/app/income";

  constructor(private http: HttpClient) { }

  postIncome(incomeDto:any):Observable<any>{
    return this.http.post(this.BASE_URL, incomeDto);
  }

  getAllIncomes():Observable<any>{
    return this.http.get(this.BASE_URL + "/all");
  }

  getIncomeById(id:number):Observable<any>{
    return this.http.get(this.BASE_URL + `/${id}`)
  }

  deleteIncome(id:any):Observable<any>{
    return this.http.delete(this.BASE_URL + `/${id}`)
      .pipe(catchError(error=>{
      return throwError(error)
    }))
  }

  updateIncome(id:number, incomeDto:any):Observable<any>{
    return this.http.put(this.BASE_URL + `/${id}`, incomeDto)
  }
}
