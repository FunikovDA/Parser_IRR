import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class IrrNewBuilding {

    ArrayList<String> flatsList;

    public IrrNewBuilding() throws IOException {

        int n = 1;
        int nn;

        String url = "http://belarus.irr.by/realestate/new/search/list=list/page" + n + "/";
        Document page = Jsoup.parse(new URL(url), 3000); // ������������ � 1-�� �������� �����
        Element countPages = page.getElementsByClass("same_adds_paging").first(); // ��� ���������� ���������� �������
        String[] countP = countPages.text().split(" ");
        nn = Integer.parseInt(countP[countP.length - 1]); // nn - ���������� �������

        flatsList = new ArrayList<>();

        do { // ��������� ���� �� ���������� �������
            Elements appartments = page.getElementsByClass("add_info");

            for (Element appartment : appartments) { // ��������� ���� �� ���� ������������ �� ����� �������� �����

                Elements rooms = appartment.getElementsByClass("add_title_wrap");
                Elements cities = appartment.getElementsByClass("city");
                Elements districts = appartment.getElementsByClass("placed");
                Elements sizes = appartment.getElementsByClass("flat_prop");
                Elements costs = appartment.getElementsByClass("right_block");

                String namesCities = cities.select("a").text();
                flatsList.add(namesCities); // ���������� ���������� �������� � ������

                String district = districts.first().text();
                String metro = districts.last().text();
                if (district.equals(metro)) metro = "";
                district  = district .replace(namesCities, "");
                flatsList.add(district );

                String[] numberRooms = rooms.select("a").text().split(",");
                String adress = "";
                if (numberRooms.length > 1) {
                    for (int i = 1; i < numberRooms.length; i++) {
                        adress = adress.concat(numberRooms[i]);
                    }
                }
                flatsList.add(adress);
                flatsList.add(metro);

                String size = sizes.text();
                int ind0 = size.indexOf("�������");
                String sizeSquare = size.substring(0, ind0);
                flatsList.add(sizeSquare);

                int ind1 = size.indexOf("����");
                String floor = size.substring(ind0 + 8, ind1);
                flatsList.add(floor);

                int ind2 = size.indexOf("���");
                String yearBuild = size.substring(ind1 + 4, ind2);
                flatsList.add(yearBuild);

                String cost = costs.text();
                ind0 = cost.indexOf("���");
                String costRub = ind0 == -1 ? "" : cost.substring(0, ind0);
                flatsList.add(costRub);

                ind1 = cost.indexOf("$");
                String costDol = ind1 == -1 ? "" : cost.substring(ind0 + 4, ind1);
                flatsList.add(costDol);

                ind2 = cost.indexOf("�");
                String costEur = ind2 == -1 ? "" : cost.substring(ind1 + 1, ind2);
                flatsList.add(costEur);

                ind0 = cost.indexOf(",");
                ind1 = cost.indexOf("�");
                String dateAdd = cost.substring(ind0 + 1, ind1);
                flatsList.add(dateAdd);
            }
            n++;
            url = "http://belarus.irr.by/realestate/new/search/list=list/page" + n + "/";
            page = Jsoup.connect(url).get(); // ��������� �� ��������� �������� �����
        } while (n <= nn + 1);
    }

    public ArrayList<String> getFlatsList() {
        return flatsList;
    }
}

