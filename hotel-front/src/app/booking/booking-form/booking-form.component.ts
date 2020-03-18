import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap, catchError } from 'rxjs/operators';
import { Booking } from '../booking';
import { Person } from '../person/person';
import { PersonService } from '../person/person.service';
import { BookingService } from '../booking.service';
import { PriceCalculator } from '../price-calculator';

@Component({
  selector: 'hotel-booking-form',
  templateUrl: './booking-form.component.html'
})
export class BookingFormComponent implements OnInit {

  formBooking: FormGroup;
  people: Person[] = [];
  @Output() onSave = new EventEmitter;

  constructor(private formBuilder: FormBuilder, 
              private personService: PersonService,
              private bookingService: BookingService) { }

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
      debounceTime(500),
      distinctUntilChanged(),
      switchMap(term => !term ? [] :this.personService.filter(term)),
      catchError(() => of([]))
    )

  resultFormatter = (result: Person): string => {return result.nome};

  inputFormatter = (item: Person) => item.nome;
  
  save() {
    const booking = this.formBooking.getRawValue() as Booking;    
    this.bookingService.insert(booking).subscribe(
      res => {        
        this.onSave.emit({'booking': res});        
        this.formBooking.setValue({
          dataEntrada: '',
          dataSaida: '',
          hospede: '',
          adicionalVeiculo: false
        })
      }
    );    
  }

  getPrice() {
    return PriceCalculator.calculateBookingPrice(this.formBooking.get('dataEntrada')?.value,
                                                 this.formBooking.get('dataSaida')?.value, 
                                                 this.formBooking.get('adicionalVeiculo')?.value);
  }
}