package com.arcsoft.facetracking;

import android.graphics.Rect;

/**
 * Created by xiaoQ on 2017/10/19.
 */

public class AFT_FSDKFace {
    Rect mRect;
    int mDegree;

    public AFT_FSDKFace(AFT_FSDKFace self) {
        this.mRect = new Rect(self.getRect());
        this.mDegree = self.getDegree();
    }

    public AFT_FSDKFace() {
        this.mRect = new Rect();
        this.mDegree = 0;
    }

    public Rect getRect() {
        return this.mRect;
    }

    public int getDegree() {
        return this.mDegree;
    }

    public String toString() {
        return this.mRect.toString() + "," + this.mDegree;
    }

    public AFT_FSDKFace clone() {
        return new AFT_FSDKFace(this);
    }
}
