package org.ecos.logic.kidsgame2.tests.integration.services.fake;

import android.support.annotation.IdRes;

import org.ecos.logic.android.core.exceptions.ResourceNotFoundException;
import org.ecos.logic.android.core.services.packages.InAppPackages;
import org.ecos.logic.android.core.services.packages.PackageLocation;
import org.ecos.logic.android.core.services.resolvers.ApplicationContextResolver;
import org.ecos.logic.android.core.services.resolvers.ResIdResolver;
import org.ecos.logic.android.core.services.resources.Raw;
import org.ecos.logic.android.core.services.resources.ResourceType;
import org.ecos.logic.android.services.media.audio.AudioService;
import org.ecos.logic.services.interfaces.speech.SpeakFinished;
import org.ecos.logic.services.interfaces.speech.SpeechEngine;

public class FakeAndTinySpeechEngine implements SpeechEngine {
    private ResIdResolver mIdResolver;
    private AudioService mAudioService;
    private ApplicationContextResolver mApplicationContextResolver;

    private PackageLocation mInAppPackage;
    private ResourceType mRawResourceType;

    public FakeAndTinySpeechEngine(ResIdResolver idResolver, AudioService audioService, ApplicationContextResolver applicationContextResolver) {
        mIdResolver = idResolver;
        mAudioService = audioService;
        mApplicationContextResolver = applicationContextResolver;

        mInAppPackage = new InAppPackages(mApplicationContextResolver);
        mRawResourceType = new Raw();
    }

    @Override
    public void speak(String text) {
        @IdRes
        int resourceIdentity;
        try {
            resourceIdentity = mIdResolver.
                setLocation(mInAppPackage, mRawResourceType).
                tryGetFrom(text);
            mAudioService.playFrom(resourceIdentity);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void speak(String text, SpeakFinished speakFinished) {
        speak(text);

        if(speakFinished != null)
            speakFinished.fireFinished();
    }
}
