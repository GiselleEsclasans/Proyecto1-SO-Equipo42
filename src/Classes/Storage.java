/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author gigie
 */
public class Storage {
    private int capacity;
    private int currentCapacity;
    private String productType;
    private Company company; 
   

    public Storage(int capacity, String productType, Company company) {
        this.capacity = capacity;
        this.currentCapacity = 0;
        this.productType = productType;
        this.company = company;
    }
    
    public synchronized void addToStorage(int type, Storage storage) {
        if (storage.getCurrentCapacity() + type <= storage.capacity) {
            //System.out.println("Ha creado " + type + " tipos de: " + storage.productType);
            this.currentCapacity = this.getCurrentCapacity() + type;
            System.out.println(this.company.getCompany() +" "+storage.productType + "Drive tiene: " + this.getCurrentCapacity() + " de " + this.capacity);
        } else {
            //System.out.println("Almacen lleno");
        }
    }
    
  
    
     public Company getCompany() {
        return this.company; // Devolvemos el campo company
    }
 

    public int getCapacity() {
        // Devolver la capacidad actual del almacen
        return this.getCurrentCapacity();
    }

    /**
     * @return the currentCapacity
     */
    public int getCurrentCapacity() {
        return currentCapacity;
    }
}
