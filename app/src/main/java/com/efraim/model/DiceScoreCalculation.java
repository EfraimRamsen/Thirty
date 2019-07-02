package com.efraim.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
		System.out.println();
		System.out.println("mRolledDiceArray: "+diceArrayToString(mRolledDiceArray)/*mRolledDiceArray.toString()*/);
		System.out.println("mUsedDiceList: "+mUsedDiceList.toString());
		System.out.println("buttonScoreChoice: " + buttonScoreChoice);
		System.out.println("TotalDiceScore: " + totalDiceScore(mRolledDiceArray));
//		System.out.println("Max possible score: " + mMaxPossibleScore);
//			double maxNonClipped = (double)totalDiceScore(mRolledDiceArray) / (double)buttonScoreChoice;
//			System.out.println("Max possible score non clipped: " + maxNonClipped);
	}


	public ArrayList<Dice> getDiceForMaxScore(){
		ArrayList<Dice> notUsedDice = new ArrayList<>(Arrays.asList(mRolledDiceArray));
		ArrayList<Dice> usedDice = new ArrayList<>();
		Iterator<Dice> notUsedDiceIterator = notUsedDice.iterator();

		//LOW
		if(mButtonScoreChoice < 4){
			while(notUsedDiceIterator.hasNext()) {
				Dice d = notUsedDiceIterator.next();
				System.out.println("d"+d);
				if (d.getDiceScore() < 4) {
					usedDice.add(d);
					System.out.println("LOW: Adding to usedDice: " + d);//test
					System.out.println("LOW: Removing from notUsedDice: " + d);//test
					notUsedDiceIterator.remove();
				}
			}
			return usedDice;
		}

		//score with one
		while(notUsedDiceIterator.hasNext()){
			Dice d = notUsedDiceIterator.next();
			if(d.getDiceScore() == mButtonScoreChoice){
				usedDice.add(d);
				System.out.println("ONE: Adding to usedDice: "+d);//test
				System.out.println("ONE: Removing from notUsedDice: "+d);//test
				notUsedDiceIterator.remove();
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
					System.out.println("TWO: Adding to usedDice: "+d+ " & "+otherDice);//test
					notUsedDice.remove(d);
					notUsedDice.remove(otherDice);
					System.out.println("TWO: Removing from notUsedDice: "+d + " & " + otherDice);//test
				}
			}
		}

		if(notUsedDice.isEmpty())
			return usedDice;

		// score with three
		for(Dice a : notUsedDice){
			for(Dice b : notUsedDice){
				for(Dice c : notUsedDice){
					if((a != b) && (b != c)){
						if(a.getDiceScore()+b.getDiceScore()+c.getDiceScore() == mButtonScoreChoice){
							usedDice.add(a);
							usedDice.add(b);
							usedDice.add(c);
							System.out.println("Adding to usedDice: "+a+ " & "+b+" & "+c);//test
							notUsedDice.remove(a);
							notUsedDice.remove(b);
							notUsedDice.remove(c);
							System.out.println("Removing from notUsedDice "+a+ " & "+b+" & "+c);//test
						}
					}
				}

			}
		}

		//score with six
		if(notUsedDice.size() == 6){
			int totalScore = 0;
			for(Dice d : notUsedDice){
				totalScore += d.getDiceScore();
			}
			if(totalScore == 6 || totalScore == 12){
				usedDice = notUsedDice;
				notUsedDice.clear();
				return usedDice;
			}
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
