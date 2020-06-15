package Checkers;

/**
 * Klasa główna gry która wywołuje kostruktory menu oraz
 * oraz nowej gry.
 */
public class MainGame {

    /**
     * Wywołanie konstruktora menu w którym zawiera się obsługa całej gry.
     */
    public MainGame() {
        new Menu();
    }

    /**
     * Metoda odpowiedzialna za wywołanie konstruktora głównej klasy gry.
     * Dzięki wywołaniu konstruktora w tej metodzie możliwe jest prawidłowe uruchomienie gry poprzez jej kompilację
     * lub bezpośrednio z poziomu archiwum .jar.
     * @param args - parametr potrzebny do poprawnego wywołania public static void main.
     */
    public static void main(String args[]) {
        new MainGame();
    }
}