import { Component } from '@angular/core';
import { ApplicationGeoSearch, ApplicationSearchResult, SimpleQuery } from 'src/app/models/application-search';
import { ApplicationService } from 'src/app/services/application.service';

@Component({
  selector: 'app-application-search',
  templateUrl: './application-search.component.html',
  styleUrls: ['./application-search.component.scss']
})
export class ApplicationSearchComponent {

  constructor(
    private applicationService: ApplicationService
  ) { }

  searchPending = false;
  geoSearchPending = false;
  queries: SimpleQuery[] = [{ field: 'firstName', value: '', startValue: 1, endValue: 1, operator: 'AND', not: false }]
  searchResults: ApplicationSearchResult[];

  addQuery() {
    this.queries.push({ field: 'firstName', value: '', startValue: 1, endValue: 1, operator: 'AND', not: false })
  }

  removeQuery(index: number) {
    this.queries.splice(index, 1);
  }

  async search() {
    if (this.queries.find(query => query.field !== 'educationLevel' && !query.value.trim().length)) {
      return
    }
    this.searchPending = true;
    try {
      this.searchResults = await this.applicationService.search({ queries: this.queries }).toPromise();
      this.searchPending = false;
    } catch {
      this.searchPending = false;
    }
  }

  async geoSearch(search: ApplicationGeoSearch) {
    this.geoSearchPending = true;

    try {
      this.searchResults = (await this.applicationService.geoSearch(search).toPromise()).map(result => ({ ...result, address: `<em>${result.address}</em>` }));
      this.geoSearchPending = false;
    } catch {
      this.geoSearchPending = false;
    }
  }

  async downloadFile(cv: boolean, fileName: string) {
    const res = await this.applicationService.download(cv, fileName).toPromise();

    const url = window.URL.createObjectURL(res)
    const a = document.createElement('a')
    document.body.appendChild(a)
    a.setAttribute('style', 'display: none')
    a.href = url
    a.download = fileName
    a.click()
    window.URL.revokeObjectURL(url)
    a.remove()
  }

  highlightEducationLevel(result: ApplicationSearchResult) {
    if (this.queries.some(query => query.field === 'educationLevel' && result.educationLevel >= query.startValue && result.educationLevel <= query.endValue)) {
      return `<em>${result.educationLevel}</em>`
    }
    return result.educationLevel
  }

}
