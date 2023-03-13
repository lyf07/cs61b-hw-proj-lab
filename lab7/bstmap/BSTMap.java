package bstmap;

import java.util.Set;
import java.util.Iterator;
import java.util.TreeMap;

public class BSTMap<K extends Comparable<K>,V> implements  Map61B<K,V>{

    private class BSTNode{
        private V value;
        private K key;

        public BSTNode(V val, K keey){
            this.value = val;
            this.key = keey;
        }
    }

    private BSTNode root;
    private BSTMap left;
    private BSTMap right;

    private void printInOrder(){
        return;
    }

    public BSTMap(){
        root = null;
        left = right = null;
    }
    /** Removes all of the mappings from this map. */
    public void clear(){
        root = null;
        left = null;
        right = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    private boolean helperContains(BSTMap tree, K key){
        if(tree == null || tree.root == null)
        {
            return false;
        }
        int com = key.compareTo(tree.root.key);
        if(com > 0){
            return helperContains(tree.right,key);
        }
        else if(com < 0){
            return helperContains(tree.left,key);
        }
        return true;
    }
    public boolean containsKey(K key){
        return helperContains(this,key);
    }

    private V helperGet(BSTMap tree, K key){
        if(tree == null || tree.root == null)
        {
            return null;
        }
        int com = key.compareTo(tree.root.key);
        if(com > 0){
            return helperGet(tree.right,key);
        }
        else if(com < 0){
            return helperGet(tree.left,key);
        }
        return  (V)tree.root.value;
    }
    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        return helperGet(this,key);
    }

    private boolean is_leaf(){
        return left == null && right == null;
    }
    /* Returns the number of key-value mappings in this map. */
    public int size(){
        if(this == null || this.root == null){
            return 0;
        }
        if(is_leaf()){
            return 1;
        }
        else if(left == null){
            return right.size() + 1;
        }
        else if(right == null){
            return left.size() + 1;
        }
        else{
            return left.size() + right.size() + 1;
        }
    }


    private BSTMap helperPut(BSTMap tree,K key,V value){
        if(tree == null || tree.root == null){
            tree = new BSTMap();
            tree.root = new BSTNode(value,key);
        }
        else{
            int com = key.compareTo(tree.root.key);
            if(com > 0){
                tree.right = helperPut(tree.right,key,value);
            }
            else if(com < 0)
            {
                tree.left = helperPut(tree.left,key,value);
            }
        }
        return tree;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        if(this.root == null){
            BSTNode node = new BSTNode(value,key);
            this.root = node;
        }
        else{
            int com = key.compareTo(this.root.key);
            if(com > 0)
            {
                this.right = helperPut(this.right,key,value);
            }
            else if(com < 0)
            {
                this.left = helperPut(this.left,key,value);
            }
        }

    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator(){
        throw new UnsupportedOperationException();
    }

}
