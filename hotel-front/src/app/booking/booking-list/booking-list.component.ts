import { Component, Input, OnChanges, SimpleChanges, EventEmitter, Output } from '@angular/core';
import { faLongArrowAltRight } from '@fortawesome/free-solid-svg-icons';
import { faLongArrowAltLeft } from '@fortawesome/free-solid-svg-icons';
import { Booking } from '../booking';
import { Page } from '../page';

@Component({
  selector: 'hotel-booking-list',
  templateUrl: './booking-list.component.html'
})
export class BookingListComponent implements OnChanges {

  faIconPrevious = faLongArrowAltLeft;
  faIconNext = faLongArrowAltRight;  

  @Input() bookings: Page<Booking> = Object.assign({});
  @Output() onChangedPage = new EventEmitter();
  @Output() onFilter = new EventEmitter();
  private page: number = 0;
  private maxRecords = 3;
  private totalElements : number; 

  ngOnChanges(changes: SimpleChanges) {
    this.bookings = changes.bookings.currentValue;
    this.totalElements = this.bookings.totalElements;
  }

  notifyChange(page, size) {
    this.onChangedPage.emit({page, size});
  }

  next() {
    ++this.page;
    this.notifyChange(this.page, this.maxRecords);
  }

  previous() {
    --this.page;
    this.notifyChange(this.page, this.maxRecords);
  }

  disableNext() {
    return (this.page + 1) * this.maxRecords >= this.totalElements;
  }

  disablePrevious() {
    return !this.page;
  }

  filter(checkOutNull: boolean) {
    this.onFilter.emit({checkOutNull});
  }

  getTitleTooltip(precoTotal: number) {
    if (!precoTotal) {
      return "";
    }

    return `Valor total já gasto pelo hóspede: R$${precoTotal.toFixed(2)}`
  }
}