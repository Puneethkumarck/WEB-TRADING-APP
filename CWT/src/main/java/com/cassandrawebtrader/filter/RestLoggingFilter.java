package com.cassandrawebtrader.filter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicLong;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import com.cassandrawebtrader.util.RequestCachingRequestWrapper;
import com.cassandrawebtrader.util.ResponseWrapper;

/**
 * @author puneethkumar
 * 
 * This class is responsible for logging Restful Web Service request and response
 *
 */
public class RestLoggingFilter extends OncePerRequestFilter{
	
	private static Logger logger = LoggerFactory.getLogger(RestLoggingFilter.class);
	private static final String REQUEST_PREFIX = "Incoming Request Message : ";
	private static final String RESPONSE_PREFIX = "Outgoing Response Message : ";
	private AtomicLong id = new AtomicLong(1);


	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		long requestId = id.incrementAndGet();
		boolean isFirstRequest = !isAsyncDispatch(request);
		
		if (isFirstRequest) {
			request = new RequestCachingRequestWrapper(requestId, request);
			response = new ResponseWrapper(requestId, response);
		}
		
		try {
			filterChain.doFilter(request, response);
			// response.flushBuffer();
		} finally {
			if (!isAsyncStarted(request)) {
				logResponse((ResponseWrapper) response);
				logRequest(request);
			}
		}
		
	}
	
	private void logRequest(final HttpServletRequest request) {
		StringBuilder msg = new StringBuilder();
		msg.append(REQUEST_PREFIX);
		if (request instanceof RequestCachingRequestWrapper) {
			msg.append("request id=").append(((RequestCachingRequestWrapper) request).getId()).append("; ");
		}
		HttpSession session = request.getSession(false);
		if (session != null) {
			msg.append("session id=").append(session.getId()).append("; ");
		}
		if (request.getMethod() != null) {
			msg.append("method=").append(request.getMethod()).append("; ");
		}
		if (request.getContentType() != null) {
			msg.append("content type=").append(request.getContentType()).append("; ");
		}
		msg.append("uri=").append(request.getRequestURI());
		if (request.getQueryString() != null) {
			msg.append('?').append(request.getQueryString());
		}

		if (request instanceof RequestCachingRequestWrapper && !isMultipart(request) && !isBinaryContent(request)) {
			RequestCachingRequestWrapper wrapper = (RequestCachingRequestWrapper) request;
			byte[] buf = wrapper.toByteArray();
			if (buf.length > 0) {
				int length = Math.min(buf.length, 10000);
				String payload;
				try {
					payload = new String(buf, 0, length, wrapper.getCharacterEncoding());
				} catch (UnsupportedEncodingException e) {
					payload = "[unknown]";
				}
				msg.append(";payload=").append(payload);
			}
		}
		logger.info(msg.toString());
	}

	
	private boolean isBinaryContent(final HttpServletRequest request) {
		if (request.getContentType() == null) {
			return false;
		}
		return request.getContentType().startsWith("image") || request.getContentType().startsWith("video")
				|| request.getContentType().startsWith("audio");
	}

	private boolean isMultipart(final HttpServletRequest request) {
		return request.getContentType() != null && request.getContentType().startsWith("multipart/form-data");
	}

	private void logResponse(final ResponseWrapper response) {
		StringBuilder msg = new StringBuilder();
		msg.append(RESPONSE_PREFIX);
		msg.append("request id=").append((response.getId()));
		msg.append("Content Type=").append((response.getContentType()));
		try {
			msg.append("; payload=").append(new String(response.toByteArray(), response.getCharacterEncoding()));
		} catch (UnsupportedEncodingException e) {
			logger.warn("Failed to parse response payload", e);
		}
		logger.info(msg.toString());
	}
}
