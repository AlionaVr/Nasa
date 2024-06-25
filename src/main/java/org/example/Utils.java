package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import javax.imageio.IIOException;
import java.io.IOException;

public class Utils {
    public static String getUrl(String url){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        ObjectMapper mapper = new ObjectMapper();
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            NasaAnswer answer = mapper.readValue(response.getEntity().getContent(), NasaAnswer.class);
            String imageUrl = answer.url;
            return imageUrl;
        }
        catch (IOException e ) {
            e.printStackTrace();
        }
        return "";
    }
}
