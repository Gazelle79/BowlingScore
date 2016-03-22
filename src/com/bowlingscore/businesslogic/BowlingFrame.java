package com.bowlingscore.businesslogic;

import java.io.*;

public class BowlingFrame 
{
	//Keep track of the individual scores per frame.

	private boolean isStrike = false;
	private char firstTry;
	private char secondTry;
	
	private char strike;
	private char thirdTry;
	
	private int totalFrameScore;  //make this a public property using a getter.
	
	
	//Standard or spare
	public BowlingFrame(char firstTry, char secondTry)
	{
		this.firstTry = firstTry;
		this.secondTry = secondTry;
		this.isStrike = false;
	}
	
	//Strike
	public BowlingFrame(char strike, char secondTry, char thirdTry)
	{
		this.strike = strike;
		this.secondTry = secondTry;
		this.thirdTry = thirdTry;
		this.isStrike = true;
	}
	
	
	/**
	 * Scoring rules
	 * 
	    X = STRIKE! (10 + total number of pins knocked down in next two rolls, on next turn)
		- = MISS (0)
		[/] = SPARE (10 + total number of pins knocked down in next roll, on next turn)
		[0-9] = Total number of pins knocked down on this turn
	 * */
	
	//Add methods to keep track of each score per frame??
	public void getScore(BowlingFrame thisFrame)
	{
		
		
		
	}
}
