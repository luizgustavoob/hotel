import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Person } from './person';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'hotel-person',
  templateUrl: './person.component.html'
})
export class PersonComponent implements OnInit {

  formPerson: FormGroup;

  constructor(private formBuilder: FormBuilder, 
              public activeModal: NgbActiveModal) { }

  ngOnInit() {
    this.formPerson = this.formBuilder.group({
      nome: ['', Validators.required],
      documento: ['', Validators.required],
      telefone: ['', Validators.required]
    });
  }

  save() {
    const person = this.formPerson.getRawValue() as Person;
    this.formPerson.reset();
    this.activeModal.close(person);
  }
}