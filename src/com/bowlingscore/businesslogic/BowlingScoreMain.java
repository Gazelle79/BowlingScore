package com.bowlingscore.businesslogic;

import java.io.File;

public class BowlingScoreMain 
{

	public static void main(String[] args) 
	{
		String rawBowlingScore = "";
		int bowlingScore = 0;
		String currentPath = new File("").getAbsolutePath();		
		
		String inputFileNameAndPath =  currentPath + "/InputFiles/BowlingScoreInput.txt";
		String outputFileNameAndPath = currentPath + "/OutputFiles/BowlingScoreOutput.txt";
		
		if(args.length > 1)
		{
			inputFileNameAndPath =  args[0];
			outputFileNameAndPath = args[1];
		}
		
		BowlingScoreCalculator parser = new BowlingScoreCalculator();
		
		try
		{
			//Read the bowling score in. (Assume it's always valid)
			rawBowlingScore = parser.readBowlingScore(inputFileNameAndPath);
		}
		
		catch (Exception e)
		{
			System.out.println("Something went wrong: " + e.getMessage());
		}
		
		//Calculate the score.
		//Write that score to a text file.
		bowlingScore = parser.calculateBowlingScore(rawBowlingScore);
		boolean wasWrittenToFile = parser.writeBowlingScore(bowlingScore, outputFileNameAndPath);

	}

}
