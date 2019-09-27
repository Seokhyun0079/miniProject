package model.vo;

public class Member {

	private String memberName;
	private String memberNumber;
	private int memberPoint;

	public Member() {
	}

	public Member(String memberName, String memberNumber) {
		this.memberName = memberName;
		this.memberNumber = memberNumber;
		this.memberPoint = 0;
	}

	public Member(String memberName, String memberNumber, int memberPoint) {
		this.memberName = memberName;
		this.memberNumber = memberNumber;
		this.memberPoint = memberPoint;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public int getMemberPoint() {
		return memberPoint;
	}

	public void setMemberPoint(int memberPoint) {
		this.memberPoint = memberPoint;
	}

	@Override
	public String toString() {
		return "memberName=" + memberName + ", memberNumber=" + memberNumber + ", memberPoint=" + memberPoint;
	}

}

/*
 * 내가 그렇게렇게 만만하니 하!
 * 내가 그렇게렇게 만만하니 하!
 * 
 * 석현이 한테 다 들었어요
 * 예지님이 나 일 안한다고 샷건 치면서 화낸다면서요  ????? 
 * 맵소사~~~~~~
 * 
 * 
 * 저  파업할거에요 예지님 미워서 헝허엏유ㅠㅠㅠㅠㅠㅠ
 * 잘살아요 예지님~
 */
