package org.openjfx.util;


import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
@Builder
@Component
@RequiredArgsConstructor
public class MapquestApiService {

    public void GetImage() throws IOException {
        /**
         * We can create an HttpUrlConnection instance using the openConnection() method of the URL class.
         * a connection to a given URL using GET method
         */
        URL url = new URL("https://www.mapquestapi.com/staticmap/v5/map?start=Wien&end=Zeiselmauer,%20Austria&size=300,200@2x&key=SHSDJSLYJHAzO1Gw0ztvr8PTThk5upYH");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        /**
         * If we want to add parameters to a request, we have to set the doOutput property to true,
         * then write a String of the form param1=value¶m2=value to the OutputStream of the HttpUrlConnection instance
         *  Map<String, String> parameters = new HashMap<>();
         *         parameters.put("param1", "val");
         *          int status = http.getResponseCode();
         *  Reading the response of the request can be done by parsing the InputStream of the HttpUrlConnection instance.
         *
         * To execute the request, we can use the getResponseCode(), connect(), getInputStream() or getOutputStream()
         */

        InputStream responseStream = http.getInputStream();
        OutputStream os = new FileOutputStream("src/main/java/org/openjfx/views/images");

        byte[] b = new byte[2048];
        int length;

        while ((length = responseStream.read(b)) != -1) {
            os.write(b, 0, length);
        }

        responseStream.close();
        os.close();
        http.disconnect();
    }

}
