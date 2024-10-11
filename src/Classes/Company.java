/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import DataStructure.Data;
import Interfaces.DashboardApple;
import Interfaces.DashboardHP;
import java.util.concurrent.Semaphore;
import javax.swing.JFrame;

public class Company {
    private String company;
    private int totalEmployees;
    private int actualEmployees;
    private Employee[] employees;
    private Employee[] ePlacaBase;
    private Employee[] eCPU;
    private Employee[] eRAM;
    private Employee[] eFA;
    private Employee[] eTG;
    private Employee[] assemblers;
    private Storage[] storages;
    private int dayCount;
    private ProjectManager projectManager;
    private Director director;
    private int daysLeft;
//    private JFrame window;
  
    private static Storage storage;
    private Semaphore mutex;
    
    private float cost = 0;
    private float earning = 0;
    private float profit = 0;
    
    private int days = 0; 
  
    
    private int computerCount;
    private int graphicComputerCount;
    private int assemblerCount;
    
    private int totalComputers;
    private int totalGraphipComputers;
    
    private DashboardApple dApple;
    private DashboardHP dHP;
    

  
    public Company(String company, int totalEmployees, int daysLeft) {

        this.company = company;
        this.totalEmployees = totalEmployees;
        this.employees = new Employee[totalEmployees];
        this.storages = new Storage[5]; // 5 tipos de trabajadores
        this.mutex = new Semaphore(1);

        this.computerCount = 0;
        this.graphicComputerCount = 0;

        this.daysLeft = daysLeft; // Inicializa días restantes (puedes ajustar este valor)

//        if (company.equals("APPLE")) {
//        this.dApple = new DashboardApple(this, null);
//        } else if (company.equals("HP")) {
//            this.dHP = new DashboardHP(this, null);
//        }

        
        // Inicializar los almacenes con capacidades fijas
        for (int i = 0; i < 5; i++) {
            Storage storage = new Storage(Data.storageCapacities[i], Data.producerTypes[i], this);
            storage.getCapacity();

            this.storages[i] = storage;
        }


        this.projectManager = new ProjectManager(this, this.getMutex(), Data.dayDuration, this.getDaysLeft());
        this.director = new Director(this.getCompany(), this.getMutex(), this.getProjectManager());

    }

    public void distributeEmployees() {
        // Distribuir el total de empleados de la compañía entre los 5 tipos de trabajadores y los ensambladores de las computadoras
        int[] employeeCounts = new int[6]; // Contadores de empleados por tipo

        // Asignar un trabajador de cada tipo como mínimo
        for (int i = 0; i < 6; i++) {
            employeeCounts[i] = 1;
        }

        // Distribuir los empleados restantes
        int remainingEmployees = this.getTotalEmployees() - 6;
        for (int i = 0; i < remainingEmployees; i++) {
            int type = (int) (Math.random() * 6);
            employeeCounts[type]++;
        }

        // Crear los empleados
        int employeeIndex = 0;
        for (int i = 0; i < 6; i++) {
            Storage storage = (i < 5) ? this.getStorages()[i] : null;
            for (int j = 0; j < employeeCounts[i]; j++) {
                Employee employee = new Employee(this, employeeIndex, i, 0, 0, storage, new Semaphore(1));
                this.getEmployees()[employeeIndex] = employee;
                employeeIndex++;
            }
        }
    }
    
    
    public void startWork() {
       
        for (Employee employee : getEmployees()) {
            employee.start();
        }
        
        this.getProjectManager().start();
        this.getDirector().start();
        System.out.println("Project started for " + this.getCompany());
    }

    public void updateDaysLeft(int days) {
        this.setDaysLeft(days); // Actualiza los días restantes
        this.getProjectManager().setDaysLeft(days); // Sincroniza el Project Manager con los nuevos días
    }


    
    public void incrementComputerCount() {
        this.setComputerCount(this.getComputerCount() + 1);
    System.out.println("\n\n" + this.getCompany() + " Computadoras normales " + this.getComputerCount() + "\n");
    this.updateDashboard();
   
}   

public void incrementGraphicComputerCount() {
    this.setGraphicComputerCount(this.getGraphicComputerCount() + 1);
    System.out.println("\n\n" + this.getCompany() + " Computadoras con gráficas " + this.getGraphicComputerCount() + "\n");
    this.updateDashboard();
   
}
    
    public void incrementAssemblerCount() {
        this.setAssemblerCount(this.getAssemblerCount() + 1);
       
    }
    
    
    public void updateDashboard() {
    if (this.getCompany().equals("APPLE")) {
        if (getdApple() != null) {
                getdApple().updateComputerCount(this.getComputerCount());
                getdApple().updateGraphicComputerCount(this.getGraphicComputerCount());
         
        }
    } else {
        if (getdHP() != null) {
                getdHP().updateComputerCount(this.getComputerCount());
                getdHP().updateGraphicComputerCount(this.getGraphicComputerCount());
       
        }
    }
}
  
   
    
  
   public void adjustEmployeeCount(int type, int amount) {
    if (type < 0 || type >= 6) {
        System.out.println("Tipo de empleado inválido");
        return;
    }

    int currentCount = 0;
    for (Employee employee : getEmployees()) {
        if (employee != null && employee.getType() == type) {
            currentCount++;
        }
    }

    int newCount = currentCount + amount;

    if (newCount < 1) {
        System.out.println("No se puede tener menos de 1 empleado en un tipo de empleado");
        return;
    }

    int totalEmployeesCount = 0;
    for (Employee employee : getEmployees()) {
        if (employee != null) {
            totalEmployeesCount++;
        }
    }

    if (totalEmployeesCount + amount > getTotalEmployees()) {
        System.out.println("No se puede superar el máximo de empleados en la compañía");
        return;
    }

    if (amount > 0) {
        // Agregar empleados
        for (int i = 0; i < amount; i++) {
            Storage storage = (type < 5) ? getStorages()[type] : null;
            Employee employee = new Employee(this, getEmployees().length, type, 0, 0, storage, new Semaphore(1));
            // Buscar el primer espacio disponible en el arreglo
            int index = 0;
            while (index < getEmployees().length && getEmployees()[index] != null) {
                index++;
            }
            if (index < getEmployees().length) {
                    getEmployees()[index] = employee;
                employee.start();
            } else {
                // Si no hay espacio disponible, aumentar el tamaño del arreglo
                Employee[] newEmployees = new Employee[getEmployees().length + 1];
                System.arraycopy(getEmployees(), 0, newEmployees, 0, getEmployees().length);
                newEmployees[getEmployees().length] = employee;
                    setEmployees(newEmployees);
                employee.start();
            }
        }
    } else if (amount < 0) {
        // Restar empleados
        int count = 0;
        for (int i = 0; i < getEmployees().length; i++) {
            if (getEmployees()[i] != null && getEmployees()[i].getType() == type) {
                    getEmployees()[i].interrupt();
                    getEmployees()[i] = null;
                count++;
                if (count == -amount) {
                    break;
                }
            }
        }
        // Reorganizar el arreglo para eliminar valores null
        int newIndex = 0;
        for (int i = 0; i < getEmployees().length; i++) {
            if (getEmployees()[i] != null) {
                    getEmployees()[newIndex] = getEmployees()[i];
                newIndex++;
            }
        }
        // Reducir el tamaño del arreglo si es necesario
        if (newIndex < getEmployees().length) {
            Employee[] newEmployees = new Employee[newIndex];
            System.arraycopy(getEmployees(), 0, newEmployees, 0, newIndex);
                setEmployees(newEmployees);
        }
    }

    printEmployeeCount(type);
}
  
    public void printEmployeeCount(int type) {
    int count = 0;
    if (getEmployees() != null) {
        for (Employee employee : getEmployees()) {
            if (employee != null && employee.getType() == type) {
                count++;
            }
        }
    }
    System.out.println("Hay " + count + " empleados de tipo " + type + " actualmente.");
}
  
    
    public void calculateOperativeCost() {
    float totalCost = 0;

    // Sumar el sueldo de todos los trabajadores
    for (Employee employee : getEmployees()) {
        if (employee != null) {
            totalCost += employee.getTotalSalary();
        }
    }

    // Sumar el sueldo del director
    totalCost += getDirector().getTotalSalary();

    // Sumar el sueldo del project manager
    totalCost += getProjectManager().getTotalSalary();

    this.setCost(totalCost);
    int companyIndex = getCompany().equals("HP") ? 0 : 1;
    this.setEarning(getEarning() + getComputerCount() * Data.profitTypeComputer[companyIndex][0] + getGraphicComputerCount() * Data.profitTypeComputer[companyIndex][1]);
    
    if (this.getCost() > 0){
    this.setProfit(this.getEarning() - this.getCost());}
    this.resetSalaries();
      
        System.out.println(this.earning);
        System.out.println(this.cost);
        System.out.println(this.profit);
        
   }
    
    public void resetSalaries() {
    for (Employee employee : getEmployees()) {
        if (employee != null) {
            employee.resetSalary();
        }
    }
    getDirector().resetSalary();
    getProjectManager().resetSalary();
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public int getComputerCount() {
        return this.computerCount;
    }
    
       public String getCompany() {
        return company;
    }
       
       public void setComputerCount(int count) {
        computerCount = count;
    }
    
    
    
    
  public int getEmployeeCount(int type) {
    int count = 0;
    for (Employee employee : getEmployees()) {
        if (employee != null && employee.getType() == type) {
            count++;
        }
    }
    return count;
}



   
    public Storage[] getStorages() {
        return this.storages;
    }

    public Employee[] getEmployees() {
        return this.employees;
    }
    
    public ProjectManager getProjectManager() {
        return projectManager;
    }

    public int getDaysLeft() {
        return daysLeft; // Getter para días restantes
    }

    public void printEmployeeCounts() {
    int[] employeeCounts = new int[6]; // Contadores de empleados por tipo

    // Contar los empleados por tipo
    for (Employee employee : this.getEmployees()) {
        employeeCounts[employee.getType()]++;
    }

    // Imprimir los contadores
        System.out.println("\n");
    for (int i = 0; i < employeeCounts.length; i++) {
        System.out.println("Tipo " + i + ": " + employeeCounts[i] + " empleados");
    }
}

    /**
     * @return the graphicComputerCount
     */
    public int getGraphicComputerCount() {
        return graphicComputerCount;
    }

    /**
     * @return the assemblerCount
     */
    public int getAssemblerCount() {
        return assemblerCount;
    }

    /**
     * @param assemblerCount the assemblerCount to set
     */
    public void setAssemblerCount(int assemblerCount) {
        this.assemblerCount = assemblerCount;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the totalEmployees
     */
    public int getTotalEmployees() {
        return totalEmployees;
    }

    /**
     * @param totalEmployees the totalEmployees to set
     */
    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    /**
     * @return the actualEmployees
     */
    public int getActualEmployees() {
        return actualEmployees;
    }

    /**
     * @param actualEmployees the actualEmployees to set
     */
    public void setActualEmployees(int actualEmployees) {
        this.actualEmployees = actualEmployees;
    }

    /**
     * @param employees the employees to set
     */
    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    /**
     * @return the ePlacaBase
     */
    public Employee[] getePlacaBase() {
        return ePlacaBase;
    }

    /**
     * @param ePlacaBase the ePlacaBase to set
     */
    public void setePlacaBase(Employee[] ePlacaBase) {
        this.ePlacaBase = ePlacaBase;
    }

    /**
     * @return the eCPU
     */
    public Employee[] geteCPU() {
        return eCPU;
    }

    /**
     * @param eCPU the eCPU to set
     */
    public void seteCPU(Employee[] eCPU) {
        this.eCPU = eCPU;
    }

    /**
     * @return the eRAM
     */
    public Employee[] geteRAM() {
        return eRAM;
    }

    /**
     * @param eRAM the eRAM to set
     */
    public void seteRAM(Employee[] eRAM) {
        this.eRAM = eRAM;
    }

    /**
     * @return the eFA
     */
    public Employee[] geteFA() {
        return eFA;
    }

    /**
     * @param eFA the eFA to set
     */
    public void seteFA(Employee[] eFA) {
        this.eFA = eFA;
    }

    /**
     * @return the eTG
     */
    public Employee[] geteTG() {
        return eTG;
    }

    /**
     * @param eTG the eTG to set
     */
    public void seteTG(Employee[] eTG) {
        this.eTG = eTG;
    }

    /**
     * @return the assemblers
     */
    public Employee[] getAssemblers() {
        return assemblers;
    }

    /**
     * @param assemblers the assemblers to set
     */
    public void setAssemblers(Employee[] assemblers) {
        this.assemblers = assemblers;
    }

    /**
     * @param storages the storages to set
     */
    public void setStorages(Storage[] storages) {
        this.storages = storages;
    }

    /**
     * @return the dayCount
     */
    public int getDayCount() {
        return dayCount;
    }

    /**
     * @param dayCount the dayCount to set
     */
    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    /**
     * @param projectManager the projectManager to set
     */
    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    /**
     * @return the director
     */
    public Director getDirector() {
        return director;
    }

    /**
     * @param director the director to set
     */
    public void setDirector(Director director) {
        this.director = director;
    }

    /**
     * @param daysLeft the daysLeft to set
     */
    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    /**
     * @return the storage
     */
    public static Storage getStorage() {
        return storage;
    }

    /**
     * @param aStorage the storage to set
     */
    public static void setStorage(Storage aStorage) {
        storage = aStorage;
    }

    /**
     * @return the mutex
     */
    public Semaphore getMutex() {
        return mutex;
    }

    /**
     * @param mutex the mutex to set
     */
    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    /**
     * @return the cost
     */
    public float getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(float cost) {
        this.cost = cost;
    }

    /**
     * @return the earning
     */
    public float getEarning() {
        return earning;
    }

    /**
     * @param earning the earning to set
     */
    public void setEarning(float earning) {
        this.earning = earning;
    }

    /**
     * @return the profit
     */
    public float getProfit() {
        return profit;
    }

    /**
     * @param profit the profit to set
     */
    public void setProfit(float profit) {
        this.profit = profit;
    }

    /**
     * @return the days
     */
    public int getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(int days) {
        this.days = days;
    }

    /**
     * @param graphicComputerCount the graphicComputerCount to set
     */
    public void setGraphicComputerCount(int graphicComputerCount) {
        this.graphicComputerCount = graphicComputerCount;
    }

    /**
     * @return the totalComputers
     */
    public int getTotalComputers() {
        return totalComputers;
    }

    /**
     * @param totalComputers the totalComputers to set
     */
    public void setTotalComputers(int totalComputers) {
        this.totalComputers = totalComputers;
    }

    /**
     * @return the totalGraphipComputers
     */
    public int getTotalGraphipComputers() {
        return totalGraphipComputers;
    }

    /**
     * @param totalGraphipComputers the totalGraphipComputers to set
     */
    public void setTotalGraphipComputers(int totalGraphipComputers) {
        this.totalGraphipComputers = totalGraphipComputers;
    }

    /**
     * @return the dApple
     */
    public DashboardApple getdApple() {
        return dApple;
    }

    /**
     * @param dApple the dApple to set
     */
    public void setdApple(DashboardApple dApple) {
        this.dApple = dApple;
    }

    /**
     * @return the dHP
     */
    public DashboardHP getdHP() {
        return dHP;
    }

    /**
     * @param dHP the dHP to set
     */
    public void setdHP(DashboardHP dHP) {
        this.dHP = dHP;
    }
    
    /**
     * @return the DashboardApple interface
     */
    public DashboardApple getWindowApple() {
        return this.dApple;
    }
    
    /**
     * @param dApple the DashboardApple interface
     */
    public void setWindowApple(DashboardApple dApple) {
        this.dApple = dApple;
    }
    
    /**
     * @return the DashboardApple interface
     */
    public DashboardHP getWindowHP() {
        return this.dHP;
    }
    
    /**
     * @param dApple the DashboardApple interface
     */
    public void setWindowHP(DashboardHP dHP) {
        this.dHP = dHP;
    }
    
    public void printApple() {
        this.getWindowApple().getEpb2().setText("AQUIIIII");
    }
    
}

