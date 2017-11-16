package org.ecos.logic.kidsgame2.texts.integration.speech;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.ecos.logic.android.core.services.resolvers.ApplicationContextResolver;
import org.ecos.logic.android.core.services.resolvers.ApplicationContextResolverImpl;
import org.ecos.logic.android.core.services.resolvers.ContextResolver;
import org.ecos.logic.android.core.services.resolvers.ContextResolverImpl;
import org.ecos.logic.android.core.services.resolvers.ResIdResolverImpl;
import org.ecos.logic.android.services.media.audio.AudioServiceImpl;
import org.ecos.logic.kidsgame2.tests.integration.services.fake.FakeAndTinySpeechEngine;
import org.ecos.logic.services.interfaces.speech.SpeechEngine;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FakeSpeechServiceTests  {

    @Test
    public void blableblibloblu() throws InterruptedException {
        Context applicationContext = InstrumentationRegistry.getTargetContext();
        ContextResolver contextResolver = new ContextResolverImpl(applicationContext);
        ApplicationContextResolver applicationContextResolver = new ApplicationContextResolverImpl(applicationContext);
        ResIdResolverImpl idResolver = new ResIdResolverImpl(contextResolver);
        AudioServiceImpl audioService = new AudioServiceImpl(applicationContextResolver);

        SpeechEngine speechEngine = new FakeAndTinySpeechEngine(idResolver, audioService,applicationContextResolver);

        speechEngine.speak("ca");
        Thread.sleep(1000);
    }
}