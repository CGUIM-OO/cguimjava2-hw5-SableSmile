import java.util.ArrayList;

public class Table {
	public static final int MAXPLAYER=4;	//一牌桌最多4人
	
	private Deck dk;	//存放所有的牌
	private Player[] players;	//存放所有的玩家
	private Dealer dlr;	//存放一莊家
	private int[] pos_betArray=new int[MAXPLAYER];	//存放每個玩家在一牌局下的注
	private int nDecks;
	public Table(int nDeck) {
		this.nDecks=nDecks;
		dk=new Deck(nDeck);	//實體化
		players=new Player[MAXPLAYER];	//初始化並宣告一個長度是MAXPLAYER 的Player array
	}
	
	public void set_player(int pos, Player p) {
		players[pos]=p;	//將Player放到牌桌上，pos為牌桌位置，最多MAXPLAYER人
	}
	public Player[] get_player() {
		return players;	//回傳所有在牌桌上的player
		
	}
	public void set_dealer(Dealer d) {
		dlr=d;	//將莊家放到牌桌上
	}
	public Card get_face_up_card_of_dealer() {		
		return dlr.getOneRoundCard().get(1);	//回傳莊家打開的那張牌(第二張牌)
	}
	private void ask_each_player_about_bets() {
		
		for(int i=0;i<MAXPLAYER;i++) {
			players[i].sayHello();	//每個玩家打招呼
			pos_betArray[i]=players[i].makeBet();	//請每個玩家下注，並將每個玩家下的注存在pos_betArray
		}
		
	}
	private void distribute_cards_to_dealer_and_players() {
		//發牌給玩家
		for(int i=0;i<MAXPLAYER;i++) {	//發兩張打開的牌給玩家
			ArrayList<Card> p_i=new ArrayList<Card>();
			p_i.add(dk.getOneCard(true));
			p_i.add(dk.getOneCard(true));
			players[i].setOneRoundCard(p_i);
		}
		//發牌給莊家
		ArrayList<Card> dl=new ArrayList<Card>();
		dl.add(dk.getOneCard(false));	//先發一張蓋著的牌給莊家
		dl.add(dk.getOneCard(true));	//再發一張打開的牌給莊家
		dlr.setOneRoundCard(dl);
		System.out.print("Dealer's face up card is:");
		get_face_up_card_of_dealer().printCard();	//印出莊家打開的牌
	}
	private void ask_each_player_about_hits() {	
		for(int i=0;i<MAXPLAYER;i++){
			System.out.println(players[i].getName()+"'s Cards now:");
			players[i].printAllCard();	//印出玩家現有的手牌
			boolean hit=false;
			do {	//詢問每個玩家是否要牌
				hit=players[i].hit_me(this);
				if(hit) {	//要牌
					players[i].getOneRoundCard().add(dk.getOneCard(true));	//再發一張
					players[i].setOneRoundCard(players[i].getOneRoundCard());
					System.out.print("Hit! ");
					System.out.println(players[i].getName()+"'s Cards now:");
					for(Card c : players[i].getOneRoundCard()){
						c.printCard();	//印出玩家要牌後的完整牌
					}
				}
				else{	//不要牌
					System.out.println(players[i].getName()+", Pass hit!");
					System.out.println(players[i].getName()+"'s hit is over!");
					
				}	
			}while(hit);
	
		}
	}
	private void ask_dealer_about_hits() {
		if(dlr.hit_me(this))	//詢問莊家是否要牌
			dlr.getOneRoundCard().add(dk.getOneCard(true));
		else
			System.out.println("Dealer's hit is over!");
	}
	private void calculate_chips() {
		
		System.out.println("Dealer's card value is "+ dlr.getTotalValue() +" ,Cards:" );	//印出莊家的點數
		dlr.printAllCard();	//印出莊家的手牌
		for(int i=0;i<MAXPLAYER;i++){	//針對每個玩家，先印出 玩家姓名和總點數
			System.out.print(players[i].getName()+" card value is "+players[i].getTotalValue()+" ,Cards:\n");
			players[i].printAllCard();	//印出玩家的所有牌
			//印出玩家贏得或輸掉的籌碼，以及剩下的籌碼
			if(dlr.getTotalValue()<=21 && players[i].getTotalValue()>21) {	//莊家沒爆，玩家爆了
				players[i].increaseChips(-players[i].makeBet());	//玩家輸，沒收籌碼
				System.out.print(players[i].getName()+", Loss "+players[i].makeBet()+" Chips, the Chips now is: "+players[i].getCurrentChips()+"\n");
			}
			else if(dlr.getTotalValue()>21 && players[i].getTotalValue()<=21){	//莊家爆了，玩家沒爆
				players[i].increaseChips(players[i].makeBet());	//玩家贏
				System.out.print(players[i].getName()+",Get "+players[i].makeBet()+" Chips, the Chips now is: "+players[i].getCurrentChips()+"\n");
			}
			else if(dlr.getTotalValue()>players[i].getTotalValue() && dlr.getTotalValue()<=21){	//莊家點數>玩家點數，且莊家點數<=21(沒爆)
				players[i].increaseChips(-players[i].makeBet());	//玩家輸，沒收籌碼
				System.out.print(players[i].getName()+", Loss "+players[i].makeBet()+" Chips, the Chips now is: "+players[i].getCurrentChips()+"\n");
			}
			else if(dlr.getTotalValue()<players[i].getTotalValue() && players[i].getTotalValue()<=21){	//莊家點數<玩家點數，且玩家點數<=21(沒爆)
				players[i].increaseChips(players[i].makeBet());	//玩家贏
				System.out.print(players[i].getName()+",Get "+players[i].makeBet()+" Chips, the Chips now is: "+players[i].getCurrentChips()+"\n");
			}
			else {	//平局(玩家點數=莊家點數，且2者點數皆<=21(沒爆)  or  2者點數接>21(爆了))
				System.out.print(players[i].getName()+",chips have no change! The Chips now is: "+players[i].getCurrentChips()+"\n");
			}
		}
		
	}
	public int[] get_players_bet() {	
		return pos_betArray;	//回傳玩家下的注
	}
	public void play() {
		ask_each_player_about_bets();	//詢問玩家下注
		distribute_cards_to_dealer_and_players();	//莊家、玩家發牌
		ask_each_player_about_hits();	//詢問玩家是否要牌
		ask_dealer_about_hits();	//詢問裝家是否要牌
		calculate_chips();	//計算點數、結算籌碼
	}
}
