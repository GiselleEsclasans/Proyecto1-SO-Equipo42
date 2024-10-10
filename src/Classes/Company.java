/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import DataStructure.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;
import javax.swing.Timer;


/**
 *
 * @author gigie
 */
public class Company {
    private String company;
    private int totalEmployees;
    private Employee[] employees;
    private Storage[] storages;
    private int dayCount;
    private ProjectManager projectManager;
    private Semaphore mutex;
    
    private int computerCount;
    private int graphicComputerCount;
    private int assemblerCount;
    
    public Company(String company, int totalEmployees) {
        this.company = company;
        this.totalEmployees = totalEmployees;
        this.employees = new Employee[totalEmployees];
        this.storages = new Storage[5]; // 5 tipos de trabajadores
        this.mutex = new Semaphore(1);

        // Inicializar los almacenes con capacidades fijas
        for (int i = 0; i < 5; i++) {
            Storage storage = new Storage(Data.storageCapacities[i], Data.producerTypes[i], this);
            storage.getCapacity();
            this.storages[i] = storage;
            
        }
        
        this.projectManager = new ProjectManager(this.getCompany(), this.mutex, Data.dayDuration);
        this.computerCount = 0;
        this.graphicComputerCount = 0;
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
        //System.out.println("here");
    }

    
    public void incrementComputerCount() {
        this.computerCount++;
        System.out.println("\n\n"+this.company + "Computadoras normales"+this.computerCount+ "\n");
    }
    
    public void incrementGraphicComputerCount() {
        this.graphicComputerCount++;
        System.out.println("\n\n" + this.company + " Computadoras con gráficas "+this.getGraphicComputerCount() + "\n");
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
    
    
    
    
  


    public Employee[] getEmployees() {
        return employees;
    }

    public Storage[] getStorages() {
        return storages;
    }
    
    public ProjectManager getProjectManager() {
        return projectManager;
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
