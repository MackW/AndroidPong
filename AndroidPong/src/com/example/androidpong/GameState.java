package com.example.androidpong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class GameState {

	//screen width and height
	final int _screenWidth = 600;
	final int _screenHeight = 1000;

	//The ball
	final int _ballSize = 10;
	int _ballX = 300; 	int _ballY = 450;
	int _ballVelocityX = 3; 	int _ballVelocityY = 3;

	//The bats
	final int _batLength = 75;	final int _batHeight = 10;
	int _topBatX = (_screenWidth/2) - (_batLength / 2);
	final int _topBatY = 20;
	int _bottomBatX = (_screenWidth/2) - (_batLength / 2);	
	final int _bottomBatY = 900;
	final int _batSpeed = 3;

	public GameState()
	{
	}

	//The update method
	public void update() {

	_ballX += _ballVelocityX;
	_ballY += _ballVelocityY;

	//DEATH!
	if(_ballY > _screenHeight || _ballY < 0)     	
	{_ballX = 300; 	_ballY = 450;}  	//Collisions with the sides

	if(_ballX > _screenWidth || _ballX < 0)
	     		_ballVelocityX *= -1; 	//Collisions with the bats     	

	if(_ballX > _topBatX && _ballX < _topBatX+_batLength && _ballY < _topBatY)     	
	                 _ballVelocityY *= -1;  //Collisions with the bats     	

	if(_ballX > _bottomBatX && _ballX < _bottomBatX+_batLength 
	                && _ballY > _bottomBatY)
	                       _ballVelocityY *= -1;
	}

	public boolean keyPressed(int keyCode, KeyEvent msg)
	{
	if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT) //left
	{
	_topBatX += _batSpeed; _bottomBatX -= _batSpeed;
	}

	if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) //right
	{
	_topBatX -= _batSpeed; _bottomBatX += _batSpeed;
	}

	return true;
	}
	public boolean motionDetected(MotionEvent event) {
		float y = event.getY();
		float x = event.getX();
		if ((int)y>(_screenHeight/2)) {		 
			_bottomBatX = (int)x - (_batLength/2);
		}else{
			_topBatX = (int)x - (_batLength/2);
		}
		 return true;
		 }
	//the draw method
	public void draw(Canvas canvas, Paint paint) {

	//Clear the screen
	canvas.drawRGB(20, 20, 20);

	//set the colour
	paint.setARGB(200, 0, 200, 0);

	//draw the ball
	canvas.drawRect(new Rect(_ballX,_ballY,_ballX + _ballSize,_ballY + _ballSize),
	                             paint);

	//draw the bats
	canvas.drawRect(new Rect(_topBatX, _topBatY, _topBatX + _batLength,
	                                      _topBatY + _batHeight), paint); //top bat
	canvas.drawRect(new Rect(_bottomBatX, _bottomBatY, _bottomBatX + _batLength, 
	                                      _bottomBatY + _batHeight), paint); //bottom bat

	}
	}