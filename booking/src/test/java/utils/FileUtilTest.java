package utils;

import static org.junit.Assert.assertArrayEquals;

import java.io.File;

import org.junit.Test;

public class FileUtilTest {

	private static String outputFile = "test.txt";
	
	@Test
	public void shouldWriteAFile() {		
		FileUtil.write("test", outputFile);
		File fileTest = new File(outputFile);
		assertArrayEquals("file test.txt should be creates", new Boolean[]{true}, new Boolean[]{fileTest.exists()});
		fileTest.delete();
	}

}
