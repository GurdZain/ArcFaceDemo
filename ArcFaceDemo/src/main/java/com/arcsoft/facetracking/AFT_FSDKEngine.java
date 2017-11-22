package com.arcsoft.facetracking;

import java.util.List;

/**
 * Created by xiaoQ on 2017/10/19.
 */

public class AFT_FSDKEngine {
    private final String TAG = this.getClass().toString();
    public static final int CP_PAF_NV21 = 2050;
    public static final int AFT_OPF_0_ONLY = 1;
    public static final int AFT_OPF_90_ONLY = 2;
    public static final int AFT_OPF_270_ONLY = 3;
    public static final int AFT_OPF_180_ONLY = 4;
    public static final int AFT_OPF_0_HIGHER_EXT = 5;
    public static final int AFT_FOC_0 = 1;
    public static final int AFT_FOC_90 = 2;
    public static final int AFT_FOC_270 = 3;
    public static final int AFT_FOC_180 = 4;
    private Integer handle = Integer.valueOf(0);
    private AFT_FSDKError error = new AFT_FSDKError();
    private AFT_FSDKFace[] mFaces = new AFT_FSDKFace[16];
    private int mFaceCount = 0;

    private native int FT_Init(String var1, String var2, int var3, int var4, int var5, AFT_FSDKError var6);

    private native int FT_Process(int var1, byte[] var2, int var3, int var4, int var5);

    private native int FT_Config(int var1, int var2);

    private native int FT_GetResult(int var1, AFT_FSDKFace[] var2);

    private native int FT_GetErrorCode(int var1);

    private native int FT_UnInit(int var1);

    private native String FT_Version(int var1);


    private AFT_FSDKFace[] obtainFaceArray(int size) {
        if(this.mFaceCount < size) {
            if(this.mFaces.length < size) {
                this.mFaces = new AFT_FSDKFace[(size / 16 + 1) * 16];
            }

            for(int i = this.mFaceCount; i < size; ++i) {
                this.mFaces[i] = new AFT_FSDKFace();
            }

            this.mFaceCount = size;
        }

        return this.mFaces;
    }

    public AFT_FSDKError AFT_FSDK_InitialFaceEngine(String appid, String sdkkey, int orientsPriority, int scale, int maxFaceNum) {
        this.handle = Integer.valueOf(this.FT_Init(appid, sdkkey, orientsPriority, scale, maxFaceNum, this.error));
        return this.error;
    }

    public AFT_FSDKError AFT_FSDK_FaceFeatureDetect(byte[] data, int width, int height, int format, List<AFT_FSDKFace> list) {
        if(list != null && data != null) {
            if(this.handle.intValue() != 0) {
                int count = this.FT_Process(this.handle.intValue(), data, width, height, format);
                this.error.mCode = this.FT_GetErrorCode(this.handle.intValue());
                if(count > 0 && this.error.mCode == 0) {
                    AFT_FSDKFace[] result = this.obtainFaceArray(count);
                    this.error.mCode = this.FT_GetResult(this.handle.intValue(), result);

                    for(int i = 0; i < count; ++i) {
                        list.add(result[i]);
                    }
                }
            } else {
                this.error.mCode = 5;
            }
        } else {
            this.error.mCode = 2;
        }

        return this.error;
    }

    public AFT_FSDKError AFT_FSDK_UninitialFaceEngine() {
        if(this.handle.intValue() != 0) {
            this.error.mCode = this.FT_UnInit(this.handle.intValue());
            this.handle = Integer.valueOf(0);
        } else {
            this.error.mCode = 5;
        }

        return this.error;
    }

    public AFT_FSDKError AFT_FSDK_GetVersion(AFT_FSDKVersion version) {
        if(version == null) {
            this.error.mCode = 2;
        } else if(this.handle.intValue() != 0) {
            this.error.mCode = 0;
            version.mVersion = this.FT_Version(this.handle.intValue());
        } else {
            this.error.mCode = 5;
        }

        return this.error;
    }

    static {
        System.loadLibrary("mpbase");
        System.loadLibrary("ArcSoft_FTEngine");
    }
}
