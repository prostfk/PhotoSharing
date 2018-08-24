package by.prostrmk.model.util;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FileUtil {



    public void deleteFile(String path){
        File file = new File("src/main/webapp/" + path);
        try{
            file.delete();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    @Deprecated
    public String saveFile(MultipartFile file){
        String path = null;
            if (!file.isEmpty()){
                try{
                    byte []bytes = file.getBytes();
                    String name = file.getOriginalFilename();
                    File directory = new File("src/main/webapp/resources/static/newsData");
                    if (!directory.exists()){
                        directory.mkdirs();
                    }
                    String pathName = directory.getAbsolutePath() + File.separator + name;
                    File uploadedFile = new File(pathName);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                    stream.write(bytes);
                    stream.flush();
                    stream.close();
                    path = "/resources/static/newsData/" +  name;
                } catch (IOException e) {
                    System.out.println(e.toString());
                }

        }
        return path;
    }

    public String saveFile(MultipartFile file, String username){
        String path = null;
        if (!file.isEmpty()){
            try{
                byte []bytes = file.getBytes();
                String name = file.getOriginalFilename();
                File directory = new File("src/main/webapp/resources/userFiles/" + username);
                if (!directory.exists()){
                    directory.mkdirs();
                }
                String pathName = directory.getAbsolutePath() + File.separator + name;
                File uploadedFile = new File(pathName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                path = "/resources/userFiles/" + username + "/" + file.getOriginalFilename();
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
        return path;
    }



}
