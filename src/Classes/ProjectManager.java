/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import DataStructure.Data;
import Interfaces.DashboardApple;
import Interfaces.DashboardHP;
import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectManager extends Thread {
    private Company company;
    private String companyName;                // Compañía a la que pertenece
    private Semaphore mutex;               // Semáforo para sincronizar el trabajo
    private int dayDuration;               // Duración de un día en milisegundos
    private int salary = 40;               // Salario fijo del PM ($40 por hora)
    private float totalSalary;             // Salario total acumulado
    private int daysLeft;                  // Días restantes para la entrega
    private boolean watchingAnime;          // Indica si está viendo anime
    private DashboardApple dApple;
    private DashboardHP dHP;
    private int faults;
    private int totalDays;
    private float penalties;

    public ProjectManager(Company company, Semaphore m, int dayDuration, int daysLeft) {
        this.company = company;
        this.companyName = company.getCompany();
        this.mutex = m;
        this.dayDuration = dayDuration;
        this.daysLeft = daysLeft;
        this.watchingAnime = false; // Inicialmente no está viendo anime
        this.totalSalary = 0;
        this.faults = 0;
        this.totalDays = Data.totalDays;
        this.penalties = 0;
        
    }

    @Override
    public void run() {
        while (true) { // Mientras queden días para la entrega
            try {
                // Las primeras 16 horas del día
                
                
                for (float t = 0; t < 16 * (getDayDuration()/24); t += 1 * (getDayDuration()/24)) { // Cada hora representa 0.5 en 30 minutos
                    setWatchingAnime(true); // Está viendo anime
                    //System.out.println(this.companyName + " Project Manager viendo anime...");
                    
                    this.updateStatus("Project Manager viendo anime...");
                    earnSalary(); // Gana salario mientras ve anime
                    sleep(30 * (getDayDuration()/1440)); // Simula 30 minutos

                    setWatchingAnime(false); // Ahora trabaja
                    //System.out.println(this.companyName + " Project Manager revisando proyecto...");
                    this.updateStatus("Project Manager revisando proyecto...");
                    earnSalary(); // Gana salario mientras trabaja
                    sleep(30 * (getDayDuration()/1440)); // Simula 30 minutos
                  
                    
                }
                

                // Últimas 8 horas del día
                //System.out.println(this.companyName + " Project Manager actualizando días restantes...");
                this.updateStatus("Project Manager actualizando días restantes...");
                sleep(8 * (getDayDuration()/24)); // Simula 8 horas
                getCompany().calculateOperativeCost(); // Calcular el costo operativo al final del día
                setDaysLeft(getDaysLeft() - 1); // Reduce el contador de días
                //System.out.println(this.companyName + " Días restantes para entregar las computadoras: " + daysLeft);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (getDaysLeft() == 0) {
            // En lugar de detenerse, reinicia el contador de días
                setDaysLeft(getTotalDays());
                getCompany().setDaysLeft(getTotalDays());
                getCompany().setComputerCount(0);
                getCompany().setGraphicComputerCount(0);
            }
        }

        // Cuando no queden más días
        //System.out.println(this.companyName + " Project Manager ha terminado el proyecto. No quedan días restantes.");
    }
    
    public void updateStatus(String status) {
        if (getdApple() != null || getdHP() != null) {
            if (this.getCompanyName() == "APPLE") {
                getdApple().updatePMStatus(getDaysLeft() + " días. " + status);
            } else {
                getdHP().updatePMStatus(getDaysLeft() + " días. " + status);
            }
        }
    }
    
    public void updateFaultsAndPenalties() {
        if (getdApple() != null || getdHP() != null) {
            if (this.getCompanyName() == "APPLE") {
                getdApple().updatePMFaults(this.getFaults());
                getdApple().updatePMPenalties(this.getPenalties());
            } else {
                getdHP().updatePMFaults(this.getFaults());
                getdHP().updatePMPenalties(this.getPenalties());
            }
        }
    }

    // Método para acumular salario
    public void earnSalary() {
        this.setTotalSalary(this.getTotalSalary() + getSalary()); // $40 por cada "hora" simulada
        //System.out.println(this.companyName + " Project Manager ha ganado: " + this.totalSalary + "$");
    }

    // Getters y setters
    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
        //System.out.println("Días restantes actualizados en Project Manager: " + this.daysLeft);
    }

    public void resetSalary() {
        this.setTotalSalary(0);
    }

    public void deductSalary(int amount) {
        try {
            getMutex().acquire(); // Adquiere el semáforo antes de modificar el salario
            this.setTotalSalary(this.getTotalSalary() - amount); // Descuenta el salario
            //System.out.println(this.companyName + " Project Manager ha tenido un descuento de $ " + amount);
            this.setFaults(this.getFaults() + 1);
            this.setPenalties(- amount);
            updateFaultsAndPenalties();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            getMutex().release(); // Libera el semáforo después de modificar el salario
        }
    }


    public boolean isWatchingAnime() {
        return watchingAnime; // Devuelve si está viendo anime
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
     * @param watchingAnime the watchingAnime to set
     */
    public void setWatchingAnime(boolean watchingAnime) {
        this.watchingAnime = watchingAnime;
    }

    /**
     * @return the company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * @return the DashboardApple interface
     */
    public DashboardApple getWindowApple() {
        return this.getdApple();
    }
    
    /**
     * @param dApple the DashboardApple interface
     */
    public void setWindowApple(DashboardApple dApple) {
        this.setdApple(dApple);
    }
    
    /**
     * @return the DashboardApple interface
     */
    public DashboardHP getWindowHP() {
        return this.getdHP();
    }
    
    /**
     * @param dApple the DashboardApple interface
     */
    public void setWindowHP(DashboardHP dHP) {
        this.setdHP(dHP);
    }

    /**
     * @return the dApple
     */
    public DashboardApple getdApple() {
        return dApple;
    }

    /**
     * @param dApple the dApple to set
     */
    public void setdApple(DashboardApple dApple) {
        this.dApple = dApple;
    }

    /**
     * @return the dHP
     */
    public DashboardHP getdHP() {
        return dHP;
    }

    /**
     * @param dHP the dHP to set
     */
    public void setdHP(DashboardHP dHP) {
        this.dHP = dHP;
    }

    /**
     * @return the faults
     */
    public int getFaults() {
        return faults;
    }

    /**
     * @param faults the faults to set
     */
    public void setFaults(int faults) {
        this.faults = faults;
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
     * @return the penalties
     */
    public float getPenalties() {
        return penalties;
    }

    /**
     * @param penalties the penalties to set
     */
    public void setPenalties(float penalties) {
        this.penalties = penalties;
    }
}
