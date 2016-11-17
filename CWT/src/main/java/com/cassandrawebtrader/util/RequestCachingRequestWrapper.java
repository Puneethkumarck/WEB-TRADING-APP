package com.cassandrawebtrader.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.web.util.WebUtils;

/**
 * @author puneethkumar
 * 
 * HttpServlet Request Wrapper
 *
 */
public class RequestCachingRequestWrapper extends HttpServletRequestWrapper{
	
	private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

	private final ServletInputStream inputStream;
	
	private long id;

	private BufferedReader reader;
	
	public long getId() {
		return id;
	}
	

	public void setId(long id) {
		this.id = id;
	}

	public RequestCachingRequestWrapper(Long requestId,HttpServletRequest request) throws IOException {
		super(request);
		this.inputStream = new RequestCachingInputStream(request.getInputStream());
		this.id=requestId;
	}


	@Override
	public ServletInputStream getInputStream() throws IOException {
		return inputStream;
	}

	@Override
	public String getCharacterEncoding() {
		return super.getCharacterEncoding() != null ? super.getCharacterEncoding() :
				WebUtils.DEFAULT_CHARACTER_ENCODING;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		if (this.reader == null) {
			this.reader = new BufferedReader(new InputStreamReader(inputStream, getCharacterEncoding()));
		}
		return this.reader;
	}

	public byte[] toByteArray() {
		return this.bos.toByteArray();
	}
	
	private class RequestCachingInputStream extends ServletInputStream {

		private final ServletInputStream is;

		private RequestCachingInputStream(ServletInputStream is) {
			this.is = is;
		}

		@Override
		public int read() throws IOException {
			int ch = is.read();
			if (ch != -1) {
				bos.write(ch);
			}
			return ch;
		}

		@Override
		public boolean isFinished() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isReady() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setReadListener(ReadListener arg0) {
			// TODO Auto-generated method stub
			
		}

	}
}
