import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MainServiceService {
  url="10.113.169.136:8081/api/get?generatedUrl=https://localhost:7070/AjJ1nZV_";

  constructor(private httpClient: HttpClient) { }

  postData(data: any): Observable<any> {
    return this.httpClient.post('http://10.113.169.136:8081/api/postCreate', data);
  }

  getData(value): Observable<any> {
    return this.httpClient.get<any>('http://10.113.169.136:8081/api/get?generatedUrl=' + value);
  }
}
