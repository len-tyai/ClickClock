package com.betterlife.clickclock;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * A speaker that uses TextToSpeech API.
 */

public class TextToSpeechSpeaker implements TextToSpeech.OnInitListener, ISpeaker {

    private TextToSpeech tts;

    @Override
    public void init(Context context){
        tts = new TextToSpeech(context, this);
    }

    @Override
    public void onInit(int i) {
        Locale dLocale = new Locale.Builder().setLanguage("ru").setScript("Cyrl").build();
        tts.setLanguage(dLocale);
    }

    @Override
    public void speak(String text) {
        System.out.println("Speak " + text);
        Bundle bundle = new Bundle();
        bundle.putString(TextToSpeech.Engine.KEY_PARAM_STREAM,
                String.valueOf(AudioManager.STREAM_NOTIFICATION));

        tts.speak(text, TextToSpeech.QUEUE_ADD, bundle, "a");
    }

    @Override
    public void destroy() {
        tts.shutdown();
    }
}
