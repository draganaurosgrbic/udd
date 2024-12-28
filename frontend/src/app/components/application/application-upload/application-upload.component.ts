import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { ApplicationUpload } from 'src/app/models/application';
import { ApplicationService } from 'src/app/services/application.service';
import { FormConfig, FormStyle } from 'src/app/utils/form';
import { SNACKBAR_CLOSE_BUTTON, SNACKBAR_ERROR_CONFIG, SNACKBAR_ERROR_TEXT, SNACKBAR_SUCCESS_CONFIG, SNACKBAR_SUCCESS_TEXT } from 'src/app/utils/popup';
import { Route } from 'src/app/utils/route';

@Component({
  selector: 'app-application-upload',
  template: `<app-form title="Upload Application" [config]="config" [pending]="pending" [style]="style" (submit)="upload($event)"></app-form>`
})
export class ApplicationUploadComponent implements OnInit {

  constructor(
    private applicationService: ApplicationService,
    private router: Router,
    private route: ActivatedRoute,
    private snackbar: MatSnackBar,
  ) { }

  pending = false;
  config: FormConfig = {
    firstName: {
      validation: 'required'
    },
    lastName: {
      validation: 'required'
    },
    email: {
      validation: 'required'
    },
    address: {
      type: 'location',
      validation: 'required'
    },
    education: {
      validation: 'required'
    },
    educationLevel: {
      validation: 'required',
      type: 'select',
      options: ['1', '2', '3', '4', '5', '6', '7']
    },
    cvFile: {
      type: 'file',
      validation: 'required'
    },
    letterFile: {
      type: 'file',
      validation: 'required'
    }
  }
  style: FormStyle = {
    width: '550px',
    'margin-top': '50px'
  }

  ngOnInit() {
    this.applicationService.announceFormAccess().subscribe(() => { });
  }

  async upload(upload: ApplicationUpload) {
    upload.advertisementId = +this.route.snapshot.params[Route.ADVERTISEMENT_ID]
    this.pending = true;

    try {
      await this.applicationService.upload(upload).toPromise();
      this.pending = false;
      this.snackbar.open(SNACKBAR_SUCCESS_TEXT, SNACKBAR_CLOSE_BUTTON, SNACKBAR_SUCCESS_CONFIG);
      this.router.navigate([Route.ADVERTISEMENTS]);
    }

    catch {
      this.pending = false;
      this.snackbar.open(SNACKBAR_ERROR_TEXT, SNACKBAR_CLOSE_BUTTON, SNACKBAR_ERROR_CONFIG);
    }
  }

}
