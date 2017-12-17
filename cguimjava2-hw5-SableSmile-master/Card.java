

public class Card {
	enum Suit {Club, Diamond, Heart, Spade};	//花色
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
		String rankname="";	//牌號名稱
		//數字名稱轉換
		if(rank==1){   //若牌號=1，則令牌號名稱為Ace	
			rankname="Ace";
	 	}
		else if(rank==11){	//若牌號=11，則令牌號名稱為Jack
			rankname="Jack";
		}
		else if(rank==12){	//若牌號=12，則令牌號名稱為Queen
			rankname="Queen";
		}
		else if(rank==13){	//若牌號=13，則令牌號名稱為King
			rankname="King";
		}
		else	//其他狀況(2~10)，則牌號名稱=其原本數字
			rankname=String.valueOf(rank);
		System.out.println("Card("+getSuit()+","+rankname+")");	//印出所有卡片
	}
	public Suit getSuit(){
		return suit;
	}
	public int getRank(){
		return rank;
	}
}
