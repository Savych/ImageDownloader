package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Controller {

    public TextArea textURL;
    public Button btnGetImage;
    public Button btnDownloadFile;
    public Button viewPic;
    public ImageView imageView;
    public MenuItem menuSave;
    public MenuItem menuExit;
    private static BufferedImage image;


    public static void setImage(URL url) {
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {}
    }
    public static void setImage(File file) {
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {}
    }
    public static void saveImage(File file) {
        try {
            ImageIO.write(image,"png", file);
        } catch (IOException e) {}
    }

    public static BufferedImage getImage() {
        return image;
    }

    public void clickBtnGetImage() {
        try {
            setImage(new URL(textURL.getText()));
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Неправильная URL-ссылка или проблема с сетью");
        }
    }
    public void clckBtnDownloadFile() {
        JFileChooser chooser = new JFileChooser();
        int reply = chooser.showOpenDialog(null);
        if(reply == JFileChooser.APPROVE_OPTION)
            setImage(chooser.getSelectedFile());
    }
    public void clckViewPic() {
        if(this.getImage() == null) return;
        Image imView = SwingFXUtils.toFXImage(image, null);
        imageView.setImage(imView);
    }
    public void clckMenuSave() {
        JFileChooser chooser = new JFileChooser();
        int reply = chooser.showSaveDialog(null);
        if(reply == JFileChooser.APPROVE_OPTION)
            saveImage(chooser.getSelectedFile());
    }
    public void clickExit() {
        System.exit(0);
    }
}

