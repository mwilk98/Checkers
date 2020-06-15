package Checkers;
import javax.swing.*;
import java.awt.*;

/**
 * Klasa obsługująca pionki gry. Obsluguje rozmieszczenie pionków,
 * powstawanie damek oraz wyświetlanie możliwych ruchów.
 */
public class Pawns extends Component {

    /**
     * Tablica dwuwymiarowa do przechowywania pionków.
     */
    protected int[][] pawnArray;
    /**
     * Tablica obiektów typu Board określająca każde pole planszy gry.
     */
    private Board[][] board;

    /**
     * Zmienna typu String przechowujca ścieżkę do pliku graficznego
     * strzałki wskazującej możlliwe ruchy na planszy gry.
     */
    final static String INDICATOR = "/assets/indicator_arrow.png";
    /**
     * Zmienna typu String przechowujca ścieżkę do pliku graficznego
     * białej damki.
     */
    final static String WHITE_KING = "/assets/white_pawn_king.png";
    /**
     * Zmienna typu String przechowujca ścieżkę do pliku graficznego
     * czarnej damki.
     */
    final static String BLACK_KING = "/assets/black_pawn_king.png";
    /**
     * Zmienna typu String przechowujca ścieżkę do pliku graficznego
     * białej damki drugiego stylu.
     */
    final static String WHITE_KING2 = "/assets/white_pawn_king_chess.png";
    /**
     * Zmienna typu String przechowujca ścieżkę do pliku graficznego
     * czarnej damki drugiego stylu.
     */
    final static String BLACK_KING2 = "/assets/black_pawn_king_chess.png";
    /**
     * Zmienna typu String przechowujca ścieżkę do pliku graficznego
     * białej damki trzeciego stylu.
     */
    final static String WHITE_KING3 = "/assets/white_pawn_king_sw.png";
    /**
     * Zmienna typu String przechowujca ścieżkę do pliku graficznego
     * czarnej damki trzeciego stylu.
     */
    final static String BLACK_KING3 = "/assets/black_pawn_king_sw.png";
    /**
     * Zmienna typu String przechowujca ścieżkę do pliku graficznego
     * czarnego pionka.
     */
    final static String BLACK_PAWN = "/assets/black_pawn.png";
    /**
     * Zmienna typu String przechowujca ścieżkę do pliku graficznego
     * białego pionka.
     */
    final static String WHITE_PAWN = "/assets/white_pawn.png";
    /**
     * Zmienna typu String przechowujca ścieżkę do pliku graficznego
     * czarnego pionka drugiego stylu.
     */
    final static String BLACK_PAWN2 = "/assets/black_pawn_chess.png";
    /**
     * Zmienna typu String przechowujca ścieżkę do pliku graficznego
     * białego pionka drugiego stylu.
     */
    final static String WHITE_PAWN2 = "/assets/white_pawn_chess.png";
    /**
     * Zmienna typu String przechowujca ścieżkę do pliku graficznego
     * czarnego pionka trzeciego stylu.
     */
    final static String BLACK_PAWN3 = "/assets/black_pawn_sw.png";
    /**
     * Zmienna typu String przechowujca ścieżkę do pliku graficznego
     * białego pionka trzeciego stylu.
     */
    final static String WHITE_PAWN3 = "/assets/white_pawn_sw.png";

    /**
     * Obiekt klasy ImageIcon przechowujący obraz białego pionka.
     */
    ImageIcon pawnWhite;
    /**
     * Obiekt klasy ImageIcon przechowujący obraz czarnego pionka.
     */
    ImageIcon pawnBlack;
    /**
     * Obiekt klasy ImageIcon przechowujący obraz strzałki wskazującej możliwe ruchy.
     */
    ImageIcon indicator;
    /**
     * Obiekt klasy ImageIcon przechowujący obraz białej damki.
     */
    ImageIcon pawnWhiteKing;
    /**
     * Obiekt klasy ImageIcon przechowujący obraz czarnejd damki.
     */
    ImageIcon pawnBlackKing;
    /**
     * Obiekt klasy ImageIcon przechowujący obraz damki do stworzenia.
     */
    ImageIcon pawnKing;

    /**
     * Konstruktor klasy Pawn obsługującej pionki.
     * Przypisuje odpowiedni wygląd zależnie od przekazanego przez parametr stylu.
     * @param pawnArray - tablica przechowująca koordynaty pionków na planszy gry.
     * @param board - obiekt klasy Board przechowujący koordynaty każdego pola na planszy gry.
     * @param style - zmienna określająca aktyalny styl gry.
     */
    public Pawns(int[][] pawnArray, Board[][] board,int style){

        this.pawnArray=pawnArray;
        this.board=board;
        if (style == 1) {
            pawnWhite = new ImageIcon(getClass().getResource(BLACK_PAWN));
            pawnBlack = new ImageIcon(getClass().getResource(WHITE_PAWN));
            pawnWhiteKing = new ImageIcon(getClass().getResource(WHITE_KING));
            pawnBlackKing = new ImageIcon(getClass().getResource(BLACK_KING));
        }
        if (style == 2) {
            pawnWhite = new ImageIcon(getClass().getResource(BLACK_PAWN2));
            pawnBlack = new ImageIcon(getClass().getResource(WHITE_PAWN2));
            pawnWhiteKing = new ImageIcon(getClass().getResource(WHITE_KING2));
            pawnBlackKing = new ImageIcon(getClass().getResource(BLACK_KING2));
        }
        if (style == 3) {
            pawnWhite = new ImageIcon(getClass().getResource(BLACK_PAWN3));
            pawnBlack = new ImageIcon(getClass().getResource(WHITE_PAWN3));
            pawnWhiteKing = new ImageIcon(getClass().getResource(WHITE_KING3));
            pawnBlackKing = new ImageIcon(getClass().getResource(BLACK_KING3));
        }
        indicator = new ImageIcon(getClass().getResource(INDICATOR));

    }

    /**
     * Metoda odpowiedzialna za rysowanie pionków na planszy gry. Zależnie od położenia pionka
     * na planszy tworzy damki lub wyświetla podpowiedzi ruchów.
     * @param g - parametr typu Graphics.
     */
    public void pawnsPaint(Graphics g){
        for (int j = 0; j < 8; j++)
            for (int i = 0; i < 8; i++) {
                if (pawnArray[i][j] == 3 || pawnArray[i][j] == 4 || pawnArray[i][j] == 5
                        || pawnArray[i][j] == 8 || pawnArray[i][j] == 9) {
                    indicator.paintIcon(this,g,board[i][j].x+5,board[i][j].y+55);
                }
            }
        for (int j = 0; j < 8; j++)
            for (int i = 0; i < 8; i++) {
                if (pawnArray[i][j] == 1 || pawnArray[i][j] == 3 || pawnArray[i][j] == 7
                        || pawnArray[i][j] == 9)
                    pawnKing = pawnBlackKing;//pionek damka czarny
                else if (pawnArray[i][j] == 2 || pawnArray[i][j] == 4 || pawnArray[i][j] == 6
                        || pawnArray[i][j] == 8)
                    pawnKing = pawnWhiteKing;//pionek damka biały

                if (pawnArray[i][j] == 1 || pawnArray[i][j] == 3)
                    pawnWhite.paintIcon(this,g, 12+75 * i,110 + 73 * j);

                if (pawnArray[i][j] == 2 || pawnArray[i][j] == 4)
                    pawnBlack.paintIcon(this,g,12+75 * i,110 + 74 * j);

                if (pawnArray[i][j] == 6 || pawnArray[i][j] == 7 || pawnArray[i][j] == 8
                        || pawnArray[i][j] == 9)
                    pawnKing.paintIcon(this,g,12+75* i, 110 + 73 * j);
            }
    }

    /**
     * Metoda odpowiedzialna za ustawianie pionków na planszy gry w odpowiedni sposób.
     * Góra dla czarnych, dół białych a środek pusty.
     */
    public void pawnsSet(){
        for (int j = 0; j < 8; j++)
            for (int i = 0; i < 8; i++) {
                pawnArray[i][j] = 0;
            }
        for (int j = 0; j < 3; j++)
            for (int i = 0; i < 8; i++) {
                if ((i + j) % 2 == 0)
                    pawnArray[i][j] = 1;
            }

        for (int j = 5; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if ((i + j) % 2 == 0)
                    pawnArray[i][j] = 2;
            }
        }
    }
}
