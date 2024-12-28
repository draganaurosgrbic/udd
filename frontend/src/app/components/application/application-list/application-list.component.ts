import { Component, OnInit } from '@angular/core';
import { Application } from 'src/app/models/application';
import { ApplicationService } from 'src/app/services/application.service';

@Component({
  selector: 'app-application-list',
  templateUrl: './application-list.component.html',
  styleUrls: ['./application-list.component.scss']
})
export class ApplicationListComponent implements OnInit {

  constructor(
    private applicationService: ApplicationService
  ) { }

  applications: Application[]

  ngOnInit() {
    this.read();
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

  private async read() {
    this.applications = await this.applicationService.read().toPromise();
  }

}
