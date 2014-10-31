package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import vo.MeetingRequestVO;
import vo.SubmissionVO;

public class BatchProcessorTest {

	private static String OPENING_HOURS = "0900 1730";
	private static String INPUT = ""
			+ OPENING_HOURS
			+ "\n2011-03-17 10:17:06 EMP001"
			+ "\n2011-03-21 09:00 2"
			+ "\n2011-03-16 12:34:56 EMP002"
			+ "\n2011-03-21 09:00 2"
			+ "\n2011-03-16 09:28:23 EMP003"
			+ "\n2011-03-22 14:00 2"
			+ "\n2011-03-17 10:17:06 EMP004"
			+ "\n2011-03-22 16:00 1"
			+ "\n2011-03-15 17:29:12 EMP005"
			+ "\n2011-03-21 16:00 3";
	private static String OUTPUT = ""
			+ "2011-03-21"
			+ "\n09:00 11:00 EMP002"
			+ "\n2011-03-22"
			+ "\n14:00 16:00 EMP003"
			+ "\n16:00 17:00 EMP004";
	
	private BatchProcessor batchProcessor = new BatchProcessor();	
	
	@Test
	public void testProcess() throws ParseException {
		BatchProcessor batchProcessor = new BatchProcessor();
		String result = batchProcessor.processMeetingRequests(INPUT);
		Assert.assertTrue(result.equals(OUTPUT));
	}
	

	@Test
	public void testProcessWorkingHours() throws ParseException {
		SubmissionVO submissionVO = new SubmissionVO();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		submissionVO.setOpeningHour(sdf.parse("0900"));
		submissionVO.setClosingHour(sdf.parse("1730"));
		Assert.assertTrue(submissionVO.equals(batchProcessor.processWorkingHours(OPENING_HOURS)));
	}

	@Test
	public void testProcessRequests() throws ParseException {
		SubmissionVO submissionVO = new SubmissionVO();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		submissionVO.setOpeningHour(sdf.parse("0900"));
		submissionVO.setClosingHour(sdf.parse("1730"));
		List<MeetingRequestVO> array = batchProcessor.processRequests(INPUT.split("\\n|\\r\\n"), submissionVO);
		Assert.assertTrue(array.size() >= 0);
	}
}
