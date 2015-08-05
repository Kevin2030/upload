/*
 * Copyright (C) 1993-2015 Kingdee Inc.All Rights Reserved.
 * 
 * FileName：UploadServlet.java
 * 
 * Description：TODO
 * 
 * History：
 * 1.0 Kai.Zhao 2015年8月5日 Create
 * 1.1 Kai.Zhao 2015年8月5日 TODO
 */
package com.xxx.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 
 * @author Kai.Zhao
 * @version 1.0
 * @see
 */
public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 2791492764425910513L;

	private final String UPLOAD_DIRECTORY = "C:/uploads";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		//process only if its multipart content
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				createDirectory();
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
					}
				}
				//File uploaded successfully
				request.setAttribute("message", "File Uploaded Successfully");
			} catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to " + ex);
			}
		} else {
			request.setAttribute("message", "Sorry this Servlet only handles file upload request");
		}
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

	private void createDirectory() {
		File file = new File(UPLOAD_DIRECTORY);
		if (!file.exists()) {
			file.mkdir();
		}
	}

}
