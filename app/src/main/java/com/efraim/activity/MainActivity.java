package com.efraim.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.efraim.model.*;

public class MainActivity extends AppCompatActivity {

	private Game mGame;
	private TextView mInstructionsTextView;
	private ImageButton[] mDiceButtonArray;
	private Button mRollButton;
	private Button mConfirmButton;

	private static final int DICE_OFF = 0;
	private static final int DICE_STANDARD = 1;
	private static final int DICE_LOCKED = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mGame = new Game();
		mInstructionsTextView = findViewById(R.id.instructions);
		mDiceButtonArray  =
				new ImageButton[]{  findViewById(R.id.dice1_button),
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
				mGame.rollAllDice();
				updateDiceIcons();

				mConfirmButton.setEnabled(true);
				mConfirmButton.setTextColor(getResources().getColor(R.color.colorAccent));

				mRollButton.setEnabled(false);
				mRollButton.setTextColor(getResources().getColor(R.color.grey_text));

				mInstructionsTextView.setText(R.string.instructions_after_roll);
			}
		});



		mConfirmButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				for(int i = 0; i < mDiceButtonArray.length; i++){
//					Dice dice = mGame.getDiceForButton(i);
//					mDiceButtonArray[i].setBackgroundResource(dice.setDiceImage(dice.getDiceScore(),dice.getDiceState())
//					);
					Dice dice = mGame.getDiceForButton(i);
					if(dice.getDiceState() == DICE_STANDARD){
						dice.setDiceState(DICE_LOCKED);
						updateDiceIcons();
					}
					mConfirmButton.setEnabled(false);
					mConfirmButton.setTextColor(getResources().getColor(R.color.grey_text));

					mRollButton.setEnabled(true);
					mRollButton.setTextColor(getResources().getColor(R.color.colorAccent));

					mInstructionsTextView.setText(R.string.instructions_after_confirm);
				}
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
