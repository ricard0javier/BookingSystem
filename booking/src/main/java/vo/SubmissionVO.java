package vo;

import java.util.List;

/**
 * Submission class which represents a booking request 
 * @author Ricardo Javier
 *
 */
public class SubmissionVO {
	
	/**
	 * Hour in which the company open, expressed in 24 hour format
	 */
	private int openingHour;
	
	/**
	 * Hour in which the company closes, expressed in 24 hour format
	 */
	private int closingHour;
	
	/**
	 * List of meeting request made
	 */
	private List<MeetingRequestVO> requests;

	/**
	 * @return the openingHour
	 */
	public int getOpeningHour() {
		return openingHour;
	}

	/**
	 * @param openingHour the openingHour to set
	 */
	public void setOpeningHour(int openingHour) {
		this.openingHour = openingHour;
	}

	/**
	 * @return the closingHour
	 */
	public int getClosingHour() {
		return closingHour;
	}

	/**
	 * @param closingHour the closingHour to set
	 */
	public void setClosingHour(int closingHour) {
		this.closingHour = closingHour;
	}

	/**
	 * @return the requests
	 */
	public List<MeetingRequestVO> getRequests() {
		return requests;
	}

	/**
	 * @param requests the requests to set
	 */
	public void setRequests(List<MeetingRequestVO> requests) {
		this.requests = requests;
	}

	public boolean equals(SubmissionVO o) {
		if (o.openingHour == this.openingHour
				&& o.closingHour == this.closingHour
				&& o.requests == this.requests) {
			return true;	
		}
		return false;
	}

}
