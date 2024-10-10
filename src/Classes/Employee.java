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
    private String companyName;                 //Compañía que pertenece
    private int id;                 //Id del trabajador
    private int type;               //Tipo de trabajador
    private int days;               //Días en que termina su trabajo
    private int workDone;           //Numero de trabajos realizados
    private Storage WorkStorage;    //Almacen que pertenece
    private float work;             //Trabajo total realizado
    private Company company;
    
    private int salary;                     //Salario por hora
    private float totalSalary;                //Salario total acumulado
    
    private Semaphore mutex;                        //Semáforo
    int dayDuration = Data.dayDuration;                         //5 segundos de duración
    private float acc;
    
 
    private boolean nextComputerWithGraphicCard = false;
    
    private int productionRate; // Tasa de producción del trabajador
    private int productionInterval; // Intervalo de producción del trabajador
    
    
    public Employee(Company company, int id, int type, int days, int workDone, Storage workStorage, Semaphore m) {
        this.companyName = company.getCompany();
        this.company = company;
        this.id = id;
        this.type = type;
        this.days = days;
        this.workDone = workDone;
        this.WorkStorage = workStorage;
        this.totalSalary = 0;
        this.mutex = m;
        this.salary = Data.salary[this.type];
        this.acc = 0;
        

     
        
        if (this.companyName == "HP") {
            this.productionRate = Data.productionRates[0][this.type];
            this.productionInterval = Data.productionIntervals[0][this.type];
        } else if (this.companyName == "APPLE") {
            this.productionRate = Data.productionRates[1][this.type];
            this.productionInterval = Data.productionIntervals[1][this.type];
        } else {
        throw new IllegalArgumentException("Compañía desconocida: " + this.companyName);
        }   
    }

 
    
    @Override
    public void run() {
        while (true) {
            
            try {
                // Trabajar durante un día

                earnSalary();
                //System.out.println(this.companyName + " Trabajador: "+this.id+" ha ganado: "+this.totalSalary+"$");
                work();
              
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
                if (this.type == 5){
                    assemblePC();
                    this.productionInterval = Data.productionIntervals[0][this.type];
                } else {
                    this.WorkStorage.addToStorage(this.productionRate, this.WorkStorage);
                    if (this.companyName == "HP") {
                        this.productionRate = Data.productionRates[0][this.type];
                        this.productionInterval = Data.productionIntervals[0][this.type];
                    } else {
                        this.productionRate = Data.productionRates[1][this.type];
                        this.productionInterval = Data.productionIntervals[1][this.type];
                    }  
                }
               
                this.mutex.release();
            } catch (InterruptedException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

        
   private void assemblePC() {
    int[] componentsNeeded = Data.componentsAssembleComputer[this.companyName == "HP" ? 0 : 1];

    Storage[] storages = this.company.getStorages(); 
    int[] componentsAvailable = new int[componentsNeeded.length];
    for (int i = 0; i < componentsNeeded.length; i++) {
        componentsAvailable[i] = storages[i].getCurrentCapacity();
    }

    boolean allComponentsAvailable = true;
    for (int i = 0; i < componentsNeeded.length; i++) {
        if (componentsAvailable[i] < componentsNeeded[i] ) {
            allComponentsAvailable = false;
            break;
        }
    }
    
    if (allComponentsAvailable) {      
        //System.out.println(this.companyName+"Ensamblando...");
        // Verificar si se debe agregar tarjeta gráfica a la computadora
        int graphicCardPolicy = Data.graphicCard[this.companyName == "HP" ? 0 : 1][0];
        int graphicCardCount = Data.graphicCard[this.companyName == "HP" ? 0 : 1][1];
        
       
        if (storages[4].getCurrentCapacity() >= graphicCardCount) {
        if (this.company.getAssemblerCount() == graphicCardPolicy) {
            // Agregar tarjeta gráfica a la computadora
            this.company.setAssemblerCount(0);
            storages[4].addToStorage(-graphicCardCount, storages[4]);
            this.company.incrementGraphicComputerCount(); 
            
        } else {
            for (int i = 0; i < componentsNeeded.length; i++) {
            storages[i].addToStorage(-componentsNeeded[i], storages[i]);
        }
            this.company.incrementComputerCount();
            this.company.incrementAssemblerCount();
          
        }}else {
            // No hay suficientes tarjetas gráficas, no crear computadora con gráfica
            allComponentsAvailable = false;
        }
        
        if (!allComponentsAvailable) {
            //System.out.println(this.companyName+"No hay suficientes componentes.");
            return;
        }
    
        
        
        
    
   
    
        //System.out.println( "\n"+this.companyName+" Computer realizada!"+"\n");
    } else {
        //System.out.println(this.companyName+"No hay suficientes componentes.");
    }
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
