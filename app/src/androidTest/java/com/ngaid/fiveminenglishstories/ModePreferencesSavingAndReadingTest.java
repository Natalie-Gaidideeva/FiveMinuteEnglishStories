package com.ngaid.fiveminenglishstories;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ModePreferencesSavingAndReadingTest {

    //tests DefaultSharedPreferences
    @Test
    public void modeChange() throws Exception {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().clear().commit();
        boolean modeIsOn = true;
        PreferencesUtil.setPrefColour(context, modeIsOn);
        assertEquals(modeIsOn, PreferencesUtil.getPrefColour(context));
    }
}
