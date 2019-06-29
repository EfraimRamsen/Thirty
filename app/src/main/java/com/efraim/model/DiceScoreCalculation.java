package com.efraim.model;

public class DiceScoreCalculation {
	private int mButtonScoreChoice;
	private int mMaxPossibleScore;
	private int mRoadToScoreChoice;
	private int mTotalDiceScore;
	private Dice[] mRolledDiceArray;
	private Dice[] mUsedDiceArray;
	private Dice[] mNotUsedDiceArray;


	public DiceScoreCalculation(int buttonScoreChoice, Game game){
		mRolledDiceArray = game.getDiceArray();
		mButtonScoreChoice = buttonScoreChoice;
		mTotalDiceScore = totalDiceScore(game.getDiceArray());
//		mMaxPossibleScore = (totalDiceScore(mRolledDiceArray) / buttonScoreChoice) * buttonScoreChoice;//fel
		mMaxPossibleScore = maxPossibleScore(game.getDiceArray());

		//todo test code
		System.out.println();
			System.out.println("ScoreChoice: " + buttonScoreChoice);
			System.out.println("TotalDiceScore: " + totalDiceScore(mRolledDiceArray));
			System.out.println("Max possible score: " + mMaxPossibleScore);
//			double maxNonClipped = (double)totalDiceScore(mRolledDiceArray) / (double)buttonScoreChoice;
//			System.out.println("Max possible score non clipped: " + maxNonClipped);
	}

	public void diceCalculate(int startAtArrayIndex){
		if(startAtArrayIndex > mRolledDiceArray.length || startAtArrayIndex < 0){
			//overflow, när man kommer till 0 igen så är det slut och man beräknar
			//todo sänk maxPossiblescore?
			return;
		}


	}

	public int maxPossibleScore(Dice[] diceArray){
		boolean[] diceIndexUsed = {false,false,false,false,false,false};
		int maxPossibleScore = 0;
		int tempScoreChoice = 0;
		int loopRestart = 0;

		for(int i = 0; i < diceArray.length; i++) {
			int diceScore = diceArray[i].getDiceScore();

			// lägg till när tärning är samma som choiceknapp
			if (diceScore == mButtonScoreChoice) {
				maxPossibleScore += diceScore;
				diceIndexUsed[i] = true;

				if(i == 5){
					loopRestart++;
					i = loopRestart;
				}
				continue;
			}

			if ((maxPossibleScore + tempScoreChoice) > (mTotalDiceScore - mButtonScoreChoice)) {
				//ja vad händer då?
				//då går det inte att få mer poäng
				break;
			}

			if (diceScore > mButtonScoreChoice)
				diceIndexUsed[i] = true;

			if (diceIndexUsed[i]){
				if(i == 5){
					loopRestart++;
					i = loopRestart;
				}
				continue;
			}
			// lägg till när tärning är mindre än choiceknapp
			else {
				//lägg till när tärning + det som sparats innan är samma som choiceknapp
				if(tempScoreChoice + diceScore == mButtonScoreChoice){
					tempScoreChoice += diceScore;
					maxPossibleScore += tempScoreChoice;
					tempScoreChoice = 0;
				}
				//lägg till när tärning + det som sparats innan är mindre än choiceknapp
				else if(tempScoreChoice + diceScore < mButtonScoreChoice){
					tempScoreChoice += diceScore;
				}

				diceIndexUsed[i] = true;
				if(i == 5){
					loopRestart++;
					i = loopRestart;
				}
				continue;
			}
		}
		maxPossibleScore = (maxPossibleScore / mButtonScoreChoice) * mButtonScoreChoice;
		return maxPossibleScore;
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
