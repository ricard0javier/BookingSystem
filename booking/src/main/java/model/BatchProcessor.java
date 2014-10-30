package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import vo.MeetingRequestVO;
import vo.SubmissionVO;

/**
 * Main class that process a batch of meeting requests
 * @author Ricardo Javier
 *
 */
public class BatchProcessor {
	
	/**
	 * Process a string input as a batch of booking requests
	 * @param input
	 * @return
	 */
	public String process(String input) {
		String result = "";
		if (input != null || !input.isEmpty()) {
			String[] lines = input.split("\\n|\\r\\n");
			if (lines.length % 2 == 1) {
				SubmissionVO submissionVO = processWorkingHours(lines[0]);
			}
		}
		return result;
	}
	
	/**
	 * Extract the working hours from a string
	 * @return
	 */
	public SubmissionVO processWorkingHours(String input) {
		SubmissionVO result = null;
		if (input != null || !input.isEmpty()) {
			String[] lines = input.split(" ");
			if (lines.length ==  2) {
				result = new SubmissionVO();
				result.setOpeningHour(Integer.valueOf(lines[0]));
				result.setClosingHour(Integer.valueOf(lines[1]));
			}
		}
		return result;
	}
	
	/**
	 * Extract the list of requests from a string
	 * @param input
	 * @return
	 */
	public List<MeetingRequestVO> processRequests(String[] input) {
		List<MeetingRequestVO> result = null;
		if (input != null && input.length-1 > 1 && input.length % 2 == 1) {
			for(int i=1; i<input.length-1; i+=2) {
//				[request submission time, in the format YYYY-MM-DD HH:MM:SS] 
//				[employee id]
//				[meeting start time, in the format YYYY-MM-DD HH:MM] 
//				[meeting duration in hours]
				MeetingRequestVO meetingRequestVO = new MeetingRequestVO();
				try {
					meetingRequestVO.setRequestTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(input[i].substring(0, 19))));
					meetingRequestVO.setEmployeeId(input[i].substring(20));
					meetingRequestVO.setStartTime((new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(input[i+1].substring(0, 16))));
					meetingRequestVO.setDuration(Integer.valueOf(input[i+1].substring(17)));
					if (result == null) {
						result = new ArrayList<MeetingRequestVO>();
					}
					result.add(meetingRequestVO);
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
		}		
		return result;
	}
	
	/**
	 * Generates the result map with the date as key and a list meetings as value
	 * @return
	 */
	public Map<Date, List<MeetingRequestVO>> processOutput(SubmissionVO input) {
		return null;
	}
	

}
