package topology.definition;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;

/**
 * Created by jim on 28/8/2017.
 */
@JacksonXmlRootElement(localName = "topology")
public class TopologyDef {
    @JacksonXmlProperty(localName = "bolts")
    private ArrayList<BoltDef> bolts;
    @JacksonXmlProperty(localName = "spouts")
    private ArrayList<SpoutDef> spouts;
    @JacksonXmlProperty(localName = "streams", isAttribute = true)
    private ArrayList<StreamConnectionDef> streams;


    public TopologyDef() {
    }

    public TopologyDef(ArrayList<BoltDef> bolts, ArrayList<SpoutDef> spouts) {
        this.bolts = bolts;
        this.spouts = spouts;
    }

    public ArrayList<BoltDef> getBolts() {
        return bolts;
    }

    public void setBolts(ArrayList<BoltDef> bolts) {
        this.bolts = bolts;
    }

    public ArrayList<SpoutDef> getSpouts() {
        return spouts;
    }

    public void setSpouts(ArrayList<SpoutDef> spouts) {
        this.spouts = spouts;
    }

    public ArrayList<StreamConnectionDef> getStreams() {
        return streams;
    }

    public void setStreams(ArrayList<StreamConnectionDef> streams) {
        this.streams = streams;
    }
}
