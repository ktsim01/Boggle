package assignment;

import java.util.*;
import java.io.*;

public class GameDictionary implements BoggleDictionary {
	//list of all the words
    
    //root node of the trie
    private TrieNode root;
    
    /*
     * loads the dictionary from the file into both the list and the root
     */
    public void loadDictionary(String filename) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(filename));

        root = new TrieNode(null);
        while(true) {
            String line = file.readLine();
            
            //null line = end of file
            if(line == null)
                break;
            
            //insert the string into the trie
            add(line);
        }
    }
    /*
     * wrapper for the trie add method.
     */
    private void add(String s) {
    	root.add(s.toLowerCase(), 0);
    }
    /*
     * wrapper for the trie isPrefix method.
     */
    public boolean isPrefix(String prefix) {
        return root.isPrefix(prefix.toLowerCase(), 0);
    }

    /*
     * wrapper for the trie contains method.
     */
    public boolean contains(String word) {
        return root.contains(word.toLowerCase(), 0);
    }

    public Iterator<String> iterator() {
        return new TrieIterator(root);
    }
    
    /*
     * class that implements a node in a trie
     */
    private class TrieNode {
    	//map to next nodes based on character
        private TreeMap<Character, TrieNode> map;
        
        //if this node is a valid end node
        private boolean end;
        
        private TrieNode parent;
        
        public TrieNode(TrieNode p) {
            map = new TreeMap<>();
            parent = p;
        }

        /*
         * Adds the String s to the trie, where the next node corresponds to the character at index i.
         */
        public void add(String s, int i) {
        	// reached the end of the string
            if(i == s.length())
                end = true;
            else {
                char c = s.charAt(i);
                
                //create the next node if it doesn't exist 
                if(!map.containsKey(c))
                    map.put(c, new TrieNode(this));
                
                //recursively build the trie
                map.get(c).add(s, i+1);
            }
        }

        public boolean isPrefix(String s, int i) {
        	// reached the end of the string in a valid node, so it is always a valid non-strict prefix
            if(i == s.length())
                return true;

            char c = s.charAt(i);
            
            //no next node means the string cannot be a prefix
            if(!map.containsKey(c))
                return false;

            //recursively traverse the trie
            return map.get(c).isPrefix(s,i+1);
        }
        public boolean contains(String s, int i) {
        	// reached the end of the string, but the current node must be an end node for it to be contained in the trie
            if(i == s.length())
                return end;

            char c = s.charAt(i);
            
            //no next node means the string cannot be in the trie
            if(!map.containsKey(c))
                return false;

            //recursively traverse the trie
            return map.get(c).contains(s,i+1);
        }
        
    }

    private class TrieIterator implements Iterator<String> {
    	TrieNode currentNode;
    	String currentString;
    	int depth;
    	public TrieIterator(TrieNode root) {
    		currentNode = root;
    		currentString = "";
    		depth = 0;
    		findNextEndNode();
    	}
    	public String next() throws NoSuchElementException {
    		if(currentNode == null) throw new NoSuchElementException();
    		String answer = currentString;
    		
    		//make sure we pass this end node
    		advanceNode();
    		
    		//find the next end node for the next call
    		findNextEndNode();
    		
    		//return the answer before we traversed to the next end node
    		return answer;
    	}
    	
    	//traverses the trie until we reach an end node, or sets currentNode to null if there are no more
    	private void findNextEndNode() {
    		while(currentNode != null && !(currentNode.end && depth == currentString.length())) {
    			advanceNode();
    		}
    	}
    	
    	//advances to the next node in the pre-order traversal of the trie
    	//maintains the other information, such as depth and currentString
    	private void advanceNode() {
    		//finished traversing
    		if(currentNode == null) return;
    		
    		//leaf node of the trie
    		if(currentNode.map.isEmpty()) {
    			currentNode = currentNode.parent;
    			depth--;
    			return;
    		}
    		
    		//determine if the next child to go to (or null if none)
    		Character next = null;
    		
    		//no previous path traversed, so we default to the smallest letter
    		if(currentString.length() == depth) {
    			next = currentNode.map.firstKey();
    		}else
    		//take the letter that goes after the last one we used (or null if none are left)
    			next = currentNode.map.higherKey(currentString.charAt(depth));
    		
    		//no next child
    		if(next == null) {
    			depth--;
    			currentNode = currentNode.parent;
    			
    			//only remove the last character if there is a last character to remove
    			if(currentString.length() != 0)
    				currentString = currentString.substring(0, currentString.length()-1);
    		} else {
    			//go to the next node if there was a character
    			currentNode = currentNode.map.get(next);

    			if(depth != currentString.length())
    				currentString = currentString.substring(0, currentString.length()-1);
    			currentString += next;
    			depth++;
    		}
    	}
    	public boolean hasNext() {
    		return currentNode != null;
    	}
    }
}

