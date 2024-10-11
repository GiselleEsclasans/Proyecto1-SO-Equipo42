/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import Classes.Company;
import Classes.Employee;
import Classes.Storage;
import DataStructure.Data;
import javax.swing.JLabel;

/**
 *
 * @author gigie
 */
public class DashboardApple extends javax.swing.JFrame {
    public static Company hp;
    public static Company apple;
    /**
     * Creates new form Dashboard
     */
     
   
    
    public DashboardApple(Company hp, Company apple) {
        this.hp = hp;
        this.apple = apple;
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        showInfo();
    }
    
    public void showInfo() {
        this.apple.setWindowApple(this);
        this.apple.printApple();
    }
    
    public JLabel getEpb2() {
        return ePB2;
    }
    
    
    public void updateComputerCount(int count) {
        cE.setText(String.valueOf(count));
        
    }

    public void updateGraphicComputerCount(int count) {
        cG.setText(String.valueOf(count));
    }
    
    public void updateStorageCapacity(Storage[] storages) {
        for (int i = 0; i < storages.length; i++) {
            String storageType = Data.producerTypes[i];
            int currentCapacity = storages[i].getCurrentCapacity();
            int maxCapacity = storages[i].getCapacity();
            // Actualizar la interfaz gráfica con la información correspondiente
            // Por ejemplo:
            if (i == 0) {
                aPB.setText(currentCapacity + " / " + maxCapacity);
            } else if (i == 1) {
                aCPU.setText(currentCapacity + " / " + maxCapacity);
            } else if (i == 2) {
                aRAM.setText(currentCapacity + " / " + maxCapacity);
            } else if (i == 3) {
                aFA.setText(currentCapacity + " / " + maxCapacity);
            } else if (i == 4) {
                aTG.setText(currentCapacity + " / " + maxCapacity);
            }
        }
    }

    public void updateWorkerCount(Employee[] employees) {
        int[] workerCounts = new int[6];
        for (Employee employee : employees) {
            workerCounts[employee.getType()]++;
        }
        // Actualizar la interfaz gráfica con la información correspondiente
        // Por ejemplo:
        ePB.setText(String.valueOf(workerCounts[0]));
        eCPU.setText(String.valueOf(workerCounts[1]));
        eRAM.setText(String.valueOf(workerCounts[2]));
        eFA.setText(String.valueOf(workerCounts[3]));
        aPB.setText(String.valueOf(workerCounts[4]));
        eE.setText(String.valueOf(workerCounts[5]));
    }
    
    
    
    
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        NodeComputadoras = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ePB = new javax.swing.JLabel();
        cG = new javax.swing.JLabel();
        eRAM = new javax.swing.JLabel();
        eFA = new javax.swing.JLabel();
        mE = new javax.swing.JLabel();
        aPB = new javax.swing.JLabel();
        aCPU = new javax.swing.JLabel();
        aRAM = new javax.swing.JLabel();
        NodeComputadoras1 = new javax.swing.JLabel();
        aTG = new javax.swing.JLabel();
        cE = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        eE = new javax.swing.JLabel();
        aFA = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        eCPU = new javax.swing.JLabel();
        HP = new javax.swing.JButton();
        iFA = new javax.swing.JButton();
        iPB = new javax.swing.JButton();
        iRAM = new javax.swing.JButton();
        iE = new javax.swing.JButton();
        iCPU = new javax.swing.JButton();
        eTG = new javax.swing.JLabel();
        iTG = new javax.swing.JButton();
        dTG = new javax.swing.JButton();
        dPB = new javax.swing.JButton();
        dRAM = new javax.swing.JButton();
        dE = new javax.swing.JButton();
        dCPU = new javax.swing.JButton();
        aCPU1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        ePB1 = new javax.swing.JLabel();
        ePB2 = new javax.swing.JLabel();
        ePB3 = new javax.swing.JLabel();
        mApple = new javax.swing.JLabel();
        mE2 = new javax.swing.JLabel();
        mE3 = new javax.swing.JLabel();
        mE4 = new javax.swing.JLabel();
        dFA1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Penalización");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, -1, -1));

        NodeComputadoras.setText("Computador con tarjeta gráfica:");
        getContentPane().add(NodeComputadoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, 190, 20));

        jLabel2.setText("Placa Base");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel3.setText("CPU");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel4.setText("Memoria RAM");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jLabel5.setText("Fuente de Alimentación");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel6.setText("Apple");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, -1, -1));

        jLabel7.setText("Costos");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, -1, -1));

        jLabel9.setText("Máximo:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 50, -1));

        ePB.setText("0");
        getContentPane().add(ePB, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 20, -1));

        cG.setText("0");
        getContentPane().add(cG, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 380, 50, -1));

        eRAM.setText("1");
        getContentPane().add(eRAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 10, -1));

        eFA.setText("1");
        getContentPane().add(eFA, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 10, -1));

        mE.setText("0");
        getContentPane().add(mE, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 410, 80, -1));

        aPB.setText("0 / 0");
        getContentPane().add(aPB, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 50, 20));

        aCPU.setText("0 / 0");
        getContentPane().add(aCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 50, -1));

        aRAM.setText("0 / 0");
        getContentPane().add(aRAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 50, -1));

        NodeComputadoras1.setText("Computador Estándar:");
        getContentPane().add(NodeComputadoras1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 360, 120, 20));

        aTG.setText("0 / 0");
        getContentPane().add(aTG, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 50, -1));

        cE.setText("0");
        getContentPane().add(cE, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 360, 50, -1));

        jLabel20.setText("Director");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        eE.setText("1");
        getContentPane().add(eE, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 10, -1));

        aFA.setText("0 / 0 ");
        getContentPane().add(aFA, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 240, 50, -1));

        jLabel23.setText("Tarjetas Gráficas");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel24.setText("Ensamblador");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        jLabel25.setText("Project Manager");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        eCPU.setText("1");
        getContentPane().add(eCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 10, -1));

        HP.setText("Ir a HP");
        HP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HPActionPerformed(evt);
            }
        });
        getContentPane().add(HP, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, -1));

        iFA.setText("+");
        iFA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iFAActionPerformed(evt);
            }
        });
        getContentPane().add(iFA, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, -1, -1));

        iPB.setText("+");
        iPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iPBActionPerformed(evt);
            }
        });
        getContentPane().add(iPB, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, -1, -1));

        iRAM.setText("+");
        iRAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iRAMActionPerformed(evt);
            }
        });
        getContentPane().add(iRAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, -1));

        iE.setText("+");
        iE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iEActionPerformed(evt);
            }
        });
        getContentPane().add(iE, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, -1, -1));

        iCPU.setText("+");
        iCPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iCPUActionPerformed(evt);
            }
        });
        getContentPane().add(iCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, -1, -1));

        eTG.setText("1");
        getContentPane().add(eTG, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 10, -1));

        iTG.setText("+");
        iTG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iTGActionPerformed(evt);
            }
        });
        getContentPane().add(iTG, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, -1, -1));

        dTG.setText("-");
        dTG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dTGActionPerformed(evt);
            }
        });
        getContentPane().add(dTG, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, -1, -1));

        dPB.setText("-");
        dPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dPBActionPerformed(evt);
            }
        });
        getContentPane().add(dPB, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, -1, -1));

        dRAM.setText("-");
        dRAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dRAMActionPerformed(evt);
            }
        });
        getContentPane().add(dRAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, -1));

        dE.setText("-");
        dE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dEActionPerformed(evt);
            }
        });
        getContentPane().add(dE, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, -1, -1));

        dCPU.setText("-");
        dCPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dCPUActionPerformed(evt);
            }
        });
        getContentPane().add(dCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, -1));

        aCPU1.setText("0 / 0");
        getContentPane().add(aCPU1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 50, -1));

        jLabel10.setText("Trabajadores");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, -1, -1));

        jLabel11.setText("Estado");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, -1, -1));

        jLabel12.setText("Estado");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, -1, -1));

        jLabel13.setText("Faltas");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, -1, -1));

        jLabel14.setText("Almacén");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, -1, -1));

        jLabel15.setText("Costos Operativos: ");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, -1, -1));

        jLabel16.setText("Ganancias");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, -1));

        jLabel17.setText("Ganancia Neta: ");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, -1, -1));

        jLabel18.setText("Ganancia Bruta: ");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, -1, -1));

        ePB1.setText("1");
        getContentPane().add(ePB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 20, -1));

        ePB2.setText("0");
        getContentPane().add(ePB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 20, -1));

        ePB3.setText("0");
        getContentPane().add(ePB3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 20, -1));

        mApple.setText("0");
        getContentPane().add(mApple, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 20, -1));

        mE2.setText("\"estado\"");
        getContentPane().add(mE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 80, -1));

        mE3.setText("\"estado\"");
        getContentPane().add(mE3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, 80, -1));

        mE4.setText("0");
        getContentPane().add(mE4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, 30, 20));

        dFA1.setText("-");
        dFA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dFA1ActionPerformed(evt);
            }
        });
        getContentPane().add(dFA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HPActionPerformed
      DashboardHP dHP = new DashboardHP(hp, apple);
      dHP.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_HPActionPerformed

    private void iFAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iFAActionPerformed
        apple.adjustEmployeeCount(3, 1);
        updateWorkerCount(apple.getEmployees());
        eFA.setText(String.valueOf(apple.getEmployeeCount(3)));
        
    }//GEN-LAST:event_iFAActionPerformed

    private void iPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iPBActionPerformed
        apple.adjustEmployeeCount(0, 1);
        updateWorkerCount(apple.getEmployees());
        ePB1.setText(String.valueOf(apple.getEmployeeCount(0)));
    }//GEN-LAST:event_iPBActionPerformed

    private void iRAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iRAMActionPerformed
        apple.adjustEmployeeCount(2, 1);
        updateWorkerCount(apple.getEmployees());
        eRAM.setText(String.valueOf(apple.getEmployeeCount(2)));
    }//GEN-LAST:event_iRAMActionPerformed

    private void iEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iEActionPerformed
        apple.adjustEmployeeCount(5, 1);
        updateWorkerCount(apple.getEmployees());
    eE.setText(String.valueOf(apple.getEmployeeCount(5)));
    }//GEN-LAST:event_iEActionPerformed

    private void iCPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iCPUActionPerformed
        apple.adjustEmployeeCount(1, 1);
        updateWorkerCount(apple.getEmployees());
    eCPU.setText(String.valueOf(apple.getEmployeeCount(1)));
    }//GEN-LAST:event_iCPUActionPerformed

    private void iTGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iTGActionPerformed
        apple.adjustEmployeeCount(4, 1);
        updateWorkerCount(apple.getEmployees());
    eTG.setText(String.valueOf(apple.getEmployeeCount(4)));
    }//GEN-LAST:event_iTGActionPerformed

    private void dTGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dTGActionPerformed
        apple.adjustEmployeeCount(4, -1);
       updateWorkerCount(apple.getEmployees());
    eTG.setText(String.valueOf(apple.getEmployeeCount(4)));
    }//GEN-LAST:event_dTGActionPerformed

    private void dPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dPBActionPerformed
        apple.adjustEmployeeCount(0, -1);

    updateWorkerCount(apple.getEmployees());
    ePB1.setText(String.valueOf(apple.getEmployeeCount(0)));
    }//GEN-LAST:event_dPBActionPerformed

    private void dRAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dRAMActionPerformed
        apple.adjustEmployeeCount(2, -1);
        updateWorkerCount(apple.getEmployees());
    eRAM.setText(String.valueOf(apple.getEmployeeCount(2)));
      
    }//GEN-LAST:event_dRAMActionPerformed

    private void dEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dEActionPerformed
        apple.adjustEmployeeCount(5, -1);
        updateWorkerCount(apple.getEmployees());
    eE.setText(String.valueOf(apple.getEmployeeCount(5)));
    }//GEN-LAST:event_dEActionPerformed

    private void dCPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dCPUActionPerformed
        apple.adjustEmployeeCount(1, -1);
        updateWorkerCount(apple.getEmployees());
    eCPU.setText(String.valueOf(apple.getEmployeeCount(1)));
    }//GEN-LAST:event_dCPUActionPerformed

    private void dFA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dFA1ActionPerformed
        apple.adjustEmployeeCount(3, -1);
        
    
    updateWorkerCount(apple.getEmployees());
        eFA.setText(String.valueOf(apple.getEmployeeCount(3)));
    }//GEN-LAST:event_dFA1ActionPerformed

  
    
    /**
     * @param args the command line arguments
     */
  
    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton HP;
    private javax.swing.JLabel NodeComputadoras;
    private javax.swing.JLabel NodeComputadoras1;
    private javax.swing.JLabel aCPU;
    private javax.swing.JLabel aCPU1;
    private javax.swing.JLabel aFA;
    private javax.swing.JLabel aPB;
    private javax.swing.JLabel aRAM;
    private javax.swing.JLabel aTG;
    private javax.swing.JLabel cE;
    private javax.swing.JLabel cG;
    private javax.swing.JButton dCPU;
    private javax.swing.JButton dE;
    private javax.swing.JButton dFA1;
    private javax.swing.JButton dPB;
    private javax.swing.JButton dRAM;
    private javax.swing.JButton dTG;
    private javax.swing.JLabel eCPU;
    private javax.swing.JLabel eE;
    private javax.swing.JLabel eFA;
    private javax.swing.JLabel ePB;
    private javax.swing.JLabel ePB1;
    private javax.swing.JLabel ePB2;
    private javax.swing.JLabel ePB3;
    private javax.swing.JLabel eRAM;
    private javax.swing.JLabel eTG;
    private javax.swing.JButton iCPU;
    private javax.swing.JButton iE;
    private javax.swing.JButton iFA;
    private javax.swing.JButton iPB;
    private javax.swing.JButton iRAM;
    private javax.swing.JButton iTG;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel mApple;
    private javax.swing.JLabel mE;
    private javax.swing.JLabel mE2;
    private javax.swing.JLabel mE3;
    private javax.swing.JLabel mE4;
    // End of variables declaration//GEN-END:variables
}
