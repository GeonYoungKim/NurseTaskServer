package model;

public class JsonResult {
	private String result;
	private String message;
	private Object data;
	
	private JsonResult(){
		
	}
	public static JsonResult success(Object data){
		JsonResult jsonResult=new JsonResult();
		jsonResult.result="success";
		jsonResult.message=null;
		jsonResult.data=data;
		return jsonResult;
	}
	public static JsonResult fail(String message){
		JsonResult jsonResult=new JsonResult();
		jsonResult.result="fail";
		jsonResult.message=message;
		jsonResult.data=null;
		return jsonResult;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
