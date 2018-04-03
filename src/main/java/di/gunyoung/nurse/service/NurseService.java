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
	public NurseVO getNurseById(String nurseid) {
		return nurseDAO.getNurseById(nurseid);
		
	}
	public RoomVO inserChatRoom(String roomname,int count,String data1,String data2) {
		RoomVO roomVo=new RoomVO();
		roomVo.setCount(count);
		roomVo.setRoomName(roomname);
		insertRoom(roomVo);
		NurseVO nurseVO=getNurseById(data1);
		roomVo=getRoom(roomVo);
		NurseRoomVO nurseRoomVO=new NurseRoomVO();	
		nurseRoomVO.setNurseId(data1);
		nurseRoomVO.setRoomNum(roomVo.getRoomNo());
		nurseRoomVO.setToken(nurseVO.getToken());
		insertNurseRoom(nurseRoomVO);
		NurseVO nurseVO2=getNurseById(data2);
		nurseRoomVO.setNurseId(data2);
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
		nurseRoomVO.setRoomNum(roomno);
		for(int i=0;i<roomsId.length;i++) {
			nurseRoomVO.setNurseId(roomsId[i]);
			nurseRoomVO.setToken(getNurseById(roomsId[i]).getToken());
			insertNurseRoom(nurseRoomVO);
		}
	}
	public List<NurseRoomVO> getNurseRoomList(String roomnum){
		return nurseDAO.getNurseRoomList(roomnum);
	}
	public List<PatientVO> getInChargePatientList(String nurseid){
		return nurseDAO.getInChargePatientList(nurseid);
	}
	public List<LongTermScheduleVO> getLongTermScheduleListById(String longnurseid){
		return nurseDAO.getLongTermScheduleListById(longnurseid);
	}
	public void updateToken(NurseVO nurseVO) {
		nurseDAO.updateToken(nurseVO);
	}
	public void updateNurseRoomToken(NurseVO nurseVO) {
		nurseDAO.updateNurseRoomToken(nurseVO);
	}
	public int getNurseRoombyflag(UpdateNurseRoomFlagVO updateNurseRoomFlagVO) {
		return nurseDAO.getNurseRoomByFlag(updateNurseRoomFlagVO);
	}
	public void updateFlag(UpdateNurseRoomFlagVO updateNurseRoomFlagVO) {
		nurseDAO.updateFlag(updateNurseRoomFlagVO);
	}
	public void updateFlag2(UpdateNurseRoomFlagVO updateNurseRoomFlagVO) {
		nurseDAO.updateFlag2(updateNurseRoomFlagVO);
	}
}
