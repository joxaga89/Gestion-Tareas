import { Tarea } from "./tarea";

export interface RespuestaAPI {
    content: Tarea[]; // Aquí se define el tipo sin inicializar
    pageable?: any; // Cambia esto según la estructura exacta si es necesario
    totalElements: number;
    totalPages: number;
    last: boolean;
    size: number;
    number: number;
    sort: {
      empty: boolean;
      sorted: boolean;
      unsorted: boolean;
    };
    numberOfElements: number;
    first: boolean;
    empty: boolean;
  }
  