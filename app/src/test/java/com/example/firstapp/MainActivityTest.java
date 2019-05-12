package com.example.firstapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    @Test
    public void testSwitchText() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        activity.btn.performClick();
        Assert.assertEquals("TextView text not right","Switch to text1",activity.text1.getText());
    }

    @Test
    public void testSwitchActivity() {
        Intent intent = new Intent();
        ArrayList<String> ls = new ArrayList<String>();
        ls.add("Hello");
        ls.add("World");
        intent.putStringArrayListExtra("ls",ls);
        SecondActivity activity = Robolectric.buildActivity(SecondActivity.class,intent).create().start().resume().get();
        Assert.assertEquals("Message of tv1 not correct!", "HelloWorld",activity.tv1.getText());
    }

    private  void sleep(double secs) {
        try{
            Thread.sleep((int)secs* 1000);
        }catch (Exception ex) {

        }
    }
    @Test
    public void testLoadImage() {
        ThirdActivity activity = Robolectric.setupActivity(ThirdActivity.class);
        activity.btnDown.performClick();
        int tryCount = 0;
        while(activity.rawBitmap == null && tryCount <20) {
            sleep(0.1);
            tryCount ++;
        }

        Assert.assertTrue("Load Bitmap failed", activity.rawBitmap != null);

        Bitmap bitmap = ((BitmapDrawable)activity.ivImage.getDrawable()).getBitmap();
        Assert.assertTrue("set Bitmap to Ui failed",bitmap.equals(activity.rawBitmap));

    }


    @Test
    public void testLoadImage1() {
        ThirdActivity activity = Robolectric.setupActivity(ThirdActivity.class);
        activity.loadData();
        int tryCount = 0;
        while(activity.rawBitmap == null && tryCount <20) {
            sleep(0.1);
            tryCount ++;
        }

        Assert.assertTrue("Load Bitmap failed", activity.rawBitmap != null);

        Bitmap bitmap = ((BitmapDrawable)activity.ivImage.getDrawable()).getBitmap();
        Assert.assertTrue("set Bitmap to Ui failed",bitmap.equals(activity.rawBitmap));

    }

    @Test
    public void testDismissDialog() {
        ThirdActivity activity = Robolectric.setupActivity(ThirdActivity.class);

        //Test Dialog Show
        activity.dialog.show();
        Assert.assertTrue("Progress dialog is showing",activity.dialog.isShowing());
        activity.dialog.dismiss();
        Assert.assertTrue("Progress dialog dismissed",!activity.dialog.isShowing());

    }
}
