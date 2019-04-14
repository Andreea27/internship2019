import model.*;

import java.net.Socket;
import java.security.KeyStore;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Solution s = new Solution();
        try
        {s.getAtmsRoute().forEach(System.out::println);}catch (RuntimeException e){System.out.println(e.getMessage());}


    }
}
