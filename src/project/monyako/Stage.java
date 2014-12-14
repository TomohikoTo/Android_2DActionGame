package project.monyako;

import java.util.Random;

public class Stage {
	
	Random setRnd = new Random();		//ステージの罠と鉱石を設定する用の乱数
	public int setTrap 		= 0;		//ステージの罠の配置を決める変数
	public float goalPoint 	= 33000;	//ノーマルモードでのゴールまでの距離、エンドレスでは周回ポイント
	public int setStone 	= 0;		//ステージの石の配置を決める変数
	public float startPoint 	= 0;		//洞窟の現在位置を示す変数
	public float cave 		= 1;		//洞窟の長さ調整用の変数
	public int LAP 			= 0;		//エンドレスモードで使用、周回数を示す変数
	
}
