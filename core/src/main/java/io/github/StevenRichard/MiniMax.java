/*
*  Author: Steven Richard
*  COMP 452
*  This program highlights the use of the minimax algorithm with alpha-beta pruning when playing the game connect4.
*  The algorithm checks all possible states of the board and chooses the best choice based on the
*  values in evaluation board. The max possible depth the algorithm checks in 4 rows deep.
*
* */

package io.github.StevenRichard;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Random;

public class MiniMax {
    final int ROW = 6;
    final int COL = 7;
    final String player = "W";
    final String aiPlayer = "B";
    int [][] evalBoard;

    Random rand;
    public MiniMax(){
        // evaluation board that is used to find the cost of each position
        evalBoard = new int[][] {{3,4,5,7,5,3,4},
                                 {4,6,8,10,8,6,4},
                                 {5,7,11,13,11,7,5},
                                 {5,7,11,13,11,7,5},
                                 {4,6,8,10,8,6,4},
                                 {3,4,5,7,5,3,4}};
        rand = new Random();

    }

    // the minimax algorithm with alpha-beta pruning
    public Node minimax(String [][] board, Node currentNode, int depth, int alpha, int beta, boolean maximizing){

        //check if board is in a winning state
        boolean playerWin = checkWinningMove(board, player);
        boolean aiPlayerWin = checkWinningMove(board, aiPlayer);

        // if reached the max depth or the player wins or the ai wins
        if(depth == 3) {
            currentNode.cost = evaluateBoard(board);
            return currentNode;
        }
        if(playerWin){
            currentNode.cost = Integer.MIN_VALUE;
            Gdx.app.log("", "Player Win");
            return currentNode;
        }
        if(aiPlayerWin){
            currentNode.cost = Integer.MAX_VALUE;
            return currentNode;
        }


        // get the valid locations
        ArrayList<Node> validLocations = getValidLocations(board);

        //check if no more valid locations
        if(validLocations.isEmpty()) {
            currentNode.cost = 0;
            return currentNode;
        }

        Node bestChoice;

        // ai players turn
        if(maximizing){
            int value = Integer.MIN_VALUE;
            int index = rand.nextInt(0, validLocations.size());
            bestChoice = validLocations.get(index);
            for(Node node : validLocations){
                //copy board and place piece
                String [][] copy = new String[ROW][COL];
                for(int i = 0; i < ROW; i++){
                    System.arraycopy(board[i], 0, copy[i], 0, COL);
                }
                copy[node.x][node.y] = aiPlayer;

                //recursively check tree
                Node temp = minimax(copy, node,depth+1, alpha, beta, false);

                // update cost if a better node is found
                if(temp.cost > value){
                    value = temp.cost;
                    bestChoice = temp;
                }

                // assign alpha value
                alpha = Integer.max(alpha, value);

                //prune this branch
                if(alpha >= beta)
                    break;
            }
            return bestChoice;
        }
        // if player turn
        else{
            int value = Integer.MAX_VALUE;
            int index = rand.nextInt(0, validLocations.size());
            bestChoice = validLocations.get(index);
            for(Node node : validLocations){
                //copy board and place piece
                String [][] copy = new String[ROW][COL];
                for(int i = 0; i < ROW; i++){
                    System.arraycopy(board[i], 0, copy[i], 0, COL);
                }
                copy[node.x][node.y] = player;

                //recursively check tree
                Node temp = minimax(copy, node,depth+1, alpha, beta, true);

                // update cost if a better node is found
                if(temp.cost < value){
                    value = temp.cost;
                    bestChoice = temp;
                }

                // assign alpha value
                beta = Integer.min(beta, value);

                //prune this branch
                if(alpha >= beta)
                    break;
            }
            return bestChoice;
        }
    }

    // evaluation function to determine who has the more strategic win
    private int evaluateBoard(String [][] board){
        int utility = 128;
        int sum = 0;
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                if(board[i][j].equals(player))
                    sum -= evalBoard[i][j];
                else if(board[i][j].equals(aiPlayer))
                    sum += evalBoard[i][j];

            }
        }
        return utility + sum;
    }

    // get valid locations that the player can drop a piece
    private ArrayList<Node> getValidLocations(String [][] board){
        ArrayList<Node> temp = new ArrayList<>();
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                if(board[i][j].isEmpty()) {
                    temp.add(new Node(i,j));
                }
            }
        }
        return temp;
    }

    // checks winning move
    private boolean checkWinningMove(String [][] board, String piece){
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


}
