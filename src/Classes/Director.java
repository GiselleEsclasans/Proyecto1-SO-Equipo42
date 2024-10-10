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