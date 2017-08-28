package algorithms;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

/**
 * Created by jim on 28/8/2017.
 */
public class DefaultBolt extends BaseRichBolt{


    OutputCollector collector;
    String id;

    public DefaultBolt(String id) {
        this.id = id;
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        System.out.println(tuple.getString(0));
    }


    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //nothing
    }
}
