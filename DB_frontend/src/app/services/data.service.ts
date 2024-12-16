import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaction, Category, HSRStation } from '../models/data-item';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  // Transaction 相关操作
  getAllTransactions(): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(`${this.apiUrl}/transactions`);
  }

  getTransactionById(id: string): Observable<Transaction> {
    return this.http.get<Transaction>(`${this.apiUrl}/transactions/${id}`);
  }

  addTransaction(transaction: Transaction): Observable<Transaction> {
    return this.http.post<Transaction>(`${this.apiUrl}/transactions`, transaction);
  }

  updateTransaction(id: string, transaction: Transaction): Observable<Transaction> {
    return this.http.put<Transaction>(`${this.apiUrl}/transactions/${id}`, transaction);
  }

  deleteTransaction(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/transactions/${id}`);
  }

  // Category 相关操作
  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.apiUrl}/categories`);
  }

  addCategory(category: Category): Observable<Category> {
    return this.http.post<Category>(`${this.apiUrl}/categories`, category);
  }

  updateCategory(id: string, category: Category): Observable<Category> {
    return this.http.put<Category>(`${this.apiUrl}/categories/${id}`, category);
  }

  deleteCategory(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/categories/${id}`);
  }

  // HSR Station 相关操作
  getAllHSRStations(): Observable<HSRStation[]> {
    return this.http.get<HSRStation[]>(`${this.apiUrl}/hsr`);
  }

  addHSRStation(station: HSRStation): Observable<HSRStation> {
    return this.http.post<HSRStation>(`${this.apiUrl}/hsr`, station);
  }

  updateHSRStation(id: string, station: HSRStation): Observable<HSRStation> {
    return this.http.put<HSRStation>(`${this.apiUrl}/hsr/${id}`, station);
  }

  deleteHSRStation(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/hsr/${id}`);
  }
}
