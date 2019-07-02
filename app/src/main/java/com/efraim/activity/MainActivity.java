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
	private TextView mDiceThrowTextView;
	private TextView mRoundTextView;

	private static final int DICE_OFF = 0;
	private static final int DICE_STANDARD = 1;
	private static final int DICE_LOCKED = 2;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mGame = new Game();

		mInstructionsTextView = findViewById(R.id.instructions);
		mDiceThrowTextView = findViewById(R.id.diceThrow_value);
		mRoundTextView = findViewById(R.id.round_value);

		createDiceButtons();
		createRollButton();
		createConfirmButton();
		createChoiceButtons();
	}

	public void createDiceButtons(){
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
	}

	public void createRollButton(){
		mRollButton = findViewById(R.id.roll_button);
		mRollButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mGame.rollAllDice();
				mGame.setAllDiceState(DICE_STANDARD);
				updateDiceIcons();
				updateDiceThrowText();

				if(mGame.getDiceThrow() >= 3) {
					makeScoreButtonChoice();
				}
				else {
					mGame.incrementDiceThrow();
					mConfirmButton.setEnabled(true);
					mConfirmButton.setTextColor(getResources().getColor(R.color.colorAccent));
					mInstructionsTextView.setText(R.string.instructions_after_roll);
				}
				mRollButton.setEnabled(false);
				mRollButton.setTextColor(getResources().getColor(R.color.grey_text));
			}
		});
	}

	public void createConfirmButton(){
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
	}

	public void createChoiceButtons(){
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

		for(int i = 0; i < mChoiceButtonArray.length; i++){
			final int index =i;
			mChoiceButtonArray[i].setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//todo lyssnare för varje choice-knapp
					choiceButtonsActivated(true);
					deselectChoiceButtons();
					changeChoiceButton(index, !mChoiceButtonArray[index].isSelected());
					DiceScoreCalculation diceScoreCalculation = new DiceScoreCalculation(index+3, mGame);
					choiceButtonInstructionText(index,diceScoreCalculation.getUsedDiceListScore());
				}
			});
		}
		choiceButtonsActivated(false);
	}

	public void makeScoreButtonChoice(){
		mInstructionsTextView.setText(R.string.instructions_on_choice_buttons);
		choiceButtonsActivated(true);
		mGame.setAllDiceState(DICE_LOCKED);
	}

	public void choiceButtonInstructionText(int buttonIndex, int scoreForIndex){
		String instructions = "";
		switch (buttonIndex) {
			case 0:
				instructions = "LOW will scrape up the bottom numbers (1-3) and combine them to " +
						"give you " + scoreForIndex + " points!";
				break;
			case 1:
				instructions = "4 will collect dice in groups of four and hand over "+scoreForIndex+" points!";
				break;
			case 2:
				instructions = "5 will collect dice in groups of five and yield "+scoreForIndex+" points!";
				break;
			case 3:
				instructions = "6 will collect dice in groups of six and cash in "+scoreForIndex+" points!";
				break;
			case 4:
				instructions = "7 will collect dice in groups of seven and get you "+scoreForIndex+" points!";
				break;
			case 5:
				instructions = "8 will collect dice in groups of eight and present you "+scoreForIndex+" points!";
				break;
			case 6:
				instructions = "9 will collect dice in groups of nine and throw you "+scoreForIndex+" points!";
				break;
			case 7:
				instructions = "10 will collect dice in groups of ten and drop you "+scoreForIndex+" points!";
				break;
			case 8:
				instructions = "11 will collect dice in groups of eleven and let you acquire "+scoreForIndex+" points!";
				break;
			case 9:
				instructions = "12 will collect dice in groups of twelve and yeet ya' "+scoreForIndex+" points!";
				break;
			default:
				System.out.println("Index needs to be 0-9");
				break;
		}
		mInstructionsTextView.setText(instructions);
	}

	public void updateDiceIcons(){

		for(int i = 0; i < mDiceButtonArray.length; i++){
			Dice dice = mGame.getDiceForButton(i);
			mDiceButtonArray[i].setBackgroundResource(dice.setDiceImage(dice.getDiceScore(),dice.getDiceState())
			);
		}
	}

	public void updateDiceThrowText(){
		String diceThrowText = mGame.getDiceThrow()+"/3";
		mDiceThrowTextView.setText(diceThrowText);
	}

	public void deselectChoiceButtons(){
		for(Button b :mChoiceButtonArray) {
			b.setSelected(false);
		}
	}

	public void choiceButtonsActivated(boolean bool){
		for(Button b :mChoiceButtonArray){
			b.setEnabled(bool);
			if(bool){
				b.setTextColor(getResources().getColor(R.color.black_text));
			}
			else{
				b.setTextColor(getResources().getColor(R.color.grey_text));
			}
		}
	}

	public void changeChoiceButton(int i, boolean activate){
		if(i < 0 || i > 9) {
			System.out.println("Index out of bounds for mChoiceButtonArray");
			return;
		}
//		mChoiceButtonArray[i].setEnabled(activate);
		mChoiceButtonArray[i].setSelected(activate);
		if(!activate) {
			mChoiceButtonArray[i].setTextColor(getResources().getColor(R.color.black_text));
		}else{
			mChoiceButtonArray[i].setTextColor(getResources().getColor(R.color.colorAccent));

		}
	}

}
