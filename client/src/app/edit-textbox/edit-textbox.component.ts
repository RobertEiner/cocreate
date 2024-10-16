import { CommonModule } from '@angular/common';
import { Component, Input, Output, ViewChild, EventEmitter } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';



@Component({
  selector: 'app-edit-textbox',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './edit-textbox.component.html',
  styleUrl: './edit-textbox.component.css'
})
export class EditTextboxComponent {
  @ViewChild('editTextForm') form: NgForm = new NgForm([], []);
  @Input() currItemToEdit: string = "";
  @Output() cancelEditTextEmitter: EventEmitter<string> = new EventEmitter<string>();

  textContent: string = "";

  editText() {
    console.log('EDIT');
  }

  cancelEditText() {
    console.log('Cancel');
    this.cancelEditTextEmitter.emit('cancel');
  }



}
