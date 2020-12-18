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
        System.out.println(tree.toString());
        System.out.println(tree.findCity("2"));

        tree.remove("2");
        System.out.println(tree.toString());
        System.out.println(tree.findCity("5"));

        String name;
        int choice = 0;
        //int choice = console.nextInt();
        switch (choice) {
            case 1:
                //wyszukiwanie miasta (po nazwie - unikalna)
                System.out.println("*WYSZUKIWANIE MIASTA*");
                System.out.print("Wpisz nazwę miasta: ");
                name = console.next();
                System.out.println("Podane miasto znajduje się w drzewie pod adresem: " + tree.findCity(name));
                break;
            case 2:
            //dodanie nowego miasta
                System.out.println("*DODAWANIE MIASTA*");
                System.out.print("Wpisz nazwę miasta: ");
                name = console.next();
                tree.add(name);
                //graph.add(name);
                //TODO:
                //Może zamienić wyjątki na warunki typu Boolean - dodało/nie dodało oraz usunięto/nieusunięto??
                System.out.println("Pomyślnie dodano miasto do drzewa");
                //System.out.println("Pomyślnie dodano miasto do grafu");
                break;
            case 3:
            //usunięcie istniejącego miasta
                System.out.println("*USUWANIE MIASTA*");
                System.out.print("Wpisz nazwę miasta: ");
                name = console.next();
                tree.remove(name);
                //graph.remove(name);
                System.out.println("Pomyślnie usunięto miasto z drzewa");
                //System.out.println("Pomyślnie usunięto miasto z grafu");
                break;
            case 4:
                //wyszukanie liczby miast o danym prefiksie nazwy
                System.out.println("*WYSZUKIWANIE MIASTA PO PREFIKSIE*");
                System.out.print("Wpisz prefiks wyszukiwanych miast: ");
                name = console.next();
                System.out.println("W danym drzewie jest: " + tree.countCitiesByPrefix(name) + " takich miast");
                break;
            default:
                break;
        }
    }
}
