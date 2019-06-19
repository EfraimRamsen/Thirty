package com.efraim.thirty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

	private ImageButton mDice1Button, mDice2Button, mDice3Button, mDice4Button, mDice5Button, mDice6Button;
	private Button mRollButton, mConfirmButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mDice1Button = findViewById(R.id.dice1_button);
		mDice1Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new Dice()
			}
		});
	}
}
