import java.util.Arrays;

public class TestMyList {
  public static void main(String[] args) {
    MyList<Integer> myList = new MyList<Integer>();
    myList.add(12);
    myList.add(15);
    myList.add(26);
    myList.add(3, 20);
    System.out.println(myList.contains(15));

    for (int i = 0; i < myList.size(); i++) {
      System.out.println(myList.get(i));
    }

    myList.remove(2);
    for (int i = 0; i < myList.size(); i++) {
      System.out.println(myList.get(i));
    }

    myList.clear();
    for (int i = 0; i < myList.size(); i++) {
      System.out.println(myList.get(i));
    }
  }
}
