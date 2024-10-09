/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Classes.Company;
import DataStructure.Data;

/**
 *
 * @author gigie
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int daysLeft = Data.totalDays;
        
        Company company = new Company("APPLE", 20, daysLeft);
        Company company2 = new Company("HP", 15, daysLeft);
        company.distributeEmployees();

        company.startWork();
        company2.distributeEmployees();
        company2.startWork();
    
}}
