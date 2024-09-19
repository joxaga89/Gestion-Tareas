import { Component } from '@angular/core';
import {  Tarea } from '../../models/tarea';
import { TareaService } from '../../services/tarea.service';
import { NgFor } from '@angular/common';
import { RouterLink } from '@angular/router';
import { RespuestaAPI } from '../../models/respuestaApi';

@Component({
  selector: 'app-tarea-list',
  standalone: true,
  imports: [NgFor,RouterLink],
  templateUrl: './tarea-list.component.html',
  styleUrl: './tarea-list.component.css'
})
export class TareaListComponent {
  tareas?:Tarea[];
  respuesta?: RespuestaAPI;

  constructor(private tareaService:TareaService){}

  ngOnInit():void{
    this.cargarTareas();
  }

  cargarTareas(): void {
    this.tareaService.getAllTareas().subscribe((respuesta: RespuestaAPI) => {
      this.tareas = respuesta.content; // Accede a la propiedad content
      console.log(this.tareas)
    });
  }

  eliminarTarea(id?:number):void{
    this.tareaService.deleteTarea(id!).subscribe(() => {
      this.cargarTareas();
    })
  }
}
