package com.efraim.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.efraim.model.Game;
import com.efraim.activity.MainActivity;

public class ScoreActivity extends AppCompatActivity {

	private TextView scoreForAllRounds;
	private TextView totalScoreValue;
	private Game mGame;

	@Override
	protected void onCreate(Bundle savedInstranceState) {
		super.onCreate(savedInstranceState);
		setContentView(R.layout.activity_score);

		//TODO skapa loop som skriver ut nummer på runda, vald knapp och poäng för tärningar
		//TODO importera game eller skicka med ett intent?



	}
}
