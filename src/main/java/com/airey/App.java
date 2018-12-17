package com.airey;

import com.airey.domain.Activity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.String.format;

public class App {
    private static final String APPLICATION_PROPERTIES = "application.properties";
    private static final String OUTPUT_DIRECTORY = "output.directory";

    public App() throws IOException {
        final Properties properties = new Properties();
        properties.load(new FileInputStream(getClass().getClassLoader().getResource(APPLICATION_PROPERTIES).getFile()));
        final String outputDirectory = properties.getProperty(OUTPUT_DIRECTORY);
        generate(outputDirectory, "runs");
        generate(outputDirectory, "rides");
        System.out.println("Completed!");
    }

    private void generate(final String outputDirectory, final String activityType) throws IOException {
        final List<Activity> activities = readActivities(activityType);
        final List<String> activityLines = generateActivityLines(activities);
        writeFile(outputDirectory, activityType, activityLines);
    }

    private List<Activity> readActivities(final String activityType) throws FileNotFoundException {
        final Gson gson = new GsonBuilder().create();
        final Type REVIEW_TYPE = new TypeToken<List<Activity>>() {
        }.getType();
        final String filename = activityType + ".json";
        final JsonReader reader = new JsonReader(new FileReader(getClass().getClassLoader().getResource(filename).getFile()));
        return gson.fromJson(reader, REVIEW_TYPE);
    }

    private List<String> generateActivityLines(final List<Activity> activities) {
        final List<String> activityLines = new ArrayList<>();
        Double total = 0.0;

        for (final Activity activity : activities) {
            total += activity.getDistance();
            activityLines.add(format("%s,%s,%s,%s,%s,%s",
                    activity.getId(), activity.getName(),
                    activity.getStartDate(), activity.getStartDateLocal(),
                    activity.getDistance(), total));
        }

        return activityLines;
    }

    private void writeFile(final String outputDirectory, final String activityType, final List<String> activityLines) throws IOException {
        final File file = getFile(outputDirectory, activityType);
        final OutputStream os = new FileOutputStream(file);
        final PrintStream printStream = new PrintStream(os);

        for (final String activityLine : activityLines) {
            printStream.println(activityLine);
        }

        printStream.close();
    }

    private File getFile(final String outputDirectory, final String activityType) throws IOException {
        final String filename = outputDirectory + activityType + ".csv";
        System.out.println(filename);
        final File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
        }

        return file;
    }

    public static void main(String[] args) throws IOException {
        new App();
    }
}
