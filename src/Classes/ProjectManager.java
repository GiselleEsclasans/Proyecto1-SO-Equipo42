/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author gigie
 */
public class ProjectManager extends Employee {
    private String states;
    private int faults;

    public ProjectManager(String company, int id, int type, int days, int workDone, int salary, Storage workStorage) {
        super(company, id, type, days, workDone, salary, workStorage);
        this.states = "";
        this.faults = 0;
    }

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
