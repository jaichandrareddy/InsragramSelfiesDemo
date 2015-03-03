package inst.app.jcr.com.instagramsample.connection;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by chandra on 3/2/15.
 */
public class HttpConnectionHandler {
    public static final String GET = "GET";

    public static final String POST = "POST";
    public static final int TIMEOUT = 20000;

    Ihandler _handler;

    private String _url;

    public HttpConnectionHandler(String url, Ihandler handler) {
        _url = url;
        _handler = handler;
    }

    public void executeHttpGET() {

        try {
            // defaultHttpClient
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(_url);
            HttpResponse httpResponse = httpClient.execute(httpGet);


            StatusLine statusLine = httpResponse.getStatusLine();
            System.out.println("StatusLine: [" + statusLine.getStatusCode() + " ,  " + statusLine.getReasonPhrase() + "]");

            createHttpServiceResponse(httpResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createHttpServiceResponse(HttpResponse httpResponse) throws IOException {

        InputStream is = null;
        try {

            if (httpResponse != null) {

                String respMsg = httpResponse.getStatusLine().getReasonPhrase();
                int respCode = httpResponse.getStatusLine().getStatusCode();
                System.out.println("Response :::::::: " + respCode + "  :: " + respMsg);

                if (respCode == HttpStatus.SC_OK) {

                    Header headerType = httpResponse.getEntity().getContentType();

                    // open the InputStream
                    is = httpResponse.getEntity().getContent();

                    // Report the ContentLength
                    long len = httpResponse.getEntity().getContentLength();

                    System.out.println("len == " + len);
                    String resultData = null;
                    resultData = readDataFromStream(is);
                    is.close();
                    if (_handler != null) {

                        _handler.handleDownloadResponse(resultData);
                    }
                } else {
                    if (_handler != null) {
                        _handler.notifyError("Error : " + respCode + " : " + respMsg);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception while creating a service response object : " + e.getMessage());
            e.printStackTrace();

        }
    }

    public String readDataFromStream(InputStream inStream) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

        StringBuilder strBuilder = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                strBuilder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return strBuilder.toString();


    }


}
