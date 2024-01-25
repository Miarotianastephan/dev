package com.dev.models;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.multipart.MultipartFile;

import com.dev.exception.ExceptionCar;


public class ImageService {
    public ImageService(){}

    // private byte[] compressBytes2(byte[] originalBytes,String originalname) throws IOException {
    //     ByteArrayInputStream inputStream = new ByteArrayInputStream(originalBytes);
    //     BufferedImage bufferedImage = ImageIO.read(inputStream);

    //     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    //     if(   originalname.substring(originalname.lastIndexOf(".")+1).compareToIgnoreCase("png")==0 ){
    //         ImageIO.write(bufferedImage, "png", outputStream);
    //     }else{
    //         ImageIO.write(bufferedImage, "jpeg", outputStream);
    //     }
        
    //     return outputStream.toByteArray();
    // }
    public static byte[] compressBytes(byte[] originalImageData,double lasttaille,String originalname) throws IOException {
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(originalImageData));

        // Créer un flux de sortie pour stocker l'image compressée
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String extension="jpeg";
        if(originalname.substring(originalname.lastIndexOf(".")+1).compareToIgnoreCase("png")==0){ extension="PNG"; }
        ImageIO.write(originalImage, extension, outputStream);
        if( ((double)outputStream.size()/1024.0)>lasttaille ){
            return originalImageData;
        }

        byte[] compressedImageData = outputStream.toByteArray();
        return compressedImageData;
    }
    public MultipartFile compressImage(MultipartFile originalFile) throws IOException {
        byte[] compressedBytes = compressBytes(originalFile.getBytes(),(((double)originalFile.getSize() )/(1024.0)),originalFile.getOriginalFilename());
        return (MultipartFile)new CustomMultipartFile(compressedBytes, originalFile.getOriginalFilename());
    }
    public MultipartFile[] compressImage(MultipartFile[] multipartFiles)throws Exception{
        if(multipartFiles==null){ return null; }
        MultipartFile[] multipartFiles2=new MultipartFile[multipartFiles.length];
        System.out.println();
        for(int i=0;i<multipartFiles.length;i++){
            System.out.print(multipartFiles[i].getOriginalFilename()+": "+(((double)multipartFiles[i].getSize() )/(1024.0))+"ko et ");
            multipartFiles2[i]=compressImage(multipartFiles[i]);
            System.out.print(multipartFiles2[i].getOriginalFilename()+": "+(((double)multipartFiles2[i].getSize() )/(1024.0))+"ko\n");
        }
        return multipartFiles2;
    }

    public String[] uploadAndGetLinksImage(MultipartFile[] files)throws Exception {
            String apiKey = "ca2d96c9ae967bb975557194bd8ec9e3";//<---- key le azon'la t@ alalan'le compte no-creern'ela t@ imagebb no atao eto,  
            List<String> imgBbResponses = uploadImagesToImgBB(apiKey, files);
            String[] urlimages=new String[imgBbResponses.size()];
            String strTemp="";
            int id1=0;
            for(int i=0;i<imgBbResponses.size();i++){
                strTemp=imgBbResponses.get(i);
                id1=strTemp.indexOf("\"url\":");
                strTemp=strTemp.substring(id1+"\"url\":".length(), strTemp.length());
                urlimages[i]=strTemp.substring(0,strTemp.indexOf(",")).replaceAll(" ","").replaceAll("\"","");
                if(urlimages[i].contains(":400")==true){ throw new ExceptionCar("erreur upload photo"); }
                // resultMap= objectMapper.readValue(imgBbResponses.get(i), new TypeReference<HashMap<String, Object>>(){});
                // System.out.println(resultMap.get("data"));
                // resultMap= objectMapper.readValue((String)resultMap.get("data"), new TypeReference<HashMap<String, Object>>(){});
                // urlimages[i]=(String)resultMap.get("url")  ;
                System.out.println(urlimages[i]);
            }
            return urlimages;

    }
    private List<String> uploadImagesToImgBB(String apiKey, MultipartFile[] imageFiles) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        List<String> imgBbResponses = Arrays.asList(new String[imageFiles.length]);
        for (int i = 0; i < imageFiles.length; i++) {
            MultipartFile file = imageFiles[i];
            HttpPost httpPost = new HttpPost("https://api.imgbb.com/1/upload");

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("key", apiKey);
            builder.addBinaryBody("image", IOUtils.toByteArray(file.getInputStream()), ContentType.MULTIPART_FORM_DATA,
                    file.getOriginalFilename());

            HttpEntity multipartEntity = builder.build();
            httpPost.setEntity(multipartEntity);

            HttpResponse response = httpClient.execute(httpPost);

            // Extract and store the response body in the list
            imgBbResponses.set(i, IOUtils.toString(response.getEntity().getContent(), "UTF-8"));
        }

        return imgBbResponses;
    }
}
