package com.betterlife.clickclock;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * A speaker that plays recordings embedded as raw resources.
 */

public class RecordingSpeaker implements ISpeaker {
    private Context context;
    private int[] hourIds;
    private int[] minuteIds;
    private int to;
    private int past;

    @Override
    public void init(Context context) {
        this.context = context;
        hourIds = new int[13];
        minuteIds = new int[31];
        hourIds[0] = R.raw.all12;
        hourIds[1] = R.raw.chas1;
        hourIds[2] = R.raw.chas2;
        minuteIds[1] = R.raw.minuti1;
        minuteIds[2] = R.raw.minuti2;
        hourIds[3] = minuteIds[3] = R.raw.all3;
        hourIds[4] = minuteIds[4] = R.raw.all4;
        hourIds[5] = minuteIds[5] = R.raw.all5;
        hourIds[6] = minuteIds[6] = R.raw.all6;
        hourIds[7] = minuteIds[7] = R.raw.all7;
        hourIds[8] = minuteIds[8] = R.raw.all8;
        hourIds[9] = minuteIds[9] = R.raw.all9;
        hourIds[10] = minuteIds[10] = R.raw.all10;
        hourIds[11] = minuteIds[11] = R.raw.all11;
        hourIds[12] = minuteIds[12] = R.raw.all12;
        minuteIds[13] = R.raw.minuti13;
        minuteIds[14] = R.raw.minuti14;
        minuteIds[15] = R.raw.minuti15;
        minuteIds[16] = R.raw.minuti16;
        minuteIds[17] = R.raw.minuti17;
        minuteIds[18] = R.raw.minuti18;
        minuteIds[19] = R.raw.minuti19;
        minuteIds[20] = R.raw.minuti20;
        minuteIds[21] = R.raw.minuti21;
        minuteIds[22] = R.raw.minuti22;
        minuteIds[23] = R.raw.minuti23;
        minuteIds[24] = R.raw.minuti24;
        minuteIds[25] = R.raw.minuti25;
        minuteIds[26] = R.raw.minuti26;
        minuteIds[27] = R.raw.minuti27;
        minuteIds[28] = R.raw.minuti28;
        minuteIds[29] = R.raw.minuti29;
        minuteIds[30] = R.raw.minuti30;
        to = R.raw.to;
        past = R.raw.past;
    }

    @Override
    public void speak(String text) {
        String[] times = text.split(":");
        int hour = Integer.parseInt(times[0]);
        int minutes = Integer.parseInt(times[1]);
        hour %= 12;
        if (minutes > 30) {
            hour++;
        }
        MediaPlayer hourPlayer = MediaPlayer.create(context, hourIds[hour]);
        MediaPlayer prepositionPlayer = null;
        MediaPlayer minutePlayer = null;
        if (minutes > 30) {
            prepositionPlayer = MediaPlayer.create(context, to);
            minutePlayer = MediaPlayer.create(context, minuteIds[60 - minutes]);
        } else if (minutes != 0) {
            prepositionPlayer = MediaPlayer.create(context, past);
            minutePlayer = MediaPlayer.create(context, minuteIds[minutes]);
        }
        if (prepositionPlayer != null && minutePlayer != null) {
            hourPlayer.setNextMediaPlayer(prepositionPlayer);
            prepositionPlayer.setNextMediaPlayer(minutePlayer);
        }
        hourPlayer.start();
    }

    @Override
    public void destroy() {
    }
}
