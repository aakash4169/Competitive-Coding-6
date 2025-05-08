import java.util.HashMap;
import java.util.Map;
//TC : O(L)
//SC : O(L)

/*
This implementation uses a Trie (prefix tree) to store log messages character by character.
Each node in the Trie represents a character and stores the latest timestamp when that message
(ending at the node) was printed. When a new message arrives, we traverse the Trie to reach its
corresponding node. If the message has not been printed in the last 10 seconds, we update the
 timestamp and allow printing. This approach avoids storing entire messages as keys and leverages
 the structure of common prefixes for space efficiency.

* */
class DesignLoggerRateLimiter {

    class TrieNode {
        Map<Character, TrieNode> children;
        Integer timestamp;

        public TrieNode() {
            children = new HashMap<>();
            timestamp = null;
        }
    }

    TrieNode root;

    public Logger() {
        root = new TrieNode();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        TrieNode node = root;

        // Traverse the Trie for each character
        for (char ch : message.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }

        // At this point, node is the leaf for this message
        if (node.timestamp == null || node.timestamp + 10 <= timestamp) {
            node.timestamp = timestamp;
            return true;
        }

        return false;
    }
}


/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */