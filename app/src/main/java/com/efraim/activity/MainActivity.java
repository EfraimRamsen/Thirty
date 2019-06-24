package com.efraim.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.efraim.model.Dice;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

	private ImageButton mDice1Button, mDice2Button, mDice3Button, mDice4Button, mDice5Button, mDice6Button;
	private Button mRollButton;
	private Button mConfirmButton;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		mConfirmButton = findViewById(R.id.confirm_button);
		mConfirmButton.setActivated(false);

		mRollButton = findViewById(R.id.roll_button);
		mRollButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				rollAllDice();
				mConfirmButton.setActivated(true);
				mConfirmButton.setTextColor(getResources().getColor(R.color.colorAccent));
				mRollButton.setActivated(false);
				mRollButton.setTextColor(getResources().getColor(R.color.grey_text));
			}
		});

		mConfirmButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

	}

	/**
	 * Rolls all the dice and creates a new Dice-object for each with
	 * a random number between 1-6 from the randomD6() method.
	 * diceState is always set to 1 = STANDARD when rolling all the dice.
	 */
	public void rollAllDice() {
		Dice dice1 = new Dice(randomD6(), 1);
		mDice1Button = findViewById(R.id.dice1_button);
		mDice1Button.setBackgroundResource(dice1.getImageResId());

		Dice dice2 = new Dice(randomD6(), 1);
		mDice2Button = findViewById(R.id.dice2_button);
		mDice2Button.setBackgroundResource(dice2.getImageResId());

		Dice dice3 = new Dice(randomD6(), 1);
		mDice3Button = findViewById(R.id.dice3_button);
		mDice3Button.setBackgroundResource(dice3.getImageResId());

		Dice dice4 = new Dice(randomD6(), 1);
		mDice4Button = findViewById(R.id.dice4_button);
		mDice4Button.setBackgroundResource(dice4.getImageResId());

		Dice dice5 = new Dice(randomD6(), 1);
		mDice5Button = findViewById(R.id.dice5_button);
		mDice5Button.setBackgroundResource(dice5.getImageResId());

		Dice dice6 = new Dice(randomD6(), 1);
		mDice6Button = findViewById(R.id.dice6_button);
		mDice6Button.setBackgroundResource(dice6.getImageResId());
	}

}