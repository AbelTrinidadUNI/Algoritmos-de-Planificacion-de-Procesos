/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lector;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author abelt
 */
public class Lector {

    public static void leer() {
        try {
            
            String ruta = "src/Archivos/Procesos.csv";
            InputStream ins = new FileInputStream(ruta);
            Scanner obj = new Scanner(ins);
            while (obj.hasNextLine()) {
                System.out.println(obj.nextLine());
            }
            
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
