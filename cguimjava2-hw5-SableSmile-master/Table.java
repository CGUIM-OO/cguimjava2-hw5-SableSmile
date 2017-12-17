import java.util.ArrayList;

public class Table {
	public static final int MAXPLAYER=4;	//�@�P��̦h4�H
	
	private Deck dk;	//�s��Ҧ����P
	private Player[] players;	//�s��Ҧ������a
	private Dealer dlr;	//�s��@���a
	private int[] pos_betArray=new int[MAXPLAYER];	//�s��C�Ӫ��a�b�@�P���U���`
	private int nDecks;
	public Table(int nDeck) {
		this.nDecks=nDecks;
		dk=new Deck(nDeck);	//�����
		players=new Player[MAXPLAYER];	//��l�ƨëŧi�@�Ӫ��׬OMAXPLAYER ��Player array
	}
	
	public void set_player(int pos, Player p) {
		players[pos]=p;	//�NPlayer���P��W�Apos���P���m�A�̦hMAXPLAYER�H
	}
	public Player[] get_player() {
		return players;	//�^�ǩҦ��b�P��W��player
		
	}
	public void set_dealer(Dealer d) {
		dlr=d;	//�N���a���P��W
	}
	public Card get_face_up_card_of_dealer() {		
		return dlr.getOneRoundCard().get(1);	//�^�ǲ��a���}�����i�P(�ĤG�i�P)
	}
	private void ask_each_player_about_bets() {
		
		for(int i=0;i<MAXPLAYER;i++) {
			players[i].sayHello();	//�C�Ӫ��a���۩I
			pos_betArray[i]=players[i].makeBet();	//�ШC�Ӫ��a�U�`�A�ñN�C�Ӫ��a�U���`�s�bpos_betArray
		}
		
	}
	private void distribute_cards_to_dealer_and_players() {
		//�o�P�����a
		for(int i=0;i<MAXPLAYER;i++) {	//�o��i���}���P�����a
			ArrayList<Card> p_i=new ArrayList<Card>();
			p_i.add(dk.getOneCard(true));
			p_i.add(dk.getOneCard(true));
			players[i].setOneRoundCard(p_i);
		}
		//�o�P�����a
		ArrayList<Card> dl=new ArrayList<Card>();
		dl.add(dk.getOneCard(false));	//���o�@�i�\�۪��P�����a
		dl.add(dk.getOneCard(true));	//�A�o�@�i���}���P�����a
		dlr.setOneRoundCard(dl);
		System.out.print("Dealer's face up card is:");
		get_face_up_card_of_dealer().printCard();	//�L�X���a���}���P
	}
	private void ask_each_player_about_hits() {	
		for(int i=0;i<MAXPLAYER;i++){
			System.out.println(players[i].getName()+"'s Cards now:");
			players[i].printAllCard();	//�L�X���a�{������P
			boolean hit=false;
			do {	//�߰ݨC�Ӫ��a�O�_�n�P
				hit=players[i].hit_me(this);
				if(hit) {	//�n�P
					players[i].getOneRoundCard().add(dk.getOneCard(true));	//�A�o�@�i
					players[i].setOneRoundCard(players[i].getOneRoundCard());
					System.out.print("Hit! ");
					System.out.println(players[i].getName()+"'s Cards now:");
					for(Card c : players[i].getOneRoundCard()){
						c.printCard();	//�L�X���a�n�P�᪺����P
					}
				}
				else{	//���n�P
					System.out.println(players[i].getName()+", Pass hit!");
					System.out.println(players[i].getName()+"'s hit is over!");
					
				}	
			}while(hit);
	
		}
	}
	private void ask_dealer_about_hits() {
		if(dlr.hit_me(this))	//�߰ݲ��a�O�_�n�P
			dlr.getOneRoundCard().add(dk.getOneCard(true));
		else
			System.out.println("Dealer's hit is over!");
	}
	private void calculate_chips() {
		
		System.out.println("Dealer's card value is "+ dlr.getTotalValue() +" ,Cards:" );	//�L�X���a���I��
		dlr.printAllCard();	//�L�X���a����P
		for(int i=0;i<MAXPLAYER;i++){	//�w��C�Ӫ��a�A���L�X ���a�m�W�M�`�I��
			System.out.print(players[i].getName()+" card value is "+players[i].getTotalValue()+" ,Cards:\n");
			players[i].printAllCard();	//�L�X���a���Ҧ��P
			//�L�X���aĹ�o�ο鱼���w�X�A�H�γѤU���w�X
			if(dlr.getTotalValue()<=21 && players[i].getTotalValue()>21) {	//���a�S�z�A���a�z�F
				players[i].increaseChips(-players[i].makeBet());	//���a��A�S���w�X
				System.out.print(players[i].getName()+", Loss "+players[i].makeBet()+" Chips, the Chips now is: "+players[i].getCurrentChips()+"\n");
			}
			else if(dlr.getTotalValue()>21 && players[i].getTotalValue()<=21){	//���a�z�F�A���a�S�z
				players[i].increaseChips(players[i].makeBet());	//���aĹ
				System.out.print(players[i].getName()+",Get "+players[i].makeBet()+" Chips, the Chips now is: "+players[i].getCurrentChips()+"\n");
			}
			else if(dlr.getTotalValue()>players[i].getTotalValue() && dlr.getTotalValue()<=21){	//���a�I��>���a�I�ơA�B���a�I��<=21(�S�z)
				players[i].increaseChips(-players[i].makeBet());	//���a��A�S���w�X
				System.out.print(players[i].getName()+", Loss "+players[i].makeBet()+" Chips, the Chips now is: "+players[i].getCurrentChips()+"\n");
			}
			else if(dlr.getTotalValue()<players[i].getTotalValue() && players[i].getTotalValue()<=21){	//���a�I��<���a�I�ơA�B���a�I��<=21(�S�z)
				players[i].increaseChips(players[i].makeBet());	//���aĹ
				System.out.print(players[i].getName()+",Get "+players[i].makeBet()+" Chips, the Chips now is: "+players[i].getCurrentChips()+"\n");
			}
			else {	//����(���a�I��=���a�I�ơA�B2���I�Ƭ�<=21(�S�z)  or  2���I�Ʊ�>21(�z�F))
				System.out.print(players[i].getName()+",chips have no change! The Chips now is: "+players[i].getCurrentChips()+"\n");
			}
		}
		
	}
	public int[] get_players_bet() {	
		return pos_betArray;	//�^�Ǫ��a�U���`
	}
	public void play() {
		ask_each_player_about_bets();	//�߰ݪ��a�U�`
		distribute_cards_to_dealer_and_players();	//���a�B���a�o�P
		ask_each_player_about_hits();	//�߰ݪ��a�O�_�n�P
		ask_dealer_about_hits();	//�߰ݸˮa�O�_�n�P
		calculate_chips();	//�p���I�ơB�����w�X
	}
}
