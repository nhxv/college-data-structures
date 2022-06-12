import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addFront("Aiko");
        list.addFront("Banana");
        list.addFront("Voibay");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        list.printList();
    }
}
