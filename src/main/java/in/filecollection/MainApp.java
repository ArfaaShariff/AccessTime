package in.filecollection; /**
 * Created by plank-arfaa on 9/4/2015.
 */


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainApp extends Application
{
    List FileList;

    public static void main(String args[])
    {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ApplicationContext context =new ClassPathXmlApplicationContext("beans.xml");
        FileAccessTime FileAccessObject = (FileAccessTime) context.getBean("fileAccessTime");
        this.FileList = FileAccessObject.ViewAccessTime();
        init(primaryStage);
        primaryStage.show();
    }
    private void init(Stage primaryStage)
    {
        primaryStage.setTitle("Spring and JavaFX Demonstration");
        //Title to be displayed in GUI.
        Label title = new Label();
        title.setText("Access Times");
        title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        //Vertical Box
        VBox vb = new VBox();
        //To Set the Alignment: Position - CENTER
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10, 50, 50, 50));
        vb.setSpacing(20);
        final TextArea textarea = new TextArea();
        textarea.setMinHeight(500);
        textarea.setMaxWidth(800);
        textarea.appendText("File List consists of the following files : \n" + FileList + "\n");
        Iterator it = FileList.iterator();
        while(it.hasNext())
        {
            String filepath = it.next().toString();
            BasicFileAttributes Attrs = FileAttrs(filepath);
            textarea.appendText("\n File name :" + filepath
                    + "\n Last Access Time :" + Attrs.lastAccessTime()
                    + "\n Last Modified Time :" + Attrs.lastModifiedTime());
        }
        vb.getChildren().addAll(title,textarea);
        Scene scene = new Scene(vb);
        primaryStage.setScene(scene);
    }
    /* Function to retrieve Basic File Attributes of given file */
    public BasicFileAttributes FileAttrs(String filepath)
    {
        File chosenfile = new File(filepath);
        Path file_dir = Paths.get(chosenfile.getParent());
        Path file = file_dir.resolve(chosenfile.getName());
        BasicFileAttributes attrs = null;
        try {
            attrs = Files.readAttributes(file, BasicFileAttributes.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return attrs;
    }
}
