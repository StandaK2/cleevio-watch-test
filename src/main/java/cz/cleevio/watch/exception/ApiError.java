package cz.cleevio.watch.exception;

import org.springframework.http.HttpStatus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ApiError {

    private HttpStatus status;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private List<ApiValidationError> apiValidationErrors;

    public ApiError() {
    }

    public ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
    }

    public List<ApiValidationError> getApiValidationErrors() {
        return apiValidationErrors;
    }

    public void setApiValidationErrors(List<ApiValidationError> apiValidationErrors) {
        this.apiValidationErrors = apiValidationErrors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

}