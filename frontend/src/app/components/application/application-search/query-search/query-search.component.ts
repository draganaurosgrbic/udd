import { Component, EventEmitter, Input, Output } from '@angular/core';
import { SimpleQuery } from 'src/app/models/application-search';

@Component({
  selector: 'app-query-search',
  templateUrl: './query-search.component.html',
  styleUrls: ['./query-search.component.scss']
})
export class QuerySearchComponent {

  @Input() query: SimpleQuery;
  @Input() first: boolean;
  @Output() removeQuery = new EventEmitter<void>();

  get educationLevel() {
    return this.query.field === 'educationLevel';
  }

}
