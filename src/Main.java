import java.util.Dictionary;
import java.util.Scanner;

public class Main {
    final static int MAX_N = 100;
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        //drzewo (póki co BST, potem dodać funkcjonalności AVL):
        TreeStruct tree = new TreeStruct;
        int choice = console.nextInt();
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
        }
    }
}
