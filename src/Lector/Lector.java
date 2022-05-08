/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lector;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author abelt
 */
public class Lector {

    public static List leer() {
        try {
            List<String[]> lectura = new ArrayList<String[]>();

            String ruta = "src/Archivos/Procesos.csv";
            InputStream ins = new FileInputStream(ruta);
            Scanner obj = new Scanner(ins);

            while (obj.hasNextLine()) {
                lectura.add(obj.nextLine().split(";"));
            }

            String tabla[][] = new String[lectura.size() - 1][4];
            String[] cabecera = new String[4];
            for (int i = 0; i < lectura.size(); i++) {
                for (int j = 0; j < 4; j++) {
                    if (i == 0) {
                        cabecera[j] = lectura.get(i)[j];

                    } else {
                        tabla[i - 1][j] = lectura.get(i)[j];
                    }
                }
            }
 
            List retorno = new ArrayList();
            retorno.add(cabecera);
            retorno.add(tabla);

            return retorno;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return null;
        } catch (NumberFormatException e) {
            System.out.println(e);
            return null;
        }
    }
}
