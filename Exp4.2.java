Experiment 4.2: Card Collection System

Objective:
Develop a Java program that collects and stores playing cards to help users find all the cards of a given symbol (suit).
The program should utilize the Collection interface (such as ArrayList, HashSet, or HashMap) to manage the card data efficiently.

import java.util.*;

class Card {
    String value;
    String suit;

    Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return value.equals(card.value) && suit.equals(card.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, suit);
    }
}

class CardCollectionSystem {
    private Set<Card> cardSet;
    private Map<String, List<Card>> suitMap; 

    public CardCollectionSystem() {
        cardSet = new HashSet<>();
        suitMap = new HashMap<>();
    }

    public void addCard(String value, String suit) {
        Card card = new Card(value, suit);
        if (cardSet.contains(card)) {
            System.out.println("Error: Card \"" + card + "\" already exists.");
        } else {
            cardSet.add(card);
            suitMap.computeIfAbsent(suit, k -> new ArrayList<>()).add(card);
            System.out.println("Card added: " + card);
        }
    }

    public void findCardsBySuit(String suit) {
        List<Card> cards = suitMap.get(suit);
        if (cards == null || cards.isEmpty()) {
            System.out.println("No cards found for " + suit + ".");
        } else {
            for (Card card : cards) {
                System.out.println(card);
            }
        }
    }

    public void removeCard(String value, String suit) {
        Card card = new Card(value, suit);
        if (cardSet.remove(card)) {
            List<Card> cardsOfSuit = suitMap.get(suit);
            if (cardsOfSuit != null) {
                cardsOfSuit.remove(card);
            }
            System.out.println("Card removed: " + card);
        } else {
            System.out.println("Error: Card \"" + card + "\" not found.");
        }
    }

    public void displayAllCards() {
        if (cardSet.isEmpty()) {
            System.out.println("No cards found.");
        } else {
            for (Card card : cardSet) {
                System.out.println(card);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        CardCollectionSystem ccs = new CardCollectionSystem();

        System.out.println("Test Case 1: No Cards Initially");
        ccs.displayAllCards();

        System.out.println("\nTest Case 2: Adding Cards");
        ccs.addCard("Ace", "Spades");
        ccs.addCard("King", "Hearts");
        ccs.addCard("10", "Diamonds");
        ccs.addCard("5", "Clubs");

        System.out.println("\nTest Case 3: Finding Cards by Suit (Hearts)");
        ccs.findCardsBySuit("Hearts");

        System.out.println("\nTest Case 4: Searching Suit with No Cards (Diamonds)");
        ccs.findCardsBySuit("Diamonds");

        System.out.println("\nTest Case 5: Displaying All Cards");
        ccs.displayAllCards();
        System.out.println("\nTest Case 6: Preventing Duplicate Cards");
        ccs.addCard("King", "Hearts");

        System.out.println("\nTest Case 7: Removing a Card");
        ccs.removeCard("10", "Diamonds");

        System.out.println("\nCards after removal:");
        ccs.displayAllCards();
    }
}




// Test Cases

// Test Case 1: No Cards Initially
// Input:
// Display All Cards
// Expected Output:
// No cards found.

// Test Case 2: Adding Cards
// Input:
// Add Card: Ace of Spades
// Add Card: King of Hearts
// Add Card: 10 of Diamonds
// Add Card: 5 of Clubs
// Expected Output:
// Card added: Ace of Spades
// Card added: King of Hearts
// Card added: 10 of Diamonds
// Card added: 5 of Clubs

// Test Case 3: Finding Cards by Suit
// Input:
// Find All Cards of Suit: Hearts
// Expected Output:
// King of Hearts

// Test Case 4: Searching Suit with No Cards
// Input:
// Find All Cards of Suit: Diamonds
// (If no Diamonds were added)
// Expected Output:
// No cards found for Diamonds.

// Test Case 5: Displaying All Cards
// Input:
// Display All Cards
// Expected Output:
// Ace of Spades
// King of Hearts
// 10 of Diamonds
// 5 of Clubs

// Test Case 6: Preventing Duplicate Cards
// Input:
// Add Card: King of Hearts
// Expected Output:
// Error: Card "King of Hearts" already exists.

// Test Case 7: Removing a Card
// Input:
// Remove Card: 10 of Diamonds
// Expected Output:
// Card removed: 10 of Diamonds
