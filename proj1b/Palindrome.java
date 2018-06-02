public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        for(int i = 0; i < word.length(); i++){
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        /*int i = 0, j = deque.size() - 1;
        while(i <= j){
            if(deque.get(i) != deque.get(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;*/
        return isPalindrome(deque);
    }

    private boolean isPalindrome(Deque<Character> deque){
        if(deque.isEmpty() || deque.size() == 1){
            return true;
        }

        char front = deque.removeFirst();
        char last = deque.removeLast();

        return front == last && isPalindrome(deque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque, cc);
    }

    private boolean isPalindrome(Deque<Character> deque, CharacterComparator cc){
        if(deque.isEmpty() || deque.size() == 1) {
            return true;
        }

        char front = deque.removeFirst();
        char last = deque.removeLast();

        return cc.equalChars(front, last) && isPalindrome(deque, cc);
    }

}
