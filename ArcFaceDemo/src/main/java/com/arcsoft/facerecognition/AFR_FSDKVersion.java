package com.arcsoft.facerecognition;

/**
 * Created by xiaoQ on 2017/10/19.
 */

public class AFR_FSDKVersion {
    String mVersion = null;
    long lFeatureLevel = 0L;

    public AFR_FSDKVersion() {
    }

    public String toString() {
        return this.mVersion;
    }

    public long getFeatureLevel() {
        return this.lFeatureLevel;
    }
}
