package mediaDB;

import java.math.BigDecimal;
import java.time.Duration;

public interface MediaContent extends Content {
    int getBitrate();
    int getLength();
    int getSize();
}
