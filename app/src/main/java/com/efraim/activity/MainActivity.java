package com.efraim.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.efraim.model.*;

public class MainActivity extends AppCompatActivity {

	private ImageButton[] mDiceButtonArray;
	private Button mRollButton;
	private Button mConfirmButton;
	private Game mGame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mGame = new Game();

		mDiceButtonArray  =
				new ImageButton[]{findViewById(R.id.dice1_button),
						findViewById(R.id.dice2_button),
						findViewById(R.id.dice3_button),
						findViewById(R.id.dice4_button),
						findViewById(R.id.dice5_button),
						findViewById(R.id.dice6_button),
				};

		mConfirmButton = findViewById(R.id.confirm_button);
		mRollButton = findViewById(R.id.roll_button);
		mConfirmButton.setEnabled(false);

		mRollButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println(mGame.mDiceArrayToString());
				mGame.rollAllDice(); // funkar
				System.out.println(mGame.mDiceArrayToString());
				updateDiceIcons(); //todo funkar inte?

				mConfirmButton.setEnabled(true);
				mConfirmButton.setTextColor(getResources().getColor(R.color.colorAccent));

				mRollButton.setEnabled(false);
				mRollButton.setTextColor(getResources().getColor(R.color.grey_text));
			}
		});

		mConfirmButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}

	public void updateDiceIcons(){
		for(int i = 0; i < mDiceButtonArray.length; i++){
			Dice dice = mGame.getDiceForButton(i);
			mDiceButtonArray[i].setBackgroundResource(dice.setDiceImage(dice.getDiceScore(),dice.getDiceState())
			);
		}
	}
}
