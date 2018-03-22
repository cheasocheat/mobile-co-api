package com.khmersolution.moduler.util;


import com.khmersolution.moduler.domain.statistic.ResponseBody;

import java.util.ArrayList;
import java.util.List;

public final class ResponseFactoryBuilder {

	public static <T> ResponseBody<T> build(List<T> data){
		ResponseBody<T> responseBody=new ResponseBody<T>();
		try{
			
			if(data!=null&&!data.isEmpty()){
				responseBody.setMessage(Message.SUCCESSED.getText());
				responseBody.setCode(StatusMessage.OK.getStatusCode());
				responseBody.setData(data);
			}else{
				responseBody.setMessage(Message.NO_DATA.getText());
				responseBody.setCode(StatusMessage.OK.getStatusCode());
				responseBody.setData(new ArrayList<T>());
			}
			
		}catch(Exception e){
				responseBody.setMessage(Message.INTERNAL_SERVER_ERROR.getText());
				responseBody.setCode(StatusMessage.INTERNAL_SERVER_ERROR.getStatusCode());
				responseBody.setData(new ArrayList<T>());
				e.printStackTrace();
		}
		return responseBody;
	}
	
	
	public static <T> ResponseBody<T> success(List<T> data){
		ResponseBody<T> responseBody=new ResponseBody<T>();
		responseBody.setMessage(Message.SUCCESSED.getText());
		responseBody.setCode(StatusMessage.OK.getStatusCode());
		responseBody.setData(data);
		return responseBody;
	}
	
	public static <T> ResponseBody<T> fail(){
		ResponseBody<T> responseBody=new ResponseBody<T>();
		responseBody.setMessage(Message.INTERNAL_SERVER_ERROR.getText());
		responseBody.setCode(StatusMessage.INTERNAL_SERVER_ERROR.getStatusCode());
		responseBody.setData(new ArrayList<T>());
		return responseBody;
	}
	
	
}
