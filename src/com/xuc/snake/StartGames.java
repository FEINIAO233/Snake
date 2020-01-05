package com.xuc.snake;

import javax.swing.*;

/**
 * @author xuc
 * @create 2020-01-05 13:21
 */
public class StartGames {
    public static void main(String[] args) {
        //绘制静态窗口
        JFrame jFrame = new JFrame("贪吃蛇");
        jFrame.setBounds(10,10,900,720);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //面板
        jFrame.add(new GamePanel());
        jFrame.setVisible(true);
    }
}
