/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataStructure;

/**
 *
 * @author gigie
 */
public class Data {
    public static int dayDuration = 2000;                        //5 segundos de duración
   
    
    public static int totalDays = 30;
    
    public final static int[] salary = {
        20, 26, 40, 16, 34, 50, 40, 60  
    };
    
    
    public final static String[] producerTypes = {
        "Placa Base",
        "CPUs",
        "RAM",
        "Fuente de Alimentación",
        "Tarjetas Gráficas",
        "Ensamblador",
        "Project Manager",
        "Director"
    };

    public final static int[][] profitTypeComputer = {
         {90000, 140000}, {100000, 150000}                  //Hp  --- Apple    Estandar/Tarjeta Gráfica 
     };
    
    
    public final static int[][] componentsAssembleComputer = {
         {1, 1, 2, 4}, {2, 1, 4, 4}                  //Hp  --- Apple
     };
    
    public final static int[][] graphicCard = {
        {2,3}, {5,2}                                //Hp  ---  Apple
    };  
    
    public final static int[][] productionRates = {
        {1, 1, 2, 3, 1, 1},{1,1,1,5,1, 1}                 //Hp  ---  Apple
    };

    public final static int[][] productionIntervals = {
        {3, 3,1,1,3, 2},{4,4,1,1,2, 2}  
        
    };
    
    public final static int[] storageCapacities = {
        25, // Productores de placa base
        20, // Productores de CPUs
        55, // Productores de Memoria RAM
        35, // Productores de Fuente de alimentación
        10  // Productores de tarjetas gráficas
    };
    public static int[] workerCounts = new int[5];
}

