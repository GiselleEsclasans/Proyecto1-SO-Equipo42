/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import DataStructure.Data;
import Interfaces.DashboardHP;
import Interfaces.DashboardApple;
import Main.App;
import java.util.concurrent.Semaphore;

public class Company {
    private String company;
    private int totalEmployees;
    private int actualEmployees;
    private Employee[] employees;
    private Employee[] ePlacaBase;
    private Employee[] eCPU;
    private Employee[] eRAM;
    private Employee[] eFA;
    private Employee[] eTG;
    private Employee[] assemblers;
    private Storage[] storages;
    private int dayCount;
    private ProjectManager projectManager;
    private Director director;
    private int daysLeft;
  
    private static Storage storage;
    private Semaphore mutex;
    
    private float cost = 0;
    private float earning = 0;
    private float profit = 0;
    
    private int days = 0; 
  
    
    private int computerCount;
    private int graphicComputerCount;
    private int assemblerCount;
    
    private int totalComputers;
    private int totalGraphipComputers;
    

  
    public Company(String company, int totalEmployees, int daysLeft) {

        this.company = company;
        this.totalEmployees = totalEmployees;
        this.employees = new Employee[totalEmployees];
        this.storages = new Storage[5]; // 5 tipos de trabajadores
        this.mutex = new Semaphore(1);

        this.computerCount = 0;
        this.graphicComputerCount = 0;

        this.daysLeft = daysLeft; // Inicializa días restantes (puedes ajustar este valor)



        
        // Inicializar los almacenes con capacidades fijas
        for (int i = 0; i < 5; i++) {
            Storage storage = new Storage(Data.storageCapacities[i], Data.producerTypes[i], this);
            storage.getCapacity();

            this.storages[i] = storage;
        }


        this.projectManager = new ProjectManager(this.company, this.mutex, Data.dayDuration, this.daysLeft);
        this.director = new Director(this.company, this.mutex, this.projectManager);

    }

    public void distributeEmployees() {
        // Distribuir el total de empleados de la compañía entre los 5 tipos de trabajadores y los ensambladores de las computadoras
        int[] employeeCounts = new int[6]; // Contadores de empleados por tipo

        // Asignar un trabajador de cada tipo como mínimo
        for (int i = 0; i < 6; i++) {
            employeeCounts[i] = 1;
        }

        // Distribuir los empleados restantes
        int remainingEmployees = this.totalEmployees - 6;
        for (int i = 0; i < remainingEmployees; i++) {
            int type = (int) (Math.random() * 6);
            employeeCounts[type]++;
        }

        // Crear los empleados
        int employeeIndex = 0;
        for (int i = 0; i < 6; i++) {
            Storage storage = (i < 5) ? this.storages[i] : null;
            for (int j = 0; j < employeeCounts[i]; j++) {
                Employee employee = new Employee(this, employeeIndex, i, 0, 0, storage, new Semaphore(1));
                this.employees[employeeIndex] = employee;
                employeeIndex++;
            }
        }
    }

    public void startWork() {
       
        for (Employee employee : employees) {
            employee.start();
        }
        
        this.projectManager.start();
        this.director.start();
        System.out.println("Project started for " + this.company);
    }

    public void updateDaysLeft(int days) {
        this.daysLeft = days; // Actualiza los días restantes
        this.projectManager.setDaysLeft(days); // Sincroniza el Project Manager con los nuevos días
    }


    
    public void incrementComputerCount() {
    this.computerCount++;
    System.out.println("\n\n" + this.company + " Computadoras normales " + this.computerCount + "\n");
    // Update the dashboard
   
}   

public void incrementGraphicComputerCount() {
    this.graphicComputerCount++;
    System.out.println("\n\n" + this.company + " Computadoras con gráficas " + this.getGraphicComputerCount() + "\n");
    // Update the dashboard
   
}
    
    public void incrementAssemblerCount() {
        this.assemblerCount++;
       
    }
  
    
  
  
  
    
    public int getComputerCount() {
        return this.computerCount;
    }
    
       public String getCompany() {
        return company;
    }
       
       public void setComputerCount(int count) {
        computerCount = count;
    }
    
    
    
    
  



    public Employee[] getEmployees() {
        return employees;
    }

    public Storage[] getStorages() {
        return storages;
    }
    
    public ProjectManager getProjectManager() {
        return projectManager;
    }

    public int getDaysLeft() {
        return daysLeft; // Getter para días restantes
    }

    public void printEmployeeCounts() {
        int[] employeeCounts = new int[5]; // Contadores de empleados por tipo

        // Contar los empleados por tipo
        for (Employee employee : this.employees) {
            employeeCounts[employee.getType()]++;
        }

        // Imprimir los contadores
        for (int i = 0; i < 5; i++) {
            System.out.println("Tipo " + i + ": " + employeeCounts[i] + " empleados");
        }
    }

    /**
     * @return the graphicComputerCount
     */
    public int getGraphicComputerCount() {
        return graphicComputerCount;
    }

    /**
     * @return the assemblerCount
     */
    public int getAssemblerCount() {
        return assemblerCount;
    }

    /**
     * @param assemblerCount the assemblerCount to set
     */
    public void setAssemblerCount(int assemblerCount) {
        this.assemblerCount = assemblerCount;
    }
    
}

