package project.monyako;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends Activity {

	
	//
	//画像用変数群■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	//タイトル画面で使う〇〇〇〇
	public Bitmap 	  banner;									//ナビゲーションバーのない機種用に、表示の余りを隠すための画像
	public Bitmap     titleBackground;							//背景画像
	public Bitmap     titleLogo;								//タイトルロゴ
	public Bitmap[]   titleStart		= new Bitmap[2];		//スタートテキストその１
		
	//ロード画面で使う〇〇〇〇
	public Bitmap[]   loading			= new Bitmap[4];		//ロード中・・・
	public Bitmap[]   loadTips 			= new Bitmap[5];		//世界観を説明するためのテキスト5種
	
	//メニュー画面で使う	〇〇〇〇〇
	public Bitmap[]   closeIcon 		= new Bitmap[2];		//オプションやクレジットを閉じるためのボタン
	public Bitmap[]   credit 			= new Bitmap[10];		//クレジットで表示する画像
	public Bitmap[]   creditView 		= new Bitmap[2];		//クレジットを見るかどうかのアイコン
	public Bitmap     creditWindow;								//クレジット画面の窓枠	
	public Bitmap[]   endlessIcon 		= new Bitmap[4];		//エンドレスモード用のアイコン
	public Bitmap[]   normalMode 		= new Bitmap[2];		//ノーマルモード用のアイコン
	public Bitmap[]   menuAdventure 	= new Bitmap[2];		//冒険アイコン [0]が閉じてる[1]が開いてる
	public Bitmap[]   menuBackground	= new Bitmap[2];		//メニュー画面の背景
	public Bitmap	  menuBar;									//メニュー画面の飾り付け
	public Bitmap[]   menuEnd			= new Bitmap[2];		//ゲーム終了用アイコン
	public Bitmap[]   menuOption 		= new Bitmap[2];		//設定画面用アイコン
	public Bitmap[]   menuScore 		= new Bitmap[2];		//スコアアイコン
	public Bitmap[]   menuTutorial 		= new Bitmap[2];		//訓練アイコン
	public Bitmap	  modeWindow;								//冒険選択時のモード確認用の窓枠
	public Bitmap[]   optionOn 			= new Bitmap[2];		//BGMのONの状態を表す画像
	public Bitmap[]   optionOff 		= new Bitmap[2];		//BGMのOFFの状態を表す画像
	public Bitmap 	  optionWindow;								//オプション用の窓枠
	public Bitmap 	  scoreDelete;								//スコア消去確認時用の窓枠
	public Bitmap 	  voice;									//レヴィの台詞の枠
	public Bitmap[]   word 				= new Bitmap[5];		//レヴィの説明文
		
	//チェック画面で使う〇〇〇〇〇〇
	public Bitmap[]   checkYes			= new Bitmap[2];		//はいボタン
	public Bitmap[]   checkNo			= new Bitmap[2];		//いいえボタン
	public Bitmap	  checkWindow;								//確認用窓枠
	public Bitmap 	  endWindow;								//終了確認用窓枠
	
	//ランキング画面で使う〇〇〇〇〇
	public Bitmap     first;									//ランキング１位
	public Bitmap     second;									//ランキング２位
	public Bitmap     third;									//ランキング３位
	public Bitmap     homeKira;									//単位であるキラの文字画像
	public Bitmap[]   scoreBoard 		= new Bitmap[2];		//スコア画面用の板、[0]はノーマルモード[1]はエンドレスモード
	public Bitmap[]   scoreChange 		= new Bitmap[2];		//ノーマルとエンドレスを切り替える用のボタン
	public Bitmap[]   scoreNum 			= new Bitmap[10];		//ランキングの得点用画像
	
	//チュートリアル画面で使う	〇〇〇〇
	public Bitmap 	  finger;									//場所を示すための指画像
	public Bitmap 	  it;										//「ここ」という文字の画像
	public Bitmap 	  skipWindow;								//スキップの確認画面の窓枠
	public Bitmap 	  strong;									//強調
	public Bitmap 	  reviNormal;								//レヴィの表情「普通」
	public Bitmap	  reviSmile;								//レヴィの表情「笑い」
	public Bitmap	  reviSad;									//レヴィの表情「悲しい」
	public Bitmap	  reviShock;								//レヴィの表情「驚き」
	public Bitmap[]   textWindow		= new Bitmap[2];		//文章用の窓枠
	public Bitmap[]   text 				= new Bitmap[3];		//文章用の画像
	public Bitmap     thisTouch;								//「ここをタッチ」とタッチする場所を示す画像
	
	//プレイ画面で使う〇〇〇〇〇〇〇
	public Bitmap[][] comboNum 			= new Bitmap[3][10];	//コンボの数字、コンボの具合で色合いが変わる
	public Bitmap[]   comboText 		= new Bitmap[3];		//「コンボ」文字の画像
	public Bitmap 	  ground;									//足場用の画像
	public Bitmap 	  playStart;								//ゲーム開始時の「スタート」の文字
	public Bitmap 	  playBackground;							//洞窟の背景
	public Bitmap[]   speedUp			= new Bitmap[3] ;		//スピードアップを示す文字の画像

	//白色 0~3 赤色4~7 緑色8~11 青色12~15
	public Bitmap[]   monyakoWalk 		= new Bitmap[16];		//もにゃこが歩いてるアニメ用画像 
	public Bitmap[]   monyakoLadder 	= new Bitmap[16];		//もにゃこが梯子を昇ってるアニメ用画像
		
	//赤使用0~3 緑使用4~7 青使用8~11 
	//赤戻り12~15 緑戻り16~19 青戻り20~23 鉱石取得24~26
	public Bitmap[]   clear 			= new Bitmap[3];		//ゴール時の演出「Clear」の文字の画像アニメ
	public Bitmap[]   effect 			= new Bitmap[27];		//キラ関連、鉱石取得のエフェクト	
	public Bitmap[]   finish 			= new Bitmap[3];		//エンドレスモード終了時の演出[finish]の文字の画像アニメ
	public Bitmap     gameOver;									//ゲームオーバー文字画像
	public Bitmap[]   gate 				= new Bitmap[4];		//ゴール時の演出「ゴールゲート」が動いてる
	public Bitmap     imgLadder;								//はしご
	public Bitmap[][] imgStone 			= new Bitmap[6][5];		//各鉱石のアニメ 光り輝いている
	public Bitmap[]   monyakoDeath 		= new Bitmap[6];		//もにゃこ死亡時のアニメーション
	public Bitmap[]   revi 				= new Bitmap[4];		//ゴール時の演出「レヴィ」が手を振って、もにゃこをお迎え
	public Bitmap[]   touchHome			= new Bitmap[2];		//タッチしてメニュー画面へ遷移するよう促す文字
	
	//ユーザーインターフェイスアイコン〇〇〇〇〇〇〇〇〇〇〇〇〇〇〇
	public Bitmap[]   ladderRest 		 = new Bitmap[5];		//はしごの所有数の数字
	public Bitmap 	  skip;										//訓練、結果画面でスキップする用の画像
	public Bitmap[]   trapNormal		 = new Bitmap[2];		//通常トラップ
	public Bitmap[][] trapKira 			 = new Bitmap[3][4];	//キラトラップ アニメーションしてる 0赤、1緑、2青
	public Bitmap[]   uiContinueIcon	 = new Bitmap[2];		//ポーズ画面での「続ける]ボタン
	public Bitmap[]   uiEndIcon 		 = new Bitmap[2];		//ポーズ画面での「やめる」ボタン
	public Bitmap 	  uiKirastone;								//ストックしているキラストーンのアイコン周りの飾り
	public Bitmap 	  uiLadder;									//はしごの所有数表示用
	public Bitmap[]   uiPauseIcon 		 = new Bitmap[2];		//ポーズボタン
	public Bitmap 	  uiPauseWindow;							//ポーズ画面の窓枠
	public Bitmap[]   uiStone 			 = new Bitmap[3];		//ストックしているキラストーン 0赤、1緑、2青
		
	//リザルト画面で使う〇〇〇〇〇〇〇〇〇〇〇〇〇〇
	public Bitmap[]   bonus				 = new Bitmap[3];		//0発表前,1発表後(良スコア),2発表後(悪スコア)
	public Bitmap     multi;									//「x」かけるマーク　鉱石の保有数用
	public Bitmap[]   resultBackGround	 = new Bitmap[3];		//リザルトの背景,0発表前,1
	public Bitmap     resultWindow;								//リザルト用の板
	public Bitmap[]   resultNum 		 = new Bitmap[10];		//スコア用の数字
	public Bitmap[]   resultStone		 = new Bitmap[6];		//0銀,1金,2虹,3赤,4緑,5青 の鉱石
	public Bitmap     resultKira;								//単位「キラ」の文字画像
	public Bitmap     resultGet;								//「Get」の文字画像
	public Bitmap[]   resultHome 		 = new Bitmap[2];		//メニューへ戻るためのアイコン
	public Bitmap[]   stoneNum 			 = new Bitmap[10];		//鉱石の獲得数用の数字
	
	//表示位置関係■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	public int	 		backTune 		 = 0;			//速度上昇時の洞窟内の背景のズレ防止用
	public float[] 		backgroundX 	 = new float[3];	//洞窟の背景を動かす用の変数
	public float[] 		groundX 		 = new float[3];	//洞窟の足場を動かす用の変数
	public View 		mainView;						//全ての画面描写を行う変数
	Timer timer;										//再描画用タイマーを作成
	public Handler 		handler 		 = new Handler();	//ハンドラを作成
	public final static long MSEC 		 = 10;			//0.01秒毎にViewを更新するための変数
	Resources 			res; 							//リソースフォルダ(画像,音楽,その他)を参照するための変数

	
	//音楽
	public MediaPlayer 	musicClear;					//ゴール時のBGM
	public MediaPlayer 	musicHome;					//メニュー画面BGM
	public MediaPlayer	musicOver;					//ゲームオーバー画面BGM
	public MediaPlayer 	musicPlay;					//冒険画面BGM
	public MediaPlayer	musicResult;				//結果発表後のBGM
	public MediaPlayer	musicResultBefore;			//結果発表前のドラムロール
	public MediaPlayer 	musicTitle;					//タイトル画面BGM

	//効果音
	public SoundPool	se; 						//効果音関連のまとめておくところ sound effect の略称
	public int 			seClimb 		 = 0;		//梯子を昇る時のSE
	public int 			seClose 		 = 0;		//Xボタンアイコン押した時のSE
	public int 			seDamage 		 = 0;		//もにゃこが罠に当たった時のSE
	public int 			seGet 			 = 0;		//鉱石GET時のSE
	public int 			seRed 			 = 0;		//赤のキラストーンを使用時のSE(火)
	public int			seGreen 		 = 0;		//緑のキラストーンを使用時のSE(風)
	public int 			seBlue 			 = 0;		//青のキラストーンを使用時のSE(水)
	public int 			seOpenBox 		 = 0;		//メニュー画面の宝箱型アイコンの開いた時のSE
	public int 			sePause 		 = 0;		//ポーズボタンとスコア消去ウィンドウ出現時のSE
	public int			seResultAfter 	 = 0;		//結果発表時のドラムのシンバルSE
	public int 			seResultBadAfter = 0;		//結果発表時、スコアが悪い時のSE
	public int 			seReturn 		 = 0;		//キラトラップ通過時のSE
	public int 			seSetLadder 	 = 0;		//梯子を架けた時のSE
	public int			seWalk 			 = 0;		//もにゃこの足音
	public int 			seYes 			 = 0;		//選択ボタンの音
	public int 			seNo 			 = 0;		//選択ボタンでいいえを押した時の音
	
	public boolean		seClimbLoad	     = false;	//はしごを昇る時の、音の暴発防止
	public boolean 		seDamageLoad 	 = false;	//チュートリアルで罠にぶつかった時の、音の暴発防止
	public boolean 		seResultBadLoad  = false;	//リザルトで、スコアが悪い時の、音の暴発防止
	public byte 		seResultLoad 	 = 0;		//リザルトで音のタイミングを計るようようの変数
	public boolean 		seWalkLoad		 = false;	//歩く時の、音の暴発防止
	
	//変数一覧■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

	
	public boolean 		bonusCheck 		= false;
	public boolean	 	caveCheck 		= false; 				//洞窟の調整確認用の変数		
	public boolean		displayCheck	= false;				//解像度別、位置調整機能用変数
	public boolean		fadeCheck 		= false;				//画面がフェードアウト中か確認用
	public boolean 		loadCheck 		= false;				//訓練で罠の再生成防止	
	public boolean		loadDisplay 	= false;				//解像度調整の処理を行ったかの判定用
	public boolean		saveCheck 		= false;				//リザルト画面でセーブを行ったかの判定用	
	public byte 		speedCheck 		= 0;					//洞窟で一定ポイントに到達し、スピードアップするかの判定用
	public boolean[]	voiceCheck 		= new boolean[3];		//メニュー画面で台詞を喋ったかの判定用 起動初回限定
	
	public float 		tune 			= 1;					//解像度調整用変数
	public float		TouchX 			= 0;					//タッチしたX座標を保持する変数
	public float 		TouchY 			= 0;					//タッチしたY座標を保持する変数
	public float		TouchUPX		= 0;					//画面に触れた指を離した場所のX座標用変数
	public float 		TouchUPY 		= 0;					//画面に触れた指を離した場所のY座標用変数
	public float		moveSpeed		= 5;					//プレイヤーの移動スピード用の変数
	public int 			hdpiNaviX 		= 897;					//ナビゲーションバーありのhdpiの解像度機種のX座標
	public int 			xhdpiNaviX	 	= 1196;					//ナビゲーションバーありのxhdpiの解像度機種のX座標
	public int 			xxhdpiNaviX		= 1787;					//ナビゲーションバーありのxxhdpiの解像度機種のX座標
	public int 			hdpiX 			= 960;					//ナビゲーションバーなしのhdpiの解像度機種のX座標
	public int 			xhdpiX 			= 1280;					//ナビゲーションバーなしのxhdpiの解像度機種のX座標
	public int 			xxhdpiX 		= 1920;					//ナビゲーションバーなしのxxhdpiの解像度機種のX座標
	
	
	//ロード画面
	Random 				tipsRnd 		 = new Random();	//ロード画面のフレーバーテキスト表示用乱数
	public int 			goalMove 		 = 0;				//ゴールシーンの演出で移動する距離用の変数
	public int 			reviMove 	     = 0;				//レヴィのアニメーション用タイマー
	public int 			tips 			 = 0;				//tipsRndの乱数を保持するための変数
	
	//タイマー
	public short 		clearTimer		 = 0;				//冒険ノーマルモードクリア時のアニメーション用
	public byte 		creditStartTimer = 0;				//クレジット画面の始まる前の間を置くためのタイマー
	public short		creditTimer 	 = 254;				//クレジットの演出用タイマー
	public byte[] 		effectTimer 	 = new byte[2];		//キラストーン関連の演出用タイマー
	public int 			fadeTimer 		 = 0;				//画面のフェードアウト演出用タイマー
	public short 		gameOverTimer 	 = 0;				//ゲームオーバー演出用タイマー
	public byte 		getTimer		 = 0;				//鉱石取得時の演出用タイマー
	public short 		lightWait 		 = 0;				//文字表示の間を置くためのタイマー
	public short 		loadTimer 		 = 0;				//ロード画面の演出用タイマー
	public byte			rankingTimer 	 = 0;				//ランキングの順次表示用のタイマー
	public short 		resultTimer 	 = 0;				//結果画面の演出用タイマー
	public byte 		scoreClearTimer  = 0;				//スコア消去機能用タイマー
	public byte 		startTimer 		 = 0;				//ゲーム開始時の「Start」文字を表示するタイマー
	public short 		textTimer 		 = 0;				//文章が表示される時間用のタイマー
	public byte[][]		trapTimer		 = new byte[3][3];  //キラトラップのアニメーション用タイマー 0=赤,1=緑,2=青
	public byte 		noTimer 		 = 0;				//いいえボタンによる演出を画面遷移前に見えるようにするためのタイマー
	//難易度
	public float 				speedDifficulty  = 0;			//洞窟内進行速度保持用の変数
	public final static float SPEED_EASY   = 4;				//進行距離6000までの速さ
	public final static float SPEED_NORMAL = 6;				//進行距離6000から18000までの速さ
	public final static float SPEED_HARD   = 8;				//進行距離18000からゴールまでの速さ
	public final static float SPEED_ENDLESS_EASY   = 4;		//進行距離6000までの速さ
	public final static float SPEED_ENDLESS_NORMAL = 7;		//進行距離6000から18000までの速さ
	public final static float SPEED_ENDLESS_HARD   = 10;		//進行距離18000からゴールまでの速さ
	public float 				LAP 		 = 0;				//エンドレスでの周回数
	
	
	public int 		 loadCount 		= 0;
	public boolean[] loadStoneState = new boolean[6];
	public final static byte NOT = 0;			
	public final static byte SET = 1;
	public final static byte USED = 2;
		
	//画面サイズ用
	public int displayX = 0;
	public int displayY = 0;
	
	
	//ゲーム各画面の状態を表す変数と定数■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	/* 音楽の状態
	 * プリファレンスにbgmStateの状態を保存する
	 * これによりゲームを次回起動時に、前回設定した状態になる
	*/
	public int 				bgmState = 0; //BGMの管理用変数
	public final static int BGM_ON	 = 0; //音楽が流れる。
	public final static int BGM_OFF	 = 1; //音楽が流れない
	
	//ゲーム全体の管理
	public byte 			 gameState 		= 0; //gameStateの数値で、何画面なのかを管理
	public final static byte GAME_TITLE 	= 0; //タイトル画面
	public final static byte GAME_LOAD 		= 1; //ロード画面
	public final static byte GAME_MENU 		= 2; //メニュー画面
	public final static byte GAME_PLAY 		= 3; //冒険画面
	public final static byte GAME_TUTORIAL 	= 4; //訓練画面
	public final static byte GAME_RESULT 	= 5; //結果画面
	
	//メニュー画面関連
	public byte 			 creditState 	= 0; //クレジットの管理
	public byte 			 menuState 		= 0; //メニュー画面の管理
	public byte 			 scoreState		= 0; //ランキング管理用
	public final static byte MENU_NORMAL 	= 0; //初期状態
	public final static byte MENU_SCORE 	= 1; //スコア画面
	public final static byte MENU_TUTORIAL 	= 2; //訓練移行確認画面
	public final static byte MENU_OPTION 	= 3; //設定画面
	public final static byte MENU_END 		= 4; //ゲーム終了確認画面
	public final static byte MENU_CREDIT 	= 5; //クレジット画面
	public final static byte MENU_DELETE 	= 6; //スコア消去確認画面
	public final static byte MENU_MODE 		= 7; //冒険のモード確認画面
	public final static byte MENU_ADVENTURE = 8; //冒険移行確認画面
	public final static byte SCORE_NORMAL 	= 0; //ノーマルのランキング表示用
	public final static byte SCORE_ENDLESS 	= 1; //エンドレスのランキング表示用
	
	//冒険画面関連
	public byte 			 playState 		= 0; //冒険画面の管理
	public final static byte PLAY_READY 	= 0; //冒険開始前の間を置く
	public final static byte PLAY_NORMAL 	= 1; //冒険進行
	public final static byte PLAY_PAUSE 	= 2; //冒険一時停止
	public final static byte PLAY_OVER 		= 3; //ノーマルモードで罠に当たってゲーム終了
	public final static byte PLAY_GOAL 		= 4; //ノーマルモードでゴールに到着
	public final static byte PLAY_FINISH 	= 5; //エンドレスモードで罠に当たってゲーム終了
		
	//ゴール演出関連(冒険のノーマルモードで使用)
	public byte 			 goalState = 0;
	public final static byte GOAL_READY 	= 0; //レヴィとゴールゲートが出現
	public final static byte GOAL_GO 		= 1; //レヴィの方へ歩き始める
	public final static byte GOAL_UP 		= 2; //もにゃこが上段にいる場合、
	public final static byte GOAL_MIDDLE 	= 3; //もにゃこが中段にいる場合の処理
	public final static byte GOAL_DOWN 		= 4; //もにゃこが下段にいる場合、中段に移動する動きをする
	public final static byte GOAL_END		= 5; //もにゃこがレヴィと合流したらする演出
		
	//訓練画面関連
	public short 			 faseState 		= 0; //訓練の段階を管理
	public byte 			 tutorialState 	= 0; //訓練の段階の内訳
	public short  			 tutorialPause 	= 0; //訓練一時停止
	public short  			 tutorialSkip 	= 0; //訓練の段階を飛ばす
	public final static byte FASE_STONE 	= 1; //鉱石についての説明
	public final static byte FASE_LADDER 	= 2; //梯子についての説明
	public final static byte FASE_TRAP 		= 3; //罠についての説明
	public final static byte FASE_FREE 		= 4; //罠避けの練習
	public final static byte FASE_KIRA 		= 5; //キラ(鉱石＆罠)についての説明
	
	//結果画面関連
	public byte				 endCheck 		= 0; //ゲーム終了確認
	public byte 			 resultCheck 	= 0; //結果の内訳発表
	public byte			 	 resultState 	= 0; //結果画面の管理
	public final static byte STONE 			= 1; //鉱石の獲得数発表
	public final static byte BONUS 			= 2; //達成ボーナスの発表
	public final static byte KIRA 			= 3; //最後の桁以外を発表
	public final static byte RESULT_BEFORE 	= 0; //結果発表前
	public final static byte RESULT_AFTER 	= 1; //結果発表後
	public final static byte RESULT_END 	= 2; //ゲーム終了確認
	
	//アイコン＆ボタンの動き関連
	public byte 			 touchState 	= 0;	//ボタンの管理
	public final static byte TOUCH_OPTION 	= 1;	//設定ボタン
	public final static byte TOUCH_END 		= 2;	//終了ボタン
	public final static byte TOUCH_CLOSE 	= 3;	//閉じるボタン
	public final static byte TOUCH_CONTINUE = 4;	//続けるボタン
	public final static byte TOUCH_QUITE 	= 5;	//やめるボタン
	public final static byte TOUCH_PAUSE 	= 6;	//一時停止ボタン
	public final static byte TOUCH_VIEW 	= 7;	//見るボタン
	public final static byte TOUCH_CHANGE 	= 8; 	//切り替えボタン
	public final static byte TOUCH_SKIP_YES = 9;	//スキップ確認のはいボタン
	public final static byte TOUCH_SKIP_NO 	= 10;	//スキップ確認のいいえボタン
	public final static byte TOUCH_MENU 	= 11;	//メニュー画面移行ボタン
	public final static byte TOUCH_YES 		= 100;	//はいボタン
	public final static byte TOUCH_NO 		= 101;	//いいえボタン

	/* メニューでのアイコンの説明
	 * ゲーム起動毎に初期化される
	 * 対応したアイコンをタッチした際、初回時にのみ台詞が出る
	 * switch文で条件分岐している
	*/
	public byte 			 wordState 		 = 0; //説明文の管理
	public final static byte WORD_ADVENTURE1 = 1; //冒険の説明１
	public final static byte WORD_ADVENTURE2 = 2; //冒険の説明２
	public final static byte WORD_SCORE1 	 = 3; //スコアの説明１
	public final static byte WORD_SCORE2	 = 4; //スコアの説明２
	public final static byte WORD_TUTORIAL	 = 5; //訓練の説明

	//インスタンス生成
	Ladder  ladder  = new Ladder();	 //はしご
	Monyako monyako = new Monyako(); //もにゃこ（プレイヤー）
	Score 	score 	= new Score();	 //スコア
	Stage 	stage	= new Stage();	 //洞窟(ステージ)
	Stone 	stone 	= new Stone();	 //鉱石
	Trap 	trap 	= new Trap();	 //罠
	//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);    
		
		gameState = GAME_TITLE;			//ゲーム画面を初期状態にする
		mainView = new GameView(this); 	//GameViewのインスタンスmainViewを生成
		setContentView(mainView);		//mainViewをセットして表示
		
		//timerのインスタンスを生成し、再描画処理
		timer = new Timer(false);
		//「MSEC」ミリ秒おきにタスク(TimerTask)を実行
		timer.schedule(new TimerTask(){
			public void run(){
				handler.post(new Runnable(){
					public void run(){
						mainView.invalidate();
					}});
				}
		}, 0,MSEC);
		
	}
	
	class GameView extends View {

		public GameView(Context context) {
			super(context);
			backTune = 10;
			res = this.getContext().getResources(); //リソースフォルダ読み込み
			gameState = GAME_TITLE;	//ゲーム画面の状態を初期画面にする
			loadBGM();
			//効果音の読み込み
			se				  = new SoundPool(16, AudioManager.STREAM_MUSIC, 0);
			seClimb			  = se.load(context, R.raw.se_climb, 1); 
			seClose			  = se.load(context, R.raw.se_close, 1); 
			seDamage		  = se.load(context, R.raw.se_damage, 1); 
			seGreen			  = se.load(context, R.raw.se_green, 1); 
			seOpenBox		  = se.load(context, R.raw.se_box, 1); 
			sePause			  = se.load(context, R.raw.se_pause, 1); 
			seResultAfter 	  = se.load(context, R.raw.se_result_after, 1); 
			seResultBadAfter  = se.load(context, R.raw.se_result_bad, 1); 
			seReturn		  = se.load(context, R.raw.se_return, 1); 
			seSetLadder		  = se.load(context, R.raw.se_set_ladder, 1); 
			seWalk			  = se.load(context, R.raw.se_walk, 1); 
			seYes 			  = se.load(context, R.raw.se_yes, 1); 
			seNo			  = se.load(context, R.raw.se_no, 1); 
			seRed			  = se.load(context, R.raw.se_red, 1); 
			seGet			  = se.load(context, R.raw.se_get, 1); 
			seBlue			  = se.load(context, R.raw.se_blue, 1); 
			//楽曲の読み込み						
			musicTitle		  = MediaPlayer.create(context, R.raw.music_title); 
			musicHome		  = MediaPlayer.create(context, R.raw.music_home); 
			musicPlay		  = MediaPlayer.create(context, R.raw.music_play); 
			musicOver		  = MediaPlayer.create(context, R.raw.music_over); 
			musicClear		  = MediaPlayer.create(context, R.raw.music_clear); 
			musicResultBefore = MediaPlayer.create(context, R.raw.music_result_before); 
			musicResult		  = MediaPlayer.create(context, R.raw.music_result); 
			
			//楽曲のループ設定
			musicHome.setLooping(true);
			musicPlay.setLooping(true);
			musicResult.setLooping(true);

			//複数の画面で使う画像の読み込み
			banner 			  = BitmapFactory.decodeResource(res, R.drawable.banner);
			checkYes[0]		  = BitmapFactory.decodeResource(res, R.drawable.check_yes1);
			checkYes[1]		  = BitmapFactory.decodeResource(res, R.drawable.check_yes2);
			checkNo[0]		  = BitmapFactory.decodeResource(res, R.drawable.check_no1);
			checkNo[1]		  = BitmapFactory.decodeResource(res, R.drawable.check_no2);
			checkWindow		  = BitmapFactory.decodeResource(res, R.drawable.check_checkwindow);
			endWindow 		  = BitmapFactory.decodeResource(res, R.drawable.check_endwindow);
			skip 			  = BitmapFactory.decodeResource(res, R.drawable.skip);
		}
		
		public void onDraw(Canvas canvas){
		
			
			//解像度調整
			displayX = canvas.getWidth() ;	//解像度:ナビゲーションバー込みのX座標を取得
			displayY = canvas.getHeight() ; //解像度:Y座標を取得
			//冒険での動く洞窟の背景用に予め、解像度に合わせた画像のX座標を取得しておく
			if(loadDisplay == false){
				groundX[0] = 0;
				groundX[1] = displayX;
				groundX[2] = displayX;
				backgroundX[0] = 0;
				backgroundX[1] = displayX;
				backgroundX[2] = displayX;
				loadDisplay = true;
			}
			//座標の位置調整用変数、tuneの
			//解像度hpdiの機種用処理
			if( hdpiNaviX <= displayX && displayX < xhdpiNaviX && displayCheck == false){
				backTune = backTune * 3 / 4;
				tune = tune * 3 / 4;
				displayCheck = true;
				
			//解像度xxhpdiの機種用処理
			}else if ( xxhdpiNaviX <= displayX  && displayCheck == false ){
				backTune = backTune * 3 / 2;
				tune = tune * 3 / 2;
				stage.cave = 2;
				displayCheck = true;
			}
			//楽曲を鳴らすか鳴らさないかの管理
			if( bgmState == BGM_ON ){
				controlMusic();
			} else if( bgmState == BGM_OFF ){
				if (musicHome.isPlaying()) {
				    //一時停止
				    musicHome.pause();
				}
			}
		
			//ゲームの状態の設定と描写
			//各画面に合わせて必要な画像の読み込みと解放も兼ねている
			switch(gameState){
				case GAME_TITLE:
					if( titleBackground == null){
						titleLoad();
					} else if( titleBackground != null){
						titleScene(canvas);
					}
					break;
				case GAME_LOAD:
					loadBlack( canvas);
					if( loading[3] == null){
						loadStateLoad();
						
					} else if( loading[3] != null){
						loadScene(canvas);
					}
					break;
				case GAME_MENU:
					loadBlack( canvas);
					if( playBackground != null){
						playLoadClear();
						tutorialLoadClear();
					} else if( resultBackGround[0] != null){
						resultLoadClear();
					} else if(titleBackground != null){
						titleClear();
					}
					if( menuBackground[0] == null){
						menuLoad();
					} else if( menuBackground[0] != null){
						menuScene(canvas);
					}
					break;
				case GAME_PLAY:
					loadBlack( canvas);
					if( menuBackground[0] != null){
						menuLoadClear();
					}
					if( playBackground == null){
						playLoad();
					} else if( playBackground != null){
						playScene(canvas);
					}
					break;
				case GAME_TUTORIAL:
					loadBlack( canvas);
					if( menuBackground[0] != null){
						menuLoadClear();
					}
					if( playBackground == null){
						playLoad();
						tutorialLoad();
					} else if( playBackground != null){
						tutorialScene(canvas);
					}
					break;	
				case GAME_RESULT:
					loadBlack( canvas);
					if( playBackground != null){
						playLoadClear();
					}
					if( resultBackGround[0] == null){
						resultLoad();
					} else if( resultBackGround[0] != null){
						resultScene(canvas);
					}
					break;
			}
			//ナビゲーションバーのない機種用、バナー表示
			if(displayX == hdpiX){
				canvas.drawBitmap(banner ,  hdpiNaviX ,  0, null);
			} else if(displayX == xhdpiX){
				canvas.drawBitmap(banner ,  xhdpiNaviX ,  0, null);
			} else if(displayX == xxhdpiX){
				canvas.drawBitmap(banner ,  xxhdpiNaviX ,  0, null);
			}
		}
	}
	
	//タッチイベント■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	public boolean onTouchEvent(MotionEvent event){
		//画面を押した（タッチした）時の処理
		
		/*
		 * ボタンが動く仕組みは
		 * タッチしたらtouchStateに定数を格納し
		 * それに応じる形でボタンが凹んだような画像に切り替わる
		 * 押したボタンを基準にするため、ACTION_UPに関しては
		 * 座標を取らずに、touchStateの定数に対応する
		 * 押した場所のX座標とY座標を条件にし、画像と同じ位置内にあるなら
		 * それに対応した処理を行う。
		 */
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			TouchX = event.getX(); //タッチした場所のX座標取得
			TouchY = event.getY(); //タッチした場所のY座標取得
			
			/*
			 * タイトル画面での処理、画面をタッチしたらメニュー画面に移る
			 * 効果音"seYes"が鳴り、二度押し防止の"fadeCheck"がtrueになり
			 * 画面がフェードアウトする
			 */
			if( gameState == GAME_TITLE && fadeCheck == false){
				se.play(seYes, 1.0F, 1.0F, 0, 0, 1.0F);
				fadeCheck = true ;
				fadeTimer = 5;
				return true;
			}
			//メニュー画面での処理
			if( gameState == GAME_MENU  ){
				//冒険のアイコンの説明、二個目の説明文が出てタッチすると、モード選択になる(seOpenBoxが鳴る)
				if(wordState == WORD_ADVENTURE1){
					wordState = WORD_ADVENTURE2;
					return true;
				}else if ( wordState == WORD_ADVENTURE2){
					se.play(seOpenBox, 1.0F, 1.0F, 0, 0, 1.0F); 
					menuState = MENU_MODE;
					wordState = 0;
					return true;
				//スコアのアイコンの説明、二個目の説明文が出てタッチすると、スコア画面が出る(seOpenBoxが鳴る)
				}else if ( wordState == WORD_SCORE1){
					wordState = WORD_SCORE2;
					return true;
				}else if ( wordState == WORD_SCORE2){
					se.play(seOpenBox, 1.0F, 1.0F, 0, 0, 1.0F); 
					menuState = MENU_SCORE;
					wordState = 0;
					return true;
				//訓練のアイコンの説明、説明文が出てタッチすると、訓練確認画面になる(seOpenBoxが鳴る)
				}else if ( wordState == WORD_TUTORIAL){
					se.play(seOpenBox, 1.0F, 1.0F, 0, 0, 1.0F); 
					menuState = MENU_TUTORIAL;
					wordState = 0;
					return true;
				}
				//メニューの状態が初期状態、またはスコア画面が開いてる時にタッチすると出来る内容
				if(menuState == MENU_NORMAL || menuState == MENU_SCORE){
					/*
					 * 冒険アイコンクリック時の動作、初回限定で説明文が出て
					 * voiceCheck[0]がtrueになり、以降ゲームを終了するか
					 * タスクを切るまで、説明文は出ないようになる
					 */
					if(  (65 * tune <= TouchX && TouchX  <= 65 * tune + 310 * tune ) &&
						(75 * tune <= TouchY && TouchY  <= 75 * tune + 150 * tune )	){
						if(voiceCheck[0] == false){
							menuState = MENU_NORMAL;
							wordState = WORD_ADVENTURE1;
							voiceCheck[0] = true;
						}else if( voiceCheck[0] == true){
							se.play(seOpenBox, 1.0F, 1.0F, 0, 0, 1.0F); 
							menuState = MENU_MODE;
						}
						return true;
						//スコアアイコンクリック時の動作、voiceCheckは[1]担当。初期状態ならスコア画面を開く
					}else if(  (65 * tune <= TouchX && TouchX  <= 65 * tune + 310 * tune ) &&
						(285 * tune <= TouchY && TouchY  <= 285 * tune + 150 * tune )  &&
						menuState == MENU_NORMAL){
						if(voiceCheck[1] == false){
							wordState = WORD_SCORE1;
							voiceCheck[1] = true;
						}else if( voiceCheck[1] == true){
							se.play(seOpenBox, 1.0F, 1.0F, 0, 0, 1.0F); 
							menuState = MENU_SCORE;
						}
						return true;
						//スコア画面の状態なら、初期状態に戻す
					}else if(  (65 * tune <= TouchX && TouchX  <= 65 * tune + 310 * tune ) &&
							(285 * tune <= TouchY && TouchY  <= 285 * tune + 150 * tune ) &&
							menuState == MENU_SCORE){
						menuState = MENU_NORMAL;
						return true;
							//訓練アイコンクリック時の動作、voiceCheckは[2]担当
					}else if(  (65 * tune <= TouchX && TouchX  <= 65 * tune + 310 * tune ) &&
							(495 * tune <= TouchY && TouchY  <= 495 * tune + 150 * tune )	){
						if( voiceCheck[2] == false){
							menuState = MENU_NORMAL;
							wordState = WORD_TUTORIAL;
							voiceCheck[2] = true;
						}else if( voiceCheck[2] == true){
							se.play(seOpenBox, 1.0F, 1.0F, 0, 0, 1.0F); 
							menuState = MENU_TUTORIAL;
						}
						return true;
						//設定ボタンクリック時の処理
					}else if(  (865 * tune <= TouchX && TouchX  <= 865 * tune + 150 * tune ) &&
							(590 * tune <= TouchY && TouchY  <= 590 * tune + 100 * tune )){
						touchState = TOUCH_OPTION;
						return true;
						//終了ボタンクリック時の処理
					}else if(  (1030 * tune <= TouchX && TouchX  <= 1030 * tune + 150 * tune ) &&
							(590 * tune <= TouchY && TouchY  <= 590 * tune + 100 * tune )){
						touchState = TOUCH_END;
						return true;
					}		
				}
				//ランキング画面での処理、切り替えボタンを押すと別モードのランキングに切り替わる
				//長押しするとランキング消去確認画面に移る
				if( menuState == MENU_SCORE){
					if( (1060 * tune <= TouchX && TouchX  <= 1060 * tune + 80 * tune ) &&
							(200 * tune <= TouchY && TouchY  <= 200 * tune + 80 * tune )){
						scoreClearTimer++;
						touchState = TOUCH_CHANGE;
					}
				}
				//設定画面での処理、閉じるボタン 見るボタン 
				//BGMのON OFFボタンがある BGMに関しては次回起動時も状態が反映される
				if( menuState == MENU_OPTION){
					if( (370 * tune - 20 * tune <= TouchX && TouchX  <= 370 * tune + 145 * tune ) &&
						(475 * tune - 20 * tune <= TouchY && TouchY  <= 475 * tune + 80 * tune )){
						touchState = TOUCH_CLOSE;
						return true;
					}else if( (650 * tune <= TouchX && TouchX  <= 650 * tune + 145 * tune ) &&
						(250 * tune <= TouchY && TouchY  <= 250 * tune + 80 * tune )){
						touchState = TOUCH_VIEW;
						return true;
					}
					if( bgmState == BGM_OFF && (585 * tune  <= TouchX && TouchX  <= 585 * tune + 120 * tune ) &&
						(365 * tune  <= TouchY && TouchY  <= 365 * tune + 65 * tune )){
						bgmState = BGM_ON;
						saveBGM();
					}else if( bgmState == BGM_ON && (735 * tune  <= TouchX && TouchX  <= 735 * tune + 120 * tune ) &&
						(365 * tune  <= TouchY && TouchY  <= 365 * tune + 65 * tune )){
						bgmState = BGM_OFF;
						saveBGM();
					}
				}
				//クレジット画面の処理、閉じるボタンで同時にクレジット関連の初期化もする
				if( menuState == MENU_CREDIT){
					if( (895 * tune <= TouchX && TouchX  <= 895 * tune + 80 * tune ) &&
							(80 * tune <= TouchY && TouchY  <= 80 * tune + 80 * tune )){
						touchState = TOUCH_CLOSE;
						creditState = 0;
						creditStartTimer = 0;
						creditTimer = 255;
						return true;
					}
				}
				//ゲーム終了確認画面での処理
				if(menuState == MENU_END){
					if( (450 * tune <= TouchX && TouchX <= 450 * tune + 180 * tune) &&
						(310 * tune <= TouchY && TouchY <= 310 * tune + 100 * tune && fadeCheck == false) ){	
						touchState = TOUCH_YES;
						return true;
					}else if( (670 * tune <= TouchX && TouchX <= 670 * tune + 180 * tune) &&
					(310 * tune <= TouchY && TouchY <= 310 * tune + 100 * tune) && fadeCheck == false){
						touchState = TOUCH_NO;
						return true;
					}
				}
				//モード確認画面での処理
				if( menuState == MENU_MODE){
					if( (450 * tune <= TouchX && TouchX <= 450 * tune + 180 * tune) &&
					(290 * tune <= TouchY && TouchY <= 290 * tune + 165 * tune) && fadeCheck == false ){	
						touchState = TOUCH_YES;
						return true;
					}else if( (670 * tune <= TouchX && TouchX <= 670 * tune + 180 * tune) &&
					(290 * tune <= TouchY && TouchY <= 290 * tune + 165 * tune) && fadeCheck == false ){
						touchState = TOUCH_NO;
						return true;
					}
				}
				//冒険確認画面での処理
				if(menuState == MENU_ADVENTURE){
					if( (450 * tune <= TouchX && TouchX <= 450 * tune + 180 * tune) &&
					(310 * tune <= TouchY && TouchY <= 310 * tune + 100 * tune) && fadeCheck == false ){	
						touchState = TOUCH_YES;
						return true;
					}else if( (670 * tune <= TouchX && TouchX <= 670 * tune + 180 * tune) &&
					(310 * tune <= TouchY && TouchY <= 310 * tune + 100 * tune) && fadeCheck == false ){
						touchState = TOUCH_NO;
						return true;
					}
				}
				//スコア消去確認画面での処理
				if(menuState == MENU_DELETE){
					if( (450 * tune <= TouchX && TouchX <= 450 * tune + 180 * tune) &&
					(310 * tune <= TouchY && TouchY <= 310 * tune + 100 * tune) && fadeCheck == false ){	
						touchState = TOUCH_YES;
						return true;
					}else if( (670 * tune <= TouchX && TouchX <= 670 * tune + 180 * tune) &&
					(310 * tune <= TouchY && TouchY <= 310 * tune + 100 * tune) && fadeCheck == false ){
						touchState = TOUCH_NO;
						return true;
					}
				}
				//訓練確認
				if(menuState == MENU_TUTORIAL){
					if( (450 * tune <= TouchX && TouchX <= 450 * tune + 180 * tune) &&
					(310 * tune <= TouchY && TouchY <= 310 * tune + 100 * tune && fadeCheck == false) ){	
						touchState = TOUCH_YES;
						return true;
					}else if( (670 * tune <= TouchX && TouchX <= 670 * tune + 180 * tune) &&
					(310 * tune <= TouchY && TouchY <= 310 * tune + 100 * tune) && fadeCheck == false){
						touchState = TOUCH_NO;
						return true;
					}
				}
			}
			//冒険画面での処理
			if(gameState == GAME_PLAY){
				//進行状態での処理
				if(playState == PLAY_NORMAL){
					//キラストーン使用時は、其々に対応した音とアニメーションに
					//色に応じたトラップに対しての、特殊判定を得られる
					//キラストーン使用 左のストックのを押した場合は、右にあるのが左にくる
					if( (stone.leftStock != 0 ) &&
					  ( 500 * tune < TouchX && TouchX < 620 * tune ) &&
					  ( 0 <= TouchY && TouchY <= 80 * tune ) ){
						switch(stone.leftStock){
							//左にストックされている石が赤なら 
							case Stone.STOCK_RED:
								effectTimer[0] = 0;
								effectTimer[1] = 0;
								se.play(seRed, 1.0F, 1.0F, 0, 0, 1.0F);
								monyako.breaker = Monyako.BREAK_RED;
								monyako.effect = Monyako.EFFECT_RED;
								break;
							//左にストックされている石が緑なら 
							case Stone.STOCK_GREEN:
								effectTimer[0] = 0;
								effectTimer[1] = 0;
								se.play(seGreen, 1.0F, 1.0F, 0, 0, 1.0F);
								monyako.breaker = Monyako.BREAK_GREEN;
								monyako.effect = Monyako.EFFECT_GREEN;
								break;
							//左にストックされている石が青なら 	
							case Stone.STOCK_BLUE:
								effectTimer[0] = 0;
								effectTimer[1] = 0;
								se.play(seBlue, 1.0F, 1.0F, 0, 0, 1.0F);
								monyako.breaker = Monyako.BREAK_BLUE;
								monyako.effect = Monyako.EFFECT_BLUE;
								break;
						}
						//ストックの所持数が減り、右にある状態を左に移し、右を空にする。
						stone.countStock--;
						stone.leftStock = stone.rightStock;
						stone.rightStock = 0;
						return true;
					//右のストックを押した時の処理、基本的には左と同じ	
					}else if( stone.rightStock != 0  &&
						    ( 650 * tune < TouchX && TouchX < 770 * tune ) &&
						    ( 0 <= TouchY && TouchY <= 80 * tune )){
						
						switch(stone.rightStock){
							case Stone.STOCK_RED:
								se.play(seRed, 1.0F, 1.0F, 0, 0, 1.0F);
								effectTimer[0] = 0;
								effectTimer[1] = 0;
								monyako.breaker = Monyako.BREAK_RED;
								monyako.effect = Monyako.EFFECT_RED;
								break;			
							case Stone.STOCK_GREEN:
								se.play(seGreen, 1.0F, 1.0F, 0, 0, 1.0F);
								effectTimer[0] = 0;
								effectTimer[1] = 0;
								monyako.breaker = Monyako.BREAK_GREEN;
								monyako.effect = Monyako.EFFECT_GREEN;
								break;
							case Stone.STOCK_BLUE:
								se.play(seBlue, 1.0F, 1.0F, 0, 0, 1.0F);
								effectTimer[0] = 0;
								effectTimer[1] = 0;
								monyako.breaker = Monyako.BREAK_BLUE;
								monyako.effect = Monyako.EFFECT_BLUE;
								break;
						}
						stone.countStock--;
						stone.rightStock = 0;
						return true;
					}
					//ポーズボタンを押した時の処理
					if( (1080 * tune <= TouchX && TouchX <= 1080 * tune + 80 * tune ) &&
						(10 * tune <= TouchY && TouchY <= 10 * tune + 80 * tune )	){
						touchState = TOUCH_PAUSE;
						return true;
					}
					
					//はしご設置処理 上段から中段の間なら、中段にはしごを設置 中段から下段の間なら下段に設置
					//countがmax未満なら設置可能で、設置する毎にcountが増える
					if(ladder.count < ladder.max && ( monyako.x * 4 / 3 < TouchX &&
					( 260 * tune <= TouchY && TouchY <= 490 * tune ) )){
						ladder.count++;
						setMiddleLadder();
						se.play(seSetLadder, 1.0F, 1.0F, 0, 0, 1.0F); 
						return true;
					}else if(ladder.count < ladder.max && monyako.x * 4 / 3 < TouchX && 
							( 490 * tune <= TouchY && TouchY <= 720 * tune) ){
						ladder.count++;
						setLowLadder();
						se.play(seSetLadder, 1.0F, 1.0F, 0, 0, 1.0F); 
						return true;
					}
					//デバック用　リザルト遷移〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■
					/*
					if( (0 <= TouchX && TouchX <= 0 + 80 * tune ) &&
							(0 <= TouchY && TouchY <= 0 + 80 * tune )	){
						
							
							gameState = GAME_RESULT;
						
							
							return true;
					}
					//クリア画面確認＆周回数上昇用
					if( (150 * tune <= TouchX && TouchX <= 230 * tune ) &&
							(0 <= TouchY && TouchY <= 0 + 80 * tune )	){
						stage.startPoint = 33000 * stage.cave ;
							return true;
					}
					*/
					//〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■〇■
				}
				//ポーズ画面での処理
				if(playState == PLAY_PAUSE){
					//続けるボタンの処理
					if( (450 * tune <= TouchX && TouchX <= 450 * tune + 180 * tune ) &&
					(310 * tune <= TouchY && TouchY <= 310 * tune + 100 * tune ) && fadeCheck == false	){
						touchState = TOUCH_CONTINUE;
						return true;
					} else if( (670 * tune <= TouchX && TouchX <= 670 * tune + 180 * tune ) &&
					(310 * tune <= TouchY && TouchY <= 310 * tune + 100 * tune ) && fadeCheck == false	){
						touchState = TOUCH_QUITE;
						return true;
					}
				}
				//ゲームオーバー画面での処理 「タッチしてホームに戻る」の文字が出て、しばらくしてからタッチ可能 フェードアウトする
				if(playState == PLAY_OVER && lightWait >= 130 && fadeCheck == false){
					fadeCheck = true;
					fadeTimer = 201;
					return true;
				}
			}//冒険画面での処理群終了
			//訓練画面での処理
			if( gameState == GAME_TUTORIAL){
				//一時停止状態出ない時の処理(faseState 100は一時停止)
				if( faseState != 100){
					//キラ関係の説明以外の時、スキップを押すとスキップ確認画面に移行出来る
					//tutorialSkipに一時的にfaseStateを保存する
					if( faseState != 101 && tutorialState != FASE_KIRA){
						if( ( 0 <= TouchX && TouchX <= 0 + 160 * tune ) &&
							(0 <= TouchY && TouchY <= 0 + 100 * tune )	){
							tutorialSkip = faseState ;
							faseState = 101;
						}
					}
				 //スキップ確認画面での処理	
				 if( faseState == 101){
						if( (450 * tune <= TouchX && TouchX <= 450 * tune + 180 * tune ) &&
							(310 * tune <= TouchY && TouchY <= 310 * tune + 100 * tune ) && fadeCheck == false	){
								touchState = TOUCH_SKIP_YES;
								return true;
							} else if( (670 * tune <= TouchX && TouchX <= 670 * tune + 180 * tune ) &&
							(310 * tune <= TouchY && TouchY <= 310 * tune + 100 * tune ) && fadeCheck == false	){
								touchState = TOUCH_SKIP_NO;
								return true;
						}
							
				}
				//スキップ確認画面でない時、一時停止画面に移行する
				if( faseState != 101){
						if( (1080 * tune <= TouchX && TouchX <= 1080 * tune + 80 * tune ) &&
						(10 * tune <= TouchY && TouchY <= 10 * tune + 80 * tune )	){
							touchState = TOUCH_PAUSE;
							return true;
						}
				}
				//はしご説明段階の時、はしごを設置する処理
				if( tutorialState == FASE_LADDER && faseState == 7){
					if(ladder.count < ladder.max && monyako.x * 2 < TouchX && ( 490 * tune <= TouchY && TouchY <= 720 * tune)
					&&	( 560 * tune <= TouchX && TouchX <= 760 * tune)
					){
						ladder.count++;
						setLowLadder();
						ladder.x[0] = 640 * tune;
						return true;
					}	
				}
				//罠説明段階の時、はしごを設置する処理	
				if( tutorialState == FASE_TRAP && faseState == 12){
					if(ladder.count < ladder.max && monyako.x * 2 < TouchX && ( 260 * tune <= TouchY && TouchY <= 490 * tune)
					&&	( 560 * tune <= TouchX && TouchX <= 760 * tune)){
						ladder.count++;
						setMiddleLadder();
						ladder.x[0] = 640 * tune;
						return true;
					}	
				}
				//罠の回避練習段階の時、はしごを設置する処理	
				if( tutorialState == FASE_FREE  && faseState == 17 || faseState == 18 ){
					if(ladder.count < ladder.max && ( monyako.x * 2 < TouchX && ( 260 * tune <= TouchY && TouchY <= 490 * tune ) )){
						ladder.count++;
						setMiddleLadder();
						se.play(seSetLadder, 1.0F, 1.0F, 0, 0, 1.0F); 
						return true;
					}else if(ladder.count < ladder.max && monyako.x * 2 < TouchX && ( 490 * tune <= TouchY && TouchY <= 720 * tune) ){
						ladder.count++;
						setLowLadder();
						se.play(seSetLadder, 1.0F, 1.0F, 0, 0, 1.0F); 
						return true;
					}
				}
				//キラについての説明段階の時、はしごを設置する処理
				//上段にいる時は、中段に置く処理。下段にいる時は、下段に置く処理。
				if( tutorialState == FASE_KIRA && faseState == 24){
					if(ladder.count < ladder.max && monyako.x * 2 < TouchX && ( 260 * tune <= TouchY && TouchY <= 490 * tune)
					&&	( 560 * tune <= TouchX && TouchX <= 760 * tune)	){
						ladder.count++;
						setMiddleLadder();
						ladder.x[0] = 640 * tune;
						return true;
					}
				}
				if( tutorialState == FASE_KIRA && faseState == 25){
					if(ladder.count < ladder.max && monyako.x * 2 < TouchX && ( 490 * tune <= TouchY && TouchY <= 720 * tune)
					&&	( 560 * tune <= TouchX && TouchX <= 760 * tune)	){
							ladder.count++;
							setLowLadder();
							ladder.x[0] = 640 * tune;
							return true;
					}	
				}
				//キラについての説明段階の時、キラストーンを使用する処理	
				if( tutorialState == FASE_KIRA && faseState == 30 && textTimer >= 400){
					if( (stone.leftStock != NOT ) &&
					   ( 500 * tune <= TouchX && TouchX < 620 * tune ) &&
					   ( 0 <= TouchY && TouchY <= 120 * tune ) ){
						switch(stone.leftStock){
							case Stone.STOCK_RED:
								se.play(seRed, 1.0F, 1.0F, 0, 0, 1.0F);
								monyako.breaker = Monyako.BREAK_RED;
								monyako.effect = Monyako.EFFECT_RED;
								break;
						}
						stone.countStock--;
						stone.leftStock = NOT;
						return true;
					}								
				}
			//一時停止中に訓練を続けるかやめるかの処理
			} else if( faseState == 100){
				if( (450 * tune <= TouchX && TouchX <= 450 * tune + 180 * tune ) &&
				(310 * tune <= TouchY && TouchY <= 310 * tune + 100 * tune ) && fadeCheck == false	){
					touchState = TOUCH_CONTINUE;
					return true;
				} else if( (670 * tune <= TouchX && TouchX <= 670 * tune + 180 * tune ) &&
				(310 * tune <= TouchY && TouchY <= 310 * tune + 100 * tune ) && fadeCheck == false	){
					touchState = TOUCH_QUITE;
					return true;
				}					
			}  
		}
		//リザルト画面での処理
		if(gameState == GAME_RESULT){
			//結果発表の各段階をスキップする処理
				if( resultState == RESULT_BEFORE){
					if( (0 <= TouchX && TouchX <= 200 * tune  ) &&
						(630 * tune <= TouchY && TouchY <= 720 * tune  ) ){
						//鉱石発表時はボーナスに飛ぶ
						if(resultCheck == STONE){
							resultCheck = BONUS;
							resultTimer = 0;
							loadCount = 0;
							return true;
						//ボーナス発表時は寸止め発表まで飛ぶ	
						} else if(resultCheck == BONUS){
							resultCheck = KIRA;
							resultTimer = 0;
							return true;
						//寸止め発表時は、最終結果まで飛ぶ
						} else if(resultCheck == KIRA){
							resultState = RESULT_AFTER;
							resultTimer = 0;
							return true;
						}
					}
				}
				//結果発表後の画面の処理、メニュー画面に飛ぶかゲーム終了するかの操作
				if(resultState == RESULT_AFTER){
					if( (860 * tune <= TouchX && TouchX <= 860 * tune + 100 * tune ) &&
						(590 * tune <= TouchY && TouchY <= 590 * tune + 100 * tune ) && 
						fadeCheck == false && endCheck == 0	){
						touchState = TOUCH_MENU;
						return true;
					}
					if( (1030 * tune <= TouchX && TouchX <= 1030 * tune + 150 * tune ) &&
						(590 * tune <= TouchY && TouchY <= 590 * tune + 100 * tune ) && endCheck == 0){
						touchState = TOUCH_END;
						return true;
					}
					//ゲーム終了確認画面の処理
					if(endCheck == RESULT_END){
						if( (450 * tune <= TouchX && TouchX <= 450 * tune + 180 * tune ) &&
						(310 * tune <= TouchY && TouchY <= 310 * tune + 100 * tune ) ) {
							touchState = TOUCH_YES;
							return true;
						}else if ( (670 * tune <= TouchX && TouchX <= 670 * tune + 180 * tune ) &&
						(310 * tune <= TouchY && TouchY <= 310 * tune + 100 * tune )){
							touchState = TOUCH_NO;
							return true;
						}
					}
				}
			}
		}//ACTION_DOWNの処理群はここまで
		//ACTION_UP 指を離した時の処理、ボタンやアイコンを押した時の画像変化と処理を行っている
		if(event.getAction() == MotionEvent.ACTION_UP){
			
			//スコア画面で切り替えボタンの押した時間が一定未満の時
			//其々のモードのランキングに切り替え、タイマーを初期化する
			if( menuState == MENU_SCORE && scoreClearTimer < 100){
				if( (1060 * tune <= TouchX && TouchX  <= 1060 * tune + 80 * tune ) &&
				(200 * tune <= TouchY && TouchY  <= 200 * tune + 80 * tune )){
					se.play(seClose, 1.0F, 1.0F, 0, 0, 1.0F);
					//ノーマル時ならエンドレスに切り替える
					if(scoreState == SCORE_NORMAL){
						rankingTimer = 0;
						scoreState = SCORE_ENDLESS;
					//エンドレス時ならノーマルに切り替える
					} else if(scoreState == SCORE_ENDLESS){
						rankingTimer = 0;
						scoreState = SCORE_NORMAL;
					}
					touchState = 0;
					scoreClearTimer = 0;
				}
			}
			 //冒険モードで一時停止画面に移行する処理
			if( gameState == GAME_PLAY && touchState == TOUCH_PAUSE){
				se.play(sePause, 1.0F, 1.0F, 0, 0, 1.0F);
				playState = PLAY_PAUSE;
				touchState = 0;
				return true;
			}
			//訓練モードで一時停止画面に移行する処理
			if( gameState == GAME_TUTORIAL && touchState == TOUCH_PAUSE){
				se.play(sePause, 1.0F, 1.0F, 0, 0, 1.0F);
				tutorialPause = faseState;
				faseState = 100;
				touchState = 0;
				return true;
			}
			//冒険モードで一時停止中に、続けるかやめるかの処理
			if( gameState == GAME_PLAY && touchState == TOUCH_CONTINUE){
				se.play(seYes, 1.0F, 1.0F, 0, 0, 1.0F);
				playState = PLAY_NORMAL;
				moveSpeed = speedDifficulty;
				touchState = 0;
				return true;
			} else if( gameState == GAME_PLAY && touchState == TOUCH_QUITE){
				se.play(seYes, 1.0F, 1.0F, 0, 0, 1.0F);
				fadeTimer = 5;
				fadeCheck = true;
				touchState = 0;
				return true;
			}
			//訓練モードスキップ画面ではいを押した時の処理
			 if( gameState == GAME_TUTORIAL && touchState == TOUCH_SKIP_YES){
				se.play(seYes, 1.0F, 1.0F, 0, 0, 1.0F);
				monyako.state = Monyako.MONYAKO_WALK;
				faseState = 102;
				touchState = 0;
				fadeTimer = 5;
				return true;
			//いいえを押した時の処理、tutorialPauseに一時的に保存してた値をfaseStateに反映させる
			}else if( gameState == GAME_TUTORIAL && touchState == TOUCH_SKIP_NO){
				se.play(seNo, 1.0F, 1.0F, 0, 0, 1.0F);
				faseState = tutorialSkip;
				touchState = 0;
				return true;
			}
			//訓練モード、一時停止画面で"続ける"か"やめる"かを押した時の処理
			if( gameState == GAME_TUTORIAL && touchState == TOUCH_CONTINUE){
				se.play(seYes, 1.0F, 1.0F, 0, 0, 1.0F);
				faseState = tutorialPause;
				touchState = 0;
				return true;
			}else if( gameState == GAME_TUTORIAL && touchState == TOUCH_QUITE){
				se.play(seYes, 1.0F, 1.0F, 0, 0, 1.0F);
				fadeTimer = 5;
				fadeCheck = true;
				touchState = 0;
				return true;
			}
			//メニュー画面で設定ボタンを押した時の処理。設定画面を開く
			if( gameState == GAME_MENU && touchState == TOUCH_OPTION){
				se.play(seYes, 1.0F, 1.0F, 0, 0, 1.0F);
				menuState = MENU_OPTION;
				touchState = 0;
				return true;
			//メニュー画面で終了ボタンを押した時の処理。終了確認画面を開く
			}else if( gameState == GAME_MENU && touchState == TOUCH_END){
				se.play(seYes, 1.0F, 1.0F, 0, 0, 1.0F);
				menuState = MENU_END;
				touchState = 0;
				return true;
			}
			//結果発表画面で、メニューボタンを押した時の処理。メニュー画面に遷移する
			if( gameState == GAME_RESULT &&  touchState == TOUCH_MENU){
				se.play(seYes, 1.0F, 1.0F, 0, 0, 1.0F);
				gameState = GAME_MENU;
				loadCount = 0;
				touchState = 0;
				return true;
			//結果発表画面で、終了ボタンを押した時の処理。終了確認画面を開く
			}else if( gameState == GAME_RESULT && touchState == TOUCH_END){
				se.play(seYes, 1.0F, 1.0F, 0, 0, 1.0F);
				endCheck = RESULT_END;
				touchState = 0;
				return true;
			}
			//見るボタンを押した時の処理。クレジット画面を開く
			if( touchState == TOUCH_VIEW){
				se.play(sePause, 1.0F, 1.0F, 0, 0, 1.0F);
				menuState = MENU_CREDIT;
				touchState = 0;
				return true;
			}
			//閉じるボタンを押した時の処理。設定画面を閉じる
			if( menuState == MENU_OPTION){
				if( touchState == TOUCH_CLOSE){
					se.play(seClose, 1.0F, 1.0F, 0, 0, 1.0F);
					noTimer++;
					touchState = 0 ;
					return true;
				}
			}
			 //クレジット画面を閉じる
			if( menuState == MENU_CREDIT){
				if( touchState == TOUCH_CLOSE){
					se.play(seClose, 1.0F, 1.0F, 0, 0, 1.0F);
					noTimer++;
					touchState = 0 ;
					return true;
				}
			}
			//"はい"ボタン."いいえ"ボタン関連の処理群
			//結果発表後画面でのゲーム終了確認画面
			if(endCheck == RESULT_END){
				//フェードアウトする、gameStateの変更はresultSceneの中に
				if( touchState == TOUCH_YES){
					fadeCheck = true;
					fadeTimer = 5;
					touchState = 0 ;
					return true;
				}else if( touchState == TOUCH_NO){
					noTimer++;
					endCheck = 0;
					touchState = 0 ;
					return true;
				}
			}
			//冒険のモード確認画面
			if( menuState == MENU_MODE){
				if( touchState == TOUCH_YES){
					se.play(seYes, 1.0F, 1.0F, 0, 0, 1.0F);
					menuState = MENU_ADVENTURE;
					score.endlessMode = false;
					touchState = 0 ;
					return true;
				}else if( touchState == TOUCH_NO){
					se.play(seYes, 1.0F, 1.0F, 0, 0, 1.0F);
					menuState = MENU_ADVENTURE;
					score.endlessMode = true;
						touchState = 0 ;
					return true;
				}
			}
			//冒険確認
			if( menuState == MENU_ADVENTURE){
				if( touchState == TOUCH_YES){
					se.play(seYes, 1.0F, 1.0F, 0, 0, 1.0F);
					fadeCheck = true;
					fadeTimer = 5;
					touchState = 0 ;
					return true;
				}else if( touchState == TOUCH_NO){
					se.play(seNo, 1.0F, 1.0F, 0, 0, 1.0F);
					noTimer++;
					touchState = 0 ;
					return true;
				}
			}
			
			//スコア消去
			if( menuState == MENU_DELETE){
				if( touchState == TOUCH_YES){
					se.play(seYes, 1.0F, 1.0F, 0, 0, 1.0F);
					if( scoreState == SCORE_NORMAL ){
						score.normalRankingPoint[0] = 0;
						score.normalRankingPoint[1] = 0;
						score.normalRankingPoint[2] = 0;
						firstSave( );
						secondSave( );
						thirdSave( );
					} else if( scoreState == SCORE_ENDLESS ){
						score.endelessRankingPoint[0] = 0;
						score.endelessRankingPoint[1] = 0;
						score.endelessRankingPoint[2] = 0;
						endlessFirstSave( );
						endlessSecondSave( );
						endlessThirdSave( );
					}
					scoreClearTimer = 0;
					noTimer++;
					touchState = 0 ;
					
					return true;
				}else if( touchState == TOUCH_NO){
					se.play(seNo, 1.0F, 1.0F, 0, 0, 1.0F);
					noTimer++;
					
						touchState = 0 ;
					return true;
				}
				
			}
			//メニューのゲーム終了確認画面
			if( menuState == MENU_END){
				if( touchState == TOUCH_YES){
					se.play(seYes, 1.0F, 1.0F, 0, 0, 1.0F);
					fadeCheck = true;
					fadeTimer = 5;
					touchState = 0 ;
					return true;
				}else if( touchState == TOUCH_NO){
					se.play(seYes, 1.0F, 1.0F, 0, 0, 1.0F);
					noTimer++;
					touchState = 0 ;
					return true;
				}
			}
			//メニューの訓練確認画面での処理
			if( menuState == MENU_TUTORIAL){
				if( touchState == TOUCH_YES){
					se.play(seYes, 1.0F, 1.0F, 0, 0, 1.0F);
					fadeCheck = true;
					fadeTimer = 5;
					touchState = 0 ;
					return true;
				}else if( touchState == TOUCH_NO){
					se.play(seNo, 1.0F, 1.0F, 0, 0, 1.0F);
					noTimer++;
					touchState = 0 ;
					return true;
				}
			}
		}
		return false;
	}//ACTION_DOWN処理群終了
	
	
	//各ゲーム画面の描画詳細■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	//タイトル画面の描画内容
	public void titleScene(Canvas canvas){
		/*
		 * ■概要■
		 * タイトル画面背景の上に、ロゴとスタートの文字が配置される
		 * 画面をタッチするとフェードアウトして、ロード画面へ遷移する
		 */
		
		canvas.drawBitmap(titleBackground , 0, 0, null);
		canvas.drawBitmap(titleLogo , 30  * tune, 30 * tune, null);
		//画面をタッチしてフェードアウトするまでは、スタートの文字が明滅している
		if(fadeTimer == 0 ){
			if( 0 <= lightWait && lightWait < 20 ){
				lightWait++;
				canvas.drawBitmap(titleStart[0] , 160  * tune , 560  * tune, null);
			}else if( 20 <= lightWait && lightWait < 40 ){
				lightWait++;
				canvas.drawBitmap(titleStart[1] , 160  * tune, 560  * tune, null);	
			}else if( 40 <= lightWait){
				lightWait++;
				canvas.drawBitmap(titleStart[1] , 160 * tune,  560 * tune, null);
				lightWait = 0;
			}
		}
		//画面をタッチしてフェードアウト開始する
		if( 5 <= fadeTimer && fadeTimer < 255){
			canvas.drawBitmap(titleStart[0] , 160 * tune, 560 * tune, null);
			fadeTimer = fadeTimer + 10;
			Paint black = new Paint();
			black.setColor(Color.argb(fadeTimer, 0, 0, 0));
			Rect rect = new Rect(0, 0, displayX, displayY);
		    canvas.drawRect(rect, black);
		//真っ暗になったらロード画面へ遷移する、その際ロード画面の文章用乱数を取得
		}else if(fadeTimer == 255){
			Paint black = new Paint();
			black.setColor(Color.argb(fadeTimer, 0, 0, 0));
			Rect rect = new Rect(0, 0, displayX, displayY);
		    canvas.drawRect(rect, black);
		    stone.rndY[0] = stone.rnd.nextInt(6);
		    tips =   tipsRnd.nextInt(5);
			gameState = GAME_LOAD;
			fadeCheck = false;
			fadeTimer = 0;
			loadTimer = 0;
		}
	}
	
	//ロード画面■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	public void loadScene(Canvas canvas){
		Paint backBlack = new Paint();
		backBlack.setColor(Color.argb(255, 0, 0, 0));
		Rect backRect = new Rect(0, 0, displayX, displayY);
	    canvas.drawRect(backRect, backBlack);
	    
	    switch(tips){
	    
	    case 0 :
	    	if( 0 <= reviMove && reviMove <= 10){
				reviMove++;
				canvas.drawBitmap(revi[0] , 555 * tune, 520 * tune, null);
			} else if( 10 <= reviMove && reviMove <= 20){
				reviMove++;
				canvas.drawBitmap(revi[1] , 555 * tune, 520 * tune, null);
			} else if( 20 <= reviMove && reviMove <= 30){
				reviMove++;
				canvas.drawBitmap(revi[2] , 555 * tune, 520 * tune, null);
			} else if( 30 <= reviMove && reviMove < 40){
				reviMove++;
				canvas.drawBitmap(revi[3] , 555 * tune, 520 * tune, null);
			} else if( 40 == reviMove){
					canvas.drawBitmap(revi[0] , 555 * tune, 520 * tune, null);
				reviMove = 0;
			}
	    	canvas.drawBitmap(loadTips[0] , 60 * tune, 80 * tune, null);
			
	    	break;
	    case 1 :
	    	monyako.walkTimer++;
	    	monyakoAction();
	    	monyako.x = 600 * tune;
			monyako.y = 560 * tune;
			getMonyako(canvas);
	    	canvas.drawBitmap(loadTips[1] , 60 * tune, 80 * tune, null);
			
	    	break;
	    case 2 :
	    	if( 0 <= reviMove && reviMove <= 10){
				reviMove++;
				canvas.drawBitmap(revi[0] , 555 * tune, 520 * tune, null);
			} else if( 10 <= reviMove && reviMove <= 20){
				reviMove++;
				canvas.drawBitmap(revi[1] , 555 * tune, 520 * tune, null);
			} else if( 20 <= reviMove && reviMove <= 30){
				reviMove++;
				canvas.drawBitmap(revi[2] ,555 * tune, 520 * tune, null);
			} else if( 30 <= reviMove && reviMove < 40){
				reviMove++;
				canvas.drawBitmap(revi[3] , 555 * tune, 520 * tune, null);
			} else if( 40 == reviMove){
					canvas.drawBitmap(revi[0] , 555 * tune, 520 * tune, null);
				reviMove = 0;
			}
	    	canvas.drawBitmap(loadTips[2] , 60 * tune, 80 * tune, null);
			
	    	break;
	    case 3 :
	    	if( 0 <= stone.timer[3][0] && stone.timer[3][0] <= 15){
				stone.timer[3][0]++;
				canvas.drawBitmap( imgStone[3][0] , 600 * tune, 560 * tune, null);
			} else if( 15 <= stone.timer[3][0] && stone.timer[3][0] <= 30){
				stone.timer[3][0]++;
				canvas.drawBitmap( imgStone[3][1] , 600 * tune, 560 * tune, null);
			} else if( 30 <= stone.timer[3][0] && stone.timer[3][0] <= 45){
				stone.timer[3][0]++;
				canvas.drawBitmap( imgStone[3][2] , 600 * tune, 560 * tune, null);
			} else if( 45 <= stone.timer[3][0] && stone.timer[3][0] <= 60){
				stone.timer[3][0]++;
				canvas.drawBitmap( imgStone[3][3] , 600 * tune, 560 * tune, null);
			}else if( 60 <= stone.timer[3][0] && stone.timer[3][0] <= 75){
				stone.timer[3][0]++;
				canvas.drawBitmap( imgStone[3][4] , 600 * tune, 560 * tune, null);
			} else if( 75 <= stone.timer[3][0] && stone.timer[3][0] < 90){
				stone.timer[3][0]++;
				canvas.drawBitmap( imgStone[3][3] , 600 * tune, 560 * tune, null);
			}else if( 90 <= stone.timer[3][0] && stone.timer[3][0] < 105){
				stone.timer[3][0]++;
				canvas.drawBitmap( imgStone[3][2] , 600 * tune, 560 * tune, null);
			}else if( 105 <= stone.timer[3][0] && stone.timer[3][0] < 120){
				stone.timer[3][0]++;
				canvas.drawBitmap( imgStone[3][1] , 600 * tune, 560 * tune, null);
			}else if( stone.timer[3][0] >= 120){
				stone.timer[3][0] = 0;
				canvas.drawBitmap( imgStone[3][0] , 600 * tune, 560 * tune, null);
			}
	    	canvas.drawBitmap(loadTips[3] , 60 * tune, 80 * tune, null);
			
	    	break;
	    case 4 :
	    	if( 0 <= stone.timer[0][0] && stone.timer[0][0] <= 15){
	    		stone.timer[0][0]++;
				canvas.drawBitmap( imgStone[0][0] , 600 * tune, 560 * tune, null);
			} else if( 15 <= stone.timer[0][0] && stone.timer[0][0] <= 30){
				stone.timer[0][0]++;
				canvas.drawBitmap( imgStone[0][1] , 600 * tune, 560 * tune, null);
			} else if( 30 <= stone.timer[0][0] && stone.timer[0][0] <= 45){
				stone.timer[0][0]++;
				canvas.drawBitmap( imgStone[0][2] , 600 * tune, 560 * tune, null);
			} else if( 45 <= stone.timer[0][0] && stone.timer[0][0] <= 60){
				stone.timer[0][0]++;
				canvas.drawBitmap( imgStone[0][3] , 600 * tune, 560 * tune, null);
			}else if( 60 <= stone.timer[0][0] && stone.timer[0][0] <= 75){
				stone.timer[0][0]++;
				canvas.drawBitmap( imgStone[0][4] , 600 * tune, 560 * tune, null);
			} else if( 75 <= stone.timer[0][0] && stone.timer[0][0] < 90){
				stone.timer[0][0]++;
				canvas.drawBitmap( imgStone[0][3] , 600 * tune, 560 * tune, null);
			}else if( 90 <= stone.timer[0][0] && stone.timer[0][0] < 105){
				stone.timer[0][0]++;
				canvas.drawBitmap( imgStone[0][2] , 600 * tune, 560 * tune, null);
			}else if( 105 <= stone.timer[0][0] && stone.timer[0][0] < 120){
				stone.timer[0][0]++;
				canvas.drawBitmap( imgStone[0][1] , 600 * tune, 560 * tune, null);
			}else if( stone.timer[0][0] >= 120){
				stone.timer[0][0] = 0;
				canvas.drawBitmap( imgStone[0][0] , 600 * tune, 560 * tune, null);
			}
	    	canvas.drawBitmap(loadTips[4] , 60 * tune, 80 * tune, null);
			
	    	break;
	    	
	    
	    }
	    loadTimer++;
		
		if( 0 <= loadTimer && loadTimer < 50 ){
			canvas.drawBitmap(loading[0] , 770 * tune, 600 * tune, null);
		} else if( 50 <= loadTimer && loadTimer < 100 ){
			canvas.drawBitmap(loading[1] , 770 * tune, 600 * tune, null);
		} else if( 100 <= loadTimer && loadTimer < 150 ){
			canvas.drawBitmap(loading[2] , 770 * tune, 600 * tune, null);
		} else if( 150 <= loadTimer && loadTimer < 200 ){
			canvas.drawBitmap(loading[3] , 770 * tune, 600 * tune, null);
		} else if( 200 <= loadTimer && loadTimer < 250 ){
			canvas.drawBitmap(loading[0] , 770 * tune, 600 * tune, null);
		} else if( 250 <= loadTimer && loadTimer < 300 ){
			canvas.drawBitmap(loading[1] , 770 * tune, 600 * tune, null);
		} else if( 300 <= loadTimer && loadTimer < 350 ){
			canvas.drawBitmap(loading[2] , 770 * tune, 600 * tune, null);
		} else if( 350 <= loadTimer && loadTimer < 400 ){
			canvas.drawBitmap(loading[3] , 770 * tune, 600 * tune, null);
		} else if( loadTimer == 400){
			canvas.drawBitmap(loading[0] , 770 * tune, 600 * tune, null);
			fadeTimer = 5;
		}
		if( 5 <= fadeTimer && fadeTimer < 255){
			canvas.drawBitmap(loading[0] , 770 * tune, 600 * tune, null);
			fadeTimer = fadeTimer + 10;
			Paint black = new Paint();
			black.setColor(Color.argb(fadeTimer, 0, 0, 0));
			Rect rect = new Rect(0, 0, displayX, displayY);
		    canvas.drawRect(rect, black);
		   
		}else if(fadeTimer == 255){
			canvas.drawBitmap(loading[0] , 770 * tune, 600 * tune, null);
			
			Paint black = new Paint();
			black.setColor(Color.argb(fadeTimer, 0, 0, 0));
			Rect rect = new Rect(0, 0, displayX, displayY);
		    canvas.drawRect(rect, black);
		    gameState = GAME_MENU;
			fadeCheck = false;
			fadeTimer = 0;
			loadTimer = 0;
		}
		
	}
	
	//メニュー画面■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	public void menuScene(Canvas canvas){
		canvas.drawBitmap(menuBackground[1] , 0, 0, null);//メニュー画面背景
		canvas.drawBitmap(menuBar, 440 * tune, 0, null);//メニューバー
		
		//起動から初回限定で各アイコンの説明噴出しが出る設定
		//wordStateの状態に応じて、対応した台詞が出る
		if(wordState > 0){
			canvas.drawBitmap(voice, 355 * tune, 285 * tune, null);//噴出し
			switch(wordState){
			
			case WORD_ADVENTURE1:
				canvas.drawBitmap(word[0], 355 * tune, 285 * tune, null);//冒険モードの説明１

			break;
			
			case WORD_ADVENTURE2:
				canvas.drawBitmap(word[1], 355 * tune, 285 * tune, null);//冒険モードの説明２
			break;
			
			case WORD_SCORE1:
				canvas.drawBitmap(word[2], 355 * tune, 285 * tune, null);//スコアの説明１
			break;
			
			case WORD_SCORE2:
				canvas.drawBitmap(word[3], 355 * tune, 285 * tune, null);//スコアの説明２

			break;
			
			case WORD_TUTORIAL:
				canvas.drawBitmap(word[4], 355 * tune, 285 * tune, null);//訓練モードの説明
			break;
			}
			
		}
		
		//touchStateの状態に応じてアイコンが変わる
		//touchStateと＝が成り立つならアイコンを押してる状態であるようにアイコンが変化
		//設定アイコン
		if( touchState != TOUCH_OPTION){
			canvas.drawBitmap(menuOption[0], 865 * tune, 590 * tune, null);
		} else if( touchState == TOUCH_OPTION){
			canvas.drawBitmap(menuOption[1], 880 * tune, 600 * tune, null);
		}
		//終了アイコン
		if( touchState != TOUCH_END){
			canvas.drawBitmap(menuEnd[0], 1030 * tune, 590 * tune, null);
		}else if( touchState == TOUCH_END){
			canvas.drawBitmap(menuEnd[1], 1045  * tune, 600 * tune, null);
		}
		
		//冒険アイコン
		if( menuState < MENU_MODE) {
		canvas.drawBitmap(menuAdventure[0] , 65 * tune, 75 * tune, null);
		}else if( menuState >= MENU_MODE ){
			canvas.drawBitmap(menuAdventure[1] , 65 * tune, 75 * tune, null);	
		}
		
		//スコアアイコン
		if( menuState != MENU_SCORE){
			canvas.drawBitmap(menuScore[0] , 65 * tune, 285 * tune, null);
			
			rankingTimer = 0;
			loadCount = 0;
			
		}else if( menuState == MENU_SCORE){
			
			canvas.drawBitmap(menuScore[1] , 65 * tune, 285 * tune, null);	
		}
		
		//訓練アイコン
		if( menuState != MENU_TUTORIAL){
		canvas.drawBitmap(menuTutorial[0] , 65 * tune, 495 * tune, null);
		
		} else if( menuState == MENU_TUTORIAL){
			canvas.drawBitmap(menuTutorial[1] , 65 * tune, 495 * tune, null);
		}
		
		
		//オプション
		if( menuState == MENU_OPTION){
			loadBGM();
			canvas.drawBitmap(optionWindow , 350 * tune, 150 * tune, null);
			if( touchState != TOUCH_VIEW){
				canvas.drawBitmap(creditView[0] , 650 * tune, 250 * tune, null);
			} else if( touchState == TOUCH_VIEW){
				canvas.drawBitmap(creditView[1] , 660 * tune, 255 * tune, null);
			}
			
			switch(bgmState){
			
			case 0:
				canvas.drawBitmap(optionOn[0] , 570 * tune, 360 * tune, null);
				canvas.drawBitmap(optionOff[1] , 735 * tune, 365 * tune, null);
				break;
			
			case 1:
				canvas.drawBitmap(optionOn[1] , 585 * tune, 365 * tune, null);
				canvas.drawBitmap(optionOff[0] , 720 * tune, 360 * tune, null);
				
			break;
				
			}
			
					
			 if( touchState != TOUCH_CLOSE){
				 canvas.drawBitmap(closeIcon[0] , 370 * tune, 475 * tune, null);
					 if( 5 > noTimer && noTimer >= 1){
				    	noTimer++;
						
					 }else if(noTimer ==5){
						menuState = MENU_NORMAL;
						noTimer = 0;
					 }
				 } else if( touchState == TOUCH_CLOSE){
					 canvas.drawBitmap(closeIcon[1] , 370 * tune, 475 * tune, null);
						    
			}
		}
		
		//クレジット関係
		if(menuState == MENU_CREDIT){
			
			Paint back = new Paint();
			back.setColor(Color.argb(255, 0, 0, 0));
		    canvas.drawRect(  230 * tune, 80 * tune, 970 * tune,  640 * tune, back);
		    canvas.drawBitmap(creditWindow ,175 * tune, 20 * tune, null);
		    creditStartTimer++;
		    if( creditStartTimer == 35){
		    	creditState = 1;
		    	
		    }
			switch(creditState){
			
			case 1:
				canvas.drawBitmap(credit[0] , 290 * tune, -30 * tune, null);
				creditTimer -= 2;
				Paint black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
			    canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer <= 1){
			    	
			    	creditState = 2;
			    }
			break;
			
			case 2:
				canvas.drawBitmap(credit[0] , 290 * tune, -30 * tune, null);
				creditTimer += 2;
				black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
				canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer >= 254){
			    	
			    	creditState = 3;
			    }
			break;
			
			case 3:
				canvas.drawBitmap(credit[1] , 290 * tune, -30 * tune, null);
				creditTimer -= 2;
				black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
				canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer <= 1){
			    	
			    	creditState = 4;
			    }
			break;
			
			case 4:
				canvas.drawBitmap(credit[1] , 290 * tune, -30 * tune, null);
				creditTimer += 2;
				black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
				canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer >= 254){
			    	
			    	creditState = 5;
			    }	
			break;
			
			case 5:
				canvas.drawBitmap(credit[2] , 290 * tune, -30 * tune, null);
				creditTimer -= 2;
				black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
				canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer <= 1){
			    	
			    	creditState = 6;
			    }
			break;
				
			case 6:
				canvas.drawBitmap(credit[2] , 290 * tune, -30 * tune, null);
				creditTimer += 2;
				black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
				canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer >= 254){
			    	
			    	creditState = 7;
			    }		
			break;
			
			case 7:
				canvas.drawBitmap(credit[3] , 290 * tune, -30 * tune, null);
				creditTimer -= 2;
				black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
				canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer <= 1){
			    	
			    	creditState = 8;
			    }
			break;
				
			case 8:
				canvas.drawBitmap(credit[3] , 290 * tune, -30 * tune, null);
				creditTimer += 2;
				black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
				canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer >= 254){
			    	
			    	creditState = 9;
			    }		
			break;
			
			case 9:
				canvas.drawBitmap(credit[4] , 290 * tune, -30 * tune, null);
				creditTimer -= 2;
				black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
				canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer <= 1){
			    	
			    	creditState = 10;
			    }
			break;
				
			case 10:
				canvas.drawBitmap(credit[4] , 290 * tune, -30 * tune, null);
				creditTimer += 2;
				black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
				canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer >= 254){
			    	
			    	creditState = 11;
			    }		
			break;
			
			case 11:
				canvas.drawBitmap(credit[5] , 290 * tune, -30 * tune, null);
				creditTimer -= 2;
				black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
				canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer <= 1){
			    	
			    	creditState = 12;
			    }
			break;
				
			case 12:
				canvas.drawBitmap(credit[5] , 290 * tune, -30 * tune, null);
				creditTimer += 2;
				black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
				canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer >= 254){
			    	
			    	creditState = 13;
			    }		
			break;
			
			case 13:
				canvas.drawBitmap(credit[6] , 290 * tune, -30 * tune, null);
				creditTimer -= 2;
				black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
				canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer <= 1){
			    	
			    	creditState = 14;
			    }
			break;
				
			case 14:
				canvas.drawBitmap(credit[6] , 290 * tune, -30 * tune, null);
				creditTimer += 2;
				black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
				canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer >= 254){
			    	
			    	creditState = 15;
			    }		
			break;
			
			case 15:
				canvas.drawBitmap(credit[7] , 290 * tune, -30 * tune, null);
				creditTimer -= 2;
				black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
				canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer <= 1){
			    	
			    	creditState = 16;
			    }
			break;
				
			case 16:
				canvas.drawBitmap(credit[7] , 290 * tune, -30 * tune, null);
				creditTimer += 2;
				black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
				canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer >= 254){
			    	
			    	creditState = 17;
			    }		
			break;
			
			case 17:
				canvas.drawBitmap(credit[8] , 290 * tune, -30 * tune, null);
				creditTimer -= 2;
				black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
				canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer <= 1){
			    	
			    	creditState = 18;
			    }
			break;
				
			case 18:
				canvas.drawBitmap(credit[8] , 290 * tune, -30 * tune, null);
				creditTimer += 2;
				black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
				canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer >= 254){
			    	
			    	creditState = 19;
			    }		
			break;
			
			case 19:
				canvas.drawBitmap(credit[9] , 290 * tune, -30 * tune, null);
				creditTimer -= 2;
				black = new Paint();
				black.setColor(Color.argb(creditTimer, 0, 0, 0));
				canvas.drawRect(250 * tune, 80 * tune,970 * tune, 640 * tune, black);
			    if(creditTimer <= 1){
			    	
			    	creditState = 20;
			    }		
			break;
			
			case 20:
				canvas.drawBitmap(credit[9] , 290 * tune, -30 * tune, null);
					
			break;
			}
			 if( touchState != TOUCH_CLOSE){
				 canvas.drawBitmap(closeIcon[0] , 895  * tune , 80  * tune , null);
					 if( 5 > noTimer && noTimer >= 1){
				    	noTimer++;
						
					 }else if(noTimer ==5){
						menuState = MENU_NORMAL;
						noTimer = 0;
					 }
				 } else if( touchState == TOUCH_CLOSE){
					 canvas.drawBitmap(closeIcon[1] , 895  * tune , 80  * tune , null);
						    
			}
			
		}
				
		//終了関係
		if( menuState == MENU_END){
			 canvas.drawBitmap(endWindow , 340  * tune , 150  * tune , null);
			 if( touchState != TOUCH_YES){
				 canvas.drawBitmap(checkYes[0] , 450  * tune , 310  * tune , null); 
			 } else if( touchState == TOUCH_YES){
				 canvas.drawBitmap(checkYes[1] , 470  * tune , 320  * tune , null);
			 }
			 if( touchState != TOUCH_NO){
			    canvas.drawBitmap(checkNo[0] , 670  * tune , 310  * tune , null);
			    if( 5 > noTimer && noTimer >= 1){
			    	noTimer++;
					
				}else if(noTimer ==5){
					menuState = MENU_NORMAL;
					noTimer = 0;
				}
			 } else if( touchState == TOUCH_NO){
				    canvas.drawBitmap(checkNo[1] , 690  * tune , 320  * tune , null);
				    
			 }
		}
		if( menuState == MENU_MODE ){
			 canvas.drawBitmap(modeWindow , 340 * tune , 150 * tune , null);
			  if( touchState != TOUCH_YES ){
				    canvas.drawBitmap(normalMode[0] , 450 * tune, 290 * tune, null); 
				   } else if( touchState == TOUCH_YES ){
					    canvas.drawBitmap(normalMode[1] , 460 * tune, 300 * tune, null); 
				   }
				   if( touchState != TOUCH_NO ){
					   if( 0 < stone.rndY[0] ){
						   canvas.drawBitmap(endlessIcon[0] , 670 * tune, 290 * tune, null);
					   }else if( 0 == stone.rndY[0]){
						   canvas.drawBitmap(endlessIcon[2] , 670 * tune, 290 * tune, null);
							     
						  }
				} else if( touchState == TOUCH_NO ){
					 if( 0 < stone.rndY[0] ){
						   canvas.drawBitmap(endlessIcon[1] , 680 * tune, 300 * tune, null);
					   }else if( 0 == stone.rndY[0]){
						   canvas.drawBitmap(endlessIcon[3] , 680 * tune, 300 * tune, null);
							     
					}
				}
			
		}
		if(menuState == MENU_ADVENTURE || menuState == MENU_TUTORIAL){
		
		    canvas.drawBitmap(checkWindow , 340 * tune, 150 * tune, null);
		   if( touchState != TOUCH_YES ){
		    canvas.drawBitmap(checkYes[0] , 450 * tune, 310 * tune, null); 
		   } else if( touchState == TOUCH_YES ){
			    canvas.drawBitmap(checkYes[1] , 460 * tune, 320 * tune, null); 
		   }
		   if( touchState != TOUCH_NO ){
			   if( 5 > noTimer && noTimer >= 1){
			    	noTimer++;
					
				}else if(noTimer ==5){
					menuState = MENU_NORMAL;
					noTimer = 0;
				}
			   canvas.drawBitmap(checkNo[0] , 670 * tune, 310 * tune, null);
		   } else if( touchState == TOUCH_NO ){
			   canvas.drawBitmap(checkNo[1] , 680 * tune, 320 * tune, null);
		   }
		    
		}else if(menuState == MENU_SCORE){
			if( 0 < scoreClearTimer && scoreClearTimer < 100 ){
				scoreClearTimer++;
				
				
			}else if( scoreClearTimer >= 100){
				se.play(sePause, 1.0F, 1.0F, 0, 0, 1.0F); 
				menuState = MENU_DELETE;
				
			}
			
			scoreLoad();
			if( scoreState == SCORE_NORMAL){
				canvas.drawBitmap(scoreBoard[0] , 370 * tune, 170 * tune, null);
				//ランキングデータ読み込み
				score.nomalRankingLoad();
			} else if(scoreState == SCORE_ENDLESS){
				canvas.drawBitmap(scoreBoard[1] , 370 * tune, 170 * tune, null);

				score.endlessRankingLoad();
				
			}
			if( touchState != TOUCH_CHANGE){
				canvas.drawBitmap(scoreChange[0] , 1060 * tune, 200 * tune, null); 
				} else if( touchState == TOUCH_CHANGE){
				canvas.drawBitmap(scoreChange[1] , 1065 * tune, 205 * tune, null);
			}
			
			
			
			if( 0 <= rankingTimer && rankingTimer <= 30){
				rankingTimer++;
			}else if( 30 <= rankingTimer && rankingTimer <= 60){
				rankingTimer++;
				loadCount = 1;
				drawRanking( canvas);
			} else if( 60 <= rankingTimer && rankingTimer <= 90){
				rankingTimer++;
				loadCount = 2;
				drawRanking( canvas);
			} else if( 90 <= rankingTimer && rankingTimer <= 120){
				rankingTimer++;
				loadCount = 3;
				drawRanking( canvas);
			} else
				drawRanking( canvas);
		}  
		    
		if( 5 <= fadeTimer && fadeTimer < 255){
			if(menuState == MENU_ADVENTURE ||
					menuState == MENU_TUTORIAL	){
			fadeTimer = fadeTimer + 10;
			}else if(menuState == MENU_END){
				fadeTimer = fadeTimer + 25;
			}
			Paint black = new Paint();
			black.setColor(Color.argb(fadeTimer, 0, 0, 0));
			Rect rect = new Rect(0, 0, displayX, displayY);
		    canvas.drawRect(rect, black);
		    playState =PLAY_READY;
		}else if(fadeTimer == 255){
			Paint black = new Paint();
			black.setColor(Color.argb(fadeTimer, 0, 0, 0));
			Rect rect = new Rect(0, 0, displayX, displayY);
		    canvas.drawRect(rect, black);
		    if( menuState == MENU_ADVENTURE){
		    monyako.y = 325 * tune;
		   
		    	gameState = GAME_PLAY;
		
			
			setPlayDefault();
			fadeCheck = false;
			fadeTimer = 0;
		    } else if( menuState == MENU_TUTORIAL){
		    
		    	gameState = GAME_TUTORIAL;
		    
		    
			tutorialState = FASE_STONE;
			faseState = 0;
			setPlayDefault();
			monyako.x = 140 * tune;
			monyako.y = 555 * tune;
			fadeCheck = false;
			fadeTimer = 0;
		    } else if( menuState == MENU_END){
		    	this.finish();
		    

			}
		    
		}
		
		if(menuState == MENU_DELETE){
			
			 canvas.drawBitmap(scoreDelete , 340 * tune, 150 * tune, null);
			   if( touchState != TOUCH_YES ){
			    canvas.drawBitmap(checkYes[0] , 450 * tune, 310 * tune, null); 
			   } else if( touchState == TOUCH_YES ){
				    canvas.drawBitmap(checkYes[1] , 460 * tune, 320 * tune, null); 
			   }
			   if( touchState != TOUCH_NO ){
				   if( 5 > noTimer && noTimer >= 1){
				    	noTimer++;
						
					}else if(noTimer ==5){
						menuState = MENU_NORMAL;
						scoreClearTimer = 0;
						noTimer = 0;
					}
				   canvas.drawBitmap(checkNo[0] , 670 * tune, 310 * tune, null);
			   } else if( touchState == TOUCH_NO ){
				   canvas.drawBitmap(checkNo[1] , 680 * tune, 320 * tune, null);
			   }
		}
	}
	//プレイ画面■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	public void playScene(Canvas canvas){
		switch(playState){
		case PLAY_READY:
			
			//キラトラップの
			trap.rndX[2] = trap.rnd.nextInt(3);
			//ステージの石配置乱数
			stage.setStone = stage.setRnd.nextInt(4);
			//stage.setStone = 3;	配置テスト用
			//ステージのトラップ配置乱数
			stage.setTrap = stage.setRnd.nextInt(3);
			//stage.setTrap = 1;	配置テスト用
			
			canvas.drawBitmap(playBackground , 0, 0, null);
			canvas.drawBitmap(ground , 0, 0, null);
			if( monyako.x < 140 * tune){
				monyako.x = monyako.x + 2;
				
			}else if( monyako.x == 140 * tune){
				playState = PLAY_NORMAL;	
				
				
			}
			
			monyako.walkTimer++;
			getMonyako(canvas);
			
						
			
		break;
		
		case PLAY_NORMAL:
			
			
			if( 0 <= stage.startPoint && stage.startPoint < 6000 * stage.cave ){
				createStone1();
				createTrap1();
			} else if( 6000 * stage.cave <= stage.startPoint && stage.startPoint < 18000 * stage.cave ){
				createStone2();
				createTrap2();
			} else if( 18000 * stage.cave <= stage.startPoint && stage.startPoint < stage.goalPoint){
				if( score.endlessMode == false && stage.setTrap == 1){
					if( 18000 * stage.cave <= stage.startPoint && stage.startPoint < 21000 * stage.cave){
						createEndlessTrap1();
						randamTrapY1();
						tuneTrap();
					} else if(24000 * stage.cave <= stage.startPoint && stage.startPoint < 27000 * stage.cave ){
						createEndlessTrap1();
						randamTrapY1();
						tuneTrap();
					}
				}
				createStone3();
				createTrap3();
				
			}
		//プレイモード ノーマル
			if( score.endlessMode == false){
				randomStoneY();

				trap.rndX[0] = trap.rnd.nextInt(3);
				trap.rndX[1] = trap.rnd.nextInt(3);
		} else if( score.endlessMode == true ){
		//エンドレス
			
			trap.rndY[0] = trap.rnd.nextInt(3);
			trap.rndY[1] = trap.rnd.nextInt(3);
			trap.rndY[2] = trap.rnd.nextInt(3);
			trap.rndY[3] = trap.rnd.nextInt(3);
			trap.rndY[4] = trap.rnd.nextInt(3);
			trap.rndY[5] = trap.rnd.nextInt(3);
			
			if(  stage.startPoint >= stage.goalPoint * stage.cave ){
				trap.rndX[2] = trap.rnd.nextInt(3);
				LAP += 1;
				stage.setTrap = stage.setRnd.nextInt(3);
				stage.setStone = stage.setRnd.nextInt(4);
				 speedCheck = 0;
				stage.startPoint = 0;
			}
			randomStoneY();
			//createEndlessStone();
			if(  31000 * stage.cave <= stage.startPoint && stage.startPoint <= 31100 * stage.cave){
				randamTrapX2();
				randamTrapY2();
				createEndlessTrap2();	
			}	
			
			if(trap.rndX[2] != 1 ){
				createEndlessTrap1();
				randamTrapY1();
				tuneTrap();
				
			} else if( trap.rndX[2] == 1){
				
				if(0 < stage.startPoint && stage.startPoint < 9500 * stage.cave){
					createEndlessTrap1();
					randamTrapY1();
					tuneTrap();
				}else if( 10500 * stage.cave < stage.startPoint && stage.startPoint < stage.goalPoint * stage.cave ){
					createEndlessTrap1();
					randamTrapY1();
					tuneTrap();
				}
			}
				
			
		
		}
		if(caveCheck == false){
		stage.goalPoint = stage.goalPoint * stage.cave;	
		moveSpeed = moveSpeed * stage.cave;
			caveCheck = true;
		}
		
		stage.startPoint = stage.startPoint + moveSpeed; 
		trap.startPoint = stage.startPoint ; 
		stone.startPoint = stage.startPoint ; 
		
		
		
		stone.setKiraRnd = stone.rnd.nextInt(3);
		stone.rndY[0] = stone.rnd.nextInt(3);
		stone.rndY[1] = stone.rnd.nextInt(3);
		stone.rndY[2] = stone.rnd.nextInt(3);
		stone.rndY[3] = stone.rnd.nextInt(3);
		stone.rndY[4] = stone.rnd.nextInt(3);
		stone.rndY[5] = stone.rnd.nextInt(3);
		
		//ストーンの被り防止用
		
			tuneLowStoneY();
			tuneMiddleStoneY();
			tuneHighStoneY();
			tuneRedStoneY();
			tuneGreenStoneY();
			tuneBlueStoneY();
	
			
		//速度難易度変化
		if( score.endlessMode == false){ 
			if(stage.startPoint <= 6000 * stage.cave && speedCheck == 0 && score.endlessMode == false){
				speedDifficulty =  SPEED_EASY  * stage.cave;
				moveSpeed = speedDifficulty;
				speedCheck = 1;
			} else if(6000 * stage.cave <= stage.startPoint && stage.startPoint <= 18000 * stage.cave
					&& speedCheck == 1  && score.endlessMode == false){
				speedDifficulty =  SPEED_NORMAL  * stage.cave;
				moveSpeed = speedDifficulty;
				speedCheck = 2;
			} else if( 18000* stage.cave  <= stage.startPoint && stage.startPoint <= 36000 * stage.cave
					&& speedCheck == 2  && score.endlessMode == false){
				speedDifficulty = SPEED_HARD  * stage.cave ;
				moveSpeed = speedDifficulty;
				speedCheck = 3;
			}
		} else if( score.endlessMode == true){ 
			if(stage.startPoint <= 6000 * stage.cave && speedCheck == 0 ){
				speedDifficulty = ( SPEED_ENDLESS_EASY   + LAP ) * stage.cave;
				moveSpeed = speedDifficulty;
				speedCheck = 1;
			} else if(6000 * stage.cave <= stage.startPoint && stage.startPoint <= 18000 * stage.cave
					&& speedCheck == 1  ){
				speedDifficulty = ( SPEED_ENDLESS_NORMAL + LAP ) * stage.cave;
				moveSpeed = speedDifficulty;
				speedCheck = 2;
			} else if( 18000* stage.cave  <= stage.startPoint && stage.startPoint <= 36000 * stage.cave
					&& speedCheck == 2 ){
				speedDifficulty = (SPEED_ENDLESS_HARD + LAP) * stage.cave ;
				moveSpeed = speedDifficulty;
				speedCheck = 3;
			}
		}
		
		//地面背景表示
		getBackGround(canvas);
		// ペイントオブジェクトを生成
			
		getTrap(canvas);
		//地面背景表示
		getGround(canvas);
		
		
		//はしご設置
		getLadder(canvas);
		
		//もにゃこ表示
		getMonyako(canvas);
		score.playComboCount();
		
		if( 0 < score.combo && 0 < score.comboTimer && score.comboTimer < 20){
			score.comboTimer++;
			getCombo(canvas);
		}else if( score.comboTimer == 20){
			score.comboTimer = 0;
		}
		
		//もにゃこの状態に応じて、付加される
		switch(monyako.state){
		
		case Monyako.MONYAKO_WALK:
			if( SPEED_EASY <= moveSpeed && moveSpeed < SPEED_NORMAL){	
					monyako.walkTimer++;
				} else if( SPEED_NORMAL <= moveSpeed && moveSpeed < SPEED_HARD){	
					monyako.walkTimer += 3;
				} else if( moveSpeed >= SPEED_HARD){	
					monyako.walkTimer+= 5;
				
			} 
			
			break;
			
		case Monyako.MONYAKO_UP:
			if(score.endlessMode == false){
				monyako.y = monyako.y - 5 * tune;
				monyako.ladderTimer++;
				ladder.speed++;
			} else if(  0 <= stage.startPoint && stage.startPoint < 18000 * stage.cave  && score.endlessMode == true){	
				monyako.y = monyako.y - 5 * tune;
				monyako.ladderTimer++;
				ladder.speed++;
			} else if(  18000 <= stage.startPoint && stage.startPoint <= stage.goalPoint * stage.cave  && score.endlessMode == true){	
				monyako.y = monyako.y - 10 * tune;
				monyako.ladderTimer+= 2;
				ladder.speed+= 2;
			} 
			
			break;
			
		case Monyako.MONYAKO_DOWN:
			if(score.endlessMode == false){
				monyako.y = monyako.y + 5 * tune;
				monyako.ladderTimer++;
				ladder.speed++;
			} else if(  0 <= stage.startPoint && stage.startPoint < 18000 * stage.cave && score.endlessMode == true){	
				monyako.y = monyako.y + 5 * tune;
				monyako.ladderTimer++;
				ladder.speed++;
			} else if(  18000 <= stage.startPoint && stage.startPoint <= stage.goalPoint * stage.cave && score.endlessMode == true){	
				monyako.y = monyako.y + 10 * tune;
				monyako.ladderTimer+= 2;
				ladder.speed+= 2;
			} 
			break;
			
		}
			
		//ゲット関係
		getGet(canvas);
		
		//キラ関連エフェクト
		getKiraEffect(canvas);
		
		//ストーン表示
		getStone(canvas);
	
		//UI■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
		if( 0 <= startTimer && startTimer < 100){
			startTimer++;
			canvas.drawBitmap(playStart , 390 * tune, 275 * tune, null);
			
		}
		
		//アイコン関係
		getUI(canvas);
		
		
		if( score.endlessMode == false){	
			if(stage.startPoint >= stage.goalPoint * stage.cave ){
			
			playState = PLAY_GOAL; 
			}
		}
		
			if( 5500 * stage.cave  < stage.startPoint && stage.startPoint < 6500 * stage.cave ){
				canvas.drawBitmap(speedUp[0] , 400 * tune, 200 * tune, null);
			}if( 17000 * stage.cave  < stage.startPoint && stage.startPoint < 18000* stage.cave ){
				canvas.drawBitmap(speedUp[0] , 400 * tune, 200 * tune, null);
			}
		
		break;
		
		//ポーズ画面時の処理〇〇〇〇〇〇〇〇〇〇〇〇〇〇〇〇〇〇〇〇〇〇〇〇〇〇〇〇〇
		case PLAY_PAUSE:
			
			moveSpeed = 0;
			getBackGround(canvas);
			getGround(canvas);
			//UI
			getUI(canvas);
				
			//はしご設置
			getLadder(canvas);
			
			//もにゃこ表示
			getMonyako(canvas);
			
			for( int i = 0; i < 4; i++){

				canvas.drawBitmap(imgStone[0][0] , stone.x[0][i], stone.y[0][i], null);
				canvas.drawBitmap(imgStone[1][0] , stone.x[1][i], stone.y[1][i], null);
				canvas.drawBitmap(imgStone[2][0] , stone.x[2][i], stone.y[2][i], null);
				canvas.drawBitmap(imgStone[3][0], stone.x[3][i], stone.y[3][i], null);
				canvas.drawBitmap(imgStone[4][0] , stone.x[4][i], stone.y[4][i], null);
				canvas.drawBitmap(imgStone[5][0] , stone.x[5][i], stone.y[5][i], null);
			}
			
			
			//ポーズアイコン
			
			gamePause(canvas);
			
		break;
		
		
		//ゲームオーバー演出
		case PLAY_OVER:
			moveSpeed = 0;
			getBackGround(canvas);
			getGround( canvas);
			if ( 0 <= monyako.deathTimer && monyako.deathTimer <= 10){
				monyako.deathTimer++;
				canvas.drawBitmap(monyakoDeath[0] , monyako.x, monyako.y, null);
			}else if ( 10 <= monyako.deathTimer && monyako.deathTimer <= 20){
				monyako.deathTimer++;
				canvas.drawBitmap(monyakoDeath[1] , monyako.x, monyako.y, null);
			}else if ( 20 < monyako.deathTimer && monyako.deathTimer <= 30){
				monyako.deathTimer++;
				canvas.drawBitmap(monyakoDeath[2] , monyako.x, monyako.y, null);
			}else if ( 30 < monyako.deathTimer && monyako.deathTimer <= 40){
				monyako.deathTimer++;
				canvas.drawBitmap(monyakoDeath[3] , monyako.x, monyako.y, null);
			}else if ( 40 < monyako.deathTimer && monyako.deathTimer <= 50){
				monyako.deathTimer++;
				canvas.drawBitmap(monyakoDeath[4] , monyako.x, monyako.y, null);
			}else if ( 50 < monyako.deathTimer && monyako.deathTimer <= 60){
				monyako.deathTimer++;
				canvas.drawBitmap(monyakoDeath[5] , monyako.x, monyako.y, null);
				fadeTimer = 5;
			}
			if( 5 <= fadeTimer && fadeTimer < 200){
				canvas.drawBitmap(monyakoDeath[5] , monyako.x, monyako.y, null);
				
				fadeTimer = fadeTimer + 5;
				Paint black = new Paint();
				black.setColor(Color.argb(fadeTimer, 0, 0, 0));
				Rect rect = new Rect(0, 0, displayX, displayY);
			    canvas.drawRect(rect, black);
				
			}else if(fadeTimer == 200){
				canvas.drawBitmap(monyakoDeath[5] , monyako.x, monyako.y, null);
				
				Paint black = new Paint();
				black.setColor(Color.argb(fadeTimer, 0, 0, 0));
				Rect rect = new Rect(0, 0, displayX, displayY);
			    canvas.drawRect(rect, black);
			    canvas.drawBitmap(gameOver , 240 * tune, 150 * tune, null);
			    lightWait++;
				
					if( 130 <= lightWait && lightWait <= 160 ){
						canvas.drawBitmap(touchHome[0] ,  370 * tune ,  465 * tune, null);
				
					}else if( 160 <= lightWait && lightWait <= 189 ){
						canvas.drawBitmap(touchHome[1] , 370 * tune ,  465 * tune , null);	
				
					}else if( lightWait >=190){
						canvas.drawBitmap(touchHome[1] , 370 * tune ,  465 * tune, null);
						lightWait = 130;
					
					}
			}else if(  201  <= fadeTimer &&  fadeTimer < 255){
					fadeTimer++;
					
					canvas.drawBitmap(monyakoDeath[5] , monyako.x, monyako.y, null);
					
				    canvas.drawBitmap(gameOver , 240 * tune, 150 * tune, null);
				    canvas.drawBitmap(touchHome[1] , 370 * tune ,  465 * tune, null);
				    Paint black = new Paint();
					black.setColor(Color.argb(fadeTimer, 0, 0, 0));
					Rect rect = new Rect(0, 0, displayX, displayY);
					canvas.drawRect(rect, black);
			}else if(fadeTimer == 255){
				Paint black = new Paint();
				black.setColor(Color.argb(fadeTimer, 0, 0, 0));
				Rect rect = new Rect(0, 0, displayX, displayY);
				canvas.drawRect(rect, black);
					
					menuState = MENU_NORMAL;
					gameState = GAME_MENU;
					fadeCheck = false;
					fadeTimer = 0;
					lightWait = 0;
					monyako.deathTimer = 0;
					
			}
				
			
		break;
		
		case PLAY_GOAL:
			getBackGround(canvas);
			getGround(canvas);
			
			switch(goalState){
			
				case GOAL_READY:
					moveSpeed = 2;
					monyako.walkTimer++;
					if( 0 <= monyako.walkTimer && monyako.walkTimer <= 15 ){
						canvas.drawBitmap(monyakoWalk[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
					} else if( 15 <= monyako.walkTimer && monyako.walkTimer <= 30){
						canvas.drawBitmap(monyakoWalk[1 + (monyako.color * 4)] , monyako.x, monyako.y, null);
				
					} else if( 30 <= monyako.walkTimer && monyako.walkTimer <= 45){
						canvas.drawBitmap(monyakoWalk[2 + (monyako.color * 4)] , monyako.x, monyako.y, null);
				
					} else if( 45 <= monyako.walkTimer && monyako.walkTimer <= 60){
						canvas.drawBitmap(monyakoWalk[3 + (monyako.color * 4)] , monyako.x, monyako.y, null);
				
					} else if(  monyako.walkTimer >= 61){
						canvas.drawBitmap(monyakoWalk[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						monyako.walkTimer = 0;
					}
					goalMove = goalMove + 2;
					
					if( 0 <= reviMove && reviMove <= 10){
						reviMove++;
						canvas.drawBitmap(gate[0] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[0] , displayX - goalMove, 265 * tune, null);
					} else if( 10 <= reviMove && reviMove <= 20){
						reviMove++;
						canvas.drawBitmap(gate[1] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[1] , displayX - goalMove, 265 * tune, null);
					} else if( 20 <= reviMove && reviMove <= 30){
						reviMove++;
						canvas.drawBitmap(gate[2] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[2] , displayX - goalMove, 265 * tune, null);
					} else if( 30 <= reviMove && reviMove < 40){
						reviMove++;
						canvas.drawBitmap(gate[3] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[3] , displayX - goalMove, 265 * tune, null);
					} else if( 40 <= reviMove){
						canvas.drawBitmap(gate[0] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[0] , displayX - goalMove, 265 * tune, null);
						reviMove = 0;
					}
					if(goalMove >= 350 * tune){	
						if ( 260 * tune < monyako.y && monyako.y < 490 * tune ){
							
							goalState = GOAL_MIDDLE;
						}	else {
							goalState = GOAL_GO;
						}
					}
					
				break;
				
				case GOAL_GO:
					moveSpeed = 0;
					
					if( 0 <= reviMove && reviMove <= 10){
						reviMove++;
						canvas.drawBitmap(gate[0] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[0] , displayX - goalMove, 265 * tune, null);
					} else if( 10 <= reviMove && reviMove <= 20){
						reviMove++;
						canvas.drawBitmap(gate[1] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[1] , displayX - goalMove, 265 * tune, null);
					} else if( 20 <= reviMove && reviMove <= 30){
						reviMove++;
						canvas.drawBitmap(gate[2] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[2] , displayX - goalMove, 265 * tune, null);
					} else if( 30 <= reviMove && reviMove < 40){
						reviMove++;
						canvas.drawBitmap(gate[3] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[3] , displayX - goalMove, 265 * tune, null);
					} else if( 40 <= reviMove){
						canvas.drawBitmap(gate[0] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[0] , displayX - goalMove, 265 * tune, null);
						reviMove = 0;
					}
					
					if(monyako.x < displayX / 2 - 1){
						monyako.x = monyako.x + 2;
						monyako.walkTimer++;
						if( 0 <= monyako.walkTimer && monyako.walkTimer <= 15 ){
							canvas.drawBitmap(monyakoWalk[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						} else if( 15 <= monyako.walkTimer && monyako.walkTimer <= 30){
							canvas.drawBitmap(monyakoWalk[1 + (monyako.color * 4)] , monyako.x, monyako.y, null);
					
						} else if( 30 <= monyako.walkTimer && monyako.walkTimer <= 45){
							canvas.drawBitmap(monyakoWalk[2 + (monyako.color * 4)] , monyako.x, monyako.y, null);
					
						} else if( 45 <= monyako.walkTimer && monyako.walkTimer <= 60){
							canvas.drawBitmap(monyakoWalk[3 + (monyako.color * 4)] , monyako.x, monyako.y, null);
					
						} else if(  monyako.walkTimer >= 61){
							canvas.drawBitmap(monyakoWalk[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
							monyako.walkTimer = 0;
						}
					}else if(monyako.x >= displayX / 2 - 1){
						canvas.drawBitmap(monyakoWalk[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						if( monyako.y < 260 * tune ){
							
							goalState = GOAL_UP;
						}else if ( 490 * tune < monyako.y && monyako.y < 720 * tune ){
							
							goalState = GOAL_DOWN;
						}
					}
					
					break;
				case GOAL_UP:
					moveSpeed = 0;
					if( 0 <= reviMove && reviMove <= 10){
						reviMove++;
						canvas.drawBitmap(gate[0] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[0] , displayX - goalMove, 265 * tune, null);
					} else if( 10 <= reviMove && reviMove <= 20){
						reviMove++;
						canvas.drawBitmap(gate[1] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[1] , displayX - goalMove, 265 * tune, null);
					} else if( 20 <= reviMove && reviMove <= 30){
						reviMove++;
						canvas.drawBitmap(gate[2] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[2] , displayX - goalMove, 265 * tune, null);
					} else if( 30 <= reviMove && reviMove < 40){
						reviMove++;
						canvas.drawBitmap(gate[3] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[3] , displayX - goalMove, 265 * tune, null);
					} else if( 40 <= reviMove){
						canvas.drawBitmap(gate[0] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[0] , displayX - goalMove, 265 * tune, null);
						reviMove = 0;
					}
					canvas.drawBitmap(imgLadder , monyako.x, 220 * tune, null);
					monyako.ladderTimer++;
					monyako.y = monyako.y + 5 * tune;
					ladder.speed++;
					if(230 * tune -5 * tune * ladder.speed >= 0){
						if( 0 <= monyako.ladderTimer && monyako.ladderTimer <= 11){	
							canvas.drawBitmap(monyakoLadder[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
							
						}else if( 11 <= monyako.ladderTimer && monyako.ladderTimer <= 23){
							canvas.drawBitmap(monyakoLadder[1 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						
						}else if( 23 <= monyako.ladderTimer && monyako.ladderTimer <= 35){
							canvas.drawBitmap(monyakoLadder[2 + (monyako.color * 4)] , monyako.x, monyako.y, null);
							
						}else if( 35 <= monyako.ladderTimer && monyako.ladderTimer <= 45){
							canvas.drawBitmap(monyakoLadder[3 + (monyako.color * 4)] , monyako.x, monyako.y, null);
							
						}else if(monyako.ladderTimer == 46){
							monyako.ladderTimer = 0;
							canvas.drawBitmap(monyakoLadder[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						
						}
					}else {
						
						ladder.speed = 0;
						monyako.walkTimer = 0;
						goalState = GOAL_MIDDLE;
					}
				break;
			
				case GOAL_MIDDLE:
					moveSpeed = 0;
					if( 0 <= reviMove && reviMove <= 10){
						reviMove++;
						canvas.drawBitmap(gate[0] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[0] , displayX - goalMove, 265 * tune, null);
						
					} else if( 10 <= reviMove && reviMove <= 20){
						reviMove++;
						canvas.drawBitmap(gate[1] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[1] , displayX - goalMove, 265 * tune, null);
					} else if( 20 <= reviMove && reviMove <= 30){
						reviMove++;
						canvas.drawBitmap(gate[2] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[2] , displayX - goalMove, 265 * tune, null);
					} else if( 30 <= reviMove && reviMove < 40){
						reviMove++;
						canvas.drawBitmap(gate[3] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[3] , displayX - goalMove, 265 * tune, null);
					} else if( 40 <= reviMove){
						
						canvas.drawBitmap(gate[0] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[0] , displayX - goalMove, 265 * tune, null);
						reviMove = 0;
					}
					
					if(monyako.x < displayX - goalMove - 50){
						monyako.x = monyako.x + 2;
						monyako.walkTimer++;
						if( 0 <= monyako.walkTimer && monyako.walkTimer <= 15 ){
							canvas.drawBitmap(monyakoWalk[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						} else if( 15 <= monyako.walkTimer && monyako.walkTimer <= 30){
							canvas.drawBitmap(monyakoWalk[1 + (monyako.color * 4)] , monyako.x, monyako.y, null);
					
						} else if( 30 <= monyako.walkTimer && monyako.walkTimer <= 45){
							canvas.drawBitmap(monyakoWalk[2 + (monyako.color * 4)] , monyako.x, monyako.y, null);
					
						} else if( 45 <= monyako.walkTimer && monyako.walkTimer <= 60){
							canvas.drawBitmap(monyakoWalk[3 + (monyako.color * 4)] , monyako.x, monyako.y, null);
					
						} else if(  monyako.walkTimer >= 61){
							canvas.drawBitmap(monyakoWalk[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
							monyako.walkTimer = 0;
						}
			}else {
				goalState = GOAL_END;
				
				
			}
			
			
					break;
		
				case GOAL_DOWN:
					moveSpeed = 0;
					if( 0 <= reviMove && reviMove <= 10){
						reviMove++;
						canvas.drawBitmap(gate[0] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[0] , displayX - goalMove, 265 * tune, null);
					} else if( 10 <= reviMove && reviMove <= 20){
						reviMove++;
						canvas.drawBitmap(gate[1] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[1] , displayX - goalMove, 265 * tune, null);
					} else if( 20 <= reviMove && reviMove <= 30){
						reviMove++;
						canvas.drawBitmap(gate[2] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[2] , displayX - goalMove, 265 * tune, null);
					} else if( 30 <= reviMove && reviMove < 40){
						reviMove++;
						canvas.drawBitmap(gate[3] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[3] , displayX - goalMove, 265 * tune, null);
					} else if( 40 <= reviMove){
						canvas.drawBitmap(gate[0] , displayX - goalMove + 100 * tune, 265 * tune, null);
						canvas.drawBitmap(revi[0] , displayX - goalMove, 265 * tune, null);
						reviMove = 0;
					}
					canvas.drawBitmap(imgLadder , monyako.x, 450 * tune, null);
					monyako.ladderTimer++;
					monyako.y = monyako.y - 5 * tune;
					ladder.speed++;
					if(230 * tune -5 * tune * ladder.speed >= 0){
						if( 0 <= monyako.ladderTimer && monyako.ladderTimer <= 11){	
							canvas.drawBitmap(monyakoLadder[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
							
						}else if( 11 <= monyako.ladderTimer && monyako.ladderTimer <= 23){
							canvas.drawBitmap(monyakoLadder[1 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						
						}else if( 23 <= monyako.ladderTimer && monyako.ladderTimer <= 35){
							canvas.drawBitmap(monyakoLadder[2 + (monyako.color * 4)] , monyako.x, monyako.y, null);
							
						}else if( 35 <= monyako.ladderTimer && monyako.ladderTimer <= 45){
							canvas.drawBitmap(monyakoLadder[3 + (monyako.color * 4)] , monyako.x, monyako.y, null);
							
						}else if(monyako.ladderTimer == 46){
							monyako.ladderTimer = 0;
							canvas.drawBitmap(monyakoLadder[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						
						}
					}else {
						
						ladder.speed = 0;
						monyako.walkTimer = 0;
						goalState = GOAL_MIDDLE;
					}
				break;
				
				
				case GOAL_END:
					
					canvas.drawBitmap(gate[0] , displayX - goalMove + 100 * tune, 265 * tune, null);
					canvas.drawBitmap(revi[0] , displayX - goalMove, 265 * tune, null);
					canvas.drawBitmap(monyakoWalk[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
					if( 0 <= clearTimer && clearTimer <= 20 ){
						canvas.drawBitmap(clear[0] , -110 * tune, -80 * tune, null);
					} else if( 20 <= clearTimer && clearTimer <= 40 ){
						canvas.drawBitmap(clear[1] , -110 * tune, -80 * tune, null);
					} else if( 40 <= clearTimer && clearTimer <= 60 ){
						canvas.drawBitmap(clear[2] , -110 * tune, -80 * tune, null);
					} else if( 60 <= clearTimer && clearTimer <= 80 ){
						canvas.drawBitmap(clear[1] , -110 * tune, -80 * tune, null);
					} else if( 80 <= clearTimer && clearTimer <= 100 ){
						canvas.drawBitmap(clear[0] , -110 * tune, -80 * tune, null);
					} else if( 100 <= clearTimer && clearTimer <= 120 ){
						canvas.drawBitmap(clear[1] , -110 * tune, -80 * tune, null);
					} else if( 120 <= clearTimer && clearTimer <= 140 ){
						canvas.drawBitmap(clear[2] , -110 * tune, -80 * tune, null);
					}else if( 140 <= clearTimer && clearTimer < 160 ){
						canvas.drawBitmap(clear[1] , -110 * tune, -80 * tune, null);
					} 
					clearTimer++;
					
					if(clearTimer >= 160){
						canvas.drawBitmap(clear[0] , -110 * tune, -80 * tune, null);
						if(  0  <= fadeTimer &&  fadeTimer < 255){
							fadeTimer = fadeTimer + 5;
							
						    
						    Paint black = new Paint();
							black.setColor(Color.argb(fadeTimer, 0, 0, 0));
							Rect rect = new Rect(0, 0, displayX, displayY);
							canvas.drawRect(rect, black);
					}else if(fadeTimer == 255){
						Paint black = new Paint();
						black.setColor(Color.argb(fadeTimer, 0, 0, 0));
						Rect rect = new Rect(0, 0, displayX, displayY);
						canvas.drawRect(rect, black);
							
							
							gameState = GAME_RESULT;
							fadeCheck = false;
							goalState = GOAL_READY;
							fadeTimer = 0;
							clearTimer = 0;
							
							
					}
						
					}
					break;
			}
			break;
			
		case PLAY_FINISH:
			canvas.drawBitmap(playBackground ,backgroundX[0], 0, null);
			canvas.drawBitmap(playBackground ,backgroundX[1], 0, null);
			canvas.drawBitmap(ground ,groundX[0], 0, null);
			canvas.drawBitmap(ground ,groundX[1], 0, null);
			
			canvas.drawBitmap(monyakoWalk[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
			if( 0 <= clearTimer && clearTimer <= 20 ){
				canvas.drawBitmap(finish[0] , -110 * tune, -80 * tune, null);
			} else if( 20 <= clearTimer && clearTimer <= 40 ){
				canvas.drawBitmap(finish[1] , -110 * tune, -80 * tune, null);
			} else if( 40 <= clearTimer && clearTimer <= 60 ){
				canvas.drawBitmap(finish[2] , -110 * tune, -80 * tune, null);
			} else if( 60 <= clearTimer && clearTimer <= 80 ){
				canvas.drawBitmap(finish[1] , -110 * tune, -80 * tune, null);
			} else if( 80 <= clearTimer && clearTimer <= 100 ){
				canvas.drawBitmap(finish[0] , -110 * tune, -80 * tune, null);
			} else if( 100 <= clearTimer && clearTimer <= 120 ){
				canvas.drawBitmap(finish[1] , -110 * tune, -80 * tune, null);
			} else if( 120 <= clearTimer && clearTimer <= 140 ){
				canvas.drawBitmap(finish[2] , -110 * tune, -80 * tune, null);
			}else if( 140 <= clearTimer && clearTimer < 160 ){
				canvas.drawBitmap(finish[1] , -110 * tune, -80 * tune, null);
			} 
			clearTimer++;
			
			if(clearTimer >= 160){
				canvas.drawBitmap(finish[0] , -110 * tune, -80 * tune, null);
				if(  0  <= fadeTimer &&  fadeTimer < 255){
					fadeTimer = fadeTimer + 5;
					
				    
				    Paint black = new Paint();
					black.setColor(Color.argb(fadeTimer, 0, 0, 0));
					Rect rect = new Rect(0, 0, displayX, displayY);
					canvas.drawRect(rect, black);
			}else if(fadeTimer == 255){
				Paint black = new Paint();
				black.setColor(Color.argb(fadeTimer, 0, 0, 0));
				Rect rect = new Rect(0, 0, displayX, displayY);
				canvas.drawRect(rect, black);
					
					
					gameState = GAME_RESULT;
					fadeCheck = false;
					goalState = GOAL_READY;
					fadeTimer = 0;
					clearTimer = 0;
					
					
			}
				
			}
			break;
		}
		
	
	}
	
	//チュートリアル画面■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	public void tutorialScene(Canvas canvas){
		
		//画像関連の描写
		getBackGround(canvas);
		getGround(canvas);
		getLadder(canvas);
		getStone(canvas);
		getTrap(canvas);
		getUI(canvas);
		getMonyako(canvas);
		getKiraEffect(canvas);
		getGet(canvas);
		getTutorial(canvas);
		
		
	}
	
	
	//リザルト画面■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	public void resultScene(Canvas canvas){
		//使わなくなったプレイ画面関係の画像メモリ解放
		if( ground != null){
			playLoadClear();
		}
		
		//点数計算
		score.comboCount();
		if(bonusCheck == false){
			if( score.maxCombo != 0){
				score.point = score.point + (score.maxCombo -1) * 50;
			}
			if( 0 < stone.have[3] && 0 < stone.have[4] && 0 < stone.have[5]){
				score.point = score.point + (score.point / 20);
				if( 0 < stone.have[0] && 0 < stone.have[1] && 0 < stone.have[2] ){
					score.point = score.point + (score.point / 10);
					
				}
			}
			bonusCheck = true;
		}
		score.scoreCount();
		scoreLoad();
		if( score.endlessMode == false){
			if( score.normalRankingPoint[0] <= score.point && saveCheck == false){
				score.normalRankingPoint[2] = score.normalRankingPoint[1];
				score.normalRankingPoint[1] = score.normalRankingPoint[0];
				score.normalRankingPoint[0] = score.point;
			
				firstSave();
				secondSave();
				thirdSave();
			} else if( score.normalRankingPoint[1] <= score.point && 
					score.point < score.normalRankingPoint[0]  && saveCheck == false){
			
				score.normalRankingPoint[2] = score.normalRankingPoint[1];
				score.normalRankingPoint[1] = score.point;
				secondSave();
				thirdSave();
			} else if( score.normalRankingPoint[2] <= score.point &&
					score.point < score.normalRankingPoint[1]  && saveCheck == false){
			
				score.normalRankingPoint[2] = score.point;
			
				thirdSave();
			}
			saveCheck = true;
		} else if( score.endlessMode == true){
			//エンドレス用セーブ
			
			//1位
			if( score.endelessRankingPoint[0] <= score.point && saveCheck == false){
				score.endelessRankingPoint[2] = score.endelessRankingPoint[1];
				score.endelessRankingPoint[1] = score.endelessRankingPoint[0];
				score.endelessRankingPoint[0] = score.point;
			
				endlessFirstSave();
				endlessSecondSave();
				endlessThirdSave();
			} else if( score.endelessRankingPoint[1] <= score.point && 
					score.point < score.endelessRankingPoint[0]  && saveCheck == false){
			//2位
				score.endelessRankingPoint[2] = score.endelessRankingPoint[1];
				score.endelessRankingPoint[1] = score.point;
				endlessSecondSave();
				endlessThirdSave();
			} else if( score.endelessRankingPoint[2] <= score.point && 
					score.point < score.endelessRankingPoint[1]  && saveCheck == false){
			//3位
				score.endelessRankingPoint[2] = score.point;
			
				endlessThirdSave();
			}
			saveCheck =true;
		}

		switch(resultState){
		case RESULT_BEFORE:
			resultBackGround[0] = Bitmap.createScaledBitmap(resultBackGround[0] , displayX , displayY, true);
			canvas.drawBitmap( resultBackGround[0] , 0 , 0, null);
			
			if( score.maxCombo  < score.combo){
				score.maxCombo = score.combo;
				
			}
			resultTimer++;
			if(resultTimer == 25 && resultCheck == NOT){
				resultCheck = STONE;
			}
			switch(resultCheck){
			case STONE:
				canvas.drawBitmap( resultWindow , 20 * tune , 60 * tune, null);
				if( saveCheck == true && bonusCheck == true ){
					canvas.drawBitmap(skip, 0 , 630 * tune, null);
				}
				if( 35 <= resultTimer && resultTimer <= 45){
					loadStoneState[0] = true;
					drawStone(canvas);
				} else if( 45 <= resultTimer && resultTimer <= 55){
					loadStoneState[1] = true;
					drawStone(canvas);
				} else if( 55 <= resultTimer && resultTimer <= 65){
					loadStoneState[2] = true;
					drawStone(canvas);
				} else if( 65 <= resultTimer && resultTimer <= 75){
					loadStoneState[3] = true;
					drawStone(canvas);
				} else if( 75 <= resultTimer && resultTimer <= 85){
					loadStoneState[4] = true;
					drawStone(canvas);
				} else if( 85 <= resultTimer && resultTimer <= 95 ){
					loadStoneState[5] = true;
					drawStone(canvas);
				} //個数表示
				else if( 95 <= resultTimer && resultTimer <= 105 ){
					loadCount = 1;
					stoneCount();
					drawStone(canvas);
					drawLow(canvas);
					
				}else if( 105 <= resultTimer && resultTimer <= 115 ){
					loadCount = 2;
					stoneCount();
					drawStone(canvas);
					drawLow(canvas);
					drawMiddle(canvas);
				}else if( 115 <= resultTimer && resultTimer <= 125 ){
					
					loadCount = 3;
					stoneCount();
					drawStone(canvas);
					drawLow(canvas);
					drawMiddle(canvas);
					drawHigh(canvas);
				}else if( 125 <= resultTimer && resultTimer <= 135 ){
					loadCount = 4;
					stoneCount();
					drawStone(canvas);
					drawLow(canvas);
					drawMiddle(canvas);
					drawHigh(canvas);
					drawRed(canvas);
				}else if( 135 <= resultTimer && resultTimer <= 145 ){
					loadCount = 5;
					stoneCount();
					drawStone(canvas);
					drawLow(canvas);
					drawMiddle(canvas);
					drawHigh(canvas);
					drawRed(canvas);
					drawGreen(canvas);
				}else if( 145 <= resultTimer &&  resultTimer < 205 ){
					loadCount = 6;
					stoneCount();
					drawStone(canvas);
					drawLow(canvas);
					drawMiddle(canvas);
					drawHigh(canvas);
					drawRed(canvas);
					drawGreen(canvas);
					drawBlue(canvas);
				}else if( resultTimer == 205 ){
					
					resultCheck = BONUS;
					resultTimer = 0;
					loadCount = 0;
				}
			break;
			
			case BONUS:
				canvas.drawBitmap( resultBackGround[0] , 0 , 0, null);
				canvas.drawBitmap( resultWindow , 20 * tune , 60 * tune, null);
				if( saveCheck == true && bonusCheck == true ){
					canvas.drawBitmap(skip, 0 , 630 * tune, null);
				}
				if( 0 <= resultTimer && resultTimer < 120){
					canvas.drawBitmap(bonus[0] , 70 * tune, 270 * tune, null);
					drawCombo(canvas);
					if( ( 0 < stone.have[3] && 0 < stone.have[4] && 0 < stone.have[5] ) && 
						( 40 <= resultTimer && resultTimer < 120)	){
						canvas.drawBitmap(bonus[1] , 100 * tune, 370 * tune, null);
						if( ( 0 < stone.have[0] && 0 < stone.have[1] && 0 < stone.have[2] ) &&
							( 80 <= resultTimer && resultTimer < 120) ){
							canvas.drawBitmap(bonus[2] , 100 * tune, 470 * tune, null);
						}
					}
				}else if( resultTimer == 120){
					
					resultCheck = KIRA;
					resultTimer = 0;
					
				}
					
			break;
			
			case KIRA:
				canvas.drawBitmap( resultBackGround[0] , 0 , 0, null);
				canvas.drawBitmap( resultWindow , 20 * tune , 60 * tune, null);
				canvas.drawBitmap( resultKira , 640 * tune , 340 * tune, null);
				if( saveCheck == true && bonusCheck == true ){
					canvas.drawBitmap(skip, 0 , 630 * tune, null);
				}
				if( score.endlessMode == false){
					if( 0 < resultTimer && resultTimer <= 25){
						
						loadCount = 1;
						drawScore(canvas);
					}else if( 25 <= resultTimer && resultTimer <= 50){
						
						loadCount = 2;
						drawScore(canvas);
					}else if( 50 <= resultTimer && resultTimer < 100){
					
						loadCount = 3;
						drawScore(canvas);
					}else if( resultTimer == 100){
					
						resultState = RESULT_AFTER;
						resultTimer = 0;
					}
				}else if( score.endlessMode == true){
					if( 0 < resultTimer && resultTimer <= 25){
						
						loadCount = 1;
						drawScore(canvas);
					}else if( 25 <= resultTimer && resultTimer <= 50){
						
						loadCount = 2;
						drawScore(canvas);
					}else if( 50 <= resultTimer && resultTimer < 75){
						
						loadCount = 3;
						drawScore(canvas);
					}else if( 75 <= resultTimer && resultTimer < 125){
						
						loadCount = 4;
						drawScore(canvas);
					}else if( resultTimer == 125){
					
						resultState = RESULT_AFTER;
						resultTimer = 0;
					}
				}
			break;
			}
		break;
		
		case RESULT_AFTER:
			//スコアが2000点未満の時、悪い結果発表
			if(score.point < 2000){
				if(seResultBadLoad == false){
				se.play(seResultBadAfter, 1.0F, 1.0F, 0, 0, 1.0F);
					seResultBadLoad = true;
				}
				resultBackGround[2] = Bitmap.createScaledBitmap(resultBackGround[2]  , displayX ,displayY, true);
				canvas.drawBitmap( resultBackGround[2] , 0 , 0, null);
			//スコアが2000点以上の時、良い結果発表
			} else if(score.point >= 2000){
				if( seResultLoad == 0){
					se.play(seResultAfter, 1.0F, 1.0F, 0, 0, 1.0F);
					seResultLoad++;
				} else if( 20 >= seResultLoad && seResultLoad >= 1){
					seResultLoad++;
				}
				
				resultBackGround[1] = Bitmap.createScaledBitmap(resultBackGround[1] , displayX ,displayY, true);
			canvas.drawBitmap( resultBackGround[1] , 0 , 0, null);
			}
			canvas.drawBitmap( resultWindow , 20 * tune , 60 * tune, null);
			canvas.drawBitmap( resultKira , 640 * tune , 340 * tune, null);
			canvas.drawBitmap( resultGet , 560 * tune , 470 * tune, null);
		
			if( score.endlessMode == false){
				loadCount = 4;
			} else if( score.endlessMode == true){
				loadCount = 5;
			}
			drawScore(canvas);
			if( touchState != TOUCH_MENU){
				canvas.drawBitmap(resultHome[0], 865 * tune, 590 * tune, null);
			} else if( touchState == TOUCH_MENU){
				canvas.drawBitmap(resultHome[1], 880 * tune, 600 * tune, null);
			}
			if( touchState != TOUCH_END){
				canvas.drawBitmap(menuEnd[0], 1030 * tune, 590 * tune, null);
			}else if( touchState == TOUCH_END){
				canvas.drawBitmap(menuEnd[1], 1045 * tune , 600 * tune, null);
			}
			
			menuState = 0;
			
			
				
				
				
			if( endCheck == RESULT_END){
				 canvas.drawBitmap(endWindow , 340 * tune, 150 * tune, null);
				 if( touchState != TOUCH_YES){
				    canvas.drawBitmap(checkYes[0] , 450 * tune, 310 * tune, null); 
				 } else if( touchState == TOUCH_YES){
					    canvas.drawBitmap(checkYes[1] , 460 * tune, 320 * tune, null); 
				}   
				 if( touchState != TOUCH_NO){
					    canvas.drawBitmap(checkNo[0] , 670 * tune, 310 * tune, null);
				}if( touchState == TOUCH_NO){
						    canvas.drawBitmap(checkNo[1] , 680 * tune, 320 * tune, null);
				}
			}
			if( 5 <= fadeTimer && fadeTimer < 255){
				if(endCheck == RESULT_END){
					fadeTimer = fadeTimer + 25;
				}
				Paint black = new Paint();
				black.setColor(Color.argb(fadeTimer, 0, 0, 0));
				Rect rect = new Rect(0, 0, displayX, displayY);
			    canvas.drawRect(rect, black);
			    
			}else if(fadeTimer == 255){
				Paint black = new Paint();
				black.setColor(Color.argb(fadeTimer, 0, 0, 0));
				Rect rect = new Rect(0, 0, displayX, displayY);
			    canvas.drawRect(rect, black);
			    if( endCheck == RESULT_END){
			    	this.finish();
			    

				}
			}  
			
			
		break;
		
		}
		
	}
	
	
	//メソッド一覧■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	//はしご昇降メソッド
	public void ladderAction(){
		
		//もにゃこが上段にいる時に
		if(95 * tune - 5 <=  monyako.y && monyako.y <= 95 * tune + 5){
			for( int i = 0; i < 4; i++){
				//梯子が中段にあるなら
				if( ladder.y[i] == 220 * tune && ladder.check[i] == true && ladder.x[i] + 75 * tune < 280 * tune){
					ladder.check[i] = false;
					ladder.state[i] = USED;
					//梯子で下降する
					monyako.state = Monyako.MONYAKO_DOWN;
					return;
				}
			}
		//もにゃこが下段にいる時に
		}else if( 555 * tune - 5 <=  monyako.y && monyako.y <= 555 * tune + 5){
			for( int i = 0; i < 4; i++){
				//梯子が下段にあるなら
				if( ladder.y[i] == 450 * tune && ladder.check[i] == true && ladder.x[i] + 75 * tune < 280 * tune){
					ladder.check[i] = false;
					ladder.state[i] = USED;
					//梯子で上昇する
					monyako.state = Monyako.MONYAKO_UP;
					return;
				} 
			}
			
		//もにゃこが中段にいる時
		}else if( 325 * tune - 5 <=  monyako.y && monyako.y <= 325 * tune + 5 )
			{
			
			for( int i = 0; i < 4; i++){
				//はしごが下段にあるなら
				if( ladder.y[i] == 450 * tune  &&
					ladder.check[i] == true && ladder.x[i] + 75 * tune < 280 * tune){
					ladder.check[i] = false;
					ladder.state[i] = USED;
					//梯子で下降する
					monyako.state = Monyako.MONYAKO_DOWN;
					return;
				} 
				
			}
				
				
			for( int i = 0; i < 4; i++){
				//はしごが中段にあるなら
					if( ladder.y[i] == 220 * tune && ladder.x[i] + 75 * tune < 280 * tune &&
						ladder.check[i] == true	){
						ladder.check[i] = false;
						ladder.state[i] = USED;
						//梯子で上昇する
						monyako.state = Monyako.MONYAKO_UP;
						return;
					}
					
				}
				
				
				
		}
	}
	
	
	//中位置はしご設置
	public void setMiddleLadder(){
		
			if( ladder.state[0] == NOT ){
				ladder.x[0] = TouchX;
				ladder.y[0] = 220 * tune;
				ladder.state[0] = SET;
			
			} else if( ladder.state[1] == NOT ){
				ladder.x[1] = TouchX;
				ladder.y[1] = 220 * tune;
				ladder.state[1] = SET;
			
			}  else if( ladder.state[2] == NOT ){
				ladder.x[2] = TouchX;
				ladder.y[2] = 220 * tune;
				ladder.state[2] = SET;
			
			} else if( ladder.state[3] == NOT ){
				ladder.x[3] = TouchX;
				ladder.y[3] = 220 * tune;
				ladder.state[3] = SET;
			
			}
			
		
	}
	
	
	//低位置はしご設置
	
	public void setLowLadder(){
		
			if( ladder.state[0] == NOT ){
				ladder.x[0] = TouchX;
				ladder.y[0] = 450 * tune;
				ladder.state[0] = SET;
			} else if( ladder.state[1] == NOT ){
				ladder.x[1] = TouchX;
				ladder.y[1] = 450 * tune;
				ladder.state[1] = SET;
			} else if( ladder.state[2] == NOT ){
				ladder.x[2] = TouchX;
				ladder.y[2] = 450 * tune;
				ladder.state[2] = SET;
			} else if( ladder.state[3] == NOT ){
				ladder.x[3] = TouchX;
				ladder.y[3] = 450 * tune;
				ladder.state[3] = SET;
			}
		
		
		
	}
	
	
	//トラップ生成
	public void createTrap1(){
		
		
		
		
		if( 0 < stage.startPoint && stage.startPoint < 100){
			switch(stage.setTrap){
			
			case 0:
				if(trap.normalBlock[0] == false){	
					switch(trap.rndY[0]){
							case 0:
								trap.normalBlockY[0] = 25 * tune;
							break;
							case 1:
								trap.normalBlockY[0] = 255 * tune;
							break;
							case 2:
								trap.normalBlockY[0] = 485 * tune;
							break;
						}
						
						
				}	
			trap.normalBlock[0] = true;
			
			break;
			
			case 1:
				if(trap.normalBlock[0] == false){	
					switch(trap.rndY[0]){
							case 0:
								trap.normalBlockY[0] = 25 * tune;
							break;
							case 1:
								trap.normalBlockY[0] = 255 * tune;
							break;
							case 2:
								trap.normalBlockY[0] = 485 * tune;
							break;
					}
						
						
				}	
			trap.normalBlock[0] = true;
				
			break;
			case 2:
				//座標
				trap.normalBlockY[0] = 485 * tune;
				trap.normalBlockY[6] = 255 * tune;
				trap.normalBlockY[1] = 25 * tune;
			
					trap.normalBlockX[6] = displayX + 500 * tune;
					trap.normalBlockX[1] = displayX + 500 * tune;
				
				trap.normalBlock[0] = true;
				trap.normalBlock[6] = true;
				trap.normalBlock[1] = true;
				
				
				
			break;
			}
			
		
	} else if( 1000* stage.cave  < stage.startPoint && stage.startPoint < 1100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
		
			if(trap.normalBlock[1] == false){
				switch(trap.rndY[1]){
				case 0:
					trap.normalBlockY[1] = 25 * tune;
					break;
				case 1:
					trap.normalBlockY[1] = 255 * tune;
					break;
				case 2:
					trap.normalBlockY[1] = 485 * tune;
					break;
				}
			}
			
			trap.normalBlock[1] = true;
		break;
		
		case 1:
			trap.normalBlockY[1] = 485 * tune;
			
			trap.normalBlockX[7] = displayX + 310 * tune;
		
			
			trap.normalBlockY[7] = 255 * tune;
			trap.normalBlock[1] = true;
			trap.normalBlock[7] = true;
			
		break;
		case 2:
			
			
			
			if(trap.normalBlock[7] == false){	
				switch(trap.rndY[0]){
						case 0:
							trap.normalBlockY[7] = 25 * tune;
						break;
						case 1:
							trap.normalBlockY[7] = 255 * tune;
						break;
						case 2:
							trap.normalBlockY[7] = 485 * tune;
						break;
					}
			}	
			trap.normalBlock[7] = true;
		break;
		}
		
	}else if( 2000 * stage.cave < stage.startPoint && stage.startPoint < 2100* stage.cave ){
		
		
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlockY[6] = 25 * tune;
			trap.normalBlockY[2] = 255 * tune;
			if(trap.normalBlock[2] == true){
			switch(trap.rndX[0]){
			case 0:
				trap.normalBlockX[2] = displayX + 200 * tune;
			break;
			case 1:
				trap.normalBlockX[2] = displayX + 310 * tune;
			break;
			case 2:
				trap.normalBlockX[2] = displayX + 400 * tune;
			break;
			
			
			}
			}
			trap.normalBlock[6] = true;
			
			trap.normalBlock[2] = true;
			
		break;
		
		case 1:
			trap.normalBlockY[2] = 25 * tune;
			trap.normalBlockY[8] = 255 * tune;
			trap.normalBlockY[3] = 485 * tune;
			trap.normalBlockX[8] = displayX + 500 * tune;
			trap.normalBlockX[3] = displayX + 500 * tune;
			
			
			trap.normalBlock[2] = true;
			trap.normalBlock[8] = true;
			trap.normalBlock[3] = true;
			
		break;
		case 2:
			
			if(trap.normalBlock[8] == false){
				switch(trap.rndY[0]){
						case 0:
							trap.normalBlockY[8] = 25 * tune;
						break;
						case 1:
							trap.normalBlockY[8] = 255 * tune;
						break;
						case 2:
							trap.normalBlockY[8] = 485 * tune;
						break;
					}
			}
			trap.normalBlock[8] = true;
		break;
		}
			
			
	}else if( 3000 * stage.cave < stage.startPoint && stage.startPoint < 3100* stage.cave ){
	
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlockY[0] = 255 * tune;
			
			trap.normalBlockY[4] = 485 * tune;
			trap.normalBlock[0] = true;
			trap.normalBlock[4] = true;
			
		break;
		
		case 1:
			trap.normalBlock[5] = true;
			trap.normalBlock[9] = true;
			trap.normalBlockY[5] = 255 * tune;
			trap.normalBlockY[9] = 485 * tune;
			
		break;
		case 2:
			//座標
			trap.normalBlockY[2] = 25 * tune;
			trap.normalBlockY[3] = 255 * tune;
			trap.normalBlockY[4] = 485 * tune;
			
			trap.normalBlockX[4] = displayX + 500 * tune;
			
			
			trap.normalBlock[2] = true;
			trap.normalBlock[3] = true;
			trap.normalBlock[4] = true;
			
			
		break;
		}

		
	}else if( 4000 * stage.cave < stage.startPoint && stage.startPoint < 4100* stage.cave ){
	
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlockY[1] = 255 * tune;
			trap.normalBlockX[3] = displayX + 500 * tune;
			
			trap.normalBlockY[3] = 255 * tune;
			trap.normalBlockY[5] = 25 * tune;
			
			trap.normalBlock[1] = true;
			trap.normalBlock[3] = true;
			trap.normalBlock[5] = true;
			
		break;
		
		case 1:
			if(trap.normalBlock[11] == false){
				switch(trap.rndY[0]){
						case 0:
							trap.normalBlockY[11] = 25 * tune;
						break;
						case 1:
							trap.normalBlockY[11] = 255 * tune;
						break;
						case 2:
							trap.normalBlockY[11] = 485 * tune;
						break;
				
					
					
			}	
			}
			trap.normalBlock[11] = true;
			 
		break;
		case 2:
			if(trap.normalBlock[9] == false){
				switch(trap.rndY[0]){
						case 0:
							trap.normalBlockY[9] = 25 * tune;
						break;
						case 1:
							trap.normalBlockY[9] = 255 * tune;
						break;
						case 2:
							trap.normalBlockY[9] = 485 * tune;
						break;
					}
			}
			trap.normalBlock[9] = true;
		break;
		}

	}else if( 5000 * stage.cave < stage.startPoint && stage.startPoint < 5100 * stage.cave ){
		
		switch(stage.setTrap){
		
		case 0:
			
			trap.normalBlockY[2] = 25 * tune;
			trap.normalBlockX[8] = displayX + 600 * tune;
			
			trap.normalBlockY[8] = 255 * tune;
			trap.normalBlockY[9] = 485 * tune;
			
			trap.normalBlock[2] = true;
			trap.normalBlock[8] = true;
			trap.normalBlock[9] = true;
		break;
		
		case 1:
			trap.normalBlockY[8] = 255 * tune;
			trap.normalBlockY[6] = 255 * tune;
			trap.normalBlockY[2] = 485 * tune;
			trap.normalBlockX[6] = displayX + 310 * tune;
			trap.normalBlockX[2] = displayX + 600 * tune;
			
			
			trap.normalBlock[8] = true;
			trap.normalBlock[6] = true;
			trap.normalBlock[2] = true;
			
		break;
		case 2:
			//座標
			trap.normalBlockY[5] = 25 * tune;
			trap.normalBlockY[6] = 485 * tune;
			trap.normalBlockY[7] = 255 * tune;
			
			trap.normalBlockX[7] = displayX + 500 * tune;
			
			
			trap.normalBlock[5] = true;
			trap.normalBlock[6] = true;
			trap.normalBlock[7] = true;
			
			
		break;
		}	
		
	}
}
	//中ポ１
	public void createTrap2(){	
		if( 6000 * stage.cave < stage.startPoint && stage.startPoint < 6100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlockY[4] = 255 * tune;
			if(trap.normalBlock[4] == false){
			switch(trap.rndX[0]){
			case 0:
				trap.normalBlockX[4] = displayX + 200 * tune;
			break;
			case 1:
				trap.normalBlockX[4] = displayX + 310 * tune;
			break;
			case 2:
				trap.normalBlockX[4] = displayX + 400 * tune;
			break;
			}
			
			}
			trap.normalBlockY[10] = 485 * tune;
			
			trap.normalBlock[4] = true;
			trap.normalBlock[10] = true;
			
		break;
		
		case 1:
			trap.normalBlockY[4] = 25 * tune;
			trap.normalBlockY[5] = 255 * tune;
			if(trap.normalBlock[4] == false){
			switch(trap.rndX[0]){
			case 0:
				trap.normalBlockX[4] = displayX + 310 * tune;
			break;
			case 1:
				trap.normalBlockX[4] = displayX + 400 * tune;
			break;
			case 2:
				trap.normalBlockX[4] = displayX + 500 * tune;
			break;
			
			}
			switch(trap.rndX[1]){
			case 0:
				trap.normalBlockX[5] = displayX + 310 * tune;
			break;
			case 1:
				trap.normalBlockX[5] = displayX + 400 * tune;
			break;
			case 2:
				trap.normalBlockX[5] = displayX + 500 * tune;
			break;
			
			}
			}
			trap.normalBlock[4] = true;
			trap.normalBlock[5] = true;
			
		break;
		case 2:
			//座標
			trap.normalBlockY[11] = 25 * tune;
			trap.normalBlockY[10] = 485 * tune;
			trap.normalBlockY[0] = 25 * tune;
			trap.kiraBlockY[2][1] = 255 * tune;
			trap.normalBlockX[0] =  displayX + 400 * tune;
			trap.kiraBlockX[2][1] =  displayX + 400 * tune;
			
			
			trap.normalBlock[11] = true;
			trap.normalBlock[10] = true;
			trap.normalBlock[0] = true;
			trap.kiraBlock[2][1] = true;
			
			
		break;
		}

	}else if( 7000* stage.cave  < stage.startPoint && stage.startPoint < 7100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.kiraBlockY[0][0] = 25 * tune;
			if(trap.normalBlock[6] == false){
			switch(trap.rndX[0]){
			case 0:
				trap.normalBlockX[6] = displayX + 200 * tune;
			break;
			case 1:
				trap.normalBlockX[6] = displayX + 310 * tune;
			break;
			case 2:
				trap.normalBlockX[6] = displayX + 400 * tune;
			break;
			
			
			}
			}
			trap.normalBlockY[6] = 255 * tune;
			trap.kiraBlock[0][0] = true;
			trap.normalBlock[6] = true;
			
		break;
		
		case 1:
			trap.normalBlockY[9] = 25 * tune;
			trap.normalBlockY[10] = 485 * tune;
			trap.normalBlockY[1] = 485 * tune;
			trap.normalBlockX[1] = displayX + 400 * tune;
			
			trap.normalBlock[9] = true;
			trap.normalBlock[10] = true;
			trap.normalBlock[1] = true;
			
			
		break;
		case 2:
			//
			trap.normalBlockY[2] = 25 * tune;
			trap.normalBlockY[3] = 485 * tune;
			trap.normalBlockX[3] = displayX + 400 * tune;
			
			
			trap.normalBlock[2] = true;
			trap.normalBlock[3] = true;
			
		break;
		}

	}else if( 8000* stage.cave  < stage.startPoint && stage.startPoint < 8100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlockY[3] = 255 * tune;
			trap.normalBlockX[11] = displayX + 500 * tune;
			
			trap.normalBlockY[11] = 255 * tune;
			
			trap.normalBlock[3] = true;
			trap.normalBlock[11] = true;
			
		break;
		
		case 1:
			trap.normalBlockY[3] = 255 * tune;
			trap.normalBlockY[11] = 25 * tune;
			trap.normalBlockX[11] = displayX + 310 * tune;
			
			trap.normalBlock[3] = true;
			trap.normalBlock[11] = true;
			
		break;
		
		case 2:
			if(trap.normalBlock[4] == false){
				switch(trap.rndY[0]){
						case 0:
							trap.normalBlockY[4] = 25 * tune;
						break;
						case 1:
							trap.normalBlockY[4] = 255 * tune;
						break;
						case 2:
							trap.normalBlockY[4] = 485 * tune;
						break;
				}
					
			}		
			
			if(trap.normalBlockY[4] == 25 * tune ){
				trap.normalBlockY[5] = 255 * tune;
			} else if(trap.normalBlockY[4] == 255 * tune ){
				trap.normalBlockY[5] = 485 * tune;
			} else if(trap.normalBlockY[4] == 485 * tune ){
				trap.normalBlockY[5] = 25 * tune;
			}
			
			trap.normalBlock[4] = true;
			trap.normalBlock[5] = true;
		break;
		}

	}else if( 9000 * stage.cave < stage.startPoint && stage.startPoint < 9100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlock[0] = true;
			trap.normalBlock[4] = true;
			trap.normalBlockY[0] = 25 * tune;
			trap.normalBlockY[4] = 255 * tune;

		break;
		
		case 1:
			trap.normalBlockY[2] = 485 * tune;
			trap.normalBlockY[4] = 25 * tune;
			trap.normalBlockY[7] = 255 * tune;
			trap.normalBlockX[4] = displayX + 400 * tune;
			trap.normalBlockX[7] = displayX + 650 * tune;
			if(monyako.color == Monyako.SET_RED || stone.leftStock == Stone.STOCK_RED  && trap.kiraBlock[0][0] == false && trap.kiraBlock[1][0] == false && trap.kiraBlock[2][0] == false  ){
				trap.kiraBlock[0][0] = true;
				trap.kiraBlockY[0][0] = 255 * tune;
				trap.kiraBlock[0][1] = true;
				trap.kiraBlockY[0][1] = 255 * tune;
				trap.kiraBlockX[0][1] = displayX + 400 * tune;
			} else if(monyako.color == Monyako.SET_GREEN || stone.leftStock == Stone.STOCK_GREEN && trap.kiraBlock[0][0] == false && trap.kiraBlock[1][0] == false && trap.kiraBlock[2][0] == false ){
				trap.kiraBlock[1][0] = true;
				trap.kiraBlockY[1][0] = 255 * tune;
				trap.kiraBlock[1][1] = true;
				trap.kiraBlockY[1][1] = 255 * tune;
				trap.kiraBlockX[1][1] = displayX + 400 * tune;
			} else if(monyako.color == Monyako.SET_BLUE || stone.leftStock == Stone.STOCK_BLUE && trap.kiraBlock[1][0] == false && trap.kiraBlock[0][0] == false && trap.kiraBlock[2][0] == false ){
				trap.kiraBlock[2][0] = true;
				trap.kiraBlockY[2][0] = 255 * tune;
				trap.kiraBlock[2][1] = true;
				trap.kiraBlockY[2][1] = 255 * tune;
				trap.kiraBlockX[2][1] = displayX + 400 * tune;
			}
			trap.normalBlock[2] = true;
			trap.normalBlock[4] = true;
			trap.normalBlock[7] = true;
			
			
		break;
		case 2:
			if(monyako.color == Monyako.SET_GREEN || stone.leftStock == Stone.STOCK_GREEN ){
				trap.kiraBlock[1][1] = true;
				trap.kiraBlockY[1][1] = 485 * tune;
				trap.kiraBlockX[1][1] = displayX + 770 * tune;
			} else if(monyako.color == Monyako.SET_BLUE || stone.leftStock == Stone.STOCK_BLUE ){
				trap.kiraBlock[2][1] = true;	
				trap.kiraBlockY[2][1] = 255 * tune;	
				
			}
			trap.normalBlockY[8] = 485 * tune;
			trap.normalBlockY[9] = 25 * tune;
			trap.kiraBlockY[0][1] = 255 * tune;
			trap.normalBlockY[7] = 25 * tune;
			
			trap.normalBlockX[9] = displayX + 400 * tune;
			trap.kiraBlockX[0][1] = displayX + 400 * tune;
			trap.normalBlockX[7] = displayX + 740 * tune;
			
			trap.normalBlock[8] = true;
			if(score.endlessMode)trap.normalBlock[9] = true;
			trap.kiraBlock[0][1] = true;
			trap.normalBlock[7] = true;
			
		break;
		}
		
	}else if( 10000 * stage.cave < stage.startPoint && stage.startPoint < 10100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			if( trap.rndX[2] == 1 && stone.leftStock != NOT || monyako.color != NOT && ( stone.leftStock != Stone.STOCK_GREEN && stone.leftStock != Stone.STOCK_BLUE)){
				if(monyako.color == Monyako.SET_RED || stone.leftStock == Stone.STOCK_RED ){
					if(score.endlessMode)trap.normalBlock[5] = true;
					trap.kiraBlock[0][0] = true;
					trap.normalBlock[6] = true;
					
				} else if(monyako.color == Monyako.SET_GREEN || stone.leftStock == Stone.STOCK_GREEN && ( stone.leftStock != Stone.STOCK_RED && stone.leftStock != Stone.STOCK_BLUE) ){
					if(score.endlessMode)trap.normalBlock[5] = true;
					trap.kiraBlock[1][0] = true;
					trap.normalBlock[6] = true;
					
				} else if(monyako.color == Monyako.SET_BLUE || stone.leftStock == Stone.STOCK_BLUE && ( stone.leftStock != Stone.STOCK_RED && stone.leftStock != Stone.STOCK_RED) ){
					if(score.endlessMode)trap.normalBlock[5] = true;
					trap.kiraBlock[2][0] = true;
					trap.normalBlock[6] = true;
					
				}
				
			}else if(trap.rndX[2] != 1){
				trap.normalBlock[1] = true;
				trap.normalBlock[2] = true;
			}
			if( trap.kiraBlock[0][0] == true){ 
				trap.normalBlockY[5] = 25 * tune;
				trap.kiraBlockY[0][0] = 485 * tune;
				trap.normalBlockY[6] = 255 * tune;
			} else if( trap.kiraBlock[1][0] == true){ 
				trap.normalBlockY[5] = 25 * tune;
				trap.kiraBlockY[1][0] = 485 * tune;
				trap.normalBlockY[6] = 255 * tune;
			} else if( trap.kiraBlock[2][0] == true){ 
				trap.normalBlockY[5] = 25 * tune;
				trap.kiraBlockY[2][0] = 485 * tune;
				trap.normalBlockY[6] = 255 * tune;
			}else if (trap.kiraBlock[0][0] == false && trap.kiraBlock[1][0] == false && trap.kiraBlock[2][0] == false   ){
			trap.normalBlockY[1] = 255 * tune;
			trap.normalBlockY[2] = 485 * tune;
			}
		break;
		
		case 1:
			trap.normalBlock[9] = true;
			trap.normalBlock[5] = true;
			trap.normalBlockY[9] = 25 * tune;
			trap.normalBlockY[5] = 485 * tune;
		break;
		case 2:
			if(monyako.color == Monyako.SET_RED || stone.leftStock == Stone.STOCK_RED ){
				
				trap.kiraBlock[0][0] = true;
				trap.kiraBlockY[0][0] = 485 * tune;
				
			} else if(monyako.color == Monyako.SET_GREEN || stone.leftStock == Stone.STOCK_GREEN && ( monyako.color != Monyako.SET_BLUE) ){
				trap.kiraBlock[1][0] = true;
				trap.kiraBlockY[1][0] = 25 * tune;
				trap.kiraBlockX[1][0] = displayX + 310 * tune;
			} else if(monyako.color == Monyako.SET_BLUE || stone.leftStock == Stone.STOCK_BLUE  && ( monyako.color != Monyako.SET_GREEN)){
				trap.kiraBlock[2][0] = true;	
				trap.kiraBlockY[2][0] = 25 * tune;
				trap.kiraBlockX[2][0] = displayX + 310 * tune;
				
			}
			trap.normalBlockY[10] = 255 * tune;
			trap.normalBlockY[1] = 485 * tune;
			trap.normalBlockX[1] = displayX + 310 * tune;
			
			trap.normalBlock[10] = true;
			trap.normalBlock[1] = true;
		break;
		}
	
		
	}else if( 11000 * stage.cave < stage.startPoint && stage.startPoint < 11100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlock[3] = true;
			trap.normalBlock[7] = true;
			trap.normalBlockY[3] = 25 * tune;
			trap.normalBlockY[7] = 485 * tune;
		break;
		
		case 1:
			if(trap.normalBlock[0] == false){
				switch(trap.rndY[0]){
						case 0:
							trap.normalBlockY[0] = 25 * tune;
						break;
						case 1:
							trap.normalBlockY[0] = 255 * tune;
						break;
						case 2:
							trap.normalBlockY[0] = 485 * tune;
						break;
				}
					
			}	
			
			trap.normalBlock[0] = true;

		break;
		case 2:
			if(trap.normalBlock[4] == false){	
				switch(trap.rndY[0]){
						case 0:
							trap.normalBlockY[4] = 25 * tune;
						break;
						case 1:
							trap.normalBlockY[4] = 255 * tune;
						break;
						case 2:
							trap.normalBlockY[4] = 485 * tune;
						break;
				}
					
					
			}	
			if(trap.normalBlockY[4] == 25 * tune ){
				trap.normalBlockY[5] = 255 * tune;
			} else if(trap.normalBlockY[4] == 255 * tune ){
				trap.normalBlockY[5] = 485 * tune;
			} else if(trap.normalBlockY[4] == 485 * tune ){
				trap.normalBlockY[5] = 25 * tune;
			}
			
			trap.normalBlock[4] = true;
			trap.normalBlock[5] = true;
		break;
		}
	
		//中3
	}else if( 12000 * stage.cave < stage.startPoint && stage.startPoint < 12100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlockY[0] = 25 * tune;
			trap.normalBlockX[0] = displayX + 400 * tune;
			trap.normalBlockX[4] = displayX + 500 * tune;
			
			trap.normalBlockY[1] = 255 * tune;
			trap.normalBlockY[4] = 255 * tune;
			
			trap.normalBlock[0] = true;
			trap.normalBlock[1] = true;
			trap.normalBlock[4] = true;
			
		break;
		
		case 1:
			trap.normalBlockY[2] = 255 * tune;
			trap.normalBlockY[3] = 485 * tune;
			trap.normalBlockX[10] = displayX + 400 * tune;
			trap.normalBlockX[11] = displayX + 400 * tune;
			
			trap.normalBlockY[10] = 25 * tune;
			trap.normalBlockY[11] = 255 * tune;
			if(monyako.color == Monyako.SET_RED || stone.leftStock == Stone.STOCK_RED && trap.kiraBlock[0][0] == false  && trap.kiraBlock[1][0] == false && trap.kiraBlock[2][0] == false  ){
				trap.kiraBlock[0][0] = true;
				trap.kiraBlockY[0][0] = 255 * tune;
				trap.kiraBlock[0][1] = true;
				trap.kiraBlockY[0][1] = 485 * tune;
				trap.kiraBlockX[0][0] = displayX + 800 * tune;
				trap.kiraBlockX[0][1] = displayX + 800 * tune;
			} else if(monyako.color == Monyako.SET_GREEN || stone.leftStock == Stone.STOCK_GREEN && trap.kiraBlock[0][0] == false && trap.kiraBlock[1][0] == false  && trap.kiraBlock[2][0] == false ){
				trap.kiraBlock[1][0] = true;
				trap.kiraBlockY[1][0] = 255 * tune;
				trap.kiraBlock[1][1] = true;
				trap.kiraBlockY[1][1] = 485 * tune;
				trap.kiraBlockX[1][0] = displayX + 800 * tune;
				trap.kiraBlockX[1][1] = displayX + 800 * tune;
			} else if(monyako.color == Monyako.SET_BLUE || stone.leftStock == Stone.STOCK_BLUE && trap.kiraBlock[1][0] == false && trap.kiraBlock[0][0] == false && trap.kiraBlock[2][0] == false ){
				trap.kiraBlock[2][0] = true;
				trap.kiraBlockY[2][0] = 255 * tune;
				trap.kiraBlock[2][1] = true;
				trap.kiraBlockY[2][1] = 485 * tune;
				trap.kiraBlockX[2][0] = displayX + 800 * tune;
				trap.kiraBlockX[2][1] = displayX + 800 * tune;
			}
			trap.normalBlock[2] = true;
			trap.normalBlock[3] = true;
			trap.normalBlock[10] = true;
			trap.normalBlock[11] = true;
		break;
		case 2:
			trap.normalBlockY[3] = 255 * tune;
			trap.normalBlockY[7] = 485 * tune;
			trap.kiraBlockY[0][1] = 25 * tune;
			trap.kiraBlockY[1][1] = 255 * tune;
			trap.kiraBlockY[2][1] = 485 * tune;
			trap.kiraBlockX[0][1] = displayX + 400 * tune;
			trap.kiraBlockX[1][1] = displayX + 600 * tune;
			trap.kiraBlockX[2][1] = displayX + 220 * tune;
			
			trap.normalBlock[3] = true;
			if(score.endlessMode)trap.normalBlock[7] = true;
			trap.kiraBlock[0][1] = true;
			trap.kiraBlock[1][1] = true;
			trap.kiraBlock[2][1] = true;
			
		break;
		}
	
		
	} else if( 13000 * stage.cave < stage.startPoint && stage.startPoint < 13100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlockY[2] = 25 * tune;
			if(trap.normalBlock[2] == false){
			switch(trap.rndX[0]){
			case 0:
				trap.normalBlockX[2] = displayX + 310 * tune;
			break;
			case 1:
				trap.normalBlockX[2] = displayX + 400 * tune;
			break;
			case 2:
				trap.normalBlockX[2] = displayX + 500 * tune;
			break;
			
			}
			}
			trap.normalBlockY[11] = 25 * tune;
			trap.kiraBlockY[1][0] = 485 * tune;
			trap.kiraBlock[1][0] = true;
			trap.normalBlock[2] = true;
			trap.normalBlock[11] = true;
			
		break;
		
		case 1:
			trap.normalBlockY[1] = 255 * tune;
			trap.normalBlockX[5] = displayX + 310 * tune;
			trap.normalBlockX[7] = displayX + 310 * tune;
			
			trap.normalBlockY[5] = 25 * tune;
			trap.normalBlockY[7] = 255 * tune;
			trap.normalBlock[1] = true;
			trap.normalBlock[5] = true;
			trap.normalBlock[7] = true;
		break;
		case 2:
			trap.normalBlockY[11] = 485 * tune;
			trap.normalBlockY[10] = 485 * tune;
			trap.normalBlockX[10] = displayX + 600 * tune;
			
			trap.normalBlock[11] = true;
			trap.normalBlock[10] = true;
			
		break;
		}
		
		
	}else if( 14000 * stage.cave < stage.startPoint && stage.startPoint < 14100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlockY[5] = 25 * tune;
			trap.kiraBlockX[2][0] = displayX + 310 * tune;
			
			trap.kiraBlockY[2][0] = 485 * tune;
			trap.normalBlockX[6] = displayX + 600 * tune;
			
			trap.normalBlockY[6] = 255 * tune;
			trap.normalBlock[5] = true;
			trap.kiraBlock[2][0] = true;
			trap.normalBlock[6] = true;
			
		break;
		
		case 1:
			trap.normalBlock[4] = true;
			trap.normalBlock[6] = true;
			trap.normalBlockY[4] = 25 * tune;
			trap.normalBlockY[6] = 485 * tune;
			
		break;
		case 2:
			if(trap.normalBlock[4] == false){	
				switch(trap.rndY[0]){
						case 0:
							trap.normalBlockY[4] = 25 * tune;
						break;
						case 1:
							trap.normalBlockY[4] = 255 * tune;
						break;
						case 2:
							trap.normalBlockY[4] = 485 * tune;
						break;
				}
			}	
			if(trap.normalBlockY[4] == 25 * tune ){
				trap.normalBlockY[5] = 255 * tune;
			} else if(trap.normalBlockY[4] == 255 * tune ){
				trap.normalBlockY[5] = 485 * tune;
			} else if(trap.normalBlockY[4] == 485 * tune ){
				trap.normalBlockY[5] = 25 * tune;
			}
			
			trap.normalBlock[4] = true;
			trap.normalBlock[5] = true;
		break;
		}
	}else if( 15000* stage.cave  < stage.startPoint && stage.startPoint < 15100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
				trap.normalBlockX[1] = displayX + 500 * tune;
				trap.normalBlockX[3] = displayX + 500 * tune;
				
				trap.normalBlockY[1] = 25 * tune;
				trap.normalBlockY[3] = 255 * tune;
				trap.normalBlockY[7] = 255 * tune;
			trap.normalBlock[1] = true;
			trap.normalBlock[3] = true;
			trap.normalBlock[7] = true;
			
		break;
		
		case 1:
			trap.normalBlockY[2] = 255 * tune;
			trap.normalBlockX[8] = displayX + 400 * tune;
			trap.normalBlockX[9] = displayX + 400 * tune;
			
			trap.normalBlockY[8] = 25 * tune;
			trap.normalBlockY[9] = 255 * tune;
			trap.normalBlock[2] = true;
			trap.normalBlock[8] = true;
			trap.normalBlock[9] = true;
		break;
		case 2:
			 if(monyako.color == Monyako.SET_GREEN || stone.leftStock == Stone.STOCK_GREEN  ){
					trap.kiraBlock[1][0] = true;
					trap.kiraBlockY[1][0] = 255 * tune;
					trap.kiraBlockX[1][0] = displayX + 220 * tune;
			} else if(monyako.color == Monyako.SET_BLUE || stone.leftStock == Stone.STOCK_BLUE  ){
					trap.kiraBlock[2][0] = true;	
					trap.kiraBlockY[2][0] = 255 * tune;
					trap.kiraBlockX[2][0] = displayX + 970 * tune;
					
			}
			trap.normalBlockY[2] = 25 * tune;
			trap.normalBlockY[8] = 255 * tune;
			trap.normalBlockY[9] = 25 * tune;
			trap.kiraBlockY[0][0] = 485 * tune;
			
			trap.normalBlockX[9] =  displayX + 600 * tune;
			trap.kiraBlockX[0][0] =  displayX + 600 * tune;
			
			trap.normalBlock[2] = true;
			trap.normalBlock[8] = true;
			if(score.endlessMode)trap.normalBlock[9] = true;
			trap.kiraBlock[0][0] = true;
		break;
		}

		
	}else if( 16000 * stage.cave < stage.startPoint && stage.startPoint < 16100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlockY[0] = 25 * tune;
			trap.normalBlockX[4] = displayX + 600 * tune;
			trap.normalBlockX[10] = displayX + 600 * tune;
			
			trap.normalBlockY[4] = 255 * tune;
			trap.normalBlockY[5] = 485 * tune;
			trap.normalBlockY[10] = 485 * tune;
			trap.normalBlock[0] = true;
			trap.normalBlock[4] = true;
			if(score.endlessMode)trap.normalBlock[5] = true;
			trap.normalBlock[10] = true;
			
		break;
		
		case 1:
			trap.normalBlockY[1] = 485 * tune;
			trap.normalBlockX[5] = displayX + 400 * tune;
			trap.normalBlockX[7] = displayX + 400 * tune;
			trap.normalBlockX[11] = displayX + 600 * tune;
			if(monyako.color == Monyako.SET_RED || stone.leftStock == Stone.STOCK_RED && trap.kiraBlock[1][0] == false && trap.kiraBlock[2][0] == false  ){
				trap.kiraBlock[0][0] = true;
				trap.kiraBlockY[0][0] = 255 * tune;
				trap.kiraBlockX[0][0] = displayX + 800 * tune;
			} else if(monyako.color == Monyako.SET_GREEN || stone.leftStock == Stone.STOCK_GREEN && trap.kiraBlock[0][0] == false && trap.kiraBlock[2][0] == false ){
				trap.kiraBlock[1][0] = true;
				trap.kiraBlockY[1][0] = 255 * tune;
				trap.kiraBlockX[1][0] = displayX + 800 * tune;
			} else if(monyako.color == Monyako.SET_BLUE || stone.leftStock == Stone.STOCK_BLUE && trap.kiraBlock[1][0] == false && trap.kiraBlock[0][0] == false ){
				trap.kiraBlock[2][0] = true;
				trap.kiraBlockY[2][0] = 255 * tune;
				trap.kiraBlockX[2][0] = displayX + 800 * tune;
			}
			
			trap.normalBlockY[5] = 255 * tune;
			trap.normalBlockY[7] = 485 * tune;
			trap.normalBlockY[11] = 255 * tune;
			trap.normalBlock[1] = true;
			trap.normalBlock[5] = true;
			trap.normalBlock[7] = true;
			if(score.endlessMode)trap.normalBlock[11] = true;
		break;
		case 2:
			trap.normalBlockY[1] = 255 * tune;
			trap.normalBlockY[5] = 255 * tune;
			trap.normalBlockY[3] = 25 * tune;
			trap.normalBlockY[11] = 485 * tune;
			trap.normalBlockX[5] = displayX + 350 * tune;
			trap.normalBlockX[3] = displayX + 720 * tune;
			trap.normalBlockX[11] = displayX + 720 * tune;
			
			trap.normalBlock[1] = true;
			trap.normalBlock[5] = true;
			trap.normalBlock[3] = true;
			if(score.endlessMode)trap.normalBlock[11] = true;
		break;
		}

	}else if( 17000 * stage.cave < stage.startPoint && stage.startPoint < 17100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlockY[8] = 25 * tune;
			trap.normalBlockY[2] = 255 * tune;
			trap.normalBlockX[9] = displayX + 500 * tune;
			trap.kiraBlockX[0][0] =  displayX + 500 * tune;
			
			trap.normalBlockY[9] = 485 * tune;
			trap.kiraBlockY[0][0] = 255 * tune;
			trap.normalBlock[8] = true;
			trap.normalBlock[2] = true;
			trap.kiraBlock[0][0] = true;
			if(score.endlessMode)trap.normalBlock[9] = true;
			

		break;
		
		case 1:
			trap.normalBlockY[3] = 25 * tune;
			trap.normalBlockY[4] = 485 * tune;
			trap.normalBlockX[6] = displayX + 500 * tune;
			trap.normalBlockX[0] = displayX + 500 * tune;
			
			trap.normalBlockY[6] = 25 * tune;
			trap.normalBlockY[0] = 255 * tune;
			trap.normalBlock[3] = true;
			trap.normalBlock[4] = true;
			trap.normalBlock[6] = true;
			trap.normalBlock[0] = true;
		break;
		case 2:
			if(monyako.color == Monyako.SET_RED || stone.leftStock == Stone.STOCK_RED ){
				
				trap.kiraBlock[0][0] = true;
				trap.kiraBlockY[0][0] = 255 * tune;
				trap.kiraBlockX[0][0] = displayX + 220 * tune;
				
			} else if(monyako.color == Monyako.SET_GREEN || stone.leftStock == Stone.STOCK_GREEN ){
				
				trap.kiraBlock[1][0] = true;
				trap.kiraBlockY[1][0] = 255 * tune;
				trap.kiraBlockX[1][0] = displayX + 220 * tune;
				
			} else if(monyako.color == Monyako.SET_RED || stone.leftStock == Stone.STOCK_RED ){
				
				trap.kiraBlock[2][0] = true;
				trap.kiraBlockY[2][0] = 255 * tune;
				trap.kiraBlockX[2][0] = displayX + 220 * tune;
				
			}
			trap.normalBlockY[0] = 25 * tune;
			trap.normalBlockY[4] = 255 * tune;
			trap.normalBlockY[10] = 255 * tune;
			trap.normalBlockY[6] = 485 * tune;
			trap.normalBlockX[10] = displayX + 600 * tune;
			trap.normalBlockX[6] = displayX + 600 * tune;
			
			trap.normalBlock[0] = true;
			trap.normalBlock[4] = true;
			if(score.endlessMode)trap.normalBlock[10] = true;
			trap.normalBlock[6] = true;
		break;
		}

		
		//高速ポ１
		}
	}
	public void	createTrap3(){
	if( 18000 * stage.cave < stage.startPoint && stage.startPoint < 18100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlockY[1] = 25 * tune;
			trap.normalBlockY[3] = 485 * tune;
			trap.normalBlockX[11] = displayX + 600 * tune;
			
			trap.normalBlockY[11] = 255 * tune;
			trap.normalBlock[1] = true;
			trap.normalBlock[3] = true;
			trap.normalBlock[11] = true;
			
		break;
		
		case 1:
			if(trap.normalBlock[2] == false){	
				switch(trap.rndY[0]){
						case 0:
							trap.normalBlockY[2] = 25 * tune;
						break;
						case 1:
							trap.normalBlockY[2] = 255 * tune;
						break;
						case 2:
							trap.normalBlockY[2] = 485 * tune;
						break;
				}
					
					
			}	
			if(trap.normalBlockY[2] == 25 * tune ){
				trap.normalBlockY[8] = 255 * tune;
			} else if(trap.normalBlockY[2] == 255 * tune ){
				trap.normalBlockY[8] = 485 * tune;
			} else if(trap.normalBlockY[2] == 485 * tune ){
				trap.normalBlockY[8] = 25 * tune;
			}
			
			trap.normalBlock[2] = true;
			trap.normalBlock[8] = true;
		break;
		case 2:
			trap.normalBlock[7] = true;
			trap.normalBlockY[7] = 25 * tune;
		break;
		}
		
		
	}else if( 19000 * stage.cave < stage.startPoint && stage.startPoint < 19100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
				trap.normalBlockX[4] = displayX + 500 * tune;
			
			trap.normalBlockY[4] = 25 * tune;
			trap.kiraBlock[1][0] = true;
			trap.normalBlock[4] = true;
			trap.kiraBlockY[1][0] = 25 * tune;
		break;
		
		case 1:
			trap.normalBlockY[7]  = 25 * tune;
			trap.normalBlockY[1]  = 255 * tune;
			trap.normalBlockX[11]  = displayX + 400 * tune;
			trap.normalBlockX[10] = displayX + 400 * tune;
			
			trap.normalBlockY[11]  = 25 * tune;
			trap.normalBlockY[10] = 485 * tune;
			trap.normalBlock[7]  = true;
			trap.normalBlock[1]  = true;
			trap.normalBlock[11] = true;
			if(score.endlessMode)trap.normalBlock[10] = true;
			
		break;
		case 2:
			trap.normalBlockY[2]  = 255 * tune;
			trap.normalBlockY[3]  = 485 * tune;
			trap.normalBlockY[4]  = 25 * tune;
			trap.normalBlockY[5]  = 255 * tune;
			trap.normalBlockX[4]  =  displayX + 500 * tune;
			trap.normalBlockX[5]  =  displayX + 500 * tune;
			
			trap.normalBlock[2] = true;
			trap.normalBlock[3] = true;
			trap.normalBlock[4] = true;
			if(score.endlessMode)trap.normalBlock[5] = true;
		break;
		}
		
	}else if( 20000 * stage.cave < stage.startPoint && stage.startPoint < 20100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlockY[3] = 255 * tune;
				trap.normalBlockX[0] = displayX + 600 * tune;
				trap.normalBlockX[5] = displayX + 600 * tune;
			
			trap.normalBlockY[5] = 255 * tune;
			trap.normalBlockY[0] = 485 * tune;
			trap.normalBlock[3] = true;
			trap.normalBlock[5] = true;
			trap.normalBlock[0] = true;
			
		break;
		
		case 1:
			trap.normalBlockY[3] = 255 * tune;
			trap.normalBlockX[5] =  displayX + 500 * tune;
			trap.normalBlockX[9] =  displayX + 500 * tune;
			
			trap.normalBlockY[5] = 25 * tune;
			trap.normalBlockY[9] = 255 * tune;
			
			trap.normalBlock[3] = true;
			trap.normalBlock[5] = true;
			trap.normalBlock[9] = true;
		break;
		case 2:
			trap.normalBlockY[0]  = 485 * tune;
			trap.normalBlockY[1]  = 255 * tune;
			trap.normalBlockY[8]  = 485 * tune;
			trap.normalBlockY[9]  = 25 * tune;
			trap.normalBlockY[1]  = displayX + 450 * tune;
			trap.normalBlockY[8]  = displayX + 450 * tune;
			trap.normalBlockY[9]  = displayX + 800 * tune;
			
			trap.normalBlock[0] = true;
			trap.normalBlock[1] = true;
			if(score.endlessMode)trap.normalBlock[8] = true;
			trap.normalBlock[9] = true;
			
		break;
		}
		
		
	}else if( 21000* stage.cave  < stage.startPoint && stage.startPoint < 21100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlockY[10] = 485 * tune;
			trap.kiraBlockY[2][0] = 485 * tune;
			trap.normalBlockX[2] = displayX + 400 * tune; 
			trap.kiraBlockX[2][0] = displayX + 500 * tune;
			
			trap.normalBlockY[2] = 255 * tune;
			trap.normalBlock[10] = true;
			trap.kiraBlock[2][0] = true;
			trap.normalBlock[2] = true;
			
		break;
		
		case 1:
			trap.normalBlockY[4] = 255 * tune;
			trap.normalBlockY[6] = 485 * tune;
			trap.normalBlockX[0] = displayX + 600 * tune;
			
			trap.normalBlockY[0] = 255 * tune;
			trap.normalBlock[4] = true;
			trap.normalBlock[6] = true;
			trap.normalBlock[0] = true;
		break;
		case 2:
			if( trap.kiraBlock[0][0] == false && trap.kiraBlock[1][0] == false && trap.kiraBlock[2][0] == false && stone.leftStock == Stone.STOCK_RED  ){
				trap.kiraBlockY[0][0] = 25 * tune;
				trap.kiraBlockX[0][0] = displayX + 200 * tune;
				trap.kiraBlock[0][0] = true;
				trap.kiraBlockY[0][1] = 485 * tune;
				trap.kiraBlockX[0][1] = displayX + 310 * tune;
				trap.kiraBlock[0][1] = true;
		
			} else if( trap.kiraBlock[0][0] == false && trap.kiraBlock[1][0] == false && trap.kiraBlock[2][0] == false && stone.leftStock == Stone.STOCK_GREEN  ){
				trap.kiraBlockY[1][0] = 25 * tune;
				trap.kiraBlockX[1][0] = displayX + 310 * tune;
				trap.kiraBlock[1][0] = true;
				trap.kiraBlockY[1][1] = 485 * tune;
				trap.kiraBlockX[1][1] = displayX + 200 * tune;
				trap.kiraBlock[1][1] = true;
		
			} else if( trap.kiraBlock[0][0] == false && trap.kiraBlock[1][0] == false && trap.kiraBlock[2][0] == false && stone.leftStock == Stone.STOCK_BLUE  ){
				trap.kiraBlockY[2][0] = 25 * tune;
				trap.kiraBlockX[2][0] = displayX + 400 * tune;
				trap.kiraBlock[2][0] = true;
				trap.kiraBlockY[2][1] = 485 * tune;
				trap.kiraBlockX[2][1] = displayX + 310 * tune;
				trap.kiraBlock[2][1] = true;
		
			}
			trap.normalBlock[10] = true;
			
			trap.normalBlockY[10]  = 255 * tune;
		break;
		}
	
	}else if( 22000* stage.cave  < stage.startPoint && stage.startPoint < 22100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlockY[4] = 25 * tune;
			trap.normalBlockX[6] = displayX + 600 * tune;
			trap.normalBlockX[7] = displayX + 600 * tune;
			
			trap.normalBlockY[6] = 485 * tune;
			trap.normalBlockY[7] = 255 * tune;
			
			trap.normalBlock[4] = true;
			trap.normalBlock[6] = true;
			trap.normalBlock[7] = true;
			
		break;
		
		case 1:
			trap.normalBlockY[1] = 25 * tune;
			trap.normalBlockY[7] = 485 * tune;
			trap.normalBlockX[10] = displayX + 600 * tune;
			trap.normalBlockX[2] = displayX + 600 * tune;
			
			trap.normalBlockY[10] = 25 * tune;
			trap.normalBlockY[2] = 255 * tune;
			trap.normalBlock[1]  = true;
			trap.normalBlock[7]  = true;
			trap.normalBlock[10] = true;
			if(score.endlessMode)trap.normalBlock[2]  = true;
		break;
		case 2:
			trap.normalBlockY[4] = 25 * tune;
			trap.normalBlockY[6] = 25 * tune;
			trap.normalBlockY[3] = 485 * tune;
			trap.normalBlockY[5] = 255 * tune;
			trap.normalBlockX[6] = displayX + 450 * tune;
			trap.normalBlockX[3] = displayX + 450 * tune;
			trap.normalBlockX[5] = displayX + 800 * tune;
			
			trap.normalBlock[4] = true;
			trap.normalBlock[6] = true;
			if(score.endlessMode)trap.normalBlock[3] = true;
			trap.normalBlock[5] = true;
		break;
		}
		
		
	}else if( 23000* stage.cave  < stage.startPoint && stage.startPoint < 23100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlock[1] = true;
			trap.normalBlock[0] = true;
			trap.normalBlockY[1] = 25 * tune;
			trap.normalBlockY[0] = 485 * tune;
		break;
		
		case 1:
			trap.normalBlockY[3] = 255 * tune;
			trap.normalBlockY[5] = 485 * tune;
			trap.normalBlockX[8] = displayX + 500 * tune;
			trap.normalBlockX[9] = displayX + 500 * tune;
			
			trap.normalBlockY[8] = 25 * tune;
			trap.normalBlockY[9] = 485 * tune;
			trap.normalBlock[3] = true;
			trap.normalBlock[5] = true;
			if(score.endlessMode)trap.normalBlock[8] = true;
			trap.normalBlock[9] = true;
		break;
		case 2:
			trap.normalBlockY[2] = 25 * tune;
			trap.normalBlockY[7] = 485 * tune;
			trap.normalBlockY[11] = 25 * tune;
			trap.normalBlockY[0] = 255 * tune;
			trap.normalBlockX[11] = displayX + 600 * tune;
			trap.normalBlockX[0] = displayX + 600 * tune;
			
			
			trap.normalBlock[2]  = true;
			trap.normalBlock[7]  = true;
			trap.normalBlock[11] = true;
			if(score.endlessMode)trap.normalBlock[0]  = true;
		break;
		}

		
	}else if( 24000 * stage.cave < stage.startPoint && stage.startPoint < 24100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.kiraBlockY[0][0] = 25 * tune;
			trap.normalBlockY[2] = 485 * tune;
			trap.normalBlockX[3] = displayX + 600 * tune;
			trap.kiraBlockX[2][0] = displayX + 600 * tune;
			
			trap.normalBlockY[3] = 25 * tune;
			trap.kiraBlockY[2][0] = 255 * tune;
			trap.kiraBlock[0][0] = true;
			trap.normalBlock[2] = true;
			if(score.endlessMode)trap.normalBlock[3] = true;
			trap.kiraBlock[2][0] = true;
		break;
		
		case 1:
			trap.normalBlockY[11] = 255 * tune;
			trap.normalBlockX[0] = displayX + 500 * tune;
			
			trap.normalBlockY[0] = 25 * tune;
			trap.normalBlock[11] = true;
			trap.normalBlock[0]  = true;
		break;
		case 2:
			trap.kiraBlock[0][0] = true;
			trap.kiraBlock[1][0] = true;
			trap.kiraBlock[2][0] = true;
			trap.kiraBlockY[0][0] = 255 * tune;
			trap.kiraBlockY[1][0] = 25 * tune;
			trap.kiraBlockY[2][0] = 255 * tune;
			trap.kiraBlockX[1][0] = displayX + 310 * tune;
			trap.kiraBlockX[2][0] = displayX + 310 * tune;
			if(score.endlessMode)trap.normalBlock[8] = true;
			trap.normalBlockY[8] = 485 * tune;
			
		break;
		}

	}else if( 25000 * stage.cave < stage.startPoint && stage.startPoint < 25100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
				trap.normalBlockX[7] = displayX + 500 * tune;
				trap.normalBlockX[6] = displayX + 400 * tune;
			
			trap.normalBlockY[6] = 255 * tune;
			trap.normalBlockY[7] = 25 * tune;
			trap.normalBlockY[4] = 485 * tune;
			trap.normalBlock[6] = true;
			trap.normalBlock[7] = true;
			trap.normalBlock[4] = true;
			
		break;
		
		case 1:
			trap.normalBlock[1] = true;
			trap.normalBlock[2] = true;
			
			trap.normalBlockY[1] = 25 * tune;
			trap.normalBlockY[2] = 485 * tune;
			
		break;
		case 2:
			trap.normalBlockY[3] = 25 * tune;
			trap.normalBlockY[4] = 255 * tune;
			trap.normalBlockY[5] = 255 * tune;
			trap.normalBlockY[6] = 485 * tune;
			trap.normalBlockX[5] = displayX + 600 * tune;
			trap.normalBlockX[6] = displayX + 600 * tune;
			
			trap.normalBlock[3] = true;
			trap.normalBlock[4] = true;
			if(score.endlessMode)trap.normalBlock[5] = true;
			trap.normalBlock[6] = true;
		break;
		}

	}else if( 26000 * stage.cave < stage.startPoint && stage.startPoint < 26100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlockY[11] = 25 * tune;
			trap.normalBlockY[10] = displayX + 400 * tune;
			trap.normalBlockX[9] = displayX + 500 * tune;
			
			trap.normalBlockY[10] = 25 * tune;
			trap.normalBlockY[9] = 255 * tune;
			trap.normalBlockY[8] = 485 * tune;
			trap.normalBlock[11] = true;
			trap.normalBlock[10] = true;
			trap.normalBlock[9]  = true;
			trap.normalBlock[8]  = true;
			
		break;
		
		case 1:
			trap.normalBlockY[3] = 255 * tune;
			trap.normalBlockX[8] = displayX + 650 * tune;
			trap.normalBlockX[4] = displayX + 310 * tune;
			trap.normalBlockX[7] = displayX + 310 * tune;
			
			trap.normalBlockY[4] = 25 * tune;
			trap.normalBlockY[7] = 255 * tune;
			trap.normalBlockY[8] = 485 * tune;
			trap.normalBlock[3] = true;
			trap.normalBlock[4] = true;
			if(score.endlessMode)trap.normalBlock[7] = true;
			trap.normalBlock[8] = true;
			
		break;
		case 2:
			trap.normalBlockY[1] = 255 * tune;
			trap.normalBlockY[11] = 25 * tune;
			trap.normalBlockY[10] = 25 * tune;
			trap.normalBlockY[9] = 255 * tune;
			if( trap.kiraBlock[0][1] == false && trap.kiraBlock[1][1] == false && trap.kiraBlock[2][1] == false && stone.leftStock == Stone.STOCK_RED  ){
				
				trap.kiraBlockY[0][1] = 485 * tune;
				trap.kiraBlockX[0][1] = displayX + 350 * tune;
				trap.kiraBlock[0][1] = true;
		
			} else if( trap.kiraBlock[0][1] == false && trap.kiraBlock[1][1] == false && trap.kiraBlock[2][1] == false && stone.leftStock == Stone.STOCK_GREEN  ){
			
				trap.kiraBlockY[1][1] = 485 * tune;
				trap.kiraBlockX[1][1] = displayX + 350 * tune;
				trap.kiraBlock[1][1] = true;
		
			} else if( trap.kiraBlock[0][1] == false && trap.kiraBlock[1][1] == false && trap.kiraBlock[2][1] == false && stone.leftStock == Stone.STOCK_BLUE  ){
				
				trap.kiraBlockY[2][1] = 485 * tune;
				trap.kiraBlockX[2][1] = displayX + 350 * tune;
				trap.kiraBlock[2][1] = true;
		
			}
			trap.normalBlockX[11] = displayX + 500 * tune;
			trap.normalBlockX[10] = displayX + 500 * tune;
			trap.normalBlockX[9] = displayX + 860 * tune;
			
			trap.normalBlock[1]  = true;
			if(score.endlessMode)trap.normalBlock[11] = true;
			trap.normalBlock[10] = true;
			trap.normalBlock[9]  = true;
		break;
		}
	
	}else if( 27000 * stage.cave < stage.startPoint && stage.startPoint < 27100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlock[5] = true;
			trap.normalBlockY[5] = 485 * tune;
		break;
		
		case 1:
			trap.normalBlockY[5] = 25 * tune;
			trap.normalBlockX[6] = displayX + 600 * tune;
			trap.normalBlockX[9] = displayX + 600 * tune;
			
			trap.normalBlockY[6] = 255 * tune;
			trap.normalBlockY[9] = 485 * tune;
			trap.normalBlock[5] = true;
			trap.normalBlock[6] = true;
			trap.normalBlock[9] = true;
		break;
		case 2:
			trap.normalBlockY[2] = 255 * tune;
			trap.normalBlockY[7] = 25 * tune;
			trap.normalBlockY[8] = 25 * tune;
			trap.normalBlockX[7] = displayX + 500 * tune;
			trap.normalBlockX[8] = displayX + 500 * tune;
			
			trap.normalBlock[2] = true;
			trap.normalBlock[7] = true;
			trap.normalBlock[8] = true;
		break;
		}
		
	}else if( 28000 * stage.cave < stage.startPoint && stage.startPoint < 28100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlockY[6] = 485 * tune;
			trap.normalBlockX[7] = displayX + 310 * tune;
			trap.normalBlockX[0] = displayX + 650 * tune;
			trap.normalBlockX[1] = displayX + 650 * tune;
			
			trap.normalBlockY[7] = 255 * tune;
			trap.normalBlockY[0] = 25 * tune;
			trap.normalBlockY[1] = 485 * tune;
			trap.normalBlock[6] = true;
			if(score.endlessMode)trap.normalBlock[7] = true;
			trap.normalBlock[0] = true;
			trap.normalBlock[1] = true;
			
		break;
		
		case 1:
			trap.normalBlockY[10] = 25 * tune;
			trap.normalBlockY[1] = 255 * tune;
			
			trap.normalBlockX[11] = displayX + 600 * tune;
			trap.normalBlockX[0] = displayX + 600 * tune;
			
			trap.normalBlockY[11] = 25 * tune;
			trap.normalBlockY[0] = 485 * tune;
			trap.normalBlock[10] = true;
			trap.normalBlock[1]  = true;
			if(score.endlessMode)trap.normalBlock[11] = true;
			trap.normalBlock[0]  = true;
			
		break;
		case 2:
			trap.normalBlockY[3] = 25 * tune;
			trap.normalBlockY[4] = 255 * tune;
			trap.normalBlockY[5] = 25 * tune;
			trap.normalBlockY[6] = 485 * tune;
		
			trap.normalBlockX[4] = displayX + 400 * tune;
			trap.normalBlockX[5] = displayX + 720 * tune;
			trap.normalBlockX[6] = displayX + 720 * tune;
			
			trap.normalBlock[3] = true;
			trap.normalBlock[4] = true;
			if(score.endlessMode)trap.normalBlock[5] = true;
			trap.normalBlock[6] = true;
			
			
		break;
		}

	}else if( 29000 * stage.cave < stage.startPoint && stage.startPoint < 29100* stage.cave ){
		switch(stage.setTrap){
		
		case 0:
			trap.normalBlockY[2] = 25 * tune;
			trap.normalBlockX[3] = displayX + 310 * tune;
			trap.normalBlockX[4] = displayX + 650 * tune;
			trap.normalBlockX[9] = displayX + 310 * tune;
			
			trap.normalBlockY[3] = 485 * tune;
			trap.normalBlockY[4] = 255 * tune;
			trap.normalBlockY[9] = 25 * tune;
			trap.normalBlock[2] = true;
			trap.normalBlock[3] = true;
			trap.normalBlock[4] = true;
			if(score.endlessMode)trap.normalBlock[9] = true;
			
		break;
		
		case 1:
			trap.normalBlockY[2] = 255 * tune;
			trap.normalBlockY[4] = 485 * tune;
			trap.normalBlockX[8] = displayX + 500 * tune;
			trap.normalBlockX[3] = displayX + 500 * tune;
			
			trap.normalBlockY[8] = 25 * tune;
			trap.normalBlockY[3] = 255 * tune;
			trap.normalBlock[2] = true;
			trap.normalBlock[4] = true;
			if(score.endlessMode)trap.normalBlock[8] = true;
			trap.normalBlock[3] = true;
		break;
		case 2:
				trap.normalBlockX[10] = displayX + 450 * tune;
				trap.normalBlockX[11] = displayX + 450 * tune;
			
			trap.normalBlockY[0] = 25 * tune;
			trap.normalBlockY[1] = 255 * tune;
			trap.normalBlockY[10] = 255 * tune;
			trap.normalBlockY[11] = 485 * tune;
			trap.normalBlock[0]  = true;
			trap.normalBlock[1]  = true;
			if(score.endlessMode)trap.normalBlock[10] = true;
			trap.normalBlock[11] = true;
		break;
		}

	}

		
		
		
		
	}
	
	
	//もにゃこ死亡
	public void death(){
		if( monyako.state == Monyako.MONYAKO_WALK){
			if(seDamageLoad == false){
				se.play(seDamage, 1.0F, 1.0F, 0, 0, 1.0F); 
				seDamageLoad = true;
			}
			if(score.endlessMode == false){
				playState = PLAY_OVER;
			} else if(score.endlessMode == true){
				playState = PLAY_FINISH;
			}
		}
	}
	
	
	
	//エンドレス罠生成
	public void createEndlessTrap1(){
		

		
		if( trap.kiraBlock[0][2] == false && stone.leftStock == Stone.STOCK_RED  ){
			trap.kiraBlockX[0][2] = displayX + 200 * tune;
			trap.kiraBlock[0][2] = true;
		} else if( trap.kiraBlock[0][2] == false &&  stone.rightStock == Stone.STOCK_RED && score.endlessMode == true ){
			trap.kiraBlockX[0][2] = displayX + 500 * tune;
			trap.kiraBlock[0][2] = true;
		} 
		
			if( trap.kiraBlock[1][2] == false && stone.leftStock == Stone.STOCK_GREEN  ){
				trap.kiraBlockX[1][2] = displayX + 200 * tune;
				trap.kiraBlock[1][2] = true;
			} else if( trap.kiraBlock[1][2] == false && stone.rightStock == Stone.STOCK_GREEN  && score.endlessMode == true ){
				trap.kiraBlockX[1][2] = displayX + 500 * tune;
				trap.kiraBlock[1][2] = true;
			} 
		
			if( trap.kiraBlock[2][2] == false && stone.leftStock == Stone.STOCK_BLUE  ){
				trap.kiraBlockX[2][2] = displayX + 200 * tune;
				trap.kiraBlock[2][2] = true;
			} else if( trap.kiraBlock[2][2] == false && stone.rightStock == Stone.STOCK_BLUE && score.endlessMode == true ){
				trap.kiraBlockX[2][2] = displayX + 500 * tune;
				trap.kiraBlock[2][2] = true;
			}    
		
		
	}
	
	
	public void createEndlessTrap2(){
		trap.endlessRnd = trap.rnd.nextInt(3);
		switch(trap.endlessRnd){
		
		case 0:
			if(trap.normalBlock[0] == false && trap.normalBlock[2] == false && trap.normalBlock[10] == false &&
			   trap.normalBlock[1] == false && trap.normalBlock[6] == false && trap.normalBlock[11] == false){
				trap.normalBlock[0] = true;
				trap.normalBlock[1] = true;
				
				
			}
			
			break;
		case 1:
			if(trap.normalBlock[0] == false && trap.normalBlock[2] == false && trap.normalBlock[10] == false &&
					   trap.normalBlock[1] == false && trap.normalBlock[6] == false && trap.normalBlock[11] == false){
				trap.normalBlock[2] = true;
				trap.normalBlock[6] = true;
				
			}
			
			break;
		case 2:
			if(trap.normalBlock[0] == false && trap.normalBlock[2] == false && trap.normalBlock[10] == false &&
					   trap.normalBlock[1] == false && trap.normalBlock[6] == false && trap.normalBlock[11] == false){
				trap.normalBlock[10] = true;
				trap.normalBlock[11] = true;
				
				
			}
			
			break;
	
		}
		
	}
	
	
	//ストーン生成(低速時)
	public void createStone1(){
		
		

			//ポイント１
			if( 500 * stage.cave <= stage.startPoint && stage.startPoint <= 600* stage.cave ){
				switch(stage.setStone){
					case 0:
						if( score.endlessMode == true && 0 < LAP){
							stone.kind[3][2] = true ;
						}
						stone.kind[0][0] = true;
						break;
					case 1:
						if( score.endlessMode == true && 0 < LAP){
							stone.kind[3][2] = true ;
						}
						stone.kind[0][0] = true;
						stone.kind[0][1] = true;
						break;
					case 2:
						stone.kind[0][0] = true;
					
						break;
					case 3:
					
						stone.kind[0][0] = true;
						if( stone.setKiraRnd == 0 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
							stone.kind[3][0] = true ;
						}else if(  stone.setKiraRnd == 1 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
							stone.kind[4][0] = true ;
						} else if( stone.setKiraRnd == 2 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
							stone.kind[5][0] = true ;
						}
						break;
				}
				
			} else if( 1500* stage.cave  <= stage.startPoint && stage.startPoint <= 1600* stage.cave ){
				switch(stage.setStone){
				case 0:
					stone.kind[0][1] = true;
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
					
				}
				 
			}else if( 2500 * stage.cave <= stage.startPoint && stage.startPoint <= 2600* stage.cave ){
				switch(stage.setStone){
				case 0:
					break;
				case 1:
					stone.kind[0][2] = true;
					stone.kind[1][0] = true;
					break;
				case 2:
					break;
				case 3:
					break;
					
				}
				
				
			}else if( 3500 * stage.cave <= stage.startPoint && stage.startPoint <= 3600* stage.cave ){
				switch(stage.setStone){
				case 0:
					break;
				case 1:
					break;
				case 2:
					stone.kind[2][0] = true;
					break;
				case 3:
					stone.kind[1][0] = true;
					break;
					
				}
				
				
			}else if( 4500 * stage.cave <= stage.startPoint && stage.startPoint <= 4600* stage.cave ){
				switch(stage.setStone){
				case 0:
					stone.kind[0][0] = true;
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
					
				}
				
			}else if( 5500 * stage.cave <= stage.startPoint && stage.startPoint <= 5600* stage.cave ){
				
				switch(stage.setStone){
				case 0:
					stone.kind[1][0] = true;
					break;
				case 1:
					if( stone.setKiraRnd == 0 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[3][0] = true ;
					}else if(  stone.setKiraRnd == 1 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[4][0] = true ;
					} else if( stone.setKiraRnd == 2 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[5][0] = true ;
					}
					break;
				case 2:
					stone.kind[0][1] = true;
					if( stone.setKiraRnd == 0 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[3][0] = true ;
					}else if(  stone.setKiraRnd == 1 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[4][0] = true ;
					} else if( stone.setKiraRnd == 2 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false){
						stone.kind[5][0] = true ;
					}
					break;
				case 3:
					stone.kind[0][1] = true;
					if( stone.setKiraRnd == 0 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false  ){
						stone.kind[3][0] = true ;
					}else if(  stone.setKiraRnd == 1 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[4][0] = true ;
					} else if( stone.setKiraRnd == 2 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[5][0] = true ;
					}
					break;
				}
				
				
			}
	}
	public void createStone2(){
		//中速
			if( 6500 * stage.cave <= stage.startPoint && stage.startPoint <= 6600* stage.cave ){
				switch(stage.setStone){
				case 0:
					if( score.endlessMode == true){
						stone.kind[4][2] = true ;
				}
					break;
				case 1:
					if( score.endlessMode == true){
						stone.kind[4][2] = true ;
				}
					break;
				case 2:
					stone.kind[1][0] = true;
					if( score.endlessMode == true){
						stone.kind[5][2] = true ;
				}
					break;
				case 3:
					stone.kind[1][0] = true;
					if( score.endlessMode == true){
						stone.kind[3][2] = true ;
				}
					break;
					
				}
			}else if( 7500* stage.cave  <= stage.startPoint && stage.startPoint <= 7600* stage.cave ){
			
				switch(stage.setStone){
				case 0:
					if( stone.setKiraRnd == 0 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[3][0] = true ;
					}else if(  stone.setKiraRnd == 1 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[4][0] = true ;
					} else if( stone.setKiraRnd == 2 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[5][0] = true ;
					}
					break;
				case 1:
					stone.kind[1][0] = true;
					break;
				case 2:
					break;
				case 3:
					break;
					
				}
				
				
			}else if( 8500* stage.cave  <= stage.startPoint && stage.startPoint <= 8600* stage.cave ){
				switch(stage.setStone){
				case 0:
					break;
				case 1:
					break;
				case 2:
					stone.kind[0][0] = true;
					break;
				case 3:
					stone.kind[0][0] = true;
					break;
					
				}
				
			}else if( 9500 * stage.cave <= stage.startPoint && stage.startPoint <= 9600* stage.cave ){
				
				switch(stage.setStone){
				case 0:
					stone.kind[1][0] = true;
					stone.kind[0][0] = true;
					break;
				case 1:
					stone.kind[0][0] = true ;
					if( stone.setKiraRnd == 0 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false  ){
						stone.kind[3][0] = true ;
					}else if(  stone.setKiraRnd == 1 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[4][0] = true ;
					} else if( stone.setKiraRnd == 2 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[5][0] = true ;
					}
					break;
				case 2:
					break;
				case 3:
					break;
					
				}
					
				
			}else if( 10500 * stage.cave <= stage.startPoint && stage.startPoint <= 10600* stage.cave ){
				switch(stage.setStone){
				case 0:
					stone.kind[0][1] = true;
					break;
				case 1:
					break;
				case 2:
					stone.kind[0][1] = true;
					if( stone.setKiraRnd == 0 && stone.kind[3][0] == false  && stone.kind[5][0] == false  ){
						stone.kind[3][0] = true ;
					}else if(  stone.setKiraRnd == 1 && stone.kind[3][0] == false && stone.kind[4][0] == false  ){
						stone.kind[4][0] = true ;
					} else if( stone.setKiraRnd == 2 && stone.kind[3][0] == false  && stone.kind[5][0] == false ){
						stone.kind[5][0] = true ;
					}
					break;
				case 3:
					stone.kind[0][1] = true;
					break;
					
				}
				
			}else if( 11500 * stage.cave <= stage.startPoint && stage.startPoint <= 11600* stage.cave ){
			
				switch(stage.setStone){
				case 0:
					stone.kind[0][2] = true;
					break;
				case 1:
					stone.kind[0][1] = true;
					stone.kind[0][2] = true;
				
					break;
				case 2:
					break;
				case 3:
					break;
					
				}
					
			}else if( 12500 * stage.cave <= stage.startPoint && stage.startPoint <= 12600* stage.cave ){
				switch(stage.setStone){
				case 0:
					
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					stone.kind[2][0] = true;
					break;
					
				}
			} else if( 13500* stage.cave  <= stage.startPoint && stage.startPoint <= 13600* stage.cave ){
				switch(stage.setStone){
				case 0:
					stone.kind[0][0] = true;
					stone.kind[2][0] = true;
					break;
				case 1:
					stone.kind[2][0] = true;
					stone.kind[1][0] = true;
				
					break;
				case 2:
					stone.kind[1][0] = true;
					
					break;
				case 3:
					stone.kind[1][0] = true;
					break;
					
				}
				
					
			}else if( 14500 * stage.cave <= stage.startPoint && stage.startPoint <= 14600* stage.cave ){
				switch(stage.setStone){
				case 0:
					stone.kind[1][0] = true;
					break;
				case 1:
					break;
				case 2:
					stone.kind[0][0] = true;
					break;
				case 3:
					stone.kind[0][0] = true;
					break;
					
				}
			}else if( 15500 * stage.cave <= stage.startPoint && stage.startPoint <= 15600* stage.cave ){
				switch(stage.setStone){
				case 0:
					stone.kind[0][2] = true;
					break;
				case 1:
					stone.kind[0][0] = true;
					if( stone.setKiraRnd == 0 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false  ){
						stone.kind[3][0] = true ;
					}else if(  stone.setKiraRnd == 1 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[4][0] = true;
					} else if( stone.setKiraRnd == 2 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[5][0] = true ;
					}
					break;
				case 2:
					stone.kind[0][1] = true;
					break;
				case 3:
					stone.kind[0][1] = true;
					break;
					
				}
				
					
				
			}else if( 16500 * stage.cave <= stage.startPoint && stage.startPoint <= 16600* stage.cave ){
				switch(stage.setStone){
				case 0:
					if( score.endlessMode == true){
						stone.kind[3][2] = true ;
				}
					stone.kind[0][1] = true;
					break;
				case 1:
					if( score.endlessMode == true){
						stone.kind[5][2] = true ;
				}
					break;
				case 2:
					if( score.endlessMode == true){
						stone.kind[4][2] = true ;
				}
					stone.kind[0][2] = true;
					stone.kind[0][3] = true;
					break;
				case 3:
					if( score.endlessMode == true){
						stone.kind[5][2] = true ;
				}
					stone.kind[0][2] = true;
					stone.kind[0][3] = true;
					break;
					
				}
			}else if( 17500 * stage.cave <= stage.startPoint && stage.startPoint <= 17600* stage.cave ){

				switch(stage.setStone){
				case 0:
					
					if( stone.setKiraRnd == 0 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[3][0] = false ;
					}else if(  stone.setKiraRnd == 1 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[4][0] = true ;
					} else if( stone.setKiraRnd == 2 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[5][0] = true ;
					}break;
				case 1:
					stone.kind[0][1] = true;
					stone.kind[0][2] = true;
					break;
				case 2:
					break;
				case 3:
					break;
					
				}
				
				//高速
			}
	}
	public void createStone3(){		
			if( 18500 * stage.cave <= stage.startPoint && stage.startPoint <= 18600* stage.cave ){
				switch(stage.setStone){
				case 0:
					/*if( stone.setKiraRnd == 0 && stone.kind[3][1] == false && stone.kind[4][1] == false && stone.kind[5][1] == false  ){
						stone.kind[3][1] = true ;
					}else if(  stone.setKiraRnd == 1 && stone.kind[3][1] == false && stone.kind[4][1] == false && stone.kind[5][1] == false ){
						stone.kind[4][1] = true ;
					} else if( stone.setKiraRnd == 2 && stone.kind[3][1] == false && stone.kind[4][1] == false && stone.kind[5][1] == false ){
						stone.kind[5][1] = true ;
					}
					*/
					break;
				case 1:
					stone.kind[0][3] = true;
					stone.kind[0][0] = true;
					if( stone.setKiraRnd == 0 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false  ){
						stone.kind[3][0] = true ;
					}else if(  stone.setKiraRnd == 1 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[4][0] = true ;
					} else if( stone.setKiraRnd == 2 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[5][0] = true ;
					}
					break;
				case 2:
					stone.kind[0][0] = true;
					if( stone.setKiraRnd == 0 && stone.kind[3][0] == false  && stone.kind[5][0] == false  ){
						stone.kind[3][0] = true ;
					}else if(  stone.setKiraRnd == 1 && stone.kind[3][0] == false && stone.kind[4][0] == false  ){
						stone.kind[4][0] = true ;
					} else if( stone.setKiraRnd == 2 && stone.kind[3][0] == false  && stone.kind[5][0] == false ){
						stone.kind[5][0] = true ;
					}
					break;
				case 3:
					/*if( stone.setKiraRnd == 0 && stone.kind[3][1] == false && stone.kind[4][1] == false && stone.kind[5][1] == false  ){
						stone.kind[3][1] = true ;
					}else if(  stone.setKiraRnd == 1 && stone.kind[3][1] == false && stone.kind[4][1] == false && stone.kind[5][1] == false ){
						stone.kind[4][1] = true ;
					} else if( stone.setKiraRnd == 2 && stone.kind[3][1] == false && stone.kind[4][1] == false && stone.kind[5][1] == false ){
						stone.kind[5][1] = true ;
					}
					*/
					stone.kind[0][0] = true;
					stone.kind[1][0] = true;
					break;
					
				}
					
				
			}else if( 19500 * stage.cave <= stage.startPoint && stage.startPoint <= 19600* stage.cave ){
				switch(stage.setStone){
				case 0:
					if( stone.setKiraRnd == 0 && stone.kind[3][2] == false && stone.kind[4][2] == false && stone.kind[5][2] == false  ){
						stone.kind[3][2] = true ;
					}else if(  stone.setKiraRnd == 1 && stone.kind[3][2] == false && stone.kind[4][2] == false && stone.kind[5][2] == false ){
						stone.kind[4][2] = true ;
					} else if( stone.setKiraRnd == 2 && stone.kind[3][2] == false && stone.kind[4][2] == false && stone.kind[5][2] == false ){
						stone.kind[5][2] = true ;
					}
					break;
				case 1:
					break;
				case 2:
					stone.kind[1][0] = true;
					stone.kind[1][1] = true;
					break;
				case 3:
					stone.kind[1][1] = true;
					break;
					
				}
			}else if( 20500* stage.cave  <= stage.startPoint && stage.startPoint <= 20600* stage.cave ){
				switch(stage.setStone){
				case 0:
					stone.kind[0][0] = true;
					break;
				case 1:
					stone.kind[1][0] = true;
					stone.kind[1][1] = true;
					break;
				case 2:
					stone.kind[2][0] = true;
					break;
				case 3:
					stone.kind[2][0] = true;
					if( stone.setKiraRnd == 0 && stone.kind[3][0] == false  && stone.kind[5][0] == false ){
						stone.kind[3][0] = true ;
					}else if(  stone.setKiraRnd == 1 && stone.kind[3][0] == false && stone.kind[4][0] == false ){
						stone.kind[4][0] = true ;
					} else if( stone.setKiraRnd == 2 && stone.kind[3][0] == false  && stone.kind[5][0] == false ){
						stone.kind[5][0] = true ;
					}
					break;
					
				}
				
					
				
			}else if( 21500 * stage.cave <= stage.startPoint && stage.startPoint <= 21600* stage.cave ){
				switch(stage.setStone){
				case 0:
					stone.kind[1][0] = true;
					break;
				case 1:
					break;
				case 2:
					stone.kind[0][0] = true;
					break;
				case 3:
					stone.kind[0][0] = true;
					break;
					
				}
			}else if( 22500 * stage.cave <= stage.startPoint && stage.startPoint <= 22600* stage.cave ){
				switch(stage.setStone){
				case 0:
					stone.kind[2][0] = true;
					break;
				case 1:
					stone.kind[2][0] = true;
					stone.kind[0][1] = true;
					break;
				case 2:
					stone.kind[0][1] = true;
					break;
				case 3:
					if( stone.setKiraRnd == 0 && stone.kind[3][2] == false && stone.kind[4][2] == false && stone.kind[5][2] == false  ){
						stone.kind[3][2] = true ;
					}else if(  stone.setKiraRnd == 1 && stone.kind[3][2] == false && stone.kind[4][2] == false && stone.kind[5][2] == false ){
						stone.kind[4][2] = true ;
					} else if( stone.setKiraRnd == 2 && stone.kind[3][2] == false && stone.kind[4][2] == false && stone.kind[5][2] == false ){
						stone.kind[5][2] = true ;
					}
					stone.kind[0][1] =true;
					break;
					
				}
				
					
				
			}else if( 23500 * stage.cave <= stage.startPoint && stage.startPoint <= 23600* stage.cave ){
				switch(stage.setStone){
				case 0:
					if( stone.setKiraRnd == 0 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false  ){
						stone.kind[3][0] = true ;
					}else if(  stone.setKiraRnd == 1 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[4][0] = true ;
					} else if( stone.setKiraRnd == 2 && stone.kind[3][0] == false && stone.kind[4][0] == false && stone.kind[5][0] == false ){
						stone.kind[5][0] = true ;
					}
					break;
				case 1:
					break;
				case 2:
					stone.kind[1][0] = true;
					break;
				case 3:
					break;
					
				}
			}else if( 24500* stage.cave  <= stage.startPoint && stage.startPoint <= 24600* stage.cave ){
				switch(stage.setStone){
				case 0:
					stone.kind[0][0] = true;
					
					break;
				case 1:
					stone.kind[0][0] = true;
					stone.kind[4][0] = true;
					break;
				case 2:
					stone.kind[0][2] = true;
					break;
				case 3:
					stone.kind[0][3] = true;
					stone.kind[0][2] = true;
					break;
					
				}
			
					
				
			}else if( 25000 * stage.cave <= stage.startPoint && stage.startPoint <= 25600* stage.cave ){
				switch(stage.setStone){
				case 0:
					stone.kind[0][1] = true;
					break;
				case 1:
					break;
				case 2:
					stone.kind[0][0] = true;
					stone.kind[0][1] = true;
					break;
				case 3:
					stone.kind[0][1] = true;
					stone.kind[1][0] = true;
					break;
					
				}
			}else if( 26500 * stage.cave <= stage.startPoint && stage.startPoint <= 26600* stage.cave ){
				switch(stage.setStone){
				case 0:
					stone.kind[0][2] = true;
					stone.kind[1][0] = true;
					break;
				case 1:
					stone.kind[0][2] = true;
					stone.kind[0][3] = true;
					break;
				case 2:
					break;
				case 3:
					break;
					
				}
			
					
				
			}else if( 27500 * stage.cave <= stage.startPoint && stage.startPoint <= 27600* stage.cave ){
				switch(stage.setStone){
				case 0:
					stone.kind[0][0] = true;
					stone.kind[0][2] = true;
					break;
				case 1:
					break;
				case 2:
					stone.kind[0][2] = true;
					stone.kind[0][3] = true;
					break;
				case 3:
					stone.kind[0][2] = true;
					stone.kind[0][3] = true;
					break;
					
				}
				
			}else if( 28500 * stage.cave <= stage.startPoint && stage.startPoint <= 28600* stage.cave ){
				switch(stage.setStone){
				case 0:
					stone.kind[0][1] = true;
					stone.kind[0][3] = true;
					break;
				case 1:	
					stone.kind[0][0] = true;
					stone.kind[0][1] = true;
					break;
				case 2:
					stone.kind[0][0] = true;
					stone.kind[1][0] = true;
					break;
				case 3:
					stone.kind[0][0] = true;
					break;
					
				}
				
				
				
			}else if( 29500 * stage.cave <= stage.startPoint && stage.startPoint <= 29600* stage.cave ){
				
				switch(stage.setStone){
				case 0:
					stone.kind[1][0] = true;
					stone.kind[2][0] = true;
					stone.kind[0][2] = true;
					break;
				case 1:
					stone.kind[1][0] = true;
					stone.kind[2][0] = true;
					stone.kind[0][2] = true;
					break;
				case 2:
					if(score.endlessMode == true){
						stone.kind[3][0] = true;
					}
					break;
				case 3:
					if(score.endlessMode == true){
						stone.kind[5][0] = true;
					}
					break;
					
				}
				
			}
	
			
		
		
	}
	
	
	
	
	
	
	//チュートリアル関係■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	public void tutorialCreate(){
		
			switch(tutorialState){
				
			case FASE_STONE:
				
				stone.kind[0][0] = true;
			break;
			
			case FASE_LADDER:
				stone.kind[1][0] = true;
				
			break;
				
			case FASE_TRAP:
				trap.normalBlock[0] = true;
				
			break;
			
			case FASE_FREE:
				
				switch(faseState){
				
				case 17:
					trap.normalBlock[3] = true;
					trap.kiraBlock[0][0] = true;
					
				break;
				
				case 18:
					if(loadCheck == false){
					trap.normalBlock[3] = true;
					trap.kiraBlock[0][0] = true;
					trap.kiraBlock[1][0] = true;
					loadCheck = true;
					}
					
				break;
				}
				break;
			case FASE_KIRA:
				
				switch(faseState){
				
				case 22:
					stone.kind[3][0] = true;
					stone.y[3][0] = 275 * tune;
				break;
				
				case 28:
					trap.normalBlock[3] = true;
					trap.normalBlockY[3] = 25 * tune;
					trap.kiraBlock[0][0] = true;
					trap.kiraBlockY[0][0] = 255 * tune;
					trap.normalBlock[5] = true;
					trap.normalBlockY[5] = 485 * tune;
					
				break;
				}
			break;
				
			}
			
		
		
	}
	
	//リザルト関係■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	
	//ランキング表示
	public void drawRanking(Canvas canvas){
		switch(loadCount){
		
		case NOT:
			
		break;

		case 1:
			
				canvas.drawBitmap( third , 450 * tune , 495 * tune, null);
				canvas.drawBitmap( homeKira, 970 * tune , 495 * tune, null);
			
				drawThird(canvas);
			
		break;
		
		case 2:
			canvas.drawBitmap( third , 450 * tune , 495 * tune, null);
			canvas.drawBitmap( homeKira, 970 * tune , 495 * tune, null);
			
			drawThird(canvas);
			
			canvas.drawBitmap( second , 450 * tune , 390 * tune, null);
			canvas.drawBitmap( homeKira, 970 * tune , 390 * tune, null);
			
			drawSecond(canvas);
			
		break;
		
		case 3:
			canvas.drawBitmap( third , 450 * tune , 495 * tune, null);
			canvas.drawBitmap( homeKira, 970 * tune , 495 * tune, null);
			
			drawThird(canvas);
			
			canvas.drawBitmap( second , 450 * tune , 390 * tune, null);
			canvas.drawBitmap( homeKira, 970 * tune , 390 * tune, null);
			
			drawSecond(canvas);
			
			canvas.drawBitmap( first , 450 * tune , 280 * tune, null);
			canvas.drawBitmap( homeKira, 970 * tune , 280 * tune, null);
			
			drawFirst(canvas);
			
		break;
		}
		
	}
	
	
	//ランキング1位画像表示
	public void drawFirst(Canvas canvas){
		
		if( scoreState == SCORE_NORMAL){
			canvas.drawBitmap( scoreNum[score.normalRanking[0][3]] , 900 * tune , 280 * tune, null);
			canvas.drawBitmap( scoreNum[score.normalRanking[0][2]] , 830 * tune , 280 * tune, null);
			canvas.drawBitmap( scoreNum[score.normalRanking[0][1]] , 760 * tune , 280 * tune, null);
			canvas.drawBitmap( scoreNum[score.normalRanking[0][0]] , 690 * tune , 280 * tune, null);
		}else if( scoreState == SCORE_ENDLESS){
			canvas.drawBitmap( scoreNum[score.endlessRanking[0][4]] , 900 * tune , 280 * tune, null);
			canvas.drawBitmap( scoreNum[score.endlessRanking[0][3]] , 830 * tune , 280 * tune, null);
			canvas.drawBitmap( scoreNum[score.endlessRanking[0][2]] , 760 * tune , 280 * tune, null);
			canvas.drawBitmap( scoreNum[score.endlessRanking[0][1]] , 690 * tune , 280 * tune, null);
			canvas.drawBitmap( scoreNum[score.endlessRanking[0][0]] , 620 * tune , 280 * tune, null);
			
		}
	}
	
	//ランキング2位画像表示
	public void drawSecond(Canvas canvas){
		
		if( scoreState == SCORE_NORMAL){
		canvas.drawBitmap( scoreNum[score.normalRanking[1][3]] , 900 * tune , 390 * tune, null);
		canvas.drawBitmap( scoreNum[score.normalRanking[1][2]] , 830 * tune , 390 * tune, null);
		canvas.drawBitmap( scoreNum[score.normalRanking[1][1]] , 760 * tune , 390 * tune, null);
		canvas.drawBitmap( scoreNum[score.normalRanking[1][0]] , 690 * tune , 390 * tune, null);
		}else if( scoreState == SCORE_ENDLESS){
			canvas.drawBitmap( scoreNum[score.endlessRanking[1][4]] , 900 * tune , 390 * tune, null);
			canvas.drawBitmap( scoreNum[score.endlessRanking[1][3]] , 830 * tune , 390 * tune, null);
			canvas.drawBitmap( scoreNum[score.endlessRanking[1][2]] , 760 * tune , 390 * tune, null);
			canvas.drawBitmap( scoreNum[score.endlessRanking[1][1]] , 690 * tune , 390 * tune, null);
			canvas.drawBitmap( scoreNum[score.endlessRanking[1][0]] , 620 * tune , 390 * tune, null);
			
		}
	}	
	
	//3位表示
	public void drawThird(Canvas canvas){
		
		if( scoreState == SCORE_NORMAL){
		canvas.drawBitmap( scoreNum[score.normalRanking[2][3]] , 900 * tune , 495 * tune, null);
		canvas.drawBitmap( scoreNum[score.normalRanking[2][2]] , 830 * tune , 495 * tune, null);
		canvas.drawBitmap( scoreNum[score.normalRanking[2][1]] , 760 * tune , 495 * tune, null);
		canvas.drawBitmap( scoreNum[score.normalRanking[2][0]] , 690 * tune , 495 * tune, null);
		}
		else if( scoreState == SCORE_ENDLESS){
			canvas.drawBitmap( scoreNum[score.endlessRanking[2][4]] , 900 * tune , 495 * tune, null);
			canvas.drawBitmap( scoreNum[score.endlessRanking[2][3]] , 830 * tune , 495 * tune, null);
			canvas.drawBitmap( scoreNum[score.endlessRanking[2][2]] , 760 * tune , 495 * tune, null);
			canvas.drawBitmap( scoreNum[score.endlessRanking[2][1]] , 690 * tune , 495 * tune, null);
			canvas.drawBitmap( scoreNum[score.endlessRanking[2][0]] , 620 * tune , 495 * tune, null);
			
		}
}	
	
	//エンドレス1位スコア保存
	public void endlessFirstSave( ){
		 // Write
		SharedPreferences endlessFirst = 
               getSharedPreferences( "endlessFirstPoint", Context.MODE_PRIVATE );

       // プリファレンスに書き込むためのEditorオブジェクト取得 //
       Editor editor = endlessFirst.edit();

       // "" というキーで名前を登録
       editor.putInt( "endlessFirstPoint", score.endelessRankingPoint[0] );
       
       // 書き込みの確定（実際にファイルに書き込む）
       editor.commit();	
	}		

	
	//1位スコア保存
	public void firstSave( ){
		 // Write
		SharedPreferences first = 
                getSharedPreferences( "firstPoint", Context.MODE_PRIVATE );

        // プリファレンスに書き込むためのEditorオブジェクト取得 //
        Editor editor = first.edit();
 
        // "" というキーで名前を登録
        editor.putInt( "firstPoint", score.normalRankingPoint[0] );
        
        // 書き込みの確定（実際にファイルに書き込む）
        editor.commit();	
	}

	//2位スコア保存
	public void secondSave( ){
		 // Write
		SharedPreferences second = 
               getSharedPreferences( "secondPoint", Context.MODE_PRIVATE );

       // プリファレンスに書き込むためのEditorオブジェクト取得 //
       Editor editor = second.edit();

       // "" というキーで名前を登録
       editor.putInt( "secondPoint", score.normalRankingPoint[1] );
       
       // 書き込みの確定（実際にファイルに書き込む）
       editor.commit();	
	}
	//エンドレス2位スコア保存
			public void endlessSecondSave(){
				 // Write
				SharedPreferences endlessSecond = 
		               getSharedPreferences( "endlessSecondPoint", Context.MODE_PRIVATE );

		       // プリファレンスに書き込むためのEditorオブジェクト取得 //
		       Editor editor = endlessSecond.edit();

		       // "" というキーで名前を登録
		       editor.putInt( "endlessSecondPoint", score.endelessRankingPoint[1] );
		       
		       // 書き込みの確定（実際にファイルに書き込む）
		       editor.commit();	
			}		
	
	//3位スコア保存
	public void thirdSave( ){
		 // Write
		SharedPreferences third = 
               getSharedPreferences( "thirdPoint", Context.MODE_PRIVATE );

       // プリファレンスに書き込むためのEditorオブジェクト取得 //
       Editor editor = third.edit();

       // "" というキーで名前を登録
       editor.putInt( "thirdPoint", score.normalRankingPoint[2] );
       
       // 書き込みの確定（実際にファイルに書き込む）
       editor.commit();	
	}
	//エンドレス3位スコア保存
		public void endlessThirdSave( ){
			 // Write
			SharedPreferences endlessThird = 
	               getSharedPreferences( "endlessThirdPoint", Context.MODE_PRIVATE );

	       // プリファレンスに書き込むためのEditorオブジェクト取得 //
	       Editor editor = endlessThird.edit();

	       // "" というキーで名前を登録
	       editor.putInt( "endlessThirdPoint", score.endelessRankingPoint[2] );
	       
	       // 書き込みの確定（実際にファイルに書き込む）
	       editor.commit();	
		}
	

	
	//ランキング3位画像表示
	//スコア表示
	public void drawScore(Canvas canvas){
		
		switch(loadCount){
		
		case NOT:
			
		break;

		case 1:
			if( score.endlessMode == false ){
				canvas.drawBitmap( resultNum[score.normalScore[3]] , 515 * tune , 280 * tune, null);
			} else if( score.endlessMode == true ){
				canvas.drawBitmap( resultNum[score.endlessScore[4]] , 515 * tune , 280 * tune, null);
			}
		break;

		case 2:
			if( score.endlessMode == false ){
				canvas.drawBitmap( resultNum[score.normalScore[3]] , 515 * tune , 280 * tune, null);
				canvas.drawBitmap( resultNum[score.normalScore[2]] , 415 * tune , 280 * tune, null);
			} else if( score.endlessMode == true ){
				canvas.drawBitmap( resultNum[score.endlessScore[4]] , 515 * tune , 280 * tune, null);
				canvas.drawBitmap( resultNum[score.endlessScore[3]] , 415 * tune , 280 * tune, null);
			}
		break;

		case 3:
			if( score.endlessMode == false ){
				canvas.drawBitmap( resultNum[score.normalScore[3]] , 515 * tune , 280 * tune, null);
				canvas.drawBitmap( resultNum[score.normalScore[2]] , 415 * tune , 280 * tune, null);
				canvas.drawBitmap( resultNum[score.normalScore[1]] , 315 * tune , 280 * tune, null);
			} else if( score.endlessMode == true ){
				canvas.drawBitmap( resultNum[score.endlessScore[4]] , 515 * tune , 280 * tune, null);
				canvas.drawBitmap( resultNum[score.endlessScore[3]] , 415 * tune , 280 * tune, null);
				canvas.drawBitmap( resultNum[score.endlessScore[2]] , 315 * tune , 280 * tune, null);
			}
		break;

		case 4:
			if( score.endlessMode == false ){
				canvas.drawBitmap( resultNum[score.normalScore[3]] , 515 * tune , 280 * tune, null);
				canvas.drawBitmap( resultNum[score.normalScore[2]] , 415 * tune , 280 * tune, null);
				canvas.drawBitmap( resultNum[score.normalScore[1]] , 315 * tune , 280 * tune, null);
				canvas.drawBitmap( resultNum[score.normalScore[0]] , 215 * tune , 280 * tune, null);
			} else if( score.endlessMode == true ){
				canvas.drawBitmap( resultNum[score.endlessScore[4]] , 515 * tune , 280 * tune, null);
				canvas.drawBitmap( resultNum[score.endlessScore[3]] , 415 * tune , 280 * tune, null);
				canvas.drawBitmap( resultNum[score.endlessScore[2]] , 315 * tune , 280 * tune, null);
				canvas.drawBitmap( resultNum[score.endlessScore[1]] , 215 * tune , 280 * tune, null);
				}
		break;
		
		case 5:
			
				canvas.drawBitmap( resultNum[score.endlessScore[4]] , 515 * tune , 280 * tune, null);
				canvas.drawBitmap( resultNum[score.endlessScore[3]] , 415 * tune , 280 * tune, null);
				canvas.drawBitmap( resultNum[score.endlessScore[2]] , 315 * tune , 280 * tune, null);
				canvas.drawBitmap( resultNum[score.endlessScore[1]] , 215 * tune , 280 * tune, null);
				canvas.drawBitmap( resultNum[score.endlessScore[0]] , 115 * tune , 280 * tune, null);
				
		break;
		}
	}
	
	//Maxコンボ計算
	
	//所有石の計算
	public void stoneCount(){
		for(int i = 0; i < 6; i++){
			score.haveResult[i][0] = stone.have[i] /10 ;
			score.haveResult[i][1] = stone.have[i];
			score.haveResult[i][1] = score.haveResult[i][1] - score.haveResult[i][0] * 10; 
		}	
	}
	
	
	//リザルト石表示
	public void drawStone(Canvas canvas){
		if( loadStoneState[0] == true){
			canvas.drawBitmap( resultStone[0] , 115 * tune , 255 * tune, null);
		}
		if( loadStoneState[1] == true){
			canvas.drawBitmap( resultStone[1] , 115 * tune , 365 * tune, null);
		}
		if( loadStoneState[2] == true){
			canvas.drawBitmap( resultStone[2] , 115 * tune , 475 * tune, null);
		}
		if( loadStoneState[3] == true){
			canvas.drawBitmap( resultStone[3] , 445 * tune , 255 * tune, null);
		}
		if( loadStoneState[1] == true){
			canvas.drawBitmap( resultStone[4] , 445 * tune , 365 * tune, null);
		}
		if( loadStoneState[1] == true){
			canvas.drawBitmap( resultStone[5] , 445 * tune , 475 * tune, null);
		}
	}
	
	//プレイ画面のコンボ表示
	
	 public void getCombo(Canvas canvas){
		 if( 0 < score.combo ){
			
				if( score.combo <= 10){
					canvas.drawBitmap(comboText[0], monyako.x + 60 * tune, monyako.y  - 50 * tune - getTimer, null);
					
					if(score.playCombo[0] != 0){
						canvas.drawBitmap( comboNum[0][score.playCombo[0]], monyako.x - 40 * tune, monyako.y  - 80 * tune - getTimer, null);
					}
					canvas.drawBitmap( comboNum[0][score.playCombo[1]], monyako.x + 10 * tune, monyako.y  - 80 * tune - getTimer, null);
					 
				} else if( 10 < score.combo && score.combo <= 20){
					canvas.drawBitmap(comboText[1], monyako.x + 60 * tune, monyako.y  - 50 * tune - getTimer, null);
					canvas.drawBitmap( comboNum[1][score.playCombo[0]], monyako.x  - 40 * tune, monyako.y  - 80 * tune - getTimer, null);
					canvas.drawBitmap( comboNum[1][score.playCombo[1]], monyako.x + 10 * tune, monyako.y  - 80 * tune - getTimer, null);
				} else if( 20 < score.combo ){
					canvas.drawBitmap(comboText[2], monyako.x + 60 * tune, monyako.y - 50 * tune - getTimer, null);
					canvas.drawBitmap( comboNum[2][score.playCombo[0]], monyako.x - 40 * tune, monyako.y - 80 * tune - getTimer, null);
					canvas.drawBitmap( comboNum[2][score.playCombo[1]], monyako.x + 10 * tune, monyako.y  - 80 * tune - getTimer, null);
				}
			}
	 }
	//リザルトコンボ
	 public void drawCombo(Canvas canvas){
			canvas.drawBitmap(comboText[0], 600 * tune , 275 * tune, null);
			if(score.playCombo[0] != 0){
				canvas.drawBitmap( comboNum[0][score.resultCombo[0]], 500 * tune , 255 * tune, null);
			}
			canvas.drawBitmap( comboNum[0][score.resultCombo[1]], 555 * tune , 255 * tune, null);
	}

	

	//リザルト低
	public void drawLow(Canvas canvas){
			canvas.drawBitmap( multi , 260 * tune , 295 * tune, null);
			if(score.haveResult[0][0] != 0){
				canvas.drawBitmap( stoneNum[score.haveResult[0][0]], 310 * tune , 265 * tune, null);
			}
			canvas.drawBitmap( stoneNum[score.haveResult[0][1]], 360 * tune , 265 * tune, null);
	}
	
	//リザルト中
		public void drawMiddle(Canvas canvas){
				canvas.drawBitmap( multi , 260 * tune , 405 * tune, null);
				if(score.haveResult[1][0] != 0){
					canvas.drawBitmap( stoneNum[score.haveResult[1][0]], 310 * tune , 375 * tune, null);
				}
				canvas.drawBitmap( stoneNum[score.haveResult[1][1]], 360 * tune , 375 * tune, null);
		}
		
		//リザルト高
		public void drawHigh(Canvas canvas){
				canvas.drawBitmap( multi , 260 * tune , 515 * tune, null);
				if(score.haveResult[2][0] != 0){
					canvas.drawBitmap( stoneNum[score.haveResult[2][0]], 310 * tune , 485 * tune, null);
				}
				canvas.drawBitmap( stoneNum[score.haveResult[2][1]], 360 * tune , 485 * tune, null);
		}

		//リザルト赤
		public void drawRed(Canvas canvas){
				canvas.drawBitmap( multi , 590 * tune , 295 * tune, null);
				if(score.haveResult[3][0] != 0){
					canvas.drawBitmap( stoneNum[score.haveResult[3][0]], 640 * tune , 265 * tune, null);
				}
				canvas.drawBitmap( stoneNum[score.haveResult[3][1]], 690 * tune , 265 * tune, null);
		}

		//リザルト緑
		public void drawGreen(Canvas canvas){
			canvas.drawBitmap( multi , 590 * tune , 405 * tune, null);
				if(score.haveResult[4][0] != 0){
					canvas.drawBitmap( stoneNum[score.haveResult[4][0]], 640 * tune , 375 * tune, null);
				}
				canvas.drawBitmap( stoneNum[score.haveResult[4][1]], 690 * tune , 375 * tune, null);
			
			
		}

		//リザルト青
		public void drawBlue(Canvas canvas){
			canvas.drawBitmap( multi , 590 * tune, 515 * tune, null);
				if(score.haveResult[5][0] != 0){
					canvas.drawBitmap( stoneNum[score.haveResult[5][0]], 640 * tune , 485 * tune, null);
				}
				canvas.drawBitmap( stoneNum[score.haveResult[5][1]], 690 * tune , 485 * tune, null);
			
			
		}
		

	
	//スコアデータ読み込み
	public int scoreLoad(){
		//1位読み込み
		SharedPreferences first = 
               getSharedPreferences( "firstPoint", Context.MODE_PRIVATE );
		 score.normalRankingPoint[0] = first.getInt( "firstPoint", 0 );
		 
		 //エンドレス
		 SharedPreferences endlessFirst = 
	               getSharedPreferences( "endlessFirstPoint", Context.MODE_PRIVATE );
			 score.endelessRankingPoint[0] = endlessFirst.getInt( "endlessFirstPoint", 0 );

		//2位読み込み
		SharedPreferences second = 
	            getSharedPreferences( "secondPoint", Context.MODE_PRIVATE );
		score.normalRankingPoint[1] = second.getInt( "secondPoint", 0 );
			
		//エンドレス
		 SharedPreferences endlessSecond = 
	               getSharedPreferences( "endlessSecondPoint", Context.MODE_PRIVATE );
		 score.endelessRankingPoint[1] = endlessSecond.getInt( "endlessSecondPoint", 0 );

		//3位読み込み
		SharedPreferences third = 
		        getSharedPreferences( "thirdPoint", Context.MODE_PRIVATE );
		score.normalRankingPoint[2] =  third.getInt( "thirdPoint", 0 );
		
		//エンドレス
		 SharedPreferences endlessThird = 
	               getSharedPreferences( "endlessThirdPoint", Context.MODE_PRIVATE );
		return score.endelessRankingPoint[2] = endlessThird.getInt( "endlessThirdPoint", 0 );


	
	}
	
	//ゲーム終了時処理
	protected void onDestroy() { 
		 super.onDestroy(); 
		 
		 System.exit(0);
		 System.gc();
		
		 
	}
	
	//プレイモード初期化
	public void setPlayDefault(){
		LAP = 0;
		seDamageLoad = false;
		seClimbLoad = false;
		seWalkLoad = false;
		seResultLoad = 0;
		seResultBadLoad = false;
		score.comboTimer = 0;
		bonusCheck = false;
		scoreClearTimer = 0;
		playState = PLAY_READY;
		startTimer = 0;
		monyako.x = -140 * tune;
		monyako.y = 555 * tune;
		ladder.count = 0;
		stone.countStock = 0;
		score.point = 0;
		stone.leftStock = 0;
		stone.rightStock = 0;
		moveSpeed = 5;
		monyako.state = Monyako.MONYAKO_WALK;
		monyako.color = Monyako.WHITE;
		monyako.effect = NOT;
		monyako.breaker = 0;
		monyako.get = false;
		getTimer = 0;

		for( int i = 0; i < 4; i++){
			ladder.state[i] = NOT;
			ladder.check[i] = false;
			ladder.x[i] = displayX;
		
		}
		
		for(int i = 0; i < 12; i++){
			trap.normalBlockX[i] = displayX;
		
			trap.normalBlock[i] = false;
		}
		
		
		for(int i = 0; i < 6; i++){
			stone.have[i] = 0;
			for(int h = 0; h < 5; h++){
				stone.timer[i][h] = 0 ;
			}
			for( int h = 0; h < 4; h++){
				stone.kind[i][h]  = false;
				stone.x[i][h]  = displayX + 100 * tune;
			
			}
		}
		for( int i = 0; i < 3; i++){
			
			trap.kiraBlock[0][i] = false;
			trap.kiraBlock[1][i] = false;
			trap.kiraBlock[2][i] = false;
			trap.kiraBlockX[0][i] = displayX;
			trap.kiraBlockX[1][i] = displayX;
			trap.kiraBlockX[2][i] = displayX;
		}
	
		for( int i = 0; i < 2; i++){
			effectTimer[i] = 0;
		}
		
		
		loadCheck = false;
		resultCheck = 0;
		resultTimer =0;
		resultState = 0;
		
		loadCount = 0;
		for(int i = 0; i < 6; i++){
			loadStoneState[i] = false;
		}
		
		ladder.speed = 0;
		saveCheck = false;
		monyako.ladderTimer = 0;
		stage.startPoint = 0;
		groundX[0] = 0;
		groundX[1] = displayX;
		textTimer = 0;
		backgroundX[0] = 0;
		backgroundX[1] = displayX;
		goalMove = 0;
		speedDifficulty = SPEED_EASY;

		score.maxCombo = 0;
		score.combo = 0;
		 speedCheck = 0;
		 

		 reviMove = 0;
	}

	
	

	

	
	
	public void controlMusic(){
		if(bgmState == BGM_ON){
			
		
		switch(gameState){
		
		case GAME_TITLE:
			if (!musicTitle.isPlaying()){
				musicTitle.start();
			}
			break;
		
		case GAME_LOAD:
			if (!musicTitle.isPlaying()){
				musicTitle.start();
			}
			break;
		case GAME_MENU:
	
			if (musicTitle.isPlaying()){
				musicTitle.pause();
			}
			
			
			if (!musicHome.isPlaying()){
				musicHome.start();
				
			}
			
			//場面遷移をし、使わない曲を一時停止し、再生位置を最初に戻す
			
			if (musicPlay.isPlaying()){
			    musicPlay.pause();
			    musicPlay.seekTo(0);
			} else if (musicOver.isPlaying()){
				musicOver.pause();
				musicOver.seekTo(0);
			} else if (musicResult.isPlaying()){
			    //一時停止
				musicResult.pause();
				musicClear.seekTo(0);
				musicResult.seekTo(0);;
			}
			
			
			break;
			//冒険画面での処理
		case GAME_PLAY:
			
			if( playState == PLAY_NORMAL){
				if( !musicPlay.isPlaying()){
					musicPlay.start();
				}
			} else if(goalState == GOAL_END){
				if( !musicClear.isPlaying()){
					musicClear.start();
				}
				
			} else if(playState == PLAY_OVER){
				if( !musicClear.isPlaying()){
					musicOver.start();
				}
			}
			//一時停止
			if (musicHome.isPlaying()){
				musicHome.pause();
				musicHome.seekTo(0);
			}
			//ポーズ画面、死亡画面、ゴール画面でPlayを一時停止
			if( playState == PLAY_PAUSE || playState == PLAY_OVER || goalState == GOAL_END){
				if( musicPlay.isPlaying()){
					musicPlay.pause();
				}
			}
			
			
			break;
			//チュートリアルでの処理
		case GAME_TUTORIAL:
			//メニュー画面の曲の停止
			if (musicHome.isPlaying()){
				musicHome.pause();
				musicHome.seekTo(0);
			}
			
			
			if( !musicPlay.isPlaying()){
				musicPlay.start();
			}
			
			if( faseState == 100){
				if(musicPlay.isPlaying()){
					musicPlay.pause();
				}
					
			}	
			break;
			//結果画面での処理
		case GAME_RESULT:
			if (musicPlay.isPlaying()){

			    musicPlay.pause();
			}
			if (musicClear.isPlaying()){
				musicClear.pause();
				
			}
			if (resultState == RESULT_BEFORE ){
				if( !musicResultBefore.isPlaying()){
					musicResultBefore.start();
				}
			}
			if(resultState == RESULT_AFTER){
				if (musicResultBefore.isPlaying()){
					musicResultBefore.pause();
				}
			}
			if(resultState == RESULT_AFTER && seResultLoad > 20){
				if( !musicResult.isPlaying()){
					musicResult.start();
				}
			}
			
			break;
			
		}
		//BGMをOFFにしてるときが鳴らさない
		}else if( bgmState== BGM_OFF){
			
		}
	}
	//ActivityがonPauseになった時の処理(主にBGMの一時停止)
	@Override 
	protected void onPause() { 
		super.onPause();   //BGMの停止 
		
		
		if(musicTitle.isPlaying()){
			musicTitle.pause();
			
		}
		if(musicHome.isPlaying()){
			musicHome.pause();
			
		}
		if(musicPlay.isPlaying()){
			musicPlay.pause();
			
		} 
		if(musicResultBefore.isPlaying()){
			musicResultBefore.pause();
			
		} 
		if(musicOver.isPlaying()){
			musicOver.pause();
			
		}
		if(musicClear.isPlaying()){
			musicClear.pause();
			
		}
		if(musicResult.isPlaying()){
			musicResult.pause();
			
		}
	} 
	
	//タイトル画面の画像メモリを解放
	public void titleClear(){
		for(int i = 0 ; i < 4 ; i++){
		  loading[i] = null;
		  monyakoWalk[i] = null;
		  revi[i] = null;
		}
		for( int i = 0; i < 5; i++){
			loadTips[i] = null;
			imgStone[3][i] = null;
			imgStone[0][i] = null; 
		}
		
		titleBackground.recycle();
		titleLogo.recycle();
		titleStart[0] = null;
		titleStart[1] = null;
	}
	
	//リザルト画面の画像読み込み
	public void resultLoad(){
		bonus[0] = BitmapFactory.decodeResource(res, R.drawable.bonus_combo);
		bonus[1] = BitmapFactory.decodeResource(res, R.drawable.bonus_kira);
		bonus[2] = BitmapFactory.decodeResource(res, R.drawable.bonus_all);
		resultBackGround[0] = BitmapFactory.decodeResource(res, R.drawable.result_background_before);
		resultBackGround[1] = BitmapFactory.decodeResource(res, R.drawable.result_background_after);
		resultBackGround[2] = BitmapFactory.decodeResource(res, R.drawable.bad_score);
		resultWindow = BitmapFactory.decodeResource(res, R.drawable.result_window);
		resultStone[0] = BitmapFactory.decodeResource(res, R.drawable.result_low);
		resultStone[1] = BitmapFactory.decodeResource(res, R.drawable.result_middle);
		resultStone[2] = BitmapFactory.decodeResource(res, R.drawable.result_high);
		resultStone[3] = BitmapFactory.decodeResource(res, R.drawable.result_red);
		resultStone[4] = BitmapFactory.decodeResource(res, R.drawable.result_green);
		resultStone[5] = BitmapFactory.decodeResource(res, R.drawable.result_blue);
		resultKira = BitmapFactory.decodeResource(res, R.drawable.result_kira);
		resultGet = BitmapFactory.decodeResource(res, R.drawable.result_get);
		resultHome[0] = BitmapFactory.decodeResource(res, R.drawable.result_home1);
		resultHome[1] = BitmapFactory.decodeResource(res, R.drawable.result_home2);
		menuEnd[0] = BitmapFactory.decodeResource(res, R.drawable.menu_end1);
		menuEnd[1] = BitmapFactory.decodeResource(res, R.drawable.menu_end2);
		multi  = BitmapFactory.decodeResource(res, R.drawable.result_multi);
		stoneNum[0] = BitmapFactory.decodeResource(res, R.drawable.stone_0);
		stoneNum[1] = BitmapFactory.decodeResource(res, R.drawable.stone_1);
		stoneNum[2] = BitmapFactory.decodeResource(res, R.drawable.stone_2);
		stoneNum[3] = BitmapFactory.decodeResource(res, R.drawable.stone_3);
		stoneNum[4] = BitmapFactory.decodeResource(res, R.drawable.stone_4);
		stoneNum[5] = BitmapFactory.decodeResource(res, R.drawable.stone_5);
		stoneNum[6] = BitmapFactory.decodeResource(res, R.drawable.stone_6);
		stoneNum[7] = BitmapFactory.decodeResource(res, R.drawable.stone_7);
		stoneNum[8] = BitmapFactory.decodeResource(res, R.drawable.stone_8);
		stoneNum[9] = BitmapFactory.decodeResource(res, R.drawable.stone_9);
		
		resultNum[0] = BitmapFactory.decodeResource(res, R.drawable.result_0);
		resultNum[1] = BitmapFactory.decodeResource(res, R.drawable.result_1);
		resultNum[2] = BitmapFactory.decodeResource(res, R.drawable.result_2);
		resultNum[3] = BitmapFactory.decodeResource(res, R.drawable.result_3);
		resultNum[4] = BitmapFactory.decodeResource(res, R.drawable.result_4);
		resultNum[5] = BitmapFactory.decodeResource(res, R.drawable.result_5);
		resultNum[6] = BitmapFactory.decodeResource(res, R.drawable.result_6);
		resultNum[7] = BitmapFactory.decodeResource(res, R.drawable.result_7);
		resultNum[8] = BitmapFactory.decodeResource(res, R.drawable.result_8);
		resultNum[9] = BitmapFactory.decodeResource(res, R.drawable.result_9);
		
		
	}
	//リザルト画面の画像メモリを解放
	public void resultLoadClear(){
		
		resultWindow =  null;
		for( int i = 0; i < 6; i++){
			resultStone[i] =  null;
		}
		
		resultKira =  null;
		resultGet =  null;
		resultHome[0] = null;
		resultHome[1] = null;
		menuEnd[0] = null;
		menuEnd[1] = null;
		multi  =  null;
		for(int i = 0; i <3; i++){
			comboText[i] = null;
			resultBackGround[i] = null;
		}
		for(int i = 0; i < 9; i ++){
			comboNum[0][i] = null;
			comboNum[1][i] = null;
			comboNum[2][i] = null;
			stoneNum[i] =  null;
			resultNum[i] =  null;
		}
		
	}

	public void getLadder(Canvas canvas){
		
		
		for( int i = 0; i < 4; i ++){
		switch(ladder.state[i]){
		
		case NOT:
			
		break;
		
		case SET:
			ladder.x[i] = ladder.x[i] - moveSpeed;
			canvas.drawBitmap(imgLadder , ladder.x[i] - 75 * tune, ladder.y[i], null);
			if(ladder.x[i] - 75 * tune * 1 / 3  < monyako.x){
				ladder.state[i] = USED;
			}
			if( (monyako.x < ladder.x[i] + 75 * tune&& ladder.x[i] + 75 * tune < 280 * tune ) && 
				( (ladder.y[i] + 105 * tune -5 <= monyako.y  && monyako.y <= ladder.y[i] + 105 * tune +5)|| 
						(ladder.y[i] - 125 * tune -10 <= monyako.y  && monyako.y <= ladder.y[i] - 125 * tune +5))
				&& monyako.state == Monyako.MONYAKO_WALK
					
					){
				
				
				ladder.check[i] = true;
				ladderAction();
			
				
			} else if(ladder.x[i] < -60 * tune ){
				ladder.count--;
				ladder.x[i] = displayX * tune;
				ladder.state[i] = NOT;
			}
		break;
		
		case USED:
			ladder.x[i] = ladder.x[i] - moveSpeed;
			canvas.drawBitmap(imgLadder , ladder.x[i] - 75 * tune, ladder.y[i], null);
			if(ladder.x[i] < -60 * tune ){
				ladder.count--;
				ladder.x[i] = displayX * tune;
				ladder.state[i] = NOT;
				
				
			}
		break;
		
		}
		}
	}
	
	public void getMonyako(Canvas canvas){
		//歩くもにゃこ
				switch(monyako.state){
				
				case Monyako.MONYAKO_WALK:
					seClimbLoad = false;
					monyako.ladderTimer = 0;
					
					if( 0 <= monyako.walkTimer && monyako.walkTimer <= 1 ){
						if( seWalkLoad == false && gameState != GAME_LOAD ){
							se.play(seWalk, 1.0F, 1.0F, 0, 0, 1.0F);
							seWalkLoad = true;
						}
							canvas.drawBitmap(monyakoWalk[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
					} else if( 2 <= monyako.walkTimer && monyako.walkTimer < 15 ){
						//se.play(seWalk, 1.0F, 1.0F, 0, 0, 1.0F);
						seWalkLoad = false;
						canvas.drawBitmap(monyakoWalk[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
					} else if( 15 <= monyako.walkTimer && monyako.walkTimer <= 16 ){
						
						canvas.drawBitmap(monyakoWalk[1 + (monyako.color * 4)] , monyako.x, monyako.y, null);
					} else if( 17 <= monyako.walkTimer && monyako.walkTimer < 30){
						canvas.drawBitmap(monyakoWalk[1 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						
					} else if( 30 <= monyako.walkTimer && monyako.walkTimer <= 31){
						if( seWalkLoad == false  && gameState != GAME_LOAD ){
							se.play(seWalk, 1.0F, 1.0F, 0, 0, 1.0F);
							seWalkLoad = true;
						}
						canvas.drawBitmap(monyakoWalk[2 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						
					}  else if( 32 <= monyako.walkTimer && monyako.walkTimer < 45){
						canvas.drawBitmap(monyakoWalk[2 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						seWalkLoad = false;
					} else if( 45 <= monyako.walkTimer && monyako.walkTimer <= 46){
						
						canvas.drawBitmap(monyakoWalk[3 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						
					} else if( 47 <= monyako.walkTimer && monyako.walkTimer <= 60){
						canvas.drawBitmap(monyakoWalk[3 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						
					} else if(  monyako.walkTimer >= 61){
						canvas.drawBitmap(monyakoWalk[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						monyako.walkTimer = 0;
					}
				
				break;
				
				case Monyako.MONYAKO_UP:
					se.stop(seWalk);
					moveSpeed = 0;
					seWalkLoad = false;
					if( seClimbLoad == false){
						se.play(seClimb, 1.0F, 1.0F, 0, 0, 1.0F);
						seClimbLoad = true;
					}
					if(230 * tune -5 * tune * ladder.speed >= 0){
						 if( 0 <= monyako.ladderTimer && monyako.ladderTimer < 11){	
							canvas.drawBitmap(monyakoLadder[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
							
						}else if( 11 <= monyako.ladderTimer && monyako.ladderTimer <= 12){
						
							canvas.drawBitmap(monyakoLadder[1 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						
						}else if( 13 <= monyako.ladderTimer && monyako.ladderTimer < 23){
							canvas.drawBitmap(monyakoLadder[1 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						
						}else if( 23 <= monyako.ladderTimer && monyako.ladderTimer <= 24){
							
							canvas.drawBitmap(monyakoLadder[2 + (monyako.color * 4)] , monyako.x, monyako.y, null);
							
						}else if( 25 <= monyako.ladderTimer && monyako.ladderTimer < 35){
							canvas.drawBitmap(monyakoLadder[2 + (monyako.color * 4)] , monyako.x, monyako.y, null);
							
						}else if( 35 <= monyako.ladderTimer && monyako.ladderTimer <= 36){
							canvas.drawBitmap(monyakoLadder[3 + (monyako.color * 4)] , monyako.x, monyako.y, null);
							
						}else if( 37 <= monyako.ladderTimer && monyako.ladderTimer <= 45){
							canvas.drawBitmap(monyakoLadder[3 + (monyako.color * 4)] , monyako.x, monyako.y, null);
							
						}else if(monyako.ladderTimer >= 46){
							monyako.ladderTimer = 0;
							canvas.drawBitmap(monyakoLadder[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						
						}
					}else {
						canvas.drawBitmap(monyakoWalk[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						moveSpeed = speedDifficulty;
						ladder.speed = 0;
						monyako.walkTimer = 0;
						if(monyako.y <= 95 * tune){
							monyako.y = 95 * tune;
							seClimbLoad = false;
						} else {
							monyako.y = 325 * tune;
							seClimbLoad = false;
						}
						monyako.state = Monyako.MONYAKO_WALK;
						
					}
				
			
				break;	
				
				case Monyako.MONYAKO_DOWN:
					se.stop(seWalk);
					moveSpeed = 0;
					seWalkLoad = false;
					if( seClimbLoad == false){
						se.play(seClimb, 1.0F, 1.0F, 0, 0, 1.0F);
						seClimbLoad = true;
					}
					if(230 * tune -5 * tune * ladder.speed >= 0){
						 if( 0 <= monyako.ladderTimer && monyako.ladderTimer < 11){	
							canvas.drawBitmap(monyakoLadder[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
							
						}else if( 11 <= monyako.ladderTimer && monyako.ladderTimer < 23){
							
							canvas.drawBitmap(monyakoLadder[1 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						
						}else if( 23 <= monyako.ladderTimer && monyako.ladderTimer < 24){
						
							canvas.drawBitmap(monyakoLadder[2 + (monyako.color * 4)] , monyako.x, monyako.y, null);
							
						}else if( 24 <= monyako.ladderTimer && monyako.ladderTimer <= 35){
							canvas.drawBitmap(monyakoLadder[2 + (monyako.color * 4)] , monyako.x, monyako.y, null);
							
						}else if( 35 <= monyako.ladderTimer && monyako.ladderTimer <= 45){
							canvas.drawBitmap(monyakoLadder[3 + (monyako.color * 4)] , monyako.x, monyako.y, null);
							
						}else if(monyako.ladderTimer >= 46){
							monyako.ladderTimer = 0;
							canvas.drawBitmap(monyakoLadder[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						
						}
					}else {
						canvas.drawBitmap(monyakoWalk[0 + (monyako.color * 4)] , monyako.x, monyako.y, null);
						moveSpeed = speedDifficulty;
						ladder.speed = 0;
						monyako.walkTimer = 0;
						if(monyako.y >= 555 * tune){
							monyako.y = 555 * tune;
							seClimbLoad = false;
						}else {
							monyako.y = 325 * tune;
							seClimbLoad = false;
						}
						monyako.state = Monyako.MONYAKO_WALK;
					}
				break;	
				}
		
	}
	
	public void getKiraEffect(Canvas canvas){
		
		
			if( 1 <= monyako.effect && monyako.effect <= 3 ){
				effectTimer[0]++;
				if( 0 <= effectTimer[0] && effectTimer[0] <= 5){
					canvas.drawBitmap(effect[monyako.effect  * 4 - 4] , monyako.x - 30 * tune , monyako.y - 60 * tune  , null);	
				}else if( 5 <= effectTimer[0] && effectTimer[0] <= 10){
					canvas.drawBitmap(effect[monyako.effect  * 4 - 3] , monyako.x - 30 * tune , monyako.y - 60 * tune   , null);	
				}else if( 10 <= effectTimer[0] && effectTimer[0] <= 15){
					canvas.drawBitmap(effect[monyako.effect * 4 - 2] , monyako.x - 30 * tune , monyako.y - 60 * tune  , null);	
				}else if( 15 <= effectTimer[0] && effectTimer[0] <= 20){
					canvas.drawBitmap(effect[monyako.effect  * 4 - 1] , monyako.x - 30 * tune , monyako.y - 60 * tune   , null);	
				}else if( 20 <= effectTimer[0] && effectTimer[0] <= 25){
					switch(monyako.effect){
				
				
					case Monyako.EFFECT_RED:
						monyako.color = Monyako.SET_RED;
						break;
					
					case Monyako.EFFECT_GREEN:
						monyako.color = Monyako.SET_GREEN;
						break;
				
					case Monyako.EFFECT_BLUE:
						monyako.color = Monyako.SET_BLUE;
					break;
					}
					
					canvas.drawBitmap(effect[monyako.effect  * 4 - 4] , monyako.x - 30 * tune , monyako.y - 60 * tune  , null);	
				}else if( 25 <= effectTimer[0] && effectTimer[0] <= 30){
					canvas.drawBitmap(effect[monyako.effect  * 4 - 3] , monyako.x - 30 * tune , monyako.y - 60 * tune  , null);	
				}else if( 30 <= effectTimer[0] && effectTimer[0] <= 35){
					canvas.drawBitmap(effect[monyako.effect  * 4 - 2] , monyako.x - 30 * tune , monyako.y - 60 * tune  , null);	
				}else if( 35 <= effectTimer[0] && effectTimer[0] <= 40){
					canvas.drawBitmap(effect[monyako.effect  * 4 - 1] , monyako.x - 30 * tune , monyako.y - 60 * tune  , null);	
				}else{
					effectTimer[0] = 0;
					monyako.effect = NOT;
				}
			}
		
		
		
			if(4 <= monyako.effect && monyako.effect <= 6){
				
				effectTimer[1]++;
				if( 0 <= effectTimer[1] && effectTimer[1] <= 1){
					
					se.play(seReturn, 1.0F, 1.0F, 0, 0, 1.0F);
					canvas.drawBitmap(effect[monyako.effect  * 4 - 4] , monyako.x - 30 * tune , monyako.y - 60 * tune  , null);
				}else if( 2 <= effectTimer[1] && effectTimer[1] <= 10){
					canvas.drawBitmap(effect[monyako.effect  * 4 - 4] , monyako.x - 30 * tune , monyako.y - 60 * tune  , null);	
				}else if( 10 <= effectTimer[1] && effectTimer[1] <= 20){
					canvas.drawBitmap(effect[monyako.effect  * 4 - 3] , monyako.x - 30 * tune , monyako.y - 60 * tune  , null);	
				}else if( 20 <= effectTimer[1] && effectTimer[1] <= 30){
					canvas.drawBitmap(effect[monyako.effect  * 4 - 2] , monyako.x - 30 * tune , monyako.y - 60 * tune  , null);	
				}else if( 30 <= effectTimer[1] && effectTimer[1] <= 40){
					canvas.drawBitmap(effect[monyako.effect  * 4 - 1] , monyako.x - 30 * tune , monyako.y - 60 * tune  , null);	
				}else if( 40 <= effectTimer[1] && effectTimer[1] <= 50){
					monyako.breaker = NOT;
					monyako.color = Monyako.WHITE;
					canvas.drawBitmap(effect[monyako.effect  * 4 - 4] , monyako.x - 30 * tune , monyako.y - 60 * tune  , null);	
				}else if( 50 <= effectTimer[1] && effectTimer[1] <= 60){
					canvas.drawBitmap(effect[monyako.effect  * 4 - 3] , monyako.x - 30 * tune , monyako.y - 60 * tune  , null);	
				}else if( 60 <= effectTimer[1] && effectTimer[1] <= 70){
					canvas.drawBitmap(effect[monyako.effect  * 4 - 2] , monyako.x - 30 * tune , monyako.y - 60 * tune  , null);	
				}else if( 70 <= effectTimer[1] && effectTimer[1] <= 80){
					canvas.drawBitmap(effect[monyako.effect  * 4 - 1] , monyako.x - 30 * tune , monyako.y - 60 * tune  , null);	
				}else{
					effectTimer[1] = 0;
					monyako.effect = NOT;
					
				}
			}
		
	}
	
	public void getStone(Canvas canvas){
		
		//低ポイントストーン
		for(int i = 0 ; i < 4 ; i++){
			if( stone.kind[0][i] == false){
				stone.timer[0][i] = 0;
				stone.x[0][i] = displayX + 100 * tune;
			} else if( stone.kind[0][i] == true){
				stone.x[0][i] = stone.x[0][i] - moveSpeed;
				if( 0 <= stone.timer[0][i] && stone.timer[0][i] <= 15){
					stone.timer[0][i]++;
					canvas.drawBitmap( imgStone[0][0] , stone.x[0][i], stone.y[0][i], null);
				} else if( 15 <= stone.timer[0][i] && stone.timer[0][i] <= 30){
					stone.timer[0][i]++;
					canvas.drawBitmap( imgStone[0][1] , stone.x[0][i], stone.y[0][i], null);
				} else if( 30 <= stone.timer[0][i] && stone.timer[0][i] <= 45){
					stone.timer[0][i]++;
					canvas.drawBitmap( imgStone[0][2] ,stone.x[0][i], stone.y[0][i], null);
				} else if( 45 <= stone.timer[0][i] && stone.timer[0][i] <= 60){
					stone.timer[0][i]++;
					canvas.drawBitmap( imgStone[0][3] , stone.x[0][i], stone.y[0][i], null);
				}else if( 60 <= stone.timer[0][i] && stone.timer[0][i] <= 75){
					stone.timer[0][i]++;
					canvas.drawBitmap( imgStone[0][4] , stone.x[0][i], stone.y[0][i], null);
				} else if( 75 <= stone.timer[0][i] && stone.timer[0][i] < 90){
					stone.timer[0][i]++;
					canvas.drawBitmap( imgStone[0][3] , stone.x[0][i], stone.y[0][i], null);
				}else if( 90 <= stone.timer[0][i] && stone.timer[0][i] < 105){
					stone.timer[0][i]++;
					canvas.drawBitmap( imgStone[0][2] , stone.x[0][i], stone.y[0][i], null);
				}else if( 105 <= stone.timer[0][i] && stone.timer[0][i] < 120){
					stone.timer[0][i]++;
					canvas.drawBitmap( imgStone[0][1] , stone.x[0][i], stone.y[0][i], null);
				}else if( stone.timer[0][i] >= 120){
					canvas.drawBitmap( imgStone[0][0] , stone.x[0][i], stone.y[0][i], null);
					stone.timer[0][i] = 0;
				}
				if(( stone.y[0][i] + 50 * tune -5 <= monyako.y && monyako.y <= stone.y[0][i] + 50 * tune +5) && 
						monyako.x /10	< stone.x[0][i] && stone.x[0][i] < monyako.x *5 /4 ){
			
					se.play(seGet, 1.0F, 1.0F, 0, 0, 1.0F);
					getStoneLow();
					stone.x[0][i] = displayX + 100 * tune;
					monyako.get = true;
					stone.kind[0][i] = false;
					stone.timer[0][i] = 0;
				}else if(stone.x[0][i] < -220 * tune){
					
					if( score.maxCombo  < score.combo){
						score.maxCombo = score.combo;
						
					}
					score.combo = 0;
					score.comboTimer = 0;
					stone.timer[0][i] = 0;
					stone.x[0][i] = displayX + 100 * tune;
					stone.kind[0][i] = false;
				}
		
			}
		}
		
		//中ポイントストーン
		for(int i = 0 ; i < 4 ; i++){
			if(stone.kind[1][i] == false){
				stone.timer[1][i] = 0;
				stone.x[1][i] = displayX + 100 * tune;
			} else if(stone.kind[1][i] == true){
				stone.x[1][i]= stone.x[1][i] - moveSpeed;
				if( 0 <= stone.timer[1][i] && stone.timer[1][i] <= 15){
					stone.timer[1][i]++;
					canvas.drawBitmap( imgStone[1][0] , stone.x[1][i], stone.y[1][i], null);
				} else if( 15 <= stone.timer[1][i] && stone.timer[1][i] <= 30){
					stone.timer[1][i]++;
					canvas.drawBitmap( imgStone[1][1] , stone.x[1][i], stone.y[1][i], null);
				} else if( 30 <= stone.timer[1][i] && stone.timer[1][i] <= 45){
					stone.timer[1][i]++;
					canvas.drawBitmap( imgStone[1][2] ,stone.x[1][i] , stone.y[1][i], null);
				} else if( 45 <= stone.timer[1][i] && stone.timer[1][i] <= 60){
					stone.timer[1][i]++;
					canvas.drawBitmap( imgStone[1][3] , stone.x[1][i], stone.y[1][i], null);
				} else if( 60 <= stone.timer[1][i] && stone.timer[1][i] <= 75){
					stone.timer[1][i]++;
					canvas.drawBitmap( imgStone[1][4] , stone.x[1][i], stone.y[1][i], null);
				} else if( 75 <= stone.timer[1][i] && stone.timer[1][i] < 90){
					stone.timer[1][i]++;
					canvas.drawBitmap( imgStone[1][3] , stone.x[1][i], stone.y[1][i], null);
				} else if( 90 <= stone.timer[1][i] && stone.timer[1][i] < 105){
					stone.timer[1][i]++;
					canvas.drawBitmap( imgStone[1][2] , stone.x[1][i], stone.y[1][i], null);
				}else if( 105 <= stone.timer[1][i] && stone.timer[1][i] < 120){
					stone.timer[1][i]++;
					canvas.drawBitmap( imgStone[1][1] , stone.x[1][i], stone.y[1][i], null);
				}else if( stone.timer[1][i] >= 120){
					canvas.drawBitmap( imgStone[1][0] , stone.x[1][i], stone.y[1][i], null);
					stone.timer[1][i] = 0;
				}
				
				if( ( stone.y[1][i] + 50 * tune -5 <= monyako.y && monyako.y <= stone.y[1][i] + 50 * tune +5) && 
						monyako.x /10	< stone.x[1][i] && stone.x[1][i] < monyako.x *5 /4 ){
				
					se.play(seGet, 1.0F, 1.0F, 0, 0, 1.0F);
					getStoneMiddle();
					stone.x[1][i] = displayX + 100 * tune;
					monyako.get = true;
					stone.kind[1][i] = false;
				}else if(stone.x[1][i] < -220 * tune){
					if( score.maxCombo  < score.combo){
						score.maxCombo = score.combo;
					}
					stone.timer[1][i] = 0;
					score.combo = 0;
					score.comboTimer = 0;
					stone.x[1][i] = displayX + 100 * tune;
					stone.kind[1][i] = false;
				}
			}
		}
		//高ポイントストーン
		for( int i = 0; i < 4; i++){

			if( stone.kind[2][i] == false){
				stone.timer[2][i] = 0;
				stone.x[2][i] = displayX + 100 * tune;
			
			} else if(stone.kind[2][i] == true){
	
				stone.x[2][i] = stone.x[2][i] - moveSpeed;
				if( 0 <= stone.timer[2][i] && stone.timer[2][i] <= 15){
					stone.timer[2][i]++;
					canvas.drawBitmap( imgStone[2][0] , stone.x[2][i], stone.y[2][i], null);
				} else if( 15 <= stone.timer[2][i] && stone.timer[2][i] <= 30){
					stone.timer[2][i]++;
					canvas.drawBitmap( imgStone[2][1] , stone.x[2][i], stone.y[2][i], null);
				} else if( 30 <= stone.timer[2][i] && stone.timer[2][i] <= 45){
					stone.timer[2][i]++;
					canvas.drawBitmap( imgStone[2][2] ,stone.x[2][i], stone.y[2][i], null);
				} else if( 45 <= stone.timer[2][i] && stone.timer[2][i] <= 60){
					stone.timer[2][i]++;
					canvas.drawBitmap( imgStone[2][3] , stone.x[2][i], stone.y[2][i], null);
				} else if( 60 <= stone.timer[2][i] && stone.timer[2][i] <= 75){
					stone.timer[2][i]++;
					canvas.drawBitmap( imgStone[2][4] , stone.x[2][i], stone.y[2][i], null);
				} else if( 75 <= stone.timer[2][i] && stone.timer[2][i] < 90){
					stone.timer[2][i]++;
					canvas.drawBitmap( imgStone[2][3] , stone.x[2][i], stone.y[2][i], null);
				} else if( 90 <= stone.timer[2][i] && stone.timer[2][i] < 105){
					stone.timer[2][i]++;
					canvas.drawBitmap( imgStone[2][2] , stone.x[2][i], stone.y[2][i], null);
				} else if( 105 <= stone.timer[2][i] && stone.timer[2][i] < 120){
					stone.timer[2][i]++;
					canvas.drawBitmap( imgStone[2][1] , stone.x[2][i], stone.y[2][i], null);
				} else if( stone.timer[2][i] >= 120){
					canvas.drawBitmap( imgStone[2][0] , stone.x[2][i], stone.y[2][i], null);
					stone.timer[2][i] = 0;
				}
			
				if( ( stone.y[2][i] + 50 * tune -5 <= monyako.y && monyako.y <= stone.y[2][i] + 50 * tune +5) && 
						monyako.x /10	< stone.x[2][i] && stone.x[2][i] < monyako.x *5 /4 ){
					se.play(seGet, 1.0F, 1.0F, 0, 0, 1.0F);
					getStoneHigh();
					stone.x[2][i] = displayX + 100 * tune;
					monyako.get = true;
					stone.kind[2][i] = false;
				}else if(stone.x[2][i] < -220 * tune){
					if( score.maxCombo  < score.combo){
						score.maxCombo = score.combo;
					}
					score.combo = 0;
					score.comboTimer = 0;
					stone.timer[2][i] = 0;
					stone.x[2][i] = displayX + 100 * tune;
					stone.kind[2][i] = false;
				}
			}		
		}	
		//レッドストーン
		for( int i = 0; i < 4; i++){
			if( stone.kind[3][i] == false){
				stone.timer[3][i] = 0;
				stone.x[3][i] = displayX + 100 * tune;
			} else if( stone.kind[3][i] == true){
				stone.x[3][i] = stone.x[3][i] - moveSpeed;
				if( 0 <= stone.timer[3][i] && stone.timer[3][i] < 15){
					stone.timer[3][i]++;
					canvas.drawBitmap( imgStone[3][0] , stone.x[3][i], stone.y[3][i], null);
				} else if( 15 <= stone.timer[3][i] && stone.timer[3][i] < 30){
					stone.timer[3][i]++;
					canvas.drawBitmap( imgStone[3][1] , stone.x[3][i], stone.y[3][i], null);
				} else if( 30 <= stone.timer[3][i] && stone.timer[3][i] < 45){
					stone.timer[3][i]++;
					canvas.drawBitmap( imgStone[3][2] ,stone.x[3][i], stone.y[3][i], null);
				} else if( 45 <= stone.timer[3][i] && stone.timer[3][i] < 60){
					stone.timer[3][i]++;
					canvas.drawBitmap( imgStone[3][3] , stone.x[3][i], stone.y[3][i], null);
				}else if( 60 <= stone.timer[3][i] && stone.timer[3][i] < 75){
					stone.timer[3][i]++;
					canvas.drawBitmap( imgStone[3][4] , stone.x[3][i], stone.y[3][i], null);
				} else if( 75 <= stone.timer[3][i] && stone.timer[3][i] < 90){
					stone.timer[3][i]++;
					canvas.drawBitmap( imgStone[3][3] , stone.x[3][i], stone.y[3][i], null);
				}else if( 90 <= stone.timer[3][i] && stone.timer[3][i] < 105){
					stone.timer[3][i]++;
					canvas.drawBitmap( imgStone[3][2] , stone.x[3][i], stone.y[3][i], null);
				}else if( 105 <= stone.timer[3][i] && stone.timer[3][i] < 120){
					stone.timer[3][i]++;
					canvas.drawBitmap( imgStone[3][1] , stone.x[3][i], stone.y[3][i], null);
				}else if( stone.timer[3][i] >= 120){
					canvas.drawBitmap( imgStone[3][0] , stone.x[3][i], stone.y[3][i], null);
					stone.timer[3][i] = 0;
				}
				
				if(( stone.y[3][i] + 50 * tune -5 <= monyako.y && monyako.y <= stone.y[3][i] + 50 * tune +5) && 
					monyako.x /10 < stone.x[3][i] && stone.x[3][i] < monyako.x *5 /4 ){
					stone.x[3][i] = displayX + 100 * tune;
					monyako.get = true;
					se.play(seGet, 1.0F, 1.0F, 0, 0, 1.0F);
					getStoneRed();
					stone.kind[3][i] = false;
				}else if(stone.x[3][i] < -220 * tune){
					if( score.maxCombo  < score.combo){
						score.maxCombo = score.combo;
					}
					score.combo = 0;
					score.comboTimer = 0;
					stone.x[3][i] = displayX + 100 * tune;
					stone.kind[3][i] = false;
				}
		
		
			}
		}
		
		for( int i = 0; i < 4; i++){
		//グリーンストーン
			if( stone.kind[4][i] == false){
				stone.timer[4][i] = 0;
				stone.x[4][i] = displayX + 100 * tune;
			} else if( stone.kind[4][i] == true){
				stone.x[4][i] = stone.x[4][i] - moveSpeed;
				if( 0 <= stone.timer[4][i] && stone.timer[4][i] <= 15){
					stone.timer[4][i]++;
					canvas.drawBitmap( imgStone[4][0] , stone.x[4][i], stone.y[4][i], null);
				} else if( 15 <= stone.timer[4][i] && stone.timer[4][i] <= 30){
					stone.timer[4][i]++;
					canvas.drawBitmap( imgStone[4][1] , stone.x[4][i], stone.y[4][i], null);
				} else if( 30 <= stone.timer[4][i] && stone.timer[4][i] <= 45){
					stone.timer[4][i]++;
					canvas.drawBitmap( imgStone[4][2] ,stone.x[4][i], stone.y[4][i], null);
				} else if( 45 <= stone.timer[4][i] && stone.timer[4][i] <= 60){
					stone.timer[4][i]++;
					canvas.drawBitmap( imgStone[4][3] , stone.x[4][i], stone.y[4][i], null);
				} else if( 60 <= stone.timer[4][i] && stone.timer[4][i] <= 75){
					stone.timer[4][i]++;
					canvas.drawBitmap( imgStone[4][4] , stone.x[4][i], stone.y[4][i], null);
				} else if( 75 <= stone.timer[4][i] && stone.timer[4][i] < 90){
					stone.timer[4][i]++;
					canvas.drawBitmap( imgStone[4][3] , stone.x[4][i], stone.y[4][i], null);
				} else if( 90 <= stone.timer[4][i] && stone.timer[4][i] < 105){
					stone.timer[4][i]++;
					canvas.drawBitmap( imgStone[4][2] , stone.x[4][i], stone.y[4][i], null);
				} else if( 105 <= stone.timer[4][i] && stone.timer[4][i] < 120){
					stone.timer[4][i]++;
					canvas.drawBitmap( imgStone[4][1] , stone.x[4][i], stone.y[4][i], null);
				}else if( stone.timer[4][i] >= 120){
					canvas.drawBitmap( imgStone[4][0] , stone.x[4][i], stone.y[4][i], null);
					stone.timer[4][i] = 0;
				}
				if(( stone.y[4][i] + 50 * tune -5 <= monyako.y && monyako.y <= stone.y[4][i] + 50 * tune +5) && 
					monyako.x /10	< stone.x[4][i] && stone.x[4][i] < monyako.x *5 /4 ){
					stone.x[4][i] = displayX + 100 * tune;
					monyako.get = true;
					se.play(seGet, 1.0F, 1.0F, 0, 0, 1.0F);
					getStoneGreen();
					stone.kind[4][i] = false;
				}else if(stone.x[4][i] < -220 * tune){
					if( score.maxCombo  < score.combo){
						score.maxCombo = score.combo;
					}
					score.combo = 0;
					score.comboTimer = 0;
					stone.x[4][i] = displayX + 100 * tune;
					stone.kind[4][i] = false;
				}
			}
		}
		
		for( int i = 0; i < 4; i++){
		//ブルーストーン
			if(	stone.kind[5][i] == false ){
				stone.timer[5][i] = 0;
				stone.x[5][i] = displayX + 100 * tune;
			} else if( stone.kind[5][i] == true){
					stone.x[5][i] = stone.x[5][i] - moveSpeed;
				if( 0 <= stone.timer[5][i] && stone.timer[5][i] <= 15){
					stone.timer[5][i]++;
					canvas.drawBitmap( imgStone[5][0] , stone.x[5][i], stone.y[5][i], null);
				} else if( 15 <= stone.timer[5][i] && stone.timer[5][i] <= 30){
					stone.timer[5][i]++;
					canvas.drawBitmap( imgStone[5][1] , stone.x[5][i], stone.y[5][i], null);
				} else if( 30 <= stone.timer[5][i] && stone.timer[5][i] <= 45){
					stone.timer[5][i]++;
					canvas.drawBitmap( imgStone[5][2] ,stone.x[5][i], stone.y[5][i], null);
				} else if( 45 <= stone.timer[5][i] && stone.timer[5][i] <= 60){
					stone.timer[5][i]++;
					canvas.drawBitmap( imgStone[5][3] , stone.x[5][i], stone.y[5][i], null);
				} else if( 60 <= stone.timer[5][i] && stone.timer[5][i] <= 75){
					stone.timer[5][i]++;
					canvas.drawBitmap( imgStone[5][4] , stone.x[5][i], stone.y[5][i], null);
				} else if( 75 <= stone.timer[5][i] && stone.timer[5][i] < 90){
					stone.timer[5][i]++;
					canvas.drawBitmap( imgStone[5][3] , stone.x[5][i], stone.y[5][i], null);
				} else if( 90 <= stone.timer[5][i] && stone.timer[5][i] < 105){
					stone.timer[5][i]++;
					canvas.drawBitmap( imgStone[5][2] , stone.x[5][i], stone.y[5][i], null);
				} else if( 105 <= stone.timer[5][i] && stone.timer[5][i] < 120){
					stone.timer[5][i]++;
					canvas.drawBitmap( imgStone[5][1] , stone.x[5][i], stone.y[5][i], null);
				}else if( stone.timer[5][i] >= 120){
					canvas.drawBitmap( imgStone[5][0] , stone.x[5][i], stone.y[5][i], null);
					stone.timer[5][i] = 0;
				}
				if((stone.y[5][i] + 50 * tune -5 <= monyako.y && monyako.y <= stone.y[5][i] + 50 * tune +5) && 
					monyako.x /10	< stone.x[5][i] && stone.x[5][i] < monyako.x *5 /4 ){
					stone.x[5][i] = displayX + 100 * tune;
					monyako.get = true;
					se.play(seGet, 1.0F, 1.0F, 0, 0, 1.0F);
					getStoneBlue();
					stone.kind[5][i] = false;
				}else if(stone.x[5][i] < -220 * tune){
					if( score.maxCombo  < score.combo){
						score.maxCombo = score.combo;
					}
					score.combo = 0;
					score.comboTimer = 0;
					stone.x[5][i] = displayX + 100 * tune;
					stone.kind[5][i] = false;
				}
					}
		}
	}
	
	public void getGet(Canvas canvas){
		
		if(monyako.get == true){
			
			getTimer++;
			if( 0 <= getTimer && getTimer <= 7){
				canvas.drawBitmap(effect[24] , monyako.x - 30 * tune , monyako.y - 30 * tune , null);	
			}else if( 7 <= getTimer && getTimer <= 14){
				canvas.drawBitmap(effect[25] , monyako.x - 30 * tune, monyako.y - 30 * tune , null);	
			}else if( 14 <= getTimer && getTimer <= 21){
				canvas.drawBitmap(effect[26] , monyako.x - 30 * tune , monyako.y - 30 * tune , null);	
			}else if( 21 <= getTimer && getTimer <= 28){
				canvas.drawBitmap(effect[24] , monyako.x - 30 * tune, monyako.y - 30 * tune , null);	
			}else if( 28 <= getTimer && getTimer <= 35){
				canvas.drawBitmap(effect[25] , monyako.x - 30 * tune , monyako.y - 30 * tune , null);	
			}else if( 35 <= getTimer && getTimer <= 42){
				canvas.drawBitmap(effect[26] , monyako.x - 30 * tune , monyako.y - 30 * tune , null);	
			}else{
				getTimer = 0;
				monyako.get = false;
			}
			
			
		}
	}
	
	public void getTrap(Canvas canvas){
		
		for( int i = 0; i <= 5; i++){
			if( trap.normalBlock[i] == false){
				trap.normalBlockX[i] = displayX + 100 * tune;
			}else if( trap.normalBlock[i] == true){
	
				trap.normalBlockX[i] = trap.normalBlockX[i] - moveSpeed;
				canvas.drawBitmap(trapNormal[0] , trap.normalBlockX[i], trap.normalBlockY[i], null);
				if(( trap.normalBlockY[i] + 70 * tune -5 <= monyako.y && monyako.y <=  trap.normalBlockY[i] +  70 * tune + 5  ) && 
				-10 * tune	< trap.normalBlockX[i] && trap.normalBlockX[i] < monyako.x *3 /2 ){
					death();
				}else if(trap.normalBlockX[i] < -220 * tune){
					trap.normalBlock[i] = false;
					trap.normalBlockX[i] = displayX;
				}
			}
		}
		for( int i = 6; i <= 11; i++){
			
		
			
			if( trap.normalBlock[i] == false){
				trap.normalBlockX[i] = displayX  + 100 * tune;
			} else if( trap.normalBlock[i] == true){
			
			
				trap.normalBlockX[i] = trap.normalBlockX[i] - moveSpeed;
				canvas.drawBitmap(trapNormal[1] , trap.normalBlockX[i], trap.normalBlockY[i], null);
				if(( trap.normalBlockY[i] + 70 * tune -5 <= monyako.y && monyako.y <=  trap.normalBlockY[i] +  70 * tune + 5  ) && 
				-10 * tune	< trap.normalBlockX[i] && trap.normalBlockX[i] < monyako.x *3 /2 ){
					death();
					
				}else if(trap.normalBlockX[i] < -220 * tune){
					trap.normalBlock[i] = false;
					trap.normalBlockX[i] = displayX;
				}
		
			}
		}
			
		for( int i = 0; i < 3; i++){
		
			
			//赤トラップ
			if( trap.kiraBlock[0][i] == false){
				trap.kiraBlockX[0][i] = displayX;
			} else if( trap.kiraBlock[0][i] == true){
		
				trap.kiraBlockX[0][i] = trap.kiraBlockX[0][i] - moveSpeed;
				if( 0 <= trapTimer[0][i] && trapTimer[0][i] <= 10){
					trapTimer[0][i]++;
					canvas.drawBitmap( trapKira[0][0] , trap.kiraBlockX[0][i], trap.kiraBlockY[0][i], null);
				} else if( 10 <= trapTimer[0][i] && trapTimer[0][i] < 20){
					trapTimer[0][i]++;
					canvas.drawBitmap( trapKira[0][1] , trap.kiraBlockX[0][i], trap.kiraBlockY[0][i], null);
				} else if( 20 <= trapTimer[0][i] && trapTimer[0][i] < 30){
					trapTimer[0][i]++;
					canvas.drawBitmap( trapKira[0][2] , trap.kiraBlockX[0][i], trap.kiraBlockY[0][i], null);
				} else if( 30 <= trapTimer[0][i] && trapTimer[0][i] < 40){
					trapTimer[0][i]++;
					canvas.drawBitmap( trapKira[0][3] , trap.kiraBlockX[0][i], trap.kiraBlockY[0][i], null);
				}  else if( trapTimer[0][i] >= 40){
					canvas.drawBitmap( trapKira[0][0] , trap.kiraBlockX[0][i], trap.kiraBlockY[0][i], null);
					trapTimer[0][i] = 0;
				}
				
				if(( trap.kiraBlockY[0][i] + 70 * tune -5 <= monyako.y && monyako.y <= trap.kiraBlockY[0][i] + 70 * tune + 5   )&& 
				-25 * tune	< trap.kiraBlockX[0][i] && trap.kiraBlockX[0][i] < monyako.x *3 /2  &&
				( monyako.breaker != Monyako.BREAK_RED && monyako.redState == false )){
					death();
				}else if(( trap.kiraBlockY[0][i] + 70 * tune -5 <= monyako.y && monyako.y <= trap.kiraBlockY[0][i] + 70 * tune + 5   ) && 
				-25 * tune	< trap.kiraBlockX[0][i] && trap.kiraBlockX[0][i] < monyako.x *3 /2  &&
				( monyako.breaker == Monyako.BREAK_RED && monyako.redState == false )){
					monyako.redState = true;
					monyako.effect = Monyako.RETURN_RED;
				
				}	else if(trap.kiraBlockX[0][i] < -220 * tune){
					trap.kiraBlock[0][i] = false;
					trap.kiraBlockX[0][i] = displayX;
					monyako.redState = false;
				}
		
		}
		
		//緑トラップ
		if( trap.kiraBlock[1][i] == false ){
		
			trap.kiraBlockX[1][i] = displayX;
		} else if(trap.kiraBlock[1][i] == true){
		
			trap.kiraBlockX[1][i] = trap.kiraBlockX[1][i] - moveSpeed;
			if( 0 <= trapTimer[1][i] && trapTimer[1][i] <= 10){
				trapTimer[1][i]++;
				canvas.drawBitmap( trapKira[1][0] , trap.kiraBlockX[1][i], trap.kiraBlockY[1][i], null);
		} else if( 10 <= trapTimer[1][i] && trapTimer[1][i] < 20){
			trapTimer[1][i]++;
			canvas.drawBitmap( trapKira[1][1] , trap.kiraBlockX[1][i], trap.kiraBlockY[1][i], null);
		}else if( 20 <= trapTimer[1][i] && trapTimer[1][i] < 30){
			trapTimer[1][i]++;
			canvas.drawBitmap( trapKira[1][2] , trap.kiraBlockX[1][i], trap.kiraBlockY[1][i], null);
		}else if( 30 <= trapTimer[1][i] && trapTimer[1][i] < 40){
			trapTimer[1][i]++;
			canvas.drawBitmap( trapKira[1][3] , trap.kiraBlockX[1][i], trap.kiraBlockY[1][i], null);
		} else if( trapTimer[1][i] == 40){
			canvas.drawBitmap( trapKira[1][0] , trap.kiraBlockX[1][i] , trap.kiraBlockY[1][i], null);
			trapTimer[1][i] = 0;
		}if(( trap.kiraBlockY[1][i] + 70 * tune -5 <= monyako.y && monyako.y <= trap.kiraBlockY[1][i] + 70 * tune + 5   )&& 
				-25 * tune	< trap.kiraBlockX[1][i] && trap.kiraBlockX[1][i] < monyako.x *3 /2  &&
		(monyako.breaker != Monyako.BREAK_GREEN && monyako.greenState == false  )){
			death();
		}else if(( trap.kiraBlockY[1][i] + 70 * tune -5 <= monyako.y && monyako.y <= trap.kiraBlockY[1][i] + 70 * tune + 5   ) && 
				-25 * tune	< trap.kiraBlockX[1][i] && trap.kiraBlockX[1][i] < monyako.x *3 /2  &&
				monyako.breaker == Monyako.BREAK_GREEN   && monyako.greenState == false ){
				monyako.greenState = true;
				monyako.effect = Monyako.RETURN_GREEN;
				
		
		}else if(trap.kiraBlockX[1][i] < -220 * tune){
			trap.kiraBlock[1][i] = false;
			trap.kiraBlockX[1][i] = displayX;
			monyako.greenState = false;
		}
		
	
		}
		
		//青トラップ
		if( trap.kiraBlock[2][i] == false){
			trap.kiraBlockX[2][i] = displayX;
		} else if(trap.kiraBlock[2][i] == true){
	
			trap.kiraBlockX[2][i] = trap.kiraBlockX[2][i] - moveSpeed;
			if( 0 <= trapTimer[2][i] && trapTimer[2][i] <= 10){
				trapTimer[2][i]++;
				canvas.drawBitmap( trapKira[2][0] , trap.kiraBlockX[2][i], trap.kiraBlockY[2][i], null);
			} else if( 10 <= trapTimer[2][i] && trapTimer[2][i] < 20){
				trapTimer[2][i]++;
				canvas.drawBitmap( trapKira[2][1] , trap.kiraBlockX[2][i] , trap.kiraBlockY[2][i], null);
			} else if( 20 <= trapTimer[2][i] && trapTimer[2][i] < 30){
				trapTimer[2][i]++;
				canvas.drawBitmap( trapKira[2][2] , trap.kiraBlockX[2][i] , trap.kiraBlockY[2][i], null);
			} else if( 30 <= trapTimer[2][i] && trapTimer[2][i] < 40){
				trapTimer[2][i]++;
				canvas.drawBitmap( trapKira[2][3] , trap.kiraBlockX[2][i] , trap.kiraBlockY[2][i], null);
			} else if( trapTimer[2][i] == 40){
				canvas.drawBitmap( trapKira[2][0] , trap.kiraBlockX[2][i] , trap.kiraBlockY[2][i], null);
				trapTimer[2][i] = 0;
			}
			
			if(( trap.kiraBlockY[2][i] + 70 * tune -5 <= monyako.y && monyako.y <= trap.kiraBlockY[2][i] + 70 * tune + 5   ) && 
				-25 * tune	< trap.kiraBlockX[2][i] && trap.kiraBlockX[2][i] < monyako.x *3 /2  &&
				(monyako.breaker !=Monyako.BREAK_BLUE   && monyako.blueState == false )){
				death();
			}else if(( trap.kiraBlockY[2][i] + 70 * tune -5 <= monyako.y && monyako.y <= trap.kiraBlockY[2][i] + 70 * tune + 5   ) && 
				-25 * tune	< trap.kiraBlockX[2][i] && trap.kiraBlockX[2][i] < monyako.x *3 /2  &&
				monyako.breaker == Monyako.BREAK_BLUE   && monyako.blueState == false){
				monyako.effect = Monyako.RETURN_BLUE;
				monyako.blueState = true;
				
			}else if(trap.kiraBlockX[2][i] < -220 * tune){
				trap.kiraBlock[2][i] = false;
				trap.kiraBlockX[2][i] = displayX;
				monyako.blueState = false;
			}
		}
		}
	}
	
	
	public void getUI(Canvas canvas){
		
		//ポーズアイコン
		canvas.drawBitmap(uiPauseIcon[1], 1080 * tune +10 * tune , 20 * tune , null);	
		if( touchState != TOUCH_PAUSE ){
			if(playState == PLAY_NORMAL){
				canvas.drawBitmap(uiPauseIcon[0], 1080 * tune , 10 * tune, null);	
				
			}
			if( faseState != 100){
			canvas.drawBitmap(uiPauseIcon[0], 1080 * tune, 10 * tune, null);	
			}
		} else if(touchState == TOUCH_PAUSE){
			canvas.drawBitmap(uiPauseIcon[1], 1080 * tune +10 * tune , 20 * tune , null);	
		} 
		
				//キラストーンストック表示
		
				canvas.drawBitmap(uiKirastone, 365 * tune, 0, null);
				switch(stone.leftStock){
				
					case Stone.STOCK_RED:
						canvas.drawBitmap(uiStone[0], 525 * tune, 5 * tune, null);
						
					break;
					
					case Stone.STOCK_GREEN:
						canvas.drawBitmap(uiStone[1], 525 * tune, 5 * tune, null);
						
					break;
					
					case Stone.STOCK_BLUE:
						canvas.drawBitmap(uiStone[2], 525 * tune, 5 * tune, null);
						
					break;
				
				}
				
				switch(stone.rightStock){
				
				case Stone.STOCK_RED:
					canvas.drawBitmap(uiStone[0], 650 * tune, 5 * tune, null);
					
				break;
				
				case Stone.STOCK_GREEN:
					canvas.drawBitmap(uiStone[1], 650 * tune, 5 * tune, null);
					
				break;
				
				case Stone.STOCK_BLUE:
					canvas.drawBitmap(uiStone[2], 650 * tune, 5 * tune, null);
					
				break;
			
			}
				//はしご残存数表示
			if( gameState == GAME_PLAY){
				canvas.drawBitmap(uiLadder , 10 * tune, 10 * tune, null);
				
				switch(ladder.count){
						
				case 4:
					canvas.drawBitmap(ladderRest[0] , 125 * tune, 15 * tune, null);
				break;
				
				case 3:
					canvas.drawBitmap(ladderRest[1] , 125 * tune, 15 * tune, null);
				break;
				
				case 2:
					canvas.drawBitmap(ladderRest[2] , 125 * tune, 15 * tune, null);
				break;
				
				case 1:
					canvas.drawBitmap(ladderRest[3] , 125 * tune, 15 * tune, null);
				break;
				
				case 0:
					canvas.drawBitmap(ladderRest[4] , 125 * tune, 15 * tune, null);
				break;
				
				}
			}else if ( gameState == GAME_TUTORIAL && tutorialState != FASE_KIRA){
				canvas.drawBitmap(skip , 10 * tune, 10 * tune, null);
			}
			
	}
	
	public void getBackGround(Canvas canvas){
		if( backgroundX[0] >= -backgroundX[2]){
			backgroundX[0] = backgroundX[0] - moveSpeed / 2;
			canvas.drawBitmap(playBackground ,backgroundX[0], 0, null);
		} else if( backgroundX[0] <= -backgroundX[2]){
			backgroundX[0] = backgroundX[2]- 10;
			backgroundX[0] = backgroundX[0] - moveSpeed / 2;
			canvas.drawBitmap(playBackground ,backgroundX[0], 0, null);
		}
		if( backgroundX[1] >= -backgroundX[2]){
			backgroundX[1] = backgroundX[1] - moveSpeed / 2;
			canvas.drawBitmap(playBackground ,backgroundX[1], 0, null);
		} else if( backgroundX[1] <= -backgroundX[2] ){
			backgroundX[1] = backgroundX[2]- 10;
			backgroundX[1] = backgroundX[1] - moveSpeed / 2;
			canvas.drawBitmap(playBackground ,backgroundX[1], 0, null);
		}

		
		
	}
	
	
	public void getGround(Canvas canvas){
		
		
		//地面
		if( groundX[0] >= -groundX[2]){
				groundX[0] = groundX[0] - moveSpeed ;
				canvas.drawBitmap(ground ,groundX[0], 0, null);
			} else if( groundX[0] <= -groundX[2]){
				groundX[0] = groundX[2] - 10;
				groundX[0] = groundX[0] - moveSpeed ;
			canvas.drawBitmap(ground ,groundX[0], 0, null);
		}
		if( groundX[1] >= -groundX[2]){
			groundX[1] = groundX[1] - moveSpeed;
			canvas.drawBitmap(ground ,groundX[1], 0, null);
		} else if( groundX[1] <= -groundX[2]){
			groundX[1] = groundX[2] - 10;
			groundX[1] = groundX[1] - moveSpeed;
			canvas.drawBitmap(ground ,groundX[1], 0, null);
		}
		
		
	}
	
	public void titleLoad(){
		titleBackground = BitmapFactory.decodeResource(res, R.drawable.title_background);
		titleBackground = Bitmap.createScaledBitmap(titleBackground , displayX , displayY, true);
		titleLogo = BitmapFactory.decodeResource(res, R.drawable.title_logo);
		titleStart[0] = BitmapFactory.decodeResource(res, R.drawable.title_start1);
		titleStart[1] = BitmapFactory.decodeResource(res, R.drawable.title_start2);
		
		
	}
	
	public void tutorialLoad(){
		skipWindow = BitmapFactory.decodeResource(res, R.drawable.skip_window);
		reviNormal = BitmapFactory.decodeResource(res, R.drawable.revi_normal);
		reviSad = BitmapFactory.decodeResource(res, R.drawable.revi_sad);
		reviShock = BitmapFactory.decodeResource(res, R.drawable.revi_shock);
		reviSmile = BitmapFactory.decodeResource(res, R.drawable.revi_smile);
		textWindow[0] = BitmapFactory.decodeResource(res, R.drawable.text_window1);
		textWindow[1] = BitmapFactory.decodeResource(res, R.drawable.text_window2);
		strong = BitmapFactory.decodeResource(res, R.drawable.strong);
		thisTouch = BitmapFactory.decodeResource(res, R.drawable.this_touch);
		finger = BitmapFactory.decodeResource(res, R.drawable.finger);
		it = BitmapFactory.decodeResource(res, R.drawable.it);
		text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_stone1_1);
		text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_stone1_2);
		text[2] = BitmapFactory.decodeResource(res, R.drawable.fase_stone1_3);
		
	}
	
	public void get3text(Canvas canvas){
		if( 100 <= textTimer && textTimer <= 250){
			canvas.drawBitmap( text[0] , 260 * tune , 130 * tune, null);
		} else if( 250 <= textTimer && textTimer <= 400){
			canvas.drawBitmap( text[0] , 260 * tune , 130 * tune, null);
			canvas.drawBitmap( text[1] , 260 * tune , 180 * tune, null);
		} else if( 400 <= textTimer && textTimer < 600){
			canvas.drawBitmap( text[0] , 260 * tune , 130 * tune, null);
			canvas.drawBitmap( text[1] , 260 * tune , 180 * tune, null);
			canvas.drawBitmap( text[2] , 260 * tune , 230 * tune, null);
			
		}
		
	}
	public void tutorialLoadClear(){
		skipWindow = null;
		reviNormal = null;
		reviSad = null;
		reviShock = null;
		reviSmile = null;
		textWindow[0] =  null;
		textWindow[1] = null;
		strong = null;
		finger = null;
		it = null;
		
		thisTouch = null;
		for(int i = 0 ; i < 3 ; i++){
			text[i] = null;
		}
		
	}
	
	public void playLoad(){
	//プレイ
		playStart = BitmapFactory.decodeResource(res, R.drawable.start);
		playBackground = BitmapFactory.decodeResource(res, R.drawable.play_background);
		ground = BitmapFactory.decodeResource(res, R.drawable.play_ground);
		speedUp[0]  = BitmapFactory.decodeResource(res, R.drawable.speed_up1);
		
		playBackground = Bitmap.createScaledBitmap(playBackground ,displayX + backTune , displayY, true);
		ground = Bitmap.createScaledBitmap(ground ,displayX + backTune  , displayY, true);
		//白
		monyakoWalk[0]  = BitmapFactory.decodeResource(res, R.drawable.walk1);
		monyakoWalk[1]  = BitmapFactory.decodeResource(res, R.drawable.walk2);
		monyakoWalk[2]  = BitmapFactory.decodeResource(res, R.drawable.walk3);
		monyakoWalk[3]  = BitmapFactory.decodeResource(res, R.drawable.walk4);
		monyakoLadder[0]  = BitmapFactory.decodeResource(res, R.drawable.climb1);
		monyakoLadder[1]  = BitmapFactory.decodeResource(res, R.drawable.climb2);
		monyakoLadder[2]  = BitmapFactory.decodeResource(res, R.drawable.climb3);
		monyakoLadder[3]  = BitmapFactory.decodeResource(res, R.drawable.climb4);
		//赤
		monyakoWalk[4]  = BitmapFactory.decodeResource(res, R.drawable.walk_red1);
		monyakoWalk[5]  = BitmapFactory.decodeResource(res, R.drawable.walk_red2);
		monyakoWalk[6]  = BitmapFactory.decodeResource(res, R.drawable.walk_red3);
		monyakoWalk[7]  = BitmapFactory.decodeResource(res, R.drawable.walk_red4);
		monyakoLadder[4]  = BitmapFactory.decodeResource(res, R.drawable.climb_red1);
		monyakoLadder[5]  = BitmapFactory.decodeResource(res, R.drawable.climb_red2);
		monyakoLadder[6]  = BitmapFactory.decodeResource(res, R.drawable.climb_red3);
		monyakoLadder[7]  = BitmapFactory.decodeResource(res, R.drawable.climb_red4);
		effect[0] = BitmapFactory.decodeResource(res, R.drawable.effect_red1);
		effect[1] = BitmapFactory.decodeResource(res, R.drawable.effect_red2);
		effect[2] = BitmapFactory.decodeResource(res, R.drawable.effect_red3);
		effect[3] = BitmapFactory.decodeResource(res, R.drawable.effect_red4);
		effect[12] = BitmapFactory.decodeResource(res, R.drawable.effect_return_red1);
		effect[13] = BitmapFactory.decodeResource(res, R.drawable.effect_return_red2);
		effect[14] = BitmapFactory.decodeResource(res, R.drawable.effect_return_red3);
		effect[15] = BitmapFactory.decodeResource(res, R.drawable.effect_return_red1);
		
		//緑
		monyakoWalk[8]  = BitmapFactory.decodeResource(res, R.drawable.walk_green1);
		monyakoWalk[9]  = BitmapFactory.decodeResource(res, R.drawable.walk_green2);
		monyakoWalk[10]  = BitmapFactory.decodeResource(res, R.drawable.walk_green3);
		monyakoWalk[11]  = BitmapFactory.decodeResource(res, R.drawable.walk_green4);
		monyakoLadder[8]  = BitmapFactory.decodeResource(res, R.drawable.climb_green1);
		monyakoLadder[9]  = BitmapFactory.decodeResource(res, R.drawable.climb_green2);
		monyakoLadder[10]  = BitmapFactory.decodeResource(res, R.drawable.climb_green3);
		monyakoLadder[11]  = BitmapFactory.decodeResource(res, R.drawable.climb_green4);
		effect[4] = BitmapFactory.decodeResource(res, R.drawable.effect_green1);
		effect[5] = BitmapFactory.decodeResource(res, R.drawable.effect_green2);
		effect[6] = BitmapFactory.decodeResource(res, R.drawable.effect_green3);
		effect[7] = BitmapFactory.decodeResource(res, R.drawable.effect_green4);
		effect[16] = BitmapFactory.decodeResource(res, R.drawable.effect_return_green1);
		effect[17] = BitmapFactory.decodeResource(res, R.drawable.effect_return_green2);
		effect[18] = BitmapFactory.decodeResource(res, R.drawable.effect_return_green3);
		effect[19] = BitmapFactory.decodeResource(res, R.drawable.effect_return_green1);
		
		//青
		monyakoWalk[12]  = BitmapFactory.decodeResource(res, R.drawable.walk_blue1);
		monyakoWalk[13]  = BitmapFactory.decodeResource(res, R.drawable.walk_blue2);
		monyakoWalk[14]  = BitmapFactory.decodeResource(res, R.drawable.walk_blue3);
		monyakoWalk[15]  = BitmapFactory.decodeResource(res, R.drawable.walk_blue4);
		monyakoLadder[12] = BitmapFactory.decodeResource(res, R.drawable.climb_blue1);
		monyakoLadder[13]  = BitmapFactory.decodeResource(res, R.drawable.climb_blue2);
		monyakoLadder[14]  = BitmapFactory.decodeResource(res, R.drawable.climb_blue3);
		monyakoLadder[15]  = BitmapFactory.decodeResource(res, R.drawable.climb_blue4);
		effect[8] = BitmapFactory.decodeResource(res, R.drawable.effect_blue1);
		effect[9] = BitmapFactory.decodeResource(res, R.drawable.effect_blue2);
		effect[10] = BitmapFactory.decodeResource(res, R.drawable.effect_blue3);
		effect[11] = BitmapFactory.decodeResource(res, R.drawable.effect_blue4);
		effect[20] = BitmapFactory.decodeResource(res, R.drawable.effect_return_blue1);
		effect[21] = BitmapFactory.decodeResource(res, R.drawable.effect_return_blue2);
		effect[22] = BitmapFactory.decodeResource(res, R.drawable.effect_return_blue3);
		effect[23] = BitmapFactory.decodeResource(res, R.drawable.effect_return_blue1);
		
		
		monyakoDeath[0]  = BitmapFactory.decodeResource(res, R.drawable.monyako_death1);
		monyakoDeath[1]  = BitmapFactory.decodeResource(res, R.drawable.monyako_death2);
		monyakoDeath[2]  = BitmapFactory.decodeResource(res, R.drawable.monyako_death3);
		monyakoDeath[3]  = BitmapFactory.decodeResource(res, R.drawable.monyako_death4);
		monyakoDeath[4]  = BitmapFactory.decodeResource(res, R.drawable.monyako_death5);
		monyakoDeath[5]  = BitmapFactory.decodeResource(res, R.drawable.monyako_death6);
		
		
		imgLadder = BitmapFactory.decodeResource(res, R.drawable.ladder);
		effect[24] = BitmapFactory.decodeResource(res, R.drawable.effect_get1);
		effect[25] = BitmapFactory.decodeResource(res, R.drawable.effect_get2);
		effect[26] = BitmapFactory.decodeResource(res, R.drawable.effect_get3);
		
		//ストーン
		imgStone[0][0] = BitmapFactory.decodeResource(res, R.drawable.stone_low1);
		imgStone[0][1] = BitmapFactory.decodeResource(res, R.drawable.stone_low2);
		imgStone[0][2] = BitmapFactory.decodeResource(res, R.drawable.stone_low3);
		imgStone[0][3] = BitmapFactory.decodeResource(res, R.drawable.stone_low4);
		imgStone[0][4] = BitmapFactory.decodeResource(res, R.drawable.stone_low5);
		
		imgStone[1][0] = BitmapFactory.decodeResource(res, R.drawable.stone_middle1);
		imgStone[1][1] = BitmapFactory.decodeResource(res, R.drawable.stone_middle2);
		imgStone[1][2] = BitmapFactory.decodeResource(res, R.drawable.stone_middle3);
		imgStone[1][3] = BitmapFactory.decodeResource(res, R.drawable.stone_middle4);
		imgStone[1][4] = BitmapFactory.decodeResource(res, R.drawable.stone_middle5);
		
		imgStone[2][0] = BitmapFactory.decodeResource(res, R.drawable.stone_high1);
		imgStone[2][1] = BitmapFactory.decodeResource(res, R.drawable.stone_high2);
		imgStone[2][2] = BitmapFactory.decodeResource(res, R.drawable.stone_high3);
		imgStone[2][3] = BitmapFactory.decodeResource(res, R.drawable.stone_high4);
		imgStone[2][4] = BitmapFactory.decodeResource(res, R.drawable.stone_high5);
		
		imgStone[3][0] = BitmapFactory.decodeResource(res, R.drawable.stone_red1);
		imgStone[3][1] = BitmapFactory.decodeResource(res, R.drawable.stone_red2);
		imgStone[3][2] = BitmapFactory.decodeResource(res, R.drawable.stone_red3);
		imgStone[3][3] = BitmapFactory.decodeResource(res, R.drawable.stone_red4);
		imgStone[3][4] = BitmapFactory.decodeResource(res, R.drawable.stone_red5);
		
		imgStone[4][0] = BitmapFactory.decodeResource(res, R.drawable.stone_green1);
		imgStone[4][1] = BitmapFactory.decodeResource(res, R.drawable.stone_green2);
		imgStone[4][2] = BitmapFactory.decodeResource(res, R.drawable.stone_green3);
		imgStone[4][3] = BitmapFactory.decodeResource(res, R.drawable.stone_green4);
		imgStone[4][4] = BitmapFactory.decodeResource(res, R.drawable.stone_green5);
		
		imgStone[5][0] = BitmapFactory.decodeResource(res, R.drawable.stone_blue1);
		imgStone[5][1] = BitmapFactory.decodeResource(res, R.drawable.stone_blue2);
		imgStone[5][2] = BitmapFactory.decodeResource(res, R.drawable.stone_blue3);
		imgStone[5][3] = BitmapFactory.decodeResource(res, R.drawable.stone_blue4);
		imgStone[5][4] = BitmapFactory.decodeResource(res, R.drawable.stone_blue5);
		
		
		//UIアイコン
		ladderRest[0] = BitmapFactory.decodeResource(res, R.drawable.ladder_rest0);
		ladderRest[1] = BitmapFactory.decodeResource(res, R.drawable.ladder_rest1);
		ladderRest[2] = BitmapFactory.decodeResource(res, R.drawable.ladder_rest2);
		ladderRest[3] = BitmapFactory.decodeResource(res, R.drawable.ladder_rest3);
		ladderRest[4] = BitmapFactory.decodeResource(res, R.drawable.ladder_rest4);
		uiLadder = BitmapFactory.decodeResource(res, R.drawable.ui_ladder);
		uiKirastone = BitmapFactory.decodeResource(res, R.drawable.ui_kirastone);
		uiStone[0] = BitmapFactory.decodeResource(res, R.drawable.ui_stone_red);
		uiStone[1] = BitmapFactory.decodeResource(res, R.drawable.ui_stone_green);
		uiStone[2] = BitmapFactory.decodeResource(res, R.drawable.ui_stone_blue);
		uiPauseIcon[0] = BitmapFactory.decodeResource(res, R.drawable.play_pause_icon1);
		uiPauseIcon[1] = BitmapFactory.decodeResource(res, R.drawable.play_pause_icon2);
		uiPauseWindow = BitmapFactory.decodeResource(res, R.drawable.play_pause_window);
		uiContinueIcon[0] = BitmapFactory.decodeResource(res, R.drawable.play_continue1);
		uiContinueIcon[1] = BitmapFactory.decodeResource(res, R.drawable.play_continue2);
		uiEndIcon[0] = BitmapFactory.decodeResource(res, R.drawable.play_quite1);
		uiEndIcon[1] = BitmapFactory.decodeResource(res, R.drawable.play_quite2);
		
		//トラップ
		trapNormal[0] = BitmapFactory.decodeResource(res, R.drawable.trap1);
		trapNormal[1] = BitmapFactory.decodeResource(res, R.drawable.trap2);
		trapKira[0][0] = BitmapFactory.decodeResource(res, R.drawable.trap_red1);
		trapKira[0][1] = BitmapFactory.decodeResource(res, R.drawable.trap_red2);
		trapKira[0][2] = BitmapFactory.decodeResource(res, R.drawable.trap_red3);
		trapKira[0][3] = BitmapFactory.decodeResource(res, R.drawable.trap_red4);
		trapKira[1][0] = BitmapFactory.decodeResource(res, R.drawable.trap_green1);
		trapKira[1][1] = BitmapFactory.decodeResource(res, R.drawable.trap_green2);
		trapKira[1][2] = BitmapFactory.decodeResource(res, R.drawable.trap_green3);
		trapKira[1][3] = BitmapFactory.decodeResource(res, R.drawable.trap_green4);
		trapKira[2][0] = BitmapFactory.decodeResource(res, R.drawable.trap_blue1);
		trapKira[2][1] = BitmapFactory.decodeResource(res, R.drawable.trap_blue2);
		trapKira[2][2] = BitmapFactory.decodeResource(res, R.drawable.trap_blue3);
		trapKira[2][3] = BitmapFactory.decodeResource(res, R.drawable.trap_blue4);

		
		//ゲームオーバー
		if( score.endlessMode == false){
		gameOver = BitmapFactory.decodeResource(res, R.drawable.game_over);
		touchHome[0] = BitmapFactory.decodeResource(res, R.drawable.touchhome1);
		touchHome[1] = BitmapFactory.decodeResource(res, R.drawable.touchhome2);
		}
		
		//ゴール
		if( score.endlessMode == false){
		gate[0]= BitmapFactory.decodeResource(res, R.drawable.gate1);
		gate[1]= BitmapFactory.decodeResource(res, R.drawable.gate2);
		gate[2]= BitmapFactory.decodeResource(res, R.drawable.gate3);
		gate[3]= BitmapFactory.decodeResource(res, R.drawable.gate4);
		revi[0]= BitmapFactory.decodeResource(res, R.drawable.revi1);
		revi[1]= BitmapFactory.decodeResource(res, R.drawable.revi2);
		revi[2]= BitmapFactory.decodeResource(res, R.drawable.revi3);
		revi[3]= BitmapFactory.decodeResource(res, R.drawable.revi4);
		//クリア
		clear[0] = BitmapFactory.decodeResource(res, R.drawable.clear1);
		clear[1] = BitmapFactory.decodeResource(res, R.drawable.clear2);
		clear[2] = BitmapFactory.decodeResource(res, R.drawable.clear3);
		}
		
		
		//フィニッシュ
		if( score.endlessMode == true){
			finish[0] = BitmapFactory.decodeResource(res, R.drawable.finish1);
			finish[1] = BitmapFactory.decodeResource(res, R.drawable.finish2);
			finish[2] = BitmapFactory.decodeResource(res, R.drawable.finish3);
		}
		//コンボ
		comboText[0] = BitmapFactory.decodeResource(res, R.drawable.combo_text1);
		comboText[1] = BitmapFactory.decodeResource(res, R.drawable.combo_text2);
		comboText[2] = BitmapFactory.decodeResource(res, R.drawable.combo_text3);
		
		comboNum[0][0]= BitmapFactory.decodeResource(res, R.drawable.combo00);
		comboNum[0][1]= BitmapFactory.decodeResource(res, R.drawable.combo01);
		comboNum[0][2]= BitmapFactory.decodeResource(res, R.drawable.combo02);
		comboNum[0][3]= BitmapFactory.decodeResource(res, R.drawable.combo03);
		comboNum[0][4]= BitmapFactory.decodeResource(res, R.drawable.combo04);
		comboNum[0][5]= BitmapFactory.decodeResource(res, R.drawable.combo05);
		comboNum[0][6]= BitmapFactory.decodeResource(res, R.drawable.combo06);
		comboNum[0][7]= BitmapFactory.decodeResource(res, R.drawable.combo07);
		comboNum[0][8]= BitmapFactory.decodeResource(res, R.drawable.combo08);
		comboNum[0][9]= BitmapFactory.decodeResource(res, R.drawable.combo09);
		comboNum[1][0]= BitmapFactory.decodeResource(res, R.drawable.combo10);
		comboNum[1][1]= BitmapFactory.decodeResource(res, R.drawable.combo11);
		comboNum[1][2]= BitmapFactory.decodeResource(res, R.drawable.combo12);
		comboNum[1][3]= BitmapFactory.decodeResource(res, R.drawable.combo13);
		comboNum[1][4]= BitmapFactory.decodeResource(res, R.drawable.combo14);
		comboNum[1][5]= BitmapFactory.decodeResource(res, R.drawable.combo15);
		comboNum[1][6]= BitmapFactory.decodeResource(res, R.drawable.combo16);
		comboNum[1][7]= BitmapFactory.decodeResource(res, R.drawable.combo17);
		comboNum[1][8]= BitmapFactory.decodeResource(res, R.drawable.combo18);
		comboNum[1][9]= BitmapFactory.decodeResource(res, R.drawable.combo19);
		comboNum[2][0]= BitmapFactory.decodeResource(res, R.drawable.combo20);
		comboNum[2][1]= BitmapFactory.decodeResource(res, R.drawable.combo21);
		comboNum[2][2]= BitmapFactory.decodeResource(res, R.drawable.combo22);
		comboNum[2][3]= BitmapFactory.decodeResource(res, R.drawable.combo23);
		comboNum[2][4]= BitmapFactory.decodeResource(res, R.drawable.combo24);
		comboNum[2][5]= BitmapFactory.decodeResource(res, R.drawable.combo25);
		comboNum[2][6]= BitmapFactory.decodeResource(res, R.drawable.combo26);
		comboNum[2][7]= BitmapFactory.decodeResource(res, R.drawable.combo27);
		comboNum[2][8]= BitmapFactory.decodeResource(res, R.drawable.combo28);
		comboNum[2][9]= BitmapFactory.decodeResource(res, R.drawable.combo29);

		
	}
	public void menuLoad(){
		
		menuBackground[0] = BitmapFactory.decodeResource(res, R.drawable.menu_background1);
		menuBackground[1] = BitmapFactory.decodeResource(res, R.drawable.menu_background2);
		menuBackground[0] = Bitmap.createScaledBitmap(menuBackground[0] ,displayX , displayY, true);
		menuBackground[1] = Bitmap.createScaledBitmap(menuBackground[1] ,displayX , displayY, true);
		menuAdventure[0] = BitmapFactory.decodeResource(res, R.drawable.menu_adventure1);
		menuAdventure[1] = BitmapFactory.decodeResource(res, R.drawable.menu_adventure2);
		menuTutorial[0] = BitmapFactory.decodeResource(res, R.drawable.menu_tutorial1);
		menuTutorial[1] = BitmapFactory.decodeResource(res, R.drawable.menu_tutorial2);
		menuScore[0] = BitmapFactory.decodeResource(res, R.drawable.menu_score1);
		menuScore[1] = BitmapFactory.decodeResource(res, R.drawable.menu_score2);
		menuBar = BitmapFactory.decodeResource(res, R.drawable.menu_homebar);
		menuEnd[0] = BitmapFactory.decodeResource(res, R.drawable.menu_end1);
		menuEnd[1] = BitmapFactory.decodeResource(res, R.drawable.menu_end2);
		menuOption[0] = BitmapFactory.decodeResource(res, R.drawable.menu_option1);
		menuOption[1] = BitmapFactory.decodeResource(res, R.drawable.menu_option2);
		scoreDelete  = BitmapFactory.decodeResource(res, R.drawable.score_delete);
		
		
		//モード選択
		modeWindow= BitmapFactory.decodeResource(res, R.drawable.mode_window);
		normalMode[0]= BitmapFactory.decodeResource(res, R.drawable.normal_icon1);
		normalMode[1]= BitmapFactory.decodeResource(res, R.drawable.normal_icon2);
		endlessIcon[0]= BitmapFactory.decodeResource(res, R.drawable.endless_icon1);
		endlessIcon[1]= BitmapFactory.decodeResource(res, R.drawable.endless_icon2);
		endlessIcon[2]= BitmapFactory.decodeResource(res, R.drawable.endless_icon3);
		endlessIcon[3]= BitmapFactory.decodeResource(res, R.drawable.endless_icon4);
								
		
		//オプション
		optionWindow = BitmapFactory.decodeResource(res, R.drawable.option_window);
		closeIcon[0] = BitmapFactory.decodeResource(res, R.drawable.close_icon1);
		closeIcon[1] = BitmapFactory.decodeResource(res, R.drawable.close_icon2);
		creditView[0] = BitmapFactory.decodeResource(res, R.drawable.credit_view1);
		creditView[1] = BitmapFactory.decodeResource(res, R.drawable.credit_view2);
		optionOn[0] = BitmapFactory.decodeResource(res, R.drawable.option_on1);
		optionOn[1] = BitmapFactory.decodeResource(res, R.drawable.option_on2);
		optionOff[0] = BitmapFactory.decodeResource(res, R.drawable.option_off1);
		optionOff[1] = BitmapFactory.decodeResource(res, R.drawable.option_off2);
		
		//スコア
		scoreBoard[0] = BitmapFactory.decodeResource(res, R.drawable.score_normal);
		scoreBoard[1] = BitmapFactory.decodeResource(res, R.drawable.score_endless);
		scoreChange[0] = BitmapFactory.decodeResource(res, R.drawable.score_change1);
		scoreChange[1] = BitmapFactory.decodeResource(res, R.drawable.score_change2);

		first = BitmapFactory.decodeResource(res, R.drawable.score_1st);
		second = BitmapFactory.decodeResource(res, R.drawable.score_2nd);
		third = BitmapFactory.decodeResource(res, R.drawable.score_3rd);
		scoreNum[0] = BitmapFactory.decodeResource(res, R.drawable.score_0);
		scoreNum[1] = BitmapFactory.decodeResource(res, R.drawable.score_1);
		scoreNum[2] = BitmapFactory.decodeResource(res, R.drawable.score_2);
		scoreNum[3] = BitmapFactory.decodeResource(res, R.drawable.score_3);
		scoreNum[4] = BitmapFactory.decodeResource(res, R.drawable.score_4);
		scoreNum[5] = BitmapFactory.decodeResource(res, R.drawable.score_5);
		scoreNum[6] = BitmapFactory.decodeResource(res, R.drawable.score_6);
		scoreNum[7] = BitmapFactory.decodeResource(res, R.drawable.score_7);
		scoreNum[8] = BitmapFactory.decodeResource(res, R.drawable.score_8);
		scoreNum[9] = BitmapFactory.decodeResource(res, R.drawable.score_9);
		homeKira = BitmapFactory.decodeResource(res, R.drawable.kira_home);
		
		//クレジット
		creditWindow = BitmapFactory.decodeResource(res, R.drawable.credit_window);
		credit[0] = BitmapFactory.decodeResource(res, R.drawable.credit1);
		credit[1] = BitmapFactory.decodeResource(res, R.drawable.credit2);
		credit[2] = BitmapFactory.decodeResource(res, R.drawable.credit3);
		credit[3] = BitmapFactory.decodeResource(res, R.drawable.credit4);
		credit[4] = BitmapFactory.decodeResource(res, R.drawable.credit5);
		credit[5] = BitmapFactory.decodeResource(res, R.drawable.credit6);
		credit[6] = BitmapFactory.decodeResource(res, R.drawable.credit7);
		credit[7] = BitmapFactory.decodeResource(res, R.drawable.credit8);
		credit[8] = BitmapFactory.decodeResource(res, R.drawable.credit9);
		credit[9] = BitmapFactory.decodeResource(res, R.drawable.credit_team);

		
		//噴出し関連
		voice = BitmapFactory.decodeResource(res, R.drawable.voice);
		word[0] = BitmapFactory.decodeResource(res, R.drawable.word_adventure1);
		word[1] = BitmapFactory.decodeResource(res, R.drawable.word_adventure2);
		word[2] = BitmapFactory.decodeResource(res, R.drawable.word_score1);
		word[3] = BitmapFactory.decodeResource(res, R.drawable.word_score2);
		word[4] = BitmapFactory.decodeResource(res, R.drawable.word_tutorial);
		
	}
	
	//メニュー画面の画像解放処理
	public void menuLoadClear(){
		
		first = null;
		second = null;
		third = null;
		homeKira =  null;
		creditWindow  = null;
		menuBar =  null;
		modeWindow = null;
		optionWindow =  null;
		scoreDelete = null;
		voice = null;
		
		for(int i = 0 ; i < 2 ; i++){
			menuBackground[i] =  null;
			menuAdventure[i] =  null;
			menuEnd[i] = null;
			menuOption[i] = null;
			normalMode[i] = null;
			closeIcon[i] = null;
			creditView[i] = null;
			scoreChange[i] = null;
			menuTutorial[i] =  null;
			menuScore[i] =  null;
			optionOn[i] =   null;
			optionOff[i] =  null;
			scoreBoard[i] =  null;
		}
		for( int i = 0; i < 4; i++){
			endlessIcon[i] = null;
		}
		for(int i = 0 ; i < 5 ; i++){
			word[i] =  null;
		}
		for(int i = 0 ; i < 10 ; i++){
			credit[i] = null;
			scoreNum[i] = null;
		}
		
	}
	
	//スキップ画面 Canvas
	public void tutorialSkip(Canvas canvas){
		moveSpeed = 0;
		canvas.drawBitmap(skipWindow , 340 * tune, 150 * tune, null);
		   if( touchState != TOUCH_SKIP_YES ){
		    canvas.drawBitmap(checkYes[0] , 450 * tune, 310 * tune, null); 
		   } else if( touchState == TOUCH_SKIP_YES ){
			    canvas.drawBitmap(checkYes[1] , 460 * tune, 320 * tune, null); 
		   }
		   if( touchState != TOUCH_SKIP_NO ){
			   if( 5 > noTimer && noTimer >= 1){
			    	noTimer++;
					
				}else if(noTimer ==5){
					noTimer = 0;
				}
			   canvas.drawBitmap(checkNo[0] , 670 * tune, 310 * tune, null);
		   } else if( touchState == TOUCH_SKIP_NO ){
			   canvas.drawBitmap(checkNo[1] , 680 * tune, 320 * tune, null);
		   }
	}
	
	//ポーズ画面 Canvas
	public void gamePause(Canvas canvas){
		moveSpeed = 0;
		Paint pauseBlack = new Paint();
		pauseBlack.setColor(Color.argb(200, 0, 0, 0));
		Rect pauseRect = new Rect(0, 0, displayX, displayY);
		canvas.drawRect(pauseRect, pauseBlack);
		canvas.drawBitmap(uiPauseWindow, 340 * tune, 150 * tune, null);
		if( touchState != TOUCH_CONTINUE){
			canvas.drawBitmap(uiContinueIcon[0], 450 * tune, 310 * tune, null);
		}else if( touchState == TOUCH_CONTINUE){
			canvas.drawBitmap(uiContinueIcon[1], 460 * tune, 320 * tune, null);
		}
		
		if( touchState != TOUCH_QUITE){
			canvas.drawBitmap(uiEndIcon[0], 670 * tune, 310 * tune, null);
		}else if( touchState == TOUCH_QUITE){
			canvas.drawBitmap(uiEndIcon[1], 680 * tune, 320 * tune, null);
		}

		if(  5  <= fadeTimer &&  fadeTimer < 255){
			fadeTimer = fadeTimer + 5;
			Paint black = new Paint();
			black.setColor(Color.argb(fadeTimer, 0, 0, 0));
			Rect rect = new Rect(0, 0, displayX, displayY);
			canvas.drawRect(rect, black);
		}else if(fadeTimer == 255){
			Paint black = new Paint();
			black.setColor(Color.argb(fadeTimer, 0, 0, 0));
			Rect rect = new Rect(0, 0, displayX, displayY);
			canvas.drawRect(rect, black);
		
			
			menuState = MENU_NORMAL;
			faseState = NOT;
			gameState = GAME_MENU;
			fadeCheck = false;
			fadeTimer = 0;
			setPlayDefault();
		
		}
	}
	//もにゃこの動きに関するメソッド
	public void monyakoAction(){
		
		switch(monyako.state){
		
		case Monyako.MONYAKO_WALK:
			 
			moveSpeed = speedDifficulty;
			//ノーマルモードはゆっくりめ
			if( score.endlessMode == false){
				if( moveSpeed == SPEED_EASY){	
					monyako.walkTimer++;
				} else if( moveSpeed == SPEED_NORMAL){	
					monyako.walkTimer += 6;
				} else if( moveSpeed == SPEED_HARD){	
					monyako.walkTimer+= 12;
				}
			//エンドレスモードは旧仕様の速度
			} else if(score.endlessMode == true){
				if( moveSpeed == SPEED_ENDLESS_EASY){	
					monyako.walkTimer++;
				} else if( moveSpeed == SPEED_ENDLESS_NORMAL){	
					monyako.walkTimer += 6;
				} else if( moveSpeed == SPEED_ENDLESS_HARD){	
					monyako.walkTimer+= 12;
				}
				
			}
			
			break;
			
		case Monyako.MONYAKO_UP:
			monyako.ladderTimer++;
			monyako.y = monyako.y - 5 * tune;
			ladder.speed++;
			break;
			
		case Monyako.MONYAKO_DOWN:
			monyako.ladderTimer++;
			monyako.y = monyako.y + 5 * tune;
			ladder.speed++;
			break;
			
			
		}
	}
	
	public void loadStateLoad(){
		loading[0] = BitmapFactory.decodeResource(res, R.drawable.load1);
		loading[1] = BitmapFactory.decodeResource(res, R.drawable.load2);
		loading[2] = BitmapFactory.decodeResource(res, R.drawable.load3);
		loading[3] = BitmapFactory.decodeResource(res, R.drawable.load4);
		 switch(tips){
		    
		    case 0 :
		    	loadTips[0]  = BitmapFactory.decodeResource(res, R.drawable.tips_hunter);
		    	revi[0]= BitmapFactory.decodeResource(res, R.drawable.revi1);
				revi[1]= BitmapFactory.decodeResource(res, R.drawable.revi2);
				revi[2]= BitmapFactory.decodeResource(res, R.drawable.revi3);
				revi[3]= BitmapFactory.decodeResource(res, R.drawable.revi4);
		    	break;
		    case 1 :
		    	loadTips[1]  = BitmapFactory.decodeResource(res, R.drawable.tips_monyako);
		    	monyakoWalk[0]  = BitmapFactory.decodeResource(res, R.drawable.walk1);
				monyakoWalk[1]  = BitmapFactory.decodeResource(res, R.drawable.walk2);
				monyakoWalk[2]  = BitmapFactory.decodeResource(res, R.drawable.walk3);
				monyakoWalk[3]  = BitmapFactory.decodeResource(res, R.drawable.walk4);
		    	break;
		    case 2 :
		    	loadTips[2]  = BitmapFactory.decodeResource(res, R.drawable.tips_revi);
		    	revi[0]= BitmapFactory.decodeResource(res, R.drawable.revi1);
				revi[1]= BitmapFactory.decodeResource(res, R.drawable.revi2);
				revi[2]= BitmapFactory.decodeResource(res, R.drawable.revi3);
				revi[3]= BitmapFactory.decodeResource(res, R.drawable.revi4);
		    	break;
		    case 3 :
		    	loadTips[3]  = BitmapFactory.decodeResource(res, R.drawable.tips_stone);
		    	imgStone[3][0] = BitmapFactory.decodeResource(res, R.drawable.stone_red1);
				imgStone[3][1] = BitmapFactory.decodeResource(res, R.drawable.stone_red2);
				imgStone[3][2] = BitmapFactory.decodeResource(res, R.drawable.stone_red3);
				imgStone[3][3] = BitmapFactory.decodeResource(res, R.drawable.stone_red4);
				imgStone[3][4] = BitmapFactory.decodeResource(res, R.drawable.stone_red5);
		    	break;
		    case 4 :
		    
		    	loadTips[4]  = BitmapFactory.decodeResource(res, R.drawable.tips_world);
				imgStone[0][0] = BitmapFactory.decodeResource(res, R.drawable.stone_low1);
				imgStone[0][1] = BitmapFactory.decodeResource(res, R.drawable.stone_low2);
				imgStone[0][2] = BitmapFactory.decodeResource(res, R.drawable.stone_low3);
				imgStone[0][3] = BitmapFactory.decodeResource(res, R.drawable.stone_low4);
				imgStone[0][4] = BitmapFactory.decodeResource(res, R.drawable.stone_low5);
		    	break;
		    	
		    }
		
			
	}
	
	public void playLoadClear(){
		
		//プレイ
		playStart = null;
		playBackground = null;
		ground = null;		//白
		speedUp[0] = null;
		for( int i = 0 ; i < 16 ; i++){
			monyakoWalk[i] = null;
			monyakoLadder[i] = null;
		}
		for( int i = 0 ; i < 27 ; i++){
			
			effect[i] = null;
		}
		
		
		
		
		imgLadder = null;
		
		//ストーン
		for(int i = 0; i < 5 ; i++){
			for(int h = 0; h < 5; h++){
				imgStone[i][h] =  null;
			}
			
			ladderRest[i] = null;
			monyakoDeath[i]  =  null;
		}
		
		
		//UIアイコン
	
		uiLadder = null;
		uiKirastone =  null;
		
		uiPauseWindow =  null;
		
		
		
		//トラップ
		trapNormal[0] = null;
		trapNormal[1] = null;
		for( int i = 0; i < 4; i++){
			trapKira[0][i] = null;
			trapKira[1][i] = null;
			trapKira[2][i] = null;
			gate[i] = null;
			revi[i] = null;
		}
				
		//ゲームオーバー
		gameOver = null;
		for( int i = 0; i < 2; i++){
			touchHome[i] = null;
			uiEndIcon[i] = null;
			uiContinueIcon[i] = null;
			uiPauseIcon[i] =  null;
			
		}
		
		for(int i = 0; i < 3; i++ ){
			clear[i] =  null;
			finish[i] = null;
			uiStone[i] = null;
		}
		

	}
	//低速時ストーン配置乱数

		
	//トラップ被り防止 エンドレス用
	public void tuneTrap(){
		
		if( trap.kiraBlock[0][2] == true && trap.kiraBlock[1][2] == true &&
				trap.kiraBlockY[0][2] == trap.kiraBlockY[1][2] && 
				displayX <= trap.kiraBlockX[0][2] && displayX <= trap.kiraBlockX[1][2] ){
				if( trap.kiraBlockY[0][2] == 25 * tune){
					trap.kiraBlockY[0][2] = 255 * tune;
					trap.kiraBlockX[0][2] += 100 * tune;
				} else if( trap.kiraBlockY[0][2] == 255 * tune){
					trap.kiraBlockY[0][2] = 485 * tune;
					trap.kiraBlockX[0][2] += 100 * tune;
				} else if( trap.kiraBlockY[0][2] == 485 * tune){
					trap.kiraBlockY[0][2] = 25 * tune;
					trap.kiraBlockX[0][2] += 100 * tune;
				}
			} else if(  trap.kiraBlock[2][2] == true && trap.kiraBlock[1][2] == true &&
					trap.kiraBlockY[2][2] == trap.kiraBlockY[1][2] && 
					displayX <= trap.kiraBlockX[2][2] && displayX <= trap.kiraBlockX[1][2]  ){
				if( trap.kiraBlockY[1][2] == 25 * tune){
					trap.kiraBlockY[1][2] = 485 * tune;
					trap.kiraBlockX[1][2] += 100 * tune;
				} else if( trap.kiraBlockY[1][2] == 255 * tune){
					trap.kiraBlockY[1][2] = 25 * tune;
					trap.kiraBlockX[1][2] += 100 * tune;
				} else if( trap.kiraBlockY[1][2] == 485 * tune){
					trap.kiraBlockY[1][2] = 255 * tune;
					trap.kiraBlockX[1][2] += 100 * tune;
				}
			} else if( trap.kiraBlock[0][2] == true && trap.kiraBlock[2][2] == true &&
					trap.kiraBlockY[0][2] == trap.kiraBlockY[2][2] && 
					displayX <= trap.kiraBlockX[0][2] && displayX <= trap.kiraBlockX[2][2]  ){
				if( trap.kiraBlockY[2][2] == 25 * tune){
					trap.kiraBlockY[2][2] = 255 * tune;
					trap.kiraBlockX[2][2] += 100 * tune;
				} else if( trap.kiraBlockY[2][2] == 255 * tune){
					trap.kiraBlockY[2][2] = 25 * tune;
					trap.kiraBlockX[2][2] += 100 * tune;
				} else if( trap.kiraBlockY[2][2] == 485 * tune){
					trap.kiraBlockY[2][2] = 485 * tune;
					trap.kiraBlockX[2][2] += 100 * tune;
				}
			}

		//既存キラ被り防止
		for(int i = 0 ; i < 2; i++){
		if(trap.kiraBlock[0][i] == true   &&  ( trap.kiraBlockY[0][2] == trap.kiraBlockY[0][i]) && 
				( displayX <= trap.kiraBlockX[0][2] &&  displayX <= trap.kiraBlockX[0][i])	
				){
			if( trap.kiraBlockY[0][2] == 25 * tune){
				trap.kiraBlockX[0][2] += 350 * tune;
				trap.kiraBlockY[0][2] = 255 * tune;
			} else if( trap.kiraBlockY[0][2] == 255 * tune){
				trap.kiraBlockX[0][2] += 350 * tune;
				trap.kiraBlockY[0][2] = 485 * tune;
			} else if( trap.kiraBlockY[0][2] == 485 * tune){
				trap.kiraBlockX[0][2] += 350 * tune;
				trap.kiraBlockY[0][2] = 25 * tune;
			}
		} else if(trap.kiraBlock[1][i] == true  &&  ( trap.kiraBlockY[0][2] == trap.kiraBlockY[1][i]) && 
				( displayX <= trap.kiraBlockX[0][2] &&  displayX <= trap.kiraBlockX[1][i]) ){
			if( trap.kiraBlockY[0][2] == 25 * tune){
				trap.kiraBlockX[0][2] += 350 * tune;
				trap.kiraBlockY[0][2] = 255 * tune;
			} else if( trap.kiraBlockY[0][2] == 255 * tune){
				trap.kiraBlockX[0][2] += 350 * tune;
				trap.kiraBlockY[0][2] = 485 * tune;
			} else if( trap.kiraBlockY[0][2] == 485 * tune){
				trap.kiraBlockX[0][2] += 350 * tune;
				trap.kiraBlockY[0][2] = 25 * tune;
			}
		} else if(trap.kiraBlock[2][i] == true  &&  ( trap.kiraBlockY[0][2] == trap.kiraBlockY[2][i]) && 
				( displayX <= trap.kiraBlockX[0][2] &&  displayX <= trap.kiraBlockX[2][i]) ){
			if( trap.kiraBlockY[0][2] == 25 * tune){
				trap.kiraBlockX[0][2] += 350 * tune;
				trap.kiraBlockY[0][2] = 255 * tune;
			} else if( trap.kiraBlockY[0][2] == 255 * tune){
				trap.kiraBlockX[0][2] += 350 * tune;
				trap.kiraBlockY[0][2] = 485 * tune;
			} else if( trap.kiraBlockY[0][2] == 485 * tune){
				trap.kiraBlockX[0][2] += 350 * tune;
				trap.kiraBlockY[0][2] = 25 * tune;
			}
		}
		
		//緑
		if(trap.kiraBlock[0][i] == true  &&  ( trap.kiraBlockY[1][2] == trap.kiraBlockY[0][i]) && 
				( displayX <= trap.kiraBlockX[1][2] &&  displayX <= trap.kiraBlockX[0][i])){
			if( trap.kiraBlockY[1][2] == 25 * tune){
				trap.kiraBlockX[1][2] += 350 * tune;
				trap.kiraBlockY[1][2] = 255 * tune;
			} else if( trap.kiraBlockY[1][2] == 255 * tune){
				trap.kiraBlockX[1][2] += 350 * tune;
				trap.kiraBlockY[1][2] = 485 * tune;
			} else if( trap.kiraBlockY[1][2] == 485 * tune){
				trap.kiraBlockX[1][2] += 350 * tune;
				trap.kiraBlockY[1][2] = 25 * tune;
			}
		} else if(trap.kiraBlock[1][i] == true  &&  ( trap.kiraBlockY[1][2] == trap.kiraBlockY[1][i]) && 
				( displayX <= trap.kiraBlockX[1][2] &&  displayX <= trap.kiraBlockX[1][i])){
			if( trap.kiraBlockY[1][2] == 25 * tune){
				trap.kiraBlockX[1][2] += 350 * tune;
				trap.kiraBlockY[1][2] = 255 * tune;
			} else if( trap.kiraBlockY[1][2] == 255 * tune){
				trap.kiraBlockX[1][2] += 350 * tune;
				trap.kiraBlockY[1][2] = 485 * tune;
			} else if( trap.kiraBlockY[1][2] == 485 * tune){
				trap.kiraBlockX[1][2] += 350 * tune;
				trap.kiraBlockY[1][2] = 25 * tune;
			}
		} else if(trap.kiraBlock[2][i] == true  &&   ( trap.kiraBlockY[1][2] == trap.kiraBlockY[2][i]) && 
				( displayX <= trap.kiraBlockX[1][2] &&  displayX <= trap.kiraBlockX[2][i])){
			if( trap.kiraBlockY[1][2] == 25 * tune){
				trap.kiraBlockX[1][2] += 350 * tune;
				trap.kiraBlockY[1][2] = 255 * tune;
			} else if( trap.kiraBlockY[1][2] == 255 * tune){
				trap.kiraBlockX[1][2] += 350 * tune;
				trap.kiraBlockY[1][2] = 485 * tune;
			} else if( trap.kiraBlockY[1][2] == 485 * tune){
				trap.kiraBlockX[1][2] += 350 * tune;
				trap.kiraBlockY[1][2] = 25 * tune;
			}
		}
		
		//青
		if(trap.kiraBlock[0][i] == true  &&  ( trap.kiraBlockY[2][2] == trap.kiraBlockY[0][i]) && 
				( displayX <= trap.kiraBlockX[2][2] &&  displayX <= trap.kiraBlockX[0][i]) ){
			if( trap.kiraBlockY[2][2] == 25 * tune){
				trap.kiraBlockX[2][2] += 350 * tune;
				trap.kiraBlockY[2][2] = 255 * tune;
			} else if( trap.kiraBlockY[2][2] == 255 * tune){
				trap.kiraBlockX[2][2] += 350 * tune;
				trap.kiraBlockY[2][2] = 485 * tune;
			} else if( trap.kiraBlockY[2][2] == 485 * tune){
				trap.kiraBlockX[2][2] += 350 * tune;
				trap.kiraBlockY[2][2] = 25 * tune;
			}
		} else if(trap.kiraBlock[1][i] == true  &&  ( trap.kiraBlockY[2][2] == trap.kiraBlockY[1][i]) && 
				( displayX <= trap.kiraBlockX[2][2] &&  displayX <= trap.kiraBlockX[1][i]) ){
			if( trap.kiraBlockY[2][2] == 25 * tune){
				trap.kiraBlockX[2][2] += 350 * tune;
				trap.kiraBlockY[2][2] = 255 * tune;
			} else if( trap.kiraBlockY[2][2] == 255 * tune){
				trap.kiraBlockX[2][2] += 350 * tune;
				trap.kiraBlockY[2][2] = 485 * tune;
			} else if( trap.kiraBlockY[2][2] == 485 * tune){
				trap.kiraBlockX[2][2] += 350 * tune;
				trap.kiraBlockY[2][2] = 25 * tune;
			}
		} else if(trap.kiraBlock[2][i] == true   &&  ( trap.kiraBlockY[2][2] == trap.kiraBlockY[2][i]) && 
				( displayX <= trap.kiraBlockX[2][2] &&  displayX <= trap.kiraBlockX[2][i]) ){
			if( trap.kiraBlockY[2][2] == 25 * tune){
				trap.kiraBlockX[2][2] += 350 * tune;
				trap.kiraBlockY[2][2] = 255 * tune;
			} else if( trap.kiraBlockY[2][2] == 255 * tune){
				trap.kiraBlockX[2][2] += 350 * tune;
				trap.kiraBlockY[2][2] = 485 * tune;
			} else if( trap.kiraBlockY[2][2] == 485 * tune){
				trap.kiraBlockX[2][2] += 350 * tune;
				trap.kiraBlockY[2][2] = 25 * tune;
			}
		}
		}
		//キラトラップ被り防止
			for( int i = 0; i < 12; i++){
				if(trap.normalBlock[i] == true &&  ( trap.kiraBlockY[0][2] == trap.normalBlockY[i] ) && 
					( displayX <= trap.kiraBlockX[0][2] &&  displayX <= trap.normalBlockX[i]) &&	
					(	-350 * tune < trap.kiraBlockX[0][2] - trap.normalBlockX[i] && trap.kiraBlockX[0][2] - trap.normalBlockX[i]  <  400 * tune  )){
		
					
						if( trap.kiraBlockY[0][2] == 25 * tune){
							trap.kiraBlockX[0][2] += 350 * tune;
							trap.kiraBlockY[0][2] = 255 * tune;			
						} else if( trap.kiraBlockY[0][2] == 255 * tune){
							trap.kiraBlockX[0][2] += 350 * tune;
							trap.kiraBlockY[0][2] = 485 * tune;	
						} else if( trap.kiraBlockY[0][2] == 485 * tune){
							trap.kiraBlockX[0][2] += 350 * tune;
							trap.kiraBlockY[0][2] = 25 * tune;	
						
				
						
					}	
				} else if(trap.normalBlock[i] == true &&  (  trap.kiraBlockY[1][2] == trap.normalBlockY[i]) && 
						( displayX <= trap.kiraBlockX[1][2] &&  displayX <= trap.normalBlockX[i]) &&	
						(	-350 * tune < trap.kiraBlockX[1][2] - trap.normalBlockX[i] && trap.kiraBlockX[1][2] - trap.normalBlockX[i]  <  400 * tune  )){
			
						
							if( trap.kiraBlockY[1][2] == 25 * tune){
								trap.kiraBlockX[1][2] += 350 * tune;
								trap.kiraBlockY[1][2] = 255 * tune;			
							} else if( trap.kiraBlockY[1][2] == 255 * tune){
								trap.kiraBlockX[1][2] += 350 * tune;
								trap.kiraBlockY[1][2] = 485 * tune;	
							} else if( trap.kiraBlockY[1][2] == 485 * tune){
								trap.kiraBlockX[1][2] += 350 * tune;
								trap.kiraBlockY[1][2] = 25 * tune;	
							}
					
				}else if(trap.normalBlock[i] == true &&  (  trap.kiraBlockY[2][2] == trap.normalBlockY[i]) && 
						( displayX <= trap.kiraBlockX[2][2] &&  displayX <= trap.normalBlockX[i]) &&	
						(	-350 * tune < trap.kiraBlockX[2][2] - trap.normalBlockX[i] && trap.kiraBlockX[2][2] - trap.normalBlockX[i]  <  400 * tune  )){
			
						
							if( trap.kiraBlockY[2][2] == 25 * tune){
								trap.kiraBlockX[2][2] += 350 * tune;
								trap.kiraBlockY[2][2] = 255 * tune;			
							} else if( trap.kiraBlockY[2][2] == 255 * tune){
								trap.kiraBlockX[2][2] += 350 * tune;
								trap.kiraBlockY[2][2] = 485 * tune;	
							} else if( trap.kiraBlockY[2][2] == 485 * tune){
								trap.kiraBlockX[2][2] += 350 * tune;
								trap.kiraBlockY[2][2] = 25 * tune;	
							
					
						}	
				}
			}
		
	}
	
	public void changeLowStoneY(){
		if( stone.y[0][stone.tune[0]] == 45 * tune){
			stone.x[0][stone.tune[0]] += 200 * tune;
			stone.y[0][stone.tune[0]] = 275 * tune;				
		} else if( stone.y[0][stone.tune[0]] == 275 * tune){
			stone.x[0][stone.tune[0]] += 200 * tune;
			stone.y[0][stone.tune[0]] = 505 * tune;				
		} else if( stone.y[0][stone.tune[0]] == 505 * tune){
			stone.x[0][stone.tune[0]] += 200 * tune;
			stone.y[0][stone.tune[0]] = 45 * tune;				
		}
		
	}
	public void tuneLowStoneY(){
		//トラップ被り防止
		for(byte h = 0; h < 4; h++){
			for( byte i = 0; i < 12; i++){
				if(trap.normalBlock[i] == true && ( trap.normalBlockY[i] + 20 * tune -5 <= stone.y[0][h] && stone.y[0][h] <=  trap.normalBlockY[i] +  20 * tune + 5  ) && 
					( displayX <= stone.x[0][h] &&  displayX <= trap.normalBlockX[i]) &&	
					(	-200 * tune < stone.x[0][h] - trap.normalBlockX[i] && stone.x[0][h] - trap.normalBlockX[i]  <  400 * tune  )){
					
					stone.tune[0] = h;
					changeLowStoneY();
				}
			}
		}
		
		//低ストーン被り防止
		for(byte i = 0; i < 4; i++ ){
			for(byte h = 0; h < 4; h++){
				if(stone.kind[0][i] == true &&  stone.y[0][i]  == stone.y[0][h]  && 
						( displayX <= stone.x[0][h] &&  displayX <= stone.x[0][i]) ){
				
					if( h != i){
						stone.tune[0] = h;
						changeLowStoneY();
					}
				}	
			}
		}
		
		//中ストーン被り防止
		for(byte i = 0; i < 4; i++ ){
			for(byte h = 0; h < 4; h++){
				if(stone.kind[1][i] == true &&  stone.y[1][i]  == stone.y[0][h]  && 
						( displayX <= stone.x[0][h] &&  displayX <= stone.x[1][i]) ){
					stone.tune[0] = h;
					changeLowStoneY();
				}
				//赤ストーン被り防止
				if( stone.kind[3][i] == true &&  stone.y[3][i]  == stone.y[0][h]  && 
						( displayX <= stone.x[0][h] &&  displayX <= stone.x[3][i]) ){
					stone.tune[0] = h;
					changeLowStoneY();
				} 
				//緑ストーン被り防止
				if( stone.kind[4][i] == true &&  stone.y[4][i]  == stone.y[0][h]  && 
						( displayX <= stone.x[0][h] &&  displayX <= stone.x[4][i]) ){
					stone.tune[0] = h;
					changeLowStoneY();
				} 
				//青ストーン被り防止
				if(stone.kind[5][i] == true &&  stone.y[5][i]  == stone.y[0][h]  && 
						( displayX <= stone.x[0][h] &&  displayX <= stone.x[5][i])){
					stone.tune[0] = h;
					changeLowStoneY();	
				}
				//赤トラップ被り防止
				if(trap.kiraBlock[0][i] == true &&  ( trap.kiraBlockY[0][i] + 20 * tune -5 <= stone.y[0][h] && stone.y[0][h] <=  trap.kiraBlockY[0][i] +  20 * tune + 5  )  && 
						( displayX <= stone.x[0][h] &&  displayX <= trap.kiraBlockX[0][i]) &&
						(	-200 * tune < stone.x[0][h] - trap.kiraBlockX[0][i] && stone.x[0][h] - trap.kiraBlockX[0][i]  <  400 * tune  )){
					stone.tune[0] = h;
					changeLowStoneY();
				} 
				
				//緑トラップ被り防止
				if(trap.kiraBlock[1][i] == true &&  ( trap.kiraBlockY[1][i] + 20 * tune -5 <= stone.y[0][h] && stone.y[0][h] <=  trap.kiraBlockY[1][i] +  20 * tune + 5  ) && 
						( displayX <= stone.x[0][h] &&  displayX <= trap.kiraBlockX[1][i]) &&
						(	-200 * tune < stone.x[0][h] - trap.kiraBlockX[1][i] && stone.x[0][h] - trap.kiraBlockX[1][i]  <  400 * tune  )){
					stone.tune[0] = h;
					changeLowStoneY();
				} 
				
				//赤トラップ被り防止
				if(trap.kiraBlock[2][i] == true &&  ( trap.kiraBlockY[2][i] + 20 * tune -5 <= stone.y[0][h] && stone.y[0][h] <=  trap.kiraBlockY[2][i] +  20 * tune + 5  )  && 
						( displayX <= stone.x[0][h] &&  displayX <= trap.kiraBlockX[2][i]) &&
						(	-200 * tune < stone.x[0][h] - trap.kiraBlockX[2][i] && stone.x[0][h] - trap.kiraBlockX[2][i]  <  400 * tune  )){
					stone.tune[0] = h;
					changeLowStoneY();
				} 
			}
		}
		
		//高ストーン被り防止
		for(byte i = 0; i < 4; i++ ){
			for(byte h = 0; h < 4; h++){
				if(stone.kind[2][i] == true &&  stone.y[2][i]  == stone.y[0][h]  && 
						( displayX <= stone.x[0][h] &&  displayX <= stone.x[2][i]) ){
					stone.tune[0] = h;
					changeLowStoneY();
				} 
				
				
				
			}
	
		}
	}
	
	public void changeMiddleStoneY(){
		
		if( stone.y[1][stone.tune[1]] == 45 * tune){
			stone.x[1][stone.tune[1]] += 200 * tune;
			stone.y[1][stone.tune[1]] = 275 * tune;				
		} else if( stone.y[1][stone.tune[1]] == 275 * tune){
			stone.x[1][stone.tune[1]] += 200 * tune;
			stone.y[1][stone.tune[1]] = 505 * tune;				
		} else if( stone.y[1][stone.tune[1]] == 505 * tune){
			stone.x[1][stone.tune[1]]+= 200 * tune;
			stone.y[1][stone.tune[1]] = 45 * tune;				
		}
	}
	
	//中ポイントストーンの被り防止
	public void tuneMiddleStoneY(){
		//トラップ[i]があり、トラップ[i]と鉱石[h]が同じ段で
		//両者が画面外にあり
		//両者の距離が-２00 ～ 400の時、鉱石[h]の位置を移動する
		for(byte h = 0; h < 3; h++){
			for( byte i = 0; i < 12; i++){
				if( trap.normalBlock[i] == true && ( trap.normalBlockY[i] + 20 * tune -5 <= stone.y[1][h] && stone.y[1][h] <=  trap.normalBlockY[i] +  20 * tune + 5  ) && 
					( displayX <= stone.x[1][h] &&  displayX <= trap.normalBlockX[i]) &&	
					(	(	-200 * tune <= stone.x[1][h] - trap.normalBlockX[i] && stone.x[1][h] - trap.normalBlockX[i]  <=  400 * tune  )   )){
			
					stone.tune[1] = h;
					changeMiddleStoneY();
				}
			}
		}
		
		//高ストーン被り防止
				for(byte i = 0; i < 4; i++ ){
					for(byte h = 0; h < 4; h++){
						if(stone.kind[2][i] == true &&  stone.y[2][i]  == stone.y[1][h]  && 
								( displayX <= stone.x[1][h] &&  displayX <= stone.x[2][i]) ){
							stone.tune[1] = h;
							changeMiddleStoneY();
						} 
					}
				}		
				for(byte i = 0; i < 4; i++ ){
					for(byte h = 0; h < 4; h++){	
						
						if( stone.kind[1][i] == true &&  stone.y[1][i]  == stone.y[1][h]  && 
								( displayX <= stone.x[1][h] &&  displayX <= stone.x[1][i]) ){
							if( h != i ){
								stone.tune[1] = h;
								changeMiddleStoneY();	
							}
						}
						//赤ストーン被り防止
						if(stone.kind[3][i] == true &&  stone.y[3][i]  == stone.y[1][h]  && 
								( displayX <= stone.x[1][h] &&  displayX <= stone.x[3][i]) ){
							stone.tune[1] = h;
							changeMiddleStoneY();
						} 
						//緑ストーン被り防止
						if(stone.kind[4][i] == true &&  stone.y[4][i]  == stone.y[1][h]  && 
								( displayX <= stone.x[1][h] &&  displayX <= stone.x[4][i]) ){
							stone.tune[1] = h;
							changeMiddleStoneY();
						} 
						//青ストーン被り防止
						if(stone.kind[5][i] == true &&  stone.y[5][i]  == stone.y[1][h]  && 
								( displayX <= stone.x[1][h] &&  displayX <= stone.x[5][i])){
							stone.tune[1] = h;
							changeMiddleStoneY();
						}
						
						//赤トラップ被り防止
						if(trap.kiraBlock[0][i] == true &&  ( trap.kiraBlockY[0][i] + 20 * tune -5 <= stone.y[1][h] && stone.y[1][h] <=  trap.kiraBlockY[0][i] +  20 * tune + 5  )  && 
								( displayX <= stone.x[1][h] &&  displayX <= trap.kiraBlockX[0][i]) &&
								(	-200 * tune < stone.x[1][h] - trap.kiraBlockX[0][i] && stone.x[1][h] - trap.kiraBlockX[0][i]  <  400 * tune  )){
							stone.tune[1] = h;
							changeMiddleStoneY();	
						} 
						
						//緑トラップ被り防止
						if(trap.kiraBlock[1][i] == true &&  ( trap.kiraBlockY[1][i] + 20 * tune -5 <= stone.y[1][h] && stone.y[1][h] <=  trap.kiraBlockY[1][i] +  20 * tune + 5  )  && 
								( displayX <= stone.x[1][h] &&  displayX <= trap.kiraBlockX[1][i]) &&
								(	-200 * tune < stone.x[1][h] - trap.kiraBlockX[1][i] && stone.x[1][h] - trap.kiraBlockX[1][i]  <  400 * tune  )){
							stone.tune[1] = h;
							changeMiddleStoneY();
						} 
						
						//青トラップ被り防止
						if(trap.kiraBlock[2][i] == true && ( trap.kiraBlockY[2][i] + 20 * tune -5 <= stone.y[1][h] && stone.y[1][h] <=  trap.kiraBlockY[2][i] +  20 * tune + 5  )  && 
								( displayX <= stone.x[1][h] &&  displayX <= trap.kiraBlockX[2][i]) &&
								(	-200 * tune < stone.x[1][h] - trap.kiraBlockX[2][i] && stone.x[1][h] - trap.kiraBlockX[2][i]  <  400 * tune  )){
							stone.tune[1] = h;
							changeMiddleStoneY();
						} 
						
					}
			
				}
	}
	
	public void changeHighStoneY(){
		if( stone.y[2][stone.tune[2]] == 45 * tune){
			stone.x[2][stone.tune[2]] += 200 * tune;
			stone.y[2][stone.tune[2]] = 275 * tune;				
		} else if( stone.y[2][stone.tune[2]] == 275 * tune){
			stone.x[2][stone.tune[2]] += 200 * tune;
			stone.y[2][stone.tune[2]] = 505 * tune;				
		} else if( stone.y[2][stone.tune[2]] == 505 * tune){
			stone.x[2][stone.tune[2]] = 200 * tune;
			stone.y[2][stone.tune[2]] = 45 * tune;				
		}
	}
	//高ポイントストーンの被り防止
	public void tuneHighStoneY(){
		for( byte h = 0; h < 2; h++){
			for( byte i = 0; i < 12; i++){
				if( trap.normalBlock[i] == true && ( trap.normalBlockY[i] + 20 * tune -5 <= stone.y[2][h] && stone.y[2][h] <=  trap.normalBlockY[i] +  20 * tune + 5  ) && 
					( displayX <= stone.x[2][h] &&  displayX <= trap.normalBlockX[i]) &&					
					(	-200 * tune < stone.x[2][h] - trap.normalBlockX[i] && stone.x[2][h] - trap.normalBlockX[i]  <  400 * tune  )){
					stone.tune[2] = h;
					changeHighStoneY();
					
				}
			}
		}
		
		
		for(byte h = 0; h < 4; h++ ){
			for(byte i = 0; i < 4; i++){
				//赤ストーン被り防止
				if( stone.kind[3][i] == true && stone.kind[2][h] == true && stone.y[3][i]  == stone.y[2][h]  && 
						( displayX <= stone.x[2][h] &&  displayX <= stone.x[3][i]) ){
					stone.tune[2] = h;
					changeHighStoneY();
				} 
				//緑ストーン被り防止
				if(stone.kind[4][i] == true &&  stone.kind[2][h] == true && stone.y[4][i]  == stone.y[2][h]  && 
						( displayX <= stone.x[2][h] &&  displayX <= stone.x[4][i]) ){
					stone.tune[2] = h;
					changeHighStoneY();
				} 
				//青ストーン被り防止
				if(stone.kind[5][i] == true &&  stone.kind[2][h] == true && stone.y[5][i]  == stone.y[2][h]  && 
						( displayX <= stone.x[2][h] &&  displayX <= stone.x[5][i])){
					stone.tune[2] = h;
					changeHighStoneY();
				}
				
				//赤トラップ被り防止
				if(trap.kiraBlock[0][i] == true &&  ( trap.kiraBlockY[0][i] + 20 * tune -5 <= stone.y[2][h] && stone.y[2][h] <=  trap.kiraBlockY[0][i] +  20 * tune + 5  ) && 
						( displayX <= stone.x[2][h] &&  displayX <= trap.kiraBlockX[0][i]) &&
						(	-200 * tune < stone.x[2][h] - trap.kiraBlockX[0][i] && stone.x[2][h] - trap.kiraBlockX[0][i]  <  400 * tune  )){
					stone.tune[2] = h;
					changeHighStoneY();	
				} 
				
				//緑トラップ被り防止
				if(trap.kiraBlock[1][i] == true &&  ( trap.kiraBlockY[1][i] + 20 * tune -5 <= stone.y[2][h] && stone.y[2][h] <=  trap.kiraBlockY[1][i] +  20 * tune + 5  )  && 
						( displayX <= stone.x[2][h] &&  displayX <= trap.kiraBlockX[1][i]) &&
						(	-200 * tune < stone.x[2][h] - trap.kiraBlockX[1][i] && stone.x[2][h] - trap.kiraBlockX[1][i]  <  400 * tune  )){
					stone.tune[2] = h;
					changeHighStoneY();
				} 
				
				//青トラップ被り防止
				if(trap.kiraBlock[2][i] == true &&  ( trap.kiraBlockY[2][i] + 20 * tune -5 <= stone.y[2][h] && stone.y[2][h] <=  trap.kiraBlockY[2][i] +  20 * tune + 5  ) && 
						( displayX <= stone.x[2][h] &&  displayX <= trap.kiraBlockX[2][i]) &&
						(	-200 * tune < stone.x[2][h] - trap.kiraBlockX[2][i] && stone.x[2][h] - trap.kiraBlockX[2][i]  <  400 * tune  )){
					stone.tune[2] = h;
					changeHighStoneY();
				} 
				
			}
		}
	}
	
	public void changeRedStoneY(){
		if( stone.y[3][stone.tune[3]] == 45 * tune){
			stone.x[3][stone.tune[3]] += 200 * tune;
			stone.y[3][stone.tune[3]] = 275 * tune;				
		} else if( stone.y[3][stone.tune[3]] == 275 * tune){
			stone.x[3][stone.tune[3]] += 200 * tune;
			stone.y[3][stone.tune[3]] = 505 * tune;				
		} else if( stone.y[3][stone.tune[3]] == 505 * tune){
			stone.x[3][stone.tune[3]] = 200 * tune;
			stone.y[3][stone.tune[3]] = 45 * tune;				
		}
	}
	//赤鉱石(redキラストーン)
	public void tuneRedStoneY(){
		for( byte h = 0; h < 4; h++){
			for( byte i = 0; i < 12; i++){
				if( trap.normalBlock[i] == true && ( trap.normalBlockY[i] + 20 * tune -5 <= stone.y[3][h] && stone.y[3][h] <=  trap.normalBlockY[i] +  20 * tune + 5  ) && 
					( displayX <= stone.x[3][h] &&  displayX <= trap.normalBlockX[i]) &&					
					(	-200 * tune < stone.x[3][h] - trap.normalBlockX[i] && stone.x[3][h] - trap.normalBlockX[i]  <  400 * tune  )){
			
					stone.tune[3] = h;
					changeRedStoneY();
				}
			}
		}
		
				//キラトラップ被り防止
				for(byte h = 0; h < 4; h++ ){
					for(byte i = 0; i < 4; i++){
						//赤ストーン被り防止
						if(stone.kind[3][i] == true && stone.kind[3][h] == true && stone.y[3][i]  == stone.y[3][h]  && 
								( displayX <= stone.x[3][h] &&  displayX <= stone.x[3][i]) ){
						if( h != i){
							stone.tune[3] = h;
							changeRedStoneY();
						}	
						} 
						//緑ストーン被り防止
						if(stone.kind[4][i] == true && stone.kind[3][h] == true && stone.y[4][i]  == stone.y[3][h]  && 
								( displayX <= stone.x[3][h] &&  displayX <= stone.x[4][i]) ){
							stone.tune[3] = h;
							changeRedStoneY();
						} 
						//青ストーン被り防止
						if(stone.kind[5][i] == true && stone.kind[3][h] == true && stone.y[5][i]  == stone.y[3][h]  && 
								( displayX <= stone.x[3][h] &&  displayX <= stone.x[5][i])){
							stone.tune[3] = h;
							changeRedStoneY();
						}
						
						//赤トラップ被り防止
						if(trap.kiraBlock[0][i] == true && stone.kind[3][h] == true &&
								( trap.kiraBlockY[0][i] + 20 * tune -5 <= stone.y[3][h] && stone.y[3][h] <=  trap.kiraBlockY[0][i] +  20 * tune + 5  )  && 
								( displayX <= stone.x[3][h] &&  displayX <= trap.kiraBlockX[0][i]) &&
								(	-200 * tune < stone.x[3][h] - trap.kiraBlockX[0][i] && stone.x[3][h] - trap.kiraBlockX[0][i]  <  400 * tune  )){
							stone.tune[3] = h;
							changeRedStoneY();
						} 
						
						//緑トラップ被り防止
						if(trap.kiraBlock[1][i] == true && stone.kind[3][h] == true &&
								( trap.kiraBlockY[1][i] + 20 * tune -5 <= stone.y[3][h] && stone.y[3][h] <=  trap.kiraBlockY[1][i] +  20 * tune + 5  )  && 
								( displayX <= stone.x[3][h] &&  displayX <= trap.kiraBlockX[1][i]) &&
								(	-200 * tune < stone.x[3][h] - trap.kiraBlockX[1][i] && stone.x[3][h] - trap.kiraBlockX[1][i]  <  400 * tune  )){
							stone.tune[3] = h;
							changeRedStoneY();
						} 
						
						//青トラップ被り防止
						if(trap.kiraBlock[2][i] == true && stone.kind[3][h] == true &&
								( trap.kiraBlockY[2][i] + 20 * tune -5 <= stone.y[3][h] && stone.y[3][h] <=  trap.kiraBlockY[2][i] +  20 * tune + 5  )  && 
								( displayX <= stone.x[3][h] &&  displayX <= trap.kiraBlockX[2][i]) &&
								(	-200 * tune < stone.x[3][h] - trap.kiraBlockX[2][i] && stone.x[3][h] - trap.kiraBlockX[2][i]  <  400 * tune  )){
							stone.tune[3] = h;
							changeRedStoneY();
						} 
						
					}
				}
	}
	
	public void changeGreenStoneY(){
		if( stone.y[4][stone.tune[4]] == 45 * tune){
			stone.x[4][stone.tune[4]] += 200 * tune;
			stone.y[4][stone.tune[4]] = 275 * tune;				
		} else if( stone.y[4][stone.tune[4]] == 275 * tune){
			stone.x[4][stone.tune[4]] += 200 * tune;
			stone.y[4][stone.tune[4]] = 505 * tune;				
		} else if( stone.y[4][stone.tune[4]] == 505 * tune){
			stone.x[4][stone.tune[4]] = 200 * tune;
			stone.y[4][stone.tune[4]] = 45 * tune;				
		}
		
	}
	//緑鉱石(greenキラストーン)の被り防止
	public void tuneGreenStoneY(){
		for( byte h = 0; h < 4; h++){
			for( byte i = 0; i < 12; i++){
				if( trap.normalBlock[i] == true && ( trap.normalBlockY[i] + 20 * tune -5 <= stone.y[4][h] && stone.y[4][h] <=  trap.normalBlockY[i] +  20 * tune + 5  ) && 
					( displayX <= stone.x[4][h] &&  displayX <= trap.normalBlockX[i]) &&					
					(	-200 * tune < stone.x[4][h] - trap.normalBlockX[i] && stone.x[4][h] - trap.normalBlockX[i]  <  400 * tune  )){
			
					stone.tune[4] = h;
					changeGreenStoneY();
				}
			}
		}
		
		
		//キラトラップ被り防止
		for(byte h = 0; h < 4; h++ ){
			for(byte i = 0; i < 4; i++){
				
				//赤ストーン被り防止
				if(stone.kind[3][i] == true && stone.kind[4][h] == true &&  stone.y[3][i]  == stone.y[4][h]  && 
						( displayX <= stone.x[4][h] &&  displayX <= stone.x[3][i]) ){
				
					stone.tune[4] = h;
					changeGreenStoneY();
				
				} 
				//緑ストーン被り防止
				if(stone.kind[4][i] == true && stone.kind[4][h] == true &&  stone.y[4][i]  == stone.y[4][h]  && 
						( displayX <= stone.x[4][h] &&  displayX <= stone.x[4][i]) ){
					if( h != i){
						stone.tune[4] = h;
						changeGreenStoneY();
					}
				} 
				//青ストーン被り防止
				if(stone.kind[5][i] == true && stone.kind[4][h] == true &&  stone.y[5][i]  == stone.y[4][h]  && 
						( displayX <= stone.x[4][h] &&  displayX <= stone.x[5][i])){
					stone.tune[4] = h;
					changeGreenStoneY();
				}
				//赤トラップ被り防止
				if(trap.kiraBlock[0][i] == true && stone.kind[4][h] == true && 
						( trap.kiraBlockY[0][i] + 20 * tune -5 <= stone.y[4][h] && stone.y[4][h] <=  trap.kiraBlockY[0][i] +  20 * tune + 5  )  && 
						( displayX <= stone.x[4][h] &&  displayX <= trap.kiraBlockX[0][i]) &&
						(	-200 * tune < stone.x[4][h] - trap.kiraBlockX[0][i] && stone.x[4][h] - trap.kiraBlockX[0][i]  <  400 * tune  )){
					stone.tune[4] = h;
					changeGreenStoneY();
				} 
				
				//緑トラップ被り防止
				if(trap.kiraBlock[1][i] == true && stone.kind[4][h] == true &&  
						( trap.kiraBlockY[1][i] + 20 * tune -5 <= stone.y[4][h] && stone.y[4][h] <=  trap.kiraBlockY[1][i] +  20 * tune + 5  )  && 
						( displayX <= stone.x[4][h] &&  displayX <= trap.kiraBlockX[1][i]) &&
						(	-200 * tune < stone.x[4][h] - trap.kiraBlockX[1][i] && stone.x[4][h] - trap.kiraBlockX[1][i]  <  400 * tune  )){
					stone.tune[4] = h;
					changeGreenStoneY();
				} 
				
				//青トラップ被り防止
				if(trap.kiraBlock[2][i] == true && stone.kind[4][h] == true &&  
						( trap.kiraBlockY[2][i] + 20 * tune -5 <= stone.y[4][h] && stone.y[4][h] <=  trap.kiraBlockY[2][i] +  20 * tune + 5  )  && 
						( displayX <= stone.x[4][h] &&  displayX <= trap.kiraBlockX[2][i]) &&
						(	-200 * tune < stone.x[4][h] - trap.kiraBlockX[2][i] && stone.x[4][h] - trap.kiraBlockX[2][i]  <  400 * tune  )){
					stone.tune[4] = h;
					changeGreenStoneY();
				} 
			}
		}
	}
	
	public void changeBlueStoneY(){

		if( stone.y[5][stone.tune[5]] == 45 * tune){
			stone.x[5][stone.tune[5]] += 200 * tune;
			stone.y[5][stone.tune[5]] = 275 * tune;				
		} else if( stone.y[5][stone.tune[5]] == 275 * tune){
			stone.x[5][stone.tune[5]] += 200 * tune;
			stone.y[5][stone.tune[5]] = 505 * tune;				
		} else if( stone.y[5][stone.tune[5]] == 505 * tune){
			stone.x[5][stone.tune[5]] = 200 * tune;
			stone.y[5][stone.tune[5]] = 45 * tune;				
		}
	}
	//青鉱石(blueキラストーン)被り防止
	public void tuneBlueStoneY(){
		for( byte h = 0; h < 3; h++){
			for( int i = 0; i < 12; i++){
				if( trap.normalBlock[i] == true && ( trap.normalBlockY[i] + 20 * tune -5 <= stone.y[5][h] && stone.y[5][h] <=  trap.normalBlockY[i] +  20 * tune + 5  ) && 
					( displayX <= stone.x[5][h] &&  displayX <= trap.normalBlockX[i]) &&					
					(	-200 * tune < stone.x[5][h] - trap.normalBlockX[i] && stone.x[5][h] - trap.normalBlockX[i]  <  400 * tune  )){
					
					stone.tune[5] = h;
					changeBlueStoneY();
					
				}
			}
		}
		
		//キラトラップ被り防止
		for(byte h = 0; h < 3; h++ ){
			for(byte i = 0; i < 3; i++){
				
				//赤ストーン被り防止
				if(stone.kind[3][i] == true &&  stone.y[3][i]  == stone.y[5][h]  && 
						( displayX <= stone.x[5][h] &&  displayX <= stone.x[3][i]) ){
				
					stone.tune[5] = h;
					changeBlueStoneY();
				
				} 
				//緑ストーン被り防止
				if(stone.kind[4][i] == true &&  stone.y[4][i]  == stone.y[5][h]  && 
						( displayX <= stone.x[5][h] &&  displayX <= stone.x[4][i]) ){
					
					stone.tune[5] = h;
					changeBlueStoneY();
					
				} 
				//青ストーン被り防止
				if(stone.kind[5][i] == true &&  stone.y[5][i]  ==  stone.y[5][h]  && 
						( displayX <= stone.x[5][h] &&  displayX <= stone.x[5][i])){
					if( h != i){
						stone.tune[5] = h;
						changeBlueStoneY();
					}
				}
				//赤トラップ被り防止
				if(trap.kiraBlock[0][i] == true &&  ( trap.kiraBlockY[0][i] + 20 * tune -5 <= stone.y[5][h] && stone.y[5][h] <=  trap.kiraBlockY[0][i] +  20 * tune + 5  )  && 
						( displayX <= stone.x[5][h] &&  displayX <= trap.kiraBlockX[0][i]) &&
						(	-200 * tune < stone.x[5][h] - trap.kiraBlockX[0][i] && stone.x[5][h] - trap.kiraBlockX[0][i]  <  400 * tune  )){
					stone.tune[5] = h;
					changeBlueStoneY();
				} 
				
				//緑トラップ被り防止
				if(trap.kiraBlock[1][i] == true &&  ( trap.kiraBlockY[1][i] + 20 * tune -5 <= stone.y[5][h] && stone.y[5][h] <=  trap.kiraBlockY[1][i] +  20 * tune + 5  )  && 
						( displayX <= stone.x[5][h] &&  displayX <= trap.kiraBlockX[1][i]) &&
						(	-200 * tune < stone.x[5][h] - trap.kiraBlockX[1][i] && stone.x[5][h] - trap.kiraBlockX[1][i]  <  400 * tune  )){
					stone.tune[5] = h;
					changeBlueStoneY();
				} 
				
				//青トラップ被り防止
				if(trap.kiraBlock[2][i] == true &&  ( trap.kiraBlockY[2][i] + 20 * tune -5 <= stone.y[5][h] && stone.y[5][h] <=  trap.kiraBlockY[2][i] +  20 * tune + 5  )  && 
						( displayX <= stone.x[5][h] &&  displayX <= trap.kiraBlockX[2][i]) &&
						(	-200 * tune < stone.x[5][h] - trap.kiraBlockX[2][i] && stone.x[5][h] - trap.kiraBlockX[2][i]  <  400 * tune  )){
					stone.tune[5] = h;
					changeBlueStoneY();
				} 
				
			}
		}
		
	}
	//鉱石のY座標を決めるメソッド
	public void randomStoneY(){
		for( int h = 0; h < 6; h++){	
			for( int i = 0; i < 4; i++){
				stone.rndY[h] = stone.rnd.nextInt(3);
				if( stone.kind[h][i] == false){
					if(stone.rndY[h] == 0){
						stone.y[h][i] = 45 * tune;
					} else if( stone.rndY[h] == 1){
						stone.y[h][i] = 275 * tune;
					} else if( stone.rndY[h] == 2){
						stone.y[h][i] = 505 * tune;
					}
				}
			}
		}
	}
	
	//罠ランダムY
	public void randamTrapY1(){
		trap.endlessRnd = trap.rnd.nextInt(3);
		switch(trap.endlessRnd){
		
		case 0:
		if(trap.kiraBlock[0][2] == false){
			trap.kiraBlockY[0][2] = 25 * tune;
			
		}
		if(trap.kiraBlock[1][2] == false){
			trap.kiraBlockY[1][2] = 255 * tune;
		}
		if(trap.kiraBlock[2][2] == false){
			trap.kiraBlockY[2][2] = 485 * tune;
		}
			break;

		case 1:
		if(trap.kiraBlock[0][2] == false){
			trap.kiraBlockY[0][2] = 255 * tune;
		}
		if(trap.kiraBlock[1][2] == false){
			trap.kiraBlockY[1][2] = 485 * tune;
		}
		if(trap.kiraBlock[2][2] == false){
			trap.kiraBlockY[2][2] = 25 * tune;
		}
			
			break;
		case 2:
		if(trap.kiraBlock[0][2] == false){
			trap.kiraBlockY[0][2] = 485 * tune;
		}
		if(trap.kiraBlock[1][2] == false){
			trap.kiraBlockY[1][2] = 25 * tune;
		}
		if(trap.kiraBlock[2][2] == false){
			trap.kiraBlockY[2][2] = 255 * tune;
		}
			
			break;
		
		}
		
	

		
	}
	
	public void randamTrapY2(){
		stone.rndY[0] = trap.rnd.nextInt(6);
		stone.rndY[1] = trap.rnd.nextInt(6);
		stone.rndY[2] = trap.rnd.nextInt(6);
		switch(stone.rndY[0]){
		
		case 0:
			if(trap.normalBlock[0] == false){
				trap.normalBlockY[0] = 25 * tune;
				trap.normalBlockY[1] = 255 * tune;
			}
			break;				
		case 1:
			if(trap.normalBlock[0] == false){
				trap.normalBlockY[0] = 255 * tune;
				trap.normalBlockY[1] = 25 * tune;
			}
			break;
		case 2:
			if(trap.normalBlock[0] == false){
				trap.normalBlockY[0] = 485 * tune;
				trap.normalBlockY[1] = 25 * tune;
			}
			break;
			
		case 3:
			if(trap.normalBlock[0] == false){
				trap.normalBlockY[0] = 25 * tune;
				trap.normalBlockY[1] = 485 * tune;
			}
			break;				
		case 4:
			if(trap.normalBlock[0] == false){
				trap.normalBlockY[0] = 255 * tune;
				trap.normalBlockY[1] = 485 * tune;
			}
			break;
		case 5:
			if(trap.normalBlock[0] == false){
				trap.normalBlockY[0] = 485 * tune;
				trap.normalBlockY[1] = 255 * tune;
			}
			break;
		}
		
		switch(stone.rndY[1]){
		
		case 0:
			if(trap.normalBlock[2] == false){
				trap.normalBlockY[2] = 255 * tune;
				trap.normalBlockY[6] = 25 * tune;
			}
			break;				
		case 1:
			if(trap.normalBlock[2] == false){
				trap.normalBlockY[2] = 485 * tune;
				trap.normalBlockY[6] = 25 * tune;
			}
			break;
		case 2:
			if(trap.normalBlock[2] == false){
				trap.normalBlockY[2] = 25 * tune;
				trap.normalBlockY[6] = 255 * tune;
			}
			break;
			
		case 3:
			if(trap.normalBlock[2] == false){
				trap.normalBlockY[2] = 255 * tune;
				trap.normalBlockY[6] = 485 * tune;
			}
			break;				
		case 4:
			if(trap.normalBlock[2] == false){
				trap.normalBlockY[2] = 485 * tune;
				trap.normalBlockY[6] = 255 * tune;
			}
			break;
		case 5:
			if(trap.normalBlock[2] == false){
				trap.normalBlockY[2] = 25 * tune;
				trap.normalBlockY[6] = 485 * tune;
			}
			break;
		}
		
		switch(stone.rndY[2]){
		
		case 0:
			if(trap.normalBlock[10] == false){
				trap.normalBlockY[10] = 485 * tune;
				trap.normalBlockY[11] = 25 * tune;
			}
			break;				
		case 1:
			if(trap.normalBlock[10] == false){
				trap.normalBlockY[10] = 25 * tune;
				trap.normalBlockY[11] = 485 * tune;
			}
			break;
		case 2:
			if(trap.normalBlock[10] == false){
				trap.normalBlockY[10] = 255 * tune;
				trap.normalBlockY[11] = 485 * tune;
			}
			break;
			
		case 3:
			if(trap.normalBlock[10] == false){
				trap.normalBlockY[10] = 485 * tune;
				trap.normalBlockY[11] = 255 * tune;
			}
			break;				
		case 4:
			if(trap.normalBlock[10] == false){
				trap.normalBlockY[10] = 25 * tune;
				trap.normalBlockY[11] = 255 * tune;
			}
			break;
		case 5:
			if(trap.normalBlock[10] == false){
				trap.normalBlockY[10] = 255 * tune;
				trap.normalBlockY[11] = 25 * tune;
			}
			break;
		}

	}
	

	public void randamTrapX2(){
		trap.rndY[3] = trap.rnd.nextInt(5);
		trap.rndY[4] = trap.rnd.nextInt(5);
		trap.rndY[5] = trap.rnd.nextInt(5);
		switch(stone.rndY[3]){
		case 0:
			if(trap.normalBlockY[1] != trap.normalBlockY[0] &&  trap.normalBlock[1] == false &&  trap.normalBlock[0] == false){
				trap.normalBlockX[1] = displayX ;
			}
			break;				
		case 1:
			if(trap.normalBlock[1] == false &&  trap.normalBlock[0] == false){
				trap.normalBlockX[1] = displayX + 310 * tune;
			}
			break;
		case 2:
			if(trap.normalBlock[1] == false &&  trap.normalBlock[0] == false){
				trap.normalBlockX[1] = displayX + 400 * tune;
			}
			break;
		case 3:
			if(trap.normalBlock[1] == false &&  trap.normalBlock[0] == false){
				trap.normalBlockX[1] = displayX + 310 * tune;
			}
			break;
		case 4:
			if(trap.normalBlock[1] == false &&  trap.normalBlock[0] == false){
				trap.normalBlockX[1] = displayX + 400 * tune;
			}
			break;
		
		}
		
		switch(stone.rndY[4]){
		case 0:
			if(trap.normalBlockY[6] != trap.normalBlockY[2] && trap.normalBlock[6] == false &&  trap.normalBlock[2] == false){
				trap.normalBlockX[6] = displayX ;
			}
			break;				
		case 1:
			if(trap.normalBlock[6] == false &&  trap.normalBlock[2] == false){
				trap.normalBlockX[6] = displayX + 310 * tune;
			}
			break;
		case 2:
			if(trap.normalBlock[6] == false &&  trap.normalBlock[2] == false){
				trap.normalBlockX[6] = displayX + 400 * tune;
			}
			break;
		case 3:
			if(trap.normalBlock[6] == false &&  trap.normalBlock[2] == false){
				trap.normalBlockX[6] = displayX + 310 * tune;
			}
			break;
		case 4:
			if(trap.normalBlock[6] == false &&  trap.normalBlock[2] == false){
				trap.normalBlockX[6] = displayX + 400 * tune;
			}
			break;
		
		}
		
		switch(stone.rndY[5]){
		case 0:
			if(trap.normalBlockY[11] != trap.normalBlockY[10] && trap.normalBlock[11] == false &&  trap.normalBlock[10] == false){
				trap.normalBlockX[11] = displayX ;
			}
			break;				
		case 1:
			if(trap.normalBlock[11] == false &&  trap.normalBlock[10] == false){
				trap.normalBlockX[11] = displayX + 310 * tune;
			}
			break;
		case 2:
			if(trap.normalBlock[11] == false &&  trap.normalBlock[10] == false){
				trap.normalBlockX[11] = displayX + 400 * tune;
			}
			break;
		case 3:
			if(trap.normalBlock[11] == false &&  trap.normalBlock[10] == false){
				trap.normalBlockX[11] = displayX + 310 * tune;
			}
			break;
		case 4:
			if(trap.normalBlock[11] == false &&  trap.normalBlock[10] == false){
				trap.normalBlockX[11] = displayX + 400 * tune;
			}
			break;
		
		}
	}
	
	
 public void loadBGM(){
	 SharedPreferences bgm = 
             getSharedPreferences( "bgmDate", Context.MODE_PRIVATE );
		  bgmState = bgm.getInt( "bgmDate", 0 );

	 
 }
 
 //オプションのBGMのオン、オフ設定を
  //記録しておく メソッド
 public void saveBGM(){
	 SharedPreferences bgm = 
             getSharedPreferences( "bgmDate", Context.MODE_PRIVATE );

     // プリファレンスに書き込むためのEditorオブジェクト取得 //
     Editor editor = bgm.edit();

     // "" というキーで名前を登録
     editor.putInt( "bgmDate", bgmState );
     
     // 書き込みの確定（実際にファイルに書き込む）
     editor.commit();	

 }
 
 
 //バックキーを押したときの動作
 @Override
 public boolean onKeyDown(int keyCode, KeyEvent event) {
   if(keyCode==KeyEvent.KEYCODE_BACK){
     
	   
	   //プレイ画面ならポーズ状態にする
	   if(gameState == GAME_PLAY){
			
			if(playState == PLAY_NORMAL){
			
				se.play(sePause, 1.0F, 1.0F, 0, 0, 1.0F);
				playState = PLAY_PAUSE;
				
					
					return true;
				
			}	
	   }
   }
   return false;
 }
 
//低ストーンゲット
		public void getStoneLow(){
			if( score.combo < score.limitCombo){
				score.combo++;
			}
			score.comboTimer = 1;
			score.point = score.point + stone.lowPoint;
			if( stone.have[0] < stone.haveLimit){	
				stone.have[0]++;
			}	
		}
		
		//中ストーンゲット
		public void getStoneMiddle(){
			if( score.combo < score.limitCombo){
				score.combo++;
			}
			score.comboTimer = 1;
			score.point = score.point + stone.middlePoint;
			if( stone.have[1] < stone.haveLimit){	
				stone.have[1]++;
			}	
		}

		
		//高ストーンゲット
		public void getStoneHigh(){
			if( score.combo < score.limitCombo){
				score.combo++;
			}
			score.comboTimer = 1;
			score.point = score.point + stone.highPoint;
			if( stone.have[2] < stone.haveLimit){	
				stone.have[2]++;
			}	
		}
		
		//キラストーン赤ゲット
		public void getStoneRed(){
			if( score.combo < score.limitCombo){
				score.combo++;
			}
			score.comboTimer = 1;
			score.point = score.point + stone.kiraPoint;
			if( stone.have[3] < stone.haveLimit){	
				stone.have[3]++;
			}
			switch(stone.countStock){
			
			case 0:
				stone.leftStock = Stone.STOCK_RED;
				stone.countStock++;
			break;
			
			case 1:
				stone.rightStock = Stone.STOCK_RED;
				stone.countStock++;
			break;
			
			case 2:
				stone.leftStock = stone.rightStock;
				stone.rightStock = Stone.STOCK_RED;
				
			break;
			}
			
		}
		

		//キラストーン緑ゲット
		public void getStoneGreen(){
			if( score.combo < score.limitCombo){
				score.combo++;
			}
			score.comboTimer = 1;
			//キラポイント分、スコアにポイント追加
			score.point = score.point + stone.kiraPoint;
			
			//所持数カンスト対策用	
			if( stone.have[4] < stone.haveLimit){
				stone.have[4]++;
			}
			//
				switch(stone.countStock){
				
				case 0:
					stone.leftStock = Stone.STOCK_GREEN;
					stone.countStock++;
				break;
				
				case 1:
					stone.rightStock = Stone.STOCK_GREEN;
					stone.countStock++;
				break;
				
				case 2:
					stone.leftStock = stone.rightStock;
					stone.rightStock = Stone.STOCK_GREEN;
					
				break;
				}
				
			}
			
		//キラストーン青ゲット
		public void getStoneBlue(){
			if( score.combo < score.limitCombo){
				score.combo++;
			}
			score.comboTimer = 1;
			score.point = score.point + stone.kiraPoint;
				if( stone.have[5] < stone.haveLimit){
					stone.have[5]++;
				}
				switch(stone.countStock){
				
				case 0:
					stone.leftStock = Stone.STOCK_BLUE;
					stone.countStock++;
				break;
				
				case 1:
					stone.rightStock = Stone.STOCK_BLUE;
					stone.countStock++;
				break;
				
				case 2:
					stone.leftStock = stone.rightStock;
					stone.rightStock = Stone.STOCK_BLUE;
					
				break;
				}
				
			}	
	//画面遷移時の チラツキ防止
		public void loadBlack( Canvas canvas){
			Paint load = new Paint();
			load.setColor(Color.argb(255, 0, 0, 0));
			Rect display = new Rect(0, 0, displayX, displayY);
			canvas.drawRect(display, load);
			
		}
		
	//チュートリアルの詳細
		public void getTutorial( Canvas canvas){
			/*チュートリアルのシーン区分け
			 * FASE_STONE 鉱石について
			 * FASE_LADDER はしごについて
			 * FASE_TRAP 罠について
			 * FASE_FREE 罠避けの練習
			 * FASE_KIRA キラストーンについて
			*/
			switch(tutorialState){

			case FASE_STONE:
				
				
				switch(faseState){
				
					case 0:
						moveSpeed = 0;
						canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
						canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
						
						textTimer++;
						get3text( canvas); 
						if(textTimer == 600){
							text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_text1);
							text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_text2);
							text[2] = BitmapFactory.decodeResource(res, R.drawable.fase_text3);
							faseState = 50;
							textTimer = 0;
						}
					break;
				
					case 50:
						
						textTimer++;
						moveSpeed = 0;
						canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
						canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
						
						
							canvas.drawBitmap( text[0] , 260 * tune , 130 * tune, null);
							
						 if(textTimer == 150){
							
							 canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
								canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
								canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
								canvas.drawBitmap( text[0] , 260 * tune , 130 * tune, null);
							faseState = 51;
							textTimer = 0;
						}
					break;
					
					case 51:
						textTimer++;
						canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
						canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( text[1] , 260 * tune , 130 * tune, null);
						 if(0 <= textTimer && textTimer <150 ){
							 canvas.drawBitmap( text[2] , 260 * tune , 180 * tune, null); 
							 
						 }
						
						if(textTimer == 150){
							canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
							canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
							canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
							canvas.drawBitmap( text[1] , 260 * tune , 130 * tune, null);
							canvas.drawBitmap( text[2] , 260 * tune , 180 * tune, null); 
							text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_text4);
							text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_text5);
							text[2] = BitmapFactory.decodeResource(res, R.drawable.fase_text6);
							
							 
								
							faseState = 52;
							textTimer = 0;
						}
						
					break;
					
					case 52:
						textTimer++;
						canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
						canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( text[0] , 260 * tune , 130 * tune, null);
						 if(0 <= textTimer && textTimer <150 ){
							 canvas.drawBitmap( text[1] , 260 * tune , 180 * tune, null); 
							 
						 }
						
						if(textTimer == 150){
								
							 canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
								canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
								canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
								canvas.drawBitmap( text[0] , 260 * tune , 130 * tune, null);
								canvas.drawBitmap( text[1] , 260 * tune , 180 * tune, null); 
							faseState = 53;
							textTimer = 0;
						}
					break;
					
					case 53:
						textTimer++;
						moveSpeed = 0;
						canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( reviSmile , 50 * tune , 100 * tune, null);
						canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
						
						
							canvas.drawBitmap( text[2] , 260 * tune , 130 * tune, null);
							
						 if(textTimer == 150){
							
							 canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
								canvas.drawBitmap( reviSmile , 50 * tune , 100 * tune, null);
								canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
								canvas.drawBitmap( text[2] , 260 * tune , 130 * tune, null);
								text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_text2);
							
							faseState = 200 ;
							textTimer = 0;
						}
					break;
					
					case 200:
						textTimer++;
						moveSpeed = 0;
						canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
						canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
						
						
							canvas.drawBitmap( text[0] , 260 * tune , 130 * tune, null);
							
						 if(textTimer == 150){
						
								text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_stone1_1);
								text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_stone1_2);
								text[2] = BitmapFactory.decodeResource(res, R.drawable.fase_stone1_3);
							
							faseState = 1 ;
							textTimer = 0;
						}
					break;
					case 1:
						if( stone.kind[0][0] == false){
							stone.x[0][0] = displayX * 3 / 2;
							stone.y[0][0] = 505 * tune;	
						}
						
						tutorialCreate();
						moveSpeed = 5;
						
					monyako.walkTimer++;
					if( stone.x[0][0] < displayX - 180 * tune){
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_stone2_1);
						text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_stone2_2);
						text[2] = BitmapFactory.decodeResource(res, R.drawable.fase_stone2_3);
						
						faseState = 2;
					}
					
					break;
					
					case 2:
						moveSpeed = 0;
						canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
						if(textTimer <= 400){
							canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
						}else if( 400 <= textTimer && textTimer <= 600){
							canvas.drawBitmap( reviSmile , 50 * tune , 100 * tune, null);
							
						}
						canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( strong , stone.x[0][0] - 120 * tune , 425 * tune, null);
						
						textTimer++;
						get3text( canvas);
						
						if(textTimer == 600){
							
							faseState = 3;
							textTimer = 0;
						}
							
						
					break;
					
					case 3:
						moveSpeed = 5;
						monyako.walkTimer++;
						if( stone.kind[0][0] == false){
							text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_stone4_1);
							text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_stone4_2);
							text[2] = BitmapFactory.decodeResource(res, R.drawable.tutorial_stone);
							
							faseState = 4;
						}
					break;
					
					case 4:
						canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
						canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
						textTimer++;
						moveSpeed = 0;
						if( 100 <= textTimer && textTimer <= 250){
							canvas.drawBitmap( text[0] , 260 * tune , 130 * tune, null);
							canvas.drawBitmap( text[2] , 240 * tune , 150 * tune, null);
						} else if( 250 <= textTimer && textTimer <= 400){
							canvas.drawBitmap( text[1] , 260 * tune , 130 * tune, null);
							canvas.drawBitmap( text[2] , 240 * tune , 150 * tune, null);
						} if(textTimer == 400){
							text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_text3);
							tutorialState = FASE_LADDER;
							faseState = 300;
							textTimer = 0;
						}
					break;
					
					case 100:
						gamePause(canvas);
						
					break;
					
					case 101:
						tutorialSkip( canvas);
						   
					
					break;
					
					case 102:
						if( 5 <= fadeTimer && fadeTimer < 255){
							fadeTimer = fadeTimer + 10;
							Paint black = new Paint();
							black.setColor(Color.argb(fadeTimer, 0, 0, 0));
							Rect rect = new Rect(0, 0, displayX, displayY);
						    canvas.drawRect(rect, black);
						   
						}else if(fadeTimer == 255){
							Paint black = new Paint();
							black.setColor(Color.argb(fadeTimer, 0, 0, 0));
							Rect rect = new Rect(0, 0, displayX, displayY);
						    canvas.drawRect(rect, black);
						  
						    text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_text3);
						    stone.kind[0][0] = false;
						    tutorialState = FASE_LADDER;
							faseState = 300 ;
							fadeCheck = false;
							fadeTimer = 0;
							textTimer = 0;
						}
						
						   
					
					break;
				}
				
				
					
					
				
				
				break;
				
				//はしごフェイズ
			case FASE_LADDER:
				switch(faseState){
				case 300:
					textTimer++;
					moveSpeed = 0;
					canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
					canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					
					
						canvas.drawBitmap( text[0] , 260 * tune , 130 * tune, null);
						
					 if(textTimer == 150){
					
							text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_stone4_1);
							text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_stone4_2);
							text[2] = BitmapFactory.decodeResource(res, R.drawable.tutorial_stone);
						faseState = 5 ;
						textTimer = 0;
					}
				break;
				
				case 5:
					
					if( stone.kind[1][0] == false){
						stone.x[1][0] = displayX * 3 / 2;
						stone.y[1][0] = 275 * tune;	
					}
					tutorialCreate();
					moveSpeed = 5;
					monyako.walkTimer++;
					if( stone.x[1][0] < displayX - 180 * tune){
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_ladder6_1);
						text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_ladder6_2);
						text[2] = BitmapFactory.decodeResource(res, R.drawable.fase_ladder6_3);
						
						faseState = 6;
					}
				break;
				
				case 6:
					moveSpeed = 0;
					canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
					if(textTimer <= 400){
						canvas.drawBitmap( reviShock , 50 * tune , 100 * tune, null);
					}else if( 400 <= textTimer && textTimer <= 600){
						canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
						
					}
					canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( strong , stone.x[1][0] - 120 * tune , 195 * tune, null);
					textTimer++;
					get3text(canvas);
					
					if(textTimer == 600){
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_ladder7_1);
						
						faseState = 7;
						textTimer = 0;
					}
				break;
				
				case 7:
					canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
					canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( text[0] , 260 * tune , 180 * tune, null);
					canvas.drawBitmap( finger , 585 * tune , 560 * tune, null);
					canvas.drawBitmap( thisTouch , 510 * tune , 485 * tune, null);
					if( ladder.state[0] == SET){
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_ladder8_1);
						text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_ladder8_2);
						text[2] = BitmapFactory.decodeResource(res, R.drawable.fase_ladder8_3);
						
						
						faseState = 8;
						
					}
				break;
				
				case 8:
					
					monyakoAction();
					canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( reviSmile , 50 * tune , 100 * tune, null);
					canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( text[0] , 260 * tune , 130 * tune, null);
					canvas.drawBitmap( text[1] , 260 * tune , 180 * tune, null);
					canvas.drawBitmap( text[2] , 260 * tune , 230 * tune, null);
					if( monyako.y == 325 * tune){
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_ladder9_1);
						text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_text4);
						
						faseState = 9;
						
					}
				break;
				
				case 9:
					
					monyakoAction();
					canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
					canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( text[0] , 260 * tune , 180 * tune, null);
					if( stone.kind[1][0] == false){
						faseState = 400;
						tutorialState = FASE_TRAP;
						
					}
				break;
				
				
				case 100:
					gamePause(canvas);
					
				break;
				
				
				case 101:
					tutorialSkip( canvas);
				   
			
					break;
				case 102:
						
					if( 5 <= fadeTimer && fadeTimer < 255){
						fadeTimer = fadeTimer + 10;
						Paint black = new Paint();
						black.setColor(Color.argb(fadeTimer, 0, 0, 0));
						Rect rect = new Rect(0, 0, displayX, displayY);
					    canvas.drawRect(rect, black);
					   
					}else if(fadeTimer == 255){
						Paint black = new Paint();
						black.setColor(Color.argb(fadeTimer, 0, 0, 0));
						Rect rect = new Rect(0, 0, displayX, displayY);
					    canvas.drawRect(rect, black);
					  
					   text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_text4);
						
					   stone.kind[1][0] = false; 
						ladder.state[0] = NOT; 
					    tutorialState = FASE_TRAP;
						monyako.y = 325 * tune;
						faseState = 400 ;
						fadeCheck = false;
						fadeTimer = 0;
						textTimer = 0;
					}
					
					
					break;
				}
				break;
				
				
				
			case FASE_TRAP:
				if( trap.normalBlock[0] == false ){
					trap.normalBlockX[0] = displayX * 3 / 2;
					trap.normalBlockY[0] = 255 * tune;	
				}
				switch(faseState){
				case 400:
					textTimer++;
					moveSpeed = 0;
					canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
					canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					
					
						canvas.drawBitmap( text[1] , 260 * tune , 130 * tune, null);
						
					 if(textTimer == 150){
					
							faseState = 10 ;
						textTimer = 0;
					}
				break;
				case 10:
					tutorialCreate();
					moveSpeed = 5;
					
				monyako.walkTimer++;
				if( trap.normalBlockX[0] < displayX - 180 * tune){
					text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_trap11_1);
					text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_trap11_2);
					text[2] = BitmapFactory.decodeResource(res, R.drawable.fase_trap11_3);
					
					faseState = 11;
				
				}
				break;
				
				case 11:
					moveSpeed = 0;
					canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
					if(textTimer <= 400){
						canvas.drawBitmap( reviShock , 50 * tune , 100 * tune, null);
					}else if( 400 <= textTimer && textTimer <= 600){
						canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
						
					}
					
					canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( strong , trap.normalBlockX[0] - 120 * tune , 195 * tune, null);
					textTimer++;
					get3text(canvas);
					if(textTimer == 600){
										
						faseState = 12;
						textTimer = 0;
					}
				break;
				
				case 12:
					canvas.drawBitmap( finger , 585 * tune , 330 * tune, null);
					canvas.drawBitmap( thisTouch , 510 * tune , 270 * tune, null);
					
					if( ladder.state[0] == SET){
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_trap13_1);
						text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_trap13_2);
						
						faseState = 13;
						
					}
				break;
				
				case 13:
					canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( reviSad , 50 * tune , 100 * tune, null);
					canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( text[0] , 240 * tune , 155 * tune, null);
					canvas.drawBitmap( text[1] , 240 * tune , 205 * tune, null);
					monyakoAction();
					if( monyako.y == 95 * tune){
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_trap14_1);
						text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_trap14_2);
						text[2] = BitmapFactory.decodeResource(res, R.drawable.tutorial_trap);
						
						faseState = 14;
						
					}
					
				break;
				
				case 14:
					textTimer++;
					moveSpeed = 0;
					canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
					canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					
					if( 100 <= textTimer && textTimer < 250){
						canvas.drawBitmap( text[0] , 260 * tune , 130 * tune, null);
						canvas.drawBitmap( text[2] , 205 * tune , 150 * tune, null);
					} else if(textTimer == 250){
						
						
						faseState = 15;
						textTimer = 0;
					}
				break;
				
				case 15:
					
					monyakoAction();
				
					if( trap.normalBlock[0] == false){
						
						tutorialState = FASE_FREE;
						faseState = 16;
					}
				break;
				case 100:
					gamePause(canvas);
					
				break;
				
				case 101:
					tutorialSkip( canvas);
			
					break;

				case 102:
						
					if( 5 <= fadeTimer && fadeTimer < 255){
						fadeTimer = fadeTimer + 10;
						Paint black = new Paint();
						black.setColor(Color.argb(fadeTimer, 0, 0, 0));
						Rect rect = new Rect(0, 0, displayX, displayY);
					    canvas.drawRect(rect, black);
					   
					}else if(fadeTimer == 255){
						Paint black = new Paint();
						black.setColor(Color.argb(fadeTimer, 0, 0, 0));
						Rect rect = new Rect(0, 0, displayX, displayY);
					    canvas.drawRect(rect, black);
					  
					    text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_text5);
					    trap.normalBlock[0] = false;
					    ladder.state[0] = NOT;
					    
						tutorialState = FASE_KIRA;
						monyako.y = 325 * tune;
						faseState = 500 ;
						fadeCheck = false;
						fadeTimer = 0;
						textTimer = 0;
					}
					
					
					break;
				}
					
					
				break;
				
				
			case FASE_FREE:
				
				switch(faseState){
				
				
				case 16:
					trap.kiraBlock[0][0] = false;
					trap.normalBlock[3] = false;
					trap.kiraBlock[1][0] = false;
					moveSpeed = 0;
					playState = 0;
					canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
					canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( text[1] , 260 * tune , 130 * tune, null);
					textTimer++;
					
					if(textTimer > 100){
						faseState = 17;
						textTimer = 0;
						
					}
				break;
				
				
				case 17:
					if( trap.normalBlock[3] == false){
						trap.normalBlockX[3] = displayX * 3 / 2;
						trap.normalBlockY[3] = 25 * tune;	
					}
					if( trap.kiraBlock[0][0] == false ){
						trap.kiraBlockX[0][0] = displayX * 3 / 2 + 200 * tune;
						trap.kiraBlockY[0][0] = 255 * tune;	
					}
					tutorialCreate();
					monyakoAction();
				
					if(playState == PLAY_OVER){
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_trap15_1);
						text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_trap15_2);
						seDamageLoad = false;
						faseState = 20;
					}
					
					if( trap.kiraBlockX[0][0] <= -200 * tune){
						trap.kiraBlock[0][0] = false;
						trap.normalBlock[3] = false;
						faseState = 18;
					}
					
				break;
				
				case 18:
					if( trap.normalBlock[3] == false){
						trap.normalBlockX[3] = (displayX * 3 / 2 )+ 720 * tune;
						trap.normalBlockY[3] = 25 * tune;	
					}
					if( trap.kiraBlock[0][0] == false  ){
						trap.kiraBlockX[0][0] = (displayX * 3 / 2 )+ 200 * tune;
						trap.kiraBlockY[0][0] = 255 * tune;	
					}
					if( trap.kiraBlock[1][0] == false){
						trap.kiraBlockX[1][0] = displayX * 3 / 2   ;
						trap.kiraBlockY[1][0] = 485 * tune;	
					}
					if(playState == PLAY_OVER){
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_trap15_1);
						text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_trap15_2);
						
						faseState = 20;
						seDamageLoad = false;
					}
					tutorialCreate();
					monyakoAction();
					
					if(trap.normalBlockX[3] <= -200 * tune ){
						
						trap.kiraBlock[0][0] = false;
						trap.normalBlock[3] = false;
						trap.kiraBlock[1][0] = false;
						
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_trap16_1);
						text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_trap16_2);
						text[2] = BitmapFactory.decodeResource(res, R.drawable.fase_trap16_3);
						faseState = 21;
					}
				
				break;
				
				case 19:
					moveSpeed = 0;
					canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
					canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					
					textTimer++;
					get3text( canvas); 
					if(textTimer == 600){
						
						faseState = 22;
						textTimer = 0;
						tutorialState = FASE_KIRA;
						for( int i = 0 ; i < 4 ; i++){
					    	ladder.state[i] = NOT;
					    	ladder.x[i] = NOT;
					    }
						ladder.count = 0;
						
					}
				break;
				
				case 20:
					moveSpeed = 0;
					if(fadeTimer == 0 ){
						canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
						if( textTimer <= 100){
							canvas.drawBitmap( reviSad , 50 * tune , 100 * tune, null);
						}else if ( 100 <= textTimer && textTimer <= 250){
							canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
						}
						canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					
						canvas.drawBitmap( text[0] , 240 * tune , 155 * tune, null);
					}
					textTimer++;
					if( 100 <= textTimer && textTimer < 250){
						canvas.drawBitmap( text[0] , 240 * tune , 155 * tune, null);
						canvas.drawBitmap( text[1] , 240 * tune , 205 * tune, null);
					} else if( 250 == textTimer  ){
						fadeTimer = 5;
						
					}
					
					if( 5 <= fadeTimer && fadeTimer < 255){
						
						fadeTimer = fadeTimer + 5;
						Paint black = new Paint();
						black.setColor(Color.argb(fadeTimer, 0, 0, 0));
						Rect rect = new Rect(0, 0, displayX, displayY);
					    canvas.drawRect(rect, black);
					    
					}else if(fadeTimer == 255){
						Paint black = new Paint();
						black.setColor(Color.argb(fadeTimer, 0, 0, 0));
						Rect rect = new Rect(0, 0, displayX, displayY);
					    canvas.drawRect(rect, black);
					    
					    trap.normalBlock[3] = false; 
					    trap.normalBlockX[3] = displayX * 3 / 2;
						trap.kiraBlock[0][0] = false;
						trap.kiraBlockX[0][0] = displayX * 3 / 2 + 200 * tune;
						trap.kiraBlock[1][0] = false;
						monyako.y = 95 * tune;
						for( int i = 0 ; i < 4 ; i++){
					    	ladder.state[i] = NOT;
					    	ladder.x[i] = displayX;
					    }
						ladder.count = 0;
						
						playState = 0;
						loadCheck = false;
						faseState = 17;
						textTimer = 0;
						fadeTimer = 0;
						    
					}
						
				
				break;
					
				case 21:
					monyakoAction();
					
					if(ladder.state[0] == NOT && ladder.state[1] == NOT && ladder.state[2] == NOT && ladder.state[3] == NOT){
						text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_text5);
						
						tutorialState = FASE_KIRA;
						faseState = 500;
					}
				break;
				case 100:
					gamePause(canvas);
					
				break;
				
				case 101:
					tutorialSkip( canvas);
				   
			
					break;
					
				case 102:
							
					if( 5 <= fadeTimer && fadeTimer < 255){
						fadeTimer = fadeTimer + 10;
						Paint black = new Paint();
						black.setColor(Color.argb(fadeTimer, 0, 0, 0));
						Rect rect = new Rect(0, 0, displayX, displayY);
					    canvas.drawRect(rect, black);
					   
					}else if(fadeTimer == 255){
						
						Paint black = new Paint();
						black.setColor(Color.argb(fadeTimer, 0, 0, 0));
						Rect rect = new Rect(0, 0, displayX, displayY);
					    canvas.drawRect(rect, black);
					  
					    text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_text5);
					    for( int i = 0 ; i < 4 ; i++){
					    	ladder.state[i] = NOT;
					    }
					    trap.normalBlock[3] = false;
						trap.kiraBlock[0][0] = false;
						trap.kiraBlock[1][0] = false;
					    tutorialState = FASE_KIRA;
						monyako.y = 325 * tune;
						faseState = 500 ;
						fadeCheck = false;
						fadeTimer = 0;
						textTimer = 0;
					}
					
					break;
				}
				
				
			break;
				
			case FASE_KIRA:
			
				switch(faseState){
				case 500:
					textTimer++;
					moveSpeed = 0;
					canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
					canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					
					
						canvas.drawBitmap( text[1] , 260 * tune , 130 * tune, null);
						
					 if(textTimer == 150){
					
							faseState = 22 ;
						textTimer = 0;
					}
				break;
				case 22:
					if( !stone.kind[3][0]){
						stone.x[3][0] = displayX * 3 / 2;
						trap.kiraBlockX[0][0] = displayX;	
					}
					tutorialCreate();
					monyakoAction();
					
					if( stone.x[3][0] < displayX - 180 * tune){
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_kira18_1);
					
						
						faseState = 23;
					}
				break;
				
				case 23:
					moveSpeed = 0;
					canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( reviSmile , 50 * tune , 100 * tune, null);
					canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( text[0] , 240 * tune , 180 * tune, null);
					
					canvas.drawBitmap( strong , stone.x[3][0] - 120 * tune , 195 * tune, null);
					textTimer++;
					if( textTimer > 100){
						if( monyako.y == 95 * tune){
							faseState = 24;
							
						} else if( monyako.y == 325 * tune){
							faseState = 26;
							
						} else if( monyako.y == 555 * tune){
							faseState = 25;
							
						}
					}
				break;
				
				
				case 24:
					
					canvas.drawBitmap( finger , 585 * tune , 330 * tune, null);
					canvas.drawBitmap( thisTouch , 510 * tune , 270 * tune, null);
					if(ladder.state[0] == SET){
						
						faseState = 26;
					}
				break;
					
				case 25:
					

					canvas.drawBitmap( finger , 585 * tune , 560 * tune, null);
					canvas.drawBitmap( thisTouch , 510 * tune , 485 * tune, null);
					if(ladder.state[0] == SET){
						
						faseState = 26;
					}
				break;
				
				case 26:
					monyakoAction();
					
					if( !stone.kind[3][0] ){
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_kira18_2);
						text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_kira18_3);
						text[2] = BitmapFactory.decodeResource(res, R.drawable.fase_kira18_4);
						faseState = 27;
						textTimer = 0;
					}
				break;
				
				case 27:
					canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( reviSmile , 50 * tune , 100 * tune, null);
					canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					moveSpeed = 0;
					textTimer++;
					get3text(canvas);
					if(250 <= textTimer && textTimer < 600 ){
						
						canvas.drawBitmap( it , 415 * tune , 0, null);
					} else if(textTimer == 600){
						
						faseState = 28;
						textTimer = 0;
					}
				break;
				
				case 28:
					if( trap.normalBlock[3] == false ){
						trap.normalBlockX[3] = displayX * 3 / 2;
					
					}
					if( trap.kiraBlock[0][0] ){
						trap.kiraBlockX[0][0] = displayX * 3 / 2;
							
					}
					if( trap.normalBlock[5] == false ){
						trap.normalBlockX[5] = displayX * 3 / 2;
					
					}
					tutorialCreate();
					monyakoAction();
					if( trap.kiraBlockX[0][0] < displayX - 180 * tune){
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_kira20_1);
						text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_kira20_2);
						text[2] = BitmapFactory.decodeResource(res, R.drawable.fase_kira20_3);
						
						faseState = 29;
					}
				break;
				
				case 29:
					moveSpeed = 0;
					canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( reviSad , 50 * tune , 100 * tune, null);
					canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					
					textTimer++;
					get3text(canvas);
					if( textTimer == 600){
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_kira21_1);
						text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_kira21_2);
						
						faseState = 30;
						textTimer = 0;
					}
				break;
				
				case 30:
					
					
					textTimer++;
					if( 0 <= textTimer && textTimer < 100){
						canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( reviSmile , 50 * tune , 100 * tune, null);
						canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( text[0] , 260 * tune , 155 * tune, null);
					} else if( 100 <= textTimer && textTimer < 250){
						canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( reviSmile , 50 * tune , 100 * tune, null);
						canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( text[0] , 260 * tune , 155 * tune, null);
						canvas.drawBitmap( text[1] , 260 * tune , 205 * tune, null);
					} else if( 250 <= textTimer ){
						
						canvas.drawBitmap( finger , 515 * tune , 50 * tune, null);
						canvas.drawBitmap( thisTouch , 475 * tune, 170 * tune, null);
						
					}
					if( monyako.color == Monyako.SET_RED){
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_kira22_1);
						text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_kira22_2);
						faseState = 31;
						textTimer = 0;
					}
					
					
				break;
				
				case 31:
					textTimer++;
					if( 0 <= textTimer && textTimer < 100){
						canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( reviShock , 50 * tune , 100 * tune, null);
						canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( text[0] , 260 * tune , 155 * tune, null);
					} else if( 100 <= textTimer && textTimer < 250){
						canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( reviShock , 50 * tune , 100 * tune, null);
						canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( text[0] , 260 * tune , 155 * tune, null);
						canvas.drawBitmap( text[1] , 260 * tune , 205 * tune, null);
					} else if( 250 <= textTimer ){
						canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( reviShock , 50 * tune , 100 * tune, null);
						canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_kira22_3);
						text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_kira22_4);
						textTimer = 0;
						faseState = 32;
						
					}
					
				break;
				case 32:
					textTimer++;
					if( 0 <= textTimer && textTimer < 100){
						canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
						canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( text[0] , 260 * tune , 155 * tune, null);
					} else if( 100 <= textTimer && textTimer < 250){
						canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
						canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( text[0] , 260 * tune , 155 * tune, null);
						canvas.drawBitmap( text[1] , 260 * tune , 205 * tune, null);
					} else if( 250 <= textTimer ){
						canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
						canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
						canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_kira22_5);
						text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_kira22_6);
						text[2] = BitmapFactory.decodeResource(res, R.drawable.fase_kira22_7);
						
						textTimer = 0;
						faseState = 33;
						
					}
				break;
				
				case 33:
					canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( reviNormal , 50 * tune , 100 * tune, null);
					canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					textTimer++;
					get3text(canvas);
					if( textTimer == 600){
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_kira23_1);
						text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_kira23_2);
						text[2] = BitmapFactory.decodeResource(res, R.drawable.fase_kira23_3);
						textTimer = 0;
						faseState = 34;
					}
				break;
				
				case 34:
					monyakoAction();
					if(monyako.color == 0){
						
						faseState = 35;
						
					}
				break;
				
				case 35:
					canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( reviSmile , 50 * tune , 100 * tune, null);
					canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					moveSpeed = 0;
					
					textTimer++;
					get3text(canvas);
					if( textTimer == 600){
						text[0] = BitmapFactory.decodeResource(res, R.drawable.fase_kira24_1);
						text[1] = BitmapFactory.decodeResource(res, R.drawable.fase_kira24_2);
						text[2] = BitmapFactory.decodeResource(res, R.drawable.fase_kira24_3);
						faseState = 36;
						textTimer = 0;
						
					}
					
				break;
				
				case 36:
					
					monyakoAction();
					
					if(trap.kiraBlockX[0][0] < -200 * tune){
						
						faseState = 37;
					}
				break;
				
				case 37:
					canvas.drawBitmap( textWindow[0] , 15 * tune , 75 * tune, null);
					canvas.drawBitmap( reviSmile , 50 * tune , 100 * tune, null);
					canvas.drawBitmap( textWindow[1] , 15 * tune , 75 * tune, null);
					moveSpeed = 0;
					textTimer++;
					get3text(canvas);
					if( textTimer == 600){
						
						
						fadeTimer = 5;
						
					}
					if(  5  <= fadeTimer &&  fadeTimer < 255){
						fadeTimer = fadeTimer + 5;
						Paint black = new Paint();
						black.setColor(Color.argb(fadeTimer, 0, 0, 0));
						Rect rect = new Rect(0, 0, displayX, displayY);
						canvas.drawRect(rect, black);
					}else if(fadeTimer == 255){
						Paint black = new Paint();
						black.setColor(Color.argb(fadeTimer, 0, 0, 0));
						Rect rect = new Rect(0, 0, displayX, displayY);
						canvas.drawRect(rect, black);
					
						textTimer = 0;
						menuState = MENU_NORMAL;
						faseState = NOT;
						gameState = GAME_MENU;
						fadeCheck = false;
						fadeTimer = 0;
						setPlayDefault();
					
					}
				break;
				case 100:
					gamePause(canvas);
					
				break;
				}
			break;
				
				
			
			
			
			}
		}
}


