import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DataManagementComponent } from './components/data-management/data-management.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone: true,
  imports: [CommonModule, DataManagementComponent]
})
export class AppComponent {
  title = '碳排放管理系统';
}
