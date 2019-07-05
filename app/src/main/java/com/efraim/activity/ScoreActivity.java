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

	private TextView scoreForAllRounds;
	private TextView totalScoreValue;
	private Score score;

	@Override
	protected void onCreate(Bundle savedInstranceState) {
		super.onCreate(savedInstranceState);
		setContentView(R.layout.activity_score);

		//TODO importera Game.getScore()
		//TODO display used dice and score button for each round
		//TODO display total score



	}
}
