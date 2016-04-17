package battelfield;


public class BattleField {
	private final int BF_WIDTH = 576;
	private final int BF_HEIGHT = 576;
	
	private 	String[][] battleField = {
			{" ", "B", "B", " ", " ", " ", "B", "B", "B"},
			{" ", " ", "B", " ", " ", " ", " ", " ", "B"},
			{"B", "B", "W", "W", " ", " ", "W", "W", "B"},
			{"B", "B", " ", " ", " ", " ", "W", "B", " "},
			{" ", " ", " ", " ", " ", "R", " ", " ", "W"},
			{"B", "R", "B", "B", "B", "R", "R", " ", "W"},
			{"B", " ", " ", " ", " ", " ", "R", " ", "B"},
			{"B", " ", " ", "B", "B", "B", " ", " ", "B"},
			{" ", " ", " ", "B", "E", "B", " ", " ", " "}
		};

	// b - кирпич, w - вода, r - скала, e - орел
	
	
	public String scanQuadrant(int v, int h){
		return battleField[v][h];
	}
	
	public int getBF_WIDTH() {
		return BF_WIDTH;
	}

	public int getBF_HEIGHT() {
		return BF_HEIGHT;
	}

	public void updateQuadrant(int v, int h, String object){
		battleField[v][h] = object;
	}
	
	public int getDimentionX(){
		return battleField.length;
	}
	
	public int getDimentionY(){
		return battleField.length;
	}
}