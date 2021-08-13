package org.ch.gobang4;

import javax.swing.*;
import java.awt.*;

public class BlackChess extends Chess implements GobangConfig{

    static BlackChess[] bList=new BlackChess[ROW*CLOUN/2+1];


    public BlackChess(int order,int seatX, int seatY,GobangMouseListener goMouLis) {

        /*
         * 子类必须调用父类的构造方法，如父类构造无参，可不写（但是依旧调用了）
         * 并且父类的构造，必须是子类构造的第一行
         * 此时不会创建父类对象，只是会调用父类的构造来初始化子类对象的属性
         * 此时如果在父类的构造中引用this,这个this其实是子类对象而且是一个未初始化的对想  */
        super(order, 1, seatX, seatY);

        if (!deDoul(seatX, seatY)) {
            System.out.println("重复落子");
            return;
        }
        this.drawBlack(goMouLis);

        //判断输赢，isWinM方法的参数为chess，此处运用里氏替换原则，传参数为bChess
        if (IsWin.isWinM(bList,this)){
            JOptionPane.showMessageDialog(null,"黑棋赢");
        }
    }


    Graphics g;
    private void drawBlack(GobangMouseListener goMouLis){
        this.g=goMouLis.g;
        g.setColor(Color.BLACK);
        g.fillOval(X+seatX*SIZE-SIZE/2,Y+seatY*SIZE-SIZE/2,SIZE,SIZE);
    }
}
