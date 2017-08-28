package topology.definition;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.apache.storm.topology.IRichBolt;

/**
 * Created by jim on 28/8/2017.
 */

public class BoltDef {
    @JacksonXmlProperty(localName = "id")
    private String id;
    @JacksonXmlProperty(localName = "className")
    private Class BoltImpl;
    @JacksonXmlProperty(localName = "parallelism")
    private int parallelism_hint=1;

    public BoltDef() {

    }

    public BoltDef(String id, Class boltImpl, int parallelism_hint) {
        this.id = id;
        BoltImpl = boltImpl;
        this.parallelism_hint = parallelism_hint;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class getBoltImpl() {
        return BoltImpl;
    }

    public void setBoltImpl(Class boltImpl) {
        BoltImpl = boltImpl;
    }

    public int getParallelism_hint() {
        return parallelism_hint;
    }

    public void setParallelism_hint(int parallelism_hint) {
        this.parallelism_hint = parallelism_hint;
    }
}
