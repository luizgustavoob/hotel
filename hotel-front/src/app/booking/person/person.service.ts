import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Person } from './person';
import { Page } from '../page';

let url = `${environment.api}/people`;

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor(private http: HttpClient) { }

  insert(person: Person) {
    return this.http.post<Person>(url, person);
  }

  update(idPerson: number, person: Person) {
    return this.http.put<Person>(`${url}/${idPerson}`, person);
  }

  findAllPaginated(page: number, size: number) {
    let params = new HttpParams();
    params = params.append('page', page.toString());
    params = params.append('size', size.toString());

    return this.http.get<Page<Person>>(`${url}/page`, {params});
  }

  findAll() {
    return this.http.get<Person[]>(url);
  }

  findById(idPerson: number) {
    return this.http.get<Person>(`${url}/${idPerson}`);
  }

  delete(idPerson: number) {
    return this.http.delete(`${url}/${idPerson}`);
  }

  filter(param: string) {
    let params = new HttpParams();
    params = params.append('param', param);
    return this.http.get<Person[]>(`${url}/filter`, {params});
  }

  filterByBookingStatus(checkOutNull: boolean, page: number, size: number) {
    let params = new HttpParams();
    params = params.append('checkOutNull', checkOutNull ? 'S' : 'N');
    params = params.append('page', page.toString());
    params = params.append('size', size.toString());
    return this.http.get<Page<Person>>(`${url}/filterByBookingStatus`, {params});    
  }

  findByDocument(document: string) {
    let params = new HttpParams();
    params = params.append('param', document);
    return this.http.get<boolean>(`${url}/filterByDocument`, {params});
  }

}