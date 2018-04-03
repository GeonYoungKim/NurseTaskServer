package di.gunyoung.nurse.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import di.gunyoung.nurse.connect.ConnectDB;
import model.ChatVO;
import model.CheckRoomVO;
import model.LongTermScheduleVO;
import model.NurseRoomVO;
import model.NurseVO;
import model.PatientVO;
import model.RoomVO;
import model.UpdateNurseRoomFlagVO;

@SuppressWarnings("unchecked")
@Repository("NurseDAO")
public class NurseDAO extends ConnectDB{
	
	public NurseVO getNurse(NurseVO nurseVO) {
		return (NurseVO)selectOne("Nurse.getNurse",nurseVO);
		
	}
	public List<PatientVO> getRoomPatientList(String room){
		return (List<PatientVO>)selectList("Patient.getRoomPatientList",room);
	}
	public void insertNurse(NurseVO nurseVO) {
		insert("Nurse.insertNurse", nurseVO);
	}
	public List<NurseVO> getNurseList(){
		return (List<NurseVO>)selectList("Nurse.getNurseList");
	}
	public RoomVO getCheckRoom(CheckRoomVO checkRoomVO) {
		return (RoomVO)selectOne("ChatRoom.getCheckRoom",checkRoomVO);		
	}
	public void insertRoom(RoomVO roomVO) {
		insert("Room.insertRoom",roomVO);	
	}
	public NurseVO getNurseById(String nurseid) {
		return (NurseVO)selectOne("Nurse.getNurse_by_id",nurseid);		
	}
	public RoomVO getRoom(RoomVO roomVO) {
		return (RoomVO)selectOne("Room.getRoom",roomVO);		
		
	}
	public void insertNurseRoom(NurseRoomVO nurseRoomVO) {
		insert("NurseRoom.insertNurseRoom",nurseRoomVO);
	}
	public List<RoomVO> getRoomList(String nurseid){
		return (List<RoomVO>)selectList("Room.getRoomList",nurseid);
	}
	public List<RoomVO> getChatList(ChatVO chatVO){
		return (List<RoomVO>)selectList("Chat.getChatList",chatVO);
	}
	public void insertChat(ChatVO chatVO){
		insert("Chat.insertChat",chatVO);
	}
	public RoomVO getRoom2(int roomno) {
		return (RoomVO)selectOne("Room.getRoom2",roomno);
	}
	public void updateRoom(RoomVO roomVO) {
		update("Room.updateRoom",roomVO);
	}
	public void deleteNurseRoom(String roomno){
		delete("NurseRoom.deleteNurseRoom", roomno);
	}
	public List<NurseRoomVO> getNurseRoomList(String roomnum){
		return (List<NurseRoomVO>)selectList("NurseRoom.getNurseRoomList",roomnum);
	}
	public List<PatientVO> getInChargePatientList(String nurseid){
		return (List<PatientVO>)selectList("InChargePatient.getInChargePatientList",nurseid);
	}
	public List<LongTermScheduleVO> getLongTermScheduleListById(String longnurseid){
		return (List<LongTermScheduleVO>)selectList("LongTermSchedule.get_long_term_schedule_list_by_id",longnurseid);
	}
	public void updateToken(NurseVO nurseVO) {
		update("Nurse.updateToken",nurseVO);
	}
	public void updateNurseRoomToken(NurseVO nurseVO) {
		update("NurseRoom.updateNurseRoomToken",nurseVO);
	}
	public int getNurseRoomByFlag(UpdateNurseRoomFlagVO updateNurseRoomFlagVO) {
		return (int)selectOne("NurseRoom.getNurseRoombyflag",updateNurseRoomFlagVO);
	}
	public void updateFlag(UpdateNurseRoomFlagVO updateNurseRoomFlagVO) {
		update("NurseRoom.updateFlag",updateNurseRoomFlagVO);
	}
	public void updateFlag2(UpdateNurseRoomFlagVO updateNurseRoomFlagVO) {
		update("NurseRoom.updateFlag2",updateNurseRoomFlagVO);
	}

}
