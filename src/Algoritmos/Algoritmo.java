package Algoritmos;

import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author abelt
 */
public abstract class Algoritmo {
    public static String EJECUCION = "X";  
    public static String ESPERA = "_";  
    public static String INACTIVO = " ";
    
    public abstract void Resolver();

    public abstract List<Proceso> OrdenarListaProcesos(List<Proceso> p);

    public abstract int TotalRafagas(List<Proceso> lp);

    public abstract int getTiempoEspera(Proceso p);

    public abstract int getTiempoEjecucion(Proceso p);

    public abstract int getTiempoRespuesta(Proceso p);

    public String promediar(List<Proceso> procesos) {
        List promedios = new ArrayList();
        promedios.add("Procesos");
        promedios.add("T. Espera");
        promedios.add("T. Ejecucion");
        promedios.add("T. Respuesta");
        promedios.add("\n");

        float sumatoria_TiempoEspera = 0;
        float sumatoria_TiempoEjecucion = 0;
        float sumatoria_TiempoRespuesta = 0;

        for (int i = 0; i < procesos.size(); i++) {
            promedios.add(procesos.get(i).getNombre());
            promedios.add(procesos.get(i).getTiempo_espera());
            promedios.add(procesos.get(i).getTiempo_ejecucion());
            promedios.add(procesos.get(i).getTiempo_respuesta());
            promedios.add("\n");

            sumatoria_TiempoEspera += procesos.get(i).getTiempo_espera();
            sumatoria_TiempoEjecucion += procesos.get(i).getTiempo_ejecucion();
            sumatoria_TiempoRespuesta += procesos.get(i).getTiempo_respuesta();

        }

        sumatoria_TiempoEspera /= procesos.size();
        sumatoria_TiempoEjecucion /= procesos.size();
        sumatoria_TiempoRespuesta /= procesos.size();

        promedios.add("Promedios");
        promedios.add(sumatoria_TiempoEspera);
        promedios.add(sumatoria_TiempoEjecucion);
        promedios.add(sumatoria_TiempoRespuesta);
        return "\n;" + promedios.toString().replaceAll(", ", ";").replace("[", "").replace("]", "");

    }

    public String getTabla(List<Proceso> procesos, int totalRafagas) {

        StringBuilder resultado = new StringBuilder();
        resultado.append("Procesos");
        resultado.append(";");
        for (int i = 0; i < totalRafagas; i++) {
            resultado.append(i);
            resultado.append(";");
        }
        resultado.append("\n");

        for (Proceso p : procesos) {
            resultado.append(p.getNombre());
            resultado.append(";");
            resultado.append(p.toStringPuntosAPintar());
            resultado.append("\n");
        }
        return resultado.toString();
    }

    public Proceso CompletarColumnas(Proceso p, int rafagas) {
        while (p.getCantidadColumnas() < rafagas) {
            p.agregarPunto(" ");
        }

        return p;
    }
}
