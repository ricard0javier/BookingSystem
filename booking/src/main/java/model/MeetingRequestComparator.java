package model;

import java.util.Comparator;

import vo.MeetingRequestVO;

public class MeetingRequestComparator implements Comparator<MeetingRequestVO> {

	public int compare(MeetingRequestVO o1, MeetingRequestVO o2) {
		return o1.getRequestTime().compareTo(o2.getRequestTime());
	}

}
