package com.efraim.model;

public class DiceScoreCalculation {
	private int mScoreChoice;
	private int mMaxPossibleScore;
	private int mRoadToScoreChoice;
	private Dice[] mUsedDiceArray;
	private Dice[] mNotUsedDiceArray;

	public DiceScoreCalculation(int scoreChoice, Dice[] rolledDice){
		mScoreChoice = scoreChoice;
		mMaxPossibleScore = (totalDiceScore(rolledDice) / scoreChoice) * scoreChoice;

		//todo test code
		System.out.println("ScoreChoice: " + scoreChoice);
		System.out.println("TotalDiceScore: " + totalDiceScore(rolledDice));
		System.out.println("Max possible score: " + mMaxPossibleScore);
		double maxNonClipped = (double)totalDiceScore(rolledDice) / (double)scoreChoice;
		System.out.println("Max possible score non clipped: " + maxNonClipped);
	}

	public void diceCalculate(int startAtArrayIndex){

	}

	public int totalDiceScore(Dice[] diceArray){
		int totalDiceScore = 0;
		for(int i = 0; i < diceArray.length; i++){
			totalDiceScore += diceArray[i].getDiceScore();
		}
		return totalDiceScore;
	}

	//todo testkod
	public String arrayToString(Dice[] array){
		String message;
		if(array == mUsedDiceArray){
			message = "mUsedDiceArray: \n";
		}
		else{
			message = "mNotUsedDiceArray \n";
		}

		if(array == null) {
			message += "is null!";
			return message;
		}
		else {
			for(int i = 0; i < array.length; i++ ){
				message += "Dice"+(i+1) + ": Score = " + array[i].getDiceScore() + " State = " + array[i].getDiceState() +"\n";
			}
			return message;
		}
	}
}
