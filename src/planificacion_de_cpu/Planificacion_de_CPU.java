/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package planificacion_de_cpu;

import Algoritmos.Algoritmo;
import Algoritmos.AlgoritmoFCFS;
import Algoritmos.AlgoritmoSJF_Exclusivo;
import Algoritmos.AlgoritmoSJF_No_Exclusivo;
import Algoritmos.Proceso;
import Lector.Lector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author abelt
 */
public class Planificacion_de_CPU {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println("hola mundo");
        Lector.leer();
        
        List<Proceso> q = new ArrayList();
        q.add(new Proceso("A", 0, 6, 3));
        q.add(new Proceso("B", 1, 2, 8));
        q.add(new Proceso("C", 3, 5, 2));
        q.add(new Proceso("D", 3, 3, 5));
        q.add(new Proceso("E", 4, 5, 5));
        q.add(new Proceso("F", 4, 3, 5));
        q.add(new Proceso("G", 4, 2, 5));
        

        System.out.println("---------------");
        System.out.println("---------------");
        System.out.println("---------------");
        System.out.println("---------------");
        System.out.println("---------------");
        //AlgoritmoFCFS f = new AlgoritmoFCFS(q);
        //f.Resolver();

        AlgoritmoFCFS f = new AlgoritmoFCFS(q);
        f.Resolver();
    }

}
