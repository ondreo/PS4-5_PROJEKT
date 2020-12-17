import java.util.Scanner;

public class Main {
    final static int MAX_N = 100;
    public static void main(String[] args) throws NameException {
        Scanner console = new Scanner(System.in);
        //drzewo (póki co BST, potem dodać funkcjonalności AVL):
        BstTree tree = new BstTree();
        tree.add("5");
        tree.add("2");
        tree.add("3");
        tree.add("1");
        //System.out.println(tree.toString());
        System.out.println(tree.findCity("2"));

        tree.remove("2");
        System.out.println(tree.toString());
        System.out.println(tree.findCity("2"));
        int choice = 0;
        //int choice = console.nextInt();
        switch (choice) {
            case 1:
                //wyszukiwanie miasta (po nazwie - unikalna)
                break;
            case 2:
            //dodanie nowego miasta

                break;
            case 3:
            //usunięcie istniejącego miasta
                break;
            case 4:
                //wyszukanie liczby miast o danym prefiksie nazwy
                break;
            default:
                break;
        }
    }
}
