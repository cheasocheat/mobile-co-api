package com.khmersolution.moduler.domain.statistic;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "ResponseBody", description = "Responsebody model" )
public class ResponseBody<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("RSLT_MSG")
	@ApiModelProperty(value = "Response message from server",required = true)
	private String message;
	
	@JsonProperty("RSLT_CD")
	@ApiModelProperty(value = "Response code from server",required = true)
	private int code;
	
	@JsonProperty("RSLT_DATA")
	@ApiModelProperty( value = "Response data from server",required = true)
	private List<T> data;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
}
