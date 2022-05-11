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
        List<Proceso> procesosCompletados = new ArrayList();

        for (int i = 0; i < this.totalRafagas; i++) {
            if (this.procesos.get(0).getTiempo_llegada() <= i) {
                int rafagasFaltantes = this.procesos.get(0).getRafagas() - this.procesos.get(0).getRafagasCompletadas();
                int quantumActual = this.quantum < rafagasFaltantes ? this.quantum : rafagasFaltantes;
                try {
                    for (int j = 0; j < quantumActual; j++) {
                        this.procesos.get(0).agregarPunto(i, "x");
                        this.procesos.get(0).setRafagasCompletadas(this.procesos.get(0).getRafagasCompletadas() + 1);
                        i++;
                    }
                    i--;
                } catch (IndexOutOfBoundsException e) {
                    for (int x = this.procesos.get(0).getCantidadColumnas(); x < i; x++) {
                        if (x < this.procesos.get(0).getTiempo_llegada()) {
                            this.procesos.get(0).agregarPunto(" ");
                        } else {
                            this.procesos.get(0).agregarPunto("_");
                        }
                    }
                    //se agrega los tiempos de ejecucion de la rafaga
                    for (int j = 0; j < quantumActual; j++) {
                        this.procesos.get(0).agregarPunto(i, "x");
                        this.procesos.get(0).setRafagasCompletadas(this.procesos.get(0).getRafagasCompletadas() + 1);
                        i++;
                    }
                    i--;
                }
                if (this.procesos.get(0).getRafagasCompletadas() == this.procesos.get(0).getRafagas()) {
                    procesosCompletados.add(this.procesos.remove(0));
                }else{
                    this.procesos.add(this.procesos.remove(0));
                }
            }

        }

        procesosCompletados = this.OrdenarListaProcesosNombres(procesosCompletados);
        for (int i = 0; i < procesosCompletados.size(); i++) {

            System.out.println(procesosCompletados.get(i).getNombre()+ " -----> " + procesosCompletados.get(i).toStringPuntosAPintar());
        }
    }

    @Override
    public List<Proceso> OrdenarListaProcesos(List<Proceso> p) {
        Collections.sort(p, (Proceso p1, Proceso p2) -> new Integer(p1.getTiempo_llegada()).compareTo(new Integer(p2.getTiempo_llegada())));
        return p;
    }
    public List<Proceso> OrdenarListaProcesosNombres(List<Proceso> p) {
        Collections.sort(p, (Proceso p1, Proceso p2) -> new String(p1.getNombre()).compareTo(new String(p2.getNombre())));
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
        int posicionX = p.getPuntosAPintar().indexOf("X");
        int posicion_ = p.getPuntosAPintar().indexOf("_");
        
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
