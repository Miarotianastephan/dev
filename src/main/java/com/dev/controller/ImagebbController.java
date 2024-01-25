package com.dev.controller;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImagebbController {

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("files") MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return new ResponseEntity<>("Veuillez sélectionner au moins un fichier.", HttpStatus.BAD_REQUEST);
        }
        try {
            String apiKey = "ca2d96c9ae967bb975557194bd8ec9e3";
            List<String> imgBbResponses = uploadImagesToImgBB(apiKey, files);
            return new ResponseEntity<>(imgBbResponses.toString(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Erreur lors de l'upload des images.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
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