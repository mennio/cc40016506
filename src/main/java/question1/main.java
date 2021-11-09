package question1;

public class main {
    public static void main(String args[]) {
        java.util.Scanner entree =   new java.util.Scanner(System.in);
        int size;
        String name, caract;
        System.out.println("veuillez saisir le nom");
        name = entree.next();
        System.out.println("veuillez saisir une chaine de caractère");
        caract =entree.next();

        ScreenSerializer s1 = new ScreenSerializer(name, caract);
        System.out.println(s1.name());
        System.out.println(s1.size());
        s1.write();






    }
}
