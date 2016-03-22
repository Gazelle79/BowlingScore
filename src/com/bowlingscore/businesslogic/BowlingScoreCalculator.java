package com.bowlingscore.businesslogic;

import java.io.*;

public class BowlingScoreCalculator 
{	
	private final int maxFrames = 10;
	private final int maxTriesPerFrame = 2;
	
	/** Scoring Rules
	    X = STRIKE (10 + total number of pins knocked down in next two rolls, on next turn)
		- = MISS (0)
		[/] = SPARE (10 + total number of pins knocked down in next roll, on next turn)
		[0-9] = Total number of pins knocked down on this turn
	 **/
	
	/**
	 * Reads a bowling score from a text file.
	 * */
	public String readBowlingScore(String fileNameAndPath) throws IOException
	{
		BufferedReader reader = null;
		String bowlingScoreText = null;
		StringBuilder contents = new StringBuilder();
				
			/*Not put in try / catch statement so error could be sent back to method caller.*/
			InputStream is = new FileInputStream(fileNameAndPath);
			reader = new BufferedReader(new InputStreamReader(is));
			
			while ((bowlingScoreText = reader.readLine()) != null) 
			{
				contents.append(bowlingScoreText + "\n");
			}
			reader.close();
				
		return contents.toString();
	}
	
	/**
	 * Calculates scoring per frame. A player has two tries in a frame.
	 * Both of those scores are recorded and added in an individual frame score. 
	 * */
	public int calculateBowlingScore(String rawBowlingScore)
	{				
		int[] frames = new int[maxFrames]; //Allocates an array of 10 frames.
		int finalScore = 0;
		int frameScore = 0;
		int frameindex = 0; //index used for the array of frames.
		
		char currentChar = ' ';
		char secondChar = ' ';
		char thirdChar = ' ';
		
		
		int tries = 0; //2 tries = 1 frame.
		
		//You have 10 frames. Put the score for that frame in an array.
		//Add all scores in the frames, and you're done.
		
		for(int i=0; i < rawBowlingScore.length()-1; i++)
		{
			currentChar = rawBowlingScore.charAt(i);
			if(currentChar == '-')
			{
				frameScore += this.getRollScore(currentChar);
				tries++;
				if(tries == maxTriesPerFrame) //This frame is done with 2 tries.
				{
					//Put frameScore in frames[i].
					frames[frameindex] = frameScore;
					System.out.println("Frame " + frameindex + " score: " + frameScore);
					frameindex++;
					
					//reset Tries and Framescore to 0.
					tries = 0;
					frameScore = 0;
				}
			}
			else if(this.isNumeric(currentChar))
			{
				frameScore += this.getRollScore(currentChar);
				tries++;
				if(tries == maxTriesPerFrame) //This frame is done with 2 tries.
				{
					frames[frameindex] = frameScore;
					System.out.println("Frame " + frameindex + " score: " + frameScore);
					frameindex++;
					tries = 0;
					frameScore = 0;
				}
			}
			else if(currentChar == '/')
			{
				//Score is 10 + pins hit in next throw.
				secondChar = rawBowlingScore.charAt(i+1);
				frameScore = this.getRollScore(currentChar) + this.getRollScore(secondChar);
				tries = 2;
				if(tries == maxTriesPerFrame) //This frame is done with 2 tries.
				{
					frames[frameindex] = frameScore;
					System.out.println("Frame " + frameindex + " score: " + frameScore);
					frameindex++;
					tries = 0;
					frameScore = 0;
				}
			}
			else if(currentChar == 'X')
			{
				//Score is 10 + pins hit in next TWO throws.
				secondChar = rawBowlingScore.charAt(i+1);
				thirdChar = rawBowlingScore.charAt(i+2);
				frameScore = this.getRollScore(currentChar) + this.getRollScore(secondChar) + this.getRollScore(thirdChar);
				tries = 2;
				if(tries == maxTriesPerFrame) //This frame is done with 2 tries.
				{
					frames[frameindex] = frameScore;
					System.out.println("Frame " + frameindex + " score: " + frameScore);
					frameindex++;
					tries = 0;
					frameScore = 0;
				}
			}
			if(frameindex == maxFrames)
			{ break; }
		}
		
		//Frames[] is an array of scores for each frame. 
		//Add all the scores for the final score.
		for( int singleFrameScore : frames) 
		{
		    finalScore += singleFrameScore;
		}
		
		System.out.println("\n The FINAL score is: " + finalScore);
		return finalScore;
	}	

	
	/**
	 * Returns the score a user got on his first or second roll in a frame. 
	 * */
	private int getRollScore(char charToTranslate)
	{
		int rollScore = 0;
		if(this.isNumeric(charToTranslate)) //Standard hit. 0-9 pins.
		{
			rollScore = Character.getNumericValue(charToTranslate);
		}
		else if(charToTranslate == '-') //Miss
		{
			rollScore = 0;
		}
		else if(charToTranslate == 'X') //Strike
		{
			rollScore = 10;
		}
		else if(charToTranslate == '/') //Spare
		{
			rollScore = 10;
		}
		else
		{
			rollScore = rollScore + 0;
		}
		
		return rollScore;
	}
	
	/**
	 * Sees is the character read from the bowling string unput is numeric [0-9].
	 * */
	private boolean isNumeric(char str)  
	{ 
		boolean isNumeric = false;
		try  
		{  
			int character = Integer.parseInt(Character.toString(str));
			isNumeric = true;
		}  
		catch(NumberFormatException e)  
		{  
			isNumeric = false;  
		}  
	  return isNumeric;  
	}
	
	/**
	 * Returns the ACTUAL number of frames in a bowling input stream. 
	 * Accounts for ANY combination of strikes, spares, misses, etc.
	 * Thought I would need this method, but I won't.
	 * */
	private int getFrameCount(String rawBowlingScore)
	{
		int frameCount = 0;
		char currentChar = ' ';
		int charCount = 0;
		
		for(int i=0; i<rawBowlingScore.length()-1; i++)
		{
			currentChar = rawBowlingScore.charAt(i);
			if(currentChar == 'X' && frameCount < maxFrames)
			{
				frameCount+=1;
			}
			else
			{
				charCount +=1;
				if(charCount %2 == 0 && frameCount < maxFrames)
				{
					frameCount +=1 ;
					charCount = 0;
				}
			}
		}
		
		return frameCount;
	}
	
	/**
	 * Will write the bowling score to an output file.
	 * */
	public boolean writeBowlingScore(int finalScore, String fileNameAndPath)
	{
		boolean wasWrittenToFile = false;

			try 
			{
				File businessCardFile = new File(fileNameAndPath);
				
				if(businessCardFile.exists())
				{
					businessCardFile.delete();
				}
				
				FileWriter writer = new FileWriter(businessCardFile);				
				writer.write("Final bowling score: " + finalScore);
				writer.close();
				wasWrittenToFile = true;								
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		
		return wasWrittenToFile;
	}
	
}
