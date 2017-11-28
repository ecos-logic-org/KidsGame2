package org.ecos.logic.kidsgame2.tests.howtos.android.services.speech;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.ecos.logic.android.core.services.resolvers.ApplicationContextResolver;
import org.ecos.logic.android.core.services.resolvers.ApplicationContextResolverImpl;
import org.ecos.logic.android.services.speech.TTSSpeechEngine;
import org.ecos.logic.services.interfaces.exceptions.NotInitialized;
import org.ecos.logic.services.interfaces.speech.SpeechEngine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TextToSpeechServiceTests {
    private SpeechEngine mSpeechEngine;
    private ApplicationContextResolver mApplicationContextResolver;

    @Before
    public void setUp() throws Exception {
        Context applicationContext = InstrumentationRegistry.getTargetContext();
        mApplicationContextResolver = new ApplicationContextResolverImpl(applicationContext);
        mSpeechEngine = new TTSSpeechEngine(mApplicationContextResolver);
    }

    @Test
    public void howTo() throws NotInitialized, InterruptedException {
        mSpeechEngine.speak("");
        Thread.sleep(1500);
        mSpeechEngine.speak("ca");
        Thread.sleep(1500);
        mSpeechEngine.speak("ce");
        Thread.sleep(1500);
        mSpeechEngine.speak("ci");
        Thread.sleep(1500);
        mSpeechEngine.speak("co");
        Thread.sleep(1500);
        mSpeechEngine.speak("cu");
        Thread.sleep(1500);
    }

    @Test
    public void howToEvents() throws NotInitialized, InterruptedException {
        mSpeechEngine.speak("");
        Thread.sleep(1500);
        mSpeechEngine.speak("ca",this::ce);
        Thread.sleep(1500+1500+1500+1500+1500+1500+1500);
    }

    private void ce() throws NotInitialized {
        reInit();

        mSpeechEngine.speak("ce",this::ci);
    }

    private void reInit(){
        mSpeechEngine = new TTSSpeechEngine(mApplicationContextResolver);
        try {
            mSpeechEngine.speak("");
            Thread.sleep(1500);
        } catch (NotInitialized|InterruptedException  throwable) {
            throwable.printStackTrace();
        }
    }

    private void ci() throws NotInitialized {
        reInit();

        mSpeechEngine.speak("ci",this::co);
    }

    private void co() throws NotInitialized {
        reInit();

        mSpeechEngine.speak("co",this::cu);
    }

    private void cu() throws NotInitialized {
        reInit();

        mSpeechEngine.speak("cu");
    }
}