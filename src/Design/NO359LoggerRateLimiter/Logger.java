package Design.NO359LoggerRateLimiter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/2 16:35
 * @description
 */
public class Logger {

    private Map<String, Integer> strToTimeStamp;

    /**
     * Initialize your data structure here.
     */
    public Logger() {
        strToTimeStamp = new HashMap<>();
    }

    /**
     * Returns true if the message should be printed in the given timestamp, otherwise returns false.
     * If this method returns false, the message will not be printed.
     * The timestamp is in seconds granularity.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        Integer stamp = strToTimeStamp.get(message);
        if (stamp != null && timestamp - stamp < 10) {
            return false;
        }
        strToTimeStamp.put(message, timestamp);
        return true;
    }

}
