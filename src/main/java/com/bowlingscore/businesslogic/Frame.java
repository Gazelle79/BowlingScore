package com.bowlingscore.businesslogic;

import com.bowlingscore.interfaces.IFrame;

public class Frame implements IFrame
{
	//Keep track of the individual scores per frame.
	enum FrameType {
		STANDARD,
		SPARE,
		MISS,
		STRIKE;
	}
	private char firstRoll;
	private char secondRoll;

	
	private int frameScore;  //Score for this frame.
	private int frameNumber;  //Frame 2 of 10, frame 6 of 10,etc.
	private FrameType frameType; //Strike, Spare, Miss, etc.

	public int getFrameScore() {
		return this.frameScore;
	}
	public FrameType getFrameType() {
		return this.frameType;
	}
	public int getFrameNumber() {
		return this.frameNumber;
	}
	public int getFirstRoll() {
		return this.firstRoll;
	}
	public int getSecondRoll() {
		return this.secondRoll;
	}
	
	
	//Standard or spare
	public Frame(char firstRoll, char secondRoll)
	{
		this.firstRoll = firstRoll;
		this.secondRoll = secondRoll;
	}

	/**
	 * Scoring rules
	 * 
	    X = STRIKE! (10 + total number of pins knocked down in next two rolls, on next turn)
		- = MISS (0)
		[/] = SPARE (10 + total number of pins knocked down in next roll, on next turn)
		[0-9] = STANDARD Total number of pins knocked down on this turn
	 * */
	




}
