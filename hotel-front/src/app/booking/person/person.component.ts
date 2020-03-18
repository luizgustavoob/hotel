import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Person } from './person';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { PersonService } from './person.service';
import { DocumentExistsValidator } from './document-exits.validator';

@Component({
  selector: 'hotel-person',
  templateUrl: './person.component.html'
})
export class PersonComponent implements OnInit {

  formPerson: FormGroup;

  constructor(private formBuilder: FormBuilder, 
              public activeModal: NgbActiveModal,
              private personService: PersonService,
              private documentExistsValidator: DocumentExistsValidator) { }

  ngOnInit() {
    this.formPerson = this.formBuilder.group({
      nome: ['', Validators.required],
      documento: ['', Validators.required, this.documentExistsValidator.validate()],
      telefone: ['', Validators.required]
    });
  }

  save() {
    const person = this.formPerson.getRawValue() as Person;
    this.personService.insert(person).subscribe(
      res => {
        this.formPerson.reset();
        this.activeModal.close(res);
      }
    );    
  }
}