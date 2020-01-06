package com.xuc.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * @author xuc
 * @create 2020-01-05 13:25
 */
public class GamePanel extends JPanel implements KeyListener,ActionListener {
    int length;
    int[] snakeX = new int[600];
    int[] snakeY = new int[500];
    String fx;
    boolean isStart = false;    //游戏是否开始
    int heardLevel = 400;
    Timer timer = new Timer(heardLevel,  this);
    int foodX;
    int foodY;
    Random random = new Random();
    boolean isFail = false;
    int score;

    public GamePanel(){
        init();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    public void init(){
        length = 3;
        snakeX[0] = 100;
        snakeY[0] = 100;
        snakeX[1] = 75;
        snakeY[1] = 100;
        snakeX[2] = 50;
        snakeY[2] = 100;
        fx= "R";
        foodX = 25 + 25 * random.nextInt(34);
        foodY = 75 + 25 * random.nextInt(24);
        score = 0;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//清屏
        this.setBackground(Color.WHITE);
        Data.header.paintIcon(this,g,25,11);

        g.fillRect(25,75,850,600);
        //静态小蛇
        if(fx.equals("R")){
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("L")){
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("U")){
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("D")){
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }

        for (int i = 1; i<length; i++){
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑",Font.BOLD,18));
        g.drawString("长度:"+length,750,35);
        g.drawString("分数:"+score,750,50);

        //食物
        Data.food.paintIcon(this,g,foodX,foodY);

        if(isStart == false){
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏" ,300,300);
        }

        if (isFail){
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格重新开始游戏" ,300,300);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //获取按下的键盘
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE){
            if(isFail){
                isFail = false;
                init();
            }else{
                isStart = !isStart;
            }
            repaint();
        }
        if (keyCode == KeyEvent.VK_LEFT && fx != "R"){
            fx = "L";
        } else if(keyCode == KeyEvent.VK_RIGHT && fx != "L"){
            fx = "R";
        } else if(keyCode == KeyEvent.VK_UP&& fx != "D") {
            fx = "U";
        } else if(keyCode == KeyEvent.VK_DOWN&& fx != "U"){
            fx = "D";
        }

    }
    
    //执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        if(isStart && !isFail){
            for(int i = length -1 ;i>0;i--){
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            if(fx.equals("R")){
                snakeX[0] = snakeX[0] + 25;
                if(snakeX[0]>850){
                    snakeX[0] = 25;
                }
            } else if(fx.equals("L")){
                snakeX[0] = snakeX[0] - 25;
                if(snakeX[0]<25){
                    snakeX[0] = 850;
                }
            } else if(fx.equals("U")){
                snakeY[0] = snakeY[0] - 25;
                if(snakeY[0]<75){
                    snakeY[0] = 650;
                }
            }else if(fx.equals("D")){
                snakeY[0] = snakeY[0] + 25;
                if(snakeY[0]>650){
                    snakeY[0] = 75;
                }
            }

            if(snakeX[0] == foodX && snakeY[0] == foodY){
                length ++ ;
                score += 10;
                if(heardLevel != 50){
                    heardLevel -= 50;
                }
                timer.setDelay(heardLevel);
                foodX = 25 + 25 * random.nextInt(34);
                foodY = 75 + 25 * random.nextInt(24);
            }

            for(int i = 1;i <length; i++){
                if(snakeX[0]==snakeX[i] && snakeY[0] ==snakeY[i]){
                    isFail = true;
                }
            }

            repaint();
        }
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }

}
