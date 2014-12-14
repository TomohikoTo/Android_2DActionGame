package project.monyako;

public class Monyako {

	//プレイヤー、もにゃこの情報
	public float 		x 			= 0; 		//x座標
	public float		y 			= 0;		//y座標
	public byte			breaker 	= 0; 
	public byte			color 		= 0; 		//もにゃこの色(キラストーンの使用で色が変わる)
	public byte			deathTimer 	= 0; 		//ゲームオーバーアニメーションの時間
	public byte 		effect 		= 0;		//キラストーンを使用した時のエフェクト
	public boolean		get 		= false; 	//もにゃこが鉱石を取った時のエフェクト 
	public byte 		ladderTimer = 0; 		//はしごを登るアニメーションの時間
	public byte 		walkTimer 	= 0; 		//歩くアニメーションの時間
	public byte			state 		= 0; 		//もにゃこの状態
	public boolean 		redState 	= false; 	//赤のキラストーンを使用後、赤である状態
	public boolean 		greenState 	= false; 	//緑のキラストーンを使用後、緑である状態
	public boolean 		blueState	= false; 	//青のキラストーンを使用後、青である状態
	
	public final static byte BREAK_RED		= 1;
	public final static byte BREAK_GREEN 	= 2;
	public final static byte BREAK_BLUE		= 3;
	public final static byte EFFECT_RED 	= 1;
	public final static byte EFFECT_GREEN 	= 2;
	public final static byte EFFECT_BLUE 	= 3;
	public final static byte RETURN_RED 	= 4;
	public final static byte RETURN_GREEN 	= 5;
	public final static byte RETURN_BLUE	= 6;
	public final static byte WHITE 			= 0;
	public final static byte SET_RED 		= 1;
	public final static byte SET_GREEN 		= 2;
	public final static byte SET_BLUE 		= 3;
	public final static byte MONYAKO_WALK 	= 0;
	public final static byte MONYAKO_UP 	= 1;
	public final static byte MONYAKO_DOWN 	= 2;
}
