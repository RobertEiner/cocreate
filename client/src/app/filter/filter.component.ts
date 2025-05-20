import { CommonModule } from '@angular/common';
import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';



@Component({
  selector: 'app-filter',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './filter.component.html',
  styleUrl: './filter.component.css'
})
export class FilterComponent implements OnInit {

  languages: string[] = ['Java', 'JavaScript', 'TypeScript', 'Python', 'C++', 'C#', 'C', 'PHP', 'Ruby', 'Swift', 'Go', 'Kotlin', 'Rust'];
  @Output() postFilterEmitter: EventEmitter<{[key: string] : boolean}> = new EventEmitter<{[key: string] : boolean}>();
  @Output() refetchAllPostsEmitter: EventEmitter<string> = new EventEmitter<string>();



  languagesTicked: { [key: string]: boolean } = {};

  ngOnInit(): void {
    this.languages.forEach((lang) => {
      this.languagesTicked[lang] = false;
    });
  }

  showResults() {
    const isAnyLanguageTicked = Object.values(this.languagesTicked).some(value => value === true);
    if(isAnyLanguageTicked) { 
      this.postFilterEmitter.emit(this.languagesTicked);
    } else {
      this.refetchAllPostsEmitter.emit('refetch');
      console.log('sfsdfsdfs')
    }
  }

}
