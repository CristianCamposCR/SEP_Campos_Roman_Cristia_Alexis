import { Component, OnInit } from '@angular/core';
import { HeroesService } from '../services/heroes.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: []
})
export class ListComponent {

  get heroes(){
    return this.heroeService.heroes;
  }
  constructor(private readonly heroeService: HeroesService) {}

}
