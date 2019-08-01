package amata1219.amalib.scoreboard;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

public class Scoreboard {

	//このスコアボードが表示されているプレイヤー
	public final Player player;

	//新しいスコアボードを作成する
	public final org.bukkit.scoreboard.Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
	public final Objective objective;

	//スコアテキストの複製配列
	private String[] texts = new String[15];

	public Scoreboard(Player player, String displayName){
		this.player = player;

		//タイトルはランダムに生成したUUIDの前方16文字を用いる
		objective = board.registerNewObjective(UUID.randomUUID().toString().substring(0, 16), "dummy", displayName);

		//表示位置をサイドバーに設定する
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	}

	public Scoreboard(Player player, String displayName, String... defaultTexts){
		this(player, displayName);

		if(defaultTexts.length > 14)
			throw new IllegalArgumentException("Default texts length be 15 or less");

		//配列の値を前から順にボードの上から下にセットしていく
		for(int i = 0; i < defaultTexts.length; i++)
			setText(14 - i, defaultTexts[i]);
	}

	//スコアボードが表示されているかどうか
	public boolean isDisplay(){
		return board.equals(player.getScoreboard());
	}

	//スコアボードを表示するか設定する
	public void setDisplay(boolean display){
		if(display != isDisplay())
			player.setScoreboard(display ? board : Bukkit.getScoreboardManager().getNewScoreboard());
	}

	//指定されたスコアのテキストを書き換える
	public void updateText(int score, String text){
		//スコアが範囲外であればエラーを投げる
		if(score < 0 || 14 < score)
			throw new IllegalArgumentException("Score must be in the range 0 to 15");

		//指定されたスコアを削除する
		board.resetScores(texts[score]);

		//スコアを書き換える
		setText(score, text);
	}

	public void setText(int score, String text){
		//複製配列にテキストをセットする
		texts[score] = text;

		//スコアをセットする
		objective.getScore(text).setScore(score);
	}

}
