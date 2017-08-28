package algorithms;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * Created by jim on 28/8/2017.
 */
public class DefaultSpout extends BaseRichSpout {

    String[] words = new String[]{"The", "brown", "fox", "quick", "jump", "sucky", "5dolla"};
    SpoutOutputCollector collector;
    private String id;

    public DefaultSpout(String id) {
        this.id = id;
    }


    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word"));
    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        collector = spoutOutputCollector;
    }

    @Override
    public void nextTuple() {
        while (true) {
            int rnd = (int) (Math.random() * 10 % words.length);
            collector.emit(new Values(words[rnd]));
        }
    }
}
