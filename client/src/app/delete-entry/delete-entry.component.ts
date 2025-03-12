import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-delete-entry',
  standalone: true,
  imports: [],
  templateUrl: './delete-entry.component.html',
  styleUrl: './delete-entry.component.css'
})
export class DeleteEntryComponent {

@Input() entryToDelete: string = '';
@Output() deleteEntryEmitter: EventEmitter<number> = new EventEmitter<number>();
@Output() cancelDeleteEmitter: EventEmitter<number> = new EventEmitter<number>();


deleteEntry() {
  this.deleteEntryEmitter.emit();
}

cancelDelete() {
  this.cancelDeleteEmitter.emit();
}

}
