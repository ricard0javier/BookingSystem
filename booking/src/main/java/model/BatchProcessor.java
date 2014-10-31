package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.DateTime;

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
	 * @throws ParseException 
	 */
	public String processMeetingRequests(String input) throws ParseException {
		String result = "";
		if (input != null && !input.isEmpty()) {
			String[] lines = input.split("\\n|\\r\\n");
			if (lines.length % 2 == 1) {
				SubmissionVO submissionVO = processWorkingHours(lines[0]);
				submissionVO.setRequests(processRequests(lines, submissionVO));
				Map<String, List<String>> summarize = processOutput(submissionVO);
				
				for (Entry<String,List<String>> entry : summarize.entrySet()) {
					result += "\n" + entry.getKey();
					for (String hours : entry.getValue()) {
						result += "\n" + hours;
					}
				}
				
			}
		}
		return result.replaceFirst("\n", "");
	}
	
	/**
	 * Extract the working hours from a string
	 * @return
	 * @throws ParseException 
	 */
	public SubmissionVO processWorkingHours(String input) throws ParseException {
		SubmissionVO result = null;
		if (input != null && !input.isEmpty()) {
			String[] lines = input.split(" ");
			SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
			if (lines.length ==  2) {
				result = new SubmissionVO();
				result.setOpeningHour(sdf.parse(lines[0]));
				result.setClosingHour(sdf.parse(lines[1]));
			}
		}
		return result;
	}
	
	/**
	 * Extract the list of requests from a string
	 * @param input
	 * @return
	 */
	public List<MeetingRequestVO> processRequests(String[] input, SubmissionVO workingHours) {
		List<MeetingRequestVO> result = null;
		if (input != null && input.length-1 > 1 && input.length % 2 == 1) {
			for(int i=1; i<input.length-1; i+=2) {
				MeetingRequestVO meetingRequestVO = new MeetingRequestVO();
				try {					
					meetingRequestVO.setRequestTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(input[i].substring(0, 19))));
					meetingRequestVO.setEmployeeId(input[i].substring(20));
					meetingRequestVO.setStartTime((new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(input[i+1].substring(0, 16))));
					meetingRequestVO.setDuration(Integer.valueOf(input[i+1].substring(17)));

					if (result == null) {
						result = new LinkedList<MeetingRequestVO>();
					}
					if (isInWorkingHours(meetingRequestVO, workingHours)) {
						result.add(meetingRequestVO);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			// ordering by Request time
			Collections.sort(result, new MeetingRequestComparator());
			
			// avoid overlap
			for (MeetingRequestVO meetingRequestVO : result) {
				if (isOverriden(meetingRequestVO, result)) {
					result.remove(meetingRequestVO);
				}
			}
			Collections.sort(result);
		}		
		return result;
	}
	
	/**
	 * Avoid overlap
	 * @param evaluated
	 * @param allMeetings
	 * @return
	 */
	private boolean isOverriden(MeetingRequestVO evaluated,
			List<MeetingRequestVO> allMeetings) {
		for (MeetingRequestVO previousMeeting : allMeetings) {
			if (evaluated.equals(previousMeeting)) {
				break;
			}
			DateTime endPreviousMeeting = new DateTime(previousMeeting.getStartTime()).plusHours(previousMeeting.getDuration());
			DateTime endEvaluatedMeeting = new DateTime(evaluated.getStartTime()).plusHours(evaluated.getDuration());
			if(!endEvaluatedMeeting.isBefore(previousMeeting.getStartTime().getTime())
					&& !endPreviousMeeting.isBefore(evaluated.getStartTime().getTime()))  {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check that the meeting is between the working hours
	 * @param meetingRequestVO
	 * @param workingHours
	 * @return
	 */
	private boolean isInWorkingHours(MeetingRequestVO meetingRequestVO,
			SubmissionVO workingHours) {
		DateTime start = new DateTime(meetingRequestVO.getStartTime());
		DateTime end = start.plusHours(meetingRequestVO.getDuration());
		DateTime openDt = new DateTime(workingHours.getOpeningHour());
		DateTime closeDt = new DateTime(workingHours.getClosingHour());
		DateTime open = start
				.withHourOfDay(openDt.getHourOfDay())
				.withMinuteOfHour(openDt.getMinuteOfHour())
				.withSecondOfMinute(0)
				.withMillisOfSecond(0);
		DateTime close = open
				.withHourOfDay(closeDt.getHourOfDay())
				.withMinuteOfHour(closeDt.getMinuteOfHour())
				.withSecondOfMinute(0)
				.withMillisOfSecond(0);
		
		if (!start.isBefore(open) 
				&& !start.isAfter(close)
				&& !end.isBefore(start)
				&& !end.isAfter(close)) {
			return true;
		}
		return false;
	}

	/**
	 * Generates the result map with the date as key and a list meetings as value
	 * @return
	 */
	public Map<String, List<String>> processOutput(SubmissionVO input) {
		Map<String, List<String>> result = null;
		if(input != null && input.getRequests() != null && !input.getRequests().isEmpty()) {
			result = new LinkedHashMap<String, List<String>>();
			SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfHour = new SimpleDateFormat("HH:mm");
			Calendar date = Calendar.getInstance();
			for(MeetingRequestVO meeting : input.getRequests()) {
				String key = sdfDay.format(meeting.getStartTime());
				if(!result.containsKey(key)) {
					List<String> value = new ArrayList<String>();
					result.put(key, value);
				}
				String str = sdfHour.format(meeting.getStartTime());
				date.setTime(meeting.getStartTime());
				date.add(Calendar.HOUR, meeting.getDuration());
				str += " " + sdfHour.format(date.getTime());
				str += " " + meeting.getEmployeeId();
				result.get(key).add(str);				
			}
		}
		return result;
	}
	

}
