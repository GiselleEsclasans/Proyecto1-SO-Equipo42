/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectManager extends Thread {
    private String company;            // Compañía a la que pertenece
    private Semaphore mutex;           // Semáforo para sincronizar el trabajo
    private int dayDuration;           // Duración de un día en milisegundos (Data.dayDuration)
    private int salary = 40;           // Salario fijo del PM ($40 por hora)
    private float totalSalary;         // Salario total acumulado
    private int animeTime;             // Tiempo total para ver anime y revisar
    private int workTime;              // Tiempo total para trabajar
    private int halfCycle;             // Alternancia entre ver anime y revisar

    public ProjectManager(String company, Semaphore m, int dayDuration) {
        this.company = company;
        this.mutex = m;
        this.dayDuration = dayDuration; // Se pasa como argumento
        this.totalSalary = 0;
        this.animeTime = (16 * dayDuration) / 24;     // 16 horas simuladas
        this.workTime = (8 * dayDuration) / 24;       // 8 horas simuladas
        this.halfCycle = animeTime / (16 * 2);        // Cada ciclo de 30 minutos (simulado)
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Ciclo de ver anime y revisar el proyecto (animeTime en total)
                for (int t = 0; t < animeTime / (2 * halfCycle); t++) {
                    // Ver anime por halfCycle
                    System.out.println("Project Manager viendo anime...");
                    this.earnSalary();
                    sleep(halfCycle); // Duerme el tiempo de ver anime

                    // Revisar el proyecto por halfCycle
                    System.out.println("Project Manager revisando proyecto...");
                    this.earnSalary();
                    sleep(halfCycle); // Duerme el tiempo de revisión
                }

                // Ciclo de trabajar durante workTime
                for (int t = 0; t < workTime / 1000; t++) {
                    System.out.println("Project Manager trabajando en el proyecto...");
                    this.earnSalary();
                    sleep(1000); // Simula cada "hora" de trabajo
                }

                // Un día de trabajo ha pasado
                System.out.println("Project Manager ha completado un día de trabajo simulado.");
            } catch (InterruptedException ex) {
                Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Método para acumular salario
    public void earnSalary() {
        this.totalSalary += salary; // $40 por cada "hora" simulada
        System.out.println("Project Manager ha ganado: " + this.totalSalary + "$");
    }


    /**
     * @return the states
     */
  

    
    
}
