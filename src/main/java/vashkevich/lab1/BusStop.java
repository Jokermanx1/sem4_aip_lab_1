package vashkevich.lab1;

import java.util.Map;
import java.util.TreeMap;

/**
 * Class for save info about one BusStop
 */
public class BusStop {

    //distance shows distance between this
    Map<Integer,Integer> disctancesList;
    String name;
    int stopId;
    //just mad parameter, becouse of xml serialization  library bug
    //will not be used
    int lelele;

    /**
     * empty constructor for xml encode and decode
     */
    public BusStop() {

    }

    /**
     * constructor
     * @param id is BusStop id
     * @param name name of BusStop
     */
    public BusStop(int id, String name) {
        this.disctancesList = new TreeMap<Integer,Integer>();
        this.name = name;
        this.stopId = id;
        lelele=1;
    }

    public BusStop(int id, String name, Map<Integer,Integer> disctancesList) {
        this.disctancesList = new TreeMap<Integer,Integer>();
        this.name = name;
        this.stopId = id;
        this.disctancesList = disctancesList;
    }

    public Map<Integer, Integer> getDisctancesList() {
        return disctancesList;
    }

    public void setDisctancesList(Map<Integer, Integer> map) {
        this.disctancesList = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStopId() {
        return stopId;
    }

    public void setStopId(int id) {
        this.stopId = id;
    }
    /**
     * add to distances list another BusStop by id
     * @param id
     * @param distance
     */
    public void addToDisctancesList(int id, int distance){
        disctancesList.put(id,distance);
    }

    /**
     *
     * @return BusStop info
     */
    public String toString(){
        String str = "";
        str += "Name: "+name+" Id:" + stopId+" " +'\n';
        str += "Distances: "+disctancesList.toString();
        return str;
    }
}
