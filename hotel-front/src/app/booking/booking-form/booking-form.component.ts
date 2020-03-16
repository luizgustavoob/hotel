import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Booking } from '../booking';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { Person } from '../person/person';
import { PriceCalculator } from '../price-calculator';

@Component({
  selector: 'hotel-booking-form',
  templateUrl: './booking-form.component.html'
})
export class BookingFormComponent implements OnInit {

  formBooking: FormGroup;
  @Input() people: Person[] = [];
  @Output() onSave = new EventEmitter;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.formBooking = this.formBuilder.group({
      dataEntrada: ['', Validators.required],
      dataSaida: '',
      hospede: ['', Validators.required],
      adicionalVeiculo: false
    });
  }

  autoCompletePerson = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(200),
      distinctUntilChanged(),
      map(term => term === '' ? [] : this.people.filter(p =>
        p.nome.toLowerCase().indexOf(term.toLowerCase()) > -1 || p.documento.indexOf(term) > -1 || p.telefone.indexOf(term) > -1
      ))
    )

  resultFormatter = (result: any): string => {return result.nome};

  inputFormatter = (item: any) => item.nome;
  
  save() {
    const booking = this.formBooking.getRawValue() as Booking;    
    if (booking.dataSaida) {
      PriceCalculator.calculateBookingPrice(booking);
    }
    this.onSave.emit({booking});
    this.formBooking.reset();
  }
}