package com.efraim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * This class is used to display the total score of the completed game, and the used choice button and
 * score for each round.
 * @author Efraim Ramsén
 */
public class ScoreActivity extends AppCompatActivity {

	private TextView mScoreForAllRounds;
	private TextView mTotalScoreValue;


	/**
	 * This method is run on the creation of the ScoreActivity and sets the textViews and score text.
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);

		mScoreForAllRounds = findViewById(R.id.round_score_text_view);
		mScoreForAllRounds.setText(printRoundScores());

		mTotalScoreValue = findViewById(R.id.total_score_value);
		mTotalScoreValue.setText(String.valueOf(getTotalScore()));

	}

	/**
	 * This method creates an empty bundle to send with the intent to start a new game when you press
	 * back from the score view.
	 */
	public void onBackPressed() {
		Intent intent = new Intent(this, MainActivity.class);
		Bundle bundle = new Bundle();
		intent.putExtra("bundle",bundle);
		startActivity(intent);
	}

	/**
	 * Gets the total score from the game to display to the user.
	 * @return totalScore, int with the combined score from all the rounds.
	 */
	public int getTotalScore(){
		int totalScore = 0;
		ArrayList<Integer> scoreForRound = this.getIntent().getIntegerArrayListExtra("scoreforround");

		for(Integer i : scoreForRound){
			totalScore += i;
		}

		return totalScore;
	}

	/**
	 * This method prints a text with the used Score Choice Button and the score for each round.
	 * @return text, a String with the text containing information about the score and used button for
	 * each round.
	 */
	public String printRoundScores(){
		String text = "";
		ArrayList<String> choiceButtonNames = this.getIntent().getStringArrayListExtra("scorebuttonnames");
		ArrayList<Integer> scoreForRound = this.getIntent().getIntegerArrayListExtra("scoreforround");

		for(int i = 1; i < 11; i++){
			text += "\n"+"Round "+ i +" - "+ "Score choice: " + choiceButtonNames.get(i-1) + ", Round score: " + scoreForRound.get(i-1);
		}
		return text;
	}
}
