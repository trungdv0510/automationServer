package autoServer.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Configurable
public class handleValideException {
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)  // Nếu validate fail thì trả về 400
	public String handleBindException(BindException e) {
	    // Trả về message của lỗi đầu tiên
	    String errorMessage = "Request không hợp lệ";
	    if (e.getBindingResult().hasErrors())
	       errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
	    return errorMessage;
	}
}
