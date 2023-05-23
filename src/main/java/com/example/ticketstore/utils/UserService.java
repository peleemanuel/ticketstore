package com.example.ticketstore.utils;

import com.example.ticketstore.exceptions.CouldNotWriteUsersException;
import com.example.ticketstore.exceptions.EmptyUsernameOrPasswordException;
import com.example.ticketstore.exceptions.UserDoesNotExistException;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import com.example.ticketstore.exceptions.UsernameAlreadyExistsException;
import com.example.ticketstore.models.User;
import org.dizitart.no2.objects.filters.ObjectFilters;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import static com.example.ticketstore.utils.FileSystemService.getPathToFile;

public class UserService {

    private static Nitrite db;
    private static ObjectRepository<User> userRepository;

    // public static void initDatabase() {
    //     Nitrite database = Nitrite.builder()
    //             .filePath(getPathToFile("registration-data.db").toFile())
    //             .openOrCreate("test", "test");
    //
    //     userRepository = database.getRepository(User.class);
    // }

    public static void loadUsersFromDatabase() {
        db = Nitrite.builder().compressed().filePath("user-database.db").openOrCreate();

        userRepository = db.getRepository(User.class);
    }

    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException, CouldNotWriteUsersException {
        // checkUserDoesNotAlreadyExist(username);
        // userRepository.insert(new User(username, encodePassword(username, password), role));

        try {
            checkUserDoesNotAlreadyExistOrIsNull(username);
            User user = new User(username, encodePassword(username, password), role);
            userRepository.insert(user);
        } catch (CouldNotWriteUsersException | EmptyUsernameOrPasswordException e) {
            e.printStackTrace();
        }
        closeDatabase();

    }

    private static void checkUserDoesNotAlreadyExistOrIsNull(String username) throws UsernameAlreadyExistsException, EmptyUsernameOrPasswordException {
        if (username.isBlank())
            throw new EmptyUsernameOrPasswordException();

        User existingUser = userRepository.find(ObjectFilters.eq("username", username)).firstOrDefault();

        if (existingUser != null) {
            throw new UsernameAlreadyExistsException(username);
        }

    }

    public static boolean checkUserExists(String username) throws UserDoesNotExistException {
        // if the user is found then return True
        User existingUser = userRepository.find(ObjectFilters.eq("username", username)).firstOrDefault();

        return existingUser != null;
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder encodedPassword = new StringBuilder();
        for (byte b : hashedPassword) {
            encodedPassword.append(String.format("%02x", b));
        }

        return encodedPassword.toString();
    }

    public static boolean checkPassword(String salt, String username, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        User user = userRepository.find(ObjectFilters.eq("username", username)).firstOrDefault();

        if (user == null) {
            // User is not found
            return false;
        }

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder encodedPassword = new StringBuilder();
        for (byte b : hashedPassword) {
            encodedPassword.append(String.format("%02x", b));
        }

        return encodedPassword.toString().equals(user.getPassword());
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static List<User> getUsers() {
        // Retrieve all users from the Nitrite database
        return userRepository.find().toList();
    }

    public static void closeDatabase() {
        db.close();
    }

}