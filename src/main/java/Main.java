public class Main {

    public static void main(String[] args) {
        String pw = "thrift,Test02";
        String salt = StringGenerator.generateSalt();
        String pwHash = PasswordHash.hash(pw.toCharArray(), salt);
        System.out.println(pwHash);
        System.out.println(salt);
    }
}
