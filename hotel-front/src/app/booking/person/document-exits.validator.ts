import { Injectable } from '@angular/core';
import { PersonService } from './person.service';
import { AbstractControl } from '@angular/forms';
import { debounceTime, switchMap, map, first } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DocumentExistsValidator {

  constructor(private personService: PersonService) { }

  validate() {
    return (control: AbstractControl) => {
      return control
          .valueChanges
          .pipe(debounceTime(500))
          .pipe(switchMap(document => this.personService.findByDocument(document)))
          .pipe(map(existe => existe ? { existe: true } : null))
          .pipe(first());
    };
  }
}