import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String word = "racecar";
        assertTrue(palindrome.isPalindrome(word));

        word = "noon";
        assertTrue(palindrome.isPalindrome(word));

        word = "a";
        assertTrue(palindrome.isPalindrome(word));

        word = "Aa";
        assertFalse(palindrome.isPalindrome(word));

        word = "ogkkds";
        assertFalse(palindrome.isPalindrome(word));

        word = "";
        assertTrue(palindrome.isPalindrome(word));
    }
}
