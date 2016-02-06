package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 * Main class which retrieves consumes api resource to get city information.
 * This class is executanle using java -jar "build/libs/gs-consuming-rest-0.1.7.jar"
 *
 * @author srikanth
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args[0]);
    }

    @Override
    public void run(String... strings) throws Exception {

        final String resource = "http://api.goeuro.com/api/v2/position/suggest/en/";

        // Access API resource
        final RestTemplate restTemplate = new RestTemplate();
        final CityInfo cityInfos[] = restTemplate.getForObject(resource + strings[0],
                CityInfo[].class);

        if (cityInfos.length == 0) {
            log.error("No city information found : expected city name");
            return;
        }

        final String fileName = "citydump.csv";
        final String directory = "./citydump";

        // File to write CSV city info dump
        final File file = createNewFile(fileName, directory);
        final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        final CsvRecorder recorder = new CsvRecorder(new ArrayList<String>());
        addHeaders(recorder);

        for (final CityInfo cityInfo : cityInfos) {
            final String cityInfoCsvLine = createCityInfoCsvLine(cityInfo);
            recorder.add(cityInfoCsvLine);
        }

        for (final String line : recorder.getData()) {
            bufferedWriter.append(line);
            bufferedWriter.newLine();
        }
        
        bufferedWriter.close();
        log.info("CSV dump generated...");
    }

    /**
     * This method adds the csv header
     *
     * @param recorder
     */
    private void addHeaders(CsvRecorder recorder) {
        recorder.add(0, "_id, name, type, latitude, longitude");
    }

    /**
     * creates a line of city information
     *
     * @param cityInfo
     * @return
     */
    private String createCityInfoCsvLine(CityInfo cityInfo) {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(cityInfo.get_id()).append(",");

        stringBuilder.append(cityInfo.getName()).append(",");

        stringBuilder.append(cityInfo.getType()).append(",");

        if (cityInfo.getGeo_position() != null) {
            stringBuilder.append(cityInfo.getGeo_position().getLatitude()).append(",");
            stringBuilder.append(cityInfo.getGeo_position().getLongitude());
        } else {
            stringBuilder.append(",,");
        }

        return stringBuilder.toString();
    }
    
    /**
     * @param fileName
     * @param directory
     * @return
     */
    private File createNewFile(String fileName, String directory) {
        try {
            
            File dir = new File(directory);
            
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            File file = new File(dir, fileName);
            
            if (file.exists()) {
                file.delete();
            }

            file.createNewFile();
            return file;
        } catch (Exception e) {
            log.error(String.format("Could not create output file with name [%s] in directory %s",
                    fileName, directory), e);
            return null;
        }
    }

}