import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { Subscription } from 'rxjs';
import { MainServiceService } from 'src/app/services/main-service.service';

@Component({
  selector: 'app-url',
  templateUrl: './url.component.html',
  styleUrls: ['./url.component.scss']
})
export class UrlComponent implements OnInit, OnDestroy {
  
  flag: boolean = false;
  invalidUrl: boolean = false;
  inputUrl: string = '';
  randomGeneratedUrl: any;
  expired: boolean = false;
  generateSubscription: Subscription;
  fetchOriginalLinkSubscription: Subscription;

  constructor(private mainServiceService: MainServiceService) { }

  ngOnInit(): void {
    this.initialize();
  }

  initialize(){
    this.inputUrl='';
    this.invalidUrl = false;
    this.flag = false;
    this.randomGeneratedUrl = null;
  }

  urlGenerator(inputUrl){
    this.inputUrl = inputUrl;
    if(this.isValidUrl(this.inputUrl)){
      this.invalidUrl = false;
      this.flag = true;
      this.inputUrl = inputUrl;
      this.generateSubscription = this.mainServiceService.postData(this.inputUrl).subscribe((res) => {
        console.log(res);
        this.randomGeneratedUrl = res.generatedUrl;
      })
    }else{
      this.flag = false;
      this.invalidUrl = true;
    }
    console.log("txt :" + inputUrl);
  }

  private isValidUrl(input: string): boolean {
    try {
      const url = new URL(input);
      return true;
    } catch (error) {
      return false;
    }
  }

  retrieveOriginalUrl(url){
    console.log(url);
    this.fetchOriginalLinkSubscription = this.mainServiceService.getData(url).subscribe((res) => {
      console.log(res);
      if(res == null){
        this.expired = true;
        this.initialize();
      }else{
        window.open(res.givenUrl);
      }
    });
  }

  ngOnDestroy(): void {
    this.generateSubscription?.unsubscribe();
    this.fetchOriginalLinkSubscription?.unsubscribe();
  }

}
