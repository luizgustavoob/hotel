import { Component, Input, OnChanges, SimpleChanges, ViewChild } from '@angular/core';
import { Booking } from '../booking';
import { faLongArrowAltRight } from '@fortawesome/free-solid-svg-icons';
import { faLongArrowAltLeft } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'hotel-booking-list',
  templateUrl: './booking-list.component.html'
})
export class BookingListComponent implements OnChanges {

  faIconPrevious = faLongArrowAltLeft;
  faIconNext = faLongArrowAltRight;
  @Input() bookings: Booking[] = [];
  bookingsClone: Booking[] = [];
  bookingsFilter: Booking[] = [];

  private page: number = 0;
  private maxRecords = 3;

  ngOnChanges(changes: SimpleChanges) {
    this.bookingsClone = [...this.bookings];
    this.bookingsFilter = [...this.bookingsClone];
    this.applyPagination();
  }

  filter(checkOutNull) {
    if (!this.bookings.length) {
      return;
    }
    
    this.bookingsClone = [...this.bookings];
    this.bookingsClone = this.bookingsClone.filter(b => checkOutNull ? !b.dataSaida : b.dataSaida);
    this.bookingsFilter = [...this.bookingsClone];
    this.applyPagination();
  }  

  applyPagination() {
    let bookingsAux = [...this.bookingsFilter];
    this.bookingsClone = [];
    let maxRecordsPagination = this.page === 0 ? this.maxRecords : this.maxRecords + 3;
    this.bookingsClone = [...bookingsAux.slice(this.page, maxRecordsPagination)];
  }

  next() {
    if (!this.bookingsClone.length) {
      return;
    }
    this.page += 3;
    this.applyPagination(); 
  }

  previous() {
    if (this.page === 0) {
      return;
    }
    this.page -= 3;
    this.applyPagination();
  }

  disableNext() {    
    return this.page + this.maxRecords >= this.bookings.length;
  }

  disablePrevious() {
    return this.page === 0;
  }

  getTitleTooltip(valorTotal) {
    return `Valor total já gasto pelo hóspede: R$${valorTotal.toFixed(2)}`;
  }
}