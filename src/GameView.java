import javax.swing.*;
import java.awt.*;
public class GameView extends JFrame {
    private static final int WINDOW_WIDTH = 600,
            WINDOW_HEIGHT = 600,
            PLAYER_CARDS_X = 300,
            PLAYER_CARDS_Y = 400,
            DEALER_CARDS_Y = 100,
            CARD_WIDTH = 72,
            CARD_HEIGHT = 96;


    private Image[] background;
    private Game game;
    private Image[] cards;

    public GameView(Game a) {
        this.game = a;
        background = new Image[5];
        background[0] = new ImageIcon("Resources/Backgrounds/BLACKJACK.png").getImage();
        background[1] = new ImageIcon("Resources/Backgrounds/bet.png").getImage();
        background[2] = new ImageIcon("Resources/Backgrounds/green.png").getImage();
        background[3] = new ImageIcon("Resources/Backgrounds/win.png").getImage();
        background[4] = new ImageIcon("Resources/Backgrounds/lose.png").getImage();
        cards = new Image[53];
        for (int i = 1; i < 53; i++) {
            cards[i- 1] = new ImageIcon("Resources/Cards/" + i + ".png").getImage();
        }
        cards[52] = new ImageIcon("Resources/Cards/back.png").getImage();
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setTitle("BLACK JACK");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        drawBackground(g);
        drawCards(g);
    }

    public void drawBackground(Graphics g) {
        int stage = game.getStage();
        g.drawImage(background[stage], 0, 0, this);
    }

    public void drawCards(Graphics g) {
        if (game.getStage() == 2) {
            // Draw player's cards
            int playerCardX = 0;
            if(game.getPlayerHand().size() % 2 ==0)
            {
                playerCardX = PLAYER_CARDS_X - (game.getPlayerHand().size()/2)*77;
            }
            else {
                playerCardX = PLAYER_CARDS_X - (game.getPlayerHand().size()/2)*82 - 36;
            }
            for (Card card : game.getPlayerHand()) {
                g.drawImage(card.getCardImage(), playerCardX, PLAYER_CARDS_Y, CARD_WIDTH, CARD_HEIGHT, this);
                playerCardX += CARD_WIDTH + 10;
            }

            // Draw dealer's cards
            g.drawImage(cards[52], PLAYER_CARDS_X - 77, DEALER_CARDS_Y, CARD_WIDTH, CARD_HEIGHT,  this);
            g.drawImage(game.getDealerHand().get(1).getCardImage(), PLAYER_CARDS_X + 5, DEALER_CARDS_Y, CARD_WIDTH, CARD_HEIGHT,  this);

        }
    }
}
