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
public class AlgoritmoFCFS extends Algoritmo {

    List<Proceso> procesos;
    private int totalRafagas;

    public AlgoritmoFCFS(List<Proceso> p) {
        this.procesos = this.OrdenarListaProcesos(p);
        this.totalRafagas = this.TotalRafagas(p);
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
    public void Resolver() {
        List<Proceso> lista = new ArrayList();
        for (int i = 0; i < this.totalRafagas; i++) {

            if (!this.procesos.get(0).getCompletado() && this.procesos.get(0).getTiempo_llegada() <= i) {
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
                this.procesos.get(0).setRafagasCompletadas(this.procesos.get(0).getRafagasCompletadas() + 1);
                if (this.procesos.get(0).getRafagasCompletadas() == this.procesos.get(0).getRafagas()) {

                    //Se calculan los tiempos de espera, ejecucion y respuesta del proceso al terminar de ejecutarlo
                    this.procesos.get(0).setTiempo_espera(this.getTiempoEspera(this.procesos.get(0)));
                    this.procesos.get(0).setTiempo_ejecucion(this.getTiempoEjecucion(this.procesos.get(0)));
                    this.procesos.get(0).setTiempo_respuesta(this.getTiempoRespuesta(this.procesos.get(0)));

                    //se marca como completado y se quita de la primera posicion y se lo manda al final de la lista
                    this.procesos.get(0).setCompletado(true);
                    Proceso p = this.procesos.get(0);
                   
                    this.procesos.remove(0);
                    this.procesos.add(CompletarColumnas(p, this.totalRafagas));
                }
            } else {

                this.procesos.get(0).agregarPunto(i, "*");
            }
        }

        Escritor e = new Escritor("src/Archivos/Resultados.csv");
        e.escibir(this.Resultado() + this.promediar(this.procesos));
    }
    
    private Proceso CompletarColumnas(Proceso p, int rafagas){
        while(p.getCantidadColumnas() < rafagas){
            p.agregarPunto("");
        }
        
        return p;
    }

    public String Resultado() {
        List<String> l = new ArrayList();
        //System.out.println("--------------------- Resultado ----------------------------");
        List<Integer> numeros = new ArrayList();
        for (int i = 0; i < this.TotalRafagas(this.procesos); i++) {
            numeros.add(i);
            //System.out.print(i + ";");
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
