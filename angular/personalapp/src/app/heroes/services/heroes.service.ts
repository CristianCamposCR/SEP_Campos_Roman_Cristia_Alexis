import { Injectable } from '@angular/core';
import { Heroe } from '../types/heroe';

@Injectable({
  providedIn: 'root'
})//desde cualquier componente se puede mandar a traer este servicio asi se deja


export class HeroesService {
  private _heroes: Heroe[] = [];//atributo


  get heroes (){//este metodo pasa a ser un atributo

    return [...this._heroes];//estamos retornado otra lista reestructurada
  }


  saveHeroe(heroe:Heroe){
    //aqui se hacen todas las validaciones correspondientes
    this._heroes.push(heroe);
  }
}
