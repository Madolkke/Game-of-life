package gol.util;
public class RandomTool{
    public static void main(String args[]){//测试用主方法

    }

    public static int intRandom(){//返回1-100的随机整数

        int result;
        int first,second;

        first = RandomTool.getOneRandom();
        second = RandomTool.getOneRandom();

        result = (first * 10 + second);
        if(result == 0){
            result = 100;
        }
        return result;

    }

    public static int getOneRandom(){//返回0-9的随机整数

        double randomnumber;

        for (randomnumber = Math.random();randomnumber == 1.0;randomnumber = Math.random()){

        }
        return (int) (randomnumber * 10);

    }
    public static boolean perProbability (int percent){//以参数为百分数输出该概率的布尔值
        //存在exception:percent的范围不合法

        int randomvalue = RandomTool.intRandom();

        return randomvalue <= percent;

    }


}




