import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    //final static int MAX_N = 100;
    static AvlTree tree;
    static Graph graph;
    static int nrOfAllLines;
    static int nrOfReadLines;
    static Scanner fromFile;
    static PrintWriter toFile;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        fromFile = new Scanner(new File("testy/projekt1_in7.txt"));
        toFile = new PrintWriter(new File("testy/projekt1_out7_moj.txt"));
        nrOfAllLines = fromFile.nextInt();
        nrOfReadLines = 0;
        tree = new AvlTree();
        graph = new Graph();
        tree.addVertex("Białystok");
        tree.addVertex("Kraków");
        tree.addVertex("Krak");
        tree.addVertex("Kraz");
        tree.addVertex("Bełchatow");
        tree.addVertex("Baltoszewo");
        tree.addVertex("Mońki");
        while(true) {
            System.out.println("Co chcesz zrobić?");
            System.out.println("1. Wyszukaj miasto po nazwie");
            System.out.println("2. Dodaj miasto");
            System.out.println("3. Usuń miasto");
            System.out.println("4. Wyszukaj miasta po prefixie");
            System.out.println("5. Wypisz strukturę drzewa AVL");
            System.out.println("6. Dodaj drogę pomiędzy miastami ");
            System.out.println("7. Usuń drogę pomiędzy miastami ");
            System.out.println("8. Znajdź najkrótszą drogę pomiędzy miastami ");
            System.out.println("9. Oblicz, do ilu miast się skróci długość przejazdu z miasta A jeśli wybudujemy drogę pomiędzy miastami B i C");
            System.out.println("10. Wczytaj kolejną linijkę z pliku.");
            System.out.println("11. Wczytaj cały plik.");
            System.out.println("12. Zakończ działanie programu.");
            System.out.print("Twój wybór: ");


            String tmpString = "";
            City tmpCity = null;
            String a,b,c;
            int length;

            String name;
            int choice = console.nextInt();
            long startTime = System.currentTimeMillis();
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
                    tmpString = tree.removeVertex(name,"");
                    if(tmpString.equals("TAK")) System.out.println("Pomyślnie usunięto miasto");
                    else System.out.println("Podane miasto nie istnieje!");
                    break;
                case 4:
                    //wypisanie liczby miast o danym prefiksie nazwy
                    System.out.println("*WYPISANIE LICZBY MIAST PO PREFIKSIE*");
                    System.out.print("Wpisz prefiks wyszukiwanych miast: ");
                    name = console.next();
                    int tmp = tree.countCitiesByPrefix(name,tree.getStartVertex());
                    System.out.print("W danym drzewie jest: ");
                    System.out.print(tmp);
                    /*if(tmp <= 100) {
                        System.out.print(tmp);
                    }
                    else {
                        System.out.print("100+");
                    }*/
                    System.out.println(" takich miast");
                    break;
                case 5:
                    //wypisanie struktury drzewa
                    System.out.println(tree);
                    break;
                case 6:
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
                case 7:
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
                case 8:
                    //znajdowanie najkrótszej ścieżki pomiędzy dwoma miastami
                    System.out.println("*ZNAJDOWANIE NAJKRÓTSZEJ ŚCIEŻKI POMIĘDZY DWOMA MIASTAMI*");
                    System.out.println("Podaj nazwy miast, pomiędzy którymi chcesz znaleźć najkrótszą drogę.");
                    System.out.print("Miasto A: ");
                    a = console.next();
                    System.out.print("Miasto B: ");
                    b = console.next();

                    System.out.println(graph.findShortestPathBetweenTwoCities(a,b));
                    break;
                case 9:
                    //do ilu miast skróci się długość przejazdu z miasta A, jeżeli wybudowana
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
                case 10:
                    //wczytywanie kolejnej linijki z pliku
                    System.out.println(readLineFromFile());
                    break;
                case 11:
                    //wczytywanie wszystkich linii pliku
                    //long startTime = System.currentTimeMillis();
                    for(int i=0;i<nrOfAllLines;++i) {
                        readLineFromFile();
                    }
                    //System.out.println("Zajęło to: " + (System.currentTimeMillis() - startTime) + " milisekund.");
                    break;
                case 12:
                    fromFile.close();
                    toFile.close();
                    return;
                default:
                    System.out.println("Wybrałeś niewłaściwą opcję! Spróbuj ponownie!");
                    break;
            }
            System.out.println("\n\n\n");
            System.out.println("Program wykonał się w: " + (System.currentTimeMillis() - startTime) + " milisekund.");
        }
    }
    static String readLineFromFile(/*Scanner fromFile,PrintWriter toFile*/) {

        String tmpString = "";
        City tmpCity = null;
        String a,b,c;
        int length;

        //System.out.println("nrOfReadLines = "+nrOfReadLines);
        if(nrOfReadLines == nrOfAllLines) return "Wczytano już cały plik.";
        String choice = fromFile.next();
        ++nrOfReadLines;
        String name;
        //System.out.println(nrOfReadLines + "choice: " + choice);
        switch (choice) {
            case "DM":
                //dodanie nowego miasta
                name = fromFile.next();
                tmpString = tree.addVertex(name);
                if(tmpString.equals("TAK")) return choice+" "+name+": "+"Pomyślnie dodano miasto";
                else return choice+" "+name+": "+"Podane miasto już istnieje";
            case "UM":
                //usunięcie istniejącego miasta
                name = fromFile.next();
                tmpString = tree.removeVertex(name, "");
                /*try {
                    tmpString = tree.removeVertex(name);
                }
                catch (NullPointerException e) {
                    System.out.println("name="+name);
                    System.out.println(e);
                    System.out.println("name="+name);
                }*/
                if(tmpString.equals("TAK")) return choice+" "+name+": "+"Pomyślnie usunięto miasto";
                else return choice+" "+name+": "+"Podane miasto nie istnieje!";
            case "WM":
                //wyszukiwanie miasta (po nazwie - unikalna)
                name = fromFile.next();
                tmpCity = tree.findCity(name);
                if(tmpCity != null) {
                    toFile.println("TAK");
                    return choice+" "+name+": "+"TAK";
                }
                else {
                    toFile.println("NIE");
                    return choice+" "+name+": "+"NIE";
                }
            case "LM":
                //wypisanie liczby miast o danym prefiksie nazwy
                name = fromFile.next();
                int tmp = tree.countCitiesByPrefix(name,tree.getStartVertex());
                toFile.println(tmp);
                return choice+" "+name+": "+"W danym drzewie jest: "+tmp+" takich miast";
                /*if(tmp <= 100) {
                    toFile.println(tmp);
                    return choice+" "+name+": "+"W danym drzewie jest: "+tmp+" takich miast";
                }
                else {
                    toFile.println("100+");
                    return choice+" "+name+": "+"W danym drzewie jest: 100+ takich miast";
                }*/
            case "WY":
                //wypisanie struktury drzewa
                String out = tree.toString();
                toFile.println(out);
                return out;
            case "DD":
                //dodanie drogi pomiędzy miastami
                a = fromFile.next();
                b = fromFile.next();
                length = fromFile.nextInt();

                tmpString = graph.addRoad(a,b,length);
                if(tmpString.equals("TAK")) return choice+" "+a+" "+b+" "+length+": "+"Pomyślnie dodano dwukierunkową drogę między miastami";
                else if(tmpString.equals("NIE")) return choice+" "+a+" "+b+" "+length+": "+"Podana droga między miastami już istnieje!";
                else return choice+" "+a+" "+b+" "+length+": "+tmpString;
            case "UD":
                //usunięcie drogi pomiędzy miastami
                a = fromFile.next();
                b = fromFile.next();
                tmpString = graph.removeRoad(a,b);
                if(tmpString.equals("TAK")) return choice+" "+a+" "+b+": "+"Pomyślnie usunięto dwukierunkową drogę między miastami";
                else if(tmpString.equals("NIE")) return choice+" "+a+" "+b+": "+"Podana droga między miastami nie istnieje!";
                else return choice+" "+a+" "+b+": "+tmpString;
            case "ND":
                //znajdowanie najkrótszej ścieżki pomiędzy dwoma miastami
                a = fromFile.next();
                b = fromFile.next();
                out = graph.findShortestPathBetweenTwoCities(a,b);
                Scanner readFromOut = new Scanner(out);
                String read;
                while(readFromOut.hasNext()) {
                    read = readFromOut.next();
                    if(read.charAt(0) >= '0' && read.charAt(0) <= '9') {
                        toFile.println(read);
                        return out;
                    }
                }
                toFile.println("NIE");
                return out;
            case "IS":
                //do ilu miast skróci się długość przejazdu z miasta A, jeżeli wybudowana
                //zostałaby droga pomiędzy miastami B i C (o zadanej długości)
                a = fromFile.next();
                b = fromFile.next();
                c = fromFile.next();
                length = fromFile.nextInt();

                tmpString = graph.whatIfRoadAdded(a,b,c,length);
                toFile.println(tmpString);
                try {
                    return choice+" "+a+" "+b+" "+c+" "+length+": "+"Dodanie tej drogi skróci drogi od A do " + Integer.parseInt(tmpString) + " miast.";
                }
                catch (NumberFormatException e) {
                    return choice+" "+a+" "+b+" "+c+" "+length+": "+tmpString;
                }
            default:
                return "Podano niewłaściwą komendę.";
        }
    }
}
