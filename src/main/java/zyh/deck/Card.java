package zyh.deck;

public class Card {


    public enum Suit {
        SPADES, HEARTS, DIAMONDS, CLUBS //扑克牌花色
    }

    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING //扑克牌点数
    }

    private final Suit suit;
    private final Rank rank;
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    public int getPointValue() {
        if (rank == Rank.ACE) {
            return 1; // ACE点数视为1
        } else if (rank == Rank.TEN || rank == Rank.JACK || rank == Rank.QUEEN || rank == Rank.KING) {
            return 10; // JQK点数视为10
        } else {
            return rank.ordinal() + 1;
        }
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

