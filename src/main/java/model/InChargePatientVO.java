package model;

public class InChargePatientVO {
	private String nurseid;
	private String patientcode;
	public String getNurseid() {
		return nurseid;
	}
	public void setNurseid(String nurseid) {
		this.nurseid = nurseid;
	}
	public String getPatientcode() {
		return patientcode;
	}
	public void setPatientcode(String patientcode) {
		this.patientcode = patientcode;
	}
	@Override
	public String toString() {
		return "InChargePatientVO [nurseid=" + nurseid + ", patientcode=" + patientcode + "]";
	}
	

}
