package TicTacToe;

import java.util.ArrayList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class UI {
	protected ArrayList<RectHV> board;
	private void initializeBoard()
	{
		board = new ArrayList<RectHV>();
		for(int x = 0; x < 600; x += 200)
		{
			for(int y = 0; y < 600; y += 200)
			{
				RectHV rect = new RectHV(x, y, x + 200, y + 200);
				board.add(rect);
			}
		}
	}
	public void drawBoard()
	{
		for(RectHV r : board)
		{
			r.draw();
		}
	}
	private UI()
	{
		initializeBoard();
	}
	public void drawX(RectHV rect)
	{
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.setPenRadius(0.03);
		StdDraw.line(rect.xmin() + 50, rect.ymin() + 50, rect.xmax() - 50, rect.ymax() - 50);
		StdDraw.line(rect.xmin() + 150, rect.ymin() + 50, rect.xmax() - 150, rect.ymax() - 50);
		
	}
	public void drawCircle(RectHV rect)
	{
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.setPenRadius(0.03);
		StdDraw.circle(rect.xmax() - 100, rect.ymax() - 100, 50);
	}
	public void declareWinner(String lastWin, int lastMove)
	{
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setPenRadius(0.03);
		if(lastWin == "vertical")
		{
			// StdDraw.line(lastMove%3 + 50, rect.ymin() + 50, rect.xmax() - 50, rect.ymax() - 50);
		}
		else if(lastWin == "horizontal")
		{
			
		}
		else if(lastWin == "leftAngle")
		{
			StdDraw.line(50, 50, 550, 550);
		}
		else
		{
			StdDraw.line(50, 550, 550, 50);
		}
		
	}
	// old
	public static void drawBoard(double xmin, int ymin, double xmax, double ymax)
	{	
		   StdDraw.setPenColor(StdDraw.BLACK);
		   
		   // draw box
		   StdDraw.line(xmin, ymin, xmax, ymin);
		   StdDraw.line(xmax, ymin, xmax, ymax);
		   StdDraw.line(xmax, ymax, xmin, ymax);
		   StdDraw.line(xmin, ymax, xmin, ymin);
		   
		   // draw the lines
		   StdDraw.line(xmax/3.0, ymax, xmax/3.0, ymin);
		   StdDraw.line((xmax/3.0)*2.0, ymax, (xmax/3.0)*2.0, ymin);
		   
		   
		   StdDraw.line(xmin, (ymax/3.0)*2.0, xmax, (ymax/3.0)*2.0);
		   StdDraw.line(xmin, ymax/3.0, xmax, ymax/3.0);
	}
	public static void drawPoints(ArrayList<Point> points)
	{
		
	}
	public int getMove(Point2D p)
	{
		
		for(int i = 0; i < 9; i++)
		{
			if(board.get(i).contains(p))
				return i;
		}
		return -1;
	}
	public static void drawX(int x, int y)
	{
		int xV = x + 200;
		int yUp = y + 200;
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.line(x, y, xV, yUp);
		StdDraw.line(x, yUp, xV, y);
	}
	public static void main(String args[])
	{
		TicTacToe game = new TicTacToe();
		UI ui = new UI();
		StdDraw.setCanvasSize(600, 600);
		StdDraw.setXscale(-50, 650);
		StdDraw.setYscale(-50, 650);
		StdDraw.show();
		ui.drawBoard();
		
//		ui.drawX(ui.board.get(1));
//		ui.drawCircle(ui.board.get(3));
		ArrayList<RectHV> marked = new ArrayList<RectHV>();
		int move = 0;
		do
		{
			if(StdDraw.mousePressed())
			{
				int x = (int) StdDraw.mouseX();
				int y = (int) StdDraw.mouseY();
				StdOut.println(x + " " + y);
				Point2D p = new Point2D(x, y);
				move = ui.getMove(p);
				if(game.isMoveLegal(move))
				{
					if(game.getCurrentChar() == 'X')
						ui.drawX(ui.board.get(move));
					if(game.getCurrentChar() == 'O')
						ui.drawCircle(ui.board.get(move));
					
					game.makeMove(move, game.currentPlayer);
					if(game.checkForWinner())
					{
						game.declareWinner();
						ui.declareWinner(game.lastWin, move);
						// TODO UI declare
						break;
					}
					game.currentPlayer = (game.currentPlayer + 1) % 2;
					
				}
				else 
				{
					continue;
				}
			}
		}while(!game.checkForWinner());
	}
}
	
