/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectManager extends Thread {
    private String company;            // Compañía a la que pertenece
    private Semaphore mutex;           // Semáforo para sincronizar el trabajo
    private int dayDuration;           // Duración de un día en milisegundos (Data.dayDuration)
    private int salary = 40;           // Salario fijo del PM ($40 por hora)
    private float totalSalary;         // Salario total acumulado
    private int animeTime;             // Tiempo total para ver anime y revisar
    private int workTime;              // Tiempo total para trabajar
    private int halfCycle;             // Alternancia entre ver anime y revisar

    public ProjectManager(String company, Semaphore m, int dayDuration) {
        this.company = company;
        this.mutex = m;
        this.dayDuration = dayDuration; // Se pasa como argumento
        this.totalSalary = 0;
        this.animeTime = (16 * dayDuration) / 24;     // 16 horas simuladas
        this.workTime = (8 * dayDuration) / 24;       // 8 horas simuladas
        this.halfCycle = animeTime / (16 * 2);        // Cada ciclo de 30 minutos (simulado)
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Ciclo de ver anime y revisar el proyecto (animeTime en total)
                for (int t = 0; t < animeTime / (2 * halfCycle); t++) {
                    // Ver anime por halfCycle
                    System.out.println("Project Manager viendo anime...");
                    this.earnSalary();
                    sleep(halfCycle); // Duerme el tiempo de ver anime

                    // Revisar el proyecto por halfCycle
                    System.out.println("Project Manager revisando proyecto...");
                    this.earnSalary();
                    sleep(halfCycle); // Duerme el tiempo de revisión
                }

                // Ciclo de trabajar durante workTime
                for (int t = 0; t < workTime / 1000; t++) {
                    System.out.println("Project Manager trabajando en el proyecto...");
                    this.earnSalary();
                    sleep(1000); // Simula cada "hora" de trabajo
                }

                // Un día de trabajo ha pasado
                System.out.println("Project Manager ha completado un día de trabajo simulado.");
            } catch (InterruptedException ex) {
                Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Método para acumular salario
    public void earnSalary() {
        this.totalSalary += salary; // $40 por cada "hora" simulada
        System.out.println("Project Manager ha ganado: " + this.totalSalary + "$");
    }
}

    // Método para simular que el PM está viendo anime
//    private void watchAnime() {
//        try {
//            System.out.println("PM viendo anime durante 30 minutos.");
//            earnSalary(0.5f); // Gana dinero mientras ve anime (30 minutos)
//            sleep(this.dayDuration / 48); // 30 minutos de 16 horas (30 minutos es 1/48 de un día completo)
//        } catch (InterruptedException ex) {
//            Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    // Método para revisar el avance del proyecto durante 30 minutos
//    private void reviewProgress() {
//        try {
//            System.out.println("PM revisando el progreso del proyecto durante 30 minutos.");
//            earnSalary(0.5f); // Gana dinero mientras revisa el progreso (30 minutos)
//            sleep(this.dayDuration / 48); // 30 minutos de revisión
//        } catch (InterruptedException ex) {
//            Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    // Método para actualizar el contador de días restantes
//    private void updateDaysLeft() {
//        try {
//            this.mutex.acquire(); // Adquirir el semáforo para acceder al contador de días
//            if (daysLeft > 0) {
//                daysLeft--; // Decrementar los días restantes
//                System.out.println("PM actualizando los días restantes. Días restantes: " + daysLeft);
//            }
//            this.mutex.release(); // Liberar el semáforo
//            earnSalary(1); // Gana dinero durante una hora de trabajo
//            sleep(this.dayDuration / 24); // Dormir por 1 hora (parte de las 8 horas restantes)
//        } catch (InterruptedException ex) {
//            Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    // Método para calcular el salario del PM según las horas trabajadas
//    private void earnSalary(float hours) {
//        this.totalSalary += this.salary * hours; // Gana $40 por cada hora, ya sea viendo anime o trabajando
//        System.out.println("PM ha ganado: " + this.totalSalary + "$ hasta ahora.");
//        hoursWorked += hours;
//    }
//
//    // Método para obtener los días restantes
//    public int getDaysLeft() {
//        return daysLeft;
//    }
//
//    // Método para obtener el salario total acumulado
//    public float getTotalSalary() {
//        return totalSalary;
//    }
//}




//package Classes;
//
//import java.util.concurrent.Semaphore;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author gigie
// */
//public class ProjectManager{
//    private String states;
//    private int faults;
//
//    //public ProjectManager(String company, int id, int type, int days, int workDone, int salary, Storage workStorage, Semaphore m) {
//        //super(company, id, type, days, workDone, salary, workStorage, m);
//        //this.states = "";
//        //this.faults = 0;
//    //}
//    
//    //*@Override
//   // public void run() {
//       // while (true) {
//         //   try {
//                //primeras 16 horas cambia de estados
//                //Pago
//                //Actualiza días del contador
//                //35 minutos para vigilar PM
//                
//            
//            
//            //    Thread.sleep(dayDuration);
//           // } //catch (InterruptedException ex) {
//            //    Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
//           // }
//       // }}
//    
//    
//    
//
//    /**
//     * @return the states
//     */
//    public String getStates() {
//        return states;
//    }
//
//    /**
//     * @param states the states to set
//     */
//    public void setStates(String states) {
//        this.states = states;
//    }
//
//    /**
//     * @return the faults
//     */
//    public int getFaults() {
//        return faults;
//    }
//
//    /**
//     * @param faults the faults to set
//     */
//    public void setFaults(int faults) {
//        this.faults = faults;
//    }
//    
//    
//    
//    
//}
