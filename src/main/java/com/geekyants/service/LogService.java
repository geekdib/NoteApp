package com.geekyants.service;

public interface LogService {

	public Object formatLogRequest(String path, Object request, Object params);

	public Object formatLogResponse(String path, Object response);

}
