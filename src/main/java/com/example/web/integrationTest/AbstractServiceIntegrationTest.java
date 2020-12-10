package com.example.web.integrationTest;

import com.example.web.testutils.Consumer;
import com.jayway.restassured.RestAssured;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Topic;
import javax.sql.DataSource;
import java.sql.SQLException;

public class AbstractServiceIntegrationTest {

    protected static final String HTTP = "http://";
    private static boolean isInitialized = false;
    private static Session session;
    private static Connection mqConnection;
    private static Consumer consumer;
    private static final String EVENT_TOPIC_NAME = ">";

    @Inject
    private Environment env;

    @Inject
    private DataSource dbtableDataSource;

    @Before
    public void setUpClass() throws SQLException{
        String protocol = HTTP;

        if(env.containsProperty("server.port")){
            RestAssured.port = Integer.parseInt(env.getProperty("server.port"));
        }
    }

    @Before
    public void setupDatabase() throws SQLException{
        if(isInitialized){
            return;
        }
        ScriptUtils.executeSqlScript(dbtableDataSource.getConnection() , new ClassPathResource("mySQL/dbTable.sql"));
        isInitialized = true;
    }

    @BeforeClass
    public static void setUpOnce() throws Exception{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        mqConnection = connectionFactory.createTopicConnection();
        session = mqConnection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(EVENT_TOPIC_NAME);
        consumer = new Consumer(session,topic);
        mqConnection.start();

        //System.out.println(consumer.getConsumedMessage().get(0));
    }



}
