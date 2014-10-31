package vo;

import java.util.Date;


public class MeetingRequestVO implements Comparable<MeetingRequestVO>{
	
	/**
	 * Moment in which the submission was requested
	 */
	private Date requestTime;
	
	/**
	 * Identity of the employee who made the request
	 */
	private String employeeId; 
	
	/**
	 * Meeting start time
	 */
	private Date startTime;
	
	/**
	 * Meeting start time
	 */
	private Date endTime;
	
	/**
	 * Meeting duration in hours
	 */
	private int duration;

	/**
	 * @return the requestTime
	 */
	public Date getRequestTime() {
		return requestTime;
	}

	/**
	 * @param requestTime the requestTime to set
	 */
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * @param startTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int compareTo(MeetingRequestVO o) {
		return this.startTime.compareTo(o.getStartTime());
	}
	
	

}
