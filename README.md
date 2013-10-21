FlashLight
==========

Demo of flashlight app for Android

Using android.hardware.Camera we can get access to the flash

We first have to check if there is a flash availble, 
then we make the proper calls.

We check it by calling this:
```
getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
```

Once we know that we have a access to the flash, 
we can initilaize the Camera.

```
camera = Camera.open();
params = camera.getParameters();
```

And set flash on.
```
params.setFlashMode(Parameters.FLASH_MODE_TORCH);
camera.setParameters(params);
```

And then we can set the flash off in the same way.

```
params.setFlashMode(Parameters.FLASH_MODE_OFF);
camera.setParameters(params);
```
![solarized vim](https://github.com/Shahar2k5/FlashLight/blob/master/res/drawable-xxhdpi/framed_flash_light_demo.png?raw=true)
