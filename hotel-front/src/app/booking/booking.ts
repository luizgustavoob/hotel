import { Person } from './person/person';

export class Booking {
  hospede: Person = new Person();
  dataEntrada: Date;
  dataSaida: Date;
  adicionalVeiculo: boolean;
  preco: number;
  precoTotal: number;

  constructor() {
    this.preco = 0;
    this.precoTotal = 0;
  }
}