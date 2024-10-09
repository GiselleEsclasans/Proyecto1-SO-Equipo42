/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import DataStructure.Data;
import java.util.concurrent.Semaphore;

public class Company {
    private String company;
    private int totalEmployees;
    private Employee[] employees;
    private Storage[] storages;
    private int dayCount;
    private ProjectManager projectManager;
    private Semaphore mutex;
    private int daysLeft;

    public Company(String company, int totalEmployees, int daysLeft) {
        this.company = company;
        this.totalEmployees = totalEmployees;
        this.employees = new Employee[totalEmployees];
        this.storages = new Storage[5]; // 5 tipos de trabajadores
        this.mutex = new Semaphore(1);
        this.daysLeft = daysLeft; // Inicializa días restantes (puedes ajustar este valor)

        // Inicializar los almacenes con capacidades fijas
        for (int i = 0; i < 5; i++) {
            Storage storage = new Storage(Data.storageCapacities[i], Data.producerTypes[i]);
            this.storages[i] = storage;
        }

        this.projectManager = new ProjectManager(this.company, this.mutex, Data.dayDuration, this.daysLeft);
    }

    public void distributeEmployees() {
        // Distribuir el total de empleados de la compañía entre los 5 tipos de trabajadores y los ensambladores de las computadoras
        int[] employeeCounts = new int[5]; // Contadores de empleados por tipo

        // Asignar un trabajador de cada tipo como mínimo
        for (int i = 0; i < 5; i++) {
            employeeCounts[i] = 1;
        }

        // Distribuir los empleados restantes
        int remainingEmployees = this.totalEmployees - 5;
        for (int i = 0; i < remainingEmployees; i++) {
            int type = (int) (Math.random() * 5);
            employeeCounts[type]++;
        }

        // Crear los empleados
        int employeeIndex = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < employeeCounts[i]; j++) {
                Employee employee = new Employee(this.company, employeeIndex, i, 0, 0, this.storages[i], new Semaphore(1));
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
        System.out.println("Project started for " + this.company);
    }

    public void updateDaysLeft(int days) {
        this.daysLeft = days; // Actualiza los días restantes
        this.projectManager.setDaysLeft(days); // Sincroniza el Project Manager con los nuevos días
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
}

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Classes;
//
//import DataStructure.Data;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.concurrent.Semaphore;
//import javax.swing.Timer;
//
//
///**
// *
// * @author gigie
// */
//public class Company {
//    private String company;
//    private int totalEmployees;
//    private Employee[] employees;
//    private Storage[] storages;
//    private int dayCount;
//    private ProjectManager projectManager;
//    private Semaphore mutex;
//
//    public Company(String company, int totalEmployees) {
//        this.company = company;
//        this.totalEmployees = totalEmployees;
//        this.employees = new Employee[totalEmployees];
//        this.storages = new Storage[5]; // 5 tipos de trabajadores
//        this.mutex = new Semaphore(1);
//
//        // Inicializar los almacenes con capacidades fijas
//        for (int i = 0; i < 5; i++) {
//            Storage storage = new Storage(Data.storageCapacities[i], Data.producerTypes[i]);
//            storage.getCapacity();
//            this.storages[i] = storage;
//            
//        }
//        
//        this.projectManager = new ProjectManager(this.company, this.mutex, Data.dayDuration);
//
//    }
//
//    public void distributeEmployees() {
//        // Distribuir el total de empleados de la compañía entre los 5 tipos de trabajadores y los ensambladores de las computadoras
//        int[] employeeCounts = new int[5]; // Contadores de empleados por tipo
//
//        // Asignar un trabajador de cada tipo como mínimo
//        for (int i = 0; i < 5; i++) {
//            employeeCounts[i] = 1;
//        }
//
//        // Distribuir los empleados restantes
//        int remainingEmployees = this.totalEmployees - 5;
//        for (int i = 0; i < remainingEmployees; i++) {
//            int type = (int) (Math.random() * 5);
//            employeeCounts[type]++;
//        }
//
//        // Crear los empleados
//        int employeeIndex = 0;
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < employeeCounts[i]; j++) {
//                Employee employee = new Employee(this.company, employeeIndex, i, 0, 0, this.storages[i], new Semaphore(1));
//                this.employees[employeeIndex] = employee;
//                employeeIndex++;
//            }
//        }
//    }
//    
//    
//    public void startWork() {
//        for (Employee employee : employees) {
//            employee.start();
//        }
//
//        this.projectManager.start();
//        System.out.println("here");
//    }
//
//  
//
//
//    public Employee[] getEmployees() {
//        return employees;
//    }
//
//    public Storage[] getStorages() {
//        return storages;
//    }
//    
//    public ProjectManager getProjectManager() {
//        return projectManager;
//    }
//    
//    
//    public void printEmployeeCounts() {
//        int[] employeeCounts = new int[5]; // Contadores de empleados por tipo
//
//        // Contar los empleados por tipo
//        for (Employee employee : this.employees) {
//            employeeCounts[employee.getType()]++;
//        }
//
//        // Imprimir los contadores
//        for (int i = 0; i < 5; i++) {
//            System.out.println("Tipo " + i + ": " + employeeCounts[i] + " empleados");
//        }
//    }
//    
//}
