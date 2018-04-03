package di.gunyoung.nurse.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

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
		String nurseId=request.getParameter("id");
		String password=request.getParameter("password");
		nurseVO.setNurseId(nurseId);
		nurseVO.setPassword(password);
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getNurse(nurseVO)));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString()); 
	}

	@RequestMapping("/room-patient-list")
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
	@RequestMapping("/insert-nurse")
	public void insertNurse(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("insertNurse");
		NurseVO nurseVO=new NurseVO();
		String nurseId=request.getParameter("id");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String birth=request.getParameter("birth");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		String image=request.getParameter("image");
		
		
		nurseVO.setNurseId(nurseId);
		nurseVO.setPassword(password);
		nurseVO.setName(name);
		nurseVO.setBirth(birth);
		nurseVO.setPhone(phone);
		nurseVO.setAddress(address);
		nurseVO.setImage(image);
		nurseVO.setTodaySchedule("Not yet schedule");
		nurseVO.setToken("0");
		
		nurseService.insertNurse(nurseVO);
		
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getNurse(nurseVO)));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString()); 			
	
	}
	@RequestMapping("/nurse-list")
	public void nurseList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("nurseList");
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getNurseList()));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString()); 	
	}
	@RequestMapping("/insert-chat-room")
	public void inserChatRoom(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("inserChatRoom");
		request.setCharacterEncoding("UTF-8");
		
		String roomName=request.getParameter("roomName");
		System.out.println(roomName);
		
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
			JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.inserChatRoom(roomName,count,data1,data2)));
			response.setContentType("application/json; charset=utf-8"); 	
			response.getWriter().print(jsonObject.toString());
		}else {
			System.out.println("원래 있는방");
			JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getRoom(roomVO)));
			response.setContentType("application/json; charset=utf-8"); 	
			response.getWriter().print(jsonObject.toString());
		
		}
	}
	@RequestMapping("/room-list")
	public void roomList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("roomList");
		String nurseId=request.getParameter("nurseId");
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getRoomList(nurseId)));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString()); 
		
	
	}
	@RequestMapping("/chat-list")
	public void chatList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("chatList");
		ChatVO chatVO=new ChatVO();
		String roomNo=request.getParameter("roomNo");
		chatVO.setRoomNo(Integer.parseInt(roomNo));
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getChatList(chatVO)));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString()); 
	
	}
	@RequestMapping("/insert-chat")
	public void insertChat(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("insertChat");
		String roomNo=request.getParameter("roomNo");
		String chatContent=request.getParameter("chatContent");
		String nurseId2=request.getParameter("nurseId2");
		ChatVO chatVO=new ChatVO();
		
		chatVO.setChatContent(chatContent);
		chatVO.setNurseId2(nurseId2);
		chatVO.setRoomNo(Integer.parseInt(roomNo));
		
		nurseService.insertChat(chatVO);
	}
	@RequestMapping("/getRoom2")
	public void getRoom2(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("getRoom2");
		String roomNo=request.getParameter("roomNo");
		
		
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getRoom2(Integer.parseInt(roomNo))));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString());
	
	}
	@RequestMapping("/update-room")
	public void updateRoom(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("updateRoom");
		String roomName=request.getParameter("roomName");
		String roomNo=request.getParameter("roomNo");
		String count=request.getParameter("count");
		String strNurseId=request.getParameter("strNurseId");
		
		RoomVO roomVO=new RoomVO();
		
		roomVO.setRoomNo(Integer.parseInt(roomNo));
		roomVO.setRoomName(roomName);
		roomVO.setCount(Integer.parseInt(count));
		
		nurseService.updateRoom(roomVO);
		nurseService.deleteNurseRoom(roomNo);
		
		nurseService.updateNurseRoom(strNurseId,Integer.parseInt(roomNo));
		
		
	}
	@RequestMapping("/get-nurse-room")
	public void getNurseRoom(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("getNurseRoom");		
		String roomNo=request.getParameter("roomNo");
		
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getNurseRoomList(roomNo)));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString());			
	
	}
	@RequestMapping("/incharge-patient-show")
	public void incharge_patient_show(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("incharge_patient_show");
		String nurseId=request.getParameter("nurseId");		
		
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getInChargePatientList(nurseId)));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString());		
	
	}
	@RequestMapping("/today-schedule-show")
	public void today_schedule_show(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("today_schedule_show");		
		String nurseId=request.getParameter("nurseId");			
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getNurseById(nurseId)));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString());	
	}
	@RequestMapping("/long-term-schedule-show")
	public void long_term_schedule_show(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("long_term_scheduleshow");
		String longNurseId=request.getParameter("nurseId");
		System.out.println(longNurseId);
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getLongTermScheduleListById(longNurseId)));
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString());				
	}
	@RequestMapping("/update-token")
	public void update_token(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("update_token");
		String nurseId=request.getParameter("nurseId");
		String token=request.getParameter("token");
		
		NurseVO nurseVO=new NurseVO();
		nurseVO.setNurseId(nurseId);
		nurseVO.setToken(token);
		
		nurseService.updateToken(nurseVO);
	}
	@RequestMapping("/update-nurseroom-token")
	public void update_nurseroom_token(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("update_nurseroom_token");
		String nurseId=request.getParameter("nurseId");
		String token=request.getParameter("token");
		
		NurseVO nurseVO=new NurseVO();
		nurseVO.setNurseId(nurseId);
		nurseVO.setToken(token);
		nurseService.updateNurseRoomToken(nurseVO);
	}
	@RequestMapping("/get-room-flag")
	public void getRoomFlag(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("getRoomFlag");
		String nurseId=request.getParameter("nurseId");
		String roomNo=request.getParameter("roomNo");
		UpdateNurseRoomFlagVO updateNurseRoomFlagVO=new UpdateNurseRoomFlagVO();
		updateNurseRoomFlagVO.setNurseId(nurseId);
		updateNurseRoomFlagVO.setRoomNum(Integer.parseInt(roomNo));
		int flag=nurseService.getNurseRoombyflag(updateNurseRoomFlagVO);
		updateNurseRoomFlagVO.setFlag(flag+1);
		nurseService.updateFlag(updateNurseRoomFlagVO);				
	}
	@RequestMapping("/get-room-flag2")
	public void getRoomFlag2(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("getRoomFlag2");
		String nurseId=request.getParameter("nurseId");
		String roomNo=request.getParameter("roomNo");
		UpdateNurseRoomFlagVO updateNurseRoomFlagVO=new UpdateNurseRoomFlagVO();
		updateNurseRoomFlagVO.setNurseId(nurseId);
		updateNurseRoomFlagVO.setRoomNum(Integer.parseInt(roomNo));
		int flag=nurseService.getNurseRoombyflag(updateNurseRoomFlagVO);
		if(flag>=1) {
			nurseService.updateFlag2(updateNurseRoomFlagVO);
		}
	}	
	@RequestMapping("/andtest")
	public void andtest(HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.out.println("andtest");
		JsonObject jsonObject=new JsonObject();
		
		jsonObject.addProperty("a", "kim");
		jsonObject.addProperty("b", "gun");
		jsonObject.addProperty("c", "young");
		System.out.println(jsonObject.toString());
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().print(jsonObject.toString()); 		
	}
}
