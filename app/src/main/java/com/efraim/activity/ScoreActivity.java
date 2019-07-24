package com.efraim.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.efraim.model.Score;

/**
 * //TODO This class will display the score for each round and the total score in activity_score.xml when complete
 * @author Efraim Ramsén
 */
public class ScoreActivity extends AppCompatActivity {

	private TextView mScoreForAllRounds;
	private TextView mTotalScoreValue;
	private Score mScore;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);

		mTotalScoreValue = findViewById(R.id.total_score_value);
		mTotalScoreValue.setText(this.getIntent().getStringExtra("totalscore"));
		//TODO importera Game.getScore() via intent?
		//TODO display used dice and score button for each round
		//TODO display total score



	}
}
