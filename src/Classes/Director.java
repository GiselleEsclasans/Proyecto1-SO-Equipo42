/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import DataStructure.Data;

public class Director extends Thread {
    private String company;            // Compañía a la que pertenece
    private Semaphore mutex;           // Semáforo para sincronizar el trabajo
    private int salary = 60;           // Salario fijo del Director ($60 por hora)
    private float totalSalary;         // Salario total acumulado
    private ProjectManager projectManager; // Referencia al ProjectManager
    private int dayDuration;
    private int totalDays;


    public Director(String company, Semaphore m, ProjectManager pm) {
        this.company = company;
        this.mutex = m;
        this.projectManager = pm;  // Guarda la referencia al ProjectManager
        this.totalSalary = 0;
        this.dayDuration = Data.dayDuration;
        this.totalDays = Data.totalDays;
// =======
// public class Director extends Employee {
//     private String status;
    
//     public Director(Company company, int id, int type, int days, int workDone, int salary, Storage WorkStorage, Semaphore m) {
//         super(company, id, type, days, workDone, WorkStorage, m);
//         this.status = "";
// >>>>>>> main
    }

    @Override
    public void run() {
        while (true) {
            if (projectManager.getDaysLeft() == 0) {
                sendComputers();
                break; // Sale del bucle si termina el trabajo
            } else {
                doAdministrativeWork();

                // Elige una hora aleatoria para revisar al Project Manager
                Random random = new Random();
                int randomHour = random.nextInt(24); // Hora aleatoria del día

                try {
                    sleep(randomHour * (this.dayDuration / 24)); // Simula el tiempo hasta la hora elegida
                    reviewProjectManager(); // Revisa el trabajo del PM
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendComputers() {
        //System.out.println(company + " Director enviando computadoras a distribuidoras...");
        try {
            sleep(this.dayDuration); // Simula 24 horas en milisegundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(company + " Director ha enviado las computadoras.");
        projectManager.setDaysLeft(totalDays); // Reinicia el contador (ejemplo)
    }

    private void doAdministrativeWork() {
        //System.out.println(company + " Director realizando labores administrativas...");
        try {
            sleep(this.dayDuration); // Simula el tiempo de trabajo administrativo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void reviewProjectManager() {
        //System.out.println(company + " Director revisando el trabajo del Project Manager...");

        if (projectManager.isWatchingAnime()) {
            //System.out.println(company + " Director descubrió al Project Manager viendo anime!");
            // Cambiado para utilizar correctamente la referencia a projectManager
            projectManager.deductSalary(100); // Descuenta $100 si está viendo anime
            System.out.println("################################################################################################");
        } else {
            //System.out.println(company + " Project Manager está trabajando.");
        }
    }

}

//package Classes;
//
//import static java.lang.Thread.sleep;
//import java.util.Random;
//import java.util.concurrent.Semaphore;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class Director extends Thread {
//    private String company;            // Compañía a la que pertenece
//    private Semaphore mutex;           // Semáforo para sincronizar el trabajo
//    private int dayDuration;           // Duración de un día en milisegundos (Data.dayDuration)
//    private int salary = 60;           // Salario fijo del Director ($60 por hora)
//    private float totalSalary;         // Salario total acumulado
//    private int daysLeft;              // Días restantes para la entrega
//    private ProjectManager projectManager;
//
//    public Director(String company, Semaphore m, ProjectManager projectManager, int dayDuration, int daysLeft) {
//        this.company = company;
//        this.mutex = m;
//        this.dayDuration = dayDuration; // Se pasa como argumento
//        this.totalSalary = 0;
//        this.daysLeft = daysLeft;       // Inicializa días restantes para la entrega
//        this.projectManager = projectManager;
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            try {
//                if (daysLeft == 0) {
//                    // Enviar computadoras a las distribuidoras
//                    System.out.println(this.company + " Director enviando computadoras a distribuidoras...");
//                    sleep(24 * dayDuration); // Dura 24 horas
//                    System.out.println(this.company + " Director ha enviado las computadoras y registrado las ganancias.");
//                    daysLeft = 30; // Reinicia el contador (puedes cambiar el valor si es necesario)
//                } else {
//                    // Realizar labores administrativas
//                    System.out.println(this.company + " Director trabajando en labores administrativas...");
//                    this.earnSalary();
//                    sleep(dayDuration); // Trabaja durante todo el día
//
//                    // Revisar lo que está haciendo el PM
//                    Random random = new Random();
//                    int randomHour = random.nextInt(48); // Una hora aleatoria en el día simulado
//                    System.out.println(this.company + " Director revisando al PM en la hora: " + (randomHour / 2) + " horas.");
//                    sleep(randomHour * (dayDuration / 48)); // Duerme hasta la hora aleatoria
//
//                    // Comprobar si el PM está viendo anime
//                    if (randomHour % 2 == 0) { // Si es un número par, el PM está viendo anime
//                        System.out.println(this.company + " Director ha descubierto al PM viendo anime.");
//                        this.projectManager.deductSalary(100); // Descuenta $100
//                    }
//                }
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    // Método para acumular salario
//    public void earnSalary() {
//        this.totalSalary += salary; // $60 por cada "hora" simulada
//        System.out.println(this.company + " Director ha ganado: " + this.totalSalary + "$");
//    }
//    
//    public void setDaysLeft(int daysLeft) {
//        this.daysLeft = daysLeft;
//        
//        // Mensaje para ver
//        System.out.println("Días restantes actualizados en Director: " + this.daysLeft);
//    }
//}


//package Classes;
//
//import static java.lang.Thread.sleep;
//import java.util.Random;
//import java.util.concurrent.Semaphore;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class Director extends Thread {
//    private String company;            // Compañía a la que pertenece
//    private Semaphore mutex;           // Semáforo para sincronizar el trabajo
//    private int dayDuration;           // Duración de un día en milisegundos (Data.dayDuration)
//    private int salary = 60;           // Salario fijo del Director ($60 por hora)
//    private float totalSalary;         // Salario total acumulado
//    private int daysLeft;              // Días restantes para la entrega
//
//    public Director(String company, Semaphore m, int dayDuration, int daysLeft) {
//        this.company = company;
//        this.mutex = m;
//        this.dayDuration = dayDuration; // Se pasa como argumento
//        this.totalSalary = 0;
//        this.daysLeft = daysLeft;       // Inicializa días restantes para la entrega
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            try {
//                if (daysLeft == 0) {
//                    // Enviar computadoras a las distribuidoras
//                    System.out.println(this.company + " Director enviando computadoras a distribuidoras...");
//                    sleep(24 * dayDuration); // Dura 24 horas
//                    System.out.println(this.company + " Director ha enviado las computadoras y registrado las ganancias.");
//                    daysLeft = 30; // Reinicia el contador (puedes cambiar el valor si es necesario)
//                } else {
//                    // Realizar labores administrativas
//                    System.out.println(this.company + " Director trabajando en labores administrativas...");
//                    this.earnSalary();
//                    sleep(dayDuration); // Trabaja durante todo el día
//
//                    // Revisar lo que está haciendo el PM
//                    Random random = new Random();
//                    int randomHour = random.nextInt(48); // Una hora aleatoria en el día simulado
//                    System.out.println(this.company + " Director revisando al PM en la hora: " + (randomHour / 2) + " horas.");
//                    sleep(randomHour * (dayDuration / 48)); // Duerme hasta la hora aleatoria
//
//                    // Comprobar si el PM está viendo anime
//                    if (randomHour % 2 == 0) { // Si es un número par, el PM está viendo anime
//                        System.out.println(this.company + " Director ha descubierto al PM viendo anime.");
//                        ((ProjectManager) this.mutex).deductSalary(100); // Descuenta $100
//                    }
//                }
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    // Método para acumular salario
//    public void earnSalary() {
//        this.totalSalary += salary; // $60 por cada "hora" simulada
//        System.out.println(this.company + " Director ha ganado: " + this.totalSalary + "$");
//    }
//    
//    public void setDaysLeft(int daysLeft) {
//        this.daysLeft = daysLeft;
//        
//        // Mensaje para ver
//        System.out.println("Días restantes actualizados en Director: " + this.daysLeft);
//    }
//}

//package Classes;
//
//import java.util.Random;
//import java.util.concurrent.Semaphore;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class Director extends Thread {
//    private String company;            // Compañía a la que pertenece
//    private Semaphore mutex;           // Semáforo para sincronizar el trabajo
//    private int dayDuration;           // Duración de un día en milisegundos (Data.dayDuration)
//    private int salary = 60;           // Salario fijo del Director ($60 por hora)
//    private float totalSalary;         // Salario total acumulado
//    private ProjectManager projectManager; // Referencia al ProjectManager
//    private int daysLeft;
//
//    public Director(String company, Semaphore m, int dayDuration, ProjectManager pm, int daysLeft) {
//        this.company = company;
//        this.mutex = m;
//        this.dayDuration = dayDuration; // Se pasa como argumento
//        this.totalSalary = 0;
//        this.projectManager = pm; // Inicializa la referencia al ProjectManager
//        this.daysLeft = daysLeft;
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            try {
//                // Espera un día para ejecutar las tareas
//                sleep(dayDuration);
//
//                // Revisa los días restantes
//                int daysLeft = projectManager.getDaysLeft();
//                if (daysLeft <= 0) {
//                    // Enviar computadoras a las distribuidoras
//                    System.out.println(this.company + " Director enviando computadoras a las distribuidoras...");
//                    this.earnSalary(24); // Toma 24 horas, así que se acumula el salario por 24 horas
//                    // Aquí se registrarían las ganancias de los computadores enviados
//                    System.out.println(this.company + " Director ha registrado las ganancias.");
//                    // Reinicia el contador de días restantes (se asume que es reiniciado en el PM)
//                    projectManager.setDaysLeft(daysLeft); // Reiniciar a un nuevo plazo, por ejemplo, 10 días
//                } else {
//                    // Labores administrativas
//                    System.out.println(this.company + " Director trabajando en labores administrativas...");
//                    this.earnSalary(1); // Acumula el salario por un día
//
//                    // Revisar al ProjectManager
//                    Random random = new Random();
//                    int reviewHour = random.nextInt(24); // Hora aleatoria del día
//                    System.out.println(this.company + " Director revisará al Project Manager a las " + reviewHour + " horas...");
//
//                    // Simular esperar hasta la hora aleatoria
//                    sleep(reviewHour * (dayDuration / 24)); // Espera hasta la hora aleatoria
//
//                    // Durante 35 minutos revisar lo que está haciendo el PM
//                    System.out.println(this.company + " Director revisando al Project Manager...");
//                    boolean pmWatchingAnime = projectManager.isWatchingAnime();
//
//                    if (pmWatchingAnime) {
//                        // Descontar $100 del sueldo del PM
//                        projectManager.deductSalary(100);
//                        System.out.println(this.company + " Director ha encontrado al PM viendo anime y ha descontado $100 de su sueldo.");
//                        System.out.println("A");
//                        System.out.println("Q");
//                        System.out.println("U");
//                        System.out.println("I");
//                    }
//                    // Esperar 35 minutos (simular)
//                    sleep(this.dayDuration / 24 / 60 * 35); // Duerme durante 35 minutos
//                }
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    // Método para acumular salario
//    public void earnSalary(int hours) {
//        this.totalSalary += salary * hours; // $60 por cada "hora" simulada
//        System.out.println(this.company + " Director ha ganado: " + this.totalSalary + "$");
//    }
//}

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Classes;
//
///**
// *
// * @author gigie
// */
//
//
//import java.util.concurrent.Semaphore;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//
//public class Director extends Employee {
//    private String status;
//    
//    public Director(String company, int id, int type, int days, int workDone, int salary, Storage WorkStorage, Semaphore m) {
//        super(company, id, type, days, workDone, WorkStorage, m);
//        this.status = "";
//    }
//    
//    
//    
//    @Override
//    public void run() {
//        while (true) {
//            try {
//                //Pago
//
//                //35 minutos para vigilar PM
//                
//                //Días restantes
//                //Si dias = 0 enviar computadoras y calcular costo y ganancias, reinicia el contador
//		
//
//                    this.getMutex().release();
//
//                 // Si no,  labores administrativas y
//                  // supervisa al PM                     
//                      
//            
//                Thread.sleep(dayDuration);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }}
// 
//
//    /**
//     * @return the status
//     */
//    public String getStatus() {
//        return status;
//    }
//
//    /**
//     * @param status the status to set
//     */
//    public void setStatus(String status) {
//        this.status = status;
//    }
//    
//}
//
//
