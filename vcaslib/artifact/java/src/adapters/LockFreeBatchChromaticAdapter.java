
package adapters;

import main.support.Element;
import main.support.SetInterface;
import main.support.KSTNode;
import main.support.OperationListener;
import main.support.Random;
import algorithms.chromatic.LockFreeBatchChromaticMap;

import java.util.function.Predicate;

public class LockFreeBatchChromaticAdapter<K extends Comparable<? super K>> extends AbstractAdapter<K> implements SetInterface<K> {
    public LockFreeBatchChromaticMap<K,K> tree;

    // public LockFreeBatchChromaticAdapter() {
    //     tree = new LockFreeBatchChromaticMap();
    // }

    // public LockFreeBatchChromaticAdapter(final int allowedViolations) {
    //     tree = new LockFreeBatchChromaticMap(allowedViolations);
    // }

    public LockFreeBatchChromaticAdapter() {
        tree = new LockFreeBatchChromaticMap();
    }

    public LockFreeBatchChromaticAdapter(final int batchingDegree) {
        tree = new LockFreeBatchChromaticMap(batchingDegree);
    }

    public LockFreeBatchChromaticAdapter(final int batchingDegree, final int allowedViolations) {
        tree = new LockFreeBatchChromaticMap(batchingDegree, allowedViolations);
    }

    public boolean contains(K key) {
        return tree.containsKey(key);
    }
    
    public boolean add(K key, Random rng) {
        return tree.putIfAbsent(key, key) == null;
        //return tree.put(key, key) == null;
    }

    public K get(K key) {
        return tree.get(key);
    }

    public boolean remove(K key, Random rng) {
        return tree.remove(key) != null;
    }

    public void addListener(OperationListener l) {

    }

    @Override
    public Object rangeQuery(K lo, K hi, int rangeSize, Random rng) {
        return tree.rangeScan(lo, hi);
    }

    @Override
    public Element<K,K>[] successors(K key, int numSuccessors) {
        return tree.successors(key, numSuccessors);
    }

    @Override
    public Element<K,K> findIf(K lo, K hi, Predicate<Element<K,K>> p) {
        return tree.findIf(lo, hi, p);
    }

    @Override
    public Object[] multiSearch(K[] keys) {
        return tree.multiSearch(keys);
    }

    public int size() {
        return sequentialSize();
    }

    public KSTNode<K> getRoot() {
        return null;
    }
    
    public double getAverageDepth() {
        return tree.getSumOfDepths() / (double) tree.getNumberOfNodes();
    }

    public int getSumOfDepths() {
        return tree.getSumOfDepths();
    }

    public int sequentialSize() {
        return tree.size();
    }
    
    public boolean supportsKeysum() {
        return true;
    }
    public long getKeysum() {
        return tree.getKeysum();
    }

    public double getRebalanceProbability() {
        return -1;
    }

    @Override
    public String toString() {
        return tree.toString();
    }
    
    public void disableRotations() {

    }

    public void enableRotations() {

    }

}