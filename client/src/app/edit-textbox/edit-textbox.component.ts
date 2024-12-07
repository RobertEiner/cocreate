import { CommonModule } from '@angular/common';
import { Component, Input, Output, ViewChild, EventEmitter } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { TextToEdit} from '../interfaces/textToEdit';



@Component({
  selector: 'app-edit-textbox',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './edit-textbox.component.html',
  styleUrl: './edit-textbox.component.css'
})

export class EditTextboxComponent {
  @ViewChild('editTextForm') form: NgForm = new NgForm([], []);
  @Input() currItemToEdit: string = '';
  @Input() textContent: string = '';
  @Output() cancelEditTextEmitter: EventEmitter<string> = new EventEmitter<string>();
  @Output() editTextEmitter: EventEmitter<TextToEdit> = new EventEmitter<TextToEdit>();


  editText(editedItem: string) {
    const textToEmit: TextToEdit = {
      type: editedItem,
      newContent: this.textContent
    };
    
    this.editTextEmitter.emit(textToEmit);

  }

  cancelEditText() {
    console.log('Cancel');
    this.cancelEditTextEmitter.emit('cancel');
  }



}
