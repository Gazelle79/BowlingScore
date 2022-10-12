package com.bowlingscore.businesslogic;

import java.io.*;

public class ScoreCalculator
{	
	private final int maxFrames = 10;
	private final int maxTriesPerFrame = 2;
	
	/** Scoring Rules
	    X = STRIKE (10 + total number of pins knocked down in next two rolls, on next turn)
		- = MISS (0)
		[/] = SPARE (10 + total number of pins knocked down in next roll, on next turn)
	 	[0-9] = STANDARD Total number of pins knocked down on this turn
	 **/
	
	/**
	 *
	 * */

	/**
	 Reads a bowling score from a text file.
	 @param fileNameAndPath The filename and path to the file that contains all bowling scores.
	 @return A String of all bowling scores, separated by a newline (\n).
	 */
	public String[] readBowlingScores(String fileNameAndPath) throws IOException
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
				
		return contents.toString().split("\n");
	}

	/**
	 Gets the count of bowling scores read from a text file.
	 @param fileNameAndPath The filename and path to the file that contains all bowling scores.
	 @return A String of all bowling scores, separated by a newline (\n).
	 */
	public int getNumberOfBowlingScores(String fileNameAndPath) throws IOException
	{
		int numberOfGames = 0;
		
		BufferedReader reader = null;
		InputStream is = new FileInputStream(fileNameAndPath);
		reader = new BufferedReader(new InputStreamReader(is));
			
		while ((reader.readLine()) != null)
		{
			numberOfGames++;
		}
		reader.close();
		
		return numberOfGames;
	}


	/**
	 Calculates the score for one game. A game has ten frames. Each frame consists of two tries.
	 Scores from both tries are recorded and added in a single frame.

	 @param rawBowlingScore A bowling score for one game, expressed as characters from a text file.
	 @return The actual score for this game, as an integer.
	 */
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
		
		System.out.println("\nFinal score for this game: " + finalScore + "\n");
		return finalScore;
	}


	/**
	 Returns the score, for a first or second roll, as an integer. The score for two rolls is stored in a frame.

	 @param charToTranslate A single character from a raw bowling score.
	 @return The actual score for this roll, as an integer.
	 */
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
	 Returns true if a character from a raw bowling score is numeric. Otherwise, false.

	 @param score A single character from a raw bowling score.
	 @return The actual score for this roll, as an integer.
	 */
	private boolean isNumeric(char score)
	{ 
		boolean isNumeric = false;
		try  
		{  
			int character = Integer.parseInt(Character.toString(score));
			isNumeric = true;
		}  
		catch(NumberFormatException e)  
		{  
			isNumeric = false;  
		}  
	  return isNumeric;  
	}

	/**
	 Returns the actual number of frames in a raw bowling score. Accounts for any number of strikes,
	 spares, etc. in case some idiot user wants to make a game with more than 10 frames.

	 @param rawBowlingScore A String represent a single game, as a String of characters from a text file.
	 @return The number of frames in this game, as an integer.
	 */
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
	 Writes a single bowling score to the console and a text file, as output.

	 @param fileNameAndPath A filename and path to write a text file to, containing the score.
	 @param finalScore The final score for this game. All the scores are added from all the frames.
	 @return True, if the final score was successfully written to the text file. Otherwise, false.
	 */
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


	/**
	 Writes many bowling scores to the console and a text file, as output.

	 @param fileNameAndPath A filename and path to write a text file to, containing the score.
	 @param finalScores The final score for these games. All the scores are added from all the frames.
	 @return True, if the final score was successfully written to the text file. Otherwise, false.
	 */
	public boolean writeBowlingScores(int[] finalScores, String fileNameAndPath)
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
				for(int i=0; i<= finalScores.length-1; i++)
				{
					writer.write("Game " + i + " final score: " + finalScores[i] + "\n");
				}	
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
