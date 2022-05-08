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
        List<Proceso> completado = new ArrayList();

        for (int i = 0; i < this.totalRafagas; i++) {

            if (this.quantum > this.procesos.get(0).getRafagas()) {
                this.procesos.get(0).agregarPunto(i, "x");
                this.procesos.get(0).setRafagasCompletadas(this.procesos.get(0).getRafagasCompletadas() + 1);

            } else {

                if (!this.procesos.get(0).getCompletado()) {
                    try {
                        this.procesos.get(0).agregarPunto(i, "x");
                    } catch (IndexOutOfBoundsException e) {
                        for (int j = 0; j < i; j++) {
                            if (j < this.procesos.get(0).getTiempo_llegada()) {
                                this.procesos.get(0).agregarPunto(j, " ");
                            } else {
                                this.procesos.get(0).agregarPunto(j, "_");
                            }
                        }
                        this.procesos.get(0).agregarPunto(i, "x");

                    }

                    for (int x = 0; x < this.quantum; x++) {
                        this.procesos.get(0).setRafagasCompletadas(procesos.get(0).getRafagasCompletadas() + 1);
                    }
                    if (this.procesos.get(0).getRafagasCompletadas() == this.procesos.get(0).getRafagas()) {

                        //Se calculan los tiempos de espera, ejecucion y respuesta del proceso al terminar de ejecutarlo
                        this.procesos.get(0).setTiempo_espera(this.getTiempoEspera(this.procesos.get(0)));
                        // this.procesos.get(0).setTiempo_ejecucion(this.getTiempoEjecucion(this.procesos.get(0)));
                        this.procesos.get(0).setTiempo_respuesta(this.getTiempoRespuesta(this.procesos.get(0)));

                        //se marca como completado y se quita de la primera posicion y se lo manda al final de la lista
                        this.procesos.get(0).setCompletado(true);
                        Proceso p = this.procesos.get(0);
                        this.procesos.remove(0);
                        this.procesos.add(p);
                    }

                    this.procesos.get(0).agregarPunto(i, "*");
                }
            }

        }

        Escritor e = new Escritor("src/Archivos/Resultados.csv");
        e.escibir(this.Resultado() + this.promediar(this.procesos));
    }

    public String Resultado() {
        List<String> l = new ArrayList();

        List<Integer> numeros = new ArrayList();
        for (int i = 0; i < this.TotalRafagas(this.procesos); i++) {
            numeros.add(i);
        }
        l.add(numeros.toString().replaceAll(", ", ";").replace("[", "").replace("]", ""));
        //System.out.println();

        for (int i = 0; i < this.procesos.size(); i++) {
            l.add(this.procesos.get(i).toStringPuntosAPintar());
            //System.out.println(this.procesos.get(i).toStringPuntosAPintar());

        }
        // System.out.println("------------------------------------------------------------");
        return l.toString().replaceAll(", ", "\n").replace("[", "").replace("]", "");
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

    private int getCantidadCaracter(String cadena, char caracter) {
        int cantidad = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == caracter) {
                cantidad++;
            }
        }
        return cantidad;
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

}
