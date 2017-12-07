package di.gunyoung.nurse.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import di.gunyoung.nurse.service.NurseService;
import model.ChatVO;
import model.CheckRoomVO;
import model.JsonResult;
import model.NurseVO;
import model.RoomVO;
import model.UpdateNurseRoomFlagVO;
import net.sf.json.JSONObject;
@Controller
public class NurseController {
	@Resource(name = "NurseService")
	private NurseService nurseService;
	
	@RequestMapping("/login")
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		NurseVO nurseVO=new NurseVO();		
		String nurseid=request.getParameter("id");
		String password=request.getParameter("password");
		nurseVO.setNurseid(nurseid);
		nurseVO.setPassword(password);
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getNurse(nurseVO)));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString()); 
	}

	@RequestMapping("/roomPatientList")
	public void roomPatientList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("roomPatientList");
		String room=request.getParameter("room");
		room=room.replaceAll(" ", "%");
		System.out.println(room);
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getRoomPatientList(room)));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString()); 
		System.out.println(jsonObject.toString());
	}
	@RequestMapping("/insertNurse")
	public void insertNurse(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("insertNurse");
		NurseVO nurseVO=new NurseVO();
		String nurseid=request.getParameter("id");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String birth=request.getParameter("birth");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		String image=request.getParameter("image");
		
		
		nurseVO.setNurseid(nurseid);
		nurseVO.setPassword(password);
		nurseVO.setName(name);
		nurseVO.setBirth(birth);
		nurseVO.setPhone(phone);
		nurseVO.setAddress(address);
		nurseVO.setImage(image);
		nurseVO.setTodayschedule("Not yet schedule");
		nurseVO.setToken("0");
		
		nurseService.insertNurse(nurseVO);
		
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getNurse(nurseVO)));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString()); 			
	
	}
	@RequestMapping("/nurseList")
	public void nurseList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("nurselist");
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getNurseList()));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString()); 	
	}
	@RequestMapping("/inserChatRoom")
	public void inserChatRoom(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("inserChatRoom");
		request.setCharacterEncoding("UTF-8");
		
		String roomname=request.getParameter("roomname");
		System.out.println(roomname);
		
		int count=Integer.parseInt(request.getParameter("count"));
		System.out.println(count);
		
		String data1=request.getParameter("data1");
		System.out.println(data1);
		String data2=request.getParameter("data2");
		System.out.println(data2);
		
		RoomVO roomVO=new RoomVO();
		CheckRoomVO checkRoomVO=new CheckRoomVO();
		checkRoomVO.setData1(data1);
		checkRoomVO.setData2(data2);
		roomVO=nurseService.getCheckRoom(checkRoomVO);
		if(roomVO==null){
			JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.inserChatRoom(roomname,count,data1,data2)));
			response.setContentType("application/json; charset=utf-8"); 	
			response.getWriter().print(jsonObject.toString());
		}else {
			System.out.println("원래 있는방");
			JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getRoom(roomVO)));
			response.setContentType("application/json; charset=utf-8"); 	
			response.getWriter().print(jsonObject.toString());
		
		}
	}
	@RequestMapping("/roomList")
	public void roomList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("roomList");
		String nurseid=request.getParameter("nurseid");
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getRoomList(nurseid)));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString()); 
		
	
	}
	@RequestMapping("/chatList")
	public void chatList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("chatList");
		ChatVO chatVO=new ChatVO();
		String roomno=request.getParameter("roomno");
		chatVO.setRoomno(Integer.parseInt(roomno));
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getChatList(chatVO)));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString()); 
	
	}
	@RequestMapping("/insertChat")
	public void insertChat(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("insertChat");
		String roomno=request.getParameter("roomno");
		String chatcontent=request.getParameter("chatcontent");
		String nurseid2=request.getParameter("nurseid2");
		ChatVO chatVO=new ChatVO();
		
		chatVO.setChatcontent(chatcontent);
		chatVO.setNurseid2(nurseid2);
		chatVO.setRoomno(Integer.parseInt(roomno));
		
		nurseService.insertChat(chatVO);
	}
	@RequestMapping("/getRoom2")
	public void getRoom2(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("getRoom2");
		String roomno=request.getParameter("roomno");
		
		
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getRoom2(Integer.parseInt(roomno))));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString());
	
	}
	@RequestMapping("/updateRoom")
	public void updateRoom(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("updateRoom");
		String roomname=request.getParameter("roomname");
		String roomno=request.getParameter("roomno");
		String count=request.getParameter("count");
		String strNurseId=request.getParameter("strNurseId");
		
		RoomVO roomVO=new RoomVO();
		
		roomVO.setRoomno(Integer.parseInt(roomno));
		roomVO.setRoomname(roomname);
		roomVO.setCount(Integer.parseInt(count));
		
		nurseService.updateRoom(roomVO);
		nurseService.deleteNurseRoom(roomno);
		
		nurseService.updateNurseRoom(strNurseId,Integer.parseInt(roomno));
		
		
	}
	@RequestMapping("/getNurseRoom")
	public void getNurseRoom(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("getNurseRoom");		
		String roomno=request.getParameter("roomno");
		
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getNurseRoomList(roomno)));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString());			
	
	}
	@RequestMapping("/incharge_patient_show")
	public void incharge_patient_show(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("incharge_patient_show");
		String nurseid=request.getParameter("nurseid");		
		
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getInChargePatientList(nurseid)));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString());		
	
	}
	@RequestMapping("/today_schedule_show")
	public void today_schedule_show(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("today_schedule_show");		
		String nurseid=request.getParameter("nurseid");			
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getNurse_by_id(nurseid)));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString());	
	}
	@RequestMapping("/long_term_schedule_show")
	public void long_term_schedule_show(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("long_term_scheduleshow");
		String longnurseid=request.getParameter("nurseid");
		System.out.println(longnurseid);
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.get_long_term_schedule_list_by_id(longnurseid)));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString());				
	}
	@RequestMapping("/update_token")
	public void update_token(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("update_token");
		String nurseid=request.getParameter("nurseid");
		String token=request.getParameter("token");
		
		NurseVO nurseVO=new NurseVO();
		nurseVO.setNurseid(nurseid);
		nurseVO.setToken(token);
		
		nurseService.updateToken(nurseVO);
	}
	@RequestMapping("/update_nurseroom_token")
	public void update_nurseroom_token(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("update_nurseroom_token");
		String nurseid=request.getParameter("nurseid");
		String token=request.getParameter("token");
		
		NurseVO nurseVO=new NurseVO();
		nurseVO.setNurseid(nurseid);
		nurseVO.setToken(token);
		nurseService.updateNurseRoomToken(nurseVO);
	}
	@RequestMapping("/getRoomFlag")
	public void getRoomFlag(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("getRoomFlag");
		String nurseid=request.getParameter("nurseid");
		String roomno=request.getParameter("roomno");
		UpdateNurseRoomFlagVO updateNurseRoomFlagVO=new UpdateNurseRoomFlagVO();
		updateNurseRoomFlagVO.setNurseid(nurseid);
		updateNurseRoomFlagVO.setRoomnum(Integer.parseInt(roomno));
		int flag=nurseService.getNurseRoombyflag(updateNurseRoomFlagVO);
		updateNurseRoomFlagVO.setFlag(flag+1);
		nurseService.updateFlag(updateNurseRoomFlagVO);			
		
	}
	@RequestMapping("/getRoomFlag2")
	public void getRoomFlag2(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("getRoomFlag2");
		String nurseid=request.getParameter("nurseid");
		String roomno=request.getParameter("roomno");
		UpdateNurseRoomFlagVO updateNurseRoomFlagVO=new UpdateNurseRoomFlagVO();
		updateNurseRoomFlagVO.setNurseid(nurseid);
		updateNurseRoomFlagVO.setRoomnum(Integer.parseInt(roomno));
		int flag=nurseService.getNurseRoombyflag(updateNurseRoomFlagVO);
		if(flag>=1) {
			nurseService.updateFlag2(updateNurseRoomFlagVO);
		}
	}
	
}
