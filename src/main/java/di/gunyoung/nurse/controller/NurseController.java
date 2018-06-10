package di.gunyoung.nurse.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@PostMapping("/login")
	public @ResponseBody String login(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,Object> data) throws IOException{
		request.setCharacterEncoding("UTF-8");
		NurseVO nurseVO=new NurseVO();		
		String nurseId=data.get("id").toString();
		String password=data.get("password").toString();
		nurseVO.setNurseId(nurseId);
		nurseVO.setPassword(password);
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getNurse(nurseVO)));
		 	
		return jsonObject.toString(); 
	}

	@PostMapping("/room-patient-list")
	public @ResponseBody String roomPatientList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("roomPatientList");
		String room=request.getParameter("room");
		room=room.replaceAll(" ", "%");
		System.out.println(room);
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getRoomPatientList(room)));

		System.out.println(jsonObject.toString());
		return jsonObject.toString();  
	}
	@PostMapping("/insert-nurse")
	public @ResponseBody String insertNurse(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,Object> data) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("insertNurse");
		NurseVO nurseVO=new NurseVO();
		
		String nurseId=data.get("id").toString();
		String password=data.get("password").toString();
		String name=data.get("name").toString();
		String birth=data.get("birth").toString();
		String phone=data.get("phone").toString();
		String address=data.get("address").toString();
		String image=data.get("image").toString();
		
		
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
		return jsonObject.toString(); 
	
	}
	@GetMapping("/nurse-list")
	public @ResponseBody String nurseList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("nurseList");
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getNurseList()));
		return jsonObject.toString(); 
	}
	@PostMapping("/insert-chat-room")
	public @ResponseBody String inserChatRoom(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,Object> data) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("inserChatRoom");
		request.setCharacterEncoding("UTF-8");
		
		String roomName=data.get("roomName").toString();
		System.out.println(roomName);
		
		int count=Integer.parseInt(data.get("count").toString());
		System.out.println(count);
		
		String data1=data.get("data1").toString();
		System.out.println(data1);
		String data2=data.get("data2").toString();
		System.out.println(data2);
		
		RoomVO roomVO=new RoomVO();
		CheckRoomVO checkRoomVO=new CheckRoomVO();
		checkRoomVO.setData1(data1);
		checkRoomVO.setData2(data2);
		roomVO=nurseService.getCheckRoom(checkRoomVO);
		if(roomVO==null){
			JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.inserChatRoom(roomName,count,data1,data2)));
			return jsonObject.toString(); 
		}else {
			System.out.println("원래 있는방");
			JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getRoom(roomVO)));
			return jsonObject.toString(); 
		
		}
	}
	@PostMapping("/room-list")
	public @ResponseBody String roomList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("roomList");
		String nurseId=request.getParameter("nurseId");
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getRoomList(nurseId)));
		return jsonObject.toString(); 
	}
	@PostMapping("/chat-list")
	public @ResponseBody String chatList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("chatList");
		ChatVO chatVO=new ChatVO();
		String roomNo=request.getParameter("roomNo");
		chatVO.setRoomNo(Integer.parseInt(roomNo));
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getChatList(chatVO)));
		return jsonObject.toString(); 
	}
	@PostMapping("/insert-chat")
	public @ResponseBody void insertChat(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,Object> data) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("insertChat");
		
		String roomNo=data.get("roomNo").toString();request.getParameter("roomNo");
		String chatContent=data.get("chatContent").toString();
		String nurseId2=data.get("nurseId2").toString();
		ChatVO chatVO=new ChatVO();
		
		chatVO.setChatContent(chatContent);
		chatVO.setNurseId2(nurseId2);
		chatVO.setRoomNo(Integer.parseInt(roomNo));
		
		nurseService.insertChat(chatVO);
	}
	@PostMapping("/getRoom2")
	public @ResponseBody String getRoom2(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("getRoom2");
		String roomNo=request.getParameter("roomNo");
		
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getRoom2(Integer.parseInt(roomNo))));
		return jsonObject.toString(); 
	
	}
	@PostMapping("/update-room")
	public @ResponseBody void updateRoom(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,Object> data) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("updateRoom");
		String roomName=data.get("roomName").toString();
		String roomNo=data.get("roomNo").toString();
		String count=data.get("count").toString();
		String strNurseId=data.get("strNurseId").toString();
		
		RoomVO roomVO=new RoomVO();
		
		roomVO.setRoomNo(Integer.parseInt(roomNo));
		roomVO.setRoomName(roomName);
		roomVO.setCount(Integer.parseInt(count));
		
		nurseService.updateRoom(roomVO);
		nurseService.deleteNurseRoom(roomNo);
		
		nurseService.updateNurseRoom(strNurseId,Integer.parseInt(roomNo));
	}
	@PostMapping("/get-nurse-room")
	public @ResponseBody String getNurseRoom(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("getNurseRoom");		
		String roomNo=request.getParameter("roomNo");
		
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getNurseRoomList(roomNo)));
		return jsonObject.toString(); 	
	
	}
	@PostMapping("/incharge-patient-show")
	public @ResponseBody String incharge_patient_show(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("incharge_patient_show");
		String nurseId=request.getParameter("nurseId");		
		
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getInChargePatientList(nurseId)));
		return jsonObject.toString(); 	
	
	}
	@PostMapping("/today-schedule-show")
	public @ResponseBody String today_schedule_show(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("today_schedule_show");		
		String nurseId=request.getParameter("nurseId");			
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getNurseById(nurseId)));
		return jsonObject.toString(); 
	}
	@PostMapping("/long-term-schedule-show")
	public @ResponseBody String long_term_schedule_show(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("long_term_scheduleshow");
		String longNurseId=request.getParameter("nurseId");
		System.out.println(longNurseId);
		JSONObject jsonObject=JSONObject.fromObject(JsonResult.success(nurseService.getLongTermScheduleListById(longNurseId)));
		return jsonObject.toString(); 		
	}
	@PostMapping("/update-token")
	public @ResponseBody void update_token(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,Object> data) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("update_token");
		String nurseId=data.get("nurseId").toString();
		String token=data.get("token").toString();
		
		NurseVO nurseVO=new NurseVO();
		nurseVO.setNurseId(nurseId);
		nurseVO.setToken(token);
		
		nurseService.updateToken(nurseVO);
	}
	@PostMapping("/update-nurseroom-token")
	public @ResponseBody void update_nurseroom_token(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,Object> data) throws IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("update_nurseroom_token");
		String nurseId=data.get("nurseId").toString();
		String token=data.get("token").toString();
		
		NurseVO nurseVO=new NurseVO();
		nurseVO.setNurseId(nurseId);
		nurseVO.setToken(token);
		nurseService.updateNurseRoomToken(nurseVO);
	}
	@PostMapping("/get-room-flag")
	public @ResponseBody void getRoomFlag(HttpServletRequest request,HttpServletResponse response) throws IOException{
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
	@PostMapping("/get-room-flag2")
	public @ResponseBody void getRoomFlag2(HttpServletRequest request,HttpServletResponse response) throws IOException{
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
	public @ResponseBody String andtest(HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.out.println("andtest");
		JsonObject jsonObject=new JsonObject();
		
		jsonObject.addProperty("a", "kim");
		jsonObject.addProperty("b", "gun");
		jsonObject.addProperty("c", "young");
		System.out.println(jsonObject.toString());
		return jsonObject.toString(); 
	}
}
