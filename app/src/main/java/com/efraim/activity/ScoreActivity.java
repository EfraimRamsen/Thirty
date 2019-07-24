package com.efraim.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.efraim.model.Score;

import java.util.ArrayList;

/**
 * //TODO This class will display the score for each round and the total score in activity_score.xml when complete
 * @author Efraim Ramsén
 */
public class ScoreActivity extends AppCompatActivity {

	private TextView mScoreForAllRounds;
	private TextView mTotalScoreValue;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);

		mScoreForAllRounds = findViewById(R.id.round_score_text_view);
		mScoreForAllRounds.setText(printRoundScores());

		mTotalScoreValue = findViewById(R.id.total_score_value);
		mTotalScoreValue.setText(String.valueOf(getTotalScore()));
		//TODO importera Game.getScore() via intent?
		//TODO display used dice and score button for each round
		//TODO display total score

	}

	public int getTotalScore(){
		int totalScore = 0;
		ArrayList<Integer> scoreForRound = this.getIntent().getIntegerArrayListExtra("scoreforround");

		for(Integer i : scoreForRound){
			totalScore += i;
		}

		return totalScore;
	}

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
