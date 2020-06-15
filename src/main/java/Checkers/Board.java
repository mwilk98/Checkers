package Checkers;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 * Klasa obsługująca planszę gry, jej pola oraz zawiera logikę poruszania
 * sie pionków.
 * @author Maciej Wilk
 * @see javax.swing
 * @see java.awt.event.MouseListener
 * @see java.awt.event.MouseEvent
 */
public class Board extends JComponent implements MouseListener {

    /**
     Zmienna określająca współrzędną X pola planszy gry.
     */
    protected int x;
    /**
     * Zmienna określająca współrzędną Y pola planszy gry.
     */
    protected int  y;
    /**
     * Zmienna określająca szerokość poszczególnego pola planszy gry.
     */
    protected int width = 65;
    /**
     * Zmienna określająca wysokość poszczególnego pola planszy gry.
     */
    protected int height = 65;
    /**
     * Zmienna określająca współrzędną x klikniętego pola planszy gry.
     */
    protected int _i;
    /**
     * Zmienna określająca współrzędną y klikniętego pola planszy gry.
     */
    protected int _j;

    /**
     * Zmienna określająca ilość wykonanych ruchów pionków białych.
     */
    protected static int whiteMoves=0;
    /**
     * Zmienna określająca ilość wykonanych ruchów pionków czarnych.
     */
    protected static int blackMoves=0;
    /**
     * Zmienna określająca ilość tur.
     */
    protected static int whichTurn=0;
    /**
     * Obiekt klasy StringBuilder potrzebny do wyświetlania aktualnych współrzędnych
     * na jakich znajduje się kursor myszy.
     */
    protected StringBuilder name = new StringBuilder();
    /**
     * String przechowujący aktualne współrzędne na jakich znajduje się kursor myszy.
     */
    protected static String tmp = "A1";
    /**
     * Zmienna JFrame odwołująca się do okna gry.
     */
    protected static JFrame gameBoard;
    /**
     * Zmienna przechowująca współrzędną X pola gdzie może dojść do zbicia pionka.
     */
    protected static int iRemove;
    /**
     * Zmienna przechowująca współrzędną Y pola gdzie może dojść do zbicia pionka.
     */
    protected static int jRemove;
    /**
     * Zmienna typu bool określająca czyja tura jest aktualnie.
     */
    protected static boolean turn;

    /**
     * Konstruktor klasy Board który inicjalizuje zmienne
     * x - zmienna określająca współrzędne X pola planszy
     * y - zmienna określająca współrzędne Y pola planszy
     * _i - przypisanie współrzędnych x klikniętego pola
     * _j - przypisanie współrzędnych y klikniętego pola
     * setBounds - określa rozmiar okna gry
     * setName - określa nad którm polem znajduje się kursor myszy
     * oraz dodaje metdy przechwytywania myszy (addMouseListener)
     * i określa zmienne do wyświetlania okna
     * oraz ustawia jego widoczność(setVisible)
     * @param f - JFrame - odwołanie do okna planszy
     * @param i - współrzędna X wyświetlanych współrzędnych nad którymi
     *          znajduję się kursor myszy
     * @param j - współrzędna Y wyświetlanych współrzędnych nad którymi
     *          znajduję się kursor myszy
     */
    public Board(JFrame f, int i, int j) {
        gameBoard = f;
        x = 13+75 * i;
        y = 50 + 75 * j;
        _i = i;
        _j = j;
        setBounds(x, y, width, height);
        setName(i, j);
        addMouseListener(this);
        setLayout(null);
        setDoubleBuffered(false);
        setVisible(true);
    }

    /**
     * Metoda wyświetlająca na panelu informacyjnym aktualne współrzędne
     * nad ktorymi znadjuję sie kursor myszy.
     * Współrzędne wyświetlane są poprzez dodanie do zmiennej
     * StringBuilder informacji poprzez metodę append().
     * @param i - współrzędna X pola na planszy gry
     * @param j - współrzędna Y pola na planszy gry
     */
    void setName(int i, int j) {
        String tmp = null;
        if (i == 0) {
            tmp = "A";
        }
        if (i == 1) {
            tmp = "B";
        }
        if (i == 2) {
            tmp = "C";
        }
        if (i == 3) {
            tmp = "D";
        }
        if (i == 4) {
            tmp = "E";
        }
        if (i == 5) {
            tmp = "F";
        }
        if (i == 6) {
            tmp = "G";
        }
        if (i == 7) {
            tmp = "H";
        }
        name.append(tmp);
        name.append(j + 1);
    }

    /**
     * Przeciążona metoda toString która pozwala na odpowiednie wyświetlanie
     * informacji o współrzędnych kursora.
     * @return zwraca współrzędne
     */
    @Override
    public String toString() {
        return name + "";
    }

    /**
     *Przesłonięta metoda interfejsu MouseListener określająca zachowanie
     * gry podczas poruszania kursorem myszy po planszy.
     * Gdy poruszamy myszką odwieżane jest miejsce wyświetlania aktualnych
     * współrzędnych na panelu informacyjnym bez potrzeby odświeżania całego
     * okna aplikacji.
     *
     * @see MouseListener
     * @see MouseListener#mouseEntered(MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        Object source = e.getSource();
        tmp = source.toString();
        gameBoard.repaint(45, 60, 25, 25);
    }

    /**
     * Metoda odpowowiedzialna za wyświetlenie komunikatu o zakończeniu
     * rozgrywki w przypadku wygrania przez jednego z graczy.
     * @param s - zmienna typu string przekazująca wygrany kolor.
     */
    public void gameOver(String s){
        JOptionPane.showMessageDialog(null, "Koniec gry! Wygral kolor: " + s);
    }

    /**
     * Metoda sprawdzający aktualny stan gry.
     * W przypadku zbicia wszystkich pionków danego koloru przekazuje
     * parametr typu String z kolorem do metody odpowiedzialnej za
     * wyświetlenie komunikatu kończącego grę.
     */
    public void gameState(){
        int b = 0;
        int c = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Menu.pawnArray[i][j] == 1 || Menu.pawnArray[i][j] == 3 || Menu.pawnArray[i][j] == 7 || Menu.pawnArray[i][j] == 9)
                    c++;
                if (Menu.pawnArray[i][j] == 2 || Menu.pawnArray[i][j] == 4 || Menu.pawnArray[i][j] == 6 || Menu.pawnArray[i][j] == 8)
                    b++;
            }
        }

        if (b == 0)
            gameOver("czarny");
        else if (c == 0)
            gameOver("bialy");
    }

    /**
     * Metoda obsługująca ruchy damek po planszy gry. Odpowiada za obliczenie
     * ruchów i możliwości zbicia pionków przez damki.
     */
    public void kingMoves(){
        for (int i = 0; i < 4; i++) {
            int tmpX = _i;
            int tmpY = _j;
            while (tmpX != -1 && tmpX != 8 && tmpY != -1 && tmpY != 8) {
                if (i == 0) {//damki ruch lewa góra
                    tmpX--;
                    tmpY--;
                } else if (i == 1) {//damki ruch  lewy  dół
                    tmpX--;
                    tmpY++;
                } else if (i == 2) {//damki ruch prawy dół
                    tmpX++;
                    tmpY++;
                } else {//damki ruch prawa góra
                    tmpX++;
                    tmpY--;
                }
                if (tmpX != -1 && tmpX != 8 && tmpY != -1 && tmpY != 8)
                    if (Menu.pawnArray[tmpX][tmpY] == 0) {
                        Menu.pawnArray[tmpX][tmpY] = 5;
                    } else {
                        if (((Menu.pawnArray[tmpX][tmpY] == 1 || Menu.pawnArray[tmpX][tmpY] == 7) && Menu.pawnArray[_i][_j] == 8)
                                || (Menu.pawnArray[tmpX][tmpY] == 2 || Menu.pawnArray[tmpX][tmpY] == 6) && Menu.pawnArray[_i][_j] == 9) {
                            if (i == 0) {//skok przez pionka lewa góra
                                tmpX--;
                                tmpY--;
                            } else if (i == 1) {//skok przez pionka lewy dół
                                tmpX--;
                                tmpY++;
                            } else if (i == 2) {//skok przez pionka prawy dół
                                tmpX++;
                                tmpY++;
                            } else {//skok przez pionka prawa góra
                                tmpX++;
                                tmpY--;
                            }
                            if (tmpX != -1 && tmpX != 8 && tmpY != -1 && tmpY != 8)
                                if (Menu.pawnArray[tmpX][tmpY] == 0) {
                                    Menu.pawnArray[tmpX][tmpY] = 5;
                                    if (i == 0) {//zbicie lewo góra
                                        tmpX++;
                                        tmpY++;
                                    } else if (i == 1) {//zbicie lewo dół
                                        tmpX++;
                                        tmpY--;
                                    } else if (i == 2) {//zbicie prawo dół
                                        tmpX--;
                                        tmpY--;
                                    } else {//zbicie lewo dół
                                        tmpX--;
                                        tmpY++;
                                    }
                                    iRemove = tmpX;
                                    jRemove = tmpY;
                                }
                        }
                        break;
                    }
            }
        }
    }

    /**
     * Metoda obsługująca ruchy białych pionków po planszy gry.
     * Odpowiada za obliczenie ruchów i możliwości zbicia czarnych
     * pionków przez białe.
     */
    public void whitePawnMoves(){
        if(!turn) {
            if (_i != 0 && _i != 7) {
                if (Menu.pawnArray[_i - 1][_j - 1] == 0) {
                    Menu.pawnArray[_i - 1][_j - 1] = 5;
                }
                if (Menu.pawnArray[_i + 1][_j - 1] == 0) {
                    Menu.pawnArray[_i + 1][_j - 1] = 5;
                }

            } else if (_i == 0) {
                if (Menu.pawnArray[_i + 1][_j - 1] == 0)
                    Menu.pawnArray[_i + 1][_j - 1] = 5;
            } else {
                if (Menu.pawnArray[_i - 1][_j - 1] == 0)
                    Menu.pawnArray[_i - 1][_j - 1] = 5;
            }
            // *****************************************************
            if (_j != 1)
                if (_i != 0 && _i != 1 && _i != 6 && _i != 7) {
                    if (Menu.pawnArray[_i - 2][_j - 2] == 0 && (Menu.pawnArray[_i - 1][_j - 1] == 1 || Menu.pawnArray[_i - 1][_j - 1] == 7)) {
                        Menu.pawnArray[_i - 2][_j - 2] = 5;
                        iRemove = _i - 1;
                        jRemove = _j - 1;
                    }
                    if (Menu.pawnArray[_i + 2][_j - 2] == 0 && (Menu.pawnArray[_i + 1][_j - 1] == 1 || Menu.pawnArray[_i - 1][_j - 1] == 7)) {
                        Menu.pawnArray[_i + 2][_j - 2] = 5;
                        iRemove = _i + 1;
                        jRemove = _j - 1;
                    }
                } else if (_i == 1 || _i == 0) {
                    if (Menu.pawnArray[_i + 2][_j - 2] == 0 && (Menu.pawnArray[_i + 1][_j - 1] == 1 || Menu.pawnArray[_i + 1][_j - 1] == 7)) {
                        Menu.pawnArray[_i + 2][_j - 2] = 5;
                        iRemove = _i + 1;
                        jRemove = _j - 1;
                    }
                } else {
                    if (Menu.pawnArray[_i - 2][_j - 2] == 0 && (Menu.pawnArray[_i - 1][_j - 1] == 1 || Menu.pawnArray[_i - 1][_j - 1] == 7)) {
                        Menu.pawnArray[_i - 2][_j - 2] = 5;
                        iRemove = _i - 1;
                        jRemove = _j - 1;
                    }
                }
        }
    }
    /**
     * Metoda obsługująca ruchy czarnych pionków po planszy gry.
     * Odpowiada za obliczenie ruchów i możliwości zbicia białych
     * pionków przez czarne.
     */
    public void blackPawnMoves(){
        if(turn) {
            iRemove = -2;
            jRemove = -2;
            if (_i != 0 && _i != 7) {
                if (Menu.pawnArray[_i - 1][_j + 1] == 0) {
                    Menu.pawnArray[_i - 1][_j + 1] = 5;
                }
                if (Menu.pawnArray[_i + 1][_j + 1] == 0) {
                    Menu.pawnArray[_i + 1][_j + 1] = 5;
                }
            } else if (_i == 0) {
                if (Menu.pawnArray[_i + 1][_j + 1] == 0)
                    Menu.pawnArray[_i + 1][_j + 1] = 5;
            } else {
                if (Menu.pawnArray[_i - 1][_j + 1] == 0)
                    Menu.pawnArray[_i - 1][_j + 1] = 5;
            }
            // *****************************************************
            if (_j != 6) {
                if (_i != 0 && _i != 1 && _i != 6 && _i != 7) {

                    if (Menu.pawnArray[_i - 2][_j + 2] == 0 && (Menu.pawnArray[_i - 1][_j + 1] == 2 || Menu.pawnArray[_i - 1][_j + 1] == 6)) {
                        Menu.pawnArray[_i - 2][_j + 2] = 5;
                        iRemove = _i - 1;
                        jRemove = _j + 1;
                    }
                    if (Menu.pawnArray[_i + 2][_j + 2] == 0 && (Menu.pawnArray[_i + 1][_j + 1] == 2 || Menu.pawnArray[_i + 1][_j + 1] == 6)) {
                        Menu.pawnArray[_i + 2][_j + 2] = 5;
                        iRemove = _i + 1;
                        jRemove = _j + 1;
                    }
                } else if (_i == 1 || _i == 0) {
                    if (Menu.pawnArray[_i + 2][_j + 2] == 0 && (Menu.pawnArray[_i + 1][_j + 1] == 2 || Menu.pawnArray[_i + 1][_j + 1] == 6)) {
                        Menu.pawnArray[_i + 2][_j + 2] = 5;
                        iRemove = _i + 1;
                        jRemove = _j + 1;
                    }
                } else {
                    if (Menu.pawnArray[_i - 2][_j + 2] == 0 && (Menu.pawnArray[_i - 1][_j + 1] == 2 || Menu.pawnArray[_i - 1][_j + 1] == 6)) {
                        Menu.pawnArray[_i - 2][_j + 2] = 5;
                        iRemove = _i - 1;
                        jRemove = _j + 1;
                    }
                }
            }
        }
    }

    /**
     * Metoda odpowiedzialna za przemieszczenie się białego pionka.
     * Zwiększa ona też stan zmiennej przechowującej ilość
     * ruchów białych pionków oraz ilość tur gry.
     * @param i - współrzędna X pionka
     * @param j - współrzędna Y pionka
     */
    public void moveWitePawn(int i,int j){
        Menu.pawnArray[i][j] = 0;//poruszanie białych
        Menu.pawnArray[_i][_j] = 2;
        whiteMoves++;
        whichTurn++;
    }
    /**
     * Metoda odpowiedzialna za przemieszczenie się czarnego pionka.
     * Zwiększa ona też stan zmiennej przechowującej ilość
     * ruchów czarnych pionków oraz ilość tur gry.
     * @param i - współrzędna X pionka
     * @param j - współrzędna Y pionka
     */
    public void moveBlackPawn(int i,int j){
        Menu.pawnArray[i][j] = 0;
        Menu.pawnArray[_i][_j] = 1;
        blackMoves++;
        whichTurn++;
    }
    /**
     * Metoda odpowiedzialna za przemieszczenie się białej damki.
     * Zwiększa ona też stan zmiennej przechowującej ilość
     * ruchów białych pionków oraz ilość tur gry.
     * @param i - współrzędna X pionka
     * @param j - współrzędna Y pionka
     */
    public void moveWhiteKing(int i,int j){
        Menu.pawnArray[i][j] = 0;
        Menu.pawnArray[_i][_j] = 6;
        whiteMoves++;
        whichTurn++;
    }
    /**
     * Metoda odpowiedzialna za przemieszczenie się czarnej damki.
     * Zwiększa ona też stan zmiennej przechowującej ilość
     * ruchów białych pionków oraz ilość tur gry.
     * @param i - współrzędna X pionka
     * @param j - współrzędna Y pionka
     */
    public void moveBlackKing(int i,int j){
        Menu.pawnArray[i][j] = 0;
        Menu.pawnArray[_i][_j] = 7;
        blackMoves++;
        whichTurn++;
    }

    /**
     * Metoda czyszcząca podpowiedź możliwych ruchów z planszy gry.
     * @param i - współrzędna X pola planszy
     * @param j - współrzędna Y pola planszy
     */
    public void clearPormpt(int i,int j){
        Menu.pawnArray[i][j] = 0;
    }
    /**
     * Metoda odpowiedzialna za dobranie ruchów do odpowiednich pionków
     * poprzez wywołanie odpowiednich metod.
     * Oznaczenia w tablicy pionków (pawnArray):
     * 3 - wybrany pionek czarny
     * 4 - wybrano pionek biały
     * 5 - pole puste na które może zostać wykonany ruch
     * 8 - wybrana damka biała
     * 9 - wybrana damka czarna
     */
    public void movePawns(){
        for (int j = 0; j < 8; j++)
            for (int i = 0; i < 8; i++) {
                if (Menu.pawnArray[i][j] == 4) {
                    if(!turn) {
                        moveWitePawn(i, j);
                         turn = true;
                    }
                }
                if (Menu.pawnArray[i][j] == 3) {//poruszanie czrnych
                    if(turn) {
                        moveBlackPawn(i, j);
                        turn = false;
                    }
                }
                if (Menu.pawnArray[i][j] == 8) {//przemieszczenie damki białej
                    if(!turn) {
                        moveWhiteKing(i, j);
                        turn = true;
                    }
                }
                if (Menu.pawnArray[i][j] == 9) {//przemieszczanie damki czarnej
                    if(turn) {
                        moveBlackKing(i, j);
                        turn = false;
                    }
                }
                if (Menu.pawnArray[i][j] == 5)//znikanie podpowiedzi
                    clearPormpt(i,j);
            }
    }

    /**
     * Metoda tworząca damkę białą.
     */
    public void createWhiteKing(){
        Menu.pawnArray[_i][_j] = 6;//tworzenie białej damki
    }

    /**
     * Metoda tworząca damkę czarną.
     */
    public void createBlackKing(){
        Menu.pawnArray[_i][_j] = 7;//tworzenie czarnej damki
    }

    /**
     * Metoda przesłonięta pochodząca z interfejsu MouseListener
     * Określa zachowanie gry podczas kliknięcia przez użytkownika
     * myszką w określonym miejscu planszy gry. Wywołuje ona inne metody
     * odpowiedzialne za obliczanie ruchów pionków według algorytmów,
     * tworzące damki oraz sprawdzające stan gry. Na końcu odświeża
     * planszę gry poprzez metodę repaint().
     * Oznaczenia poszczególnych pól w tablicy pionków:
     * 1 - pionek czarny
     * 2 - pionek biały
     * 3 - wybrany pionek czarny
     * 4 - wybrano pionek biały
     * 5 - pole puste na które może zostac wykonany ruch
     * 6 - damka biała
     * 7 - damka czarna
     * 8 - wybrana damka biała
     * 9 - wybrana damka czarna
     */
    @Override
    public void mouseClicked(MouseEvent e) {


        if (Menu.pawnArray[_i][_j] != 5) {
            for (int j = 0; j < 8; j++)
                for (int i = 0; i < 8; i++) {
                    if (Menu.pawnArray[i][j] == 3 || Menu.pawnArray[i][j] == 4 || Menu.pawnArray[i][j] == 8 || Menu.pawnArray[i][j] == 9) {
                        Menu.pawnArray[i][j] -= 2;
                    }
                    if (Menu.pawnArray[i][j] == 5) {
                        Menu.pawnArray[i][j] = 0;
                    }
                }
            if (Menu.pawnArray[_i][_j] == 1 || Menu.pawnArray[_i][_j] == 2 || Menu.pawnArray[_i][_j] == 6 || Menu.pawnArray[_i][_j] == 7) {
                iRemove = -2;
                jRemove = -2;
                Menu.pawnArray[_i][_j] += 2;
                // ******************************************************
                if (Menu.pawnArray[_i][_j] == 8 || Menu.pawnArray[_i][_j] == 9) {
                    kingMoves();//ruch damkami
                }
                // ******************************************************
                if (Menu.pawnArray[_i][_j] == 4){ // KLIKNIETY PIONEK
                        whitePawnMoves();
                }
                // *****************************************************CZARNY PIONEK
                if (Menu.pawnArray[_i][_j] == 3) {
                        blackPawnMoves();
                }
            }
        }
        else {
            movePawns();
            if ((_i == iRemove + 1 || _i == iRemove - 1) && (_j == jRemove - 1 || _j == jRemove + 1)) {
                Menu.pawnArray[iRemove][jRemove] = 0;
                iRemove = -2;
                jRemove = -2;
            }
            if (_j == 0 && Menu.pawnArray[_i][_j] == 2) {
                createWhiteKing();
            }
            if (_j == 7 && Menu.pawnArray[_i][_j] == 1) {
                createBlackKing();
            }
        }
        if (Menu.game) {

            gameState();

        }
        gameBoard.repaint();
    }
    /**
     * Metoda przesłonięta pochodząca z interfejsu {@link MouseListener} -
     * niewykorzystywana. Jej obecność jest obowiązkowa w celu poprawnego
     * zaimplementowania inntch metod takich jak mouseClicked czy mouseEntered
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }
    /**
     * Metoda przesłonięta pochodząca z interfejsu {@link MouseListener} -
     * niewykorzystywana. Jej obecność jest obowiązkowa w celu poprawnego
     * zaimplementowania inntch metod takich jak mouseClicked czy mouseEntered
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }
    /**
     * Metoda przesłonięta pochodząca z interfejsu {@link MouseListener} -
     * niewykorzystywana. Jej obecność jest obowiązkowa w celu poprawnego
     * zaimplementowania inntch metod takich jak mouseClicked czy mouseEntered
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }
}