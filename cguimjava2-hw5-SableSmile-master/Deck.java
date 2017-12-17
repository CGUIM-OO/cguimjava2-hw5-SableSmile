import java.util.ArrayList;
import java.util.Random;


public class Deck {
	private ArrayList<Card> cards;
	private ArrayList<Card> usedCard;
	private ArrayList<Card> openCard; //¬ö¿ý¥´¶}ªºµP
	public int nUsed;
	public Deck(int nDeck){
		cards=new ArrayList<Card>();
		for(int deck=1;deck<=nDeck;deck++){  		//deck->¦Ü¤Ö­n¤@°ÆµP¡AnDeck->¦Ü¦h­n´X°ÆµP
			for(Card.Suit s : Card.Suit.values()){  //¤@°ÆµP¦³4ºØªá¦â‰²
				for(int rank=1;rank<=13;rank++){	//1ºØªá¦â13±iµP
					Card card=new Card(s,rank);		//¹êÅé¤Æ¥dµP
					cards.add(card);				//·s¼W¥dµP
				}
			}
		}
		shuffle(); 	//¬~µP
	}	
	public Card getOneCard(boolean isOpened){
		
		Card c1=new Card(cards.get(nUsed).getSuit(),cards.get(nUsed).getRank());	//¹êÅé¤Æ¨ÃµoµP
		usedCard.add(cards.get(nUsed));		//±Nµo¥XªºµP¬ö¿ý¦busedCard
		openCard=new ArrayList<Card>();
		if(isOpened) {		//µP¬O¶}µÛªº
			openCard.add(cards.get(nUsed));	//¥[¤JopenCard
		}
		nUsed++;	//¬ö¿ýµo¥XªºµP¼Æ¶q
		
		if(nUsed==52*4) {	//·íµPµo§¹¤F
			shuffle();	//¬~µP
			nUsed=0;	//µo¥XªºµP¼Æ¶qÂk0	
		}
		return c1;
	}
	public void shuffle(){		
		Random rnd = new Random(); 	//¨Ï¥ÎRandom ²£¥Í¤@­Órnd
		openCard=new ArrayList<Card>();	//¹êÅé¤Æ
		nUsed=0;	//µo¥XªºµP¼Æ¶q¬°0
		openCard.clear();  //­«¸m¥´¶}ªºµP;	
		usedCard=new ArrayList<Card>();
		
		/* ¬~µP¹Lµ{¤¤ÀH¾÷¬D¿ï¬Y¤@±iµP¡A
		 * ¿ï¨ìªºµP·|³Q¨Ì§Ç©ñ¤JusedCard¡A
		 * ¨Ã±Ncards¸Ì³Q¿ï¤¤ªºµP²¾°£¡A
		 * ¸g¹L"¬~µP"(°j°é)­«½Æn¦¸«á¡A
		 * usedCard¤¤±o¨ìªº¥dµPªá¦â¡BµP¸¹´N·|³Q¥´¶Ã
		   ¦A±N¬~¦nªºµP±qusedCard©ñ¦^cards°}¦C¤¤*/
		for(int n=0;n<52*4;n++){      	
			int j = rnd.nextInt(52*4-n);	//²£¥Í¤@­ÓÀH¾÷ÅÜ¼Ærnd¡A¨M©w¿ï¨ìªº¼Æ¦r¦b¤@Å|¼³§JµP¤¤ªº"±Æ§Ç"
			usedCard.add(cards.get(j));	//±N¿ï¨ìªºµP¼È®É©ñ¨ìusedCard°}¦C¤¤
			cards.remove(cards.get(j));	//±N¿ï¨ìªºµP±qcards°}¦C¤¤²¾°£™¤
		}
		for(int x=0;x<52*4;x++){
			cards.add(usedCard.get(x));	//¨Ì§Ç±NusedCard°}¦C¤¤ªºµP©ñ¦^cards°}¦C¤¤¡A§¹¦¨¬~µP°Ê§@
		}
	}	public ArrayList<Card> getAllCards(){
		return cards;
	}
	public ArrayList<Card> getOpenedCard(){
		return openCard;	//¦^¶Ç©Ò¦³¥´¶}¹LªºµP
	}
}
