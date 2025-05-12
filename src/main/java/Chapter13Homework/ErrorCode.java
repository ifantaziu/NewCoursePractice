package Chapter13Homework;

public enum ErrorCode {
    INVALID_INPUT(400, "Invalid input value for file"),
    NOT_FOUND(404, "The server cannot find the requested resource"),
    SERVER_ERROR(503, "The server cannot handle the request, because it is overloaded or down for maintenance");
    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorCode fromCode(int code) {
        for (ErrorCode errorCode : values()) {
            if (errorCode.code == code) return errorCode;
        }
        return null;
    }

    public String getUserMessage() {
        switch (this) {
            case INVALID_INPUT:
                return "Oops! Something went wrong with the input.";
            case NOT_FOUND:
                return "Sorry, we couldn't find what you were looking for.";
            case SERVER_ERROR:
                return "We're experiencing technical difficulties. Please try again later.";
            default:
                return "Everything works perfectly.";
        }
    }
    public String getFixingActions() {
        switch (this) {
            case INVALID_INPUT:
                return "Inspect the underlying exception message, the log files, or both.";
            case NOT_FOUND:
                return "Check server access logs from Cpanel (/var/log/apache2/access.log for Apache) or (/var/log/nginx/access.log for Nginx) to find URLs returning 404 errors.";
            case SERVER_ERROR:
                return "1. Check to see if your server is going through maintenance.\n"
                        + "2. Look through your server and application logs.\n"
                        + "3. Review code for bugs and recent file modifications.\n"
                        + "4. Enable WP_DEBUG in WordPress.\n"
                        + "5. Increase your server resources.\n"
                        + "6. Try a server reboot.\n"
                        + "7. Use a web application firewall.\n"
                        + "8. Scan for malware.";
            default:
                return "No fixing actions needed.";
        }
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}