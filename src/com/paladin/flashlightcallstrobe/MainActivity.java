package com.paladin.flashlightcallstrobe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener {

	private ImageButton switchButton;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// load the view from the xml
		setContentView(R.layout.activity_main);

		context = this;
		// link our object to the xml
		switchButton = (ImageButton) findViewById(R.id.switchButton);

		// listen to user clicks on our image button
		switchButton.setOnClickListener(this);

		/*
		 * because not all the devices have a flash, we need to check if there a
		 * flash support and notify the user about it.
		 */
		if (!CameraUtils.deviceHasFlash(context)) {
			// the user doesn't have flash support!
			// create an Alert Dialog and show it to the user.

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);

			// set title for our dialog
			alertDialogBuilder.setTitle(getString(R.string.dialog_title));

			// set dialog message
			alertDialogBuilder.setMessage(getString(R.string.dialog_body));
			// add a button to dismiss the dialog
			alertDialogBuilder.setPositiveButton(getString(R.string.dismiss),
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// when this button is clicked, close
							// current activity, or
							finish();
						}
					});
			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();
			// show it to the user
			alertDialog.show();

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu;
		// this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// here we decide what to do when the user clicks the button
		// turn the flash on or off
		if (CameraUtils.isFlashOn) {
			// turn off flash
			turnOffFlash();
		} else {
			// turn on flash
			turnOnFlash();
		}
	}

	/**
	 * Turn On flash  
	 */
	private void turnOnFlash() {
		// turn the flash on
		CameraUtils.turnOnFlash();
		// change the image on the button
		toggleButtonImage();
	}

	/**
	 * Turn Off flash
	 */
	private void turnOffFlash() {
		// turn the flash off
		CameraUtils.turnOffFlash();
		// change the image on the button
		toggleButtonImage();
	}

	/**
	 * set the image of the button according to the flash state
	 */
	private void toggleButtonImage() {
		// change the image on the button according to the
		// new state of the flash light
		if (CameraUtils.isFlashOn) {
			switchButton.setImageResource(R.drawable.ic_button_on);
		} else {
			switchButton.setImageResource(R.drawable.ic_button_off);
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		// when the app starts,
		// init the camera and the camera parameters.
		CameraUtils.getCamera();
	}

	@Override
	protected void onStop() {
		super.onStop();
		// when the app stops,
		// release the camera
		CameraUtils.releaseCamera();
	}

}
