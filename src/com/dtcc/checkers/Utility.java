package com.dtcc.checkers;

public class Utility {

	public static char player;
	public static void print2DArray(String[][] board) {
		
		for(int i=0;i<board.length;i++)
		{
			for(int j =0;j<board[i].length;j++)
			{
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("---------------------------------------------------------------------------------------");
		
	}
	
	public static boolean checkProperMove(Move move,String[][] board)
	{
		boolean isProper=false;
		if(Utility.getPlayer()== board[move.startY][move.startX].charAt(0))
		{
			if(board[move.startY][move.startX].charAt(0)=='R') 
			{
				System.out.println("It is other player's turn : Select Black piece."); isProper=false;
			}
			else 
			{
				System.out.println("It is other player's turn : Select Red piece."); isProper=false;
			}	
		}
		//clicking outside of the board.
		/*
		 * else if(move.startX>=8 || move.startX <0 || move.endX>=8 || move.endX<0 ) {
		 * System.out.println("You are clicking ourside of the board."); isProper=false;
		 * }
		 */
		//pawn moving in same column.
		else if(move.endX - move.startX==0 && (move.endY>=0 && move.endY<=7) ||
				move.endY - move.startY==0 && (move.endX>=0 && move.endX<=7))
		{
			System.out.println("Pawn can not move in same row or same column.");
			isProper=false; 
		}
		//else if(board[move.startY][move.startX]) {return false;} //STILL HAVE TO WORK : MORE ILLEGAL moves.
		
		else if(move.endY-move.startY >=3 || move.endY-move.startY <=-3 || move.endX-move.startX==3
				|| move.endX-move.startX <=-3)	//more than 2 squares move. 
		{
			System.out.println("Pawn can not take  3 or more squares jump.");
			isProper=false;
		}
		else if(board[move.startY][move.startX].equals("R-P") && move.endY <move.startY )	//Red pawn can not move backward
		{
			System.out.println("Red pawn can not go backward");
			isProper=false;
		}
		else if(board[move.startY][move.startX].equals("B-P") && move.endY > move.startY )	//Black pawn can not move backward
		{
			System.out.println("Black pawn can not go backward");
			isProper=false;
		}
		else if(!(board[move.endY][move.endX].equals("EMPTY")))	//if square already contains a pawn.
		{
			System.out.println("Pawn is present.");
			isProper=false;
		}
		//else if((move.endY - move.startY== 1) || (move.endY - move.startY== -1))
		else if((move.endY - move.startY== 1) && (move.endX - move.startX==2) || (move.endY - move.startY== 1) && (move.endX - move.startX==-2))
			{
				System.out.println("Illegal Move. Line 73");
				isProper=false;
			}
		else if((move.endY - move.startY== -1) && (move.endX - move.startX==2) || (move.endY - move.startY== -1) && (move.endX - move.startX==-2))
			{
				System.out.println("Illegal Move. Line 78");
				isProper=false;
			}
		//}
		else if(move.endY - move.startY== 2 || move.endY - move.startY== -2) //Pawns should not jump over same color pawn.
		{
			int startx=move.startX;
			int endx =move.endX;
			
			if(endx-startx==2 && move.endY - move.startY== 2) 
			{
				isProper= emptySquareJump(board,(move.startY)+1,(move.startX)+1); //Pawn should not jump over Empty Square
			
				if(board[move.startY][move.startX].charAt(0)=='R' && board[(move.startY)+1][(move.startX)+1].charAt(0)=='R')
				{
					System.out.println("Red pawn can not jump over same red color pawn...(right side move)"); 
					isProper =false;
				}
			}
			if(endx-startx==-2 && move.endY - move.startY== 2) 
			{
				isProper= emptySquareJump(board,(move.startY)+1,(move.startX)-1); //Pawn should not jump over Empty Square
				
				if(board[move.startY][move.startX].charAt(0)=='R' && board[(move.startY)+1][(move.startX)-1].charAt(0)=='R')
				{
					System.out.println("Red pawn can not jump over same red color pawn...(left side move)");
					isProper=false;	
				}
			}
			if(endx-startx==2 && move.endY - move.startY== -2) 
			{
				isProper= emptySquareJump(board,(move.startY)-1,(move.startX)+1); //Pawn should not jump over Empty Square
			
				if(board[move.startY][move.startX].charAt(0)=='B' && board[(move.startY)-1][(move.startX)+1].charAt(0)=='B')
				{
					System.out.println("Black pawn can not jump over same black color pawn....(right side move)"); 
					isProper=false;
				}
			}
			if(endx-startx==-2 && move.endY - move.startY== -2) 
			{
				isProper= emptySquareJump(board,(move.startY)-1,(move.startX)-1); //Pawn should not jump over Empty Square
				
				if(board[move.startY][move.startX].charAt(0)=='B' && board[(move.startY)-1][(move.startX)-1].charAt(0)=='B')
				{
					System.out.println("Black pawn can not jump over same black color pawn...(left side move)"); 
					isProper=false;
				}
			}
			if((move.endY - move.startY== 2 && endx-startx==1) || (move.endY - move.startY== 2 && endx-startx==-1))
			{
				System.out.println("Illegal move.");
				return false;
			}
			if((move.endY - move.startY== -2 && endx-startx==1) || (move.endY - move.startY== -2 && endx-startx==-1))
			{
				System.out.println("Illegal move.");
				return false;
			}
		}
		else if((move.endX - move.startX >2) || (move.endX - move.startX< -2)) //Illegal move.
		{	
			if((move.endX - move.startX >2) || (move.endX - move.startX< -2))
			{
				System.out.println("Illegal move. Line 142");
				isProper=false;
			}
		}
		else{isProper=true;}
		return isProper;
	}
	
	public static void setPlayer(char p)
	{
		player=p;
	}
	
	public static char getPlayer()
	{
		return player; 
	}
	
	public static boolean emptySquareJump(String[][] board, int x, int y)
	{
		boolean isNotEmpty=true;
		if(board[x][y].charAt(0)=='E') 
		{
			System.out.println("Pawn can not jump over empty square.");
			isNotEmpty=false;
		}
		else
		{
			isNotEmpty=true;
		}
		return isNotEmpty;
	}
	
	public static boolean isGameOver(String[][] board)
	{
		int rCount=0;
		int bCount=0;
		boolean isGameOver=false;
		//String winner=null;	
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				if(board[i][j].charAt(0)=='R') {rCount++;}
				else if(board[i][j].charAt(0)=='B') {bCount++;}
			}
		}
		if(rCount>0 && bCount==0)
		{
			System.out.println("RED Team is a winner.");
			isGameOver=true;	
		}
		else if(bCount>0 && rCount==0) 
		{
			System.out.println("BLACK Team is a winner.");
			isGameOver=true;
		}
		else 
		{
			isGameOver=false;
		}
		return isGameOver;
	}
}
