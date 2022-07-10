import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        IrrNewBuilding irrNewBuilding = new IrrNewBuilding();
        ArrayList<String> flatsList;
        flatsList = irrNewBuilding.getFlatsList();
        String SHEET_NAME = "Новостройки";

        new FormingExcel(flatsList, SHEET_NAME);
        flatsList.clear();
    }
}
