package mediaDB;

import javafx.beans.value.ObservableValue;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;

public interface MediaContent extends Content {
    int getBitrate();
    int getLength();
    int getSize();

}
