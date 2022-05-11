/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abelt
 */
public class Proceso {
    private String nombre;
    private int tiempo_llegada;
    private int rafagas;
    private int prioridad;
    private int rafagasCompletadas;
    private boolean completado;
    private List<String> puntosAPintar;
    private double prioridadHRRB;
    
    private int tiempo_espera;
    private int tiempo_ejecucion;
    private int tiempo_respuesta;
    
    public Proceso(String nombre, int t_llegada, int rafagas, int prioridad) {
        this.nombre = nombre;
        this.tiempo_llegada = t_llegada;
        this.rafagas = rafagas;
        this.prioridad = prioridad;
        this.puntosAPintar = new ArrayList<String>();
        this.completado = false;
        
        this.rafagasCompletadas = 0;
        this.tiempo_espera = 0;
        this.tiempo_ejecucion = 0;
        this.tiempo_respuesta = 0;
        this.prioridadHRRB = 0.0;
    }

    public int getTiempo_espera() {
        return tiempo_espera;
    }

    public double getPrioridadHRRB() {
        return prioridadHRRB;
    }

    public void setPrioridadHRRB(double prioridadHRRB) {
        this.prioridadHRRB = prioridadHRRB;
    }

    public void setTiempo_espera(int tiempo_espera) {
        this.tiempo_espera = tiempo_espera;
    }

    public int getTiempo_ejecucion() {
        return tiempo_ejecucion;
    }

    public void setTiempo_ejecucion(int tiempo_ejecucion) {
        this.tiempo_ejecucion = tiempo_ejecucion;
    }

    public int getTiempo_respuesta() {
        return tiempo_respuesta;
    }

    public void setTiempo_respuesta(int tiempo_respuesta) {
        this.tiempo_respuesta = tiempo_respuesta;
    }

    public int getRafagasCompletadas(){
        return this.rafagasCompletadas;
    }
    public void setRafagasCompletadas(int rafagasCompletadas){
        this.rafagasCompletadas = rafagasCompletadas;
    }
    
    public boolean getCompletado(){
        return this.completado;
    }
    public void setCompletado(boolean completado){
        this.completado = completado;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempo_llegada() {
        return tiempo_llegada;
    }

    public void setTiempo_llegada(int tiempo_llegada) {
        this.tiempo_llegada = tiempo_llegada;
    }

    public int getRafagas() {
        return rafagas;
    }

    public void setRafagas(int rafagas) {
        this.rafagas = rafagas;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    public List<String> getPuntosAPintar(){
        return this.puntosAPintar;
    }
    public void agregarPunto(int posicion, String valor){
        this.puntosAPintar.add(posicion, valor);
    }
    public void agregarPunto(String valor){
        this.puntosAPintar.add(valor);
    }    
    public String toStringPuntosAPintar(){
        return this.puntosAPintar.toString().replaceAll(", ", ";").replace("[", "").replace("]", "");
    }
    public int getCantidadColumnas(){
        return this.puntosAPintar.size();
    }
}
