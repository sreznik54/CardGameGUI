import javax.swing.*;
import java.awt.*;
public class GameView extends JFrame {
    private static final int WINDOW_WIDTH = 600,
            WINDOW_HEIGHT = 600,
            YOFFSET = 60,
            PLAYER_CARDS_X = 300,
            PLAYER_CARDS_Y = 400,
            DEALER_CARDS_Y = 100,
            CARD_WIDTH = 72,
            CARD_HEIGHT = 96;


    private Image[] background;
    private Game game;
    private Image[] cards;

    // Constructs the window
    public GameView(Game a) {
        // Passes in the backend
        this.game = a;
        // Initializes all the background images
        background = new Image[5];
        background[0] = new ImageIcon("Resources/Backgrounds/BLACKJACK.png").getImage();
        background[1] = new ImageIcon("Resources/Backgrounds/bet.png").getImage();
        background[2] = new ImageIcon("Resources/Backgrounds/green.png").getImage();
        background[3] = new ImageIcon("Resources/Backgrounds/win.png").getImage();
        background[4] = new ImageIcon("Resources/Backgrounds/lose.png").getImage();
        // Initializes all the card images
        cards = new Image[53];
        for (int i = 1; i < 53; i++) {
            cards[i- 1] = new ImageIcon("Resources/Cards/" + i + ".png").getImage();
        }
        cards[52] = new ImageIcon("Resources/Cards/back.png").getImage();
        // Constructs the window
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setTitle("BLACK JACK");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    // Draws the game
    public void paint(Graphics g) {
        drawBackground(g);
        drawScore(g);
        drawCards(g);
        drawDealer(g);
        drawName(g);
    }

    // Draws the backgrounds of the game
    public void drawBackground(Graphics g) {
        // finds what stage the game is in
        int stage = game.getStage();
        g.drawImage(background[stage], 0, 0, this);
    }

    // Draws the cards centered on the board
    public void drawCards(Graphics g) {
        // Only draws on the game board
        if (game.getStage() == 2) {
            // Draw player's cards
            int playerCardX = 0;
            // Centers the cards determining weather the player has an even or odd amount of cards
            if(game.getPlayerHand().size() % 2 ==0)
            {
                playerCardX = PLAYER_CARDS_X - (game.getPlayerHand().size()/2)*77;
            }
            else {
                playerCardX = PLAYER_CARDS_X - (game.getPlayerHand().size()/2)*82 - 36;
            }
            // Draws the cards using the x value that was just found to start
            for (Card card : game.getPlayerHand()) {
                g.drawImage(card.getCardImage(), playerCardX, PLAYER_CARDS_Y, CARD_WIDTH, CARD_HEIGHT, this);
                playerCardX += CARD_WIDTH + 10;
            }

            // Draw dealer's cards centered
            // First card is face down
            g.drawImage(cards[52], PLAYER_CARDS_X - 77, DEALER_CARDS_Y, CARD_WIDTH, CARD_HEIGHT,  this);
            // Second Card is face up
            g.drawImage(game.getDealerHand().get(1).getCardImage(), PLAYER_CARDS_X + 5, DEALER_CARDS_Y, CARD_WIDTH, CARD_HEIGHT,  this);

        }
    }

    // Draws the Score in the top left corner
    public void drawScore(Graphics g){
        if (game.getStage() > 0) {
            g.setColor(Color.WHITE);
            g.setFont(new Font(Font.SERIF, Font.BOLD, 30));
            g.drawString("Score: " + String.valueOf(game.getPlayerScore()), 10, YOFFSET );
        }
    }

    // Writes the dealers name centered under the cards on the game board
    public void drawDealer(Graphics g){
        if (game.getStage() == 2) {
            g.setColor(Color.WHITE);
            g.setFont(new Font(Font.SERIF, Font.BOLD, 30));
            String dealerText = "Dealer";
            // Gets the x coordinate to center dealer
            int dealerTextWidth = g.getFontMetrics().stringWidth(dealerText);
            int x = (WINDOW_WIDTH - dealerTextWidth) / 2;
            g.drawString(dealerText, x, DEALER_CARDS_Y + CARD_HEIGHT + 30);
        }
    }
    // Writes the players name centered over the cards on the game board
    public void drawName(Graphics g){
        if (game.getStage() == 2) {
            g.setColor(Color.WHITE);
            g.setFont(new Font(Font.SERIF, Font.BOLD, 30));
            String name = game.getPlayerName();
            // Gets the x coordinate to center the name
            int dealerTextWidth = g.getFontMetrics().stringWidth(name);
            int x = (WINDOW_WIDTH - dealerTextWidth) / 2;
            g.drawString(name, x, PLAYER_CARDS_Y - 10);
        }
    }
}
