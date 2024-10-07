/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author gigie
 */


import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Director extends Employee {
    private String status;
    
    public Director(String company, int id, int type, int days, int workDone, int salary, Storage WorkStorage, Semaphore m) {
        super(company, id, type, days, workDone, WorkStorage, m);
        this.status = "";
    }
    
    
    
    @Override
    public void run() {
        while (true) {
            try {
                //Pago

                //35 minutos para vigilar PM
                
                //Días restantes
                //Si dias = 0 enviar computadoras y calcular costo y ganancias, reinicia el contador
		

                    this.getMutex().release();

                 // Si no,  labores administrativas y
                  // supervisa al PM                     
                      
            
                Thread.sleep(dayDuration);
            } catch (InterruptedException ex) {
                Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
            }
        }}
 

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}


