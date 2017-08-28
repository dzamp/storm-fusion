package topology.definition;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.apache.storm.topology.IComponent;
import org.apache.storm.topology.IRichBolt;

/**
 * Created by jim on 28/8/2017.
 */
@JacksonXmlRootElement(localName = "streams")
public class StreamConnectionDef {

    //Types of stream groupings Stoorm allows
    public static enum Type {
        ALL,
        CUSTOM,
        DIRECT,
        SHUFFLE,
        LOCAL_OR_SHUFFLE,
        FIELDS,
        GLOBAL,
        NONE
    }
    @JacksonXmlProperty(localName = "type")
    private Type type;
    @JacksonXmlProperty(localName = "from")
    private String from;
    @JacksonXmlProperty(localName = "to")
    private String to;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
