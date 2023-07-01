package Tai_Xiu_Deeee_Ban_eeee;

import java.util.Random;

public class Dicess {
	private int[] probabilities = {1,2,3,4,5,6};
	private int[] weight = {16,16,16,16,16,16};
	
	public Dicess(int biggest_weight) {
		super();
		this.weight[biggest_weight] = 20;
	}
	
	public int[] getWeight() {
		return weight;
	}

	public int rollDice() {
		Random rand = new Random();
		int randNumber = rand.nextInt(100);
		int i;
		for (i=0; i<6; i++) {
			if (randNumber<weight[i])
				break;
			randNumber -= weight[i];
		}
		return probabilities[i];
	}
	
}
