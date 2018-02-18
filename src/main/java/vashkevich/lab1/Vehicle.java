package vashkevich.lab1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Vehicle {

    String startTime;
    private int id;
    float speedCoef;
    int routeId;
    int circleTime;
    int route;
    public Vehicle() {

    }
    public Vehicle(int id,int route,float speedCoef) {
        this.id = id;
        this.route = route;
        this.speedCoef=speedCoef;
        circleTime = Math.round(PathsInfo.getInstance().list.get(route).getFullCircleDistance()*speedCoef);
        reSetStartTime();
    }
    public Vehicle(int id, int number,int route,LocalDateTime startTime,float speedCoef) {
        this.id = id;
        this.route = route;
        this.startTime = fromLocalDataTimeTostring(startTime);
        this.speedCoef=speedCoef;
        circleTime =  Math.round(PathsInfo.getInstance().list.get(route).getFullCircleDistance()*speedCoef);
    }


    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getCircleTime() {
        return circleTime;
    }

    public void setCircleTime(int circleTime) {
        this.circleTime = circleTime;
    }

    public int getRoute() {
        return route;
    }

    public void setRoute(int route) {
        this.route = route;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public float getSpeedCoef() {
        return speedCoef;
    }

    public void setSpeedCoef(float speedCoef) {
        this.speedCoef = speedCoef;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    private void reSetStartTime(){
        LocalDateTime current = LocalDateTime.now();
        int year = current.getYear();
        int mounth = current.getMonthValue();
        int day = current.getDayOfMonth();
        startTime = fromLocalDataTimeTostring(LocalDateTime.of(year, mounth, day, 7, 0, 0));
    }

    /**
     * we have start time, so we need know every how minutes will
     * come vehicle
     * @param stopId
     * @return
     */
    public String getTimesOnStop(int stopId){
        String str ="Bas Id: "+id+"   Route number: "+route+"\n Squedule:";
        LocalDateTime auxTime = fromtStringToLocalDateTime(startTime);
        int distance =PathsInfo.getInstance().list.get(route).getStartDistanceToBusStop(stopId);
        auxTime = auxTime.plusMinutes( Math.round(distance*speedCoef));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        distance =PathsInfo.getInstance().list.get(route).fullRouteDistance;
        str+= auxTime.format(formatter)+"  ";
        int day =auxTime.getDayOfMonth();
        while(auxTime.getHour()<=23&&day==auxTime.getDayOfMonth()){
            auxTime = auxTime.plusMinutes( Math.round(distance*speedCoef));
            str+= auxTime.format(formatter)+"  ";
        }
        return str;
    }

    /**
     * when bus start the rouse if stop is not first, he will need time for get to
     * some stop, here we are calculationg it
     * @param stopId
     * @return
     */
    public String getTimesForFirstTime(int stopId){
        String str ="";
        int distance =PathsInfo.getInstance().list.get(route).getStartDistanceToBusStop(stopId);
        startTime = fromLocalDataTimeTostring(fromtStringToLocalDateTime(startTime).plusMinutes( Math.round(distance*speedCoef)));
        return str;
    }
    public String fromLocalDataTimeTostring(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }
    public LocalDateTime fromtStringToLocalDateTime(String str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return  LocalDateTime.parse(str, formatter);
    }
    public String toString(){
        String str = "";
        str += "Id: "+id+" SpeedCoef:" + speedCoef+" Route:" + route+" " +'\n';
        str += "CircleTime: "+circleTime+" StartTime: "+startTime;
        return str;
    }

}
