package di.gunyoung.nurse.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import di.gunyoung.nurse.connect.ConnectDB;
import model.InChargePatientVO;
import model.LongTermScheduleVO;
import model.NurseVO;
import model.PatientVO;

@SuppressWarnings("unchecked")
@Repository("AdminDAO")
public class AdminDAO extends ConnectDB{
	
	public List<PatientVO> getPatientList(){
		return (List<PatientVO>)selectList("Patient.getPatientList");
	}
	public void insertPatient(PatientVO patientVO) {
		insert("Patient.insertPatient",patientVO);
	}
	public PatientVO getPatient(PatientVO patientVO) {
		return (PatientVO)selectOne("Patient.getPatient",patientVO);
	}
	public void updatePatient(PatientVO patientVO) {
		update("Patient.updatePatient",patientVO);
	}
	public void deletePatient(String patientcode) {
		delete("Patient.deletePatient",patientcode);
	}
	public void deleteInChargePatient(String nurseid) {
		delete("InChargePatient.deleteInChargePatient",nurseid);
	}
	public void insertInChargePatient(InChargePatientVO inChargePatientVO) {
		insert("InChargePatient.insertInChargePatient",inChargePatientVO);
	}
	public void update_nurse_today_schedule(NurseVO nurseVO) {
		update("Nurse.update_today_schedule",nurseVO);
	}
	public void insertLongTermSchedule(LongTermScheduleVO longTermScheduleVO) {
		insert("LongTermSchedule.insertLongTermSchedule", longTermScheduleVO);
	}
	

}
