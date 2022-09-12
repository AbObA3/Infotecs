package Client;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

public class FTPClientImpl implements FTPClient {

    private String server;
    private int port;
    private String user;
    private String password;
    private org.apache.commons.net.ftp.FTPClient ftp;
    private int mode;

    public FTPClientImpl(String server, int port, String user, String password, int mode) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.password = password;
        this.mode = mode;
    }


    @Override
    public void open() throws IOException {
        ftp = new org.apache.commons.net.ftp.FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        ftp.connect(server, port);
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new IOException("Error to create connection with FTP server");
        }
        if (mode == 1) {
            ftp.enterLocalActiveMode();
        } else {
            ftp.enterLocalPassiveMode();
        }
        ftp.login(user, password);

    }

    @Override
    public void close() throws IOException {
        ftp.disconnect();
    }

    @Override
    public void downloadFile() throws IOException {
        FileOutputStream out = new FileOutputStream("students.json");
        ftp.retrieveFile("/students.json", out);
    }

    @Override
    public void UploadFile() throws URISyntaxException, IOException {
        ftp.storeFile("/students.json", new FileInputStream("students.json"));
    }
}
