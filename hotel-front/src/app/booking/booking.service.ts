import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Booking } from './booking';
import { Page } from './page';

let url = `${environment.api}/bookings`;

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  constructor(private http: HttpClient) { }

  insert(booking: Booking) {
    return this.http.post<Booking>(url, booking);
  }

  update(idBooking: number, booking: Booking) {
    return this.http.put<Booking>(`${url}/${idBooking}`, booking);
  }

  findAllPaginated(page: number, size: number, checkOutNull: string = '') {    
    let params = new HttpParams();
    checkOutNull = checkOutNull === '' ? 'A' : checkOutNull;
    params = params.append('checkOutNull', checkOutNull);
    params = params.append('page', page.toString());
    params = params.append('size', size.toString());

    return this.http.get<Page<Booking>>(`${url}/page`, {params});
  }

  findAll() {
    return this.http.get<Booking[]>(url);
  }

  findById(idBooking: number) {
    return this.http.get<Booking>(`${url}/${idBooking}`);
  }

  delete(idBooking: number) {
    return this.http.delete(`${url}/${idBooking}`);
  }

}