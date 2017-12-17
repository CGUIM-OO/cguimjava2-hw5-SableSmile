import java.util.ArrayList;

public class Player extends Person{	//繼承Person  

	@Override
	public boolean hit_me(Table tbl) {	//計算點數、是否要牌
		int total_value = getTotalValue();
		if (total_value < 17)
			return true;
		else if (total_value == 17 && hasAce()) {
			return true;
		} else {
			if (total_value >= 21)
				return false;
			else {
				Player[] players = tbl.get_player();
				int lose_count = 0;
				int v_count = 0;
				int[] betArray = tbl.get_players_bet();
				for (int i = 0; i < players.length; i++) {
					if (players[i] == null) {
						continue;
					}
					if (players[i].getTotalValue() != 0) {
						if (total_value < players[i].getTotalValue()) {
							lose_count += betArray[i];
						} else if (total_value > players[i].getTotalValue()) {
							v_count += betArray[i];
						}
					}
				}
				if (v_count < lose_count)
					return true;
				else
					return false;
			}
		}
	}
	private String name;	//玩家姓名
	private int chips;		//籌碼
	private int bet;		//下注
	
	
	public  Player(String name, int chips){	
		this.name=name;	//姓名參數			
		this.chips=chips;	//有的籌碼參數
	}
	
	public String getName(){
		return name;	//name
	}
	public int makeBet(){
		bet=1;	//下注一次1元
		if(chips==0)	//檢查是否還有籌碼
			return 0;	//沒籌碼不下注
		return bet;
	}
	
	public boolean hitMe(){
		if(getTotalValue()<=16){	//16點以下要牌
			return true;
		}
		else	//17點以上不要牌
			return false;
	}

	public int getCurrentChips(){
		return chips;		//回傳玩家現有籌碼
	}
	public void increaseChips (int diff){
		this.chips+=diff;	//玩家籌碼變動
	}
	public void sayHello(){		//玩家sayHello
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}
}
