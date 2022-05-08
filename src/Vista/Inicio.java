/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Algoritmos.AlgoritmoFCFS;
import Algoritmos.AlgoritmoHRRB;
import Algoritmos.AlgoritmoPrioridad;
import Algoritmos.AlgoritmoRoundRobin;
import Algoritmos.AlgoritmoSJF_Exclusivo;
import Algoritmos.Proceso;
import Lector.Lector;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author abelt
 */
public class Inicio extends javax.swing.JFrame {

    private List<Proceso> procesos;

    /**
     * Creates new form Inicio
     */
    public Inicio() {
        initComponents();
        List contenido = Lector.leer();
        DefaultTableModel m = new DefaultTableModel(((String[][]) contenido.get(1)), ((String[]) contenido.get(0)));
        this.jTable.setModel(m);
        procesos = new ArrayList();

        for (String[] item : ((String[][]) contenido.get(1))) {
            String nombre = item[0];
            int t_llegada = Integer.parseInt(item[1]);
            int rafaga = Integer.parseInt(item[2]);
            int prioridad = Integer.parseInt(item[3]);

            this.procesos.add(new Proceso(nombre, t_llegada, rafaga, prioridad));
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jp_Principal = new javax.swing.JPanel();
        jpCabecera = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jRBFCFS = new javax.swing.JRadioButton();
        jRBSJFNE = new javax.swing.JRadioButton();
        jRBSJFE = new javax.swing.JRadioButton();
        jRBPrioridad = new javax.swing.JRadioButton();
        jRBRR = new javax.swing.JRadioButton();
        jRBHRRN = new javax.swing.JRadioButton();
        jTFQuantum = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jBCalcular = new javax.swing.JButton();
        jBCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Algoritmos de Planificacion de Procesos");

        javax.swing.GroupLayout jpCabeceraLayout = new javax.swing.GroupLayout(jpCabecera);
        jpCabecera.setLayout(jpCabeceraLayout);
        jpCabeceraLayout.setHorizontalGroup(
            jpCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpCabeceraLayout.setVerticalGroup(
            jpCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setText("Seleccione el algoritmo que quiere aplicar:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                .addContainerGap())
        );

        jRBFCFS.setText("FCFS");
        jRBFCFS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBFCFSMouseClicked(evt);
            }
        });
        jRBFCFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFCFSActionPerformed(evt);
            }
        });

        jRBSJFNE.setText("SJF(No Expulsivo)");
        jRBSJFNE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBSJFNEMouseClicked(evt);
            }
        });

        jRBSJFE.setText("SJF(Expulsivo)");
        jRBSJFE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBSJFEMouseClicked(evt);
            }
        });

        jRBPrioridad.setText("Prioridad");
        jRBPrioridad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBPrioridadMouseClicked(evt);
            }
        });

        jRBRR.setText("RR");
        jRBRR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBRRMouseClicked(evt);
            }
        });
        jRBRR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBRRActionPerformed(evt);
            }
        });

        jRBHRRN.setText("HRRN");
        jRBHRRN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBHRRNMouseClicked(evt);
            }
        });

        jTFQuantum.setText("4");
        jTFQuantum.setEnabled(false);
        jTFQuantum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFQuantumKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFQuantumKeyTyped(evt);
            }
        });

        jLabel3.setText("Q =");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRBFCFS)
                            .addComponent(jRBPrioridad))
                        .addGap(308, 308, 308)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRBSJFNE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRBRR)
                                .addGap(44, 44, 44)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFQuantum, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRBSJFE)
                            .addComponent(jRBHRRN))
                        .addGap(56, 56, 56))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBFCFS)
                    .addComponent(jRBSJFNE)
                    .addComponent(jRBSJFE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBPrioridad)
                    .addComponent(jRBRR)
                    .addComponent(jRBHRRN)
                    .addComponent(jTFQuantum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel4.setText("Procesos a Planificar: ");

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Nombre", "Apellido", "Juan"},
                {"Nombre", "Apellido", "Juan"},
                {"Nombre", "Apellido", "Juan"},
                {"Nombre", "Apellido", "Juan"}
            },
            new String [] {
                "Nombre", "Apellido", "Juan"
            }
        ));
        jScrollPane1.setViewportView(jTable);

        jScrollPane2.setViewportView(jScrollPane1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBCalcular.setText("Calcular");
        jBCalcular.setEnabled(false);
        jBCalcular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBCalcularMouseClicked(evt);
            }
        });
        jBCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCalcularActionPerformed(evt);
            }
        });

        jBCerrar.setText("Cerrar");
        jBCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBCerrarMouseClicked(evt);
            }
        });
        jBCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_PrincipalLayout = new javax.swing.GroupLayout(jp_Principal);
        jp_Principal.setLayout(jp_PrincipalLayout);
        jp_PrincipalLayout.setHorizontalGroup(
            jp_PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_PrincipalLayout.createSequentialGroup()
                .addGroup(jp_PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_PrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_PrincipalLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jBCerrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBCalcular)
                .addGap(14, 14, 14))
        );
        jp_PrincipalLayout.setVerticalGroup(
            jp_PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_PrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jp_PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBCalcular)
                    .addComponent(jBCerrar)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jp_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jp_Principal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRBFCFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFCFSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBFCFSActionPerformed

    private void jTFQuantumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFQuantumKeyTyped
        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (this.jTFQuantum.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_jTFQuantumKeyTyped

    private void jRBRRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBRRMouseClicked
        if (this.jRBRR.isSelected()) {
            this.jTFQuantum.setEnabled(true);
        } else {
            this.jTFQuantum.setEnabled(false);
        }
        this.bloquearBoton();
    }//GEN-LAST:event_jRBRRMouseClicked

    private void jBCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarActionPerformed

    }//GEN-LAST:event_jBCerrarActionPerformed

    private void jBCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBCerrarMouseClicked
        this.dispose();
    }//GEN-LAST:event_jBCerrarMouseClicked

    private void jBCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCalcularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBCalcularActionPerformed

    private void jBCalcularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBCalcularMouseClicked
        boolean fcfs = this.jRBFCFS.isSelected();
        boolean sjfne = this.jRBSJFNE.isSelected();
        boolean sjfe = this.jRBSJFE.isSelected();
        boolean prioridad = this.jRBPrioridad.isSelected();
        boolean rr = this.jRBRR.isSelected();
        boolean hrrb = this.jRBHRRN.isSelected();

        try {
            int quantum = 0;
            boolean error = false;
            if (rr) {
                quantum = Integer.parseInt(this.jTFQuantum.getText().trim());
                if (quantum <= 0) {
                    error = true;
                }
            }
            if (!error) {
                if (fcfs) {
                    AlgoritmoFCFS algoritmo = new AlgoritmoFCFS(procesos);
                    algoritmo.Resolver();
                }
                if (sjfne) {
                    AlgoritmoSJF_Exclusivo algoritmo = new AlgoritmoSJF_Exclusivo(procesos);
                    algoritmo.Resolver();
                }
                if (sjfe) {
                    AlgoritmoSJF_Exclusivo algoritmo = new AlgoritmoSJF_Exclusivo(procesos);
                    algoritmo.Resolver();
                }
                if (prioridad) {
                    //AlgoritmoPrioridad algoritmo = new AlgoritmoPrioridad(procesos);
                    //algoritmo.Resolver();
                }
                if (rr) {
                    //AlgoritmoRoundRobin algoritmo = new AlgoritmoRoundRobin(procesos);
                    //algoritmo.Resolver();
                }
                if (hrrb) {
                    //AlgoritmoHRRB algoritmo = new AlgoritmoHRRB(procesos);
                    //algoritmo.Resolver();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Debe indicar el valor del Quantum, este debe ser mayor a 0!!!");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe indicar el Quantum para el algoritmo de RR!!!");
            //System.out.println(e);
        }


    }//GEN-LAST:event_jBCalcularMouseClicked

    private void jRBFCFSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBFCFSMouseClicked
        this.bloquearBoton();
    }//GEN-LAST:event_jRBFCFSMouseClicked

    private void jRBSJFNEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBSJFNEMouseClicked
        this.bloquearBoton();
    }//GEN-LAST:event_jRBSJFNEMouseClicked

    private void jRBSJFEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBSJFEMouseClicked
        this.bloquearBoton();
    }//GEN-LAST:event_jRBSJFEMouseClicked

    private void jRBPrioridadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBPrioridadMouseClicked
        this.bloquearBoton();
    }//GEN-LAST:event_jRBPrioridadMouseClicked

    private void jRBRRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBRRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBRRActionPerformed

    private void jRBHRRNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBHRRNMouseClicked
        this.bloquearBoton();
    }//GEN-LAST:event_jRBHRRNMouseClicked

    private void jTFQuantumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFQuantumKeyPressed

    }//GEN-LAST:event_jTFQuantumKeyPressed
    private void bloquearBoton() {
        boolean fcfs = this.jRBFCFS.isSelected();
        boolean sjfne = this.jRBSJFNE.isSelected();
        boolean sjfe = this.jRBSJFE.isSelected();
        boolean prioridad = this.jRBPrioridad.isSelected();
        boolean rr = this.jRBRR.isSelected();
        boolean hrrb = this.jRBHRRN.isSelected();
        boolean quantum = this.jTFQuantum.getText().trim().length() > 0;

        if (!fcfs && !sjfne && !sjfe && !prioridad && !hrrb && (!rr || !quantum)) {
            this.jBCalcular.setEnabled(false);
        } else {
            this.jBCalcular.setEnabled(true);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCalcular;
    private javax.swing.JButton jBCerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRBFCFS;
    private javax.swing.JRadioButton jRBHRRN;
    private javax.swing.JRadioButton jRBPrioridad;
    private javax.swing.JRadioButton jRBRR;
    private javax.swing.JRadioButton jRBSJFE;
    private javax.swing.JRadioButton jRBSJFNE;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFQuantum;
    private javax.swing.JTable jTable;
    private javax.swing.JPanel jpCabecera;
    private javax.swing.JPanel jp_Principal;
    // End of variables declaration//GEN-END:variables
}