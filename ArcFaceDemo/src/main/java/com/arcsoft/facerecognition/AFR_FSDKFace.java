package com.arcsoft.facerecognition;

/**
 * Created by xiaoQ on 2017/10/19.
 */

public class AFR_FSDKFace {
    public static final int FEATURE_SIZE = 22020;
    byte[] mFeatureData;

    public AFR_FSDKFace(AFR_FSDKFace self) {
        this.mFeatureData = (byte[])self.getFeatureData().clone();
    }

    public AFR_FSDKFace() {
        this.mFeatureData = new byte[22020];
    }

    public AFR_FSDKFace(byte[] data) {
        this.mFeatureData = data;
    }

    public byte[] getFeatureData() {
        return this.mFeatureData;
    }

    public void setFeatureData(byte[] data) {
        this.mFeatureData = data;
    }

    public AFR_FSDKFace clone() {
        return new AFR_FSDKFace(this);
    }
}
