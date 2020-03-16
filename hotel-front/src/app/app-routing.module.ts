import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BookingsComponent } from './booking/bookings.component';

const routes: Routes = [
  { path: 'bookings', component: BookingsComponent },
  { path: '', pathMatch: 'full', redirectTo: 'bookings'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
