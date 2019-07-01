package com.efraim.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiceScoreCalculation {
	private int mButtonScoreChoice;
	private int mMaxPossibleScore;
	private int mTotalDiceScore;
	private Dice[] mRolledDiceArray;
	private List<Dice> mUsedDiceList;


	public DiceScoreCalculation(int buttonScoreChoice, Game game){
		mRolledDiceArray = game.getDiceArray();
		mButtonScoreChoice = buttonScoreChoice;
		mTotalDiceScore = totalDiceScore(game.getDiceArray());

		mUsedDiceList = getDiceForMaxScore(); //TODO jobba på den här metoden lite enklare, kolla först om en tärning fyller kraven, sen två osv.


		//todo test code
		System.out.println();
		System.out.println("mRolledDiceArray: "+diceArrayToString(mRolledDiceArray)/*mRolledDiceArray.toString()*/);
		System.out.println("mUsedDiceList: "+mUsedDiceList.toString());
		System.out.println("buttonScoreChoice: " + buttonScoreChoice);
		System.out.println("TotalDiceScore: " + totalDiceScore(mRolledDiceArray));
		System.out.println("Max possible score: " + mMaxPossibleScore);
//			double maxNonClipped = (double)totalDiceScore(mRolledDiceArray) / (double)buttonScoreChoice;
//			System.out.println("Max possible score non clipped: " + maxNonClipped);
	}


	public ArrayList<Dice> getDiceForMaxScore(){
		ArrayList<Dice> notUsedDice = new ArrayList<>(Arrays.asList(mRolledDiceArray));
		ArrayList<Dice> usedDice = new ArrayList<>();



		for(int i = diceStartIndex; i < 6; i++, addToSortedDiceArrayIndex++){

//														System.out.println("På index "+addToSortedDiceArrayIndex //todo testkod
//																+ " ska tärning med värde "+mRolledDiceArray[i].getDiceScore()+" skrivas.");

			sortedDiceArray[addToSortedDiceArrayIndex] = mRolledDiceArray[i];
														System.out.println("\nsortedDiceArray första loopen: "+diceArrayToString(sortedDiceArray));//todo testkod
		}

		for(int i = 0; addToSortedDiceArrayIndex < 6; i++){

//														System.out.println("\nPå index "+addToSortedDiceArrayIndex //todo testkod
//														+ " ska tärning med värde "+mRolledDiceArray[i].getDiceScore()+" skrivas.");

			sortedDiceArray[addToSortedDiceArrayIndex] = mRolledDiceArray[i];
														System.out.println("sortedDiceArray andra loopen: "+diceArrayToString(sortedDiceArray));//todo testkod
		}

		//loop: för varje tärning (1-6) (som är kvar i listan);
		for(int i = 0; i < 6; i++){

			//om i + diceStartIndex är större än 5 så är det 5 - i
		}
			//loopa igenom score withOneDice
				//lägg till de som passar i listan
			//loopa igenom score withTwoDice
				// /.../ etc. Sen kör man hela metoden igen men börjar på diceStartIndex+1, sen tar man den som hade högst resultat



		return usedDice;
	}

	public boolean scoreWithOneDice(int diceIndex){
		if(mRolledDiceArray[diceIndex].getDiceScore() == mButtonScoreChoice)
			return true;
		return false;
	}

//	public int getDiceForMaxScore(Dice[] diceArray){
//		boolean[] diceIndexUsed = {false,false,false,false,false,false};
//		int getDiceForMaxScore = 0;
//		int tempScoreChoice = 0;
//		int loopRestart = 0;
//
//		for(int i = 0; i < diceArray.length; i++) {
//			int diceScore = diceArray[i].getDiceScore();
//
//			// lägg till när tärning är samma som choiceknapp
//			if (diceScore == mButtonScoreChoice) {
//				getDiceForMaxScore += diceScore;
//				diceIndexUsed[i] = true;
//
//				if(i == 5){
//					loopRestart++;
//					i = loopRestart;
//				}
//				continue;
//			}
//
//			if ((getDiceForMaxScore + tempScoreChoice) > (mTotalDiceScore - mButtonScoreChoice)) {
//				//ja vad händer då?
//				//då går det inte att få mer poäng
//				break;
//			}
//
//			if (diceScore > mButtonScoreChoice)
//				diceIndexUsed[i] = true;
//
//			if (diceIndexUsed[i]){
//				if(i == 5){
//					loopRestart++;
//					i = loopRestart;
//				}
//				continue;
//			}
//			// lägg till när tärning är mindre än choiceknapp
//			else {
//				//lägg till när tärning + det som sparats innan är samma som choiceknapp
//				if(tempScoreChoice + diceScore == mButtonScoreChoice){
//					tempScoreChoice += diceScore;
//					getDiceForMaxScore += tempScoreChoice;
//					tempScoreChoice = 0;
//				}
//				//lägg till när tärning + det som sparats innan är mindre än choiceknapp
//				else if(tempScoreChoice + diceScore < mButtonScoreChoice){
//					tempScoreChoice += diceScore;
//				}
//
//				diceIndexUsed[i] = true;
//				if(i == 5){
//					loopRestart++;
//					i = loopRestart;
//				}
//				continue;
//			}
//		}
//		getDiceForMaxScore = (getDiceForMaxScore / mButtonScoreChoice) * mButtonScoreChoice;
//		return getDiceForMaxScore;
//	}


	public int totalDiceScore(Dice[] diceArray){
		int totalDiceScore = 0;
		for(int i = 0; i < diceArray.length; i++){
			totalDiceScore += diceArray[i].getDiceScore();
		}
		return totalDiceScore;
	}

	public String diceArrayToString(Dice[] diceArray){
		String message = " ";
		for(int i = 0; i < diceArray.length; i++){
			if(diceArray[i] == null) {
				message += "null" + " ";
			}
			else{
			message += diceArray[i].getDiceScore() + " ";
				}
		}
		return "["+message+"]";
	}
}
