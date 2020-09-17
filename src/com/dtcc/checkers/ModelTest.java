package com.dtcc.checkers;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModelTest {
	
	Model model;
	String[][] board;
	Move move;
	private Path workingDir;
	
	@BeforeEach
	public void setUp() {
	model = new Model();
	board=new String[8][8];
	move=new Move();
		this.workingDir = Path.of("", "src/com/dtcc/checkers");
	}
	
	
	@Test
	public void getBoradTest() {
		 String[][] expected = new String[8][8];
		 assertEquals(expected.length,model.getBoard().length);
	}
	@Test
	public void getBoradIndexTest() {
		 String expected = "R-P";
		String[][] actual = model.getBoard();
		 assertEquals(expected, actual[1][2]);
	}
	@Test
	public void getBoradIndexLastTest() {
		 String expected = "B-P";
		String[][] actual = model.getBoard();
		 assertEquals(expected, actual[7][4]);
	}
	
	@Test
	public void testFileExists()
	{
		File file =new File("src/com/dtcc/checkers/SavedBoard14.txt");
		assertTrue(file.exists());
	}
	@Test
	public void testContentOfFile() throws IOException {
		Path file = this.workingDir.resolve("SavedBoard14.txt");
		 String content = Files.readString(file);
		 assertEquals("EMPTY",content.subSequence(0,5));
		 assertEquals("R-P",content.substring(6,9));
	}

	@Test
	public void testOneDiagonalMoveForRedPawn() {
		board=model.getBoard();
		move.startX=3;move.startY=2;
		move.endX=2;move.endY=3;
		Utility.checkProperMove(move, board);
		model.update(move);
		assertEquals("R-P",board[3][2]);

	}
	
	@Test
	public void testOneDiagonalMoveForBlackPawn() {
		board=model.getBoard();
		move.startX=4;move.startY=5;
		move.endX=3;move.endY=4;
		Utility.checkProperMove(move, board);
		model.update(move);
		assertEquals("B-P",board[4][3]);
	}
	
	/*
	@Test
	public void testForBlackKing() {
		board=model.getBoard();
		move.startX=5;
		move.startY=2;
		move.endY=0;
		move.endX=3;
		board[move.startY][move.startX]="B-P";
		board[move.endY][move.endX]="EMPTY";
		Utility.checkProperMove(move, board);
		board=model.update(move);
		assertEquals("B-K",board[0][3]);
	}
*/
}