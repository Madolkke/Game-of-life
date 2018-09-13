package gol.contents;

import gol.util.RandomTool;
import javafx.scene.layout.CornerRadii;

import javax.swing.text.html.HTMLDocument;
import java.util.*;
import java.util.Iterator;



public class Map{

    /*public static void main(String args[]){
        Map map = new Map(5);
        map.location = new boolean[5][5];
        map.mapInitialize();

        map.setCell(0,0);
        map.setCell(1,2);
        map.setCell(2,0);
        map.setCell(2,1);
        map.setCell(2,2);
        map.setCell(2,3);
        map.setCell(3,2);


        for (int cwidth = 0;cwidth <= map.width - 1;cwidth++){
            for(int cheight = 0;cheight <= map.height - 1;cheight++){

                if (map.location[cwidth][cheight]){
                    map.record.add(new Coordinate(cwidth,cheight));

                }
            }
        }

        map.toSaver();
        boolean mae = map.saver[1].presentStatus(map);
        boolean ato = map.saver[1].nextStatus(map);
        boolean none = new Coordinate(3,3).nextStatus(map);
        //遍历全部location，检测是否有true，标记出对应coordinate


        MyArrayList myArrayList = map.createSuperCoordinateSet();
        Iterator<Coordinate> iterator = myArrayList.iterator();
        while (iterator.hasNext()){
            Coordinate coordinate = iterator.next();
            coordinate.printCoordinate();
            System.out.println(coordinate.nextStatus(map));
        }
        map.easyPrint();
        map.nextStep();
        System.out.println();
        map.easyPrint();
        Map map = new Map(20);
        map.cellInitialize(50);
        for (int counter = 0;counter <= 100;counter++){
            System.out.println(counter + 1);
            map.easyPrint();
            map.nextStep();
        }
        System.out.println(map.mapStringout());






    }*/

    private Map(){

    }

    private Map(int square){

        this.height = this.width = square;
        this.location = new boolean[this.width][this.height];

    }

    private Map(int width,int height){
        this.width = width;
        this.height = height;
    }

    private int height;
    private int width;
    private boolean[][] location;//各位置的细胞存在状态，存在为true，不存在为false
    private MyArrayList record = new MyArrayList();
    private Coordinate[] saver;//存储当前record中的位置，即有活细胞存在的位置




    public void mapInitialize(){

        for (int cwidth = 0;cwidth < this.width - 1;cwidth++){
            for(int cheight = 0;cheight < this.height - 1;cheight++){
                location[cwidth][cheight] = false;
            }
        }

    }//用空位置填充地图，无意义

    public void cellInitialize(int percent) {


        for (int cwidth = 0;cwidth <= this.width - 1;cwidth++){
            for(int cheight = 0;cheight <= this.height - 1;cheight++){
                if (RandomTool.perProbability(percent)){
                    this.location[cwidth][cheight] = true;
                    this.record.add(new Coordinate(cwidth,cheight));//记录已填充的位置

                }else {
                    location[cwidth][cheight] = false;
                }
            }
        }
        this.toSaver();


    }//概率填充活细胞

    public static Map createMap(){
        return new Map(100);
    }//获取新map,100x100

    public MyArrayList getRecord() {
        return this.record;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Coordinate checkCell(){

        Iterator<Coordinate> iterator = this.getRecord().iterator();
        Coordinate coordinate = iterator.next();
        return coordinate;

    }//??

    public void setCell(int x,int y){
        this.location[x][y] = true;
    }

    public void toSaver(){
        Iterator<Coordinate> iterator = this.record.iterator();
        this.saver = new Coordinate[this.record.size()];
        int counter = 0;
        while (iterator.hasNext()){
            this.saver[counter] = iterator.next();
            counter++;
        }


    }//将record中内容存入saver


    /*
    1.单一saver使用getAround获取周围坐标，将获取到的坐标与record中比对，若存在则使计数器1++，返回计数
    1.5：独立的阈值判断函数
    2.建立空白的全集并对record取补集，获取单个空白位置的坐标，内部使用计数器，若超过阈值返回
    */
    public int recordCheck(Coordinate incoordinate){

        int existcounter = 0;


        Coordinate[] coordinate = incoordinate.getAround(this);

        for (int counter = 0;counter <= coordinate.length - 1;counter++){
            if (coordinate[counter] != null){
                if (this.record.contains(coordinate[counter])){
                    existcounter++;

                }
            }

        }
        return existcounter;

    }//检测传入坐标的周围坐标，返回存在个数计数器

    public Coordinate getOrderRecord(int ordernumber){
        return this.record.get(ordernumber);
    }

    public MyArrayList createSuperCoordinateSet(){
        MyArrayList coordinateArrayList = new MyArrayList();
        for (int cwidth = 0;cwidth <= this.width - 1;cwidth++){
            for(int cheight = 0;cheight <= this.height - 1;cheight++){
                coordinateArrayList.add(new Coordinate(cwidth,cheight));
                }
            }
        return coordinateArrayList;

    }//创建全集

    public MyArrayList obtainEmptyPlace(){
        MyArrayList coordinateHashSet = this.createSuperCoordinateSet();
        MyArrayList innerrecord = this.record;
        coordinateHashSet.removeAll(innerrecord);
        return coordinateHashSet;

    }//获取补集

    public boolean getLocation(int x,int y){
        return this.location[x][y];
    }

    public boolean[][] getLocation() {
        return location;
    }

    public void nextStep(){

        Iterator<Coordinate> iterator = this.createSuperCoordinateSet().iterator();
        MyArrayList myArrayList = new MyArrayList();
        while(iterator.hasNext()){
            Coordinate coordinate = iterator.next();
            this.location[coordinate.getX()][coordinate.getY()] = coordinate.nextStatus(this);
            if (coordinate.nextStatus(this)){
                myArrayList.add(coordinate);
            }
        }
        this.record = myArrayList;

    }//改为新状态，更新record

    public void easyPrint(){

        for (int cheight = this.height - 1;cheight >= 0;cheight--){
            for(int cwidth = 0;cwidth <= this.width - 1;cwidth++){
                boolean xxx = this.location[cwidth][cheight];
                if (xxx){
                    System.out.print("()");
                }else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

    }//字符方式显示map

    public String mapStringout(){

        StringBuilder stringBuilder = new StringBuilder();
        for (int cheight = this.height - 1;cheight >= 0;cheight--){
            for(int cwidth = 0;cwidth <= this.width - 1;cwidth++){
                boolean xxx = this.location[cwidth][cheight];
                if (xxx){
                    stringBuilder.append("()");
                }else {
                    stringBuilder.append("  ");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    //全图遍历，删去死细胞，新增活细胞



}

class MyArrayList extends java.util.ArrayList<Coordinate>{

    MyArrayList(){

    }

    public boolean contains(Object o) {

        boolean recorder = false;
        Iterator<Coordinate> coordinateIterator = this.iterator();
        Coordinate coordinatein = (Coordinate) o;
        while (coordinateIterator.hasNext()){
            Coordinate middlevalue = coordinateIterator.next();
            if (middlevalue.equals(coordinatein)){
                recorder = true;
            }
        }
        return recorder;

    }

}


