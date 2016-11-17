package com.cassandrawebtrader.util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @author puneethkumar
 * 
 *         HTTP Servlet response Wrapper
 *
 */
public class ResponseWrapper extends HttpServletResponseWrapper {

	private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
	private PrintWriter writer = new PrintWriter(bos);
	private long id;

	public ResponseWrapper(Long requestId, HttpServletResponse response) {
		super(response);
		this.id = requestId;
	}

	@Override
	public ServletResponse getResponse() {
		return this;
	}

/*	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return new ServletOutputStream() {

			private TeeOutputStream tee = new TeeOutputStream(
					ResponseWrapper.super.getOutputStream(), bos);

			@Override
			public void write(int b) throws IOException {
				tee.write(b);
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setWriteListener(WriteListener arg0) {
				// TODO Auto-generated method stub

			}

		};
	}
*/
	@Override
	public PrintWriter getWriter() throws IOException {
		return new TeePrintWriter(super.getWriter(), writer);
	}

	public byte[] toByteArray() {
		return bos.toByteArray();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
	private static class TeePrintWriter extends PrintWriter {

		PrintWriter branch;

		public TeePrintWriter(PrintWriter main, PrintWriter branch) {
			super(main, true);
			this.branch = branch;
		}

		public void write(char buf[], int off, int len) {
			super.write(buf, off, len);
			super.flush();
			branch.write(buf, off, len);
			branch.flush();
		}

		public void write(String s, int off, int len) {
			super.write(s, off, len);
			super.flush();
			branch.write(s, off, len);
			branch.flush();
		}

		public void write(int c) {
			super.write(c);
			super.flush();
			branch.write(c);
			branch.flush();
		}

		public void flush() {
			super.flush();
			branch.flush();
		}

	}


}
