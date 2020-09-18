package com.dtcc.checkers;

import static org.junit.Assert.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UtilityTest {
	Model model;
	String[][] board;
	Move move;
	
	@BeforeEach
	public void setUp() {
	model = new Model();
	board=new String[8][8];
	move=new Move();
	}
	
	@Test
	public void testPawnCanTake3orMoreSqaureJumps() {
		board=model.getBoard();
		move.startX=3;move.startY=2;
		move.endX=6;move.endY=6;
		assertFalse(Utility.checkProperMove(move, board));
	}
	
	@Test
	public void testRedPawnGoBackwards() {
		board=model.getBoard();
		move.startX=1;move.startY=2;
		move.endX=0;move.endY=1;
		assertFalse(Utility.checkProperMove(move, board));
	}
	@Test
	public void testIfRedPawnCanMoveInSameRowColumn() {
		board=model.getBoard();
		move.startX=4;move.startY=2;
		move.endX=4;move.endY=3;
		assertFalse(Utility.checkProperMove(move, board));
	}
	
	@Test
	public void testIfBlackPawnCanMoveInSameRowColumn() {
		board=model.getBoard();
		move.startX=2;move.startY=5;
		move.endX=1;move.endY=6;
		assertFalse(Utility.checkProperMove(move, board));
	}
	
	@Test
	public void testIfRedPawnCanJumpOverRedPawn() {
		board=model.getBoard();
		board[2][3]="EMPTY";
		board[3][4]="R-P";
		board[4][5]="B-P";
		board[5][4]="EMPTY";
		move.startX=5;move.startY=2;
		move.endX=3;move.endY=4;
		assertFalse(Utility.checkProperMove(move, board));
	}
	@Test
	public void testIfPawnIsAlreadyPresent() {
		board=model.getBoard();
		board[2][5]="EMPTY";
		board[3][4]="R-P";
		move.startX=4;move.startY=3;
		move.endX=2;move.endY=5;
		assertFalse(Utility.checkProperMove(move, board));
	}
	@Test
	public void testCanJumpOverEmptySquare() {
		board=model.getBoard();
		move.startX=3;move.startY=2;
		move.endX=1;move.endY=4;
		assertFalse(Utility.checkProperMove(move, board));
	}
	
	@Test
	public void testIllegalMove1() {
		board=model.getBoard();
		move.startX=5;move.startY=2;
		move.endX=3;move.endY=3;
		assertFalse(Utility.checkProperMove(move, board));
	}
	
	@Test
	public void testIllegalMove2() {
		board=model.getBoard();
		move.startX=1;move.startY=2;
		move.endX=0;move.endY=4;
		assertFalse(Utility.checkProperMove(move, board));
	}
}
