package di.gunyoung.nurse.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import di.gunyoung.nurse.dao.NurseDAO;
import model.ChatVO;
import model.CheckRoomVO;
import model.LongTermScheduleVO;
import model.NurseRoomVO;
import model.NurseVO;
import model.PatientVO;
import model.RoomVO;
import model.UpdateNurseRoomFlagVO;

@Service("NurseService")
public class NurseService {
	@Resource(name = "NurseDAO")
	private NurseDAO nurseDAO;
	
	public NurseVO getNurse(NurseVO nurseVO) {
		return nurseDAO.getNurse(nurseVO);
		
	}
	public List<PatientVO> getRoomPatientList(String room){
		return nurseDAO.getRoomPatientList(room);
	}
	public void insertNurse(NurseVO nurseVO) {
		nurseDAO.insertNurse(nurseVO);		
	}
	public List<NurseVO> getNurseList(){
		return nurseDAO.getNurseList();
	}
	public RoomVO getCheckRoom(CheckRoomVO checkRoomVO) {
		return nurseDAO.getCheckRoom(checkRoomVO);		
	}
	public void insertRoom(RoomVO roomVO) {
		nurseDAO.insertRoom(roomVO);	
	}
	public NurseVO getNurse_by_id(String nurseid) {
		return nurseDAO.getNurse_by_id(nurseid);
		
	}
	public RoomVO inserChatRoom(String roomname,int count,String data1,String data2) {
		RoomVO roomVo=new RoomVO();
		roomVo.setCount(count);
		roomVo.setRoomname(roomname);
		insertRoom(roomVo);
		NurseVO nurseVO=getNurse_by_id(data1);
		roomVo=getRoom(roomVo);
		NurseRoomVO nurseRoomVO=new NurseRoomVO();	
		nurseRoomVO.setNurseid(data1);
		nurseRoomVO.setRoomnum(roomVo.getRoomno());
		nurseRoomVO.setToken(nurseVO.getToken());
		insertNurseRoom(nurseRoomVO);
		NurseVO nurseVO2=getNurse_by_id(data2);
		nurseRoomVO.setNurseid(data2);
		nurseRoomVO.setToken(nurseVO2.getToken());
		insertNurseRoom(nurseRoomVO);
		return getRoom(roomVo);
		
	}
	public RoomVO getRoom(RoomVO roomVO) {
		return nurseDAO.getRoom(roomVO);
		
	}
	public void insertNurseRoom(NurseRoomVO nurseRoomVO) {
		nurseDAO.insertNurseRoom(nurseRoomVO);
	}
	public List<RoomVO> getRoomList(String nurseid){
		return nurseDAO.getRoomList(nurseid);
	}
	public List<RoomVO> getChatList(ChatVO chatVO){
		return nurseDAO.getChatList(chatVO);
	}
	public void insertChat(ChatVO chatVO){
		nurseDAO.insertChat(chatVO);
	}
	public RoomVO getRoom2(int roomno) {
		return nurseDAO.getRoom2(roomno);
	}
	public void updateRoom(RoomVO roomVO) {
		nurseDAO.updateRoom(roomVO);
	}
	public void deleteNurseRoom(String roomno){
		nurseDAO.deleteNurseRoom(roomno);
	}
	public void updateNurseRoom(String strNurseId,int roomno) {
		String[] roomsId=strNurseId.split(",");
		NurseRoomVO nurseRoomVO=new NurseRoomVO();
		nurseRoomVO.setRoomnum(roomno);
		for(int i=0;i<roomsId.length;i++) {
			nurseRoomVO.setNurseid(roomsId[i]);
			nurseRoomVO.setToken(getNurse_by_id(roomsId[i]).getToken());
			insertNurseRoom(nurseRoomVO);
		}
	}
	public List<NurseRoomVO> getNurseRoomList(String roomnum){
		return nurseDAO.getNurseRoomList(roomnum);
	}
	public List<PatientVO> getInChargePatientList(String nurseid){
		return nurseDAO.getInChargePatientList(nurseid);
	}
	public List<LongTermScheduleVO> get_long_term_schedule_list_by_id(String longnurseid){
		return nurseDAO.get_long_term_schedule_list_by_id(longnurseid);
	}
	public void updateToken(NurseVO nurseVO) {
		nurseDAO.updateToken(nurseVO);
	}
	public void updateNurseRoomToken(NurseVO nurseVO) {
		nurseDAO.updateNurseRoomToken(nurseVO);
	}
	public int getNurseRoombyflag(UpdateNurseRoomFlagVO updateNurseRoomFlagVO) {
		return nurseDAO.getNurseRoombyflag(updateNurseRoomFlagVO);
	}
	public void updateFlag(UpdateNurseRoomFlagVO updateNurseRoomFlagVO) {
		nurseDAO.updateFlag(updateNurseRoomFlagVO);
	}
	public void updateFlag2(UpdateNurseRoomFlagVO updateNurseRoomFlagVO) {
		nurseDAO.updateFlag2(updateNurseRoomFlagVO);
	}
}
