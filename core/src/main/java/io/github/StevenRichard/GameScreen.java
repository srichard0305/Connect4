package io.github.StevenRichard;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class GameScreen implements Screen {

    OrthographicCamera camera;
    SpriteBatch batch;
    Sprite boardSprite, whiteChip, blackChip, arrowSelect;
    ArrayList<Sprite> playerPieces;
    ArrayList<Sprite> compPieces;
    Rectangle col1, col2, col3, col4, col5, col6, col7, arrowRec;
    Game game;
    Viewport viewport;
    Stage stage;
    String [][] board;
    final int COL = 7;
    final int ROW = 6;
    boolean playerTurn;
    GameOverScreen gameOverScreen;
    boolean win;
    MiniMax minimax;

    public GameScreen(Game game){
        this.game = game;
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        board = new String [ROW][COL];
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                board[i][j] = "";
            }
        }

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);

        batch = new SpriteBatch();
        boardSprite = new Sprite(new Texture("board.png"));
        whiteChip = new Sprite(new Texture("white.png"));
        whiteChip.setPosition(Gdx.graphics.getWidth()/2-250, Gdx.graphics.getHeight()/2+250);
        blackChip = new Sprite(new Texture("black.png"));
        blackChip.setPosition(Gdx.graphics.getWidth()/2+200, Gdx.graphics.getHeight()/2+250);
        arrowSelect = new Sprite(new Texture("arrow.png"));
        arrowSelect.rotate(270);
        arrowRec= new Rectangle();
        arrowRec.setSize(75,75);

        col1 = new Rectangle();
        col1.setSize(114, 80);
        col1.setPosition(0, Gdx.graphics.getHeight()/2+155);

        col2 = new Rectangle();
        col2.setSize(114, 80);
        col2.setPosition(114, Gdx.graphics.getHeight()/2+155);

        col3 = new Rectangle();
        col3.setSize(114, 80);
        col3.setPosition(228, Gdx.graphics.getHeight()/2+155);

        col4 = new Rectangle();
        col4.setSize(114, 80);
        col4.setPosition(342, Gdx.graphics.getHeight()/2+155);

        col5 = new Rectangle();
        col5.setSize(114, 80);
        col5.setPosition(456, Gdx.graphics.getHeight()/2+155);

        col6 = new Rectangle();
        col6.setSize(114, 80);
        col6.setPosition(570, Gdx.graphics.getHeight()/2+155);

        col7 = new Rectangle();
        col7.setSize(114, 80);
        col7.setPosition(684, Gdx.graphics.getHeight()/2+155);

        playerPieces = new ArrayList<>();
        compPieces = new ArrayList<>();

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        stage = new Stage(viewport);

        playerTurn = true;
        win = false;
        minimax = new MiniMax();
        gameOverScreen = new GameOverScreen(game);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(68/255f, 85/255f, 155/255f, 1f);

        arrowSelect.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight()/2+160);
        arrowRec.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight()/2+160);

        if(playerTurn) {
            if (Gdx.input.justTouched()) {
                setPlayerPiece();
                playerTurn = false;
            }
        }
        else{
            Node aiPiece = minimax.minimax(board, new Node(), 0, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
            setAIPiece(aiPiece);
            playerTurn = true;
        }

        batch.begin();
        for(Sprite s : playerPieces){
            s.draw(batch);
        }
        for(Sprite s : compPieces){
            s.draw(batch);
        }
        boardSprite.draw(batch);
        whiteChip.draw(batch);
        blackChip.draw(batch);
        arrowSelect.draw(batch);
        batch.end();

        win = checkWinningMove("W");
        if(win)
            gameOver();
        win = checkWinningMove("B");
        if(win)
            gameOver();

    }

    private void setPlayerPiece() {
        if(arrowRec.overlaps(col1)){
            for(int i = 0; i < board.length; i++){
                if(board[i][0].isEmpty()) {
                    board[i][0] = "W";
                    Sprite chip = new Sprite(new Texture("white.png"));
                    if(i == 0) {
                        chip.setPosition(30, i + 12);
                    }
                    else{
                        chip.setPosition(30, (i*80) + 12);
                    }
                    playerPieces.add(chip);
                    break;
                }
            }
        }
        else if(arrowRec.overlaps(col2)){
            for(int i = 0; i < board.length; i++){
                if(board[i][1].isEmpty()) {
                    board[i][1] = "W";
                    Sprite chip = new Sprite(new Texture("white.png"));
                    if(i == 0) {
                        chip.setPosition(140, i + 12);
                    }
                    else{
                        chip.setPosition(140, (i*80) + 12);
                    }
                    playerPieces.add(chip);
                    break;
                }
            }
        }
        else if(arrowRec.overlaps(col3)){
            for(int i = 0; i < board.length; i++){
                if(board[i][2].isEmpty()) {
                    board[i][2] = "W";
                    Sprite chip = new Sprite(new Texture("white.png"));
                    if(i == 0) {
                        chip.setPosition(250, i + 12);
                    }
                    else{
                        chip.setPosition(250, (i*80) + 12);
                    }
                    playerPieces.add(chip);
                    break;
                }
            }
        }
        else if(arrowRec.overlaps(col4)){
            for(int i = 0; i < board.length; i++){
                if(board[i][3].isEmpty()) {
                    board[i][3] = "W";
                    Sprite chip = new Sprite(new Texture("white.png"));
                    if(i == 0) {
                        chip.setPosition(360, i + 12);
                    }
                    else{
                        chip.setPosition(360, (i*80) + 12);
                    }
                    playerPieces.add(chip);
                    break;
                }
            }
        }
        else if(arrowRec.overlaps(col5)){
            for(int i = 0; i < board.length; i++){
                if(board[i][4].isEmpty()) {
                    board[i][4] = "W";
                    Sprite chip = new Sprite(new Texture("white.png"));
                    if(i == 0) {
                        chip.setPosition(470, i + 12);
                    }
                    else{
                        chip.setPosition(470, (i*80) + 12);
                    }
                    playerPieces.add(chip);
                    break;
                }
            }
        }
        else if(arrowRec.overlaps(col6)){
            for(int i = 0; i < board.length; i++){
                if(board[i][5].isEmpty()) {
                    board[i][5] = "W";
                    Sprite chip = new Sprite(new Texture("white.png"));
                    if(i == 0) {
                        chip.setPosition(580, i + 12);
                    }
                    else{
                        chip.setPosition(580, (i*80) + 12);
                    }
                    playerPieces.add(chip);
                    break;
                }
            }
        }
        else if(arrowRec.overlaps(col7)){
            for(int i = 0; i < board.length; i++){
                if(board[i][6].isEmpty()) {
                    board[i][6] = "W";
                    Sprite chip = new Sprite(new Texture("white.png"));
                    if(i == 0) {
                        chip.setPosition(690, i + 12);
                    }
                    else{
                        chip.setPosition(690, (i*80) + 12);
                    }
                    playerPieces.add(chip);
                    break;
                }
            }
        }

    }

    public void setAIPiece(Node aiPiece){
        if(aiPiece.y == 0){
            for(int i = 0; i < board.length; i++){
                if(board[i][0].isEmpty()) {
                    board[i][0] = "B";
                    Sprite chip = new Sprite(new Texture("black.png"));
                    if(i == 0) {
                        chip.setPosition(30, i + 12);
                    }
                    else{
                        chip.setPosition(30, (i*80) + 12);
                    }
                    compPieces.add(chip);
                    break;
                }
            }
        }
        else if(aiPiece.y == 1){
            for(int i = 0; i < board.length; i++){
                if(board[i][1].isEmpty()) {
                    board[i][1] = "B";
                    Sprite chip = new Sprite(new Texture("black.png"));
                    if(i == 0) {
                        chip.setPosition(140, i + 12);
                    }
                    else{
                        chip.setPosition(140, (i*80) + 12);
                    }
                    compPieces.add(chip);
                    break;
                }
            }
        }
        else if(aiPiece.y == 2){
            for(int i = 0; i < board.length; i++){
                if(board[i][2].isEmpty()) {
                    board[i][2] = "B";
                    Sprite chip = new Sprite(new Texture("black.png"));
                    if(i == 0) {
                        chip.setPosition(250, i + 12);
                    }
                    else{
                        chip.setPosition(250, (i*80) + 12);
                    }
                    compPieces.add(chip);
                    break;
                }
            }
        }
        else if(aiPiece.y == 3){
            for(int i = 0; i < board.length; i++){
                if(board[i][3].isEmpty()) {
                    board[i][3] = "B";
                    Sprite chip = new Sprite(new Texture("black.png"));
                    if(i == 0) {
                        chip.setPosition(360, i + 12);
                    }
                    else{
                        chip.setPosition(360, (i*80) + 12);
                    }
                    compPieces.add(chip);
                    break;
                }
            }
        }
        else if(aiPiece.y == 4){
            for(int i = 0; i < board.length; i++){
                if(board[i][4].isEmpty()) {
                    board[i][4] = "B";
                    Sprite chip = new Sprite(new Texture("black.png"));
                    if(i == 0) {
                        chip.setPosition(470, i + 12);
                    }
                    else{
                        chip.setPosition(470, (i*80) + 12);
                    }
                    compPieces.add(chip);
                    break;
                }
            }
        }
        else if(aiPiece.y == 5){
            for(int i = 0; i < board.length; i++){
                if(board[i][5].isEmpty()) {
                    board[i][5] = "B";
                    Sprite chip = new Sprite(new Texture("black.png"));
                    if(i == 0) {
                        chip.setPosition(580, i + 12);
                    }
                    else{
                        chip.setPosition(580, (i*80) + 12);
                    }
                    compPieces.add(chip);
                    break;
                }
            }
        }
        else if(aiPiece.y == 6){
            for(int i = 0; i < board.length; i++){
                if(board[i][6].isEmpty()) {
                    board[i][6] = "B";
                    Sprite chip = new Sprite(new Texture("black.png"));
                    if(i == 0) {
                        chip.setPosition(690, i + 12);
                    }
                    else{
                        chip.setPosition(690, (i*80) + 12);
                    }
                    compPieces.add(chip);
                    break;
                }
            }
        }
    }

    public boolean checkWinningMove(String piece){
        // check horizontal win
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL-3; j++){
                if(board[i][j].equals(piece) && board[i][j+1].equals(piece) && board[i][j+2].equals(piece) && board[i][j+3].equals(piece))
                    return true;
            }
        }
        //check vertical win
        for(int i = 0; i < ROW-3; i++){
            for(int j = 0; j < COL; j++){
                if(board[i][j].equals(piece) && board[i+1][j].equals(piece) && board[i+2][j].equals(piece) && board[i+3][j].equals(piece))
                    return true;
            }
        }
        //check diagonal
        for(int i = 0; i < ROW-3; i++){
            for(int j = 0; j < COL-3; j++){
                if(board[i][j].equals(piece) && board[i+1][j+1].equals(piece) && board[i+2][j+2].equals(piece) && board[i+3][j+3].equals(piece))
                    return true;
            }
        }
        //check diagonal
        for(int i = 0; i < ROW-3; i++){
            for(int j = COL-1; j > COL-3; j--){
                if(board[i][j].equals(piece) && board[i+1][j-1].equals(piece) && board[i+2][j-2].equals(piece) && board[i+3][j-3].equals(piece))
                    return true;
            }
        }
        return false;
    }

    private void gameOver() {
        game.setScreen(gameOverScreen);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void dispose() {

    }

}
