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
	private Button[] mChoiceButtonArray;

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
		for(int i = 0; i < mDiceButtonArray.length; i++){
			final Dice dice = mGame.getDiceForButton(i);
			mDiceButtonArray[i].setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					switch (dice.getDiceState()) {
						case DICE_STANDARD: dice.setDiceState(DICE_OFF);
						break;
						case DICE_OFF: dice.setDiceState(DICE_STANDARD);
						break;
				}
				updateDiceIcons();
				}
			});
		}

		mRollButton = findViewById(R.id.roll_button);
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

		mConfirmButton = findViewById(R.id.confirm_button);
		mConfirmButton.setEnabled(false);
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

		mChoiceButtonArray = new Button[]{  findViewById(R.id.choiceLow_button),
											findViewById(R.id.choice4_button),
											findViewById(R.id.choice5_button),
											findViewById(R.id.choice6_button),
											findViewById(R.id.choice7_button),
											findViewById(R.id.choice8_button),
											findViewById(R.id.choice9_button),
											findViewById(R.id.choice10_button),
											findViewById(R.id.choice11_button),
											findViewById(R.id.choice12_button),
		};
		choiceButtonsActivated(false);
		for(int i = 0; i < mChoiceButtonArray.length; i++){
			final int index =i;
			mChoiceButtonArray[i].setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//todo lyssnare för varje choice-knapp
					changeChoiceButton(index, !mChoiceButtonArray[index].isSelected());
					new DiceScoreCalculation(index+3, mGame);
				}
			});
		}
	}

	public void choiceButtonsActivated(boolean bool){
		for(Button b :mChoiceButtonArray){
			b.setActivated(bool);
			if(bool){
				b.setTextColor(getResources().getColor(R.color.grey_text));
			}
			else{
				b.setTextColor(getResources().getColor(R.color.black_text));
			}
		}
	}

	public void changeChoiceButton(int i, boolean activate){
		if(i < 0 || i > 9) {
			System.out.println("Index out of bounds for mChoiceButtonArray"); //todo LOG message istället?
			return;
		}
//		mChoiceButtonArray[i].setEnabled(activate);
		mChoiceButtonArray[i].setSelected(activate);
		if(!activate) {
			mChoiceButtonArray[i].setTextColor(getResources().getColor(R.color.grey_text));
		}else{
			mChoiceButtonArray[i].setTextColor(getResources().getColor(R.color.colorAccent));

		}
	}

	public void updateDiceIcons(){
		for(int i = 0; i < mDiceButtonArray.length; i++){
			Dice dice = mGame.getDiceForButton(i);
			mDiceButtonArray[i].setBackgroundResource(dice.setDiceImage(dice.getDiceScore(),dice.getDiceState())
			);
		}
	}
}
