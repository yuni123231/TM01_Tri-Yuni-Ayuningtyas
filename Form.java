import Model.ResponseModel;
import org.json.JSONArray;
import org.json.JSONObject;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Form extends JFrame{
    private JPanel FromPanel;
    private JTextField tfStatus;
    private JTextField tfMessage;
    private JTextField tfComment;
    private JButton submitButton;
    private JButton closeButton;

    public Form() {

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ConnectURI koneksi = new ConnectURI();
                    URL myAddress = koneksi.builduRL
                            ("https://harber.mimoapps.xyz/api/getaccess.php");
                    String response = koneksi.getResponseFromHttpUrl(myAddress);
                    System.out.println(response);

                    JSONArray responseJSON = new JSONArray();
                    ArrayList<ResponseModel> responseModel = new ArrayList<>();
                    for (int i = 0; i < responseJSON.length(); i++) {
                        ResponseModel resModel = new ResponseModel();
                        JSONObject myJSONObject = responseJSON.getJSONObject(i);
                        resModel.setMsg(myJSONObject.getString("message"));
                        resModel.setStatus(myJSONObject.getString("status"));
                        resModel.setComment(myJSONObject.getString("comment"));
                        responseModel.add(resModel);
                    }

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfMessage.setText("");
                tfStatus.setText("");
                tfComment.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Formulir GUI");
        frame.setContentPane(new Form().FromPanel);
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
}

