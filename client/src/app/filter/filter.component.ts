import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';


@Component({
  selector: 'app-filter',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './filter.component.html',
  styleUrl: './filter.component.css'
})
export class FilterComponent {

  languages: string[] = ['Java', 'JavaScript', 'TypeScript', 'Python', 'C++', 'C#', 'C', 'PHP', 'Ruby', 'Swift', 'Go', 'Kotlin', 'Rust'];


}
