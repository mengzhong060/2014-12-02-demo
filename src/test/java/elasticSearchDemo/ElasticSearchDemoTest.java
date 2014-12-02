package elasticSearchDemo;

import org.junit.Test;

public class ElasticSearchDemoTest {
    ElasticSearchDemo elasticSearchDemo = new ElasticSearchDemo();
    @org.junit.Test
    public void testGetInfoById() throws Exception {
        elasticSearchDemo.createIndex();
        elasticSearchDemo.getInfoById("stu11111000");
    }

    @Test
    public void queryIndexTest() throws Exception {
        elasticSearchDemo.queryIndex();
    }
}
