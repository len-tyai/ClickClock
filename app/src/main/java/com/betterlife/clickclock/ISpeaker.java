package com.betterlife.clickclock;

import android.content.Context;

/**
 * An interface for Speaker services.
 */

public interface ISpeaker {

    void init(Context context);

    void speak(String text);

    void destroy();
}
