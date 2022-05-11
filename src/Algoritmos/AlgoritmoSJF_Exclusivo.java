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
public class AlgoritmoSJF_Exclusivo extends Algoritmo {

    List<Proceso> procesos;
    List<Proceso> procesosEnCola;
    List<Proceso> procesosCompletados;
    private int totalRafagas;

    public AlgoritmoSJF_Exclusivo(List<Proceso> p) {
        this.procesos = this.OrdenarListaProcesos(p);
        this.totalRafagas = this.TotalRafagas(p);
        this.procesosCompletados = new ArrayList();
        this.procesosEnCola = new ArrayList();
    }

    @Override
    public void Resolver() {
        for (int i = 0; i < this.totalRafagas; i++) {
            //en cada vuelta se verificara si i es igual a alguno de los tiempos de llegada de los procesos
            //si es asi se procedera a ordenar la lista con procesos hasta ese momento

            //se cargan los procesos
            for (int j = 0; j < this.procesos.size() && this.procesosEnCola.size() <= this.procesos.size(); j++) {
                if (this.procesos.get(j).getTiempo_llegada() == i) {
                    this.procesosEnCola.add(this.procesos.get(j));
                }
            }

            this.procesosEnCola = this.OrdenarListaProcesosRafagas(this.procesosEnCola);

            int aux = this.procesosEnCola.size();
            for (int j = 0; j < aux; j++) {
                if (j == 0) {
                    try {
                        this.procesosEnCola.get(0).agregarPunto(i, Algoritmo.EJECUCION);
                    } catch (IndexOutOfBoundsException e) {
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

                    if (this.procesosEnCola.get(0).getRafagasCompletadas() == this.procesosEnCola.get(0).getRafagas()) {
                        //Se calculan los tiempos de espera, ejecucion y respuesta del proceso al terminar de ejecutarlo
                        this.procesosEnCola.get(0).setTiempo_espera(this.getTiempoEspera(this.procesosEnCola.get(0)));
                        this.procesosEnCola.get(0).setTiempo_ejecucion(this.getTiempoEjecucion(this.procesosEnCola.get(0)));
                        this.procesosEnCola.get(0).setTiempo_respuesta(this.getTiempoRespuesta(this.procesosEnCola.get(0)));

                        this.procesosCompletados.add(this.procesosEnCola.remove(0));
                        aux--;
                    }

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

        this.procesosCompletados = this.OrdenarListaProcesosNombre(this.procesosCompletados);

        String resultado = this.getTabla(this.procesosCompletados, this.totalRafagas) + this.promediar(this.procesosCompletados);
        Escritor e = new Escritor("src/Archivos/Resultados-Algoritmo-SJF_Exclusivo.csv");
        e.escibir(resultado);

        //System.out.println(resultado);
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
        //PRIMER TIEMPO DE EJECUCION
        int posicionX = p.getPuntosAPintar().indexOf(Algoritmo.EJECUCION);
        //PRIMER TIEMPO DE ESPERA
        int posicion_ = p.getPuntosAPintar().indexOf(Algoritmo.ESPERA);
        int tiempoRespuesta = 1;
        if (posicion_ < posicionX) {
            tiempoRespuesta = posicionX - posicion_ + 1;
        }
        return tiempoRespuesta;
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
