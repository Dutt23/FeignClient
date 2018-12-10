package sd.dutt.shatyaki.FeignClient.errors;

import org.springframework.core.env.Environment;

import sd.dutt.shatyaki.FeignClient.configuration.ApplicationContextProvider;

public class ErrorResponse {

	private Environment env = ApplicationContextProvider.getBean(Environment.class);

	private String code;
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
