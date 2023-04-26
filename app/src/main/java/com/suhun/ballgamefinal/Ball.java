package com.suhun.ballgamefinal;

import java.util.Random;
import java.util.TimerTask;

public class Ball extends TimerTask {
    public String tag = Ball.class.getSimpleName();
    private float ballX, ballY;
    private int viewW, viewH, ballW, ballH, dX, dY;
    private int ballResourceId;
    public Ball(int viewW, int viewH, int ballW, int ballH, float ballX, float ballY){
        this.viewW = viewW;
        this.viewH = viewH;
        this.ballW = ballW;
        this.ballH = ballH;
        this.ballX = ballX;
        this.ballY = ballY;
        ballResourceId = new Random().nextInt(4);
        dX = (int)((Math.random()*2==0)?1:-1) * (int)(Math.random()*16 + 4);
        dY = (int)((Math.random()*2==0)?1:-1) * (int)(Math.random()*16 + 4);
    }
    @Override
    public void run() {
        if(ballX < 0 || ballX + ballW > viewW){
            dX *= -1;
        }else if(ballY < 0 || ballY + ballH > viewH){
            dY *= -1;
        }
        ballX = ballX + dX;
        ballY = ballY + dY;
    }
    public float getBallX(){
        return this.ballX;
    }
    public float getBallY(){
        return this.ballY;
    }
    public int getBallResourceId(){
        return this.ballResourceId;
    }
}
