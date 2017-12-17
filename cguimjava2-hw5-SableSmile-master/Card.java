

public class Card {
	enum Suit {Club, Diamond, Heart, Spade};	//���
	Suit suit;  //Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
	private int rank; //1~13	
	/**
	 * @param s suit
	 * @param r rank
	 */
	
	public Card(Suit s,int value){
		suit=s;
		rank=value;
	}
	
	public void printCard(){
		//Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as Clubs Ace
		String rankname="";	//�P���W��
		//�Ʀr�W���ഫ
		if(rank==1){   //�Y�P��=1�A�h�O�P���W�٬�Ace	
			rankname="Ace";
	 	}
		else if(rank==11){	//�Y�P��=11�A�h�O�P���W�٬�Jack
			rankname="Jack";
		}
		else if(rank==12){	//�Y�P��=12�A�h�O�P���W�٬�Queen
			rankname="Queen";
		}
		else if(rank==13){	//�Y�P��=13�A�h�O�P���W�٬�King
			rankname="King";
		}
		else	//��L���p(2~10)�A�h�P���W��=��쥻�Ʀr
			rankname=String.valueOf(rank);
		System.out.println("Card("+getSuit()+","+rankname+")");	//�L�X�Ҧ��d��
	}
	public Suit getSuit(){
		return suit;
	}
	public int getRank(){
		return rank;
	}
}
