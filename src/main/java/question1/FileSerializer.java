package question1;

public class FileSerializer implements Serializer {
    String name, caract;

    public FileSerializer(String name, String caract ){
        this.name = name;
        this.caract=caract;

    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public Integer size() {
        Integer size = 0;
        for(int i =0; i<caract.length() ; i++){
            size ++;
        }
        return size;
    }

    @Override
    public void write() {
        System.out.println("La chaine qui a ete serializer est = "+ this.caract + " et le nom du fichier est = " + this.name);
    }
}
