package lazyTrees;

/**
 * Interface that can be used for all tree traversal algorithms
 * @param <E>
 */
public interface Traverser<E> {
    public void visit(E x);
}
