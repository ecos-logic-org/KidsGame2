package org.ecos.logic.kidsgame2.tests.howtos.android.services.media.audio.speech;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.ecos.logic.android.core.services.resolvers.ApplicationContextResolver;
import org.ecos.logic.android.core.services.resolvers.ApplicationContextResolverImpl;
import org.ecos.logic.android.core.services.resolvers.ContextResolver;
import org.ecos.logic.android.core.services.resolvers.ContextResolverImpl;
import org.ecos.logic.android.core.services.resolvers.ResIdResolverImpl;
import org.ecos.logic.android.services.media.audio.AudioServiceImpl;
import org.ecos.logic.android.services.media.audio.speech.LocalResourcesSpeechEngine;
import org.ecos.logic.services.interfaces.exceptions.NotInitialized;
import org.ecos.logic.services.interfaces.speech.SpeechEngine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LocalResourcesSpeechServiceTests {

    private SpeechEngine mSpeechEngine;

    @Before
    public void setUp() throws Exception {
        Context applicationContext = InstrumentationRegistry.getTargetContext();
        ContextResolver contextResolver = new ContextResolverImpl(applicationContext);
        ApplicationContextResolver applicationContextResolver = new ApplicationContextResolverImpl(applicationContext);
        ResIdResolverImpl idResolver = new ResIdResolverImpl(contextResolver);
        AudioServiceImpl audioService = new AudioServiceImpl(applicationContextResolver);

        mSpeechEngine = new LocalResourcesSpeechEngine(idResolver, audioService, applicationContextResolver);
    }

    @Test
    public void howToUseLocalResourcesSpeechEngine_WithNo_EventCatching() throws InterruptedException, NotInitialized {
        mSpeechEngine.speak("ca");
        Thread.sleep(1000);

        mSpeechEngine.speak("ce");
        Thread.sleep(1000);

        mSpeechEngine.speak("ci");
        Thread.sleep(1000);

        mSpeechEngine.speak("co");
        Thread.sleep(1000);

        mSpeechEngine.speak("cu");
        Thread.sleep(1000);
    }

    @Test
    public void howToUseLocalResourcesSpeechEngine_With_EventCatching() throws InterruptedException, NotInitialized {
        mSpeechEngine.speak("ca", this::speechCeSyllable);
        Thread.sleep(5000);
    }

    private void speechCeSyllable() throws NotInitialized {
        mSpeechEngine.speak("ce", this::speechCiSyllable);
    }

    private void speechCiSyllable() throws NotInitialized {
        mSpeechEngine.speak("ci", this::speechCoSyllable);
    }

    private void speechCoSyllable() throws NotInitialized {
        mSpeechEngine.speak("co", this::speechCuSyllable);
    }

    private void speechCuSyllable() throws NotInitialized {
        mSpeechEngine.speak("cu");
    }
}