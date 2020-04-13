package tp56;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PerformanceRecorder {
    private static final int THREAD_LIMIT = 20;
    private static final String IMAGE__TEST = "15226222451_75d515f540_o.jpg";

    public static void main(String [] args) throws Exception {
        System.out.println("Part 3 - Performance Recorder\n");
        System.out.println("****************************************");
        System.out.println("******** Single Threaded Engine ********");
        System.out.println("****************************************");
        SingleThreadedImageFilteringEngine singleEngine = new SingleThreadedImageFilteringEngine();
        singleEngine.loadImage(IMAGE__TEST);
        System.out.println("Gray filter : " + measureTime(singleEngine, new GrayFilter()) + " sec");
        System.out.println("Gaussian filter : " + measureTime(singleEngine, new GaussianContourExtractorFilter()) + " sec");

        System.out.println("\n\n");

        System.out.println("****************************************");
        System.out.println("********* Multi Threaded Engine ********");
        System.out.println("****************************************");

        double [] recordedTimeGray = new double[THREAD_LIMIT + 1];
        double [] recordedTimeGaussian = new double[THREAD_LIMIT + 1];
        MultiThreadedImageFilteringEngine multiEngine;

        for(int i = 2; i <= THREAD_LIMIT; i++) {
            System.out.println("\n***     Number of thread = \t" + i + "     ***");
            multiEngine = new MultiThreadedImageFilteringEngine(i);
            multiEngine.loadImage("15226222451_5fd668d81a_c.jpg");

            recordedTimeGray[i] = measureTime(multiEngine, new GrayFilter());
            recordedTimeGaussian[i] = measureTime(multiEngine, new GaussianContourExtractorFilter());

            System.out.println("Gray filter : " + recordedTimeGray[i] + " sec");
            System.out.println("Gaussian filter : " + recordedTimeGaussian[i] + " sec");
        }

        writeCSV("performance_gray.csv", recordedTimeGray);
        writeCSV("performance_gaussian.csv", recordedTimeGaussian);
    }

    private static double measureTime(IImageFilteringEngine engine, IFilter filter) throws Exception {
        long startTime = System.nanoTime();
        engine.applyFilter(filter);
        long elapsedTime = System.nanoTime() - startTime;
        return elapsedTime * Math.pow(10, -9);
    }

    private static void writeCSV(String csvFile, double [] times) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, false));
        writer.append("Number_Thread,Time_Sec");
        for(int i = 1; i < times.length; i++) {
            writer
                    .append("\n")
                    .append(String.valueOf(i))
                    .append(",")
                    .append(String.valueOf(times[i]));
        }
        writer.close();
    }
}
