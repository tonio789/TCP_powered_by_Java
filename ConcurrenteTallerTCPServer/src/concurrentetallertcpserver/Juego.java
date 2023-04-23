package concurrentetallertcpserver;

public class Juego {

    public String palabra = "palabra";
    public char arregloChar[] = palabra.toCharArray();
    public char[] ArregloCharGuion = new char[palabra.length()];

    //llena el arreglo de guiones
    public void llenarArreglo() {
        for (int i = 0; i < palabra.length(); i++) {
            arregloChar[i] = palabra.charAt(i);
            ArregloCharGuion[i] = '_';
        }
    }

    //print de palabra para adivinar
    public String print() {
        String palabraOculta = String.valueOf(ArregloCharGuion);
        
        return "La palabra contiene " + palabra.length() + " letras:" + palabraOculta;
    }

}
