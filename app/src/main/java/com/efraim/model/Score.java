package com.efraim.model;

import java.util.ArrayList;

public class Score {
	private int mTotalScore = 0;
	private ArrayList<ArrayList<Dice>> mDiceForEachRound = new ArrayList<>();

	public void addDiceList(ArrayList<Dice> diceList){
		mDiceForEachRound.add(diceList);
		for (ArrayList<Dice> list : mDiceForEachRound){
			for(Dice d : list){
				mTotalScore += d.getDiceScore();
			}
		}
	}

	public int getTotalScore() {
		return mTotalScore;
	}

	public void setTotalScore(int totalScore) {
		this.mTotalScore = totalScore;
	}

	public ArrayList<ArrayList<Dice>> getDiceForEachRound() {
		return mDiceForEachRound;
	}

	public void setDiceForEachRound(ArrayList<ArrayList<Dice>> mDiceForEachRound) {
		this.mDiceForEachRound = mDiceForEachRound;
	}
}
