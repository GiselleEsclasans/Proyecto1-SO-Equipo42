/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import DataStructure.Data;
import Interfaces.DashboardApple;
import Interfaces.DashboardHP;

public class Director extends Thread {
    private String company;            // Compañía a la que pertenece
    private Semaphore mutex;           // Semáforo para sincronizar el trabajo
    private int salary = 60;           // Salario fijo del Director ($60 por hora)
    private float totalSalary;         // Salario total acumulado
    private ProjectManager projectManager; // Referencia al ProjectManager
    private int dayDuration;
    private int totalDays;
    private String status;
    private DashboardApple dApple;
    private DashboardHP dHP;


    public Director(String company, Semaphore m, ProjectManager pm) {
        this.company = company;
        this.mutex = m;
        this.projectManager = pm;  // Guarda la referencia al ProjectManager
        this.totalSalary = 0;
        this.dayDuration = Data.dayDuration;
        this.totalDays = Data.totalDays;
        this.status = "Empezando trabajo.";
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
            if (getProjectManager().getDaysLeft() == 0) {
                sendComputers();
                break; // Sale del bucle si termina el trabajo
            } else {
                doAdministrativeWork();

                // Elige una hora aleatoria para revisar al Project Manager
                Random random = new Random();
                int randomHour = random.nextInt(this.getDayDuration() /24); // Hora aleatoria del día

                try {
                    sleep(randomHour * (this.getDayDuration() / 24)); // Simula el tiempo hasta la hora elegida
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
            sleep(this.getDayDuration()); // Simula 24 horas en milisegundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(company + " Director ha enviado las computadoras.");
        getProjectManager().setDaysLeft(getTotalDays()); // Reinicia el contador (ejemplo)
    }

    private void doAdministrativeWork() {

        updateStatus("Director realizando labores administrativas...");

        try {
            sleep(this.getDayDuration()); // Simula el tiempo de trabajo administrativo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void reviewProjectManager() {
        updateStatus("Director revisando el trabajo del Project Manager...");
        
        if (getProjectManager().isWatchingAnime()) {
            System.out.println(company + " Director descubrió al Project Manager viendo anime!");
            updateStatus("Director descubrió al Project Manager viendo anime!");
            // Cambiado para utilizar correctamente la referencia a projectManager
            getProjectManager().deductSalary(100); // Descuenta $100 si está viendo anime
        } else {
            //System.out.println(company + " Project Manager está trabajando.");
        }
    }
    
    public void updateStatus(String status) {
        if (dApple != null || dHP != null) {
            if (this.company == "APPLE") {
                dApple.updatePMStatus(status);
            } else {
                dHP.updatePMStatus(status);
            }
        }
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }
    
    public void resetSalary() {
    this.totalSalary = 0;
}

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the mutex
     */
    public Semaphore getMutex() {
        return mutex;
    }

    /**
     * @param mutex the mutex to set
     */
    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    /**
     * @return the salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * @return the totalSalary
     */
    public float getTotalSalary() {
        return totalSalary;
    }

    /**
     * @param totalSalary the totalSalary to set
     */
    public void setTotalSalary(float totalSalary) {
        this.totalSalary = totalSalary;
    }

    /**
     * @return the projectManager
     */
    public ProjectManager getProjectManager() {
        return projectManager;
    }

    /**
     * @param projectManager the projectManager to set
     */
    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    /**
     * @return the dayDuration
     */
    public int getDayDuration() {
        return dayDuration;
    }

    /**
     * @param dayDuration the dayDuration to set
     */
    public void setDayDuration(int dayDuration) {
        this.dayDuration = dayDuration;
    }

    /**
     * @return the totalDays
     */
    public int getTotalDays() {
        return totalDays;
    }

    /**
     * @param totalDays the totalDays to set
     */
    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }
    
    /**
     * @return the DashboardApple interface
     */
    public DashboardApple getWindowApple() {
        return this.dApple;
    }
    
    /**
     * @param dApple the DashboardApple interface
     */
    public void setWindowApple(DashboardApple dApple) {
        this.dApple = dApple;
    }
    
    /**
     * @return the DashboardApple interface
     */
    public DashboardHP getWindowHP() {
        return this.dHP;
    }
    
    /**
     * @param dApple the DashboardApple interface
     */
    public void setWindowHP(DashboardHP dHP) {
        this.dHP = dHP;
    }

}