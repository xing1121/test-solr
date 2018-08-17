package com.sf.wdx.test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient.Builder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sf.wdx.domain.Product;

public class TestSolr {
	
	/**
	 * solr的客户端，用来访问solr的服务端
	 */
	private static HttpSolrClient client;
	
	/**
	 * 初始化HttpSolrClient操作对象
	 *	@ReturnType	void 
	 *	@Date	2018年8月17日	下午4:22:33
	 *  @Param
	 */
	@BeforeClass
	public static void init(){
		String baseURL = "http://192.168.136.133:8081/solr";
		Builder builder = new Builder(baseURL);
		client = new HttpSolrClient(builder) {
			private static final long serialVersionUID = -6523464730358879561L;
		};
	}
	
	/**
	 * 简单搜索
	 *	@ReturnType	void 
	 *	@Date	2018年8月17日	下午4:42:38
	 *  @Param  @throws Exception
	 */
    @Test
    public void testSearchSimple() throws Exception{
        // 1.创建搜索条件SolrQuery
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        
        // 2.执行查询
        QueryResponse response = client.query(solrQuery);
        
        // 3.处理结果
        SolrDocumentList results = response.getResults();
        
        // 4.遍历结果
        for (SolrDocument solrDocument : results) {
        	Map<String, Object> map = new HashMap<>();
        	Collection<String> keys = solrDocument.getFieldNames();
        	
        	for (String key : keys) {
				Object value = solrDocument.get(key);
				map.put(key, value);
			}
        	String jsonString = JSON.toJSONString(map);
        	Product product = JSON.parseObject(jsonString, Product.class);
        	System.out.println(product);
            System.out.println("------------------------");
        }
        
    }
    
    /**
     * 删除索引
     *	@ReturnType	void 
     *	@Date	2018年8月17日	下午4:30:11
     *  @Param  @throws Exception
     */
    @Test
    public void testDeleteIndex() throws Exception{
        String id = "change.me1112222221";
        
        // 1.根据id删除 
        client.deleteById(id);
        
        //批量删除  清空所有记录
//        client.deleteByQuery("*:*");
        
        // 2.提交 
        client.commit();
        System.out.println("删除索引成功！");
    }
	
	/**
	 * 添加/更新（按照id）产品到solr的索引库
	 *	@ReturnType	void 
	 *	@Date	2018年8月17日	下午3:46:59
	 *  @Param  @throws Exception
	 */
	@Test
	public void insertOrUpdateProductToIndex() throws Exception {
		// 1.创建文档
		SolrInputDocument document = new SolrInputDocument();

		// 2.设置文档内容
		Product product = new Product(1L, "手机", "超级大大大屏幕", 5888L, 180, "http://www.xxx.com/xxx.jpg", 12L, 1);
		String jsonString = JSON.toJSONString(product);
		Map<String, Object> map = JSON.parseObject(jsonString, new TypeReference<Map<String, Object>>(){});
		for (Entry<String, Object> entry : map.entrySet()) {
			document.addField(entry.getKey(), entry.getValue());
		}
		
		// 3.添加文档到client
		client.add(document);

		// 4.提交到索引库
		client.commit();
		System.out.println("添加商品到索引库成功！");
	}

}
