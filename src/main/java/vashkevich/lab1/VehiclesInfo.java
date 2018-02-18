package vashkevich.lab1;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class VehiclesInfo {
    //map of busstops ids an list of another busStops and distance between them in minutes
    private static VehiclesInfo instance;
    Map<Integer,Vehicle> map;

    /**
     * Constructor is private for make sure what it is singleton
     */
    private VehiclesInfo(){
        map = new TreeMap<Integer,Vehicle>();
    }

    /**
     * Returning unique object
     * @return
     */
    public static VehiclesInfo getInstance(){
        if(instance ==null){
            instance = new VehiclesInfo();
        }
        return instance;
    }
    public void saveInfoToFile(){
        File mapFile = new File("vehicles.txt");
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
     * reading routes info from file
     */
    public void openInfoFromFile(){
        try {
            BufferedInputStream mapOFile = new BufferedInputStream(new FileInputStream("vehicles.txt"));
            java.beans.XMLDecoder xe1 = new java.beans.XMLDecoder(mapOFile);
            map = (Map<Integer,Vehicle>) xe1.readObject();
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
    public void addVehicle(){

        System.out.println("What type of vehicle? (b,e,t)");
        Scanner t = new Scanner(System.in);
        char r = Character.toLowerCase(t.next().charAt(0));
        Vehicle v=null;
        Scanner in = new Scanner(System.in);
        System.out.println("Insert vehicle id");
        int id1 = in.nextInt();
        System.out.println("Insert route id");
        int id2 = in.nextInt();
        switch(r) {
            case 'b':
                v = new Bus(id1,id2, (float)1.2);
                break;
            case 't':
                v = new Bus(id1,id2, (float)1.7);
                break;
            case 'e':
                v = new Bus(id1,id2, (float)0.9);
                break;
        }
        map.put(id1,v);
    }
}
