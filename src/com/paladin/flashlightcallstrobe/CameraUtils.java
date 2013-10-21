package com.paladin.flashlightcallstrobe;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;

public class CameraUtils {

	public static final String TAG = "CameraUtils";
	public static Camera camera;
	public static boolean isFlashOn;
	public static boolean hasFlash;
	public static Parameters params;

	/**
	 * Check if this device is supporting flashlight or not
	 * 
	 * @param context
	 *            to access the Android resources
	 * @return true if the device has flash support.
	 */
	public static boolean deviceHasFlash(Context context) {

		hasFlash = context.getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA_FLASH);
		if (!hasFlash) {
			Log.d(TAG, "Device does not has flash");
			return false;
		}
		Log.d(TAG, "Device has flash");

		return true;
	}

	/**
	 * init the camera and parameters
	 * 
	 * @return true if the initialize was ok.
	 */
	public static boolean getCamera() {
		try {
			camera = null;
			camera = Camera.open();
			params = camera.getParameters();
			return true;
		} catch (Exception exception) {
			Log.e("Camera Error. Failed to Open. Error: ",
					exception.getLocalizedMessage());
			return false;
		}
	}

	/**
	 * Turn On flash  
	 */
	public static void turnOnFlash() {
		if (!isFlashOn) {
			if (camera == null || params == null) {
				return;
			}
			params.setFlashMode(Parameters.FLASH_MODE_TORCH);
			camera.setParameters(params);
			isFlashOn = true;
		}
	}

	/**
	 * Turn off flash
	 */
	public static void turnOffFlash() {
		if (isFlashOn) {
			if (camera == null || params == null) {
				return;
			}
			params.setFlashMode(Parameters.FLASH_MODE_OFF);
			camera.setParameters(params);
			isFlashOn = false;
		}
	}

	/**
	 * Release the camera
	 */
	public static void releaseCamera() {
		// release the camera
		if (camera != null) {
			camera.release();
			camera = null;
		}
	}

}
