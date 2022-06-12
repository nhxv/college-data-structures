package lazyTrees;

/**
 * visit and print a node in the Lazy Search Tree
 * @param <E>
 */
class PrintObject<E> implements Traverser<E> {
    public void visit(E x)
    {
        System.out.print( x + " ");
    }
};
