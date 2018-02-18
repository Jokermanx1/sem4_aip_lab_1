package vashkevich.lab1;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class PathsInfo {
    //map of busstops ids an list of another busStops and distance between them in minutes
    private static PathsInfo instance;
    Map<Integer,Path> list;
    /**
     * Constructor is private for make sure what it is singleton
     */
    private PathsInfo(){
        list = new TreeMap<Integer,Path>();
    }

    /**
     * Returning unique object
     * @return
     */
    public static PathsInfo getInstance(){
        if(instance ==null){
            instance = new PathsInfo();
        }
        return instance;
    }
    public void createNewRoute(){
        System.out.println("Introduct route number");
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        Path route = new Path(number);
        route.readPathStopsFromConsole();
        System.out.println(list.size());
        list.put(number,route);
    }


    public void saveInfoToFile(){
        File mapFile = new File("rotes.txt");
        try {
            mapFile.createNewFile(); // if file already exists will do nothing
            FileOutputStream mapOFile = new FileOutputStream(mapFile, false);
            java.beans.XMLEncoder xe1 = new java.beans.XMLEncoder(mapOFile);
            xe1.writeObject(list);
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
            BufferedInputStream mapOFile = new BufferedInputStream(new FileInputStream("rotes.txt"));
            java.beans.XMLDecoder xe1 = new java.beans.XMLDecoder(mapOFile);
            list = (Map<Integer,Path>) xe1.readObject();
            xe1.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * print all information
     */
    public void printInfo(){
        list.forEach((k,v)->System.out.println(v.toString()));
    }
}
