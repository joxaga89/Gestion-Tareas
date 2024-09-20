import { Component } from '@angular/core';
import { Tarea } from '../../models/tarea';
import { TareaService } from '../../services/tarea.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-tarea-form',
  standalone: true,
  imports: [FormsModule, MatSnackBarModule],
  templateUrl: './tarea-form.component.html',
  styleUrl: './tarea-form.component.css'
})
export class TareaFormComponent {
  tarea: Tarea = new Tarea;

  constructor(private tareaService:TareaService,private snackBar: MatSnackBar,private router:Router,private route:ActivatedRoute){}

  ngOnInit():void{
    const tareaId = +this.route.snapshot.paramMap.get('id')!;
    if(tareaId){
      this.tareaService.getTareaById(tareaId).subscribe(tarea => {
        this.tarea = tarea;
        console.log(this.tarea);
      })
    }
  }

  onSaveTarea():void{
    if(this.tarea.id){
      this.tareaService.updateTarea(this.tarea.id,this.tarea).subscribe(updateTarea => {
        this.snackBar.open('¡Tarea actualizada con éxito!', 'Cerrar', {
          duration: 3000, // Duración en milisegundos
          horizontalPosition: 'center', // Posición horizontal
          verticalPosition: 'bottom', // Posición vertical
        });
        this.router.navigate(['/tareas']);
      })
    }else{
      this.tareaService.createTarea(this.tarea).subscribe(savedTarea => {
        this.snackBar.open('¡Tarea guardada con éxito!', 'Cerrar', {
          duration: 3000, // Duración en milisegundos
          horizontalPosition: 'center', // Posición horizontal
          verticalPosition: 'bottom', // Posición vertical
        });
        this.router.navigate(['/tareas']);
      })
    }
  }
}
