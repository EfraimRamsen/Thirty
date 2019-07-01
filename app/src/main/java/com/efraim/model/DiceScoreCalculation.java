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

		mUsedDiceList = getDiceForMaxScore(buttonScoreChoice); //TODO jobba på den här metoden lite enklare, kolla först om en tärning fyller kraven, sen två osv.


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


	public ArrayList<Dice> getDiceForMaxScore(int buttonScoreChoice){
		ArrayList<Dice> notUsedDice = new ArrayList<>(Arrays.asList(mRolledDiceArray));
		ArrayList<Dice> usedDice = new ArrayList<>();
		//todo gör undantag för LOW

		//score with one
		for(Dice d : notUsedDice){
			if(d.getDiceScore() == mButtonScoreChoice){
				usedDice.add(d);
				System.out.println("Adding to usedDice: "+d);//test
				notUsedDice.remove(d);
				System.out.println("Removing from notUsedDice: "+d);//test
			}
		}

		if(notUsedDice.isEmpty())
			return usedDice;

		//score with two
		for(Dice d : notUsedDice){
			for(Dice otherDice : notUsedDice){
				if(d != otherDice && (d.getDiceScore() + otherDice.getDiceScore() == mButtonScoreChoice))	{
					usedDice.add(d);
					usedDice.add(otherDice);
					System.out.println("Adding to usedDice: "+d+ " & "+otherDice);//test
					notUsedDice.remove(d);
					notUsedDice.remove(otherDice);
					System.out.println("Removing from notUsedDice: "+d + " & " + otherDice);//test
				}
			}
		}

		if(notUsedDice.isEmpty())
			return usedDice;

		for(Dice a : notUsedDice){
			for(Dice b : notUsedDice){
				if(a != b 	{

		}

			}
				int ab = a.getDiceScore() + b.getDiceScore();
			}






		return usedDice;
	}

	public int listTotalScore(List<Dice> diceList){
		int totalScore = 0;
		for (Dice d : diceList){
			totalScore += d.getDiceScore();
		}
		return totalScore;
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
