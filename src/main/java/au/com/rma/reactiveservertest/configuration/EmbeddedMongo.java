package au.com.rma.reactiveservertest.configuration;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.context.annotation.Bean;

public class EmbeddedMongo {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public MongodExecutable embeddedMongo() throws Exception {
        return MongodStarter.getDefaultInstance()
                .prepare(new MongodConfigBuilder()
                        .version(Version.Main.PRODUCTION)
                        .net(new Net("localhost", 27017, Network.localhostIsIPv6()))
                        .build());
    }

}
