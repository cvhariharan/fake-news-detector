/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fakenews;
import weka.core.Instances;
import java.io.*;
import weka.classifiers.functions.SGDText;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.converters.ConverterUtils.DataSource;
/**
 *
 * @author thero
 */
public class Detector {
  
    public Detector(String news) throws Exception
    {
        String toWrite = "@relation 'D__Study Materials_Research Papers_manually_Test Dataset'\n" +
                        "\n" +
                        "@attribute text string\n" +
                        "@attribute @@class@@ {fake,real}\n" +
                        "\n" +
                        "@data\n" +
                        "'";
        FileWriter fw = new FileWriter("data/test.arff");
        BufferedWriter bw = new BufferedWriter(fw);
        news = news+"',?";
        toWrite += news;
        bw.write(toWrite);
        bw.close();
    }
    
    public String detect() throws Exception
    {
        DataSource source = new DataSource("data/test.arff");
        Instances data = source.getDataSet();
        if (data.classIndex() == -1)
            data.setClassIndex(data.numAttributes() - 1);
        SGDText sgd = new SGDText();
        sgd = (SGDText)weka.core.SerializationHelper.read("model/fake_news_detector_model4.model");
        
        Instance newInst = data.instance(0);
        double realOrFake = sgd.classifyInstance(newInst);
        return (realOrFake==0.0?"Fake":"Real");
    }
}
