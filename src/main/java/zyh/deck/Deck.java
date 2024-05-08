package zyh.deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }//将每一个花色的每一张牌加入牌库
    }
    public void shuffle() {
        Collections.shuffle(cards); //洗牌
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("The deck is empty."); //牌库抽光的情况
        }
        return cards.remove(cards.size() - 1); //每抽一张牌牌库数量对应减少
    }

    public int remainingCards() {
        return cards.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card).append("\n");
        }
        return sb.toString();
    }


}
