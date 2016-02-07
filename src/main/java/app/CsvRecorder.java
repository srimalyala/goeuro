package app;

import java.util.List;
import org.apache.commons.lang3.StringUtils;


/**
 * This class is used in generating CSV files.
 *
 * @author srikanth
 */
public class CsvRecorder {

    private final static String SEPERATOR = ",";

    private List<String> data;

    public CsvRecorder(List<String> data) {
        super();
        this.data = data;
    }

    public void add(String... line) {
        data.add(StringUtils.join(line, SEPERATOR));
    }

    public void add(int pos, String... line) {
        data.add(pos, StringUtils.join(line, SEPERATOR));
    }

    public List<String> getData() {
        return data;
    }

    String getSeperator() {
        return CsvRecorder.SEPERATOR;
    }
}
