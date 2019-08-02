package amata1219.amalib.command;

import amata1219.amalib.text.TextTemplate;

public class TestCommand implements Command {

	@Override
	public void onCommand(Sender sender, Arguments args) {
		//送信者がプレイヤーでなければ戻る
		if(blockNonPlayer(sender))
			return;

		//次の引数を取得する
		switch(args.next()){
		case "int":
			//次の引数がint型に変換可能でなければ戻る
			if(!args.hasNextInt()){
				sender.warn("Argument(index: 1) must be int.");
				return;
			}

			//次の引数をint型の値として取得
			int size = args.nextInt();

			//$インデックスを対応したオブジェクトの文字列に置き換える
			sender.info(TextTemplate.apply("Inputted number is $0", size));
			return;
		case "message":
			//Arguments#getInRange(int, int)は指定範囲内の引数を結合して返す
			sender.info(TextTemplate.apply("Inputted message is $0", args.getInRange(1, args.args.length)));
			return;
		default:
			//上手く使うと不正な引数を実際に示しつつ警告出来る
			sender.warn(TextTemplate.apply("\"$0\"(index: $1) is invalid argument.", args.get(), args.getIndex() - 1));
			return;
		}
	}

}
