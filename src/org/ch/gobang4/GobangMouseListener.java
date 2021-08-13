package org.ch.gobang4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 只用到了mousepressed方法。
 * 用来监听鼠标点击的地方，来进行落子。
 * 负责计步、判断黑白棋、判断落子是否在范围内（其实这个也可以封装进Chess,不过无所谓了）。
 * new一个棋子对象，将其存进相应的对象数组。
 */
public class GobangMouseListener implements GobangConfig, MouseListener {

    //落子的行列号
    int chessX;
    int chessY;
    //用来计数
    int order;
    int bOrder;
    int wOrder;


    //该方法用来将UI的画笔传给接口
    Graphics g;
    public void setGraphics(Graphics g){this.g=g;}
    //同上，获取ui的lable
    private JLabel BLab;
    public void setBLab(JLabel lab){this.BLab=lab;}
    private JLabel WLab;
    public void setWLab(JLabel lab){this.WLab=lab;}


    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        /*
         编号从0开始，从左上角开始编号
         由于java中的除法采用的是去余，即去除掉商的小数点后面的所有值
         所以当我们想要实现四舍五入时，可以尝试先”+SIZE/2“再求商  */
        chessX=(x+SIZE/2-X)/SIZE;
        chessY=(y+SIZE/2-Y)/SIZE;

        if(x>=X-SIZE/2&&x<=X+(CLOUN-1)*SIZE+SIZE/2&&y>=Y-SIZE/2&&y<=Y+(ROW-1)*SIZE+SIZE/2){

            if (order%2==0){

                BlackChess bChess=new BlackChess(order,chessX,chessY,this);
                BlackChess.bList[bOrder]=bChess;
                Chess.chessList[order]=bChess;
                order++;bOrder++;
                BLab.setText("黑棋走了："+bOrder+"步");

            }else if (order%2==1){

                WhiteChess wChess=new WhiteChess(order,chessX,chessY,this);
                WhiteChess.wList[wOrder]=wChess;
                Chess.chessList[order]=wChess;
                order++;wOrder++;
                WLab.setText("白棋走了："+wOrder+"步");

            }

        }else {
            System.out.println("位置错误");
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
