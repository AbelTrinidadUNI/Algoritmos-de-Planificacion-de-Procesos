/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmos;

import Escitor.Escritor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author abelt
 */
public class AlgoritmoRoundRobin extends Algoritmo {

    List<Proceso> procesos;
    private int totalRafagas;
    private int quantum;

    public AlgoritmoRoundRobin(List<Proceso> p, int q) {
        this.procesos = this.OrdenarListaProcesos(p);
        this.totalRafagas = this.TotalRafagas(p);
        this.quantum = q;
    }


    /*
        Hacer que se pueda seleccionar el tamaño del quantum, asi se cubre el del cuantum 4, 8, 16, etc
     */
    @Override
    public void Resolver() {
        List<Proceso> procesosCompletado = new ArrayList();

        for (int i = 0; i < this.totalRafagas; i++) {

            for (Proceso p : this.procesos) {
                System.out.println(p.getNombre());
            }
            System.out.println("\n");
            int operacion = this.procesos.get(0).getRafagas() - this.procesos.get(0).getRafagasCompletadas();
            //en caso de que sea mayor o igual a la cantidad de rafagas
            if (this.procesos.size() > 0 && this.quantum >= operacion) {
                for (int c = this.procesos.get(0).getCantidadColumnas(); c < i; c++) {
                    if (c < this.procesos.get(0).getTiempo_llegada()) {
                        this.procesos.get(0).agregarPunto(" ");
                    } else {
                        this.procesos.get(0).agregarPunto("_");
                    }
                }
                for (int y = 0; y < this.procesos.get(0).getRafagas(); y++) {
                    i++;
                    this.procesos.get(0).agregarPunto("x");
                    this.procesos.get(0).setRafagasCompletadas(this.procesos.get(0).getRafagasCompletadas() + 1);
                }
                i--;//pq se aumenta 1 de mas en el for entonces revertimos eso
                if (this.procesos.get(0).getRafagasCompletadas() == this.procesos.get(0).getRafagas()) {
                    procesosCompletado.add(this.procesos.remove(0));
                }

            } //en caso de que el proceso es menor que el quantum
            else if (this.procesos.size() > 0) {
                for (int c = this.procesos.get(0).getCantidadColumnas(); c < i; c++) {
                    if (c < this.procesos.get(0).getTiempo_llegada()) {
                        this.procesos.get(0).agregarPunto(" ");
                    } else {
                        this.procesos.get(0).agregarPunto("_");
                    }

                }

                for (int x = 0; x < this.quantum; x++) {
                    i++;//se suma pq se va agregando cosas por la posicion de mi i
                    this.procesos.get(0).agregarPunto("x");
                    this.procesos.get(0).setRafagasCompletadas(procesos.get(0).getRafagasCompletadas() + 1);

                }
                i--;//pq se aumenta 1 de mas en el for entonces revertimos eso
                if (this.procesos.get(0).getRafagasCompletadas() == this.procesos.get(0).getRafagas()) {
                    procesosCompletado.add(this.procesos.remove(0));
                } else {

                    this.procesos.add(this.procesos.remove(0));
                }
            }
            //System.out.println(procesosCompletado.size());
            /*  if (this.procesos.size() > 0) {
                if (this.procesos.get(0).getRafagasCompletadas() == this.procesos.get(0).getRafagas()) {

                    this.procesos.get(0).setCompletado(true);
                    //Se calculan los tiempos de espera, ejecucion y respuesta del proceso al terminar de ejecutarlo
                    this.procesos.get(0).setTiempo_espera(this.getTiempoEspera(this.procesos.get(0)));
                    this.procesos.get(0).setTiempo_ejecucion(this.getTiempoEjecucion(this.procesos.get(0)));
                    this.procesos.get(0).setTiempo_respuesta(this.getTiempoRespuesta(this.procesos.get(0)));
                }
            }*/
            //      this.procesos.get(0).agregarPunto("*");
        }

        for (int i = 0; i < procesosCompletado.size(); i++) {

            System.out.println(procesosCompletado.get(i).toStringPuntosAPintar());
        }
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

    public int tiempoEspera(List<Proceso> p) {
        int sumarTiempo = 0;
        for (Proceso pp : p) {
            sumarTiempo += pp.getTiempo_espera();
        }
        return sumarTiempo;
    }

    @Override
    public int getTiempoEjecucion(Proceso p) {
        return this.getTiempoEjecucion(p) + p.getTiempo_espera();
    }

    @Override
    public int getTiempoRespuesta(Proceso p) {
        return this.getTiempoEspera(p) + 1;
    }

    @Override
    public int getTiempoEspera(Proceso p) {
        return this.getCantidadCaracter(p.toStringPuntosAPintar(), '_');
    }

    private int setQuantum(int q) {
        return this.quantum = q;
    }

    private int getQuantum() {
        return this.quantum;
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

    private Proceso CompletarColumnas(Proceso p, int rafagas) {
        while (p.getCantidadColumnas() < rafagas) {
            p.agregarPunto("");
        }

        return p;
    }

}
