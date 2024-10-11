/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Classes.Company;
import Interfaces.Inicio;
import DataStructure.Data;


/**
 *
 * @author gigie
 */
public class App {
    private static App instance;
    public static Company hp;
    public static Company apple;

    private App() {}

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
            hp = new Company("HP", 20, Data.daysLeft);
            apple = new Company("APPLE", 15, Data.daysLeft);
        }
        return instance;
    }

    public void start() {
        Inicio inicio = new Inicio(null, null);
//        hp.distributeEmployees();
//        apple.distributeEmployees();
//        hp.startWork();
//        apple.startWork();
        inicio.setVisible(true);
    }
}

