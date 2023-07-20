package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.R
import android.graphics.drawable.*
import android.content.*

//some work needed in this file

fun ToastImageLogic(
context: Context,
winner: Boolean,
isXImage: Boolean,
xImageMax: Int,
circleImageMax: Int
)/*: Painter*/ {

	val image = if (!winner) {
   	 if (isXImage) xImageMax else circleImageMax
	}else {
    	if(!isXImage) xImageMax else circleImageMax
	} 
	//return context.getDrawable(image)!!       
}