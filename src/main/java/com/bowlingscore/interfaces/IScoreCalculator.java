package com.bowlingscore.interfaces;

public interface IScoreCalculator
{
    int getFinalScore(String bowlingData);  //Adds scores for all frames. Returns a final score.

    boolean writeBowlingScores(int[] finalScores, String fileNameAndPath); //Writes final score to screen.
}
