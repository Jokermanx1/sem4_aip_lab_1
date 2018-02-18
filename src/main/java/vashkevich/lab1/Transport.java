package vashkevich.lab1;

import java.util.Scanner;

public class Transport {
    public static BusStopsInfo stops;
    public static PathsInfo routes;
    public static VehiclesInfo vehicles;
    static boolean exit =false;
    public static void main(String[] args) {
        stops = BusStopsInfo.getInstance();
        stops.openInfoFromFile();
        routes = PathsInfo.getInstance();
        routes.openInfoFromFile();
        vehicles = VehiclesInfo.getInstance();
        vehicles.openInfoFromFile();
        while(!exit){
            mainMenu();
        }
    }
    public static void mainMenu(){
        prinMainMenu();
        Scanner s = new Scanner(System.in);
        char c = Character.toLowerCase(s.next().charAt(0));
        switch(c){
            case 'a':
                stops.printInfo();
                break;
            case 'b':
                Scanner in = new Scanner(System.in);
                System.out.println("Insert Vehicle number");
                int id3 = in.nextInt();
                System.out.println("Insert busStopId");
                int id4 = in.nextInt();
                System.out.println(vehicles.map.get(id3).getTimesOnStop(id4));
                break;
            case 'c':
                stops.readBusStopFromConsole();
                break;
            case 'd':
                stops.addDistanceFromConsole();
                break;
            case 'e':
                routes.createNewRoute();
                break;
            case 'f':
                routes.printInfo();
                break;
            case 'g':
                vehicles.addVehicle();
                break;
            case 'i':
                vehicles.printInfo();
                break;
            case 'x':
                System.out.println("Saving");
                stops.saveInfoToFile();
                routes.saveInfoToFile();
                vehicles.saveInfoToFile();
                System.out.println("Exiting programm");
                exit = true;
                break;
        }
    }
    public static void prinMainMenu(){
        System.out.println("\n------------------------------");
        System.out.println("Select the option");
        System.out.println("A - Info about bussStops");
        System.out.println("B - list of transport of some busStop");
        System.out.println("C - add busStop");
        System.out.println("D - add Distance between busStops");
        System.out.println("E - add Route");
        System.out.println("F - info about Routes");
        System.out.println("G - add vehicle to root");
        System.out.println("I - Info Abot Vehicles");
        System.out.println("X - save and exit");
    }
}
