package org.ch.gobang4;

import javax.swing.*;
import java.awt.*;

/**
 * 相应注释请见blackchess类
 */
public class WhiteChess extends Chess implements GobangConfig{
    static WhiteChess[] wList=new WhiteChess[ROW*CLOUN-1];

    public WhiteChess(int order,int seatX,int seatY,GobangMouseListener goMouLis) {
        super(order, 2, seatX, seatY);
        if (!deDoul(seatX, seatY)) {
            System.out.println("重复落子");
            return;
        }
        this.drawWhite(goMouLis);

        //判断输赢，isWinM方法的参数为chess，此处运用里氏替换原则，传参数为wChess
        if (IsWin.isWinM(wList,this)){
            JOptionPane.showMessageDialog(null,"白棋赢");
        }
    }

    Graphics g;
    private void drawWhite(GobangMouseListener goMouLis){
        this.g=goMouLis.g;
        g.setColor(Color.WHITE);
        g.fillOval(X+seatX*SIZE-SIZE/2,Y+seatY*SIZE-SIZE/2,SIZE,SIZE);
    }
}
