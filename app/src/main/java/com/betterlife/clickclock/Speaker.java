package com.betterlife.clickclock;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import java.util.Locale;

/**
 * Created by iva on 23.12.17.
 */

public class Speaker implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;

    public Speaker(Context context) {
        tts = new TextToSpeech(context, this);
    }

    @Override
    public void onInit(int i) {
        Locale dLocale = new Locale.Builder().setLanguage("ru").setScript("Cyrl").build();
        tts.setLanguage(dLocale);
    }

    public void speak(String text) {
        System.out.println("Speak " + text);
        Bundle bundle = new Bundle();
        bundle.putString(TextToSpeech.Engine.KEY_PARAM_STREAM,
                String.valueOf(AudioManager.STREAM_NOTIFICATION));

        tts.speak(text, TextToSpeech.QUEUE_ADD, bundle, "a");
    }

    public void pause(int duration) {
        tts.playSilence(duration, TextToSpeech.QUEUE_ADD, null);
    }

    public void destroy() {
        tts.shutdown();
    }
}
