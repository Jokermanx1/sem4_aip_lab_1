package vashkevich.lab1;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Path {
    List<Integer> way;
    int pathNumber;

    //sum of all distances between busStops
    int fullRouteDistance =0;

    public Path() {

    }
    public Path(List<Integer> way, int pathNumber) {
        this.way = way;
        this.pathNumber = pathNumber;
    }
    public Path(int pathNumber) {
        this.way = new LinkedList<Integer>();
        this.pathNumber = pathNumber;
    }

    public List<Integer> getWay() {
        return way;
    }

    public void setWay(List<Integer> way) {
        this.way = way;
    }

    public int getPathNumber() {
        return pathNumber;
    }

    public void setPathNumber(int pathNumber) {
        this.pathNumber = pathNumber;
    }


    /**
     * getting distance of full circle before vehicle will beck to some stop
     * @return
     */
    public int getFullCircleDistance(){
        return fullRouteDistance*2;
    }

    /**
     * returns way from start circle to current Stop
     * we know when route gets to this stop
     * @param id - busStopId
     * @return
     */
    public int getStartDistanceToBusStop(int id){
        int position =-1;
        int result=0;
        for(int i=0;i<way.size();i++){
            if(way.get(i)==id){
                position = i;
                break;
            }
        }
        int lastStopId = way.get(0);
        if(position>1){
            for(int i=1;i<position;i++){
                //getting distance from current stop to last
                //making sum of all distances before this stop
                result+= BusStopsInfo.getInstance().map.get(way.get(i)).disctancesList.get(lastStopId);
            }
        }else{
            //if not in list return -1
            result =-1;
        }
        return result;
    }

    public void readPathStopsFromConsole(){
        int stop = -1;
        Scanner in = new Scanner(System.in);
        while(stop!=0){
            System.out.println("Introduct another stop Id, if want finish introduct 0");
            stop = in.nextInt();
            if(stop!=0&&
                    BusStopsInfo.getInstance().map.containsKey(stop)&&
                    !way.contains(stop)
                    ){
                if(way.size()>0) {
                    BusStop last = BusStopsInfo.getInstance().map.get(way.get(way.size() - 1));
                    fullRouteDistance += last.getDisctancesList().get(stop) * 2;
                    System.out.println(fullRouteDistance);
                }
                way.add(stop);
            }
        }
    }
    public String toString(){
        String str = "";
        str += "Number: "+pathNumber+" Distance:" + fullRouteDistance+" " +'\n';
        str += "busStops: "+way.toString();
        return str;
    }

    public int getFullRouteDistance() {
        return fullRouteDistance;
    }

    public void setFullRouteDistance(int fullRouteDistance) {
        this.fullRouteDistance = fullRouteDistance;
    }
}
