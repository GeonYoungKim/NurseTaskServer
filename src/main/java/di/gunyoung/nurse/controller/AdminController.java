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
	
	@RequestMapping("/patientList")
	public void nurseList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("patientList");
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(adminService.getPatientList()));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString()); 
	}
	@RequestMapping("/insertPatient")
	public void insertPatient(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("insertPatient");
		PatientVO patientVO=new PatientVO();
		String patientcode=request.getParameter("patientcode");
		String name=request.getParameter("name");
		String birth=request.getParameter("birth");
		String sex=request.getParameter("sex");
		String disease=request.getParameter("disease");
		String period=request.getParameter("period");
		String note=request.getParameter("note");
		String room=request.getParameter("room");
		String image=request.getParameter("image");
		
		patientVO.setPatientcode(patientcode);
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
	@RequestMapping("/updatePatient")
	public void updatePatient(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("updatePatient");
		PatientVO patientVO=new PatientVO();
		String patientcode=request.getParameter("patientcode");
		String name=request.getParameter("name");
		String birth=request.getParameter("birth");
		String sex=request.getParameter("sex");
		String disease=request.getParameter("disease");
		String period=request.getParameter("period");
		String note=request.getParameter("note");
		String room=request.getParameter("room");
		
		
		patientVO.setPatientcode(patientcode);
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
	@RequestMapping("/deletePatient")
	public void deletePatient(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("deletePatient");
		String patientcode=request.getParameter("patientcode");
		adminService.deletePatient(patientcode);
	
	}
	@RequestMapping("/insertInChargePatient")
	public void insertInChargePatient(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("insertInChargePatient");		
		String nurseId=request.getParameter("nurseId");
		String patientcodes=request.getParameter("patientcode");
		System.out.println(nurseId);
		System.out.println(patientcodes);			
		
		adminService.deleteInChargePatient(nurseId);
		adminService.insertInChargePatient(nurseId,patientcodes);
	
	}
	@RequestMapping("/today_schedule_update")
	public void today_schedule_update(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("today_schedule_update");
		String today_schedule_result=request.getParameter("today_schedule_result");
		String nurseid=request.getParameter("nurseid");
		
		NurseVO nurseVO=new NurseVO();
		nurseVO.setNurseid(nurseid);
		nurseVO.setTodayschedule(today_schedule_result);
		
		adminService.update_nurse_today_schedule(nurseVO);
	}
	@RequestMapping("/long_term_schedule_insert")
	public void long_term_schedule_insert(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("long_term_schedule_insert");
		String startday=request.getParameter("startday");
		String endday=request.getParameter("endday");
		String content=request.getParameter("content");
		String nurseid=request.getParameter("nurseid");
		
		LongTermScheduleVO longTermScheduleVO=new LongTermScheduleVO();
		longTermScheduleVO.setContent(content);
		longTermScheduleVO.setEndday(endday);
		longTermScheduleVO.setStartday(startday);
		longTermScheduleVO.setLongnurseid(nurseid);
		
		adminService.insertLongTermSchedule(longTermScheduleVO);
		
	}
	
	
}
