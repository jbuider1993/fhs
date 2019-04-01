package com.fhs.fileService.ueditor.upload;

import com.fhs.core.config.EConfig;
import com.fhs.fileService.ueditor.define.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class Uploader {
	private HttpServletRequest request = null;
	private Map<String, Object> conf = null;

	/**
	 * 文件服务域名
	 */
	public static String basePath =  EConfig.getPathPropertiesValue("fileServiceUrl");

	/**
	 * 文件储存路径
	 */
	public static String fileSavePath  = EConfig.getPathPropertiesValue("saveFilePath");;

	public Uploader(HttpServletRequest request, Map<String, Object> conf) {
		this.request = request;
		this.conf = conf;
	}

	public final State doExec() {
		String filedName = (String) this.conf.get("fieldName");
		State state = null;

		if ("true".equals(this.conf.get("isBase64"))) {
			state = Base64Uploader.save(this.request.getParameter(filedName),
					this.conf);
		} else {
			state = (State)BinaryUploader.save(this.request, this.conf, fileSavePath,basePath);
		}
		return state;
	}
}
