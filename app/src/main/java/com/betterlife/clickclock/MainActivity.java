package com.betterlife.clickclock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private final Handler mHideHandler = new Handler();
    private TextView mButton;
    private ISpeaker mSpeaker;

    Runnable mShowTime = new Runnable() {
        @Override
        public void run() {
            mButton.setText(new SimpleDateFormat("HH:mm", Locale.US)
                    .format(new Date()));
            mHideHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.button);
        mHideHandler.postDelayed(mShowTime, 10);
        mSpeaker = new RecordingSpeaker();
        mSpeaker.init(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHideHandler.removeCallbacks(mShowTime);
    }

    public void button_clicked(View view) {
        System.out.println("Button clicked ");
        mSpeaker.speak(mButton.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSpeaker.destroy();
    }
}
