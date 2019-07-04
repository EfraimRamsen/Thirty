package com.efraim.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Calculates the score for a chosen score button (mButtonScoreChoice)
 * by going through all the rolled dice (mRolledDiceArray) and
 * saving the dice that are used for scoring (mUsedDiceList).
 * @author Efraim Ramsén
 */
public class DiceScoreCalculation {
	private int mButtonScoreChoice;
	private Dice[] mRolledDiceArray;
	private ArrayList<Dice> mUsedDiceList;


	/**
	 * Constructor that sets mRolledDiceArray, mButtonScoreChoice and mUsedDiceList
	 * @param buttonScoreChoice the score choice button being pressed, supposed
	 *                          to be an int 3-12 where number 3 = the 'LOW' choice and the others
	 *                          are the score choices 4-12
	 * @param rolledDiceArray, the array containing the currently rolled and displayed Dice objects;
	 */
	DiceScoreCalculation(int buttonScoreChoice, Dice[] rolledDiceArray){
		mRolledDiceArray = rolledDiceArray;
		mButtonScoreChoice = buttonScoreChoice;
		mUsedDiceList = getDiceForMaxScore();

		//todo test code
		System.out.println("mRolledDiceArray: "+diceArrayToString(mRolledDiceArray));
		System.out.println("mUsedDiceList: "+mUsedDiceList.toString());
		System.out.println("mButtonScoreChoice: " + mButtonScoreChoice);
		System.out.println("mSumOfAllRolledDice: " + sumOfAllDiceInArray(mRolledDiceArray));
	}

	/**
	 * The massive method that calculates what dice in mRolledDiceArray can be used to
	 * achieve the maximum possible score for the chosen score button (mButtonScoreChoice).
	 * It starts by calculating if the score choice is 'LOW' and then continues with checking
	 * the lowest amounts of dice that can be used to achieve for one group of the chosen score.
	 *
	 * E.g. if the chosen score is 5 and there is a dice that is 5, that will be used first, then
	 * groups of two e.g. 3 and 2 will be used, then groups of three etc.
	 *
	 * At the start all the rolled dice in the 'mRolledDiceArray' will be put in the local
	 * ArrayList 'notUsedDice', and when a dice is used it's added to the ArrayList 'usedDice'
	 * and removed from 'notUsedDice'.
	 *
	 * @return usedDice, the ArrayList with Dice objects that are used. If the list is empty,
	 * the score for that round will be 0.
	 */
	ArrayList<Dice> getDiceForMaxScore(){
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
		//TODO buggfix (kanske på alla "score")
		/*
		 * I/System.out: TWO: Adding to usedDice: a:6 b:3
		 * I/chatty: uid=10286(com.efraim.activity) identical 1 line
		 * I/System.out: TWO: Adding to usedDice: a:6 b:3
		 * I/System.out: TWO: Adding to usedDice: a:5 b:4
		 *     mRolledDiceArray: [ 6 5 3 4 3 3 ]
		 *     mUsedDiceList: [6, 3, 6, 3, 6, 3, 5, 4]
		 *     mButtonScoreChoice: 9
		 *     mSumOfAllRolledDice: 24
		 *
		 * Den lägger till a+c, a+d, a+e.. använder a flera gånger.
		 */
		for(Dice a : notUsedDice){
			if(usedDice.contains(a))
				continue;
			for(Dice b : notUsedDice){
				if(usedDice.contains(b))
					continue;
				if(a != b && a.getDiceScore() + b.getDiceScore() == mButtonScoreChoice){
					usedDice.add(a);
					usedDice.add(b);
					System.out.println("TWO: Adding to usedDice: a:"+a+" b:"+b);//test
				}
			}
		}
		notUsedDice.removeAll(usedDice);

		if(notUsedDice.isEmpty())
			return usedDice;

		// score with three
		for(Dice a : notUsedDice){
			if(usedDice.contains(a))
				continue;
			for(Dice b : notUsedDice){
				if(usedDice.contains(b))
					continue;
				for(Dice c : notUsedDice){
					if(usedDice.contains(c))
						continue;
					if(a != b && a != c &&
							b != c &&
							a.getDiceScore() + b.getDiceScore() + c.getDiceScore() == mButtonScoreChoice) {
						usedDice.add(a);
						usedDice.add(b);
						usedDice.add(c);
						System.out.println("THREE: Adding to usedDice: a:"+a+" b:"+b+" c:"+c);//test
					}
				}
			}
		}
		notUsedDice.removeAll(usedDice);

		if(notUsedDice.isEmpty())
			return usedDice;

		// score with four
		for(Dice a : notUsedDice){
			if(usedDice.contains(a))
				continue;
			for(Dice b : notUsedDice){
				if(usedDice.contains(b))
					continue;
				for(Dice c : notUsedDice){
						if(usedDice.contains(c))
							continue;
					for(Dice d : notUsedDice) {
						if (usedDice.contains(d))
							continue;
						if (a != b && a != c && a != d &&
							b != c && b != d &&
							c != d &&
							a.getDiceScore() + b.getDiceScore() + c.getDiceScore() + d.getDiceScore() == mButtonScoreChoice
							) {

							usedDice.add(a);
							usedDice.add(b);
							usedDice.add(c);
							usedDice.add(d);
							System.out.println("FOUR: Adding to usedDice: a:"+a+" b:"+b+" c:"+c+" d:"+d);//test
						}
					}
				}
			}
		}
		notUsedDice.removeAll(usedDice);

		if(notUsedDice.isEmpty())
			return usedDice;

		// score with five
		for(Dice a : notUsedDice){
			if(usedDice.contains(a))
				continue;
			for(Dice b : notUsedDice){
				if(usedDice.contains(b))
					continue;
				for(Dice c : notUsedDice){
					if(usedDice.contains(c))
						continue;
					for(Dice d : notUsedDice) {
						if (usedDice.contains(d))
							continue;
						for(Dice e : notUsedDice) {
							if (usedDice.contains(e))
								continue;
							if (a != b && a != c && a != d && a != e &&
								b != c && b != d && b != e &&
								c != d && c != e &&
								d != e &&
								a.getDiceScore() + b.getDiceScore() + c.getDiceScore() + d.getDiceScore() + e.getDiceScore() == mButtonScoreChoice
							) {
								usedDice.add(a);
								usedDice.add(b);
								usedDice.add(c);
								usedDice.add(d);
								usedDice.add(e);
								System.out.println("FIVE: Adding to usedDice: a:" + a + " b:" + b + " c:" + c + " d:" + d+" e:"+e);//test
							}
						}
					}
				}
			}
		}
		notUsedDice.removeAll(usedDice);

		if(notUsedDice.isEmpty())
			return usedDice;

		//score with six
		for(Dice a : notUsedDice){
			if(usedDice.contains(a))
				continue;
			for(Dice b : notUsedDice){
				if(usedDice.contains(b))
					continue;
				for(Dice c : notUsedDice){
					if(usedDice.contains(c))
						continue;
					for(Dice d : notUsedDice) {
						if (usedDice.contains(d))
							continue;
						for(Dice e : notUsedDice) {
							if (usedDice.contains(e))
								continue;
							for(Dice f : notUsedDice) {
								if (usedDice.contains(f))
									continue;

								if (a != b && a != c && a != d && a != e && a != f &&
										b != c && b != d && b != e && b != f &&
										c != d && c != e && c != f &&
										d != e && d != f &&
										e != f &&
										a.getDiceScore() + b.getDiceScore() + c.getDiceScore() + d.getDiceScore() + e.getDiceScore() + f.getDiceScore() == mButtonScoreChoice
								) {
									usedDice.add(a);
									usedDice.add(b);
									usedDice.add(c);
									usedDice.add(d);
									usedDice.add(e);
									usedDice.add(f);

									System.out.println("SIX: Adding to usedDice: a:" + a + " b:" + b + " c:" + c + " d:" + d + " e:" + e + " f:" + f);//test
								}
							}
						}
					}
				}
			}
		}
		notUsedDice.removeAll(usedDice);

		System.out.println("Gått igenom alla test!");//test
		return usedDice;
	}

	/**
	 * TEST
	 * Used for test printouts.
	 * @param diceArray an array of rolled dice that should be calculated.
	 * @return totalDiceScore, the total score of the dice in the parameter array.
	 */
	private int sumOfAllDiceInArray(Dice[] diceArray){
		int totalDiceScore = 0;
		for (Dice d : diceArray) {
			totalDiceScore += d.getDiceScore();
		}
		return totalDiceScore;
	}

	/**
	 * TEST
	 * Used for test printouts
	 * @param diceArray the array of dice that should be printed.
	 * @return String message, the printout like: [ 1 2 3 4 5 6 ]
	 */
	private String diceArrayToString(Dice[] diceArray){
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
