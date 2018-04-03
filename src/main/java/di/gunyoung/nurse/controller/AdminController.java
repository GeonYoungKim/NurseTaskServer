package di.gunyoung.nurse.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import di.gunyoung.nurse.service.AdminService;
import model.JsonResult;
import model.LongTermScheduleVO;
import model.NurseVO;
import model.PatientVO;
import net.sf.json.JSONObject;

@Controller
public class AdminController {
	
	@Resource(name="AdminService")
	private AdminService adminService;
	
	@RequestMapping("/patient-list")
	public void nurseList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("patientList");
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(adminService.getPatientList()));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString()); 
	}
	@RequestMapping("/insert-patient")
	public void insertPatient(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("insertPatient");
		PatientVO patientVO=new PatientVO();
		String patientCode=request.getParameter("patientCode");
		String name=request.getParameter("name");
		String birth=request.getParameter("birth");
		String sex=request.getParameter("sex");
		String disease=request.getParameter("disease");
		String period=request.getParameter("period");
		String note=request.getParameter("note");
		String room=request.getParameter("room");
		String image=request.getParameter("image");
		
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
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString()); 		
	}
	@RequestMapping("/update-patient")
	public void updatePatient(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("updatePatient");
		PatientVO patientVO=new PatientVO();
		String patientCode=request.getParameter("patientCode");
		String name=request.getParameter("name");
		String birth=request.getParameter("birth");
		String sex=request.getParameter("sex");
		String disease=request.getParameter("disease");
		String period=request.getParameter("period");
		String note=request.getParameter("note");
		String room=request.getParameter("room");
		
		
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
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString());
	}
	@RequestMapping("/delete-patient")
	public void deletePatient(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("deletePatient");
		String patientCode=request.getParameter("patientCode");
		adminService.deletePatient(patientCode);
	
	}
	@RequestMapping("/insert-incharge-patient")
	public void insertInChargePatient(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("insertInChargePatient");		
		String nurseId=request.getParameter("nurseId");
		String patientCode=request.getParameter("patientCode");
		System.out.println(nurseId);
		System.out.println(patientCode);			
		
		adminService.deleteInChargePatient(nurseId);
		adminService.insertInChargePatient(nurseId,patientCode);
	
	}
	@RequestMapping("/today-schedule-update")
	public void today_schedule_update(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("today_schedule_update");
		String todayScheduleResult=request.getParameter("todayScheduleResult");
		String nurseId=request.getParameter("nurseId");
		
		NurseVO nurseVO=new NurseVO();
		nurseVO.setNurseId(nurseId);
		nurseVO.setTodaySchedule(todayScheduleResult);
		
		adminService.updateNurseTodaySchedule(nurseVO);
	}
	@RequestMapping("/long-term-schedule-insert")
	public void long_term_schedule_insert(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("long_term_schedule_insert");
		String startDay=request.getParameter("startDay");
		String endDay=request.getParameter("endDay");
		String content=request.getParameter("content");
		String nurseId=request.getParameter("nurseId");
		
		LongTermScheduleVO longTermScheduleVO=new LongTermScheduleVO();
		longTermScheduleVO.setContent(content);
		longTermScheduleVO.setEndDay(endDay);
		longTermScheduleVO.setStartDay(startDay);
		longTermScheduleVO.setLongNurseId(nurseId);
		
		adminService.insertLongTermSchedule(longTermScheduleVO);
		
	}
	
	
}
