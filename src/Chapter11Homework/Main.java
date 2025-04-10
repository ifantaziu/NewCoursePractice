package Chapter11Homework;

public class Main {
    public static void main(String[] args) {
        GreetingService greetingService = new GreetingService() {
            @Override
            public void greet(String name) {
                System.out.println("Hey " + name + "! Nice to see you!");
            }
        };
        greetingService.greet("Maria");
    }
}
