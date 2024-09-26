import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class StatsService {

  private BASE_URL = "http://localhost:8081/app/stat"

  constructor(private http: HttpClient) { }

  getStats():Observable<any>{
    return this.http.get(this.BASE_URL);
  }

  getChart():Observable<any>{
    return this.http.get(this.BASE_URL + "/chart");
  }
}
