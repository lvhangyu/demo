package com.forezp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.io.IOException;
import java.nio.charset.Charset;


public class CustomErrorHandler implements ResponseErrorHandler {
	private static Logger logger = LoggerFactory.getLogger(ResponseErrorHandler.class);
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		HttpStatus statusCode = response.getStatusCode();
	    if(statusCode.is5xxServerError()){
			throw new RestClientException("Unknown status code [" + statusCode + "]");
		}else{
			return;
		}
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return hasError(getHttpStatusCode(response));
	}
	
	protected boolean hasError(HttpStatus statusCode) {
		return (statusCode.series() == HttpStatus.Series.CLIENT_ERROR ||
				statusCode.series() == HttpStatus.Series.SERVER_ERROR);
	}

	protected HttpStatus getHttpStatusCode(ClientHttpResponse response) throws IOException {
		try {
			return response.getStatusCode();
		}
		catch (IllegalArgumentException ex) {
			logger.error("IllegalArgumentException:{}", ex);
			throw new UnknownHttpStatusCodeException(response.getRawStatusCode(), response.getStatusText(),
					response.getHeaders(), getResponseBody(response), getCharset(response));
		}
	}
	
	protected Charset getCharset(ClientHttpResponse response) {
		HttpHeaders headers = response.getHeaders();
		MediaType contentType = headers.getContentType();
		return (contentType != null ? contentType.getCharset() : null);
	}
	
	protected byte[] getResponseBody(ClientHttpResponse response) {
		try {
			return FileCopyUtils.copyToByteArray(response.getBody());
		}
		catch (IOException ex) {
			logger.error("IOException:{}", ex);
			// ignore
		}
		return new byte[0];
	}
}
