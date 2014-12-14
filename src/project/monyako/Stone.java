package project.monyako;

import java.util.Random;

public class Stone extends Stage {
	 
	/*[]の左は鉱石の種類、[]の右は鉱石の個体番号 
	* 鉱石の種類番号: 0 = low, 1 = middle, 2 = high, 3 = red, 4 = green, 5 = blue
	* low = 銀色の鉱石 低ポイント　 middle = 金色の鉱石 中ポイント　high = 虹色の鉱石 高ポイント
	* red = 赤色の鉱石 赤色のトラップ回避に使う　 
	* green = 緑色の鉱石 緑色のトラップ回避に使う
	* blue = 青色の鉱石 青色のトラップ回避に使う
	* 赤、青、緑の鉱石はゲームの呼称上、キラストーンと言う表記をする
	* 画像の表示位置は上段45、中段275、下段505
	*/
	public boolean[][]		kind	 	= new boolean[6][4];	//鉱石が存在しているか判定する為の変数
	public float[][] 		x 			= new float[6][4];		//鉱石のx座標
	public float[][]		y 			= new float[6][4];		//鉱石のy座標
	public int[]			have 		= new int[6];			//鉱石の取得数(リザルト画面での獲得数表示に使用)
	public byte[] 			tune	 	= new byte[6];			//鉱石の配置被りでの調整用の変数
	public byte[][] 		timer 		= new byte[6][5];		//鉱石のアニメーション表示用
	public byte				haveLimit	= 99;					//鉱石獲得数の上限。レイアウトの都合上、百の桁がないようにするため
	public int 				lowPoint 	= 65;					//銀色の鉱石のポイント
	public int 				middlePoint = 350;					//金色の鉱石のポイント
	public int 				highPoint 	= 650;					//虹色の鉱石のポイント
	public int 				kiraPoint 	= 455;					//キラストーンのポイント
	public byte 			countStock 	= 0;					//現在、保有しているキラストーンのための変数 
	public int[] rndY = new int[9] ;													//2個まで保有出来、それ以上は上書きされる
	public byte				leftStock 	= 0;					//2個持てるうちの左側用の変数
	public byte				rightStock 	= 0;					//2個持てるうちの右側用の変数
	public final static int STOCK_RED 	= 1;					//赤色の鉱石を保有している事を示すための定数
	public final static int STOCK_GREEN = 2;					//緑色の鉱石を保有している事を示すための定数
	public final static int STOCK_BLUE 	= 3;					//青色の鉱石を保有している事を示すための定数
	Random rnd = new Random();
	public int setKiraRnd = 0;
}
