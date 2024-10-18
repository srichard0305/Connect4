package io.github.StevenRichard;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class GameScreen implements Screen {

    OrthographicCamera camera;
    SpriteBatch batch;
    Sprite boardSprite, whiteChip, blackChip, arrowTurn, arrowSelect;

    ArrayList<Sprite> playerPieces;
    ArrayList<Sprite> compPieces;

    Rectangle col1, col2, col3, col4, col5, col6, col7, arrowRec;
    Game game;
    Viewport viewport;
    Stage stage;
    String [][] board;
    int COL = 7;
    int ROW = 6;
    boolean playerTurn;


    public GameScreen(Game game){
        this.game = game;
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        board = new String [COL][ROW];

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);

        batch = new SpriteBatch();
        boardSprite = new Sprite(new Texture("board.png"));
        whiteChip = new Sprite(new Texture("white.png"));
        whiteChip.setPosition(Gdx.graphics.getWidth()/2-250, Gdx.graphics.getHeight()/2+250);
        blackChip = new Sprite(new Texture("black.png"));
        blackChip.setPosition(Gdx.graphics.getWidth()/2+200, Gdx.graphics.getHeight()/2+250);
        arrowTurn = new Sprite(new Texture("arrow.png"));
        arrowTurn.setPosition(Gdx.graphics.getWidth()/2+120, Gdx.graphics.getHeight()/2+250);
        arrowSelect = new Sprite(new Texture("arrow.png"));
        arrowSelect.rotate(270);
        arrowRec= new Rectangle();
        arrowRec.setSize(75,75);

        col1 = new Rectangle();
        col1.setSize(100, 100);
        col1.setPosition(0, Gdx.graphics.getHeight()/2+155);

        col2 = new Rectangle();
        col2.setSize(100, 100);
        col2.setPosition(100, Gdx.graphics.getHeight()/2+155);

        col3 = new Rectangle();
        col3.setSize(100, 100);
        col3.setPosition(200, Gdx.graphics.getHeight()/2+155);

        col4 = new Rectangle();
        col4.setSize(100, 100);
        col4.setPosition(300, Gdx.graphics.getHeight()/2+155);

        col5 = new Rectangle();
        col5.setSize(100, 100);
        col5.setPosition(400, Gdx.graphics.getHeight()/2+155);

        col6 = new Rectangle();
        col6.setSize(100, 100);
        col6.setPosition(500, Gdx.graphics.getHeight()/2+155);

        col7 = new Rectangle();
        col7.setSize(100, 100);
        col7.setPosition(600, Gdx.graphics.getHeight()/2+155);

        playerPieces = new ArrayList<>();
        compPieces = new ArrayList<>();

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        stage = new Stage(viewport);

        playerTurn = true;

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(68/255f, 85/255f, 155/255f, 1f);

        arrowSelect.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight()/2+160);
        arrowRec.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight()/2+160);

        if(playerTurn) {
            if (Gdx.input.justTouched()) {
                setPlayerPiece();
            }
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
        arrowTurn.draw(batch);
        arrowSelect.draw(batch);
        batch.end();

    }

    private void setPlayerPiece() {
        if(arrowRec.overlaps(col1)){
            for(int i = 0; i < board[0].length; i++){
                if(board[0][i] == null) {
                    board[0][i] = "W";
                    Sprite chip = new Sprite(new Texture("white.png"));
                    if(i == 0) {
                        chip.setPosition(30, i + 12);
                    }
                    else{
                        Sprite temp = playerPieces.get(i-1);
                        chip.setPosition(30, temp.getY()+80);
                    }
                    playerPieces.add(chip);
                    break;
                }
            }
        }
        else if(arrowRec.overlaps(col2)){
            for(int i = 0; i < board[1].length; i++){
                if(board[1][i] == null) {
                    board[1][i] = "W";
                    Sprite chip = new Sprite(new Texture("white.png"));
                    if(i == 0) {
                        chip.setPosition(140, i + 12);
                    }
                    else{
                        Sprite temp = playerPieces.get(i-1);
                        chip.setPosition(140, temp.getY()+80);
                    }
                    playerPieces.add(chip);
                    break;
                }
            }
        }
        else if(arrowRec.overlaps(col3)){
            for(int i = 0; i < board[2].length; i++){
                if(board[2][i] == null) {
                    board[2][i] = "W";
                    Sprite chip = new Sprite(new Texture("white.png"));
                    if(i == 0) {
                        chip.setPosition(250, i + 12);
                    }
                    else{
                        Sprite temp = playerPieces.get(i-1);
                        chip.setPosition(250, temp.getY()+80);
                    }
                    playerPieces.add(chip);
                    break;
                }
            }
        }
        else if(arrowRec.overlaps(col4)){
            for(int i = 0; i < board[3].length; i++){
                if(board[3][i] == null) {
                    board[3][i] = "W";
                    Sprite chip = new Sprite(new Texture("white.png"));
                    if(i == 0) {
                        chip.setPosition(360, i + 12);
                    }
                    else{
                        Sprite temp = playerPieces.get(i-1);
                        chip.setPosition(360, temp.getY()+80);
                    }
                    playerPieces.add(chip);
                    break;
                }
            }
        }
        else if(arrowRec.overlaps(col5)){
            for(int i = 0; i < board[4].length; i++){
                if(board[4][i] == null) {
                    board[4][i] = "W";
                    Sprite chip = new Sprite(new Texture("white.png"));
                    if(i == 0) {
                        chip.setPosition(470, i + 12);
                    }
                    else{
                        Sprite temp = playerPieces.get(i-1);
                        chip.setPosition(470, temp.getY()+80);
                    }
                    playerPieces.add(chip);
                    break;
                }
            }
        }
        else if(arrowRec.overlaps(col6)){
            for(int i = 0; i < board[5].length; i++){
                if(board[5][i] == null) {
                    board[5][i] = "W";
                    Sprite chip = new Sprite(new Texture("white.png"));
                    if(i == 0) {
                        chip.setPosition(580, i + 12);
                    }
                    else{
                        Sprite temp = playerPieces.get(i-1);
                        chip.setPosition(580, temp.getY()+80);
                    }
                    playerPieces.add(chip);
                    break;
                }
            }
        }
        else if(arrowRec.overlaps(col7)){
            for(int i = 0; i < board[6].length; i++){
                if(board[6][i] == null) {
                    board[6][i] = "W";
                    Sprite chip = new Sprite(new Texture("white.png"));
                    if(i == 0) {
                        chip.setPosition(690, i + 12);
                    }
                    else{
                        Sprite temp = playerPieces.get(i-1);
                        chip.setPosition(690, temp.getY()+80);
                    }
                    playerPieces.add(chip);
                    break;
                }
            }
        }
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
