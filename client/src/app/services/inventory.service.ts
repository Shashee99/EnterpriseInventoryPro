import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class InventoryService {
  private baseURL = "http://localhost:8087/inventories";
  constructor(private httpClient: HttpClient) {}

  getAllinv():Observable<any>{

    const params = new HttpParams()
      .set('operation', 'GETALL')
      .set('offset', '0')
      .set('pageSize', '20');

    return this.httpClient.get('http://localhost:8087/inventories', { params: params });
  }
  getMorewithoutSearch(pageNumber:number):Observable<any>{

    const params = new HttpParams()
      .set('operation', 'GETALL')
      .set('offset', `${pageNumber}`)
      .set('pageSize', '20');
    console.log(params)
    return this.httpClient.get('http://localhost:8087/inventories', { params: params });
  }

  searchInventories(
    brand?: string[],
    types?: string[],
    desc?: string,
    pageNumber: number = 0
  ): Observable<any> {
    const params = new HttpParams()
      .set('operation', 'SEARCH')
      .set('brands', brand ? brand.join(',') : '') // Convert array to a comma-separated string if provided, otherwise use an empty string
      .set('typelist', types ? types.join(',') : '')
      .set('description', desc || '') // Use desc if provided, otherwise use an empty string
      .set('offset', `${pageNumber}`)
      .set('pageSize', '20');
    console.log(params);
    return this.httpClient.get('http://localhost:8087/inventories', { params: params });
  }

  deleteInventory(id: number): Observable<any> {
    // Construct the URL with the provided ID
    const url = `${this.baseURL}?ids=${id}`;

    // Set up the headers if needed
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
      // Add any other headers as needed
    });

    // Send the DELETE request
    return this.httpClient.delete(url, { headers });
  }

  addInventory(data:any):Observable<any>{
    return this.httpClient.post(this.baseURL,data)
  }
  getInventoryById(id:number):Observable<any>{
    let url :string = `${this.baseURL}/${id}`
    return this.httpClient.get(url)
  }

  updateInventory(data:any){
    console.log("indse")
    console.log(data)
    return this.httpClient.put(this.baseURL,data)
  }

}
