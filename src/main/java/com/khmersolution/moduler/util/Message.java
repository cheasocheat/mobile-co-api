package com.khmersolution.moduler.util;


import java.awt.*;

public enum Message {
	
	   NO_DATA("No data fetch from database"),	
	   INTERNAL_SERVER_ERROR("Internal server error"),	
	   REQUEST_MESSAGE_MANDATORY("RequestMessage is mandatory"),
	   SERVICE_HEADER_MANDATORY("ServiceHeader is mandatory"),
	   THIRDPARTY_MANDATORY("Third Party is mandatory"),
	   THIRDPARTY_NOT_FOUND("Unknown Third Party"),
	   USERNAME_MANDATORY("Username is mandatory"),
	   PASSWORD_MANDATORY("Password is mandatory"),
	   USERNAME_NOT_FOUND("Unknown username"),
	   TOKEN_ID_NOT_FOUND("Token id is mandatory"),
	   TOKEN_ID_INVALID("Token id invalid"),
	   OTHER_ERROR("Other error"),
	   CONTRACT_NOT_FOUND("Reference number is mandatory"),
	   CONTRACT_LENGTH_NOT_CORRECT("Reference number should be 8 digits"),
	   UNKNOWN_CONTRACT("Unknown reference number"),
	   PAID_AMOUNT_NOT_MATCH("Paid Not Match With Amount"),
	   SUCCESSED("Transaction success"),
	   CUSTMER_CODE_NOT_FOUND("Customer code not found"),
	   CUSTMER_CODE_LENGTH_NOT_CORRECT("customer code sholud be 9 digits"),
	   CUSTMER_CODE_INVALID("Customer code invalid");

	   /** Message type */
	   private TrayIcon.MessageType type;
	   
	   /** Message text */
	   private String text;

	   /**
	    * @param text
	    */
	   private Message(String text) {
	      this.text = text;
	      this.type = TrayIcon.MessageType.ERROR;
	   }

	   /**
	    * @return MessageType
	    */
	   public TrayIcon.MessageType getType() {
	      return type;
	   }

	   /**
	    * @return text as string
	    */
	   public String getText() {
	      return text;
	   }

	   /**
	    * @param text
	    * @return Message
	    */
	   public static Message fromText(String text) {
	      if (text != null) {
	         for (Message message : values()) {
	            if (message.getText().equals(text)) {
	               return message;
	            }
	         }
	      }
	      return null;
	   }

}
