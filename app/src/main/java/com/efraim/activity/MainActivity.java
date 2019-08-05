package com.efraim.activity;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.efraim.model.*;

import java.util.ArrayList;

/**
 * This class handles the buttons and textviews visible on activity_main.xml
 * and starts a new instance of the class Game when created.
 * The buttons have conditions in their onClick() methods depending on
 * what stage game is in.
 * @author Efraim Ramsén
 */
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

	private static final String KEY_MAIN_INSTRUCTIONS = "instructions";
	private static final String KEY_MAIN_ROLL_BUTTON_STATE = "rollbuttonstate";
	private static final String KEY_MAIN_CONFIRM_BUTTON_STATE = "confirmbuttonstate";
	private static final String KEY_SCORE_TOTAL = "totalscore";
	private static final String KEY_SCORE_FOR_EACH_ROUND = "scoreforeachround";
	private static final String KEY_MAIN_CHOICE_BUTTON_SELECTED = "choicebuttonselected";
	private static final String KEY_MAIN_CHOICE_BUTTON_ENABLED = "choicebuttonsenabled";
	private static final String KEY_GAME_USED_CHOICE_BUTTONS = "usedchoicebuttons";
	private static final String KEY_GAME_DICE_ARRAY = "dicearray";
	private static final String KEY_GAME_ROUND = "round";
	private static final String KEY_GAME_THROW = "throw";
	private static final String KEY_GAME_LATEST_SCORE_DICE_LIST = "latestscoredicelist";
	private static final String KEY_GAME_GAME_OVER = "gameover";

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);

		savedInstanceState.putCharSequence(KEY_MAIN_INSTRUCTIONS, mInstructionsTextView.getText());
		savedInstanceState.putBoolean(KEY_MAIN_ROLL_BUTTON_STATE,mRollButton.isEnabled());
		savedInstanceState.putBoolean(KEY_MAIN_CONFIRM_BUTTON_STATE,mConfirmButton.isEnabled());
		savedInstanceState.putParcelableArray(KEY_GAME_DICE_ARRAY, mGame.getDiceArray());//TODO kolla
		savedInstanceState.putInt(KEY_GAME_ROUND, mGame.getRound());
		savedInstanceState.putInt(KEY_GAME_THROW, mGame.getDiceThrow());
		savedInstanceState.putInt(KEY_SCORE_TOTAL, mGame.getScore().getTotalScore());
		savedInstanceState.putIntegerArrayList(KEY_SCORE_FOR_EACH_ROUND, mGame.getScore().getScoreForEachRound());
		savedInstanceState.putParcelableArrayList(KEY_GAME_LATEST_SCORE_DICE_LIST, mGame.getLatestScoreDiceList());
		savedInstanceState.putIntegerArrayList(KEY_GAME_USED_CHOICE_BUTTONS, mGame.getUsedChoiceButtonIDs());
		savedInstanceState.putBoolean(KEY_GAME_GAME_OVER, mGame.getGameOver());

		boolean[] choiceButtonIsSelected = new boolean[mChoiceButtonArray.length];
		boolean[] choiceButtonIsEnabled = new boolean[mChoiceButtonArray.length];

		for(int i = 0; i < mChoiceButtonArray.length; i++){
			choiceButtonIsSelected[i] = mChoiceButtonArray[i].isSelected();
			System.out.println(mChoiceButtonArray[i].isSelected());
			choiceButtonIsEnabled[i] = mChoiceButtonArray[i].isEnabled();
		}
		savedInstanceState.putBooleanArray(KEY_MAIN_CHOICE_BUTTON_SELECTED,choiceButtonIsSelected);
		savedInstanceState.putBooleanArray(KEY_MAIN_CHOICE_BUTTON_ENABLED,choiceButtonIsEnabled);
	}

	/**
	 * Starts a new instance of Game when created and sets the listener for
	 * the buttons and the first text instructions.
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mGame = new Game();

		if(savedInstanceState != null){
			Parcelable[] temp = savedInstanceState.getParcelableArray(KEY_GAME_DICE_ARRAY);
			Dice[] temp2 = new Dice[6];
			for(int i = 0; i < temp.length; i++){
				temp2[i] = (Dice)temp[i];
			}
			mGame.setDiceArray(temp2);

			mGame.setRound(savedInstanceState.getInt(KEY_GAME_ROUND));
			mGame.setDiceThrow(savedInstanceState.getInt(KEY_GAME_THROW));
			mGame.getScore().setTotalScore(savedInstanceState.getInt(KEY_SCORE_TOTAL));
			mGame.getScore().setScoreForEachRound(savedInstanceState.getIntegerArrayList(KEY_SCORE_FOR_EACH_ROUND));
			mGame.setLatestScoreDiceList(savedInstanceState.<Dice>getParcelableArrayList(KEY_GAME_LATEST_SCORE_DICE_LIST));
			mGame.setUsedChoiceButtonIDs(savedInstanceState.getIntegerArrayList(KEY_GAME_USED_CHOICE_BUTTONS));
			mGame.setGameOver(savedInstanceState.getBoolean(KEY_GAME_GAME_OVER));
		}


		mInstructionsTextView = findViewById(R.id.instructions);
		mDiceThrowTextView = findViewById(R.id.diceThrow_value);
		mRoundTextView = findViewById(R.id.round_value);
		mRollButton = findViewById(R.id.roll_button);

		createDiceButtons();
		createConfirmButton();
		createChoiceButtons();

		if(savedInstanceState != null){
			mInstructionsTextView.setText(savedInstanceState.getString(KEY_MAIN_INSTRUCTIONS));

			toggleButtonEnabled(mRollButton,savedInstanceState.getBoolean(KEY_MAIN_ROLL_BUTTON_STATE));
			toggleButtonEnabled(mConfirmButton,savedInstanceState.getBoolean(KEY_MAIN_CONFIRM_BUTTON_STATE));

			updateDiceIcons();
			updateDiceThrowText();
			updateRoundText();


			for(int i = 0; i < mChoiceButtonArray.length; i++){
				mChoiceButtonArray[i].setEnabled(savedInstanceState.getBooleanArray(KEY_MAIN_CHOICE_BUTTON_ENABLED)[i]);
				mChoiceButtonArray[i].setSelected(savedInstanceState.getBooleanArray(KEY_MAIN_CHOICE_BUTTON_SELECTED)[i]);

				if(mChoiceButtonArray[i].isSelected()){
					mChoiceButtonArray[i].setTextColor(getResources().getColor(R.color.colorAccent));
				}
				else if(mChoiceButtonArray[i].isEnabled()){
					mChoiceButtonArray[i].setTextColor(getResources().getColor(R.color.black_text));
				}
			}

		}

		createRollButton();

	}

	/**
	 * Starts the score activity, this is called when the game is over
	 */
	public void startScoreActivity(){
		Intent intent = new Intent(this,ScoreActivity.class);
		intent.putExtra("scorebuttonnames",getUsedChoiceButtonNames());
		intent.putExtra("scoreforround",mGame.getScore().getScoreForEachRound());

		startActivity(intent);
	}

	/**
	 * Create dice buttons and set onClick listener. The dice buttons are
	 * set in an array and used with index (0-5) to reach the individual buttons.
	 */
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

			if(mGame.getDiceThrow() == 0){
				mDiceButtonArray[i].setEnabled(false);
			}
			else{
				mDiceButtonArray[i].setEnabled(true);
			}

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

	/**
	 * Create the roll button and set the onClick listener.
	 */
	public void createRollButton(){

		mRollButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mGame.rollAllDice();
				mGame.setAllDiceState(DICE_STANDARD);
				for(ImageButton ib : mDiceButtonArray){
					ib.setEnabled(true);
				}

				updateDiceIcons();

				if(mGame.getDiceThrow() < 2) {
					toggleButtonEnabled(mConfirmButton,true);
					mInstructionsTextView.setText(R.string.instructions_after_roll);
				}
				else {
					makeScoreButtonChoice();
				}

				mGame.incrementDiceThrow();
				updateDiceThrowText();
				toggleButtonEnabled(mRollButton,false);
			}
		});
	}

	/**
	 * Create the confirm button and the onClick listener. The confirm button
	 * is used to confirm what dice should be re-rolled or what score choice button
	 * should be used to finish the round, so there are conditions to see if there are any
	 * dice with state DICE_OFF that should be re-rolled or if there are any score choice buttons
	 * that are enabled and selected that should be used to finish a round.
	 */
	public void createConfirmButton(){
		mConfirmButton = findViewById(R.id.confirm_button);
		mConfirmButton.setEnabled(false);
		mConfirmButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// confirmButton when no re-roll chosen
				if(mGame.allDiceStandardState()){
					makeScoreButtonChoice();
				}
				// confirmButton when a choiceButton is selected
				else if(mGame.allDiceLockedState()){
					for(Button b : mChoiceButtonArray){
						if(b.isSelected()){
							int i = b.getId();
							mGame.getUsedChoiceButtonIDs().add(i);//ändrar från b till b.getId()
						}
					}
					mGame.finishRound();
					if(mGame.getGameOver()){
						startScoreActivity();
					}
					toggleButtonEnabled(mConfirmButton,false);
					toggleButtonEnabled(mRollButton,true);
					deselectChoiceButtons();
					choiceButtonsActivated(false);
					updateDiceThrowText();
					updateRoundText();
					for(ImageButton b : mDiceButtonArray){
						b.setEnabled(false);
					}
					updateDiceIcons();
					mInstructionsTextView.setText(R.string.instructions_on_new_round);

				}
				else {
					for (int i = 0; i < mDiceButtonArray.length; i++) {
						Dice dice = mGame.getDiceForButton(i);
						if (dice.getDiceState() == DICE_STANDARD) {
							dice.setDiceState(DICE_LOCKED);
						}
						if (dice.getDiceState() == DICE_OFF) {
							mDiceButtonArray[i].setEnabled(false);
						}
					}
					updateDiceIcons();
					toggleButtonEnabled(mConfirmButton,false);
					toggleButtonEnabled(mRollButton,true);
					mInstructionsTextView.setText(R.string.instructions_after_confirm);
				}
			}
		});
	}

	/**
	 * Create the choice buttons in an array and set the onClick listener.
	 */
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
					choiceButtonsActivated(true);
					deselectChoiceButtons();
					setChoiceButtonSelected(index, !mChoiceButtonArray[index].isSelected());
					mGame.diceScoreCalculation(index+3, mGame.getDiceArray());
					int scoreForChoiceButton = mGame.getDiceListScore(mGame.getLatestScoreDiceList());
					setChoiceButtonInstructionText(index,scoreForChoiceButton);
					toggleButtonEnabled(mConfirmButton, true);
				}
			});
		}
		choiceButtonsActivated(false);
	}

	/**
	 * Sets a button to enabled or not enabled and changes the button text color
	 * @param button, Button object that is being set
	 * @param enabled, boolean true or false
	 */
	public void toggleButtonEnabled(Button button, boolean enabled){
		button.setEnabled(enabled);
		if(enabled){
			button.setTextColor(getResources().getColor(R.color.colorAccent));

		}
		else {
			button.setTextColor(getResources().getColor(R.color.grey_text));
		}
	}

	/**
	 * This method is run when the player is finished rolling and re-rolling the dice
	 * to activate the score choice buttons for being pressed and deactivate the confirm
	 * button before a choice button is pressed. All the dice are being locked.
	 */
	public void makeScoreButtonChoice(){
		mInstructionsTextView.setText(R.string.instructions_on_choice_buttons);
		choiceButtonsActivated(true);
		toggleButtonEnabled(mConfirmButton,false);
		mGame.setAllDiceState(DICE_LOCKED);
		updateDiceIcons();
	}

	/**
	 * This sets a slightly different text for each score choice button that is pressed,
	 * just for fun :)
	 * @param buttonIndex, the score button that is being pressed
	 * @param scoreForIndex, the score that using the chosen score button will give
	 */
	public void setChoiceButtonInstructionText(int buttonIndex, int scoreForIndex){
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

	/**
	 * Updates the images for all the dice in the dice array depending on the
	 * score and state of each dice.
	 */
	public void updateDiceIcons(){
		for(int i = 0; i < mDiceButtonArray.length; i++){
			Dice dice = mGame.getDiceForButton(i);
			mDiceButtonArray[i].setBackgroundResource(dice.setDiceImage(dice.getDiceScore(),dice.getDiceState())
			);
		}
	}

	/**
	 * Updates the visible dice throw number (0-3)
	 */
	public void updateDiceThrowText(){
		String diceThrowText = mGame.getDiceThrow()+"/3";
		mDiceThrowTextView.setText(diceThrowText);
	}

	/**
	 * Updates the visible round number (1-10)
	 */
	public void updateRoundText(){
		String roundText = mGame.getRound()+"/10";
		mRoundTextView.setText(roundText);
	}

	/**
	 * Set all score choice buttons to not be selected,
	 * used before marking a clicked button as selected as
	 * only one should be selected at a time
	 */
	public void deselectChoiceButtons(){
		for(Button b :mChoiceButtonArray) {
			b.setSelected(false);
		}
	}

	/**
	 * Set all score choice buttons to enabled or not enabled and change
	 * the button text color accordingly
	 * @param activate, boolean true to enable or false to not enable
	 */
	public void choiceButtonsActivated(boolean activate){
		for(Button b :mChoiceButtonArray){
			if(activate && !mGame.choiceButtonIsUsed(b)){
				b.setEnabled(true);
				b.setTextColor(getResources().getColor(R.color.black_text));
			}
			else{
				b.setEnabled(false);
				b.setTextColor(getResources().getColor(R.color.grey_text));
			}
		}
	}

	/**
	 * Set if a score choice button is selected
	 * @param i, the index of the button to be set in the mChoiceButtonArray
	 * @param bool, true = selected, false = not selected
	 */
	public void setChoiceButtonSelected(int i, boolean bool){
		if(i < 0 || i > 9) {
			System.out.println("Index out of bounds for mChoiceButtonArray");
			return;
		}
		mChoiceButtonArray[i].setSelected(bool);
		if(!bool) {
			mChoiceButtonArray[i].setTextColor(getResources().getColor(R.color.black_text));
		}else{
			mChoiceButtonArray[i].setTextColor(getResources().getColor(R.color.colorAccent));

		}
	}

	/**
	 * This method is used to save the name of the Choice Button used for each round so it can be
	 * displayed in the Score activity after the game is over.
	 * @return usedChoiceButtonNames, an ArrayList of Strings ("LOW","4","5" etc)
	 */
	public ArrayList<String> getUsedChoiceButtonNames(){
		ArrayList<String> usedChoiceButtonNames = new ArrayList<>();
		for(Integer i : mGame.getUsedChoiceButtonIDs()){
			for(Button b : mChoiceButtonArray)
				if(i == b.getId()){
					usedChoiceButtonNames.add((String)b.getText());
				}
		}

		return usedChoiceButtonNames;
	}
}
