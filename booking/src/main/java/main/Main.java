package main;

import model.BatchProcessor;
import utils.FileUtil;



/**
 * Class that contains the main method which expect at least 
 * one parameter, the input file path, and an additional
 * parameter (optional) to indicate where output 
 * should be stored, by default, output is stored
 * at the same place where the jar is located (./meetings.txt)
 * 
 * @author Ricardo Javier
 *
 */
public class Main {

	/**
	 * Main method which expect at least 
	 * one parameter, the input file path, and an additional
	 * parameter (optional) to indicate where the output 
	 * should be stored, by default, output is stored
	 * at the same place where the jar is located (./meetings.txt) 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			String path = args[0].replaceAll("\\\\|/", "\\"+System.getProperty("file.separator"));
			String input = FileUtil.readFile(path);
			BatchProcessor batchProcessor = new BatchProcessor();
			String content = batchProcessor.processMeetingRequests(input);
		
			String filePath;
			if (args.length > 1 && args[1] != null) {
				filePath = args[1];
			} else {
				filePath = "meetings.txt";
			}
			FileUtil.write(content, filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}