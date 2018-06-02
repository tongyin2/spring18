import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertFalse(offByOne.equalChars('a','a'));
        assertFalse(offByOne.equalChars('a', 'A'));
        assertTrue(offByOne.equalChars('a', 'b'));
    }

    @Test
    public void testOffByOne() {
        Palindrome palindrome = new Palindrome();
        assertTrue(palindrome.isPalindrome("afmlgb", new OffByOne()));
        assertTrue(palindrome.isPalindrome("2st1", new OffByOne()));
        assertFalse(palindrome.isPalindrome("aa", new OffByOne()));
    }

    @Test
    public void testOffByN() {
        Palindrome palindrome = new Palindrome();
        assertTrue(palindrome.isPalindrome("afmlgb", new OffByN(1)));
        assertTrue(palindrome.isPalindrome("2st1", new OffByN(1)));
        assertFalse(palindrome.isPalindrome("aa", new OffByN(1)));
    }
}
