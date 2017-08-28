package parser;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jim on 28/8/2017.
 */
public class FusionParser  {

    private static final Logger LOG = Logger.getLogger(FusionParser.class);

    private FusionParser() {}

    private Parser parser;

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public  void parseTopology(String inputFile){
        //delegate the parsing to an interface since there will be many implementations
        parser.parseTopology(inputFile);
    }

}
