package org.ch.gobang4;

/**
 * 该类为棋子父类，下面有黑/白棋子两个子类。
 * 该类构造方法除了基本的传值，还检测了落子重复。
 * 定义了对象数组，用来储存棋路。
 */
public class Chess implements GobangConfig{

    int order;
    int chessNum;  //黑子还是白子
    int seatX;
    int seatY;
    static Chess[] chessList=new Chess[ROW*CLOUN];


    public Chess(int order, int chessNum, int seatX, int seatY) {
        if (!deDoul(seatX, seatY)) return;   //首先检测落子重复
        this.order = order;
        this.chessNum = chessNum;
        this.seatX = seatX;
        this.seatY = seatY;
    }


    /*
        检测落子重复
        方法：把落子的坐标与Chess[]中已有的坐标进行对比  */
    public Boolean deDoul(int seatX,int seatY){
        for (int i=0;i<chessList.length;i++){
            if (chessList[i]==null) return true;
            if (seatY==chessList[i].seatY&&seatX==chessList[i].seatX) {
                return false;
            }
        }
        return true;
    }
}
