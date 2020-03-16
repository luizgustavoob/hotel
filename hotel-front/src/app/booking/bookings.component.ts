import { Component } from '@angular/core';
import { Booking } from './booking';
import { Person } from './person/person';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PersonComponent } from './person/person.component';

@Component({
  selector: 'hotel-bookings',
  templateUrl: './bookings.component.html'
})
export class BookingsComponent {

  people: Person[] = [];
  bookings: Booking[] = [];

  constructor(private modalService: NgbModal) { }

  addPerson() {
    const modalRef = this.modalService.open(PersonComponent);    
    modalRef.result.then(
      result => this.people = this.people.concat(result), 
      reason => console.log('Fechou sem salvar', reason));
  }

  onSaveBooking(event) {
    const booking = event.booking as Booking;
    
    let total = this.bookings.filter(b => b.hospede.nome === booking.hospede.nome).reduce((total, bookingCurrent) => {
      return total + bookingCurrent.preco;
    }, booking.preco);

    this.bookings = this.bookings.concat(booking);

    this.bookings.map(b => {
      if (b.hospede.nome === booking.hospede.nome) {
        b.precoTotal = total;
      }
    });
  }
}