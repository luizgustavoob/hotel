import { Component, OnInit } from '@angular/core';
import { Booking } from './booking';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PersonComponent } from './person/person.component';
import { Page } from './page';
import { BookingService } from './booking.service';
import { Subject, BehaviorSubject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';

@Component({
  selector: 'hotel-bookings',
  templateUrl: './bookings.component.html'
})
export class BookingsComponent implements OnInit {

  bookings: Page<Booking> = Object.assign({});
  page: number = 0;
  size: number = 3;
  checkOutNull: string = 'A';
  
  private _success = new Subject<string>();
  message: string;

  constructor(private modalService: NgbModal,
              private bookingService: BookingService) { }

  ngOnInit() {
    this._success.subscribe(msg => this.message = msg);
    this._success.pipe(debounceTime(3500)).subscribe(() => this.message = null);
    this.loadBookings();  
  }

  private loadBookings() {
    this.bookingService.findAllPaginated(this.page, this.size, this.checkOutNull).subscribe(
      res => this.bookings = res
    );
  }

  addPerson() {
    const modalRef = this.modalService.open(PersonComponent);    
    modalRef.result.then(
      () => this._success.next('Pessoa salva com sucesso!'),
      () => console.log('Fechou sem salvar'));
  }

  onSaveBooking(event) {
    this._success.next('Reserva salva com sucesso!')
    this.loadBookings();    
  }

  onChangedPage(event) {
    this.page = event.page;
    this.size = event.size;
    this.loadBookings();  
  }

  onFilter(event) {
    this.checkOutNull = event.checkOutNull ? 'S' : 'N';
    this.loadBookings();
  }
}