package topology.definition;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.apache.storm.topology.IRichSpout;

/**
 * Created by jim on 28/8/2017.
 */
public class SpoutDef {

    @JacksonXmlProperty(localName = "id")
    protected String id;
    @JacksonXmlProperty(localName = "className")
    private Class SpoutImpl;
    @JacksonXmlProperty(localName = "parallelism")
    private int parallelism_hint =1;

    public SpoutDef(){}

    public SpoutDef(String id, Class spoutImpl, int parallelism_hint) {
        this.id = id;
        SpoutImpl = spoutImpl;
        this.parallelism_hint = parallelism_hint;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class getSpoutImpl() {
        return SpoutImpl;
    }

    public void setSpoutImpl(Class spoutImpl) {
        SpoutImpl = spoutImpl;
    }

    public int getParallelism_hint() {
        return parallelism_hint;
    }

    public void setParallelism_hint(int parallelism_hint) {
        this.parallelism_hint = parallelism_hint;
    }


}
