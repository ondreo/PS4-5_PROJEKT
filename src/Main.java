import java.util.Scanner;

public class Main {
    //final static int MAX_N = 100;
    static AvlTree tree;
    static Graph graph;
    public static void main(String[] args) throws NameException {
        Scanner console = new Scanner(System.in);
        //drzewo (póki co BST, potem dodać funkcjonalności AVL):
        tree = new AvlTree();
        graph = new Graph();
        tree.addVertex("Białystok");
        //System.out.println(tree.toString());
        //System.out.println();

        tree.addVertex("Kraków");
        //System.out.println(tree.toString());
        //System.out.println();

        tree.addVertex("Krak");
        //System.out.println(tree.toString());
        //System.out.println();

        tree.addVertex("Kraz");
        //System.out.println(tree.toString());
        //System.out.println();

        tree.addVertex("Bełchatow");
        //System.out.println(tree.toString());
        //System.out.println();

        tree.addVertex("Baltoszewo");
        //System.out.println(tree.toString());
        //System.out.println();

        tree.addVertex("Mońki");
        System.out.println(tree.toString());
        System.out.println();

        while(true) {
            System.out.println("Co chcesz zrobić?");
            System.out.println("1. Wyszukaj miasto po nazwie");//"ERROR"
            System.out.println("2. Dodaj miasto");
            System.out.println("3. Usuń miasto");
            System.out.println("4. Wyszukaj miasta po prefixie");
            System.out.println("5. Dodaj drogę pomiędzy miastami ");
            System.out.println("6. Usuń drogę pomiędzy miastami ");
            System.out.println("7. Znajdź najkrótszą drogę pomiędzy miastami ");
            System.out.println("8. Oblicz, do ilu miast się skróci długość przejazdu z miasta A jeśli wybudujemy drogę pomiędzy miastami B i C");
            System.out.print("Twój wybór: ");


            String tmpString = "";
            City tmpCity = null;
            String a,b,c;
            int length;

            String name;
            //int choice = 0;
            int choice = console.nextInt();
            switch (choice) {
                case 1:
                    //wyszukiwanie miasta (po nazwie - unikalna)
                    System.out.println("*WYSZUKIWANIE MIASTA*");
                    System.out.print("Wpisz nazwę miasta: ");
                    name = console.next();

                    tmpCity = tree.findCity(name);
                    if(tmpCity != null) System.out.println("TAK");
                    else System.out.println("NIE");

                    //System.out.println("Podane miasto znajduje się w drzewie pod adresem: " + tree.findCity(name));
                    break;
                case 2:
                    //dodanie nowego miasta
                    System.out.println("*DODAWANIE MIASTA*");
                    System.out.print("Wpisz nazwę miasta: ");
                    name = console.next();
                    tmpString = tree.addVertex(name);
                    if(tmpString.equals("TAK")) System.out.println("Pomyślnie dodano miasto");
                    else System.out.println("Podane miasto już istnieje");
                    break;
                case 3:
                    //usunięcie istniejącego miasta
                    System.out.println("*USUWANIE MIASTA*");
                    System.out.print("Wpisz nazwę miasta: ");
                    name = console.next();
                    tmpString = tree.removeVertex(name);
                    if(tmpString.equals("TAK")) System.out.println("Pomyślnie usunięto miasto");
                    else System.out.println("Podane miasto nie istnieje!");
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
                case 5:
                    //dodanie drogi pomiędzy miastami
                    System.out.println("*DODAWANIE DROGI POMIĘDZY MIASTAMI*");
                    System.out.println("Podaj nazwy miast, pomiędzy którymi ma powstać droga.");
                    System.out.print("Miasto A: ");
                    a = console.next();
                    System.out.print("Miasto B: ");
                    b = console.next();
                    System.out.print("Podaj długość drogi łączącej miasta:");
                    length = console.nextInt();

                    tmpString = graph.addRoad(a,b,length);
                    if(tmpString.equals("TAK")) System.out.println("Pomyślnie dodano dwukierunkową drogę między miastami");
                    else if(tmpString.equals("NIE")) System.out.println("Podana droga między miastami już istnieje!");
                    else System.out.println(tmpString);
                    break;
                case 6:
                    //usunięcie drogi pomiędzy miastami
                    System.out.println("*USUWANIE DROGI POMIĘDZY MIASTAMI*");
                    System.out.println("Podaj nazwy miast, pomiędzy którymi drogę chcesz usunąć.");
                    System.out.print("Miasto A: ");
                    a = console.next();
                    System.out.print("Miasto B: ");
                    b = console.next();

                    tmpString = graph.removeRoad(a,b);
                    if(tmpString.equals("TAK")) System.out.println("Pomyślnie usunięto dwukierunkową drogę między miastami");
                    else if(tmpString.equals("NIE")) System.out.println("Podana droga między miastami nie istnieje!");
                    else System.out.println(tmpString);
                    break;
                case 7:
                    //znajdowanie najkrótszej ścieżki pomiędzy dwoma miastami
                    System.out.println("*ZNAJDOWANIE NAJKRÓTSZEJ ŚCIEŻKI POMIĘDZY DWOMA MIASTAMI*");
                    System.out.println("Podaj nazwy miast, pomiędzy którymi chcesz znaleźć najkrótszą drogę.");
                    System.out.print("Miasto A: ");
                    a = console.next();
                    System.out.print("Miasto B: ");
                    b = console.next();

                    System.out.println(graph.findShortestPathBetweenTwoCities(a,b));
                    break;
                case 8:
                    //do ilu miast skróci się długość przejazdu z miasta A (z miasta A do ilu pozostałych miast się skróci - to bardziej zrozumiałe), jeżeli wybudowana
                    //zostałaby droga pomiędzy miastami B i C (o zadanej długości)
                    System.out.println("*OBLICZENIE DO ILU MIAST SKRÓCI SIĘ DŁUGOŚĆ PRZEJAZDU Z MIASTA A, JEŻELI WYBUDOWANA");
                    System.out.println("ZOSTAŁABY DROGA POMIĘDZY MIASTAMI B I C O ZADANEJ DŁUGOŚCI*");
                    System.out.print("Miasto A: ");
                    a = console.next();
                    System.out.print("Miasto B: ");
                    b = console.next();
                    System.out.print("Miasto C: ");
                    c = console.next();
                    System.out.print("Podaj długość drogi łączącej miasta:");
                    length = console.nextInt();

                    tmpString = graph.whatIfRoadAdded(a,b,c,length);
                    try {
                        System.out.println("Dodanie tej drogi skróci drogi od A do " + Integer.parseInt(tmpString) + " miast.");
                    }
                    catch (NumberFormatException e) {
                        System.out.println(tmpString);
                    }
                    break;
                default:
                    break;
            }
            System.out.println("\n\n\n");
            //int tmpWyraz = Integer.parseInt("wyraz");
        }
    }
}
