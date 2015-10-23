package TicTacToe;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class TicTacToe {
	protected char[][] board;
	private  char[] player;
	public int currentPlayer;
	private int[] score;
	protected String lastWin;
	public void initializePlayer()
	{
		player = new char[2];		
		player[0] = 'O';
		player[1] = 'X';
		currentPlayer = 0;
		lastWin = "";
	}
	public void initializeBoard()
	{
		board = new char[3][3];
		int counter = 1;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				board[i][j] = (char) ('0' + counter);
				counter++;
			}
		}
	}
	
	// constructor
	public TicTacToe()
	{
		initializePlayer();
		initializeBoard();
		score = new int[2];
		score[0] = 0;
		score[1] = 0;
	}
	// reset the board
	public void cleanBoard()
	{
		int counter = 1;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				board[i][j] = (char) ('0' + counter);
				counter++;
			}
		}
	}
	// Checks if a player made a winning move
	public boolean checkForWinner()
	{
		boolean winnerFlag = false;
		boolean rightAngle = true;
		boolean leftAngle = true;
		
		for(int i = 0; i < 3; i++)
		{
			boolean vertical = true;
			boolean horizontal = true;
			for(int j = 0; j < 3; j++)
			{
				if(board[i][j] != player[currentPlayer])
				{
					vertical = false;
				}
				if(board[j][i] != player[currentPlayer])
				{
					horizontal = false;
				}
				
			}
			if(vertical || horizontal)
			{
				winnerFlag = true;
				if(vertical) lastWin = "vertical";
				else if(horizontal) lastWin = "horizontal";
				break;
			}
			
			if(board[i][i] != player[currentPlayer])
			{
				rightAngle = false;
			}
			if(board[i][2-i] != player[currentPlayer])
			{
				leftAngle = false;
			}
			
		}
		if(rightAngle || leftAngle)
		{
			if(rightAngle) lastWin = "leftAngle";
			else if(rightAngle) lastWin = "rightAngle";
			winnerFlag = true;
		}
		return winnerFlag;
	}
	public char getChar(int win)
	{
		return board[win/3][win%3];
	}
	 
	public boolean isMoveLegal(int move)
	{
		if(move < 0 || move > 8)
		{
			// TODO EXCEPTION OUT OF BOUNDS;
			StdOut.println("Out of bounds, try again");
			return false;
		}
		else if(board[move/3][move%3] == 'X' || board[move/3][move%3] == 'O')
		{
			StdOut.println("This move has already been made");
			return false;
		}
		return true;
	}
	public void makeMove(int move, int n)
	{
		board[move/3][move%3] = player[n];
		
	}
	public void declareWinner()
	{
		StdOut.println("Congratulations " + player[currentPlayer] + " you won!!");
		score[currentPlayer]++;
	}
	// Player X becomes O and vice versa
	public void switchPlayers()
	{
		char temp = player[0];
		player[0] = player[1];
		player[1] = temp;
	}
	public void displayScore()
	{
		StdOut.println("Player 1 has " + score[0] + " points");
		StdOut.println("Player 2 has " + score[1] + " points");
	}
	public char getCurrentChar()
	{
		return player[currentPlayer];
	}
	public void displayBoard()
	{
		StdOut.println();
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				StdOut.print(board[i][j] + " ");
			}
			StdOut.println();
		}
	};
	
	public static void start(TicTacToe game)
	{
		In in = new In();
		game.displayBoard();
		int turn = 0;
		while(true)
		{
			if(turn > 6)
			{
				StdOut.println("DRAW!");
				break;
			}
			StdOut.print("Player " + game.player[game.currentPlayer] + " select you're move: ");
			int move = in.readInt() - 1;
			if(game.isMoveLegal(move))
			{
				game.makeMove(move, game.currentPlayer);
				game.displayBoard();
				if(turn > 5 && game.checkForWinner())
				{
					game.declareWinner();
					break;
				}
				game.currentPlayer = (game.currentPlayer + 1) % 2;
				StdOut.println();
			}
			else 
			{
				continue;
			}
			turn++;
		}
	}
	public static void main(String[] args)
	{
		In in = new In();
		TicTacToe game = new TicTacToe();
		boolean cont = true;
		while(true)
		{
			start(game);
			game.displayScore();
			
			StdOut.println("Do you want to play another game? y/n ");
			String str = in.readString();
			if(str.contains("y"))
			{
				game.cleanBoard();
				game.switchPlayers();
				continue;
			}
			else
			{
				StdOut.println("Thanks for playing TicTacToe (c)");
				break;
			}
		}
			

		

	}
}

