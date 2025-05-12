package HelloJavaWorld.Prezentare;

public class Person
{
    String FirstName;
public void setFirstName(String FirstName){
    this.FirstName = FirstName;}
    public String getFirstName() {
    return FirstName;
    }
    public static void main (String[] args) {
    Person person = new Person();
    person.setFirstName("Irina");
    {System.out.println("My name is " + person.getFirstName());

    }


    }
}
