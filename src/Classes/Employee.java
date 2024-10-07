/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author gigie
 */

import DataStructure.Data;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

 
public class Employee extends Thread {
    private String company;                 //Compañía que pertenece
    private int id;                 //Id del trabajador
    private int type;               //Tipo de trabajador
    private int days;               //Días en que termina su trabajo
    private int workDone;           //Numero de trabajos realizados
    private Storage WorkStorage;    //Almacen que pertenece
    private float work;             //Trabajo total realizado
    
    
    private int salary;                     //Salario por hora
    private float totalSalary;                //Salario total acumulado
    
    private Semaphore mutex;                        //Semáforo
    int dayDuration = Data.dayDuration;                         //5 segundos de duración
    private float acc;
    
 
    
    private int productionRate; // Tasa de producción del trabajador
    private int productionInterval; // Intervalo de producción del trabajador
    
    public Employee(String company, int id, int type, int days, int workDone, Storage workStorage, Semaphore m) {
        this.company = company;
        this.id = id;
        this.type = type;
        this.days = days;
        this.workDone = workDone;
        this.WorkStorage = workStorage;
        this.totalSalary = 0;
        this.mutex = m;

        //  tasa de producción, el intervalo de producción y el salario según el tipo de trabajador
        this.productionRate = Data.productionRates[this.type];
        this.productionInterval = Data.productionIntervals[this.type];
        this.salary = Data.salary[this.type];
        this.acc = 0;
    }

 
    
    @Override
    public void run() {
        while (true) {
            
            try {
                // Trabajar durante un día

                earnSalary();
                System.out.println("Trabajador: "+this.id+" ha ganado: "+this.totalSalary+"$");
                work();
                // Dormir durante un día
                sleep(this.dayDuration);
            } catch (InterruptedException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
 
    public void earnSalary(){
            this.totalSalary += this.salary*24;
    }
        
        public void work() {
        this.productionInterval--;
        if (this.productionInterval == 0) {
            try {
                this.mutex.acquire();
                this.WorkStorage.addToStorage(this.productionRate, this.WorkStorage);
                this.productionInterval = Data.productionIntervals[this.type];
                this.mutex.release();
            } catch (InterruptedException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

        
        
        
   

    
        
    
    
    
    
    
    
    
    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

   

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the days
     */
    public int getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(int days) {
        this.days = days;
    }

    /**
     * @return the workDone
     */
    public int getWorkDone() {
        return workDone;
    }

    /**
     * @param workDone the workDone to set
     */
    public void setWorkDone(int workDone) {
        this.workDone = workDone;
    }

    /**
     * @return the WorkStorage
     */
    public Storage getWorkStorage() {
        return WorkStorage;
    }

    /**
     * @param WorkStorage the WorkStorage to set
     */
    public void setWorkStorage(Storage WorkStorage) {
        this.WorkStorage = WorkStorage;
    }

    /**
     * @return the work
     */
    public float getWork() {
        return work;
    }

    /**
     * @param work the work to set
     */
    public void setWork(float work) {
        this.work = work;
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
    
    
    
    
}
