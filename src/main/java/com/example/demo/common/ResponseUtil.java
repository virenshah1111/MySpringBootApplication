package com.example.demo.common;

public class ResponseUtil<T> {

	private T data;
	private String message;

	public ResponseUtil() {
	}

	public ResponseUtil(String message) {
		this.message = message;
	}

	public ResponseUtil(T data, String message) {
		this.data = data;
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
