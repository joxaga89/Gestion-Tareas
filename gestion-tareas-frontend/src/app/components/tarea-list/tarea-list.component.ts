import { Component } from '@angular/core';
import {  Tarea } from '../../models/tarea';
import { TareaService } from '../../services/tarea.service';
import { NgFor } from '@angular/common';
import { RouterLink } from '@angular/router';
import { RespuestaAPI } from '../../models/respuestaApi';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';


@Component({
  selector: 'app-tarea-list',
  standalone: true,
  imports: [NgFor,RouterLink,MatSnackBarModule],
  templateUrl: './tarea-list.component.html',
  styleUrl: './tarea-list.component.css'
})
export class TareaListComponent {
  tareas?:Tarea[];
  respuesta?: RespuestaAPI;

  constructor(private tareaService:TareaService,private snackBar: MatSnackBar,){}

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
      this.snackBar.open('¡Tarea eliminada con éxito!', 'Cerrar', {
        duration: 3000, // Duración en milisegundos
        horizontalPosition: 'center', // Posición horizontal
        verticalPosition: 'bottom', // Posición vertical
      });
      this.cargarTareas();
    })
  }
}
