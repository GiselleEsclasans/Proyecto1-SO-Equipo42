/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author gigie
 */
public class Director extends Employee {
    private String status;
    
    public Director(String company, int id, int type, int days, int workDone, int salary, Storage WorkStorage) {
        super(company, id, type, days, workDone, salary, WorkStorage);
        this.status = "";
    }

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


