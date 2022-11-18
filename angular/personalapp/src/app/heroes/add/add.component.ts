import { Component, OnInit } from '@angular/core';
import { HeroesService } from '../services/heroes.service';
import { Heroe } from '../types/heroe';


@Component({
  selector: 'app-add',
  templateUrl: './add.component.html'
})
export class AddComponent {
  heroe:Heroe = {
    name:'',
    power: 0,
    abilities: []
  }
  constructor(private readonly heroeService: HeroesService) { }
   saveHeroe(){
    this.heroeService.saveHeroe({...this.heroe});
    this.heroe = {
      name:'',
      power: 0,
      abilities: []

   };
  }
}
