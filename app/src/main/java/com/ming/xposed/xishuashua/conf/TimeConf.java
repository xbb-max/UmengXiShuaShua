package com.ming.xposed.xishuashua.conf;


import com.ming.util.common.Util;

public class TimeConf {
    public static long getTime() {
        int randomRate = Util.getRandomRate(new float[]{0.01f, 0.02f, 0.06f, 0.1f, 0.09f, 0.72f});
        switch (randomRate) {
            case 1:
                return 0L;
            case 2:
                return 25 * 1000L;
            case 6:
                return 30 * 60 * 1000L;
            case 10:
                return 60 * 1000L;
            case 9:
                return 3 * 60 * 1000L;
            case 72:
            default:
                return 10 * 60 * 1000L;
        }
    }
}
