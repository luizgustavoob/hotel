import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'hotel-booking-error',
  templateUrl: './message-error.component.html'
})
export class MessageErrorComponent {

  @Input() error: string;
  @Input() text: string;
  @Input() control: FormControl;

  hasError(): boolean {
    return this.control.dirty && this.control.hasError(this.error);
  }
}