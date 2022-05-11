package Escitor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author abelt
 */
public class Escritor {

    private String Ruta;

    public Escritor(String ruta) {
        this.Ruta = ruta;
    }

    public void escibir(String cadena) {
        System.out.println("");
        try {
            FileWriter fw = new FileWriter(this.Ruta, false);
            PrintWriter salida = new PrintWriter(fw);
            salida.println(cadena);
            salida.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex);
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
