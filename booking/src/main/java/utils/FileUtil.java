package utils;

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
}
