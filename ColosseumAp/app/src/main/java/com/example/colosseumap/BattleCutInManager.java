package com.example.colosseumap;

import android.app.Activity;
import android.os.Handler;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;

public class BattleCutInManager {

    // === ユーザー側（下→上）エフェクト ===
    public static void showUserBattleIcon(Activity activity, FrameLayout[] boardCells, int startCell, ArrayList<Integer> attackCells, int milliSecond) {
        showBattleIcon(activity, boardCells, attackCells, R.drawable.userbattle, milliSecond);
    }

    // === 敵側（上→下）エフェクト ===
    public static void showEnemyBattleIcon(Activity activity, FrameLayout[] boardCells, int startCell, ArrayList<Integer> attackCells, int milliSecond) {
        showBattleIcon(activity, boardCells, attackCells, R.drawable.enemybattle, milliSecond);
    }

    // === 共通描画処理 ===
    private static void showBattleIcon(Activity activity, FrameLayout[] boardCells, ArrayList<Integer> attackCells, int imageRes, int milliSecond) {
        activity.runOnUiThread(() -> {
            for (int attackCell : attackCells) {
                if (attackCell < 0 || attackCell >= boardCells.length) continue;

                FrameLayout targetCell = boardCells[attackCell];
                ImageView explosion = new ImageView(activity);
                explosion.setImageResource(imageRes);

                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                );
                params.gravity = Gravity.CENTER;
                explosion.setLayoutParams(params);
                targetCell.addView(explosion);

                // 5秒後に削除（カードは消さない）
                new Handler().postDelayed(() -> targetCell.removeView(explosion), milliSecond);
            }
        });
    }
}
