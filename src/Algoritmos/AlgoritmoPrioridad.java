/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmos;

import Escitor.Escritor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author abelt
 */
public class AlgoritmoPrioridad extends Algoritmo {

    List<Proceso> procesos;
    List<Proceso> procesosEnCola;
    List<Proceso> procesosCompletados;
    private int totalRafagas;

    public AlgoritmoPrioridad(List<Proceso> p) {
        this.procesos = this.OrdenarListaProcesos(p);
        this.totalRafagas = this.TotalRafagas(p);
        this.procesosCompletados = new ArrayList();
        this.procesosEnCola = new ArrayList();
    }

    @Override

    public void Resolver() {
        for (int i = 0; i < this.totalRafagas; i++) {

            //se cargan los procesos
            for (int j = 0; j < this.procesos.size() && this.procesosEnCola.size() <= this.procesos.size(); j++) {
                if (this.procesos.get(j).getTiempo_llegada() == i) {
                    this.procesosEnCola.add(this.procesos.get(j));

                }

            }
            //ordeno la lista por prioridad
            this.procesosEnCola = this.OrdenarListaProcesosPrioridad(this.procesosEnCola);

            //esto para saber la cantidad de procesos en cola
            int aux = this.procesosEnCola.size();
            for (int j = 0; j < aux; j++) {
                if (j == 0) {
                    try {

                        //en la posicion 0 se obtiene el proceso y se le indica que se esta ejecutando
                        this.procesosEnCola.get(0).agregarPunto(i, Algoritmo.EJECUCION);
                        //en caso de que se quiera agregar x en una posicion invalida
                    } catch (IndexOutOfBoundsException e) {
                        //obtengo cantidad de columnas ya pintadas y empiezo desde ahi
                        for (int x = this.procesosEnCola.get(0).getCantidadColumnas(); x < i; x++) {
                            if (x < this.procesosEnCola.get(0).getTiempo_llegada()) {
                                this.procesosEnCola.get(0).agregarPunto(Algoritmo.INACTIVO);
                            } else {
                                this.procesosEnCola.get(0).agregarPunto(Algoritmo.ESPERA);
                            }
                        }
                        this.procesosEnCola.get(0).agregarPunto(i, Algoritmo.EJECUCION);

                    }
                    this.procesosEnCola.get(0).setRafagasCompletadas(this.procesosEnCola.get(0).getRafagasCompletadas() + 1);

                    //se comprueba si ya se completa la cantidad de rafagas del proceso
                    if (this.procesosEnCola.get(0).getRafagasCompletadas() == this.procesosEnCola.get(0).getRafagas()) {
                        this.procesosCompletados.add(this.procesosEnCola.remove(0));
                        aux--;//disminuyo la cantidad de la lista de los procesos en cola
                    }
                    //en caso de que los procesos no esten en ejecucion
                } else {

                    try {
                        this.procesosEnCola.get(j).agregarPunto(i, Algoritmo.ESPERA);
                    } catch (IndexOutOfBoundsException e) {
                        for (int x = this.procesosEnCola.get(j).getCantidadColumnas(); x < i; x++) {
                            if (this.procesosEnCola.get(j).getTiempo_llegada() < x) {
                                this.procesosEnCola.get(j).agregarPunto(Algoritmo.ESPERA);

                            } else {
                                this.procesosEnCola.get(j).agregarPunto(Algoritmo.INACTIVO);
                            }
                        }
                        this.procesosEnCola.get(j).agregarPunto(Algoritmo.ESPERA);
                    }

                }
            }
        }

        //ordena por nombres los procesos
        this.procesosCompletados = this.OrdenarListaProcesosNombre(this.procesosCompletados);

        //crea la tabla
        String resultado = this.getTabla(this.procesosCompletados, this.totalRafagas) + this.promediar(this.procesosCompletados);
        Escritor e = new Escritor("src/Archivos/Resultados-Algortmo-Prioridad.csv");
        e.escibir(resultado);

    }

    public List<Proceso> OrdenarListaProcesosNombre(List<Proceso> p) {
        Collections.sort(p, (Proceso p1, Proceso p2) -> new String(p1.getNombre()).compareTo(new String(p2.getNombre())));
        return p;
    }

    public List<Proceso> OrdenarListaProcesosPrioridad(List<Proceso> p) {
        Collections.sort(p, (Proceso p1, Proceso p2) -> new Integer(p1.getPrioridad()).compareTo(new Integer(p2.getPrioridad())));
        return p;
    }

    @Override
    public List<Proceso> OrdenarListaProcesos(List<Proceso> p) {
        Collections.sort(p, (Proceso p1, Proceso p2) -> new Integer(p1.getTiempo_llegada()).compareTo(new Integer(p2.getTiempo_llegada())));
        return p;
    }

    @Override
    public int TotalRafagas(List<Proceso> lp) {
        int sum = 0;
        for (Proceso p : lp) {
            sum += p.getRafagas();
        }
        return sum;
    }

    @Override
    public int getTiempoEspera(Proceso p) {
        return this.getCantidadCaracter(p.toStringPuntosAPintar(), '_');
    }

    @Override
    public int getTiempoEjecucion(Proceso p) {
        return this.getTiempoEspera(p) + p.getRafagas();
    }

    @Override
    public int getTiempoRespuesta(Proceso p) {
        return this.getTiempoEspera(p) + 1;
    }

    private int getCantidadCaracter(String cadena, char caracter) {
        int cantidad = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == caracter) {
                cantidad++;
            }
        }
        return cantidad;
    }
}
