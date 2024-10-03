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
    public static int dayDuration = 5000;                        //5 segundos de duración
    
    public final static String[] companies = {
        "Apple", "Hp"
    };
    
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
}
