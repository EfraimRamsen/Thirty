package com.efraim.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DiceScoreCalculation {
	private int mButtonScoreChoice;
	private int mMaxPossibleScore;
	private int mSumOfAllRolledDice;
	private Dice[] mRolledDiceArray;
	private ArrayList<Dice> mUsedDiceList;


	public DiceScoreCalculation(int buttonScoreChoice, Game game){
		mRolledDiceArray = game.getDiceArray();
		mButtonScoreChoice = buttonScoreChoice;
		mSumOfAllRolledDice = sumOfAllDiceInArray(game.getDiceArray());

		mUsedDiceList = getDiceForMaxScore(); //TODO jobba på den här metoden lite enklare, kolla först om en tärning fyller kraven, sen två osv.


		//todo test code
		System.out.println();
		System.out.println();
		System.out.println("mRolledDiceArray: "+diceArrayToString(mRolledDiceArray)/*mRolledDiceArray.toString()*/);
		System.out.println("mUsedDiceList: "+mUsedDiceList.toString());
		System.out.println("mButtonScoreChoice: " + mButtonScoreChoice);
		System.out.println("mSumOfAllRolledDice: " + sumOfAllDiceInArray(mRolledDiceArray));
	}


	public ArrayList<Dice> getDiceForMaxScore(){
		ArrayList<Dice> notUsedDice = new ArrayList<>(Arrays.asList(mRolledDiceArray));
		ArrayList<Dice> usedDice = new ArrayList<>();

		//LOW
		Iterator<Dice> iter1 = notUsedDice.iterator();
		if(mButtonScoreChoice < 4){
			while(iter1.hasNext()) {
				Dice d = iter1.next();
				System.out.println("d"+d);
				if (d.getDiceScore() < 4) {
					usedDice.add(d);
					System.out.println("LOW: Adding to usedDice: " + d);//test
					System.out.println("LOW: Removing from notUsedDice: " + d);//test
					iter1.remove();
				}
			}
			return usedDice;
		}

		//score with one
		Iterator<Dice> iter2 = notUsedDice.iterator();
		while(iter2.hasNext()){
			Dice d = iter2.next();
			if(d.getDiceScore() == mButtonScoreChoice){
				usedDice.add(d);
				System.out.println("ONE: Adding to usedDice: "+d);//test
				System.out.println("ONE: Removing from notUsedDice: "+d);//test
				iter2.remove();
			}
		}

		if(notUsedDice.isEmpty())
			return usedDice;

		//score with two
		for(Dice a : notUsedDice){
			if(usedDice.contains(a))
				continue;
			for(Dice b : notUsedDice){
				if(usedDice.contains(b))
					continue;
				if(a != b && a.getDiceScore() + b.getDiceScore() == mButtonScoreChoice){
					usedDice.add(a);
					usedDice.add(b);
				}
			}
		}
		notUsedDice.removeAll(usedDice);

		if(notUsedDice.isEmpty())
			return usedDice;

		// score with three


		//score with six
		if(notUsedDice.size() == 6){
			int totalScore = 0;
			for(Dice d : notUsedDice){
				totalScore += d.getDiceScore();
			}
			if(totalScore == 6 || totalScore == 12){
				usedDice.addAll(notUsedDice);
				System.out.println("SIX: Adding to usedDice: "+notUsedDice.toString());//test
				System.out.println("SIX: Removing from notUsedDice: " + notUsedDice.toString());//test
				notUsedDice.clear();
				return usedDice;
			}
		}


		System.out.println("Gått igenom alla test!");//test
		return usedDice;
	}

	public int listTotalScore(List<Dice> diceList){
		int totalScore = 0;
		for (Dice d : diceList){
			totalScore += d.getDiceScore();
		}
		return totalScore;
	}

	public int sumOfAllDiceInArray(Dice[] diceArray){
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
