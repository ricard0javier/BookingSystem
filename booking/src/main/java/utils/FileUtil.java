package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Utility class that help to write a file
 * @author Ricardo Javier
 *
 */
public class FileUtil {
	
	/**
	 * Write content in a file
	 * @param content String with the content to be written
	 * @param filePath path to the file which will contains the content
	 */
	public static void write(String content, String filePath) {
		Writer writer = null;
		try {
			writer = new FileWriter(filePath);
			writer.write(content);
		} catch (IOException e) {
			System.err.println("Error writing the file : ");
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	/**
	 * Read a file and return a string with its data
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static String readFile(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		 String everything = null;
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\r\n");
	            line = br.readLine();
	        }
	        everything = sb.toString();
	    } finally {
	        br.close();
	    }
	    return everything;
	}
}
