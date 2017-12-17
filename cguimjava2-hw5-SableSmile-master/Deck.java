import java.util.ArrayList;
import java.util.Random;


public class Deck {
	private ArrayList<Card> cards;
	private ArrayList<Card> usedCard;
	private ArrayList<Card> openCard; //�������}���P
	public int nUsed;
	public Deck(int nDeck){
		cards=new ArrayList<Card>();
		for(int deck=1;deck<=nDeck;deck++){  		//deck->�ܤ֭n�@�ƵP�AnDeck->�ܦh�n�X�ƵP
			for(Card.Suit s : Card.Suit.values()){  //�@�ƵP��4�ت�≲
				for(int rank=1;rank<=13;rank++){	//1�ت��13�i�P
					Card card=new Card(s,rank);		//����ƥd�P
					cards.add(card);				//�s�W�d�P
				}
			}
		}
		shuffle(); 	//�~�P
	}	
	public Card getOneCard(boolean isOpened){
		
		Card c1=new Card(cards.get(nUsed).getSuit(),cards.get(nUsed).getRank());	//����ƨõo�P
		usedCard.add(cards.get(nUsed));		//�N�o�X���P�����busedCard
		openCard=new ArrayList<Card>();
		if(isOpened) {		//�P�O�}�۪�
			openCard.add(cards.get(nUsed));	//�[�JopenCard
		}
		nUsed++;	//�����o�X���P�ƶq
		
		if(nUsed==52*4) {	//��P�o���F
			shuffle();	//�~�P
			nUsed=0;	//�o�X���P�ƶq�k0	
		}
		return c1;
	}
	public void shuffle(){		
		Random rnd = new Random(); 	//�ϥ�Random ���ͤ@��rnd
		openCard=new ArrayList<Card>();	//�����
		nUsed=0;	//�o�X���P�ƶq��0
		openCard.clear();  //���m���}���P;	
		usedCard=new ArrayList<Card>();
		
		/* �~�P�L�{���H���D��Y�@�i�P�A
		 * ��쪺�P�|�Q�̧ǩ�JusedCard�A
		 * �ñNcards�̳Q�襤���P�����A
		 * �g�L"�~�P"(�j��)����n����A
		 * usedCard���o�쪺�d�P���B�P���N�|�Q����
		   �A�N�~�n���P�qusedCard��^cards�}�C��*/
		for(int n=0;n<52*4;n++){      	
			int j = rnd.nextInt(52*4-n);	//���ͤ@���H���ܼ�rnd�A�M�w��쪺�Ʀr�b�@�|���J�P����"�Ƨ�"
			usedCard.add(cards.get(j));	//�N��쪺�P�Ȯɩ��usedCard�}�C��
			cards.remove(cards.get(j));	//�N��쪺�P�qcards�}�C��������
		}
		for(int x=0;x<52*4;x++){
			cards.add(usedCard.get(x));	//�̧ǱNusedCard�}�C�����P��^cards�}�C���A�����~�P�ʧ@
		}
	}	public ArrayList<Card> getAllCards(){
		return cards;
	}
	public ArrayList<Card> getOpenedCard(){
		return openCard;	//�^�ǩҦ����}�L���P
	}
}
