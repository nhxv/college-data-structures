public class Main {
    public static void main(String[] args) {
        LinkedListQueue<String> list = new LinkedListQueue<>("string list");
        list.enqueue("Apple");
        list.enqueue("Banana");
        list.enqueue("Lemon");
        list.enqueue("Grapes");
        for (String fruit : list) {
            System.out.println(fruit);
        }
    }
}
