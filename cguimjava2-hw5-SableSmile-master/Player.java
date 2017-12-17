import java.util.ArrayList;

public class Player extends Person{	//�~��Person  

	@Override
	public boolean hit_me(Table tbl) {	//�p���I�ơB�O�_�n�P
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
	private String name;	//���a�m�W
	private int chips;		//�w�X
	private int bet;		//�U�`
	
	
	public  Player(String name, int chips){	
		this.name=name;	//�m�W�Ѽ�			
		this.chips=chips;	//�����w�X�Ѽ�
	}
	
	public String getName(){
		return name;	//name
	}
	public int makeBet(){
		bet=1;	//�U�`�@��1��
		if(chips==0)	//�ˬd�O�_�٦��w�X
			return 0;	//�S�w�X���U�`
		return bet;
	}
	
	public boolean hitMe(){
		if(getTotalValue()<=16){	//16�I�H�U�n�P
			return true;
		}
		else	//17�I�H�W���n�P
			return false;
	}

	public int getCurrentChips(){
		return chips;		//�^�Ǫ��a�{���w�X
	}
	public void increaseChips (int diff){
		this.chips+=diff;	//���a�w�X�ܰ�
	}
	public void sayHello(){		//���asayHello
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}
}
