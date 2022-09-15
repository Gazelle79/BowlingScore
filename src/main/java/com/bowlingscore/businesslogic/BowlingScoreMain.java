package com.bowlingscore.businesslogic;

import java.io.File;

public class BowlingScoreMain 
{

	public static void main(String[] args) 
	{
		
		int[] bowlingScores = new int[0];
		String currentPath = new File("").getAbsolutePath();		
		
		String inputFileNameAndPath =  currentPath + "/InputFiles/BowlingScoreInput.txt";
		String outputFileNameAndPath = currentPath + "/OutputFiles/BowlingScoreOutput.txt";
		String[] rawBowlingScores = new String[0]; //blank strings
		boolean wasWrittenToFile = false;
		
		if(args.length > 1)
		{
			inputFileNameAndPath =  args[0];
			outputFileNameAndPath = args[1];
		}
		
		ScoreCalculator parser = new ScoreCalculator();
		
		
		try
		{
			int bowlingScoreCount = parser.getNumberOfBowlingScores(inputFileNameAndPath);
			rawBowlingScores = new String[bowlingScoreCount];
			bowlingScores = new int[bowlingScoreCount];
			
			//Read the bowling score in. (Assume it's always valid)
			//Calculate the score.
			//Write that score to a text file.
			rawBowlingScores = parser.readBowlingScore(inputFileNameAndPath).split("\n");
			
			
			for(int i=0; i<bowlingScoreCount; i++)
			{
				bowlingScores[i] = parser.calculateBowlingScore(rawBowlingScores[i]);
			}
			wasWrittenToFile = parser.writeBowlingScores(bowlingScores, outputFileNameAndPath);
		}
		
		catch (Exception e)
		{
			System.out.println("Something went wrong: " + e.getMessage());
		}
		
		
		
	}

}
