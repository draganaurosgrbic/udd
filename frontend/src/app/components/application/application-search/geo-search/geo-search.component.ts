import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ApplicationGeoSearch } from 'src/app/models/application-search';
import { FormConfig, FormStyle } from 'src/app/utils/form';

@Component({
  selector: 'app-geo-search',
  template: `<app-form title="Geo Search" [config]="config" [pending]="pending" [style]="style" (submit)="search.emit($event)"></app-form>`
})
export class GeoSearchComponent {

  @Input() pending: boolean;
  @Output() search = new EventEmitter<ApplicationGeoSearch>();

  config: FormConfig = {
    location: {
      type: 'location',
      validation: 'required'
    },
    distance: {
      validation: 'positive-integer'
    },
    unit: {
      type: 'select',
      validation: 'required',
      options: ['km', 'm']
    }
  }
  style: FormStyle = {
    'margin-top': '50px'
  }

}
