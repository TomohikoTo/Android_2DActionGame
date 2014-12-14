package project.monyako;


public class Score {

	/* コンボ用の変数群■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	 * 
	 * ゲームのやりこみ要素のひとつとして
	 * 鉱石を画面外へと見逃さず、連続で獲得すると
	 * 「コンボ」が発生し、コンボ数に応じて
	 * 冒険終了後のボーナス得点が貰える
	*/
	public byte 	combo ; 						//鉱石を見逃さず、連続で取った時の追加要素
	public byte 	comboTimer;						//コンボ継続時に画像を表示する用の変数
	public byte		limitCombo  = 99;		 		//コンボ数の上限
	public byte 	maxCombo 	= 0;				//コンボが途切れるまでに獲得したコンボ数を保存する用の変数
	public int[] 	playCombo 	= new int[2];		//冒険中でのコンボ数表示用の変数 
	public int 		point ;							//冒険中に獲得したポイント用の変数
	public int[] 	resultCombo = new int[2];  		//結果画面での最大コンボ数表示用の変数
	
	/* ノーマルモード用の変数群■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	 * ノーマルモードは、一定距離まで進行したらクリアとなり
	 * エンドレスモードは、トラップにぶつかったらクリアとなる
	 * 各々、それまでに獲得したポイントがランキング形式で保存され
	 * ノーマルモードとエンドレスモードとは
	 * ランキングは別に扱う
	*/
	public int[][] 	haveResult 			= new int[6][2];	//クリアまでに獲得した石の数を保存するための配列
	public int		normalLimit 		= 9999; 			//ノーマルモードでの点数の上限
	public int[] 	normalRankingPoint 	= new int[3];		//ノーマルモードのランキング計算用の配列
	public int[] 	normalScore 		= new int[4];		//ノーマルモードクリア時のスコアを保存する用の配列
	public int[][] 	normalRanking 		= new int[3][4];	//ノーマルモードで得たスコアをランキングで表示するための配列
		
	// エンドレスモード用の変数群■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	public int 		endlessLimit		 = 99999;			//エンドレスモードでの点数の上限
	public boolean 	endlessMode 	     = false;			//エンドレスモード
	public int[] 	endelessRankingPoint = new int[3];		//エンドレスのランキング計算用の配列
	public int[]	endlessScore		 = new int[5];		//エンドレスクリア時のスコア
	public int[][]	endlessRanking		 = new int[3][5];	//エンドレスのランキング
		
	//現在のコンボ数表示のための計算
	public void playComboCount(){
		playCombo[0] = combo / 10 ;
		playCombo[1] = combo ;
		playCombo[1] = playCombo[1] - playCombo[0] * 10; 
		
	}
	
	//リザルト画面での最大コンボ数表示のための計算
	public void comboCount(){
		resultCombo[0] = maxCombo / 10 ;
		resultCombo[1] = maxCombo ;
		resultCombo[1] = resultCombo[1] - resultCombo[0] * 10; 		
	}
	
	//所持点数の計算
		public void scoreCount(){
			
			//ノーマルモード用
			if(endlessMode == false){
				if(point > normalLimit ){
					point = normalLimit;
				}
				normalScore[0] = point / 1000;
				normalScore[1] = point / 100;
				normalScore[2] = point / 10;
				normalScore[3] = point ;
				normalScore[3] = normalScore[3] - normalScore[2] * 10;
				normalScore[2] = normalScore[2] - normalScore[1] * 10;
				normalScore[1] = normalScore[1] - normalScore[0] * 10;
			}else if(endlessMode == true){
		
			//エンドレスモード用	
				if(point > endlessLimit ){
					point = endlessLimit;
				}
				endlessScore[0] = point / 10000;
				endlessScore[1] = point / 1000;
				endlessScore[2] = point / 100;
				endlessScore[3] = point / 10;
				endlessScore[4] = point ;
				endlessScore[4] = endlessScore[4] - endlessScore[3] * 10;
				endlessScore[3] = endlessScore[3] - endlessScore[2] * 10;
				endlessScore[2] = endlessScore[2] - endlessScore[1] * 10;
				endlessScore[1] = endlessScore[1] - endlessScore[0] * 10;
			}	
		}
		
		
		/*
		 * 配列の左側は順位、配列の右側は各桁数の数字を格納
		 * 右側に格納された数字を参照する形で
		 * 対応した数字の画像を表示する
		 * ノーマルモードの右側:[0]=千の桁、[1]=百の桁、[2]=十の桁、[3]=一の桁
		 * エンドレスモードの右側:[0]=万の桁、[1]=千の桁、[2]=百の桁、[3]=十の桁、[4]=一の桁
		 */
		//ノーマルモードのランキングデータの計算
		public void nomalRankingLoad(){
			for( int i = 0; i < 3; i++){
				normalRanking[i][0] = normalRankingPoint[i] / 1000;
				normalRanking[i][1] = normalRankingPoint[i] / 100;
				normalRanking[i][2] = normalRankingPoint[i] / 10;
				normalRanking[i][3] = normalRankingPoint[i] ;
				normalRanking[i][3] = normalRanking[i][3] - normalRanking[i][2] * 10;
				normalRanking[i][2] = normalRanking[i][2] - normalRanking[i][1] * 10;
				normalRanking[i][1] = normalRanking[i][1] - normalRanking[i][0] * 10;
			}
		}
		
		//エンドレスモードのランキングデータの計算
		public void endlessRankingLoad(){
			for( int i = 0; i < 3; i++){
				endlessRanking[i][0] = endelessRankingPoint[i] / 10000;
				endlessRanking[i][1] = endelessRankingPoint[i] / 1000;
				endlessRanking[i][2] = endelessRankingPoint[i] / 100;
				endlessRanking[i][3] = endelessRankingPoint[i] / 10;
				endlessRanking[i][4] = endelessRankingPoint[i] ;
				endlessRanking[i][4] = endlessRanking[i][4] - endlessRanking[i][3] * 10;
				endlessRanking[i][3] = endlessRanking[i][3] - endlessRanking[i][2] * 10;
				endlessRanking[i][2] = endlessRanking[i][2] - endlessRanking[i][1] * 10;
				endlessRanking[i][1] = endlessRanking[i][1] - endlessRanking[i][0] * 10;
			}
		}
}
