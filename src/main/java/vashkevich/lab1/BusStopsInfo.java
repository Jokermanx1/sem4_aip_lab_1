package vashkevich.lab1;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Singletone class for have information about busStops and distances between them
 *
 * */
public class BusStopsInfo {
    //map of busstops ids an list of another busStops and distance between them in minutes
    private static BusStopsInfo instance;
    Map<Integer,BusStop> map;

    /**
     * Constructor is private for make sure what it is singleton
     */
    private BusStopsInfo(){
        map = new TreeMap<Integer,BusStop>();
    }

    /**
     * Returning unique object
     * @return
     */
    public static BusStopsInfo getInstance(){
        if(instance ==null){
            instance = new BusStopsInfo();
        }
        return instance;
    }

    /**
     * using console here for save time
     * not the best practices, but this was is faster for implement
     * reading from console id of new BusStop and its name
     */
    public void readBusStopFromConsole(){
        Scanner in = new Scanner(System.in);
        System.out.println("Insert new BusStop id");
        BusStop bs;
        int id = in.nextInt();
        if(map.containsKey(id)){
            System.out.println("This busStopExist in the system");
        }else{
            System.out.println("Insert the name");
            String name = in.next();
            bs = new BusStop(id,name);
            map.put(id, bs);
            System.out.println("BusStop have been added");
        }
    }
    /**
     * using console here for save time
     * not the best practices, but this was is faster for implement
     * reading from console 2 ids and distance
     */
    public void addDistanceFromConsole(){
        Scanner in = new Scanner(System.in);
        System.out.println("Insert first id");
        int id1 = in.nextInt();
        System.out.println("Insert second id");
        int id2 = in.nextInt();
        System.out.println("Insert distance");
        int distance = in.nextInt();
        if(map.containsKey(id1)&&map.containsKey(id2)){
            map.get(id1).addToDisctancesList(id2, distance);
            map.get(id2).addToDisctancesList(id1, distance);
            System.out.println("This distance have been added");
        }else{
            System.out.println("One of stops doesnt exist");
        }

    }

    public void saveInfoToFile(){
        File mapFile = new File("map.txt");
        try {
            mapFile.createNewFile(); // if file already exists will do nothing
            FileOutputStream mapOFile = new FileOutputStream(mapFile, false);
            java.beans.XMLEncoder xe1 = new java.beans.XMLEncoder(mapOFile);
            xe1.writeObject(map);
            xe1.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reading busStops info from file
     */
    public void openInfoFromFile(){
        try {
            BufferedInputStream mapOFile = new BufferedInputStream(new FileInputStream("map.txt"));
            java.beans.XMLDecoder xe1 = new java.beans.XMLDecoder(mapOFile);
            map = (Map<Integer,BusStop>) xe1.readObject();
            xe1.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * print all information
     */
    public void printInfo(){
        map.forEach((k,v)->System.out.println(v.toString()));
    }

    public int getDistance(int id1, int id2){
        if(map.containsKey(id1)&&map.containsKey(id2)) {
            return map.get(id1).getDisctancesList().get(id2);
        }else{
            return 0;
        }
    }
}