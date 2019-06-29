package com.efraim.model;


public class DiceScoreCheck {
	private Dice[] mRolledDiceArray;
	private int mButtonScoreChoice;
	private boolean[][] mDiceMatrix = new boolean[7][7];

	public DiceScoreCheck(int buttonScoreChoice, Game game){
		mRolledDiceArray = game.getDiceArray();
		mButtonScoreChoice = buttonScoreChoice;

		//todo funkar inte ännu
		for(int score = 1; score < mRolledDiceArray.length; score++){
			for(int dice = 1; dice < mRolledDiceArray.length; dice++) {

				if(mRolledDiceArray[dice-1].getDiceScore() == score)
					mDiceMatrix[score][dice] = true;
			}
		}
		System.out.println(this.toString());
	}


	public String toString() {
		String message = "\nThe matrix\n";
		for (int score = 0; score < mRolledDiceArray.length; score++) {
			for (int dice = 0; dice < mRolledDiceArray.length; dice++) {
				if(mDiceMatrix[score][dice]){
					message += "X ";
				}
				else{
					message += "- ";
				}
			}
			message +="\n";
		}
		return message;
	}
}
