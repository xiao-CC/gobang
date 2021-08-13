package org.ch.gobang4;

import javax.swing.*;
import java.util.Arrays;

/**
 * 整体思路:当每下一颗棋子时，调用四个deXX方法，分别在四个方向上检测有没有连成线的其他棋子。
 * 如果有，调用isRow方法进行计数，达到五个则返回true。
 * 将iswinm除外的方法设为私有，保证了封装性
 */

public class IsWin implements GobangConfig {

    /**
     * 查找与新棋子同一列的同色棋子（即x坐标相同）。如果有，储存他们的行号（y坐标）。
     * 然后其放到isARow中判定
     */
    private static Boolean deRowSeat(Chess[] chessList, Chess chess){
        /*
        这个数组不能是静态的，所以必须要放在方法体内
        因为每次执行这个方法时必须保证这个数组=null，不能残留上次的数据  */
        int[] rowSeat =new int[ROW];
        int a=0;

        /*
        * 此处最大的问题是，在执行这步时，新落子已添加到了对象数组中，我们要避免新落子与数组中的自己作比较
        * order/2恰好解决了这个问题，因为新落子一定是对象数组中最后一个非空的值
        * 所以，此处不管是下黑子还是白子，新落子都是在和自己前面的值比较
        * 当然，也可以先判断输赢，再将新落子添加到对象数组，来解决这个问题  */
        for (int i=0;i<chess.order/2;i++){
            if (chessList[i]==null) break;
            if (chess.seatX==chessList[i].seatX){
                rowSeat[a]=chessList[i].seatY;
                a++;
            }
        }

        /*
        * 这里需要再重新把自己本身添加进去，再进行判断
        * 否则会出现在bug(两边都有两个子，在中间下一个子，不会返回true)*/
        rowSeat[a++]=chess.seatY;
        return isARow(rowSeat);
    }

    //参考上面的
    private static Boolean deCloundSeat(Chess[] chessList, Chess chess){

        int[] clounSeat =new int[CLOUN];
        int a=0;
        for (int i=0;i<chess.order/2;i++){
            if (chessList[i]==null) break;
            if (chess.seatY==chessList[i].seatY){
                clounSeat[a]=chessList[i].seatX;
                a++;
            }
        }
        clounSeat[a++]=chess.seatX;
        return isARow(clounSeat);
    }

    /**
     *查找与新落子斜向（从左上到右下）同色的棋子。在这个方向上，同一条线上的棋子，行列号的差（seatX-seatY）相等。
     * 其余思路同上
     */
    private static Boolean deSlantSeat(Chess[] chessList, Chess chess){

        int[] slantSeat =new int[22];
        int a=0;
        for (int i=0;i<chess.order/2;i++){
            if (chessList[i]==null) break;
            if (chess.seatX-chess.seatY==chessList[i].seatX-chessList[i].seatY) {
                slantSeat[a] = chessList[i].seatX;
                a++;
            }
        }
        slantSeat[a++]=chess.seatX;
        return isARow(slantSeat);
    }

    /**
     *从右上到左下，同一条线上，棋子行列号和相等
     */
    private static Boolean deSlant2Seat(Chess[] chessList, Chess chess){

        int[] slant2eat =new int[22];
        int a=0;
        for (int i=0;i<chess.order/2;i++){
            if (chessList[i]==null) break;
            if ((chess.seatX+chess.seatY)==chessList[i].seatX+chessList[i].seatY) {
                slant2eat[a] = chessList[i].seatX;
                a++;
            }
        }
        slant2eat[a++]=chess.seatX;
        return isARow(slant2eat);
    }

    /**
     *首先将储存着行/列号的数组进行排序。
     * 然后查找相互之间差值为1的值（即相邻的棋子）。
     * 当相邻的棋子有五个时，输出true。
     */
    private static Boolean isARow(int[] arr){
        if (arr==null) return false;
        Arrays.sort(arr);   //排序
        int count=0;  //计数器
        for (int i=0;i<arr.length-1;i++){
            if ((arr[i+1]-arr[i])==1) count++;
            if (count==4) return true;
        }
        return false;
    }

    public static Boolean isWinM(Chess[] chessList, Chess chess){
        return deSlantSeat(chessList, chess)
                || deSlant2Seat(chessList, chess)
                || deRowSeat(chessList, chess)
                || deCloundSeat(chessList, chess);
    }
}
