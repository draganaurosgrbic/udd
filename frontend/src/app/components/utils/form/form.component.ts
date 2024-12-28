import { AfterViewInit, Component, ElementRef, EventEmitter, Input, OnInit, Output, QueryList, ViewChildren } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormService } from 'src/app/services/form.service';
import { FormConfig, FormStyle } from 'src/app/utils/form';
import places from 'places.js';
import { ALGOLIA_API_ID, ALGOLIA_API_KEY } from 'src/app/utils/algolia';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit, AfterViewInit {

  constructor(
    private formService: FormService
  ) { }

  @Input() title: string;
  @Input() config: FormConfig;
  @Input() pending: boolean;
  @Input() style: FormStyle;
  @Output() submit = new EventEmitter();
  form: FormGroup;

  @ViewChildren('locationInput') locationInputs: QueryList<ElementRef<HTMLInputElement>>;
  location: { lat: number, lng: number };

  get controls() {
    return Object.keys(this.config).filter(control => this.config[control].type !== 'file')
  }

  get fileControls() {
    return Object.keys(this.config).filter(control => this.config[control].type === 'file')
  }

  ngOnInit() {
    this.form = this.formService.build(this.config);
  }

  ngAfterViewInit() {
    for (const locationInput of this.locationInputs) {
      const locationAutocomplete = places({
        container: locationInput.nativeElement,
        apiKey: ALGOLIA_API_KEY,
        appId: ALGOLIA_API_ID
      });

      locationAutocomplete.on('change', event => {
        this.location = event.suggestion.latlng;
        if ('address' in this.form.value) {
          this.form.get('address').setValue(event.suggestion.value);
        }
      });
    }
  }

  type(control: string) {
    return this.config[control].type || 'text'
  }

  options(control: string) {
    return this.config[control].options || []
  }

  handleSubmit() {
    if (this.form.invalid) {
      this.form.markAsTouched();
      return;
    }
    this.submit.emit({ ...this.form.value, lat: this.location?.lat, lng: this.location?.lng });
  }

  capitalize(text: string) {
    text = text.replace(/([a-z])([A-Z])/g, '$1 $2');
    return text[0].toUpperCase() + text.substr(1).toLowerCase();
  }

  updateFile(control: string, file: Blob) {
    this.form.get(control).setValue(file);
  }

}
