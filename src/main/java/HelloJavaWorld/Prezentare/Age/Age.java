package HelloJavaWorld.Prezentare.Age;


public class Age {
    private int varsta;

    public Age() {
        this.varsta = 29;
    }

    public int getVarsta() {
        return varsta;
    }
    public static void main(String[] args) {
        Age persoana = new Age();
        System.out.println("Varsta mea este " + persoana.getVarsta());
    }
}