package se.lexicon;

import java.util.Arrays;

/**
 * The NameRepository class provides methods to manage a list of names.
 * It offers functionalities such as adding, removing, finding, and updating names.
 */
public class NameRepository {

    private static String[] names = new String[0];


    /**
     * Retrieves the current size of the names array.
     *
     * @return The number of elements in the names array.
     */
    public static int getSize() {
        return names.length;
    }


    /**
     * Sets the names array to the provided array of names & it should replace all existing names.
     *
     * @param names The array of names to set.
     */
    public static void setNames(String[] names) {
        NameRepository.names = Arrays.copyOf(names, names.length);
    }


    /**
     * Clears the names array by creating a new empty array.
     */
    public static void clear() {
        names = new String[0];
    }


    /**
     * Returns all names in a new array (Retrieves a copy of the names array).
     *
     * @return A new array containing all elements from the names array.
     */
    public static String[] findAll() {
        return Arrays.copyOf(names, names.length);
    }


    /**
     * Finds a name that matches the given fullName case-insensitively.
     *
     * @param fullName The full name to search for.
     * @return The matching name if found; otherwise, null.
     */
    public static String find(String fullName) {
        String[] sortedNames = Arrays.copyOf(names, names.length);
        for (int i = 0; i < sortedNames.length; i++) {
            sortedNames[i] = sortedNames[i].toLowerCase();
        }
        fullName = fullName.toLowerCase();
        Arrays.sort(sortedNames);

        int foundNameIndex = Arrays.binarySearch(sortedNames, fullName);
        if(foundNameIndex < 0) {
            return null;
        } else {
            String[] foundName = sortedNames[foundNameIndex].split(" ");
            for (int i = 0; i < foundName.length; i++) {
                foundName[i] = foundName[i].substring(0, 1).toUpperCase() + foundName[i].substring(1);
            }
            return foundName[0] + " " + foundName[1];
        }
    }


    /**
     * Adds a new fullName to the names array if it doesn't already exist.
     *
     * @param fullName The full name to add.
     * @return True if the fullName is added successfully; false if it already exists.
     */
    public static boolean add(String fullName) {
        boolean foundName = false;

        if(find(fullName) == null) {
            names = Arrays.copyOf(names, names.length + 1);
            names[names.length-1] = fullName;
            foundName = true;
        }
        return foundName;
    }


    /**
     * Find all names that match the given firstName.
     *
     * @param firstName The first name to search for.
     * @return An array containing all matching names.
     */
    public static String[] findByFirstName(String firstName) {
        String[] foundNames = new String[0];
        for (int i = 0; i < names.length; i++) {
            if(names[i].startsWith(firstName)) {
                foundNames = Arrays.copyOf(foundNames, foundNames.length + 1);
                foundNames[foundNames.length-1] = names[i];
            }
        }

        if(foundNames.length == 0) {
            return null;
        } else {
            return foundNames;
        }
    }


    /**
     * Find all names that match the given lastName.
     *
     * @param lastName The last name to search for.
     * @return An array containing all matching names.
     */
    public static String[] findByLastName(String lastName) {
        String[] foundNames = new String[0];
        for (int i = 0; i < names.length; i++) {
            if(names[i].endsWith(lastName)) {
                foundNames = Arrays.copyOf(foundNames, foundNames.length + 1);
                foundNames[foundNames.length-1] = names[i];
            }
        }

        if(foundNames.length == 0) {
            return null;
        } else {
            return foundNames;
        }
    }


    /**
     * Updates a name in the names array from the original name to the updated name.
     *
     * @param original    The original name to update.
     * @param updatedName The updated name to set.
     * @return True if the name is updated successfully; false if the updated name already exists or the original name is not found.
     */
    public static boolean update(String original, String updatedName) {
        boolean updated = false;

        for (int i = 0; i < names.length; i++) {
            if(names[i].equalsIgnoreCase(original)) {
                names[i] = updatedName;
                updated = true;
            }
        }

        return updated;
    }


    /**
     * Removes a name from the names array, case-insensitively.
     *
     * @param fullName The full name to remove.
     * @return True if the name is removed successfully; false if the name is not found in the array.
     */
    public static boolean remove(String fullName) {
        boolean removed = false;

        for (int i = 0; i < names.length; i++) {
            if(names[i].equalsIgnoreCase(fullName)) {
                String[] firstHalf = Arrays.copyOfRange(names, 0, i);
                String[] secondHalf = Arrays.copyOfRange(names, i+1, names.length);

                names = Arrays.copyOf(firstHalf, firstHalf.length + secondHalf.length);
                System.arraycopy(secondHalf, 0, names, firstHalf.length, secondHalf.length);

                removed = true;
            }
        }

        return removed;
    }


}