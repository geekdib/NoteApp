package com.dib.service;

import org.json.JSONObject;

public interface LogService {

	public JSONObject formatLogRequest(String path, Object request, Object params);
	public JSONObject formatLogResponse(String path, Object response);
	
}
