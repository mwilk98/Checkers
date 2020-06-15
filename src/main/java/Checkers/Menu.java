package Checkers;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Klasa obslugująca menu gry oraz panel informacyjny.
 */

public class Menu extends JFrame implements ActionListener {
    /**
     * Tablica dwuwymiarowa potrzebna do określenia współrzędnych pionków na planszy gry.
     */
    protected static int[][] pawnArray;
    /**
     * Tablica dwuwytmiarowa określająca współrzędne każdego pola na planszy gry.
     */
    private Board[][] gameBoard;
    /**
     * Obiekt klasy menu.
     */
    private Menu menu;
    /**
     * Obiekt klasy pawns(pionki).
     */
    private Pawns pawns;
    /**
     * Zmienna typu bool określająca czy gra się rozpoczęła.
     */
    protected static boolean game;
    /**
     * Zmienna typu String przechowująca adres położenia pliku graficznego
     * pierwszego stylu planszy gry.
     */
    final static String BOARD_1 = "/assets/board.png";
    /**
     * Zmienna typu String przechowująca adres położenia pliku graficznego
     * drugiego  stylu planszy gry.
     */
    final static String BOARD_2 = "/assets/board1.png";
    /**
     * Zmienna typu String przechowująca adres położenia pliku graficznego
     * trzeciego stylu planszy gry.
     */
    final static String BOARD_3 = "/assets/board2.png";

    /**
     * Obiekt ImageIcon przechowująca aktualny obraz planszy gry.
     */
    ImageIcon board;
    /**
     * Obiekt ImageIcon przechowująca pierwszy styl planszy gry.
     */
    ImageIcon board1;
    /**
     * Obiekt ImageIcon przechowująca durgi styl planszy gry.
     */
    ImageIcon board2;
    /**
     * Obiekt ImageIcon przechowująca trzeci styl planszy gry.
     */
    ImageIcon board3;
    /**
     * Obiekt klasy JMenuBar.
     */
    JMenuBar menuBar;
    int style;

    /**
     * Konstruktor Menu gry. Inicjowane w nim są komponenty biblioteki Swing
     * zmienne do obslugi gry oraz tablice dwuwymiarowe planszy gry.
     * Nadana zostaje nazwa Warcaby.
     * Przypisana ilośc pól na planszę 8x8.
     * Ustawiany jest styl 1 jako podstawowy gry.
     * Inicjowane są pionki.
     * Gra ustawiana jako nie rozpoczęta,
     * Wywoływana jest metoda menu tworząca menu gry.
     * Ustawiana jest szerokość i wysokość oraz inne opcje okna gry.
     * Przypisywany jest styl 1 jako styl planszy.
     * Przypisywane są pozostałe style gry do wyboru.
     * Inicjowana jest plansza, gra ustawiona jako rozpoczęta,
     * odświeżenie planszy gry oraz włączenie jej wyświetlania.
     */
    public Menu() {
        super("Warcaby");
        pawnArray = new int[8][8];
        gameBoard = new Board[8][8];
        style=1;
        pawns = new Pawns(pawnArray,gameBoard,style);
        game = false;
        menu();

        int width = 615;
        int height = 708;
        setSize(width, height);
        setLocationRelativeTo(menu);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board1 = new ImageIcon(getClass().getResource(BOARD_1));
        board2 = new ImageIcon(getClass().getResource(BOARD_2));
        board3 = new ImageIcon(getClass().getResource(BOARD_3));

        board=board1;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[i][j] = new Board(this, i, j);
                add(gameBoard[i][j]);
            }
        }
        pawns.pawnsSet();
        repaint();
        game = true;
        setVisible(true);

    }

    /**
     * Metoda tworząca menu. Dodaje komponenty menu
     * oraz odpowiada za wyświetlanie panelu informacyjnego gry.
     */
    public void menu(){
        menuBar = new JMenuBar();
        JMenu game = new JMenu("Gra");
        menuBar.add(game);
        JMenuItem newGame = new JMenuItem("Nowa gra");
        newGame.addActionListener(e -> {
            pawns.pawnsSet();
            Board.whichTurn = 0;
            Board.whiteMoves = 0;
            Board.blackMoves = 0;
            Board.turn = false;
            repaint();
        });
        game.add(newGame);
        JMenuItem quitGame = new JMenuItem("Zakoncz");
        quitGame.addActionListener(e -> {
            System.exit(0);
        });
        game.add(quitGame);
        JMenu styles = new JMenu("Style");
        menuBar.add(styles);
        ButtonGroup styleGroup = new ButtonGroup();
        JRadioButtonMenuItem style1 = new JRadioButtonMenuItem("Klasyczny");
        style1.addActionListener(e -> {
            board=board1;
            style=1;
            pawns = new Pawns(pawnArray,gameBoard,style);
            repaint();
        });
        JRadioButtonMenuItem style2 = new JRadioButtonMenuItem("Styl 2");
        style2.addActionListener(e -> {
            board=board2;
            style=2;
            pawns = new Pawns(pawnArray,gameBoard,style);
            repaint();
        });
        JRadioButtonMenuItem style3 = new JRadioButtonMenuItem("Styl 3");
        style3.addActionListener(e -> {
            board=board3;
            style=3;
            pawns = new Pawns(pawnArray,gameBoard,style);
            repaint();
        });
        styleGroup.add(style1);
        styleGroup.add(style2);
        styleGroup.add(style3);
        styles.add(style1);
        styles.add(style2);
        styles.add(style3);
        this.setJMenuBar(menuBar);

    }

    /**
     * Metoda przesłonięta paint. W niej ustawiane jest malowanie panelu informacyjnego gry
     * oraz planszy gry i pionków.
     * @param g - parametr typu Graphics.
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g);

        board.paintIcon(this,g,8,100);
        pawns.pawnsPaint(g);


        g2d.setColor(Color.BLACK);
        g2d.drawString("POLE:", 10, 80);
        g2d.drawString(Board.tmp, 50, 80);


        g2d.drawString("RUCHY BIALE:", 80, 80);
        g2d.drawString(String.valueOf(Board.whiteMoves), 165, 80);


        g2d.drawString("RUCHY CZARNE:", 180, 80);
        g2d.drawString(String.valueOf(Board.blackMoves), 280, 80);


        g2d.drawString("TURA KOLORU:", 295, 80);
        if(Board.turn)
            g2d.drawString("CZARNE", 385, 80);
        if(!Board.turn) {
            g2d.setColor(Color.WHITE);
            g2d.drawString("BIALE", 385, 80);
        }

        g2d.setColor(Color.BLACK);
        g2d.drawString("TURA NUMER:", 440, 80);
        g2d.drawString(String.valueOf(Board.whichTurn), 525, 80);

    }

    /**
     * Metoda przesłonięta niezbędna do zaimplenetowania pozostałych metod do obsługi gry.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
