import { Person } from './person/person';

export class Booking {  
  id: number;
  hospede: Person = new Person();
  dataEntrada: Date;
  dataSaida: Date;
  adicionalVeiculo: boolean;
  preco: number;
  precoTotal: number;
}