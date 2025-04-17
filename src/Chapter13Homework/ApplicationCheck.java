package Chapter13Homework;

public class ApplicationCheck {
    public static void main(String[] args) {
        ErrorCode errorCode = ErrorCode.fromCode(400);
        if (errorCode != null) {
            System.out.println("Code: " + errorCode.getCode());
            System.out.println("System message: " + errorCode.getMessage());
            System.out.println("User message: " + errorCode.getUserMessage());
            if (errorCode == ErrorCode.SERVER_ERROR) {
                System.out.println("Error detected!");
            }
            System.out.println("Actions to do for fixing the error:\n" + errorCode.getFixingActions());
        } else {
            System.out.println("Unknown error code.");
        }
    }
}