package com.example.ole.mygame;

/**
 * Created by Ole on 28.01.2016.
 */


import android.graphics.Canvas;
import android.graphics.Typeface;
import android.view.MotionEvent;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.input.TouchListener;

public class Pong extends State implements TouchListener {

    private boolean begun;
    private Canvas deviceCanvas;
    private Image ballImage= new Image(R.drawable.ball);
    private Image playerOneImage = new Image(R.drawable.player1);
    private Image playerTwoImage = new Image(R.drawable.player2);
    private Image backgroundImage = new Image(R.drawable.pongbg);

    private Sprite westWall, eastWall;
    private Sprite backSprite;
    private Sprite ballSprite;
    private Sprite player1Sprite;
    private Sprite player2Sprite;
    private int canvasHeight, canvasWidth;
    private int playerOneScore, playerTwoScore;


    @Override
    public boolean onTouchMove(MotionEvent event) {
        player1Sprite.setPosition((player1Sprite.getX()), event.getY());
        return true;
    }

    @Override
    public boolean onTouchUp(MotionEvent event) {

        player1Sprite.setPosition((player1Sprite.getX()),event.getY());
        return true;
    }

    public Pong() {
        playerOneScore = 0; playerTwoScore = 0;
        begun = false;
        backSprite = new Sprite(backgroundImage);
        ballSprite = new Sprite(ballImage);
        player1Sprite = new Sprite(playerOneImage);
        player2Sprite = new Sprite(playerTwoImage);
        ballSprite.setPosition(canvasWidth / 2, canvasHeight / 2);
        ballSprite.setSpeed(400, 200);
    }

    @Override
    public void draw(Canvas canvas){
        deviceCanvas = canvas;
        canvasHeight = deviceCanvas.getHeight();
        canvasWidth = deviceCanvas.getWidth();

        if(!begun)
        {
            player1Sprite.setPosition(canvasWidth-20, canvasHeight / 2);
            player2Sprite.setPosition(20, canvasHeight / 2);
            begun = true;
        }

        backSprite.draw(canvas);
        ballSprite.draw(canvas);
        player1Sprite.draw(canvas);
        player2Sprite.draw(canvas);

        Font playerOneScoreText = new Font(50, 50, 255, 50, Typeface.SANS_SERIF, Typeface.NORMAL);
        canvas.drawText("" + playerOneScore, (canvasWidth / 2 + 50), 50, playerOneScoreText);
        Font playerTwoScoreText = new Font(255, 50, 50, 50, Typeface.SANS_SERIF, Typeface.NORMAL);
        canvas.drawText(""+playerTwoScore, (canvasWidth / 2 - 50), 50,  playerTwoScoreText);
    }

    public void update(float dt) {

        /// Screen borders ///

        if(ballSprite.getX()>=canvasWidth)
        {
             ballSprite.setSpeed(-ballSprite.getSpeed().getX(), ballSprite.getSpeed().getY());
            playerTwoScore++;
        }
        if(ballSprite.getX() <= 0) {

            ballSprite.setSpeed(-ballSprite.getSpeed().getX(), ballSprite.getSpeed().getY());
            playerOneScore++;
        }

        if(ballSprite.getY() >= canvasHeight)
        {
            ballSprite.setSpeed(ballSprite.getSpeed().getX(), -ballSprite.getSpeed().getY());
        }

        if(ballSprite.getY() <= 0)
        {
            ballSprite.setSpeed(ballSprite.getSpeed().getX(), -ballSprite.getSpeed().getY());
        }
        System.out.println(player1Sprite.getPosition());

        if(ballSprite.getX() >= canvasWidth-30 && ballSprite.getY() < player1Sprite.getY() && ballSprite.getY() > (player1Sprite.getY() - 150))
        {
            playerOneScore += 1111;
            ballSprite.setSpeed(-ballSprite.getSpeed().getX(), ballSprite.getSpeed().getY());
        }
        /// END SCREEN BORDERS //
        player1Sprite.update(dt);
        player2Sprite.update(dt);
        ballSprite.update(dt);
    }

}