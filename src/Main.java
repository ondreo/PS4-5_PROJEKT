import java.util.Scanner;

public class Main {
    final static int MAX_N = 100;
    public static void main(String[] args) throws NameException {
        Scanner console = new Scanner(System.in);
        //drzewo (póki co BST, potem dodać funkcjonalności AVL):
        BstTree tree = new BstTree();
        tree.add("Białystok");
        tree.add("Kraków");
        tree.add("Krak");
        tree.add("Kraz");
        tree.add("Bełchatow");
        tree.add("Baltoszewo");
        tree.add("Mońki");
        /*tree.add("2");
        tree.add("3");
        tree.add("1");
        System.out.println(tree.toString());
        System.out.println(tree.findCity("2"));

        tree.remove("2");
        System.out.println(tree.toString());
        System.out.println(tree.findCity("5"));*/

        while(true) {
            System.out.println("Co chcesz zrobić?");
            System.out.println("1. Wyszukaj miasto po nazwie");//"ERROR"
            System.out.println("2. Dodaj miasto");
            System.out.println("3. Usuń miasto");
            System.out.println("4. Wyszukaj miasta po prefixie");
            System.out.print("Twój wybór: ");
            String name;
            //int choice = 0;
            int choice = console.nextInt();
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
                    //Zamienić wyjątki na zwracane "NIE"
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
                    int tmp = tree.countCitiesByPrefix(name,tree.getStartVertex());
                    System.out.print("W danym drzewie jest: ");
                    if(tmp <= 100) {
                        System.out.print(tmp);
                    }
                    else {
                        System.out.print(100 + "+");
                    }
                    System.out.println(" takich miast");
                    break;
                default:
                    break;
            }
            System.out.println("\n\n\n");
        }
    }
}
