package org.ch.gobang4;

import javax.swing.*;
import java.awt.*;

public class GobangUI extends JFrame implements GobangConfig {

    GobangMouseListener gl=new GobangMouseListener();
    ButListener butListener=new ButListener();
    private static final Image bgimg = new ImageIcon("img/ab.jpeg").getImage();


    public void innitGobangMainUI(){

        this.setSize(1300,900);
        this.setTitle("五子棋");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(null);

        //悔棋按钮
        JButton reBut=new JButton("悔棋");
        reBut.setFont(new Font("", Font.BOLD, 16));
        reBut.setBounds(900,700,100,50);
        this.add(reBut);

        //计数台
        JLabel BnumLab=new JLabel("白棋走了： 步");
        BnumLab.setFont(new Font("", Font.BOLD, 16));
        BnumLab.setBounds(900,100,150,50);
        this.add(BnumLab);
        JLabel WnumLab=new JLabel("白棋走了： 步");
        WnumLab.setFont(new Font("", Font.BOLD, 16));
        WnumLab.setBounds(900,160,150,50);
        this.add(WnumLab);


        this.setVisible(true);
        gl.setGraphics(this.getGraphics());
        gl.setBLab(BnumLab);
        gl.setWLab(WnumLab);
        butListener.setBLab(BnumLab);
        butListener.setWLab(WnumLab);
        this.addMouseListener(gl);
        reBut.addActionListener(butListener);
    }

    @Override
    public void paint(Graphics g) {

        /*在重写paint方法时, 调用 super.paint(g); 就可以保留之前的绘制, 接着绘制
        如果删除了super.paint(g); 那么之前的绘制, 就会被清除*/
        super.paint(g);

        g.drawImage(bgimg, X, Y, (CLOUN - 1) * SIZE, (ROW - 1) * SIZE, null);
        for (int i = 0; i < 15; i++) {
            //画横线
            g.drawLine(X, Y + i * SIZE, X + (CLOUN - 1) * SIZE, Y + i * SIZE);
            //画竖线
            g.drawLine(X + i * SIZE, Y, X + i * SIZE, Y + (ROW - 1) * SIZE);
        }

        Chess[] chessList=Chess.chessList;
        for (int i = 0; i < chessList.length; i++) {

            /*
               非空判断,虽然代码上确实给chessList传了值
               但是编译器不放心，依旧认为下面的chessList[i]有可能存在空值
               所以要做非空判断，否则虽然有运行效果，但是控制台会报错 */
            if (chessList[i]==null) return;

            /*
               重绘功能，使得拖动UI时，棋子不消失
               在棋子撤回后，也可以实现其他棋子的重绘 */
            if (chessList[i].chessNum == 1) {
                g.setColor(Color.BLACK);
                g.fillOval(X+chessList[i].seatX*SIZE-SIZE/2,Y+chessList[i].seatY*SIZE-SIZE/2,SIZE,SIZE);
            } else if (chessList[i].chessNum == 2) {
                g.setColor(Color.white);
                g.fillOval(X+chessList[i].seatX*SIZE-SIZE/2,Y+chessList[i].seatY*SIZE-SIZE/2,SIZE,SIZE);
            }
        }
    }

}
