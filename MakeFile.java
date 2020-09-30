import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MakeFile {
    public MakeFile(String result){
        String filename;
        boolean isFail;
        Scanner scanner= new Scanner(System.in);

        do {
            isFail=false;
            
            System.out.print("Masukkan nama file (tanpa ext.): ");  
            filename=scanner.nextLine();  

            File nf=new File("./",filename+".txt");
            if(!nf.exists() && !nf.isDirectory()){
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filename+".txt"));
                    writer.write(result);
                    writer.flush();
                    writer.close();
                    System.out.println("File sukses dibuat");
                } catch (IOException err){};
            } else {
                System.out.println("Nama file sudah diambil, coba dengan nama yang lain");
                isFail=true;
            }
        } while (isFail);
    }
}