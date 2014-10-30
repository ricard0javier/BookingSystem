package model;

import static org.junit.Assert.*;

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
	
	private BatchProcessor batchProcessor = new BatchProcessor();	
	
	@Test
	public void testProcess() {
		BatchProcessor batchProcessor = new BatchProcessor();
		batchProcessor.process(INPUT);
	}
	

	@Test
	public void testProcessWorkingHours() {
		SubmissionVO submissionVO = new SubmissionVO();
		submissionVO.setOpeningHour(900);
		submissionVO.setClosingHour(1730);
		Assert.assertTrue(submissionVO.equals(batchProcessor.processWorkingHours(OPENING_HOURS)));
	}

	@Test
	public void testProcessRequests() {
		List<MeetingRequestVO> array = batchProcessor.processRequests(INPUT.split("\\n|\\r\\n"));
		Assert.assertTrue(array.size() >= 0);
	}

	@Test
	public void testProcessOutput() {
		fail("Not yet implemented");
	}



}
