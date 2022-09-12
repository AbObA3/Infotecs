package Client;

import java.io.IOException;
import java.net.URISyntaxException;

public interface FTPClient {

    void open() throws IOException;

    void close() throws IOException;

    void downloadFile() throws IOException;

    void UploadFile() throws URISyntaxException, IOException;
}
