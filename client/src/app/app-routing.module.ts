import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UrlComponent } from './modules/url/url.component';
import { RouteConstants } from './shared/route-constants.enum';


const routes: Routes = [
  {
    path: RouteConstants.HOME,
    component: UrlComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
