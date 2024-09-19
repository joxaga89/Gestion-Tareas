import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tarea } from '../models/tarea';
import { RespuestaAPI } from '../models/respuestaApi';

@Injectable({
  providedIn: 'root'
})
export class TareaService {


  private baseUrl='http://localhost:9090/api/tareas';

  constructor(private http:HttpClient) { }

  getAllTareas(): Observable<RespuestaAPI> {
    return this.http.get<RespuestaAPI>(this.baseUrl);
  }

  getTareaById(id:number):Observable<Tarea>{
    return this.http.get<Tarea>(`${this.baseUrl}/id/${id}`);
  }

  getTareaByTitle(titulo:string):Observable<Tarea>{
    return this.http.get<Tarea>(`${this.baseUrl}/id/${titulo}`);
  }

  createTarea(tarea:Tarea):Observable<Tarea>{
    return this.http.post<Tarea>(this.baseUrl,tarea);
  }

  updateTarea(id:number,tarea:Tarea):Observable<Tarea>{
    return this.http.put<Tarea>(`${this.baseUrl}/${id}`,tarea);
  }

  deleteTarea(id:number):Observable<void>{
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

}
