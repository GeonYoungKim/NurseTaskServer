package di.gunyoung.nurse.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import di.gunyoung.nurse.service.AdminService;
import model.JsonResult;
import model.LongTermScheduleVO;
import model.NurseVO;
import model.PatientVO;
import net.sf.json.JSONObject;

@RestController
public class AdminController {
	
	@Resource(name="AdminService")
	private AdminService adminService;
	
	@GetMapping("/patient-list")
	public @ResponseBody String nurseList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("patientList");
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(adminService.getPatientList()));
		return jsonObject.toString(); 
	}
	@PostMapping("/insert-patient")
	public @ResponseBody String insertPatient(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,Object> data) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("insertPatient");
		PatientVO patientVO=new PatientVO();
		
		String patientCode=data.get("patientCode").toString();
		String name=data.get("name").toString();
		String birth=data.get("birth").toString();
		String sex=data.get("sex").toString();
		String disease=data.get("disease").toString();
		String period=data.get("period").toString();
		String note=data.get("note").toString();
		String room=data.get("room").toString();
		String image=data.get("image").toString();
		
		patientVO.setPatientCode(patientCode);
		patientVO.setName(name);
		patientVO.setBirth(birth);
		patientVO.setSex(sex);
		patientVO.setDisease(disease);
		patientVO.setPeriod(period);
		patientVO.setNote(note);
		patientVO.setRoom(room);
		patientVO.setImage(image);
		adminService.insertPatient(patientVO);
		
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(adminService.getPatient(patientVO)));
		return jsonObject.toString();
	}
	@PostMapping("/update-patient")
	public @ResponseBody String updatePatient(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,Object> data) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("updatePatient");
		PatientVO patientVO=new PatientVO();
		String patientCode=data.get("patientCode").toString();
		String name=data.get("name").toString();
		String birth=data.get("birth").toString();
		String sex=data.get("sex").toString();
		String disease=data.get("disease").toString();
		String period=data.get("period").toString();
		String note=data.get("note").toString();
		String room=data.get("room").toString();
		
		
		patientVO.setPatientCode(patientCode);
		patientVO.setName(name);
		patientVO.setBirth(birth);
		patientVO.setSex(sex);
		patientVO.setDisease(disease);
		patientVO.setPeriod(period);
		patientVO.setNote(note);
		patientVO.setRoom(room);
		
		adminService.updatePatient(patientVO);
		
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(adminService.getPatient(patientVO)));
		return jsonObject.toString();
	}
	@PostMapping("/delete-patient")
	public @ResponseBody void deletePatient(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("deletePatient");
		String patientCode=request.getParameter("patientCode");
		adminService.deletePatient(patientCode);
	
	}
	@PostMapping("/insert-incharge-patient")
	public @ResponseBody void insertInChargePatient(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,Object> data) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("insertInChargePatient");		
		String nurseId=data.get("nurseId").toString();
		String patientCode=data.get("patientCode").toString();
		System.out.println(nurseId);
		System.out.println(patientCode);			
		
		adminService.deleteInChargePatient(nurseId);
		adminService.insertInChargePatient(nurseId,patientCode);
	
	}
	@PostMapping("/today-schedule-update")
	public @ResponseBody void today_schedule_update(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,Object> data) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("today_schedule_update");
		String todayScheduleResult=data.get("todayScheduleResult").toString();request.getParameter("todayScheduleResult");
		String nurseId=data.get("nurseId").toString();
		
		NurseVO nurseVO=new NurseVO();
		nurseVO.setNurseId(nurseId);
		nurseVO.setTodaySchedule(todayScheduleResult);
		
		adminService.updateNurseTodaySchedule(nurseVO);
	}
	@PostMapping("/long-term-schedule-insert")
	public @ResponseBody void long_term_schedule_insert(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,Object> data) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("long_term_schedule_insert");
		
		String startDay=data.get("startDay").toString();
		String endDay=data.get("endDay").toString();
		String content=data.get("content").toString();
		String nurseId=data.get("nurseId").toString();
		
		LongTermScheduleVO longTermScheduleVO=new LongTermScheduleVO();
		longTermScheduleVO.setContent(content);
		longTermScheduleVO.setEndDay(endDay);
		longTermScheduleVO.setStartDay(startDay);
		longTermScheduleVO.setLongNurseId(nurseId);
		
		adminService.insertLongTermSchedule(longTermScheduleVO);
		
	}
	
	
}
