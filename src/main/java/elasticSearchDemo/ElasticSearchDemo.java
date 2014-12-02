package elasticSearchDemo;

import bean.Student;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.io.IOException;

/**
 * @author: wenchengzhu Date: 14-11-19 Time: 下午8:55
 * @version: $Id$
 */
public class ElasticSearchDemo {
    static Client client = null;
    static {
        connect();
    }

    /**
     * 连接elasticsearch服务端
     */
    public static void connect() {
        // Settings settings = ImmutableSettings.settingsBuilder().put("client.transport.sniff",
        // true).put("cluster.name", "elasticSearch").build();
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticSearch").build();
        client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
    }

    /**
     * 创建索引
     * 
     * @throws IOException
     */
    public void createIndex() throws IOException {
        Student student = new Student(11111000, 1, "xiaoming", 22);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonValue = objectMapper.writeValueAsString(student);
        IndexResponse response = client.prepareIndex("student_index", "student_info", "stu11111000")
                .setSource(jsonValue).execute().actionGet();
        System.out.println("ID = " + response.getId() + "; index = " + response.getIndex() + "; type = "
                + response.getType());
    }

    /**
     * 根据id获取数据
     * 
     * @param id
     * @throws IOException
     */
    public void getInfoById(String id) throws IOException {
        GetResponse responseGet = client.prepareGet("student_index", "student_info", id).execute().actionGet();
        System.out.println(responseGet.getSourceAsString());
        ObjectMapper objectMapper = new ObjectMapper();
        Student object = objectMapper.readValue(responseGet.getSourceAsString(), Student.class);
    }

    /**
     * 查询索引
     */
    public void queryIndex() {
        SearchRequestBuilder builder = client.prepareSearch("student_index").setTypes("student_info")
                .setSearchType(SearchType.DEFAULT).setFrom(0).setSize(100);
        BoolQueryBuilder qb = QueryBuilders.boolQuery();// .must(new QueryStringQueryBuilder("xiaoming").field("body"))
        builder.setQuery(qb);
        SearchResponse response = builder.execute().actionGet();
        System.out.println("  " + response);
        System.out.println(response.getHits().getTotalHits());

    }

    /**
     * 删除索引
     */
    public void deleteIndex() {
        DeleteResponse response = client.prepareDelete("student_index", "student_info", "stu11111000")
                .setOperationThreaded(false).execute().actionGet();
        System.out.println(response.getId());
    }
}
