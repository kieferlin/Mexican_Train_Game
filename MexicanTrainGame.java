import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * this class combines the main functions of the Mexican Train Game (java code)  with the GUI of the game (javafx code)
 * @author Kiefer Lin
 */

public class MexicanTrainGame extends Application {
    // a field storing the number of dominoes in each player's hand
    private static final int handSize = 12;

    // a field storing the starting domino
    private static final int startingDomino = 9;

    private static final int buttonWidth = 80;

    // a field storing the number of players
    private static int numPlayers = 0;

    // a field storing a LinkedList of dominoes
    private LinkedList<Domino> drawPile;

    // a field storing a LinkedList of playerHands
    private LinkedList<Domino>[] playerHands;

    // a field storing a LinkedList of draw buttons
    private ArrayList<Button> drawButtonsOnLeft = new ArrayList<Button>();

    // a field storing a LinkedList of action buttons
    private ArrayList<Label> trainLabelsOnRight = new ArrayList<Label>();

    // a field storing a LinkedList of action buttons
    private ArrayList<Button> addButtonsOnRight = new ArrayList<Button>();

    // a field storing an array for all Domino trains (for each player)
    private ArrayList<DominoTrain> playerDominoTrains = new ArrayList<>();

    // a field storing the Mexican Train's DominoTrain
    private DominoTrain mexicanDominoTrain = new DominoTrain();

    // a field storing an ArrayList for the domino (button) being clicked on
    private ArrayList<Button> selectedDominoButtons = new ArrayList<>();

    // a field storing an array to keep track of which players are "active", designating whose turn it is
    private boolean[] activePlayers;

    // a field storing an int to keep track of which players are "active", designating whose turn it is
    private int activePlayerIndex;

    // a field storing the domino trains for each player (on the left of the screen)
    private HBox[] dominoesDisplayOnLeft;

    // a field storing the Label for the Mexican Train (on the right of the screen)
    private Label mexicanTrainLabelOnRight;

    // a field storing the Scene that displays when the game ends (not a winner)
    private Scene endGameScene;

    // a field storing the Scene that displays when the game is played
    private Scene primaryScene;

    // a field storing the Scene that displays when the game ends and there is a winner
    private Scene winnerScene;

    // a field storing the Stage that the scenes (play game, end scene, win scene) are displayed on
    private Stage primaryStage;

    // a field storing the Label that displays when the game ends and there is a winner
    private Label winnerPlayer;

    /**
     * a method that sets up the game by creating dominoes for the game and shuffling them then creating a draw pile
     * @param numPlayers the number of players in the game
     */
    @SuppressWarnings("unchecked")
    public void setupGame(int numPlayers) {
        // create the dominoes using two for loops to fill in the LinkedList
        drawPile = new LinkedList<>();
        for (int i = 0; i <= startingDomino; i++) {
            for (int j = i; j <= startingDomino; j++) {
                Domino newDomino = new Domino(i,j);
                // never inputs [9|9} double domino because it is "reserved" as the starting point
                if (i == startingDomino && j == startingDomino) {
                }
                else {
                    drawPile.add(newDomino);
                }
            }
        }

        // shuffle the dominoes
        Collections.shuffle(drawPile);

        // create player hands
        playerHands = new LinkedList[numPlayers];

        // add all dominoes into the draw pile
        System.out.println("drawPile size: " + drawPile.size());

        // a for loop that deals the dominoes to each player
        for (int i = 0; i < numPlayers; i++) {
            playerHands[i] = new LinkedList<>();
            dealDominos(playerHands[i]);
        }
    }

    /**
     * a method that deals dominoes to each player's hands
     * @param hand the player's hand that the dominoes are dealt to
     */
    private void dealDominos(LinkedList<Domino> hand) {
        // a for loop that deals dominoes to a player's hand from the draw pile
        for (int i = 0; i < handSize; i++) {
            if (!drawPile.isEmpty()) {
                hand.add(drawPile.poll());
            }
            else {
                System.out.println("Draw pile is empty.");
                break;
            }
        }
    }

    /**
     * creates the visual windows of the MexicanTrainGame
     * @param primaryStage the stage that contains all the windows of the game including player hands, Mexican Train, etc.
     */
    public void start(Stage primaryStage) {
        // set up the game before creating the GUI's for the game
        setupGame(numPlayers);

        // print the initial hands of each player in the console
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Player " + (i + 1) + "'s hand: " + playerHands[i]);
        }
        // creates a new HBox to display the player's hands (on the left side of the screen)
        dominoesDisplayOnLeft = new HBox[numPlayers];

        // creates a new boolean for each player (whether they are the current "active" player or not)
        activePlayers = new boolean[numPlayers];

        // create a variable for setting the height of the player's hand's displays
        int height = 200;

        // create a variable for setting the position for each player's hand
        int positionX = 0;
        int positionY = 0;

        // set up controls on the left
        setupLeftArea(positionX, positionY, height);

        // set up controls on the right
        setupRightArea(primaryStage);

        // sets up the primary (big) window on the right side of the screen
        primaryStage.setTitle("Mexican Train Game");
        primaryStage.setX(2000);
        primaryStage.setY(500);
        primaryStage.show();

        // add all players to the active players array
        for (int i = 0; i < numPlayers; i++) {
            activePlayers[i] = true;
        }

        // set the first player active
        Button firstButton = addButtonsOnRight.get(0);
        Label firstLabel = trainLabelsOnRight.get(0);
        setActiveButton(firstButton);
        setActiveLabel(firstLabel);
        activePlayerIndex = 0;
    }

    /**
     * sets up the "board" that will show the domino trains (on the right side of the screen)
     * @param primaryStage the primaryStage that displays the trains
     */
    private void setupRightArea(Stage primaryStage) {
        // create primary stage layout (VBox with rows for players)
        VBox layout = new VBox();

        // plus one row, the Mexican train
        for (int i = 1; i < numPlayers + 1; i++) {
            // player display
            BorderPane playerPane = new BorderPane();

            // creates labels for each player
            Label playerLabel = new Label("Player " + i);
            playerLabel.setMinWidth(120);

            // player domino display
            Label dominoTrainLabel = new Label("");
            dominoTrainLabel.setTextAlignment(TextAlignment.LEFT);
            dominoTrainLabel.setMaxWidth(400);
            trainLabelsOnRight.add(dominoTrainLabel);

            // button display, set the background to be lightgrey and disabled for all
            Button actionButton = new Button("Add Domino " + i);
            actionButton.setMinWidth(80);
            setInactiveButton(actionButton);
            addButtonsOnRight.add(actionButton);

            // creates a dominoTrain for each player
            DominoTrain playerDominoTrain = new DominoTrain();
            playerDominoTrains.add(playerDominoTrain);

            // initial setup for selectedDomainButton
            selectedDominoButtons.add(null);

            // creates the area where each player's train, add domino button, and player label is displayed (on the right side of the screen)
            playerPane.setLeft(playerLabel);
            playerPane.setCenter(dominoTrainLabel);
            playerPane.setRight(actionButton);

            // adding the playerPane to the layout
            layout.getChildren().add(playerPane);
        }

        // loop that adds event handlers to player's domino buttons
        for (int i = 0; i < numPlayers; i++) {

            // creates a final variable for the currentPlayerIndex, the current value of i
            final int currentPlayerIndex = i;

            // create the event handler for each action button for each player's domino train
            addButtonsOnRight.get(currentPlayerIndex).setOnAction(e ->
                    setActionOnAddButton(e, currentPlayerIndex, playerDominoTrains.get(currentPlayerIndex), trainLabelsOnRight.get(currentPlayerIndex)));
        }

        // creates a new BorderPane for the Mexican Train
        BorderPane mexicanTrainPane = new BorderPane();

        // creates a new Label for the Mexican Train
        Label trainLabel = new Label("Mexican Train");
        trainLabel.setMinWidth(110);

        // creates a Label for the Mexican Train dominoes to be displayed
        mexicanTrainLabelOnRight = new Label("");
        mexicanTrainLabelOnRight.setTextAlignment(TextAlignment.LEFT);
        mexicanTrainLabelOnRight.setMaxWidth(400);
        setActiveLabel(mexicanTrainLabelOnRight);

        // creates a Button to add Dominoes to the Mexican Train
        Button mexicanTrainAddButton = new Button("Add Domino");
        mexicanTrainAddButton.setMinWidth(110);
        setActiveButton(mexicanTrainAddButton);
        mexicanTrainAddButton.setOnAction(e -> setActionOnAddButton(e, activePlayerIndex, mexicanDominoTrain, mexicanTrainLabelOnRight));

        // the last "row" where all the components of Mexican Train are displayed
        mexicanTrainPane.setLeft(trainLabel);
        mexicanTrainPane.setCenter(mexicanTrainLabelOnRight);
        mexicanTrainPane.setRight(mexicanTrainAddButton);
        layout.getChildren().add(mexicanTrainPane);

        // an HBox to display when the game is over
        Label gameOverLabel = new Label("Game is Over!!!!");
        BorderPane gameOver = new BorderPane();
        gameOver.setCenter(gameOverLabel);
        endGameScene = new Scene(gameOver, 700, 400);

        // an HBox to display when the game is over
        winnerPlayer = new Label("Winner: Player 2!!!!");
        BorderPane winner = new BorderPane();
        winner.setCenter(winnerPlayer);
        winnerScene = new Scene(winner, 700, 400);

        // sets the Stage with all the components to play the game
        primaryScene = new Scene(layout, 700, 400);
        primaryStage.setScene(primaryScene);
        this.primaryStage = primaryStage;
    }

    /**
     * sets up each player's hands with all of their dominoes (on the left side of the screen)
     * @param positionX the x-coordinate on the screen for the player's hand GUI
     * @param positionY the y-coordinate on the screen for the initial position of the player's hand GUI
     * @param height the space (height) between each player's hand GUI
     */
    private void setupLeftArea (int positionX, int positionY, int height) {
        // a loop that creates a separate stage for each player's hand
        for (int i = 1; i <= numPlayers; i++) {
            Stage playerHandStage = new Stage();
            VBox playerHandLayout = new VBox();

            // display for domino's in each player's hand
            HBox dominoesDisplay = new HBox();
            dominoesDisplayOnLeft[i-1] = dominoesDisplay;

            // creates a "draw" button for each player
            Button drawButton = new Button("Draw " + i);
            drawButtonsOnLeft.add(drawButton);

            // creates a final variable for the currentPlayerIndex, the current value of i
            final int currentPlayerIndex = (i - 1);

            // creates an eventHandler for each individual button
            drawButton.setOnAction(e -> setActionOnDrawButton(e, currentPlayerIndex));

            // adds each player's Domino hands and each player's draw Button
            playerHandLayout.getChildren().addAll(dominoesDisplay, drawButton);
            Scene playerHandScene = new Scene(playerHandLayout, 200, 100);

            // a loop that creates buttons for each domino in the player's hand
            for (Domino domino : playerHands[i - 1]) {
                Button dominoButton = new Button(domino.toString());
                dominoButton.setMinWidth(buttonWidth);
                dominoButton.setOnAction(ee -> setActionOnDominoButton(ee, currentPlayerIndex));
                dominoesDisplayOnLeft[i-1].getChildren().add(dominoButton);
            }

            // displays all the components of each player's hand on to the Stage
            playerHandStage.setScene(playerHandScene);
            playerHandStage.setTitle("Player " + i + "'s Hand");
            playerHandStage.setX(positionX);
            playerHandStage.setY(positionY + height);
            playerHandStage.setWidth(1000);
            playerHandStage.setHeight(100);
            playerHandStage.show();
            positionY += height;
        }
    }

    /**
     * a method that handles when "Add Domino" Button is clicked
     * @param e the event that occurs when the "Add Domino" button is clicked
     * @param currentPlayerIndex the int representing who the current Player is (whose turn it is)
     * @param selectedDominoTrain the current Player's DominoTrain
     * @param selectedLabel the current Player's Label (that represents their Dominoes)
     */
    void setActionOnAddButton(Event e, int currentPlayerIndex, DominoTrain selectedDominoTrain, Label selectedLabel) {
        // prints out the current Player when clicked in the console
        System.out.println("currentPlayerIndex = " + currentPlayerIndex);

        // gets the Domino that each different player is currently selecting
        Button selectedButton = selectedDominoButtons.get(currentPlayerIndex);

        // when no Domino Button is being selected
        if (selectedButton == null) {
            System.out.println("no button is selected for player " + (currentPlayerIndex));
        }
        // when a Domino Button is clicked on
        else {
            // initially set "is legalMove" and "added" to be false (allow these to work LATER - below)
            boolean legalMove = false;
            boolean added = false;

            // gets the Domino Button that is being selected - converting the Button to a Domino using buttonToDomino method
            Domino selectedDomino = buttonToDomino(selectedButton);

            // an int storing the count/length of the DominoTrain before a Domino is added
            int beforeAddLength = selectedDominoTrain.length();

            // printed in console
            System.out.print("attempting to add the domino to the train: " + selectedDomino);

            // add the selected domino to the player's dominoTrain; if it is empty, add to the first node; otherwise append to the end of the train
            if (selectedDominoTrain.length() == 0) {
                // printed in console
                System.out.println("setting first node: " + selectedDomino);
                selectedDominoTrain.setFirstNode(new LLNode<Domino>(selectedDomino, null));
                legalMove = true;
            }
            else {
                if (selectedDominoTrain.canAdd(selectedDomino) == true) {
                    // printed in console
                    System.out.println("adding new node: " + selectedDomino);
                    selectedDominoTrain.addToEnd(selectedDomino);
                    legalMove = true;
                }
            }

            // update the train label (right side of the screen) after the attempt of adding the domino
            if (legalMove) {
                selectedLabel.setText(selectedDominoTrain.toString());

                // check if the addition really takes place by comparing the DominoTrain's before-adding count/length (above) to the current count/length
                added = selectedDominoTrain.length() > beforeAddLength;
            }

            // update the button list (left side of the screen) only if the Domino is really being added to the train
            if (added) {
                System.out.println("removing domino from the player's domino button: " + selectedDomino);
                // search for the corresponding domino in the current player's hand
                Iterator<Domino> iterator = playerHands[currentPlayerIndex].iterator();
                while (iterator.hasNext()) {
                    Domino domino = iterator.next();
                    if (domino.toString().equals(selectedButton.getText())) {
                        // remove the domino from the player's hand
                        iterator.remove();
                        // remove the domain from the player's UI
                        dominoesDisplayOnLeft[currentPlayerIndex].getChildren().remove(selectedButton);
                        break;
                    }
                }
            }
            else {
                System.out.println(selectedDomino + " didn't get add to the train!!!");
            }
            // checks whether the game is over by calling the checkGameStatus method
            checkGameStatus(currentPlayerIndex, selectedDominoTrain);
        }
    }

    /**
     * a method that handles when the "Draw" Button is clicked
     * @param e the event that occurs when the "Draw" button is clicked
     * @param currentPlayerIndex the int representing who the current Player is (whose turn it is)
     */
    public void setActionOnDrawButton(Event e, int currentPlayerIndex) {
        // checks if the player's hand is 12 dominoes or the drawPile is empty
        if (handSize == dominoesDisplayOnLeft[currentPlayerIndex].getChildren().size() || drawPile.isEmpty()) {
            // prints in the console
            System.out.println("Drawing is not allowed in this situation");
        }
        else {
            // gets a Domino from the drawPile
            Domino newDomino = drawPile.poll();

            // prints in the console
            System.out.println("new domino: " + newDomino);

            // adds the new drawn Domino into the current Player's hand
            Button b = dominoToButton(newDomino);
            b.setOnAction(ee -> setActionOnDominoButton(ee, currentPlayerIndex));
            dominoesDisplayOnLeft[currentPlayerIndex].getChildren().add(b);
            playerHands[currentPlayerIndex].add(newDomino);

            // checks whether the game is over by calling checkGameStatus
            checkGameStatus(currentPlayerIndex, playerDominoTrains.get(currentPlayerIndex));
        }
    }

    /**
     * a method that handles when the individual Domino Button is clicked
     * @param e the event that occurs when the individual Domino Button is clicked
     * @param currentPlayerIndex the int representing who the current Player is (whose turn it is)
     */
    public void setActionOnDominoButton(Event e, int currentPlayerIndex) {
        // read the domino from the button to make domino into button
        Button selectedButton = (Button)e.getSource();
        selectedDominoButtons.set(currentPlayerIndex, selectedButton);
        System.out.println("domino button clicked: " + selectedButton.getText());

        // changes the color of the "Domino" button when clicked (only one is highlighted while the others are transparent)
        for (Object b : dominoesDisplayOnLeft[currentPlayerIndex].getChildren()) {
            if (((Button)b).getText() == selectedButton.getText()) {
                ((Button)b).setStyle("-fx-background-color: yellow;");
            }
            else {
                ((Button)b).setStyle("-fx-background-color: transparent;");
            }
        }
    }

    /**
     * a method that checks whether the game is over (and we have a winner) or not
     * @param currentPlayerIndex the int representing who the current Player is (whose turn it is)
     * @param dominoTrain the current Player's DominoTrain
     */
    public void checkGameStatus(int currentPlayerIndex, DominoTrain dominoTrain) {
        // disable all Buttons and Labels and the next player Button will be enabled below
        for (int j=0; j<addButtonsOnRight.size(); j++) {
            setInactiveButton(addButtonsOnRight.get(j));
            setInactiveLabel(trainLabelsOnRight.get(j));
        }

        // check if the player has anymore Dominoes
        if (playerHands[currentPlayerIndex].size() == 0) {
            // prints out in console
            System.out.println("Winner: Player " + currentPlayerIndex + 1 + "!!!\n");

            winnerPlayer.setText("Winner: Player " + currentPlayerIndex + 1 + "!!!!");

            //declares a winner
            primaryStage.setScene(winnerScene);

        }
        else {
            // check if the player can still play, if not, they are out of the game
            if (endPlay(currentPlayerIndex, dominoTrain)) {
                activePlayers[currentPlayerIndex] = false;
            }

            // check if more than one active player left, if not, the game is over
            if (endGame()) {
                System.out.println("\n!!! Game is over !!!\n");

                // declares the game is over
                primaryStage.setScene(endGameScene);
            }
            else {
                int count = numPlayers-1;
                int nextPlayerIndex = currentPlayerIndex + 1;
                while(count != 0) {
                    // find the next player
                    if (nextPlayerIndex == numPlayers) {
                        nextPlayerIndex = 0;
                    }

                    // enable the current player's Button and DominoTrain
                    if (activePlayers[nextPlayerIndex] == true) {
                        setActiveButton(addButtonsOnRight.get(nextPlayerIndex));
                        setActiveLabel(trainLabelsOnRight.get(nextPlayerIndex));
                        activePlayerIndex = nextPlayerIndex;
                        count = 0;
                    }
                    else {
                        nextPlayerIndex = nextPlayerIndex + 1;
                        count = count + 1;
                    }
                }
            }
        }
    }

    /**
     * a method that sets the Button's "style" (color) to be active
     * @param button a Button to work with
     */
    public void setActiveButton(Button button) {
        button.setStyle("-fx-background-color: lightblue;");
        button.setDisable(false);
    }

    /**
     * a method that sets the Button's "style" (color) to be inactive
     * @param button a Button to work with
     */
    public void setInactiveButton(Button button) {
        button.setStyle("-fx-background-color: lightgrey;");
        button.setDisable(true);
    }

    /**
     * a method that sets the Label's "style" (color) to be active
     * @param label a Label to work with
     */
    public void setActiveLabel(Label label) {
        label.setStyle("-fx-border-color: green");
    }

    /**
     * a method that sets the Label's "style" (color) to be inactive
     * @param label a Label to work with
     */
    void setInactiveLabel(Label label) {
        label.setStyle("-fx-border-color: transparent");
    }

    /**
     * a method that determines if there are any active players left (players still in the game that are able to make their turn)
     * @return true if there is at least one player active and false if not
     */
    public boolean endGame() {
        int active = 0;
        for (int i=0; i<activePlayers.length; i++) {
            if (activePlayers[i]) {
                active++;
            }
        }
        return active == 1;
    }

    /**
     * a method that checks whether a Player's turn is over (they have 12 Dominoes in their hand but those Dominoes can't be played)
     * @param currentPlayerIndex the int representing who the current Player is (whose turn it is)
     * @param dominoTrain the DominoTrain representing the Player's hand
     * @return true if the Player has no moves left and false if they do
     */
    public boolean endPlay(int currentPlayerIndex, DominoTrain dominoTrain) {
        boolean hasPlay = false;
        boolean canDraw = false;
        // check if the player has any domino to play
        for (Domino dominoOnHand : playerHands[currentPlayerIndex]) {
            if(dominoTrain.canAdd(dominoOnHand)) {
                hasPlay = true;
                break;
            }
        }

        // check if the player can draw from pile
        if (playerHands[currentPlayerIndex].size() < handSize) {
            canDraw = true;
        }

        // if the play has at least one play or can draw from pile, move to the next player
        return (!hasPlay && !canDraw);
    }

    /**
     ** a method that creates a domino from a button
     * @param button a button used to create the domino
     * @return a Domino created from the Button
     */
    public Domino buttonToDomino(Button button) {
        // read the button to make button into domino
        String getText = button.getText();

        // remove the first character and last character (the brackets "[]")
        getText = getText.substring(1,getText.length() - 1);

        // splits the String to remove the "|" symbol separating the two integers
        String[] temp = getText.split("\\|");

        return new Domino(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
    }

    /**
     ** a method that creates a button from a domino
     * @param domino a domino used to create the button
     * @return Button a Button created from the Domino
     */
    public Button dominoToButton(Domino domino) {
        // read the domino to make domino into button
        Button b = new Button(domino.toString());

        // sets the width of the Button
        b.setMinWidth(buttonWidth);
        b.setStyle("-fx-background-color: transparent;");
        return b;
    }

    /**
     * the main method that runs the game
     * @param args the arguments that the user enters to set the number of players in the game
     */
    public static void main(String[] args) {
        // checks if there is anything entered into the argument
        if (args.length > 0) {
            try {
                // checks if the first argument is an integer
                int players = Integer.parseInt(args[0]);
                // checks if the first argument is an integer between 2 and 4 (players)
                if (players >= 2 && players <= 4) {
                    numPlayers = players;
                    launch(args);
                }
                else {
                    System.out.println("Invalid number of players. Please enter 2, 3, or 4.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number of players.");
            }
        }
        else {
            System.out.println("Invalid input. Please enter a number of players.");
        }
    }
}


