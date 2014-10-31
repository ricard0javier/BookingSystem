package vo;

import java.util.Date;
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
	private Date openingHour;
	
	/**
	 * Hour in which the company closes, expressed in 24 hour format
	 */
	private Date closingHour;
	
	/**
	 * List of meeting request made
	 */
	private List<MeetingRequestVO> requests;

	/**
	 * @return the openingHour
	 */
	public Date getOpeningHour() {
		return openingHour;
	}

	/**
	 * @param openingHour the openingHour to set
	 */
	public void setOpeningHour(Date openingHour) {
		this.openingHour = openingHour;
	}

	/**
	 * @return the closingHour
	 */
	public Date getClosingHour() {
		return closingHour;
	}

	/**
	 * @param closingHour the closingHour to set
	 */
	public void setClosingHour(Date closingHour) {
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
		if (o.openingHour.equals(this.openingHour)
				&& o.closingHour.equals(this.closingHour))
			if((o.requests != null && o.requests.equals(this.requests))
				|| (o.requests == null && this.requests == null)) {
			return true;	
		}
		return false;
	}

}
