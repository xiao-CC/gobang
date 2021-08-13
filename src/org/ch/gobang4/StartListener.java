package org.ch.gobang4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartListener implements ActionListener {

    private GobangUI gobangUI=new GobangUI();
    private JTextField usename=new JTextField();
    private JTextField paw=new JTextField();
    private StartUI startUI;

    public void setStartUI(StartUI startUI){this.startUI=startUI;}
    public void setUsename(JTextField textField){this.usename=textField;}
    public void setpaw(JTextField textField){this.paw=textField;}

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        //密码登录功能，为了方便，暂时先注释掉
        if (usename.getText().equals("123") && paw.getText().equals("123")) {
            startUI.dispose();
            gobangUI.innitGobangMainUI();
        } else {
            JFrame errFr=new JFrame("用户名活密码错误");
            errFr.setSize(400,1);
            errFr.setVisible(true);
        }  */

        //关闭登录界面，打开游戏界面
        startUI.dispose();
        gobangUI.innitGobangMainUI();
    }
}
