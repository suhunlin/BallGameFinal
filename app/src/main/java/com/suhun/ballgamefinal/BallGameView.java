package com.suhun.ballgamefinal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class BallGameView extends View {
    private String tag = BallGameView.class.getSimpleName();
    private Resources res;
    private boolean isInitBallGame;
    private int viewW, viewH, ballW, ballH;
    private int[] ballResoutceId = {R.drawable.ball0, R.drawable.ball1, R.drawable.ball2, R.drawable.ball3};
    private Bitmap[] ballsBmp = new Bitmap[ballResoutceId.length];
    private Timer timer = new Timer();
    private LinkedList<Ball> balls = new LinkedList<>();

    private class RefreshTask extends TimerTask{
        @Override
        public void run() {
            postInvalidate();
        }
    }
    public BallGameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundResource(R.drawable.ballgame_bg);
        res = context.getResources();
    }

    private void initBallGame(){
        viewW = getWidth(); viewH = getHeight();
        ballW = (int)(viewW/12.0); ballH = ballW;
        for(int i=0;i<ballResoutceId.length;i++){
            ballsBmp[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, ballResoutceId[i])
                    , ballW, ballH, false);
        }
        timer.schedule(new RefreshTask(), 0, 30);
        isInitBallGame = true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isInitBallGame){
            initBallGame();
            isInitBallGame = true;
        }
        for(Ball ball:balls){
            canvas.drawBitmap(ballsBmp[ball.getBallResourceId()], ball.getBallX(), ball.getBallY(), null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Ball ball = new Ball(viewW, viewH, ballW, ballH, event.getX(), event.getY());
        balls.add(ball);
        timer.schedule(ball, 100, 30);
        return super.onTouchEvent(event);
    }
}
