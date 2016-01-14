package org.baddev.learning.demos.se_basics.collections.userdata36;

import java.util.HashMap;
import java.util.Map;

public class UserDataDemo {

    public static Map<String, String> getFakeUsersData(){
        Map<String, String> userData = new HashMap<>();
        userData.put("donkey", "secret_password123");
        userData.put("sammiq", "qwer21");
        userData.put("simpson", "dog_cat5");
        userData.put("gospel", "mypassword212");
        userData.put("king_power", "bet23");
        return userData;
    }

    /**
     * Prints only users with password length greater than defined as a parameter
     * @param usersData contains user names and passwords
     * @param passLength predicate which defines which users to be printed
     */
    public static void printUsers(Map<String, String> usersData, int passLength){
        usersData.entrySet().stream().filter(u -> u.getValue().length()>passLength).forEach(System.out::println);
    }

    public static void main(String[] args) {
        Map<String, String> users = getFakeUsersData();
        printUsers(users,6);
    }

}
