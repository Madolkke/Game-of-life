package gol.contents;

public class Coordinate{

    int x;
    int y;

    Coordinate(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Coordinate createCoordinate(int x,int y){
        return new Coordinate(x,y);
    }

    public Coordinate[] getAround(Map map){

        /*

            6  5  4
            7  P  3
            0  1  2

        */
        Coordinate[] coordinate = new Coordinate[8];

        coordinate[0] = new Coordinate(this.x - 1,this.y - 1);

        coordinate[1] = new Coordinate(this.x,this.y - 1);

        coordinate[2] = new Coordinate(this.x + 1,this.y - 1);

        coordinate[3] = new Coordinate(this.x + 1,this.y);

        coordinate[4] = new Coordinate(this.x + 1,this.y + 1);

        coordinate[5] = new Coordinate(this.x,this.y + 1);

        coordinate[6] = new Coordinate(this.x - 1,this.y + 1);

        coordinate[7] = new Coordinate(this.x - 1,this.y);

        if (this.x == 0){
            coordinate[0] = coordinate[6] = coordinate[7] = null;
        }
        if (this.y == 0){
            coordinate[0] = coordinate[1] = coordinate[2] = null;
        }
        if (this.x == map.getWidth()){
            coordinate[2] = coordinate[3] = coordinate[4] = null;
        }
        if (this.y == map.getHeight()){
            coordinate[4] = coordinate[5] = coordinate[6] = null;
        }



        return coordinate;
    }

    public boolean equals(Coordinate coordinate) {

        return this.getX() == coordinate.getX() && this.getY() == coordinate.getY();

    }

    public void printCoordinate(){
        System.out.println("(" + this.x + "," + this.y + ")");
    }//输出该坐标的xy值

    public boolean presentStatus(Map map){
        return map.getLocation(this.x,this.y);
    }//获取坐标的当前细胞存在状态

    public boolean nextStatus(Map map){

        boolean status = map.getLocation(this.x,this.y);
        int a = map.recordCheck(this);

        if (status){
            if (a < 2 || a > 3){
                status = false;
            }
        }else {
            if (a == 3){
                status = true;
            }
        }

        return status;
    }//返回当前坐标对应的下一细胞存在状态



}
