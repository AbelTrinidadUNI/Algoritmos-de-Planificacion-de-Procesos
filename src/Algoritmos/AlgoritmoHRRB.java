/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author abelt
 */
public class AlgoritmoHRRB extends Algoritmo {

    private List<Proceso> procesos;
    private List<Proceso> procesosEnCola;
    private List<Proceso> procesosCompletados;

    public AlgoritmoHRRB(List<Proceso> p) {
        this.procesos = this.OrdenarListaProcesos(p);
        this.procesosEnCola = new ArrayList();
        this.procesosCompletados = new ArrayList();
    }

    @Override
    public void Resolver() {
        int rafagasTotales = this.TotalRafagas(this.procesos);
        System.out.println("Total rafagas: " + rafagasTotales);
        //se obtienen los procesos listos para ejecutarse
        for (int i = 0; i < rafagasTotales; i++) {
            int cantProcesos = this.procesos.size();
            
            for (int j = 0; j < this.procesos.size() && this.procesosEnCola.size() <= this.procesos.size(); j++) {
                if (this.procesos.get(j).getTiempo_llegada() <= i && !this.procesos.get(j).getCompletado()) {
                    this.procesosEnCola.add(this.procesos.get(j));
                    this.procesos.get(j).setCompletado(true);
                }
            }

            if (this.procesosEnCola.size() > 0) {
                for (int j = 0; j < this.procesosEnCola.get(0).getRafagas(); j++) {
                    try {
                        this.procesosEnCola.get(0).agregarPunto(i, "x");
                    } catch (IndexOutOfBoundsException e) {
                        for (int x = 0; x < i; x++) {
                            if (x >= this.procesosEnCola.get(0).getTiempo_llegada()) {
                                this.procesosEnCola.get(0).agregarPunto(x, "_");
                            } else {
                                this.procesosEnCola.get(0).agregarPunto(x, " ");
                            }
                        }
                        this.procesosEnCola.get(0).agregarPunto(i, "x");
                    }
                    i++;
                }
                i--;
                this.procesosCompletados.add(this.procesosEnCola.remove(0));
            }

            this.procesosEnCola = this.OrdenarListaProcesosPrioridadHRRB(this.calcularPrioridad(this.procesosEnCola, i));
            Collections.reverse(this.procesosEnCola);
        }
        /*NO FUNCIONA PORQUE LOS PROCESOS NO TIENE PINTADO NADA TODAVIA*/
        this.procesosCompletados = this.OrdenarListaProcesosNombre(this.procesosCompletados);
        for (int i = 0; i < this.procesosCompletados.size(); i++) {
            System.out.println(this.procesosCompletados.get(i).getNombre() + " ----> " + this.procesosCompletados.get(i).toStringPuntosAPintar());
        }
    }

    public List<Proceso> calcularPrioridad(List<Proceso> p, int tiempo) {
        for (Proceso i : p) {
            int w = tiempo - i.getTiempo_llegada();
            i.setTiempo_espera(w);
            int t = i.getRafagas();
            double d = (w + t) / t;
            System.out.println("PRIORIDAD = " + d + ", w = " + w + ", t = " + t);
            i.setPrioridadHRRB(d);
        }
        return p;
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

    public List<Proceso> OrdenarListaProcesosPrioridadHRRB(List<Proceso> p) {
        Collections.sort(p, (Proceso p1, Proceso p2) -> new Double(p1.getPrioridadHRRB()).compareTo(new Double(p2.getPrioridadHRRB())));
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
        System.out.println("P en la linea 128: " + p.getNombre());
        return this.getCantidadCaracter(p.toStringPuntosAPintar(), '_');
    }

    @Override
    public int getTiempoEjecucion(Proceso p) {
        return this.getTiempoEspera(p) + p.getRafagas();
    }

    @Override
    public int getTiempoRespuesta(Proceso p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private int getCantidadCaracter(String cadena, char caracter) {
        int cantidad = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == caracter) {
                cantidad++;
            }
        }
        System.out.println("Cantidad caracter: " + cantidad + "para la cadena: " + cadena);
        return cantidad;
    }

}
