import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Application, ApplicationUpload } from '../models/application';
import { ApplicationGeoSearch, ApplicationSearch, ApplicationSearchResult } from '../models/application-search';

@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API = `${environment.apiUrl}/applications`;

  read() {
    return this.http.get<Application[]>(this.API);
  }

  upload(upload: ApplicationUpload) {
    const data = new FormData();
    for (const field in upload) {
      data.append(field, upload[field]);
    }
    return this.http.post<Application>(this.API, data);
  }

  search(search: ApplicationSearch) {
    return this.http.post<ApplicationSearchResult[]>(`${this.API}/search`, search);
  }

  geoSearch(search: ApplicationGeoSearch) {
    return this.http.post<ApplicationSearchResult[]>(`${this.API}/geo_search`, search);
  }

  download(cv: boolean, fileName: string) {
    return this.http.get<Blob>(`${this.API}/${cv ? 'cv' : 'letter'}/${fileName}`, { responseType: 'blob' as 'json' });
  }

  announceFormAccess() {
    return this.http.get<void>(`${this.API}/form_access`);
  }

}
