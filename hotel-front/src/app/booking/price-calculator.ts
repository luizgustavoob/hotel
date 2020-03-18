import * as moment from 'moment';

export class PriceCalculator {
  
  private static NORMALPRICE: number = 120.0;
  private static WEEKENDPRICE: number = 150.0;
  private static NORMALPARKINGPRICE: number = 15.0;
  private static WEEKENDPARKINGPRICE: number = 20.0;
  
  static calculateBookingPrice(checkIn: Date, checkOut: Date, useParking: boolean): number {
    const dateCheckout = moment(checkOut, 'YYYY-MM-DD');
    let date = moment(checkIn, 'YYYY-MM-DD');
    
    let price = 0;    
    while (date.isBefore(dateCheckout)) {      
      price += this.calculatePriceOfDate(date, useParking);
      date = moment(date).add(1, 'days');
    }
    
    if (this.newDaily(checkOut)) {
      price += this.calculatePriceOfDate(moment(date).add(1, 'days'), useParking);      
    }
    
    return price;
  }

  private static calculatePriceOfDate(data: moment.Moment, adicionalVeiculo: boolean): number {
    let price = 0;
    price += this.isWeekend(data) ? this.WEEKENDPRICE : this.NORMALPRICE;
    if (adicionalVeiculo) {
      price += this.isWeekend(data) ? this.WEEKENDPARKINGPRICE : this.NORMALPARKINGPRICE;
    }
    return price;
  } 

  private static isWeekend(data: moment.Moment): boolean {
    return data.weekday() === 0 || data.weekday() === 6;
  }

  private static newDaily(checkOut: Date) {
    const dia = parseInt(moment(checkOut).format('DD'));
    const mes = parseInt(moment(checkOut).format('MM')) - 1;
    const ano = parseInt(moment(checkOut).format('YYYY'));

    const horaLimite = moment(new Date(ano, mes, dia, 16, 30));
    const horaCheckout = moment(checkOut);

    return horaCheckout.isAfter(horaLimite);
  }
}