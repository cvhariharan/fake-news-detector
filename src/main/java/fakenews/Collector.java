/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fakenews;
import com.hariharan.textsummarizer.TextSum;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 *
 * @author thero
 */
public class Collector {
    
    private ArrayList<String> urls = new ArrayList<String>();
    public Collector() throws IOException
    {
        for(int i=2;i<9;i++)
        {
        Document doc = Jsoup.connect("http://www.theunrealtimes.com/category/national/page/"+i).get(); //Change the last part of url to scrape other pages
        Elements newsLinks = doc.select("div.post-thumb"); //Change the class ID for each website
        //Elements li = newsLinks.select("li");
        for(Element link: newsLinks)
        {
            Element t = link.select("a").first();
            System.out.println(t.attr("href"));
            urls.add(t.attr("href"));
        }
        }
        NewScraper newScraper = new NewScraper(urls);
    }
    
}
