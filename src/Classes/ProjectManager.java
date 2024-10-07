/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gigie
 */
public class ProjectManager{
    private String states;
    private int faults;

    //public ProjectManager(String company, int id, int type, int days, int workDone, int salary, Storage workStorage, Semaphore m) {
        //super(company, id, type, days, workDone, salary, workStorage, m);
        //this.states = "";
        //this.faults = 0;
    //}
    
    //*@Override
   // public void run() {
       // while (true) {
         //   try {
                //primeras 16 horas cambia de estados
                //Pago
                //Actualiza días del contador
                //35 minutos para vigilar PM
                
            
            
            //    Thread.sleep(dayDuration);
           // } //catch (InterruptedException ex) {
            //    Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
           // }
       // }}
    
    
    

    /**
     * @return the states
     */
    public String getStates() {
        return states;
    }

    /**
     * @param states the states to set
     */
    public void setStates(String states) {
        this.states = states;
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
    
    
    
    
}
