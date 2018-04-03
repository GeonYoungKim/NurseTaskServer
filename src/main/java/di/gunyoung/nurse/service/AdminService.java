package di.gunyoung.nurse.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



import di.gunyoung.nurse.dao.AdminDAO;
import model.InChargePatientVO;
import model.LongTermScheduleVO;
import model.NurseVO;
import model.PatientVO;


@Service("AdminService")
public class AdminService {
	
	@Resource(name = "AdminDAO")
	private AdminDAO adminDAO;
	
	public List<PatientVO> getPatientList(){
		return adminDAO.getPatientList();
	}
	public void insertPatient(PatientVO patientVO) {
		adminDAO.insertPatient(patientVO);
	}
	public PatientVO getPatient(PatientVO patientVO) {
		return adminDAO.getPatient(patientVO);
	}
	public void updatePatient(PatientVO patientVO) {
		adminDAO.updatePatient(patientVO);
	}
	public void deletePatient(String patientcode) {
		adminDAO.deletePatient(patientcode);
	}
	public void deleteInChargePatient(String nurseid) {
		adminDAO.deleteInChargePatient(nurseid);
	}
	public void insertInChargePatient(String nurseid,String patientcodes) {
		String[] inchargepatient=patientcodes.split("-");
		InChargePatientVO inChargePatientVO=new InChargePatientVO();
		for(String str:inchargepatient) {
			inChargePatientVO.setNurseId(nurseid);
			inChargePatientVO.setPatientCode(str);
			adminDAO.insertInChargePatient(inChargePatientVO);
		}
		
	}
	
	public void updateNurseTodaySchedule(NurseVO nurseVO) {
		adminDAO.updateNurseTodaySchedule(nurseVO);
	}
	public void insertLongTermSchedule(LongTermScheduleVO longTermScheduleVO) {
		adminDAO.insertLongTermSchedule(longTermScheduleVO);
	}
	
	
}
