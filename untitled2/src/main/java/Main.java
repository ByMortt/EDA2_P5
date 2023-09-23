import java.util.HashMap;
import java.util.Scanner;

public class Main {
    // This is the main class
    public static void main(String[] args) {
        // create a new hash map
        HashMap<String, Integer> map = new HashMap<>();
        // add some values
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        // print the map
        System.out.println(map);
        // print the value of key "a"
        System.out.println(map.get("a"));
        // use the keySet method to get a set of keys
        System.out.println(map.keySet());
        // use the containsValue method to check if a value exists
        System.out.println(map.containsValue(1));
        // create a new hash map
        HashMap<String, Integer> map2 = new HashMap<>();
        // add some values
        map2.put("d", 4);
        map2.put("e", 5);
        map2.put("f", 6);
        // use the equals method to check if two maps are equal
        System.out.println(map.equals(map2));
        // use the remove method to remove a key
        map.remove("a");
        // print the map
        System.out.println(map);
        // use the size method to get the size of the map
        System.out.println(map.size());

        //ejercicio 2

        //solicitar al usuario mediante Scanner 10 números enteros de 4 dígitos (exclusivamente), y realizar la función hash cuadrado medio para cada uno de ellos para obtener la posición en la tabla hash de 25 posiciones (para ello complementar con la función hash modulo de 25).
        Scanner sc = new Scanner(System.in);
        int[] numeros = new int[25];
        HashMap<Integer, Integer> mapClaves = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            System.out.println("Ingrese un número entero de 4 dígitos");
            int numero = sc.nextInt();
            if (numero > 999 && numero < 10000) {
                numeros[i] = numero;
                int clave = hashModulo(hashCuadradoMedio(numero, 4), 25);
                // en caso de colisión, se suma 1 a la clave, caso contrario se agrega la clave y el número
                if (mapClaves.containsKey(clave)) {
                    clave++;
                    if (clave > 26) {
                        while (mapClaves.containsKey(clave)) {
                            clave = clave-1;
                        }
                    }
                    mapClaves.put(clave, numero);
                }
                // en caso de no haber colisión, se agrega la clave y el número
                mapClaves.put(clave, numero);
            } else {
                System.out.println("El número ingresado no es de 4 dígitos");
                i--;
            }
        }
        System.out.println(mapClaves);
        //checar si todos los números están en la tabla mediante su clave
        for (int i = 1; i <= 10; i++) {
            int clave = hashModulo(hashCuadradoMedio(numeros[i], 4), 25);
            if (mapClaves.containsKey(clave)) {
                System.out.println("El número " + numeros[i] + " está en la tabla, tiene la clave " + clave);
            } else {
                System.out.println("El número " + numeros[i] + " no está en la tabla");
            }
        }
        //el objeto sc se resetea
        sc.reset();
        //ejercicio 3
        //crear un arreglo bidimensional de 10 elementos ya que las colisiones se resuelven con encadenamiento
        int[][] tabla = new int[11][11];
        // solicitar al usuario 10 numeros de 9 digitos y realizar la función hash plegamiento para ccada uno de ellos para obtener la posición en el arreglo bidimensional de 10x10, en caso de colision, usar el metodo de encadenamiento por ejemplo si la clave es 5 y ya existe un valor en esa posición, se guarda en [5][1], y así sucesivamente
        for (int i = 1; i <= 10; i++) {
            System.out.println("Ingrese un número entero de 9 dígitos");
            int numero = sc.nextInt();
            if (numero > 99999999 && numero < 1000000000) {
                int clave = hashPlegamiento(numero);
                // en caso de colisión, se suma 1 a la clave, caso contrario se agrega la clave y el número
                if (tabla[clave][0] != 0) {
                    int j = 1;
                    while (tabla[clave][j] != 0) {
                        j++;
                    }
                    tabla[clave][j] = numero;
                }
                // en caso de no haber colisión, se agrega la clave y el número
                tabla[clave][0] = numero;
            } else {
                System.out.println("El número ingresado no es de 9 dígitos");
                i--;
            }
        }
        //cerramos el scanner
        sc.close();
        //imprimir la tabla
        for (int i = 0; i < 10; i++) {
            System.out.println("Posición " + i + ": " + tabla[i][0] + " " + tabla[i][1] + " " + tabla[i][2] + " " + tabla[i][3] + " " + tabla[i][4] + " " + tabla[i][5] + " " + tabla[i][6] + " " + tabla[i][7] + " " + tabla[i][8] + " " + tabla[i][9] + " " + tabla[i][10]);
        }
    }

    // función hash cuadrado medio
    public static int hashCuadradoMedio(int x, int n) {
        int y = x * x;
        String s = Integer.toString(y);
        int m = s.length();
        int k = (m - n) / 2;
        String z = s.substring(k, k + n);
        return Integer.parseInt(z);
    }

    // metodo hash modulo
    public static int hashModulo(int x, int n) {
        return x % n;
    }

    //metodo hash plegamiento para numeros de 9 digitos (3 grupos de 3 digitos) y devolver el dígito menos significativo
    public static int hashPlegamiento(int x) {
        String s = Integer.toString(x);
        int m = s.length();
        int k = m / 3;
        int a = Integer.parseInt(s.substring(0, k));
        int b = Integer.parseInt(s.substring(k, k * 2));
        int c = Integer.parseInt(s.substring(k * 2, m));
        int d = a + b + c;
        String e = Integer.toString(d);
        int f = Integer.parseInt(e.substring(e.length() - 1));
        return f;
    }
}