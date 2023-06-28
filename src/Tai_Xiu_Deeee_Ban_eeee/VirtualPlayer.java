package Tai_Xiu_Deeee_Ban_eeee;

public class VirtualPlayer extends Player {
	private String emotion;
	public VirtualPlayer(String name, String emotion) {
		super(name);
		this.emotion = emotion;
		// TODO Auto-generated constructor stub
	}
	public String getEmotion() {
		return emotion;
	}
	@Override
	public String toString() {
		return "VirtualPlayer [name =" + this.getName() + " ,score=" + this.getScore() +  " ,emotion=" + emotion + "]";
	}

}
