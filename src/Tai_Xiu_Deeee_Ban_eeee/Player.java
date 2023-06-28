package Tai_Xiu_Deeee_Ban_eeee;

public class Player {
	private String name;
	private int score = 0;
	
    public Player(String name) {
        this.name = name;
    }

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", score=" + score + "]";
	}
	
}
