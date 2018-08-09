import gol.util.RandomTool;


public class Contents {
    public static void main(String args[]){
        Map map = Map.createMap();
        map.thingsInitialize(75);


    }
}

class Logic{


}
class Map{

    private Map(){

    }

    private Map(int square){

        this.Height = this.Width = square;

    }

    private int Height;
    private int Width;
    private Things coordinate[][];
    private int counter = 0;//填充计数器，计算填充数




    public void mapInitialize(){//用空位置填充地图，无意义

        for (int cWidth = 0;cWidth < this.Width - 1;cWidth++){
            for(int cHeight = 0;cHeight < this.Height - 1;cHeight++){
                coordinate[cWidth][cHeight] = EmptyPlace.createEmptyPlace();
            }
        }

    }

    public void thingsInitialize(int percent) {//概率填充活细胞

        for (int cWidth = 0;cWidth < this.Width - 1;cWidth++){
            for(int cHeight = 0;cHeight < this.Height - 1;cHeight++){
                if (RandomTool.perProbability(percent)){
                    this.coordinate[cWidth][cHeight] = Cell.createCell();
                    this.counter++;
                }else {
                    coordinate[cWidth][cHeight] = EmptyPlace.createEmptyPlace();
                }
            }
        }


    }
    public static Map createMap(){
        return new Map(100);
    }

    public int getCounter() {
        return counter;
    }
}
abstract class Things{

}
class Cell extends Things{
    private boolean status;//细胞的状态，true为活细胞，false为死细胞

    private Cell(){

    }

    private Cell(boolean firststatus){
        status = firststatus;
    }

    static Things createCell(){//获取cell类对象
        return new Cell(true);
    }

}
class EmptyPlace extends Things{
    private EmptyPlace(){

    }
    static Things createEmptyPlace(){//获取emptyplace类对象
        return new EmptyPlace();
    }

}
