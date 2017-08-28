import algorithms.DefaultBolt;
import algorithms.DefaultSpout;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.Bolt;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseComponent;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.utils.Utils;
import topology.definition.BoltDef;
import topology.definition.SpoutDef;
import topology.definition.TopologyDef;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by jim on 28/8/2017.
 */
public class TopologyDemo {
    static volatile boolean keepRunning = true;
    public static final Thread mainThread = Thread.currentThread();

    public static void main(String[] args) {
        ObjectMapper objectMapper = new XmlMapper();
        TopologyDef topology = null;
        Object obj = new Object();
        System.out.println(obj.getClass());
        try {
            topology = objectMapper.readValue(
                    StringUtils.toEncodedString(Files.readAllBytes(Paths.get("src/main/resources/simpleTopology.xml")), StandardCharsets.UTF_8),
                    TopologyDef.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BoltDef boltDef = topology.getBolts().get(0);
        Constructor[] constructorsBolt = boltDef.getBoltImpl().getConstructors();
        IRichBolt bolt = null;

        SpoutDef spoutDef = topology.getSpouts().get(0);
        Constructor[] constructorsSpout = spoutDef.getSpoutImpl().getConstructors();
        IRichSpout spout = null;

        try {
            bolt = (IRichBolt) constructorsBolt[0].newInstance(boltDef.getId());
            spout = (IRichSpout) constructorsSpout[0].newInstance(spoutDef.getId());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout(spoutDef.getId(),spout,spoutDef.getParallelism_hint());
        builder.setBolt(boltDef.getId(),bolt,boltDef.getParallelism_hint()).shuffleGrouping(spoutDef.getId());

        Config config = new Config();
        config.setDebug(true);
        System.out.println(topology);

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("test", config, builder.createTopology());
        Utils.sleep(100000000);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Shutdown--------------------------");
                keepRunning = false;
                // mongoConnectorProcess.destroyProcess();
                try {
                    mainThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cluster.killTopology("test");
                cluster.shutdown();
            }
        });
    }

}
