package tp56;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Performance {
	private static Integer numberOfthread = 10;
	private static String IMAGE__TEST1 = "15226222451_75d515f540_o.jpg";
	private static String graytoLoad = "15226222451_5fd668d81a_c_gray.png";

	static double[] recordedTimeGray = new double[numberOfthread];
	static double[] recordedTimeGaussian = new double[numberOfthread];
	static MultiThreadedImageFilteringEngine multiEngine;

	private static double timeOfprocess(IImageFilteringEngine engine, IFilter filter) throws Exception {
		long deb = (int) System.currentTimeMillis();
		engine.applyFilter(filter);
		long fin = (int) System.currentTimeMillis();
		return (fin - deb) * Math.pow(10, -3);
	}

	private static void writeCSV(String csvFile, double[] times) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, false));
		writer.append("numberOfthread;Time_Sec");
		for (int i = 1; i < times.length; i++) {
			writer.append("\n").append(String.valueOf(i)).append(";").append(String.valueOf(times[i]));
		}
		writer.close();
	}

	public static void main(String[] args) throws Exception {

		System.out.println("******** Single numberOfthreaded Engine ********");
		System.out.println("****************************************");
		SingleThreadedImageFilteringEngine single = new SingleThreadedImageFilteringEngine();
		single.loadImage(IMAGE__TEST1);
		System.out.println("Gray filter : " + timeOfprocess(single, new GrayFilter()) + " sec");
		System.out.println("Gaussian filter : " + timeOfprocess(single, new GaussianContourExtractorFilter()) + " sec");

		System.out.println("\n\n");

		System.out.println("****************************************");
		System.out.println("********* Multi thread Test ********");
		System.out.println("****************************************");

		for (int i = 2; i <=numberOfthread+1; i++) {
			System.out.println("\n***     Number of thread = \t" + i + "     ***");
			multiEngine = new MultiThreadedImageFilteringEngine(i);
			multiEngine.loadImage(graytoLoad);

			recordedTimeGray[i - 2] = timeOfprocess(multiEngine, new GrayFilter());
			recordedTimeGaussian[i - 2] = timeOfprocess(multiEngine, new GaussianContourExtractorFilter());

			System.out.println("Gray filter : " + recordedTimeGray[i - 2] + " sec");
			System.out.println("Gaussian filter : " + recordedTimeGaussian[i - 2] + " sec");
		}

		writeCSV("performance_gray.csv", recordedTimeGray);
		writeCSV("performance_gaussian.csv", recordedTimeGaussian);
	}

}
