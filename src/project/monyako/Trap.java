package project.monyako;

import java.util.Random;

public class Trap extends Stage{
	
	/* 
	 * normalBlock[0]～[5] = 岩型のブロック
	 * normalBlock[6]～[11] = 刃型のブロック
	 * キラブロックは、キラストーンを使用する事で、回避が可能になるブロック
	 * kiraBlock[0] == 赤のキラブロック(炎)
	 * kiraBlock[1] == 緑のキラブロック(風)
	 * kiraBlock[2] == 青のキラブロック(水)
	*/
	public boolean[] 	normalBlock  = new boolean[12]; 	//通常のブロック、存在の有無を判別する変数
	public float[] 		normalBlockX = new float[12];		//通常のブロックのX座標を示す変数
	public float[] 		normalBlockY = new float[12];		//通常のブロックのY座標を示す変数
	public boolean[][]	kiraBlock 	 = new boolean[3][4];	//キラブロック 存在の有無判別用変数
	public float[][] 	kiraBlockX 	 = new float[3][4];		//キラブロックのX座標を示す変数
	public float[][] 	kiraBlockY 	 = new float[3][4];		//キラブロックのY座標を示す変数
	public int 			endlessRnd	 = 0;	 				//エンドレス用トラップの乱数格納変数
	public int[] rndY = new int[6];		
	public int[] rndX = new int[3];
	Random 				rnd 		 = new Random();
	

}
