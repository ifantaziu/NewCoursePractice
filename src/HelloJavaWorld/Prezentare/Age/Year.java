package HelloJavaWorld.Prezentare.Age;

import HelloJavaWorld.Prezentare.Age.Age;
class AnulNasterii {
    public static void main(String[] args) {
        Age persoana = new Age();
        int anulCurent = 2025;
        int varsta = persoana.getVarsta();
        int AnulNasterii = calculeazaAnulNasterii(anulCurent, varsta);
        System.out.println("Anul nașterii este: " + AnulNasterii);
    }

    public static int calculeazaAnulNasterii(int anulCurent, int varsta) {
        return anulCurent - varsta;
    }
}