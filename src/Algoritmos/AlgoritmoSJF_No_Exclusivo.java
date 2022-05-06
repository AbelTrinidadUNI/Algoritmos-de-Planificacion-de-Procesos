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
public class AlgoritmoSJF_No_Exclusivo extends Algoritmo {

    /*
        Se tendria que los procesos despues de que un proceso termine su ejecucion y al cargar ordenar de menor a mayor en cuando a su rafaga para ejecuar el menor
     */
    List<Proceso> procesos;
    List<Proceso> procesosCompletados;
    private int totalRafagas;

    public AlgoritmoSJF_No_Exclusivo(List<Proceso> p) {
        this.procesos = this.OrdenarListaProcesos(p);//se debe ordenar en tiempo de ejecucion
        this.totalRafagas = this.TotalRafagas(p);
        this.procesosCompletados = new ArrayList();
    }

    @Override
    public void Resolver() {
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
                    this.procesosCompletados.add(this.procesos.get(0));
                    this.procesos.remove(0);
                    this.procesos = this.OrdenarListaProcesosRafagas(this.procesos);
                }
            } else {

                this.procesos.get(0).agregarPunto(i, "*");
            }
        }
        this.procesosCompletados = this.OrdenarListaProcesosNombre(this.procesosCompletados);
        String resultado = this.getTabla(this.procesosCompletados, this.totalRafagas) + this.promediar(this.procesosCompletados);
        
        Escritor e = new Escritor("src/Archivos/Resultados.csv");
        e.escibir(resultado);
        
        /*for(int i = 0; i < this.procesosCompletados.size(); i++){
            System.out.println(this.procesosCompletados.get(i).getNombre() + "  =   " + this.procesosCompletados.get(i).toStringPuntosAPintar());
        }*/
    }
    
    public List<Proceso> OrdenarListaProcesosNombre(List<Proceso> p) {
        Collections.sort(p, (Proceso p1, Proceso p2) -> new String(p1.getNombre()).compareTo(new String(p2.getNombre())));
        return p;
    }
    
    public List<Proceso> OrdenarListaProcesosRafagas(List<Proceso> p) {
        Collections.sort(p, (Proceso p1, Proceso p2) -> new Integer(p1.getRafagas()).compareTo(new Integer(p2.getRafagas())));
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
