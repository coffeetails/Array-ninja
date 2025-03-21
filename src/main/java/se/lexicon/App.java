package se.lexicon;


import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        // Should we make a program to interact with or just print everything?
        int size = NameRepository.getSize();
        System.out.println("Size at start: " + size);
        NameRepository.setNames(new String[]{"Erik Svensson", "Mehrdad Javan"});
        System.out.println("Size after setNames(): " + NameRepository.getSize());
        // call more methods as needed

        printAllNames(NameRepository.findAll());
        textDivider();

        String[] namesToAdd = {"Anna Skog", "John Doe", "Harriet Greenbottle", "John Doe", "Anna Eriksson"};
        for(String name : namesToAdd) {
            boolean result = NameRepository.add(name);
            if(result) {
                System.out.println("Name added successfully: " + name);
            } else {
                System.out.println("Name already exists: " + name);
            }
        }

        printAllNames(NameRepository.findAll());
        textDivider();

        String[] namesToSearch = {"JOHN DOE", "Johnny Danny", "Anna Skog"};
        for(String name : namesToSearch) {
            printAllNames(name, NameRepository.find(name));
        }
        textDivider();

        printAllNames("Anna", NameRepository.findByFirstName("Anna"));
        printAllNames("Glenn", NameRepository.findByFirstName("Glenn"));
        printAllNames("Doe", NameRepository.findByLastName("Doe"));
        printAllNames("Andersson", NameRepository.findByLastName("Andersson"));
        textDivider();

        String[] namesToRemove = {"Anton Svensson", "John Doe"};
        for(String name : namesToRemove) {
            if(NameRepository.remove(name)) {
                System.out.println("The name " +  name + " was successfully removed");
            } else {
                System.out.println("The name " +  name + " isn't in the list and therefore can't be removed");
            }
            printAllNames(NameRepository.findAll());
        }
        textDivider();

        String[][] namesToUpdate = {{"John Doe", "Johnny Dough"},{"Harriet Greenbottle", "Harriet Yellowbottle"}};
        for (int i = 0; i < namesToUpdate.length; i++) {
            if(NameRepository.update(namesToUpdate[i][0],namesToUpdate[i][1])) {
                System.out.println("The name " +  namesToUpdate[i][0] + " was successfully updated to " + namesToUpdate[i][1]);
            } else {
                System.out.println("The name wasn't updated. Either the name " +  namesToUpdate[i][0] + " isn't in the list or the name " + namesToUpdate[i][1] + " already exists.");
            }
            printAllNames(NameRepository.findAll());
        }
        textDivider();

        NameRepository.clear();
        System.out.println("after clear(): " + NameRepository.getSize());

    }


    private static void printAllNames(String name, String result) {
        if(result == null) {
            System.out.println("Sorry, the name " + name + " is not in the list");
        } else {
            System.out.println("Name found: " + result);
        }
    }
    private static void printAllNames(String[] result) {
        System.out.print("All names: ");
        for (int i = 0; i < result.length; i++) {
            if (i == result.length - 1) {
                System.out.println(result[i] + "\n");
            } else {
                System.out.print(result[i] + ", ");
            }
        }
    }
    private static void printAllNames(String name, String[] result) {
        if(result == null) {
            System.out.println("Sorry, the name " + name + " is not in the list");
        } else if(result.length == 1) {
            System.out.println("Name found: " + result[0]);
        } else {
            printAllNames(result);
        }
    }

    private static void textDivider() {
        System.out.println("»»»»»»»»»»»»»»»»»»»»» \n");
    }

}
