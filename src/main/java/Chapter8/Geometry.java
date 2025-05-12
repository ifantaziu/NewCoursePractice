package Chapter8;

    public class Geometry {
        static int calcArea(int latura) {
            return latura * latura;
        }

        static int calcArea(int lungime, int latime) {
            return lungime * latime;
        }

        static double calcArea(double raza) {
            return Math.PI * raza * raza;
        }

        public static void main(String[] args) {
            System.out.println("Aria pătratului: " + Geometry.calcArea(20));
            System.out.println("Aria dreptunghiului: " + Geometry.calcArea(20, 9));
            System.out.println("Aria cercului: " + Math.round(Geometry.calcArea(5.5)* 1000.0) / 1000.0);
                   }
    }
