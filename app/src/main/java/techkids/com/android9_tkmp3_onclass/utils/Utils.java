package techkids.com.android9_tkmp3_onclass.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by tungthanh.1497 on 07/29/2017.
 */

public class Utils {
    public static String converTime(int time) {
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(time)
                , TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
    }
}
