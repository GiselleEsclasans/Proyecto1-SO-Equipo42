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
    private String company;                // Compañía a la que pertenece
    private Semaphore mutex;               // Semáforo para sincronizar el trabajo
    private int dayDuration;               // Duración de un día en milisegundos
    private int salary = 40;               // Salario fijo del PM ($40 por hora)
    private float totalSalary;             // Salario total acumulado
    private int daysLeft;                  // Días restantes para la entrega
    private boolean watchingAnime;          // Indica si está viendo anime

    public ProjectManager(String company, Semaphore m, int dayDuration, int daysLeft) {
        this.company = company;
        this.mutex = m;
        this.dayDuration = dayDuration;
        this.daysLeft = daysLeft;
        this.watchingAnime = false; // Inicialmente no está viendo anime
        this.totalSalary = 0;
    }

    @Override
    public void run() {
        while (daysLeft > 0) { // Mientras queden días para la entrega
            try {
// <<<<<<< AndresImery
                // Las primeras 16 horas del día
                for (int t = 0; t < 16; t += 0.5) { // Cada hora representa 0.5 en 30 minutos
                    watchingAnime = true; // Está viendo anime
                    //System.out.println(this.company + " Project Manager viendo anime...");
                    earnSalary(); // Gana salario mientras ve anime
                    sleep(dayDuration / 48); // Simula 30 minutos

                    watchingAnime = false; // Ahora trabaja
                    //System.out.println(this.company + " Project Manager revisando proyecto...");
                    earnSalary(); // Gana salario mientras trabaja
                    sleep(dayDuration / 48); // Simula 30 minutos
                }

                // Últimas 8 horas del día
                System.out.println(this.company + " Project Manager actualizando días restantes...");
                sleep(dayDuration / 24 * 8); // Simula 8 horas
                daysLeft--; // Reduce el contador de días
                System.out.println(this.company + " Días restantes para entregar las computadoras: " + daysLeft);

// =======
//                 // Ciclo de ver anime y revisar el proyecto (animeTime en total)
//                 for (int t = 0; t < animeTime / (2 * halfCycle); t++) {
//                     // Ver anime por halfCycle
//                     //System.out.println(this.company + " Project Manager viendo anime...");
//                     this.earnSalary();
//                     sleep(halfCycle); // Duerme el tiempo de ver anime

//                     // Revisar el proyecto por halfCycle
//                     //System.out.println(this.company + " Project Manager revisando proyecto...");
//                     this.earnSalary();
//                     sleep(halfCycle); // Duerme el tiempo de revisión
//                 }

//                 // Ciclo de trabajar durante workTime
//                 for (int t = 0; t < workTime / 1000; t++) {
//                     //System.out.println(this.company + " Project Manager trabajando en el proyecto...");
//                     this.earnSalary();
//                     sleep(1000); // Simula cada "hora" de trabajo
//                 }

//                 // Un día de trabajo ha pasado
//                 //System.out.println(this.company + " Project Manager ha completado un día de trabajo simulado.");
// >>>>>>> main
            } catch (InterruptedException ex) {
                Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Cuando no queden más días
        //System.out.println(this.company + " Project Manager ha terminado el proyecto. No quedan días restantes.");
    }

    // Método para acumular salario
    public void earnSalary() {
        this.totalSalary += salary; // $40 por cada "hora" simulada
        //System.out.println(this.company + " Project Manager ha ganado: " + this.totalSalary + "$");
    }

    // Getters y setters
    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
        //System.out.println("Días restantes actualizados en Project Manager: " + this.daysLeft);
    }

    public void deductSalary(int amount) {
        try {
            mutex.acquire(); // Adquiere el semáforo antes de modificar el salario
            this.totalSalary -= amount; // Descuenta el salario
            //System.out.println(this.company + " Project Manager ha tenido un descuento de $ " + amount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.release(); // Libera el semáforo después de modificar el salario
        }
    }

    public boolean isWatchingAnime() {
        return watchingAnime; // Devuelve si está viendo anime
    }
}
