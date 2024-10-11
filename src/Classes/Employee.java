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
    private int dayDuration = Data.dayDuration;                         //5 segundos de duración
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
        throw new IllegalArgumentException("Compañía desconocida: " + this.getCompanyName());
        }   
    }

 
    int c = 0;
    @Override
    public void run() {
        while (true) {
            c++;
            try {
                System.out.println(c + "Dia");
                // Trabajar durante un día
                
                earnSalary();
                work();
                
                sleep(this.getDayDuration());
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); // Reestablecer la bandera de interrupción
                System.out.println("Hilo interrumpido");
                return;
            }
        }
    }
    
    
 
    public void earnSalary(){
        this.setTotalSalary(this.getTotalSalary() + this.getSalary() * 24);
    }
        
        public void work() {
            this.setProductionInterval(this.getProductionInterval() - 1);
        if (this.getProductionInterval() == 0) {
            try {
                this.getMutex().acquire();
                if (this.getType() == 5){
                    assemblePC();
                    this.setProductionInterval(Data.productionIntervals[0][this.getType()]);
                } else {
                    this.getWorkStorage().addToStorage(this.getProductionRate(), this.getWorkStorage());
                    if (this.getCompanyName() == "HP") {
                        this.setProductionRate(Data.productionRates[0][this.getType()]);
                        this.setProductionInterval(Data.productionIntervals[0][this.getType()]);
                    } else {
                        this.setProductionRate(Data.productionRates[1][this.getType()]);
                        this.setProductionInterval(Data.productionIntervals[1][this.getType()]);
                    }  
                }
               
                this.getMutex().release();
            } catch (InterruptedException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

        
   private void assemblePC() {
    int[] componentsNeeded = Data.componentsAssembleComputer[this.getCompanyName() == "HP" ? 0 : 1];

    Storage[] storages = this.getCompany().getStorages(); 
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
        int graphicCardPolicy = Data.graphicCard[this.getCompanyName() == "HP" ? 0 : 1][0];
        int graphicCardCount = Data.graphicCard[this.getCompanyName() == "HP" ? 0 : 1][1];
        
       
        if (storages[4].getCurrentCapacity() >= graphicCardCount) {
        if (    this.getCompany().getAssemblerCount() == graphicCardPolicy) {
            // Agregar tarjeta gráfica a la computadora
            this.getCompany().setAssemblerCount(0);
            storages[4].addToStorage(-graphicCardCount, storages[4]);
            this.getCompany().incrementGraphicComputerCount(); 
            
        } else {
            for (int i = 0; i < componentsNeeded.length; i++) {
            storages[i].addToStorage(-componentsNeeded[i], storages[i]);
        }
            this.getCompany().incrementComputerCount();
            this.getCompany().incrementAssemblerCount();
          
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
    
        
    
    
    public void resetSalary() {
    this.totalSalary = 0;
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

    /**
     * @return the id
     */
  

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
     * @return the acc
     */
    public float getAcc() {
        return acc;
    }

    /**
     * @param acc the acc to set
     */
    public void setAcc(float acc) {
        this.acc = acc;
    }

    /**
     * @return the nextComputerWithGraphicCard
     */
    public boolean isNextComputerWithGraphicCard() {
        return nextComputerWithGraphicCard;
    }

    /**
     * @param nextComputerWithGraphicCard the nextComputerWithGraphicCard to set
     */
    public void setNextComputerWithGraphicCard(boolean nextComputerWithGraphicCard) {
        this.nextComputerWithGraphicCard = nextComputerWithGraphicCard;
    }

    /**
     * @return the productionRate
     */
    public int getProductionRate() {
        return productionRate;
    }

    /**
     * @param productionRate the productionRate to set
     */
    public void setProductionRate(int productionRate) {
        this.productionRate = productionRate;
    }

    /**
     * @return the productionInterval
     */
    public int getProductionInterval() {
        return productionInterval;
    }

    /**
     * @param productionInterval the productionInterval to set
     */
    public void setProductionInterval(int productionInterval) {
        this.productionInterval = productionInterval;
    }

  
    
    
    
    
}
