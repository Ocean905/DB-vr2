import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { DataService } from '../../services/data.service';

@Component({
  selector: 'app-data-management',
  templateUrl: './data-management.component.html',
  styleUrls: ['./data-management.component.css'],
  imports: [CommonModule, FormsModule],
  standalone: true
})
export class DataManagementComponent implements OnInit {
  selectedTable: string = 'invoice_transactions';
  transactions: any[] = [];
  categories: any[] = [];
  hsrStations: any[] = [];
  
  showModal: boolean = false;
  showDeleteConfirm: boolean = false;
  errorMessage: string = '';
  
  editingItem: any = null;
  editingTransaction: any = {};
  editingCategory: any = {};
  editingHSR: any = {};
  deletingItemId: string = '';

  @ViewChild('form') form!: NgForm;

  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.loadItems();
    this.dataService.getAllCategories().subscribe({
      next: (data) => this.categories = data,
      error: (error) => this.showError('載入類別失敗')
    });
  }

  selectTable(table: string) {
    this.selectedTable = table;
    this.loadItems();
  }

  loadItems() {
    switch(this.selectedTable) {
      case 'invoice_transactions':
        this.dataService.getAllTransactions().subscribe({
          next: (data) => this.transactions = data,
          error: (error) => this.showError('載入品項失敗')
        });
        break;
      case 'invoice_transtions_categories':
        this.dataService.getAllCategories().subscribe({
          next: (data) => this.categories = data,
          error: (error) => this.showError('載入類別失敗')
        });
        break;
      case 'HSR':
        this.dataService.getAllHSRStations().subscribe({
          next: (data) => this.hsrStations = data,
          error: (error) => this.showError('載入高鐵站別失敗')
        });
        break;
    }
  }

  editItem(item: any) {
    this.editingItem = item;
    switch(this.selectedTable) {
      case 'invoice_transactions':
        this.editingTransaction = {...item};
        break;
      case 'invoice_transtions_categories':
        this.editingCategory = {...item};
        break;
      case 'HSR':
        this.editingHSR = {...item};
        break;
    }
    this.showModal = true;
  }

  deleteItem(id: string) {
    switch(this.selectedTable) {
      case 'invoice_transactions':
        this.dataService.deleteTransaction(id).subscribe({
          next: () => this.loadItems(),
          error: (error) => this.showError('刪除失敗')
        });
        break;
      case 'invoice_transtions_categories':
        this.dataService.deleteCategory(id).subscribe({
          next: () => this.loadItems(),
          error: (error) => this.showError('刪除失敗')
        });
        break;
      case 'HSR':
        this.dataService.deleteHSRStation(id).subscribe({
          next: () => this.loadItems(),
          error: (error) => this.showError('刪除失敗')
        });
        break;
    }
  }

  saveItem() {
    if (!this.form.valid) {
      Object.keys(this.form.controls).forEach(key => {
        const control = this.form.controls[key];
        control.markAsTouched();
      });
      return;
    }

    switch(this.selectedTable) {
      case 'invoice_transactions':
        const transactionOp = this.editingItem ? 
          this.dataService.updateTransaction(this.editingItem.id, this.editingTransaction) :
          this.dataService.addTransaction(this.editingTransaction);
        
        transactionOp.subscribe({
          next: () => {
            this.loadItems();
            this.showModal = false;
          },
          error: (error) => this.showError('保存失敗')
        });
        break;

      case 'invoice_transtions_categories':
        const categoryOp = this.editingItem ?
          this.dataService.updateCategory(this.editingItem.id, this.editingCategory) :
          this.dataService.addCategory(this.editingCategory);
        
        categoryOp.subscribe({
          next: () => {
            this.loadItems();
            this.showModal = false;
          },
          error: (error) => this.showError('保存失敗')
        });
        break;

      case 'HSR':
        const hsrOp = this.editingItem ?
          this.dataService.updateHSRStation(this.editingItem.id, this.editingHSR) :
          this.dataService.addHSRStation(this.editingHSR);
        
        hsrOp.subscribe({
          next: () => {
            this.loadItems();
            this.showModal = false;
          },
          error: (error) => this.showError('保存失敗')
        });
        break;
    }
  }

  showError(message: string) {
    this.errorMessage = message;
    setTimeout(() => {
      this.errorMessage = '';
    }, 3000);
  }
}
