package org.ch.gobang4;

import javax.swing.*;
import java.awt.*;

public class StartUI extends JFrame {

    StartListener startListener=new StartListener();

    public void innitStartUI(){
        this.setSize(800,500);
        this.setTitle("崔皓的五子棋");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(null);

        //加两个按钮
        JButton pveBut=new JButton("开始人机对战");
        pveBut.setFont(new Font("", Font.BOLD, 16));
        pveBut.setBounds(150,300,200,80);
        this.add(pveBut);
        JButton pvpBut=new JButton("开始联机游戏");
        pvpBut.setFont(new Font("", Font.BOLD, 16));
        pvpBut.setBounds(450,300,200,80);
        this.add(pvpBut);


        //加两个标签
        JLabel unLab=new JLabel("用户名:");
        unLab.setForeground(Color.BLACK);
        unLab.setFont(new Font("", Font.BOLD, 16));
        unLab.setBounds(200,100,80,40);
        this.add(unLab);
        JLabel pawLab=new JLabel("密码:");
        pawLab.setForeground(Color.BLACK);
        pawLab.setFont(new Font("", Font.BOLD, 16));
        pawLab.setBounds(200,150,80,40);
        this.add(pawLab);


        //用户名、密码输入框
        JTextField usename=new JTextField();
        usename.setBounds(300,100,300,40);
        JPasswordField paw=new JPasswordField();
        paw.setBounds(300,150,300,40);
        this.add(usename);
        this.add(paw);

        this.setVisible(true);

        pveBut.addActionListener(startListener);
        startListener.setStartUI(this);
        startListener.setUsename(usename);
        startListener.setpaw(paw);
    }


}
