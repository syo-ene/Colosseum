package com.example.colosseumap;

import android.media.AudioAttributes;  // ★ 追加
import android.media.MediaPlayer;      // ★ 追加
import android.media.SoundPool;        // ★ 追加
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.colosseumap.JavaCard.CardList.Card;
import com.example.colosseumap.JavaCard.CardList.*;
import com.example.colosseumap.JavaCard.Main;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int cE = 0;
    private int userSelect = 1, enemySelect = 2, waitPlayer =3, abilityFist = 4, abilityPlayer1Select = 5, abilityFist2 = 6,  abilityPlayer2Select = 7, abilitySecond2 = 8;
    private int flag = userSelect;

    private LinearLayout enemyHand, enemyUse, userHand, userUse;
    private TextView enemyName, enemyText, userName, userText;

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Main main = new Main();
    Card back = new Spell31();

    private int userPiece, enemyPiece;
    private int userPosition = -1, enemyPosition = -1;
    private int end;

    // === 🎵 効果音・BGM ===（★ 追加ここから）
    private SoundPool soundPool;
    private int tapSoundId, battleSoundId, battleBeforeSoundId, userSetSoundId, enemySetSoundId;

    private int userFinSoundId, enemyFinSoundId, userLooseSoundId, enemyLooseSoundId;
    private MediaPlayer bgmPlayer;
    // === 🎵 効果音・BGM ===（★ 追加ここまで）

    private FrameLayout[] boardCells;

    private LinearLayout buttonLine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ★ BGMと効果音を初期化
        initBGM();         // ★ 追加
        initSoundPool();   // ★ 追加

        // --- UI取得 ---
        enemyHand = findViewById(R.id.enemyHand);
        enemyUse = findViewById(R.id.enemyUse);
        enemyName = findViewById(R.id.enemyName);
        enemyText = findViewById(R.id.enemyText);

        userHand = findViewById(R.id.userHand);
        userUse = findViewById(R.id.userUse);
        userName = findViewById(R.id.userName);
        userText = findViewById(R.id.userText);

        userPiece = R.drawable.user_piece;
        enemyPiece = R.drawable.enemy_piece;

        boardCells = new FrameLayout[]{
                findViewById(R.id.board_cell_1),
                findViewById(R.id.board_cell_2),
                findViewById(R.id.board_cell_3),
                findViewById(R.id.board_cell_4),
                findViewById(R.id.board_cell_5),
                findViewById(R.id.board_cell_6),
                findViewById(R.id.board_cell_7)
        };

        buttonLine = findViewById(R.id.buttonLine);

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(v -> {
            resetGame();
        });

        Button plusButton = findViewById(R.id.plusButton);
        plusButton.setOnClickListener(v -> {
            plus();
        });

        Button minusButton = findViewById(R.id.minusButton);
        minusButton.setOnClickListener(v -> {
            minus();
        });




        // --- ゲーム開始処理 ---
        reloadHand();
        movePiece();
    }




    // === 手札関連 ===
    private void reloadHand(){
        reloadUserHand();
        reloadEnemyHand();
    }

    private void reloadUserHand(){
        userHand.removeAllViews();
        for (int i = 0; i < main.player1.getHand().size(); i++) {
            addUserHand(main.player1.getHand().get(i), i);
        }
    }
    private void reloadEnemyHand(){
        enemyHand.removeAllViews();
        for (int i = 0; i < main.player2.getHand().size(); i++) {
            addEnemyHand(main.player2.getHand().get(i), i);
        }
    }

    private void addUserHand(Card card, int index) {
        userHand.post(() -> {
            int width = userHand.getWidth() / 7;
            int height = (int) (width * 1.5f);

            ImageView cardView = createCardView(width, height, card.getImage());

            cardView.setOnClickListener(v -> {
                userHandClickListener(card, index);
            });

            userHand.addView(cardView);
        });
    }

    private void userHandClickListener(Card card, int index) {
        if (flag == userSelect || flag == enemySelect || flag == abilityPlayer1Select || flag == abilityPlayer2Select) {
            addUserUse(card, index);
        }
    }

    private void addEnemyHand(Card card, int index) {
        enemyHand.post(() -> {
            int width = enemyHand.getWidth() / 7;
            int height = (int) (width * 1.5f);

            ImageView cardView = createCardView(width, height, card.getImage());

            // ★ タップ音を追加
            cardView.setOnClickListener(v -> {
                enemyHandClickListener(card, index);
            });

            enemyHand.addView(cardView);
        });
    }

    private void enemyHandClickListener(Card card, int index) {
        if (flag == userSelect || flag == enemySelect || flag == abilityPlayer1Select || flag == abilityPlayer2Select) {
            addEnemyUse(card, index);
        }
    }

    private void addUserUse(Card card, int index) {
        userName.setText(card.getName());
        userText.setText(card.getText());
        userUse.removeAllViews();
        userUse.post(() -> {
            int width = (int) (userUse.getWidth() * 1f);
            int height = (int) (width * 1.5f);
            ImageView cardView = createCardView(width, height, card.getImage());
            cardView.setOnClickListener(v -> userUseClickListener(card, index));
            userUse.addView(cardView);
        });
        soundPool.play(tapSoundId, 1f, 1f, 0, 0, 1f);

    }

    private void userUseClickListener(Card card, int index) {
        if (flag == userSelect) {
            // ★ 自分がカードを出したときの効果音
            soundPool.play(userSetSoundId, 1f, 1f, 0, 0, 1f);

            addUserUse(back, -1);
            this.flag = enemySelect;

            enemyName.setText("");
            enemyText.setText("");
            enemyUse.removeAllViews();
            userText.setText("相手に渡して");

            main.player1.use(index);

        } else if (flag == waitPlayer) {
            abilityFist();
        }
        if(flag == abilityPlayer2Select && !card.getName().equals(new Move1().getName())){
            main.abilityPlayer2(index);
            if(main.player2 == main.first){
                abilityFist2();
            }
            else if(main.player2 == main.second){
                abilitySecond2();
            }
            addUserUse(main.player1.getUseCard().get(0), -2);
            addEnemyUse(main.player2.getUseCard().get(0), -2);
        }
    }

    private void addEnemyUse(Card card, int index) {
        enemyName.setText(card.getName());
        enemyText.setText(card.getText());
        enemyUse.removeAllViews();
        enemyUse.post(() -> {
            int width = (int) (enemyUse.getWidth() * 1f);
            int height = (int) (width * 1.5f);
            ImageView cardView = createCardView(width, height, card.getImage());
            cardView.setOnClickListener(v -> enemyUseClickListener(card, index));
            enemyUse.addView(cardView);
        });
        soundPool.play(tapSoundId, 1f, 1f, 0, 0, 1f);
    }

    private void enemyUseClickListener(Card card, int index) {
        if (flag == enemySelect) {
            // ★ 敵がカードを出したときの効果音

            soundPool.play(enemySetSoundId, 1.2f, 1.2f, 0, 0, 1f);

            addEnemyUse(back, -1);
            addUserUse(back, -1);
            this.flag = waitPlayer;
            enemyText.setText("両プレイヤーがいる時にカードの裏面をタップして");

            main.player2.use(index);

        } else if (flag == waitPlayer) {
            abilityFist();
        }

        if(flag == abilityPlayer1Select && !card.getName().equals(new Move1().getName())){
            main.abilityPlayer1(index);
            if(main.player1 == main.first){
                abilityFist2();
            }
            else if(main.player1 == main.second){
                abilitySecond2();
            }
            addUserUse(main.player1.getUseCard().get(0), -2);
            addEnemyUse(main.player2.getUseCard().get(0), -2);
        }
    }

    private void abilityFist() {
        reloadHand();
        addUserUse(main.player1.getUseCard().get(0), -2);
        addEnemyUse(main.player2.getUseCard().get(0), -2);
        this.flag = abilityFist;
        // ★ バトル前の効果音
        soundPool.play(battleBeforeSoundId, 1.2f, 1.2f, 0, 0, 1f);
        if (!main.first.getUseCard().get(0).getText().contains("①")){
            userHand.postDelayed(() -> {
                abilitySecond();
            }, 500);
        }
        else{
            userHand.postDelayed(() -> {
                showCutIn(R.drawable.cut_in_11);
                userHand.postDelayed(() -> {
                    if (main.first.getUseCard().get(0).getName().equals(new Spell12().getName())){
                        if (main.first == main.player1){
                            this.flag = abilityPlayer1Select;
                        }
                        else if(main.first == main.player2){
                            this.flag = abilityPlayer2Select;
                        }

                    }
                    else {
                        abilityFist2();
                    }
                }, 1000);
            }, 500);
        }
    }


    private void abilityFist2(){
        this.flag = abilityFist2;
        main.abilityFirst();
        reloadHand();
        movePiece();
        userHand.postDelayed(() -> {
            abilitySecond();
        }, 500);
    }

    private void abilitySecond(){
        if (!main.second.getUseCard().get(0).getText().contains("①")){
            forward();
        }
        else{
            showCutIn(R.drawable.cut_in_12);

            userHand.postDelayed(() -> {
                if (main.second.getUseCard().get(0).getName().equals(new Spell12().getName())){
                    if (main.second == main.player1){
                        this.flag = abilityPlayer1Select;
                    }
                    else if(main.second == main.player2){
                        this.flag = abilityPlayer2Select;
                    }
                }
                else {
                    abilitySecond2();
                }
            }, 1000);

        }


    }

    private void abilitySecond2(){
        this.flag = abilitySecond2;
        main.abilitySecond();
        reloadHand();
        movePiece();
        userHand.postDelayed(() -> {
            forward();
        }, 500);
    }

    private void forward(){
        if(0 == main.first.getUseCard().get(0).getForward() + main.second.getUseCard().get(0).getForward()){
            attack();
        }
        else {
            showCutIn(R.drawable.cutin_2);
            this.end = main.move();
            userHand.postDelayed(() -> {
                if (end == 0) {
                    movePiece();
                    userHand.postDelayed(() -> {
                        attack();
                    }, 500);
                } else {
                    int delayMills = 0;
                    if (10 < end) {
                        delayMills += 1000;
                        showCutIn(R.drawable.judge_line);
                    }
                    userHand.postDelayed(() -> {
                        playWinLoseEffect(end);
                    }, delayMills);
                }
            }, 1000);
        }
    }

    private void attack(){
        if(0 == main.first.getUseCard().get(0).getAttack().size() + main.second.getUseCard().get(0).getAttack().size()){
            main.resetUseCard();
            userUse.removeAllViews();
            enemyUse.removeAllViews();
            userName.setText("");
            userText.setText("");
            enemyName.setText("");
            enemyText.setText("");
            reloadHand();
            this.flag = userSelect;
        }else {
            showCutIn(R.drawable.cutin3);
            this.end = main.attack();

            userHand.postDelayed(() -> {
                if (end == 0) {
                    attackEffect(500);
                } else {
                    attackEffect(4000);
                }

                userHand.postDelayed(() -> {
                    if (end == 0) {
                        main.resetUseCard();
                        userUse.removeAllViews();
                        enemyUse.removeAllViews();
                        userName.setText("");
                        userText.setText("");
                        enemyName.setText("");
                        enemyText.setText("");
                        reloadHand();
                        this.flag = userSelect;

                    } else {
                        int delayMills = 0;
                        if (10 < end) {
                            showCutIn(R.drawable.judge_line);
                            delayMills += 1000;
                        }
                        userHand.postDelayed(() -> {
                            playWinLoseEffect(end);
                        }, delayMills);
                    }
                }, 500);
            }, 1000);
        }


    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//        return true;
//    }


//    private void onClickR(View view){
//        resetGame();
//    }

    private void resetGame() {
        // メインロジック側の初期化
        main = new Main();

        // 手札・場のクリア
        userUse.removeAllViews();
        enemyUse.removeAllViews();
        userName.setText("");
        enemyName.setText("");
        userText.setText("");
        enemyText.setText("");

        // 旗（進行状態）を初期状態に戻す
        this.flag = userSelect;

        // 駒の位置リセット
//        userPosition = 0;
//        enemyPosition = 0;

        // 駒を初期マスへ再配置
        movePiece();

        // 手札を再描画
        reloadHand();

        // 効果音（任意）
        soundPool.play(tapSoundId, 1f, 1f, 0, 0, 1f);

        // カットイン消す（もし表示中なら）
        ImageView cutInView = findViewById(R.id.cutIn11);
        cutInView.setVisibility(View.GONE);
    }

    private void plus(){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) buttonLine.getLayoutParams();
        params.weight += 0.3;
        buttonLine.setLayoutParams(params);
    }

    private void minus(){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) buttonLine.getLayoutParams();
        if(0.3 < params.weight) {
            params.weight -= 0.3;
            buttonLine.setLayoutParams(params);
        }
    }






    private ImageView createCardView(int width, int height, int cardResId) {
        ImageView cardView = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.setMargins(8, 0, 8, 0);
        cardView.setLayoutParams(params);
        cardView.setImageResource(cardResId);
        cardView.setTag(cardResId);
        return cardView;
    }

    private void showCutIn(int drawableResId) {
        // ImageView（R.id.cutIn11）を取得
        ImageView cutInView = findViewById(R.id.cutIn11);

        // 指定された画像をImageViewに設定
        cutInView.setImageResource(drawableResId);

        // Viewを表示状態にして、透明度を0（完全に透明）に設定
        cutInView.setVisibility(View.VISIBLE);
        cutInView.setAlpha(0f);

        // フェードイン（透明→不透明）のアニメーションを開始
        cutInView.animate()
                .alpha(1f)              // アルファ値を1（完全に表示）に
                .setDuration(0)       // フェードイン時間：0ミリ秒
                .withEndAction(() ->    // フェードイン終了後に実行する処理
                        cutInView.postDelayed(() -> {
                            // 一定時間（1000ms）後にフェードアウト開始
                            cutInView.animate()
                                    .alpha(0f)             // 不透明→透明へ
                                    .setDuration(0)      // フェードアウト時間：0ミリ秒
                                    .withEndAction(() ->
                                            // 完全に透明になったら非表示に戻す
                                            cutInView.setVisibility(View.GONE))
                                    .start();
                        }, 500)         // 表示維持時間：500ミリ秒（1秒）
                )
                .start();                 // フェードインアニメーションを開始
    }

    private void movePiece(){
        addPiece(userPiece, 7 - main.player1.getLine());
        addPiece(enemyPiece, 1 + main.player2.getLine());
    }
    private void addPiece(int drawableResId, int cellNumber) {
        int[] cellIds = {
                R.id.board_cell_1, R.id.board_cell_2, R.id.board_cell_3,
                R.id.board_cell_4, R.id.board_cell_5, R.id.board_cell_6, R.id.board_cell_7
        };

        if (cellNumber < 1 || cellNumber > cellIds.length) return;

        boolean isUserPiece = drawableResId == userPiece;
        int previousPosition = isUserPiece ? userPosition : enemyPosition;

        if (previousPosition != -1) {
            FrameLayout previousCell = findViewById(cellIds[previousPosition - 1]);
            previousCell.removeAllViews();
        }

        if (isUserPiece) userPosition = cellNumber;
        else enemyPosition = cellNumber;

        FrameLayout targetCell = findViewById(cellIds[cellNumber - 1]);
        ImageView piece = new ImageView(this);
        piece.setImageResource(drawableResId);

        targetCell.post(() -> {
            int cellHeight = targetCell.getHeight();
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(cellHeight, cellHeight);
            params.gravity = Gravity.CENTER;
            piece.setLayoutParams(params);
            targetCell.removeAllViews();
            targetCell.addView(piece);
        });
    }


//柴野メソッド
    // === 🎵 BGMの初期化 ===（★ 追加ここから）
    private void initBGM() {
        bgmPlayer = MediaPlayer.create(this, R.raw.game);
        if (bgmPlayer != null) {
            bgmPlayer.setLooping(true);
            bgmPlayer.setVolume(0.1f, 0.1f);
            bgmPlayer.start();
        }
    }

    // === 🎵 効果音の初期化 ===（★ 追加ここから）
    private void initSoundPool() {
        AudioAttributes attrs = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(attrs)
                .build();

        tapSoundId = soundPool.load(this, R.raw.tap, 1);
        battleSoundId = soundPool.load(this, R.raw.battle, 1);
        battleBeforeSoundId = soundPool.load(this, R.raw.battlebefore, 1);
        userSetSoundId = soundPool.load(this, R.raw.userset, 1);
        enemySetSoundId = soundPool.load(this, R.raw.enemyset, 1);

        //1117 === 🎵 効果音の初期化 ===（追加分）
        userFinSoundId = soundPool.load(this, R.raw.userfin, 1);     // ★ 追加
        enemyFinSoundId = soundPool.load(this, R.raw.enemyfin, 1);   // ★ 追加
        userLooseSoundId = soundPool.load(this, R.raw.userloose, 1); // ★ 追加
        enemyLooseSoundId = soundPool.load(this, R.raw.enemyloose, 1); // ★ 追加
        //1117 === 🎵 効果音の初期化 ===（★ 追加ここまで）
    }
    // === 🎵 効果音の初期化 ===（★ 追加ここまで）
    // ★ 終了時に音リソースを解放
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
        if (bgmPlayer != null) {
            bgmPlayer.stop();
            bgmPlayer.release();
            bgmPlayer = null;
        }
    }

    private void attackEffect(int milliSecond){
        ArrayList<Integer> userAttackCells = new ArrayList<>();
        Card userCard = main.player1.getUseCard().get(0);
        Card enemyCard = main.player2.getUseCard().get(0);
        for (int rel : userCard.getAttack()) {
            int logicalCell = main.player1.getLine() + rel; // 下から見た論理セル
            int uiCell = 6 - logicalCell; // UI上では逆方向（下→上）
            if (uiCell >= 0 && uiCell < 7) userAttackCells.add(uiCell);
        }

        ArrayList<Integer> enemyAttackCells = new ArrayList<>();
        for (int rel : enemyCard.getAttack()) {
            int logicalCell = main.player2.getLine() + rel; // 上から見た論理セル
            int uiCell = logicalCell; // 上→下そのまま
            if (uiCell >= 0 && uiCell < 7) enemyAttackCells.add(uiCell);
        }

        // === ★ エフェクト表示（UI方向で表示）===
        if (!userAttackCells.isEmpty()) {
            BattleCutInManager.showUserBattleIcon(this, boardCells, userAttackCells.get(0), userAttackCells, milliSecond);
        }
        if (!enemyAttackCells.isEmpty()) {
            BattleCutInManager.showEnemyBattleIcon(this, boardCells, enemyAttackCells.get(0), enemyAttackCells , milliSecond);
        }
    }

    //1117 ★ 勝利・敗北 SE を順番に再生するメソッド（追加）
    private void playWinLoseEffect(int winner){
        this.cE++;
        // winner: 1 = user勝利, 2 = enemy勝利

//        if(soundPool == null) return;

        if(winner % 10 == 1){
            // user勝利
            showCutIn(R.drawable.win_first);
            userName.setText("勝ち");
            userText.setText("");
            enemyName.setText("負け");
            enemyText.setText("");
            soundPool.play(userFinSoundId, 1.5f,1.5f,0,0,1f); // 勝利SE
            userHand.postDelayed(() -> {
                soundPool.play(enemyLooseSoundId, 1.5f,1.5f,0,0,1f); // 1秒後 敵敗北SE
            }, 1000);
        }
        else if(winner % 10 == 2){
            // enemy勝利
            showCutIn(R.drawable.win_second);
            userName.setText("負け");
            userText.setText("");
            enemyName.setText("勝ち");
            enemyText.setText("");
            soundPool.play(enemyFinSoundId, 1.5f,1.5f,0,0,1f);
            userHand.postDelayed(() -> {
                soundPool.play(userLooseSoundId, 1.5f,1.5f,0,0,1f);
            }, 1000);
        }
    }
    //1117 ★ 勝利・敗北 SE を順番に再生するメソッド（追加ここまで）
}
