package com.efraim.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DiceScoreCalculation {
	private int mButtonScoreChoice;
	private Dice[] mRolledDiceArray;
	private ArrayList<Dice> mUsedDiceList;


	public DiceScoreCalculation(int buttonScoreChoice, Game game){
		mRolledDiceArray = game.getDiceArray();
		mButtonScoreChoice = buttonScoreChoice;
		mUsedDiceList = getDiceForMaxScore();

		//todo test code
		System.out.println("mRolledDiceArray: "+diceArrayToString(mRolledDiceArray));
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
