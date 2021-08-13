package org.ch.gobang4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 悔棋按钮的监听器，完成悔棋操作
 * 其实感觉这些东西大部分可以封装在Chess类中，但是懒得做了
 */
public class ButListener implements ActionListener, GobangConfig {

    //1.获取前端的lable控制权
    private JLabel BLab;
    public void setBLab(JLabel lab){this.BLab=lab;}
    private JLabel WLab;
    public void setWLab(JLabel lab){this.WLab=lab;}


    @Override
    public void actionPerformed(ActionEvent e) {

        //2.遍历chessList,找到最后一枚棋子,并且删除
        int del=0;
        for (int i=1;i<ROW*CLOUN;i++){
            if (Chess.chessList[i]==null){
                del=i-1;
                break;
            }
        }
        Chess.chessList[del]=null;


        /*
         * 下面两句比较陌生，第一句，返回鼠标点击的组件（按钮）
         * 第二句，返回会按钮所在的JFrame */
        JButton btn = (JButton) e.getSource();
        GobangUI gb= (GobangUI) btn.getRootPane().getParent();


        //3.撤回棋子后，需要重新确定下一课棋该下黑/白子
        if (del%2==0){
            gb.gl.order--;
            gb.gl.bOrder--;
        }else if (del%2==1){
            gb.gl.order--;
            gb.gl.wOrder--;
        }

        //4.调整记步的lable
        BLab.setText("黑棋走了："+gb.gl.bOrder+"步");
        WLab.setText("白棋走了："+gb.gl.wOrder+"步");
        //重绘所有棋子
        gb.repaint();
    }
}
