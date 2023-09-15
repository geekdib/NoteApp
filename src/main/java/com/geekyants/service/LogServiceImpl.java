package com.geekyants.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * This component is responsible for formatting LogMessages in a JSON format
 */
@Service
public class LogServiceImpl implements LogService {

	@Override
	public Object formatLogRequest(String path, Object request, Object params) {
		Map<Object, Object> resLog = new HashMap<>();
		resLog.put("path", path);
		if (request != null)
			resLog.put("requestBody", request.toString());
		if (params != null)
			resLog.put("params", params.toString());
		return resLog;
	}

	@Override
	public Object formatLogResponse(String path, Object response) {
		Map<Object, Object> resLog = new HashMap<>();
		resLog.put("path", path);
		if (response != null)
			resLog.put("responseBody", response.toString());
		return resLog;
	}

}
